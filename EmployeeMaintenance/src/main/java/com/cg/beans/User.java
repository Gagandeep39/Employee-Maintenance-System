
package com.cg.beans;

import com.cg.enums.UserType;

/**
 * @author Gagandeep
 * @time 7:45:09 pm
 * @date 10-Feb-2020
 * i.	User_Master: UserId VARCHAR2(6), UserName VARCHAR2(15), UserPassword VARCHAR2(50), UserType VARCHAR2(10)
 * For Admin, and User (Employee), assume that the users are already added to the system.
 */
public class User {
	
	public static final int USER_ID_INIT = 1001;
	static int userIdAutoGen;
	static {
		userIdAutoGen = USER_ID_INIT;
	}
	private int userId;
	private String userName;
	private String userPassword;
	private UserType userType;
	
	
	
	public User(String userName, String userPassword, UserType userType) {
		super();
		this.userId = userIdAutoGen++;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userType = userType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userType="
				+ userType + "]";
	}
	
	

}
