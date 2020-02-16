
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
 * The Class EmployeeServiceImpl. It contains implementation of all methods
 * defined in the interface
 *
 * @author Gagandeep
 * @time 2:28:07 pm
 * @date 11-Feb-2020
 */
public class EmployeeServiceImpl extends UserServiceImpl implements EmployeeService {

	/** The employee dao object to carry operation on employee */
	protected EmployeeDao employeeDao;

	/** The leave dao to carry operation on leaves */
	protected LeaveDao leaveDao;

	/** The user dao to carry operation on User */
	protected UserDao userDao;

	/**
	 * Instantiates a dao objects in the constructor
	 */
	public EmployeeServiceImpl() {
		employeeDao = DaoImpl.getDaoImpl();
		leaveDao = DaoImpl.getDaoImpl();
		userDao = DaoImpl.getDaoImpl();
	}

	/**
	 * Search employee basd on id
	 *
	 * @param empId the emp id
	 * @return the employee object
	 * @throws UserNotFoundException the user not found exception
	 */
	@Override
	public Employee searchEmployee(int empId) throws UserNotFoundException {
		Employee employee = employeeDao.searchEmployee(empId);
		if (employee == null)
			throw new UserNotFoundException();
		else
			return employee;

	}

	/**
	 * Search employee basd on id
	 *
	 * @param empId the emp id
	 * @return the employee object
	 * @throws UserNotFoundException the user not found exception
	 */
	@Override
	public List<Employee> searchEmployee(String name) throws UserNotFoundException {
		List<Employee> list = employeeDao.searchEmployee(name);
		if (list.size() == 0)
			throw new UserNotFoundException("No employees with name: " + name + " found.");
		else
			return list;
	}

	/**
	 * Search employee based on depaertment
	 *
	 * @param d the derpartmnet object whose ID will be used to search for employees
	 * @return the list matching the query
	 * @throws UserNotFoundException the user not found exception
	 */
	@Override
	public List<Employee> searchEmployee(Department d) throws UserNotFoundException {
		List<Employee> list = employeeDao.searchEmployee(d);
		if (list.size() == 0)
			throw new UserNotFoundException("No employees with Department: " + d.getDepartmentId() + " found.");
		return list;
	}

	/**
	 * Search employee.
	 *
	 * @param g the gradetype
	 * @return the list matching the specific grade type
	 * @throws UserNotFoundException the user not found exception
	 */
	@Override
	public List<Employee> searchEmployee(GradeType g) throws UserNotFoundException {
		List<Employee> list = employeeDao.searchEmployee(g);
		if (list.size() == 0)
			throw new UserNotFoundException("No employees with GradeType: " + g + " found.");
		return list;
	}

	/**
	 * Search employee based on marriage status
	 *
	 * @param m the marital status of employees
	 * @return the list matching the mrital status
	 * @throws UserNotFoundException the user not found exception
	 */
	@Override
	public List<Employee> searchEmployee(MaritalStatus m) throws UserNotFoundException {
		List<Employee> list = employeeDao.searchEmployee(m);
		if (list.size() == 0)
			throw new UserNotFoundException("No employees with Marital Status: " + m + " found.");
		return list;
	}

	/**
	 * Show all employees.
	 *
	 * @return the list of all employees
	 * @throws UserNotFoundException the user not found exception
	 */
	@Override
	public List<Employee> showAllEmployees() throws UserNotFoundException {
		if (employeeDao.showAllEmployees().size() == 0) {
			throw new UserNotFoundException();
		} else
			return employeeDao.showAllEmployees().values().stream().collect(Collectors.toList());

	}

	/**
	 * Apply for leave.
	 *
	 * @param l the leave object containing leave data
	 * @see LeaveHistory
	 * @return the leaveId of the leave object created
	 */
	@Override
	public int applyForLeave(LeaveHistory l) {
		int x = leaveDao.createLeave(l);
		System.out.println("Success created leave with id " + x + " for employee " + l.getEmpId());
		return l.getLeaveId();
	}

	/**
	 *
	 * {@inheritDoc}
	 */
	@Override
	public List<LeaveHistory> showLeaveHistory(int empId) {
		HashMap<Integer, LeaveHistory> history = leaveDao.showAllLeaves();
		try {
			if (history.size() == 0)
				throw new LeaveException("Employee has taken no leaves");
			else {
				return history.values().stream().filter(h -> h.getEmpId() == empId).collect(Collectors.toList());
			}
		} catch (LeaveException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}