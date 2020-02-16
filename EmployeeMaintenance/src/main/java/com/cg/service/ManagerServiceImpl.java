
package com.cg.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.cg.beans.Employee;
import com.cg.beans.LeaveHistory;
import com.cg.dao.EmployeeDao;
import com.cg.dao.LeaveDao;
import com.cg.dao.UserDao;
import com.cg.enums.LeaveStatus;
import com.cg.exception.LeaveException;
import com.cg.exception.UserNotFoundException;

/**
 * The Class ManagerServiceImpl. Contains
 *
 * @author Gagandeep
 * @time 9:57:12 am
 * @date 13-Feb-2020
 */
public class ManagerServiceImpl extends EmployeeServiceImpl implements ManagerService {

	/**
	 * Approve leave requested by user.
	 *
	 * @param leaveId the leave id to be apprved
	 * @param status  the status of leave to whihc the current status is to be
	 *                changed
	 * @return the leaveId of the leave will be returned afteer update
	 * @see LeaveStatus
	 */
	@Override
	public int approveLeave(int leaveId, LeaveStatus status) {
		LeaveHistory leaveHistory = leaveDao.fetchLeaveHistory(leaveId);
		try {
			if (leaveHistory == null)
				throw new LeaveException();
			else if (leaveHistory.getStatus() == LeaveStatus.Approved) {
				System.out.println("Leave was already approved");
			} else if (status == LeaveStatus.Rejected) {

				leaveHistory.setStatus(status);
				int x = leaveDao.updateLeaves(leaveHistory);
				System.out.println("Leave with ID: " + x + " has been " + status);
				return x;
			} else if (leaveHistory.getLeaveBalance() >= leaveHistory.getNoOfDaysApplied()) {
				int days = leaveHistory.getLeaveBalance() - leaveHistory.getNoOfDaysApplied();
				leaveHistory.setStatus(status);
				leaveHistory.setLeaveBalance(days);
				int x = leaveDao.updateLeaves(leaveHistory);
				updateLeaveBalances(leaveHistory.getEmpId(), days);
				System.out.println("Leave with ID: " + x + " has been " + status);
				return x;
			} else
				throw new LeaveException("Out of Leaves");
		} catch (LeaveException e) {
			System.out.println(e);
		}
		return leaveId;
	}

	/**
	 * Update leave balances.
	 *
	 * @param empId        the emp id
	 * @param leaveBalance the leave balance
	 */
	private void updateLeaveBalances(int empId, int leaveBalance) {
		HashMap<Integer, LeaveHistory> leaveMap = leaveDao.showAllLeaves();
		if (leaveMap.size() != 0)
			leaveMap.values().stream().filter(l -> l.getEmpId() == empId).forEach(e -> {
				e.setLeaveBalance(leaveBalance);
				leaveDao.updateLeaves(e);
			});

	}

	/**
	 * Show all leaves.
	 *
	 * @return the list
	 */
	@Override
	public List<LeaveHistory> showAllLeaves() {
		HashMap<Integer, LeaveHistory> leaveMap = leaveDao.showAllLeaves();
		if (leaveMap.size() == 0)
			System.out.println("No one has applied for any Leave");
		else
			return leaveDao.showAllLeaves().values().stream().collect(Collectors.toList());
		return null;
	}

	/**
	 * Show all leaves of sub employees of the Manager.
	 *
	 * @param managerId the manager id whose subordinates leaves are to be shown
	 * @return the hash map containg leave of all managers sub employees
	 */
	@Override
	public HashMap<Integer, LeaveHistory> showAllLeavesOfSubEmployees(int managerId) {

		HashMap<Integer, LeaveHistory> map = leaveDao.showAllLeaves();
		HashMap<Integer, Employee> employeeMap = employeeDao.showAllEmployees();
		HashMap<Integer, LeaveHistory> leaveList = new HashMap<Integer, LeaveHistory>();
		if (map.size() != 0 && employeeMap.size() != 0) {
			List<Employee> employees = employeeMap.values().stream().filter(e -> e.getManagerId() == managerId)
					.collect(Collectors.toList());
			for (Employee employee : employees) {
				for (LeaveHistory leave : map.values()) {
					if (employee.getEmpId() == leave.getEmpId())
						leaveList.put(leave.getLeaveId(), leave);
				}
			}
		} else {
			System.out.println("No leaves Found");
		}
		return leaveList;
	}

}
