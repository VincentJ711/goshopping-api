package com.revature.goshopping.dao;

import java.util.List;

import javax.persistence.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.goshopping.dto.User;
import com.revature.goshopping.entity.UserEntity;
import com.revature.goshopping.utility.PasswordUtility;

@Repository
public class UserDAOForUserService {
	@Autowired
	SessionFactory sessionFactory;

	public List<UserEntity> getUsers() throws Exception {
		return null;
	}

	public UserEntity postUser(UserEntity user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public User deleteUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateUserPassword(int userId, String newPassword) throws Exception {
		// hash the new password
		String hash = PasswordUtility.hash(newPassword);
		Query query = sessionFactory.getCurrentSession()
				.createQuery("update UserEntity m set m.password = :password where m.id= :id")
				.setParameter("password", hash).setParameter("id", userId);
		return query.executeUpdate();
	}

	public UserEntity getUserById(int userId) throws Exception {
		return sessionFactory.getCurrentSession().get(UserEntity.class, userId);
	}

}
