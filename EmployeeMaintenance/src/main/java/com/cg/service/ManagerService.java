
package com.cg.service;

import java.util.HashMap;
import java.util.List;

import com.cg.beans.LeaveHistory;
import com.cg.enums.LeaveStatus;

/**
 * @author Gagandeep
 * @time 2:20:18 pm
 * @date 11-Feb-2020
 */
public interface ManagerService extends EmployeeService{
	
	int approveLeave(int leaveId, LeaveStatus status);
	List<LeaveHistory> showAllLeaves();	//
	HashMap<Integer, LeaveHistory> showAllLeavesOfSubEmployees(int managerId); //All em[ployes working under this manager will be displayed, has to be implemented

}

