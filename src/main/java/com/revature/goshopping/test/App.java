package com.revature.goshopping.test;


import com.revature.goshopping.config.AppConfig;
import com.revature.goshopping.dao.ItemDao;
import com.revature.goshopping.dto.Item;
import com.revature.goshopping.dto.Tag;
import com.revature.goshopping.entity.ItemEntity;
import com.revature.goshopping.entity.OrderEntity;
import com.revature.goshopping.entity.TagEntity;
import com.revature.goshopping.entity.UserEntity;
import com.revature.goshopping.service.ItemService;
import com.revature.goshopping.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;

public class App {
	
	public static void main(String[] args) throws Exception {
//    example1();
		//example2();
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ItemService serv = context.getBean(ItemService.class);
		System.out.println(serv.getItems());
		System.out.println(serv.getItem(12));
		System.out.println(serv.searchItems("cup"));
		System.out.println(serv.getItemsbyTag("food"));
		System.out.println(serv.searchItems("cup", "nothing"));
//		Item cupcakes = new Item(2.5F, "Cupcakes", "these are cupcakes");
//		Tag foodTag = new Tag("food");
//		cupcakes.addTag(foodTag);
//		serv.addItem(cupcakes);
//		serv.removeItem(16);
//		ItemDao itemDao = context.getBean(ItemDao.class);
//		TagEntity foodTag = new TagEntity("food");
//		TagEntity entertainmentTag = new TagEntity("entertainment");
//		ItemEntity cupcakes = new ItemEntity(2.5F, "Cupcakes", "these are cupcakes");
//		ItemEntity diehard = new ItemEntity(10F, "Die hard", "this is die hard");
//		cupcakes.addTag(foodTag);
//		diehard.addTag(entertainmentTag);
//		itemDao.addItem(cupcakes);
//		itemDao.addItem(diehard);
//		System.out.println(itemDao.getItems());
//		System.out.println(itemDao.getTag("food"));
//		System.out.println(itemDao.getTag("nothing"));
//		System.out.println(itemDao.searchItems("die", "entertainment"));
//		itemDao.searchItems("cup");
//		itemDao.getItemsbyTag("food");
	}

	public static void example1() {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();

			TagEntity foodTag = new TagEntity("food");
			TagEntity entertainmentTag = new TagEntity("entertainment");

			// session.save(foodTag);
			// session.save(entertainmentTag);

			ItemEntity cupcakes = new ItemEntity(2.5F, "Cupcakes", "these are cupcakes");
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

			OrderEntity order = new OrderEntity(new Date(System.currentTimeMillis()), hisham);
			order.addItem(cupcakes, 2);
			order.addItem(diehard, 1);

			// session.persist(order);

			hisham.getOrders().add(order);
			session.persist(hisham);

			tx.commit();
		}
	}
}
