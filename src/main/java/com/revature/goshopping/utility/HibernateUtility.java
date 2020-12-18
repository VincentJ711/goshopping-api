package com.revature.goshopping.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {

	private static SessionFactory sessionFactory;

	public static Session getSession() {
		if (sessionFactory == null) {
			// instantiate a sessionFactory
			sessionFactory = new Configuration()
					.setProperty("hibernate.connection.username", System.getenv("DB_USERNAME"))
					.setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"))
					.setProperty("hibernate.connection.url", System.getenv("DB_URL"))
					.setProperty("hibernate.hbm2ddl.auto", System.getenv("HBM2_DDL_AUTO"))
					.configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return sessionFactory.openSession();
	}

}
