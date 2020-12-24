package com.revature.goshopping.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.goshopping.entity.UserEntity;
import com.revature.goshopping.utility.PasswordUtility;

@Repository
@Transactional
public class UserDao {

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<UserEntity> getAllUsers() throws Exception {
		return sessionFactory.getCurrentSession().createQuery("from UserEntity").list();
	}

	public int addUser(UserEntity user) throws Exception {

		return (int) sessionFactory.getCurrentSession().save(user);
	}

	public UserEntity getUserById(int userId) throws Exception {
		return sessionFactory.getCurrentSession().get(UserEntity.class, userId);
	}

	public int updateUserPassword(int userId, String newHashedPassword) throws Exception {

		Query query = sessionFactory.getCurrentSession()
				.createQuery("update UserEntity m set m.password = :password where m.id= :id")
				.setParameter("password", newHashedPassword).setParameter("id", userId);
		return query.executeUpdate();
	}

	public UserEntity getUserByUsername(String username) throws NoResultException {
		Session session = sessionFactory.getCurrentSession();
		try {
			return session.createQuery("from UserEntity u where username= :username", UserEntity.class)
					.setParameter("username", username).getSingleResult();
			// catch NoResultException if user is not found and returns null
		} catch (NoResultException e) {
			return null;
		}
	}

	public int deleteUserByID(int userId) throws Exception {
		UserEntity delete = new UserEntity();
		delete.setId(userId);
		sessionFactory.getCurrentSession().delete(delete);
		return 1;
	}

}
