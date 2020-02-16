
package com.cg.dao;

import java.util.HashMap;
import java.util.List;

import com.cg.beans.User;


/**
 * The Interface UserDao.
 * Contains signature of various operation that can be carried on employee list
 * @author Gagandeep
 * @time 7:50:04 pm
 * @date 10-Feb-2020
 */
public interface UserDao {
	
	/**
	 * Fetches the user map containg all user in the system
	 *
	 * @return the user map
	 */
	HashMap<Integer, User> getUserMap();
	
	/**
	 * Fetch user based on ID
	 *
	 * @param userId the user id
	 * @return the user object conating user data having id passed as a parameter
	 */
	User fetchUser(int userId);
	
	/**
	 * Adds the user to userMap
	 *
	 * @param user the user object that will be added to the userMap
	 * @return the userId that will be returned after insertion if data
	 */
	int addUser(User user);
	
	/**
	 * Update user replacing previous user object with new one
	 *
	 * @param user the user
	 * @return the int
	 */
	int updateUser(User user);
	
	/**
	 * Delete user based on ID
	 *
	 * @param userId the user id
	 * @return the user
	 */
	User deleteUser(int userId);
}
