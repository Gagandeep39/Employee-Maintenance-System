
package com.cg.service;

import java.util.HashMap;
import java.util.List;

import com.cg.beans.LeaveHistory;
import com.cg.enums.LeaveStatus;

/**
 * The Interface ManagerService. Manager interface containg all operations
 * performed by manager It extends Employee interface since Manager is also an
 * Employee
 *
 * @author Gagandeep
 * @time 2:20:18 pm
 * @date 11-Feb-2020
 */
public interface ManagerService extends EmployeeService {

	/**
	 * Approve leave requested by user.
	 *
	 * @param leaveId the leave id to be apprved
	 * @param status  the status of leave to whihc the current status is to be
	 *                changed
	 * @return the leaveId of the leave will be returned afteer update
	 * @see LeaveStatus
	 */
	int approveLeave(int leaveId, LeaveStatus status);

	/**
	 * Show all leaves stored in the system.
	 *
	 * @return the list
	 */
	List<LeaveHistory> showAllLeaves();

	/**
	 * Show all leaves of sub employees of the Manager.
	 *
	 * @param managerId the manager id whose subordinates leaves are to be shown
	 * @return the hash map containg leave of all managers sub employees
	 */
	HashMap<Integer, LeaveHistory> showAllLeavesOfSubEmployees(int managerId); // All em[ployes working under this
																				// manager will be displayed, has to be
																				// implemented

}
