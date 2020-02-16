
package com.cg.dao;

import java.util.HashMap;

import com.cg.beans.LeaveHistory;

/**
 * The Interface LeaveDao.
 * It contains signature of various operations that can be carried out on various Leave
 * 
 *
 * @author Gagandeep
 * @time 11:09:31 am
 * @date 11-Feb-2020
 */
public interface LeaveDao {
	
	/**
	 * Creates the leave request and adds it on leaveMap
	 *
	 * @param leave the leave Object to be iserted
	 * @return the leaveId of the newly inserted leaveHistory
	 */
	int createLeave(LeaveHistory leave);
	
	/**
	 * Update leaves.
	 *
	 * @param leave the leave object that replaces previous object
	 * @return the leaveId that has been overwritten
	 */
	int updateLeaves(LeaveHistory leave);
	
	/**
	 * Fetch leave history.
	 *
	 * @param leaveId the leave id through which leaves are fetched
	 * @return the leave history
	 */
	LeaveHistory fetchLeaveHistory(int leaveId);
	
	/**
	 * Show all leaves.
	 *
	 * @return the hash map containing all leaves
	 */
	HashMap<Integer, LeaveHistory> showAllLeaves();

}
