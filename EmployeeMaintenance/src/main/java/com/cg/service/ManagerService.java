
package com.cg.service;

import java.util.List;

import com.cg.beans.LeaveHistory;

/**
 * @author Gagandeep
 * @time 2:20:18 pm
 * @date 11-Feb-2020
 */
public interface ManagerService extends EmployeeService{
	
	int approveLeave(int leaveId);
	List<LeaveHistory> showAllLeaves();	//
//	List<LeaveHistory> showAllLeavesOfSubEmployees(int managerId); //All em[ployes working under this manager will be displayed, has to be implemented
}
