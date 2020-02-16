
package com.cg.service;

import com.cg.beans.User;
import com.cg.exception.UserNotFoundException;

/**
 * @author Gagandeep
 * @time 7:47:17 pm
 * @date 12-Feb-2020
 */
public interface UserService {

	User login(int userId, String userPassword) ;
	boolean validate(User user, int userId, String userPassword);
	/**
	 * 
	 */
	int createUser(User user);
	/**
	 * @param uname
	 * @param password
	 * @return
	 */
	User login(String uname, String password);
}
