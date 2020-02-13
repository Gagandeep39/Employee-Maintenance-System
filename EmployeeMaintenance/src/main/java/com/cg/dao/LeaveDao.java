
package com.cg.dao;

import java.util.HashMap;

import com.cg.beans.LeaveHistory;

/**
 * @author Gagandeep
 * @time 11:09:31 am
 * @date 11-Feb-2020
 */
public interface LeaveDao {
	
	int createLeave(LeaveHistory leave);
	int updateLeaves(LeaveHistory leave);
	LeaveHistory fetchLeaveHistory(int leaveId);
	HashMap<Integer, LeaveHistory> showAllLeaves();

}
