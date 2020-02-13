
package com.cg.exception;

/**
 * @author Gagandeep
 * @time 12:13:14 pm
 * @date 11-Feb-2020
 */
public class UserNotFoundException extends Exception {

	public UserNotFoundException() {
		super();

	}

	@Override
	public String toString() {
		return "User not found";
	}

	public UserNotFoundException(String arg0) {
		super(arg0);
		
	}

	
}
