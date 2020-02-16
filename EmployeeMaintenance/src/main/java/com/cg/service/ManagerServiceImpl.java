
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
 * @author Gagandeep
 * @time 9:57:12 am
 * @date 13-Feb-2020
 */
public class ManagerServiceImpl extends EmployeeServiceImpl implements ManagerService {

	@Override
	public int approveLeave(int leaveId, LeaveStatus status) {
		LeaveHistory leaveHistory = leaveDao.fetchLeaveHistory(leaveId);
		try {
			if (leaveHistory == null)
				throw new LeaveException();
			else if (leaveHistory.getStatus() == LeaveStatus.Approved) {
				System.out.println("Leave was already approved");
			}else if(status==LeaveStatus.Rejected) {

				leaveHistory.setStatus(status);
				int x = leaveDao.updateLeaves(leaveHistory);
				System.out.println("Leave with ID: " + x + " has been " + status);
				return x;
			}
			else if(leaveHistory.getLeaveBalance()>=leaveHistory.getNoOfDaysApplied()) {
				int days = leaveHistory.getLeaveBalance()-leaveHistory.getNoOfDaysApplied();
				leaveHistory.setStatus(status);
				leaveHistory.setLeaveBalance(days);
				int x = leaveDao.updateLeaves(leaveHistory);
				updateLeaveBalances(leaveHistory.getEmpId(), days);
				System.out.println("Leave with ID: " + x + " has been " + status);
				return x;
			}else throw new LeaveException("Out of Leaves");
		} catch (LeaveException e) {
			System.out.println(e);
		}
		return leaveId;
	}

	/**
	 * @param empId
	 */
	private void updateLeaveBalances(int empId, int leaveBalance) {
		HashMap<Integer, LeaveHistory> leaveMap = leaveDao.showAllLeaves();
		if (leaveMap.size() != 0)
			leaveMap.values().stream().filter(l->l.getEmpId()==empId).forEach(e->{
				e.setLeaveBalance(leaveBalance);
				leaveDao.updateLeaves(e);
			});
		
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

	@Override
	public HashMap<Integer, LeaveHistory> showAllLeavesOfSubEmployees(int managerId) {

		HashMap<Integer, LeaveHistory> map = leaveDao.showAllLeaves();
		HashMap<Integer, Employee> employeeMap = employeeDao.showAllEmployees();
		HashMap<Integer, LeaveHistory> leaveList = new HashMap<Integer, LeaveHistory>();
		if(map.size()!=0 && employeeMap.size()!=0) {
			List<Employee> employees = employeeMap.values().stream().filter(e -> e.getManagerId() == managerId)
					.collect(Collectors.toList());
//			System.out.println("employeeList: " +employees.size());
			for (Employee employee : employees) {
				for (LeaveHistory leave : map.values()) {
					if (employee.getEmpId() == leave.getEmpId())
						leaveList.put(leave.getLeaveId(), leave);
				}
			}
		}else {
			System.out.println("No leaves Found");
		}
//		System.out.println("leavelist: " + leaveList.size());
//		System.out.println("Leaves Requested by Your Subordinates are");
//		for (LeaveHistory l : leaveList) {
//			System.out.println(l);
//		}

		return leaveList;
	}


}
