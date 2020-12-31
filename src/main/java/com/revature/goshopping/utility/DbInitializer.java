package com.revature.goshopping.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.goshopping.dto.Item;
import com.revature.goshopping.dto.Order;
import com.revature.goshopping.dto.OrderItem;
import com.revature.goshopping.dto.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * this class is meant to initialize our backend database with dummy data so
 * we don't have to manually create the data each time our database is
 * created. this class makes http requests to the api and lets the api make
 * the necessary insertions into the database.
 */
class DbInitializer {
  /**
   * looks like http://localhost:4321/api
   */
  private final String url = Objects.requireNonNull(System.getenv("API_URL"));

  /**
   * an admin jwt token so the calls to the api are properly authenticated
   */
  private final String jwt = Objects.requireNonNull(System.getenv("ADMIN_JWT"));

  private final String newUserPassword = Objects.requireNonNull(
      System.getenv("NEW_USER_PASSWORD"));

  private final ObjectMapper mapper = new ObjectMapper();

  private final LinkedMultiValueMap<String, String> headers;

  public DbInitializer() {
    headers = new LinkedMultiValueMap<>();
    headers.add("Authorization", "Bearer " + jwt);
  }

  public static void main(String[] args) throws IOException {
    DbInitializer dbi = new DbInitializer();
    dbi.populate();
  }

  private void populate() throws IOException {
    User[] users = populateUsers();
    Item[] items = populateItems();
    populateOrders(users, items);
  }

  private User[] populateUsers() throws IOException {
    String json = readJsonFrom("dummyusers.json");
    User[] users = mapper.readValue(json, User[].class);

    for (int i = 0; i < users.length; i++) {
      User u = users[i];
      u.setPassword(newUserPassword);
      User made = makePost("/user", users[i], User.class);
      users[i] = made;
      System.out.println("posted " + made);
    }

    return users;
  }

  private Item[] populateItems() throws IOException {
    String json = readJsonFrom("dummyitems.json");
    Item[] items = mapper.readValue(json, Item[].class);

    for (int i = 0; i < items.length; i++) {
      if (items[i].getName().length() > 250) {
        items[i].setName(items[i].getName().substring(0, 250));
      }

      Item made = makePost("/item", items[i], Item.class);
      items[i] = made;

      if (((i + 1) % 100) == 0) {
        System.out.println("posted " + i + " items");
      }
    }

    System.out.println("posted " + items.length + " items");

    return items;
  }

  private void populateOrders(User[] users, Item[] items) {
    Pair<Integer, Item> curr = new Pair<>(-1, null);
    List<Order> todo = new ArrayList<>();

    for (User user : users) {
      todo.add(new Order(user.getId(), Collections.emptyList()));
      todo.add(new Order(user.getId(), Collections.singletonList(
          new OrderItem(nextItem(items, curr).second.getId(), 1)
      )));
      todo.add(new Order(user.getId(), Arrays.asList(
          new OrderItem(nextItem(items, curr).second.getId(), 1),
          new OrderItem(nextItem(items, curr).second.getId(), 2)
      )));
    }

    for (Order order : todo) {
      Order made = makePost("/order", order, Order.class);
      System.out.println("posted " + made);
    }
  }

  private Pair<Integer, Item> nextItem(Item[] items, Pair<Integer, Item> curr) {
    while (curr.first++ < items.length) {
      Item item = items[curr.first];
      if (item.getDescription().length() > 0) {
        curr.second = item;
        return curr;
      }
    }
    throw new RuntimeException("couldnt find item.");
  }

  private String readJsonFrom(String resourceFileName) throws IOException {
    Resource resource = new ClassPathResource(resourceFileName);
    InputStream is = resource.getInputStream();
    InputStreamReader ir = new InputStreamReader(is, StandardCharsets.UTF_8);
    return new BufferedReader(ir).lines().collect(Collectors.joining("\n"));
  }

  private <T> T makePost(String route, T o, Class<T> cls) {
    HttpEntity<T> req = new HttpEntity<>(o, headers);
    return new RestTemplate().postForEntity(url + route, req, cls).getBody();
  }
}
