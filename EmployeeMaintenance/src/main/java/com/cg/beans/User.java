
package com.cg.beans;

import com.cg.enums.UserType;

/**
 * User.java is a bean that stores login credentials of User and Admin
 * 
 * @author Gagandeep
 * @time 7:45:09 pm
 * @date 10-Feb-2020
 */
public class User {

	/**
	 * {@value #USER_ID_INIT} It is the starting value of user ID
	 */
	public static final int USER_ID_INIT = 100001;
	/**
	 * A variable that assigns a new ID to user object every time an object is
	 * created and increments the value by 1
	 */
	static int userIdAutoGen;
	/**
	 * Initialize auto-increment variable with {@value #USER_ID_INIT}
	 */
	static {
		userIdAutoGen = USER_ID_INIT;
	}
	/**
	 * Unique ID of the user
	 */
	private int userId;
	/**
	 * Username used to login
	 */
	private String userName;
	/**
	 * Password of the User
	 */
	private String userPassword;
	/**
	 * Type of the user (Admin, Employee)
	 */
	private UserType userType;

	/**
	 * Parameterized constructor to create a new User
	 * 
	 * @param userName
	 * @param userPassword
	 * @param userType
	 */
	public User(String userName, String userPassword, UserType userType) {
		super();
		this.userId = userIdAutoGen++;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userType = userType;
	}

	/**
	 * 
	 * @return user ID of the User
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * 
	 * @param userId Sets User ID of User
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 
	 * @return Username of user
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 * @param userName Sets Username of user
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 
	 * @return Password of user
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * 
	 * @param userPassword Sets password
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * 
	 * @return Type of User (Admin, Employee)
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * 
	 * @param userType Sets Type of User
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return A string containing all field Values of User
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userType="
				+ userType + "]";
	}

}
