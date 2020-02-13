
package com.cg.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.cg.beans.LeaveHistory;
import com.cg.dao.EmployeeDao;
import com.cg.dao.LeaveDao;
import com.cg.dao.UserDao;
import com.cg.enums.LeaveStatus;
import com.cg.exception.LeaveException;

/**
 * @author Gagandeep
 * @time 9:57:12 am
 * @date 13-Feb-2020
 */
public class ManagerServiceImpl extends EmployeeServiceImpl implements ManagerService{

	
	
	@Override
	public int approveLeave(int leaveId) {
		LeaveHistory leaveHistory = leaveDao.fetchLeaveHistory(leaveId);
		try {
			if(leaveHistory==null)
				throw new LeaveException();
			else {
			leaveHistory.setStatus(LeaveStatus.Approved);
			int x = leaveDao.updateLeaves(leaveHistory);
			System.out.println("Leave with ID: " + x + " has been updated");
			return x;
			}
		} catch (LeaveException e) {
			System.out.println(e);
		}
		return leaveId;
	}

	@Override
	public List<LeaveHistory> showAllLeaves() {
		HashMap<Integer, LeaveHistory> leaveMap = leaveDao.showAllLeaves();
		if (leaveMap.size() == 0)
			System.out.println("No one has applied for any Leave");
		else
			return leaveDao.showAllLeaves().values().stream().collect(Collectors.toList());
		return null;
	}
}
