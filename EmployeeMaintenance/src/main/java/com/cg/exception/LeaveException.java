
package com.cg.exception;

/**
 * @author Gagandeep
 * @time 2:57:49 pm
 * @date 11-Feb-2020
 */
public class LeaveException extends Exception{
	
	
	
	
	
	public LeaveException() {
		super();
		
	}

	@Override
	public String toString() {
		return "Leave with given ID doesnt exist";
	}

}
