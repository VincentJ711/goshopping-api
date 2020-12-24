package com.revature.goshopping.test;

import com.revature.goshopping.entity.ItemEntity;
import com.revature.goshopping.entity.OrderEntity;
import com.revature.goshopping.entity.TagEntity;
import com.revature.goshopping.entity.UserEntity;
import com.revature.goshopping.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

public class App {
  @Autowired
  static SessionFactory sessionFactory;

  public static void main(String[] args) throws Exception {
    // example1();
    // example2();
  }

  public static void example1() {
    try (Session session = HibernateUtility.getSession()) {
      Transaction tx = session.beginTransaction();

      TagEntity foodTag = new TagEntity("food");
      TagEntity entertainmentTag = new TagEntity("entertainment");

      // session.save(foodTag);
      // session.save(entertainmentTag);

      ItemEntity cupcakes =
          new ItemEntity(2.5F, "Cupcakes", "these are cupcakes");
      ItemEntity diehard = new ItemEntity(10F, "Die hard", "this is die hard");
      cupcakes.addTag(foodTag);
      diehard.addTag(entertainmentTag);

      // if u change these to save calls, u must uncomment the saves above.
      // save does not recursively persist the tags if they've not been saved.
      // persist does
      session.persist(cupcakes);
      session.persist(diehard);

      UserEntity hisham = new UserEntity("hisham", "123", true);

      // session.save(hisham);

      OrderEntity order =
          new OrderEntity(new Date(System.currentTimeMillis()), hisham);
      order.addItem(cupcakes, 2);
      order.addItem(diehard, 1);

      // session.persist(order);

      hisham.getOrders().add(order);
      session.persist(hisham);

      tx.commit();
    }
  }
}
