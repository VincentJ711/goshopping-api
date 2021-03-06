package com.revature.goshopping.controller;

import com.revature.goshopping.dto.Auth;
import com.revature.goshopping.dto.Order;
import com.revature.goshopping.service.OrderService;
import com.revature.goshopping.utility.ControllerUtility;
import com.revature.goshopping.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
  @Autowired
  OrderService orderService;

  @Autowired
  private JwtUtility jwtUtility;

  /**
   * this posts an order to be recorded
   */
  @PostMapping
  public ResponseEntity<Order> postOrder(@RequestBody Order order,
      @RequestHeader Map<String, String> headers) {
    Auth auth = jwtUtility.getAuth(headers);
    return ControllerUtility
        .handle(() -> orderService.createOrder(auth, order));
  }

  @GetMapping
  public ResponseEntity<List<Order>> getOrders(
      @RequestHeader Map<String, String> headers,
      @RequestParam(required = false) Integer uid,
      @RequestParam(required = false) String username) {
    Auth auth = jwtUtility.getAuth(headers);
    if (uid != null && username == null) {
      return ControllerUtility.handle(() -> orderService.getOrders(auth, uid));
    } else {
      return ControllerUtility.handle(() -> orderService.getOrders(auth, username, uid));
    }
  }

  /**
   * This returns the order with the given id
   */
  @GetMapping("/{id}")
  public ResponseEntity<Order> getOrderById(@PathVariable("id") int id) {
    return ControllerUtility.handle(() -> orderService.getOrder(id));
  }
}
