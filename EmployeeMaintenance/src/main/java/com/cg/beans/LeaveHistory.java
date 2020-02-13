
package com.cg.beans;

import java.sql.Date;

import com.cg.enums.*;

/**
 * @author Gagandeep
 * @time 8:21:15 pm
 * @date 10-Feb-2020
 * v.Leave_History : Leave_Id number, Emp_id  foreign key references employee(emp_id), leave_balance number check (leave_balance>=0), 
 * noofdays_applied number, date_from date, date_to date, status varchar2(20) check (status in ('applied','approved','rejected'))
 * Leave_Id should be populated from a sequence.
 */

//Add new attribute for createdOn
public class LeaveHistory {
	
	public static final int LEAVE_ID_INIT = 1001;
	public static final int DEFAULT_LEAVE_BAL = 12;
	static int leaveIdAutoGen;
	static {
		leaveIdAutoGen = LEAVE_ID_INIT;
	}
	
	
	private int leaveId;
	private int empId;
	private int leaveBalance;
	private int noOfDaysApplied;
	private Date dateFrom;
	private Date dateTo;
	private LeaveStatus status;
	public LeaveHistory(int empId, int noOfDaysApplied, Date dateFrom, Date dateTo,
			LeaveStatus status) {
		super();
		this.leaveId = leaveIdAutoGen++;
		this.empId = empId;
		this.leaveBalance = DEFAULT_LEAVE_BAL;
		this.noOfDaysApplied = noOfDaysApplied;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.status = status;
	}
	public int getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getLeaveBalance() {
		return leaveBalance;
	}
	public void setLeaveBalance(int leaveBalance) {
		this.leaveBalance = leaveBalance;
	}
	public int getNoOfDaysApplied() {
		return noOfDaysApplied;
	}
	public void setNoOfDaysApplied(int noOfDaysApplied) {
		this.noOfDaysApplied = noOfDaysApplied;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public LeaveStatus getStatus() {
		return status;
	}
	public void setStatus(LeaveStatus status) {
		this.status = status;
	}
	
	

}
