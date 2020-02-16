
package com.cg.service;

import java.util.HashMap;
import java.util.Optional;

import com.cg.beans.User;
import com.cg.dao.DaoImpl;
import com.cg.dao.UserDao;
import com.cg.exception.UserNotFoundException;

/**
 * The Class UserServiceImpl.
 *
 * @author Gagandeep
 * @time 7:49:49 pm
 * @date 12-Feb-2020
 */
public class UserServiceImpl implements UserService {

	/** The dao object to perform data access operation of user */
	UserDao dao;

	/**
	 * Instantiates a new user service impl.
	 */
	public UserServiceImpl() {
		dao = DaoImpl.getDaoImpl();
	}

	/**
	 * Login operation is performed which returns User object os successfully logged
	 * in user
	 *
	 * @param userId       the user id
	 * @param userPassword the user password
	 * @return the user
	 */
	@Override
	public User login(int userId, String userPassword) {
		User user = dao.fetchUser(userId);
		try {
			if (user == null) { // Only check if user exists or not
				throw new UserNotFoundException();
			} else if (validate(user, userId, userPassword)) {
				System.out.println("Successfully Logged In");
				return user;
			} else {
				System.out.println("Error in username or password");
			}

		} catch (UserNotFoundException e) {
			System.out.println(e);
		}
		return null;
	}

	/**
	 * Validate userId or Password
	 *
	 * @param user         the user object fetched from system
	 * @param userId       the user id entered by user to be validated against data
	 *                     in system
	 * @param userPassword the user password entered by the user
	 * @return true, if successful
	 */
	@Override
	public boolean validate(User user, int userId, String userPassword) {
		if (user.getUserId() == userId && user.getUserPassword().equals(userPassword))
			return true;
		return false;

	}

	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @return the int
	 */
	@Override
	public int createUser(User user) {
		int userId = dao.addUser(user);
		System.out.println("Successfully Added user with Id: " + userId);
		return userId;
	}

	/**
	 * Login.
	 *
	 * {@inheritDoc}}
	 * 
	 * After successfull login a welcome screen is also provided to the user
	 */
	@Override
	public User login(String uname, String password) {
		HashMap<Integer, User> userMap = dao.getUserMap();
		if (userMap.size() == 0)
			return null;
		else {
			Optional<User> user = userMap.values().stream().filter(u -> u.getUserName().equals(uname)).findFirst();
			if (!user.isPresent())
				System.out.println("Username Not found");
			else if (!user.get().getUserPassword().equals(password)) {
				System.out.println("Invalid Password");
			} else {
				System.out.println("************************************************\n");
				System.out.println("         Welcome " + uname + " (ID: " + user.get().getUserId() + ")         ");
				System.out.println("\n************************************************\n");
				return user.get();
			}
		}
		return null;
	}

}
