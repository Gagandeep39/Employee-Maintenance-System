
package com.cg.beans;


import java.time.LocalDate;

import com.cg.enums.*;

/**
 * 
 * LeaveHistory.java is a bean which contains necessary data about the leave taken by the employee
 * 
 * @author Gagandeep
 * @time 8:21:15 pm
 * @date 10-Feb-2020
 */

//Add new attribute for createdOn
public class LeaveHistory {
	
	/**
	 * {@value #LEAVE_ID_INIT} It is the starting value of Leave ID
	 */
	public static final int LEAVE_ID_INIT = 1001;
	/**
	 * {@value #DEFAULT_LEAVE_BAL} It is the default max available leaves
	 */
	public static final int DEFAULT_LEAVE_BAL = 12;
	/**
	 * An ID variable that auto-increment after each constructor call
	 */
	static int leaveIdAutoGen;
	/**
	 * Initializes Auto-increment variable with {@value #LEAVE_ID_INIT}
	 */
	static {
		leaveIdAutoGen = LEAVE_ID_INIT;
	}
	
	/**
	 * Primary key for leave created
	 */
	private int leaveId;
	/**
	 * Employee ID to which the leave is associated with
	 */
	private int empId;
	/**
	 * The number of leaves that the Empoyee can take
	 */
	private int leaveBalance;
	/**
	 * Date on Which the leave was Applied
	 */
	private LocalDate appliedOn;
	/**
	 * Number of days for which leave was applied
	 */
	private int noOfDaysApplied;
	/**
	 * Start date of Leave
	 */
	private LocalDate dateFrom;
	/**
	 * End date of Leave
	 */
	private LocalDate dateTo;
	/**
	 * Current Status of the Leave (Applied, Approved, Rejected)
	 */
	private LeaveStatus status;
	/**
	 * Parameterized constructor to assign values to various Field of LeaveHistory Object
	 * 
	 * @param empId
	 * @param leaveBalance
	 * @param noOfDaysApplied
	 * @param dateFrom
	 * @param dateTo
	 * @param status
	 */
	public LeaveHistory(int empId, int leaveBalance, int noOfDaysApplied, LocalDate dateFrom, LocalDate dateTo,
			LeaveStatus status) {
		super();
		this.leaveId = leaveIdAutoGen++;
		this.empId = empId;
		this.leaveBalance =leaveBalance;
		this.appliedOn = LocalDate.now();
		this.noOfDaysApplied = noOfDaysApplied;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.status = status;
	}
	
	/**
	 * 
	 * @return Leave ID of Leave
	 */
	public int getLeaveId() {
		return leaveId;
	}
	/**
	 * 
	 * @param leaveId Set Leave ID
	 */
	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}
	/**
	 * 
	 * @return Employee ID to which Leave is associated with
	 */
	public int getEmpId() {
		return empId;
	}
	/**
	 * 
	 * @param empId Sets the employee ID to the leave
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	/**
	 * 
	 * @return Max number of Leave available
	 */
	public int getLeaveBalance() {
		return leaveBalance;
	}
	/**
	 * 
	 * @param leaveBalance Sets Max available Leaves
	 */
	public void setLeaveBalance(int leaveBalance) {
		this.leaveBalance = leaveBalance;
	}
	/**
	 * 
	 * @return Number of Days the leave is applied for
	 */
	public int getNoOfDaysApplied() {
		return noOfDaysApplied;
	}
	/**
	 * 
	 * @param noOfDaysApplied Sets Number of Days of Leave
	 */
	public void setNoOfDaysApplied(int noOfDaysApplied) {
		this.noOfDaysApplied = noOfDaysApplied;
	}
	/**
	 * 
	 * @return Start date of Leave
	 */
	public LocalDate getDateFrom() {
		return dateFrom;
	}
	/**
	 * 
	 * @param dateFrom Sets Start date of Leave
	 */
	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}
	/**
	 * 
	 * @return End date of Leave
	 */
	public LocalDate getDateTo() {
		return dateTo;
	}
	/**
	 * 
	 * @param dateTo Sets End date of Leave
	 */
	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}
	/**
	 * 
	 * @return Current Leave Status
	 */
	public LeaveStatus getStatus() {
		return status;
	}
	/**
	 * 
	 * @param status Sets Current Leave Status
	 */
	public void setStatus(LeaveStatus status) {
		this.status = status;
	}
	
	/**
	 * {@inheritDoc}
	 * @return A string containing all field Values of LeaveHistory
	 */
	@Override
	public String toString() {
		return "LeaveHistory [leaveId=" + leaveId + ", empId=" + empId + ", leaveBalance=" + leaveBalance
				+ ", appliedOn=" + appliedOn + ", noOfDaysApplied=" + noOfDaysApplied + ", dateFrom=" + dateFrom
				+ ", dateTo=" + dateTo + ", status=" + status + "]";
	}
	
	
	

}
