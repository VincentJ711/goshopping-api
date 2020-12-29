package com.revature.goshopping.service;

import com.revature.goshopping.config.Env;
import com.revature.goshopping.dao.ItemDao;
import com.revature.goshopping.dao.OrderDao;
import com.revature.goshopping.dao.UserDao;
import com.revature.goshopping.dto.Auth;
import com.revature.goshopping.dto.Order;
import com.revature.goshopping.dto.OrderItem;
import com.revature.goshopping.entity.ItemEntity;
import com.revature.goshopping.entity.ItemOrderEntity;
import com.revature.goshopping.entity.OrderEntity;
import com.revature.goshopping.entity.UserEntity;
import com.revature.goshopping.exception.ServiceException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {
  @Autowired
  OrderDao orderDao;

  @Autowired
  UserDao userDao;

  @Autowired
  ItemDao itemDao;

  @Autowired
  private Env env;

  /**
   * @return the order with the given id
   */
  public Order getOrder(int id) throws Exception {
    OrderEntity order = orderDao.getOrder(id);

    if (order == null) {
      throw new ServiceException(HttpStatus.NOT_FOUND);
    }

    return new Order(order);
  }

  /**
   * @return list of all the orders in the database for the user with the
   *     given userID
   */
  public List<Order> getOrders(Auth auth, int userID) throws Exception {
    if (auth == null) {
      throw new ServiceException(HttpStatus.UNAUTHORIZED);
    } else if (!auth.isAdmin() && (auth.getId() != userID)) {
      throw new ServiceException(HttpStatus.FORBIDDEN);
    }
    return fromOrderEntities(orderDao.getOrdersForUser(userID));
  }

  /**
   * @return list of all orders in the database.
   */
  public List<Order> getOrders(Auth auth, String userSearch, Integer uidSearch) throws Exception {
    if (auth == null) {
      throw new ServiceException(HttpStatus.UNAUTHORIZED);
    } else if (!auth.isAdmin()) {
      throw new ServiceException(HttpStatus.FORBIDDEN);
    }
    return fromOrderEntities(orderDao.getAllOrders(userSearch, uidSearch));
  }

  private List<Order> fromOrderEntities(List<OrderEntity> orders) {
    List<Order> out = new ArrayList<>();

    for (OrderEntity o : orders) {
      out.add(new Order(o));
    }

    return out;
  }

  /**
   * creates an order for the user with the userID on the given order.
   *
   * @return the order we created.
   */
  public Order createOrder(Auth auth, Order givenOrder) throws Exception {
    if (givenOrder == null) {
      throw new ServiceException(HttpStatus.BAD_REQUEST);
    } else if (auth == null) {
      throw new ServiceException(HttpStatus.UNAUTHORIZED);
    } else if (givenOrder.getStripeToken() == null && !auth.isAdmin()) {
    	throw new ServiceException(HttpStatus.BAD_REQUEST, "No card " +
                "information has been provided!!");
    }

    int userID = givenOrder.getUserID();

    if (!auth.isAdmin() && (auth.getId() != userID)) {
      throw new ServiceException(HttpStatus.FORBIDDEN);
    }

    UserEntity user = userDao.getUserById(userID);

    if (user == null) {
      throw new ServiceException(HttpStatus.BAD_REQUEST, "can't create an " +
          "order for a user that doesn't exist!");
    }

    List<OrderItem> givenItems = new ArrayList<>();
    Set<ItemOrderEntity> itemsForOrderToMake = new HashSet<>();
    OrderEntity orderToMake = new OrderEntity(
        new Date(System.currentTimeMillis()),
        user
    );

    if (givenOrder.getItems() != null) {
      givenItems = givenOrder.getItems();
    }

    // total of the order = total price of the items in the order
    float total = 0;

    for (OrderItem oi : givenItems) {
      if (oi == null) {
        continue;
      }

      ItemEntity ie = itemDao.getItem(oi.getId());
      
      int quantity = oi.getQuantity();

      if (ie == null) {
        throw new ServiceException(HttpStatus.BAD_REQUEST, "you provided an " +
            "item in the order that doesnt exist!");
      } else if (quantity < 1) {
        throw new ServiceException(HttpStatus.BAD_REQUEST, "you cannot " +
            "provide an item in the order with a quantity < 1");
      }

      // add the price for each item to the order total
      total += ie.getPrice();
      itemsForOrderToMake.add(new ItemOrderEntity(ie, orderToMake, quantity));
    }

    if (givenOrder.getStripeToken() != null) {
      payForOrder((long) (total * 100), givenOrder.getStripeToken());
    }

    orderToMake.setItemOrders(itemsForOrderToMake);
    orderDao.addOrder(orderToMake);
    return new Order(orderToMake);
  }

  private void payForOrder(long amountInPennies, String token)
      throws Exception {
    Stripe.apiKey = env.stripeKey;

    PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
        .setAmount(amountInPennies)
        .setCurrency("usd")
        .setConfirm(true)
        .setPaymentMethod(token)
        .build();

    try {
      PaymentIntent paymentIntent = PaymentIntent.create(params);
      if (!paymentIntent.getStatus().contentEquals("succeeded")) {
        throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } catch (StripeException e) {
      String errorMessage = e.getMessage();
      throw new ServiceException(HttpStatus.PAYMENT_REQUIRED,
          errorMessage.substring(0, errorMessage.indexOf(';')));
    }
  }
}
