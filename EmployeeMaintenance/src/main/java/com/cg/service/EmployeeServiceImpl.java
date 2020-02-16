
package com.cg.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

import com.cg.beans.Employee;
import com.cg.beans.LeaveHistory;
import com.cg.dao.DaoImpl;
import com.cg.dao.EmployeeDao;
import com.cg.dao.LeaveDao;
import com.cg.dao.UserDao;
import com.cg.enums.Department;
import com.cg.enums.GradeType;
import com.cg.enums.LeaveStatus;
import com.cg.enums.MaritalStatus;
import com.cg.exception.LeaveException;
import com.cg.exception.UserNotFoundException;

/**
 * @author Gagandeep
 * @time 2:28:07 pm
 * @date 11-Feb-2020
 */
public class EmployeeServiceImpl extends UserServiceImpl implements EmployeeService{

	protected EmployeeDao employeeDao;
	protected LeaveDao leaveDao;
	protected UserDao userDao;
	
	/**
	 * 
	 */
	public EmployeeServiceImpl() {
		employeeDao = DaoImpl.getDaoImpl();
		leaveDao = DaoImpl.getDaoImpl();
		userDao = DaoImpl.getDaoImpl();
	}

	@Override
	public Employee searchEmployee(int empId) throws UserNotFoundException {
		Employee employee = employeeDao.searchEmployee(empId);
//		try {
			if (employee == null)
				throw new UserNotFoundException();
			else
				return employee;
//		} catch (UserNotFoundException e) {
//			System.out.println(e);
//		}
//		return employee;
		
	}

	@Override
	public List<Employee> searchEmployee(String name) throws UserNotFoundException {
		List<Employee> list = employeeDao.searchEmployee(name);
//		try {
			if(list.size()==0)
				throw new UserNotFoundException("No employees with name: " + name + " found.");
			else return list;
//		} catch (UserNotFoundException e) {
//			System.out.println(e.getMessage()); 
//		}
//		return list;
	}

	@Override
	public List<Employee> searchEmployee(Department d) throws UserNotFoundException{
		List<Employee> list = employeeDao.searchEmployee(d);
//		try {
			if(list.size()==0)
				throw new UserNotFoundException("No employees with Department: " + d.getDepartmentId() + " found.");
			return list;
//		} catch (UserNotFoundException e) {
//			System.out.println(e.getMessage());
//		}
//		return list;
	}

	@Override
	public List<Employee> searchEmployee(GradeType g) throws UserNotFoundException {
		List<Employee> list = employeeDao.searchEmployee(g);
//		try {
			if(list.size()==0)
				throw new UserNotFoundException("No employees with GradeType: " + g + " found.");
			return list;
//		} catch (UserNotFoundException e) {
//			System.out.println(e.getMessage());
//		}
//		return list;
	}

	@Override
	public List<Employee> searchEmployee(MaritalStatus m) throws UserNotFoundException {
		List<Employee> list = employeeDao.searchEmployee(m);
//		try {
			if(list.size()==0)
				throw new UserNotFoundException("No employees with Marital Status: " + m + " found.");
			return list;
//		} catch (UserNotFoundException e) {
//			System.out.println(e.getMessage());
//		}
//		return list;
	}

	@Override
	public List<Employee> showAllEmployees() {
		if (employeeDao.showAllEmployees().size() == 0) {
			System.out.println("No Employees Found");
			return null;
		} else
			return employeeDao.showAllEmployees().values().stream().collect(Collectors.toList());

	}

	@Override
	public int applyForLeave(LeaveHistory l) {
		int x = leaveDao.createLeave(l);
		System.out.println("Success created leave with id " + x + " for employee " + l.getEmpId());
		return l.getEmpId();
	}

	@Override
	public List<LeaveHistory> showLeaveHistory(int empId) {
		HashMap<Integer, LeaveHistory> history =leaveDao.showAllLeaves();
		try {
			if(history.size()==0)
				throw new LeaveException("Employee has taken no leaves");
			else {
				return history.values().stream().filter(h->h.getEmpId()==empId).collect(Collectors.toList());
			}
		} catch (LeaveException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}