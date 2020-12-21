package com.revature.goshopping.dao;

import com.revature.goshopping.entity.UserEntity;

public class UserDaoForLoginService {

	/**
	 * im not sure if hibernate throws an error if user is not found. if it does,
	 * catch that SPECIFIC error, and return null. dont catch any other errors.
	 *
	 * @return nullable UserEntity
	 */
	public UserEntity fromUsername(String username) throws Exception {
		return null;
	}
	// public UserEntity fromUsername(String username) throws Exception {
	// return null;
	// Hisham's code: implemented the following method for testing purposes only---
	// who's going to build the DAO
	// can change it

//	@Autowired
//	SessionFactory sessionFactory;
//	
//	public UserEntity fromUsername(String username) throws NoResultException {
//		Session session = sessionFactory.getCurrentSession();
//		try {
//			return session.createQuery("from UserEntity u where username= :username", UserEntity.class)
//					.setParameter("username", username).getSingleResult();
//			// catch NoResultException if user is not found and returns null
//		}catch (NoResultException e) {
//			return null;
//		}
//  }

}
