package com.revature.goshopping.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.revature.goshopping.dao.UserDAOForUserService;
import com.revature.goshopping.dto.Auth;
import com.revature.goshopping.dto.SwapPassword;
import com.revature.goshopping.dto.User;
import com.revature.goshopping.entity.UserEntity;
import com.revature.goshopping.exception.ServiceException;
import com.revature.goshopping.utility.PasswordUtility;

@Service
public class UserService {
	@Autowired
	UserDAOForUserService userDAO;

	public List<User> getUserFromService(Auth auth) throws Exception {

		List<User> users = new ArrayList<>();
		if (auth == null) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED);
		} else if (!auth.isAdmin()) {
			throw new ServiceException(HttpStatus.FORBIDDEN);
		}

		for (UserEntity u : userDAO.getUsers()) {
			users.add(new User(u.getId(), u.getUsername(), u.isAdmin(), null));
		}

		return users;
	}

	/**
	 * Post a user to database and return the user if the operation is succeed
	 * otherwise return null.
	 * 
	 * @param auth
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	public User postUserFromService(User user) throws Exception {
		UserEntity userEntity = new UserEntity(user.getUsername(), PasswordUtility.hash(user.getPassword()),
				user.getAdmin());

		userDAO.postUser(userEntity);
		return new User(userEntity.getId(), userEntity.getUsername(), userEntity.isAdmin(), null);

	}

	public User findUserFromService(Auth auth, int id) throws Exception {
		User user = null;
		if (auth == null) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED);
		} else if (!auth.isAdmin() && auth.getId() != id) {
			throw new ServiceException(HttpStatus.FORBIDDEN);
		}

		UserEntity u = userDAO.getUserById(id);
		if (u == null) {
			throw new ServiceException(HttpStatus.NOT_FOUND);
		}
		user = new User(u.getId(), u.getUsername(), u.isAdmin(), null);

		return user;
	}

	public void deleteUserFromService(Auth auth, int id) throws Exception {
		if (auth == null) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED);
		} else if (!auth.isAdmin()) {
			throw new ServiceException(HttpStatus.FORBIDDEN);

		}

		userDAO.deleteUserById(id);

	}

	public User updateUserFromService(Auth auth, SwapPassword swap) throws Exception {

		if (auth == null) {
			throw new ServiceException(HttpStatus.FORBIDDEN);
		}
		UserEntity u = userDAO.getUserById(auth.getId());
		if (u == null) {
			throw new ServiceException(HttpStatus.NOT_FOUND);
		}
		if (!PasswordUtility.doesMatch(swap.getOldPass(), u.getPassword())) {
			throw new ServiceException(HttpStatus.BAD_REQUEST);
		}

		String hashedPassword = PasswordUtility.hash(swap.getNewPass());
		userDAO.updateUserPassword(auth.getId(), hashedPassword);

		return new User(u.getId(), u.getUsername(), u.isAdmin(), null);

	}
}
