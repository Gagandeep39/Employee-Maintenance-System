
package com.cg.dao;

import java.util.HashMap;
import java.util.List;

import com.cg.beans.User;

/**
 * @author Gagandeep
 * @time 7:50:04 pm
 * @date 10-Feb-2020
 */
public interface UserDao {
	HashMap<Integer, User> getUserMap();
	User fetchUser(int userId);
	int addUser(User user);
	int updateUser(User user);
	User deleteUser(int userId);
}
