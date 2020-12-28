package com.revature.goshopping.controller;

import com.revature.goshopping.dto.Auth;
import com.revature.goshopping.dto.Item;
import com.revature.goshopping.dto.Tag;
import com.revature.goshopping.service.ItemService;
import com.revature.goshopping.utility.ControllerUtility;
import com.revature.goshopping.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ItemController {
  @Autowired
  private ItemService serv;

  @GetMapping("/item")
  public ResponseEntity<List<Item>> searchItems(
      @RequestParam(required = false, name = "text") String text,
      @RequestParam(required = false, name = "tag") String tag,
      @RequestParam(required = false, name = "quantity") Integer quantity,
      @RequestParam(required = false, name = "page") Integer page) {
    return ControllerUtility.handle(() ->
        serv.searchItems(text, tag, quantity, page));
  }

  @GetMapping("/item/{id}")
  public ResponseEntity<Item> getItem(@PathVariable int id) {
    return ControllerUtility.handle(() -> serv.getItem(id));
  }

  @DeleteMapping("/item/{id}")
  public ResponseEntity<Integer> deleteItem(@PathVariable int id,
      @RequestHeader Map<String, String> headers) {
    Auth auth = JwtUtility.getAuth(headers);
    return ControllerUtility.handle(() -> serv.removeItem(id, auth));
  }

  @PutMapping("/item/{id}")
  public ResponseEntity<Item> putItem(@PathVariable int id,
      @RequestBody Item item, @RequestHeader Map<String, String> headers) {
    Auth auth = JwtUtility.getAuth(headers);
    return ControllerUtility.handle(() -> serv.updateItem(item, id, auth));
  }

  @PostMapping("/item")
  public ResponseEntity<Item> postItem(@RequestBody Item item,
      @RequestHeader Map<String, String> headers) {
    Auth auth = JwtUtility.getAuth(headers);
    return ControllerUtility.handle(() -> serv.addItem(item, auth));
  }

  @GetMapping("/tags")
  public ResponseEntity<List<Tag>> getTags() {
    return ControllerUtility.handle(() -> serv.getTags());
  }
}
