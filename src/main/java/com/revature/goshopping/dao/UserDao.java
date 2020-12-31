package com.revature.goshopping.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.goshopping.entity.UserEntity;

@Repository
@Transactional
public class UserDao {

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<UserEntity> getAllUsers() throws Exception {
		return sessionFactory.getCurrentSession().createQuery("from UserEntity").list();
	}

	public int addUser(UserEntity user) throws ConstraintViolationException, Exception {

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

	public void deleteUserByID(int userId) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("delete from UserEntity where id = " + userId)
				.executeUpdate();
	}
}
