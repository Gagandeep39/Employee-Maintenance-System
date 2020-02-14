
package com.cg.service;

import com.cg.beans.User;
import com.cg.dao.DaoImpl;
import com.cg.dao.UserDao;
import com.cg.exception.UserNotFoundException;

/**
 * @author Gagandeep
 * @time 7:49:49 pm
 * @date 12-Feb-2020
 */
public class UserServiceImpl implements UserService{
	UserDao dao;

	public UserServiceImpl() {
		dao = DaoImpl.getDaoImpl();
	}

	@Override
	public User login(int userId, String userPassword){
		User user = dao.fetchUser(userId);
			try {
				if(user==null) {	//Only check if user exists or not
					throw new UserNotFoundException();
				}
				else 
					if(validate(user, userId,  userPassword)) {
						System.out.println("Successfully Logged In");
						return user;
					}else {
						System.out.println("Error in username or password");
					}
				
			} catch (UserNotFoundException e) {
				System.out.println(e);
			}
			return null;
	}

	@Override
	public boolean validate(User user, int userId, String userPassword){
		if(user.getUserId()==userId && user.getUserPassword().equals(userPassword))
			return true;
		return false;

	}

	@Override
	public int createUser(User user) {
		int userId = dao.addUser(user);
		System.out.println("Successfully Added user with Id: " + userId);
		return userId;
	}

}
