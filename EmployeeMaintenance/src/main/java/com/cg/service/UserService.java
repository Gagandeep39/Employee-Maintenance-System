
package com.cg.service;

import com.cg.beans.User;
import com.cg.exception.UserNotFoundException;

/**
 * The Interface UserService. UserService interface contains signature of
 * different operations related to Login Signature of both userid + password and
 * username + password is created, any of them can be used for login
 *
 * @author Gagandeep
 * @time 7:47:17 pm
 * @date 12-Feb-2020
 */
public interface UserService {

	/**
	 * Login operation is performed which returns User object os successfully logged
	 * in user
	 *
	 * @param userId       the user id
	 * @param userPassword the user password
	 * @return the user
	 */
	User login(int userId, String userPassword);

	/**
	 * Validate userId or Password
	 *
	 * @param user         the user object fetched from system
	 * @param userId       the user id entered by user to be validated against data
	 *                     in system
	 * @param userPassword the user password entered by the user
	 * @return true, if successful
	 */
	boolean validate(User user, int userId, String userPassword);

	/**
	 * Creates the user.
	 *
	 * @param user the user object to be added to the system
	 * @return the int
	 */
	int createUser(User user);

	/**
	 * Login to the system using username and password
	 *
	 * @param uname    the uname
	 * @param password the password
	 * @return the user
	 */
	User login(String uname, String password);
}
