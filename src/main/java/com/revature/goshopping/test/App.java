package com.revature.goshopping.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.goshopping.model.User;
import com.revature.goshopping.utility.HibernateUtility;

public class App {

	public static void main(String[] args) {

		try (Session session = HibernateUtility.getSession()) {

			Transaction tx = session.beginTransaction();

			User user = new User("hisham", "123", true);
//			
			session.save(user);
			tx.commit();
//			System.out.println(id);
		}

	}

}
