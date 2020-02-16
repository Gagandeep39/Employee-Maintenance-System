
package com.cg.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.cg.beans.Employee;
import com.cg.dao.DaoImpl;
import com.cg.dao.EmployeeDao;
import com.cg.dao.UserDao;
import com.cg.exception.UserNotFoundException;

/**
 * The Class AdminServiceImpl. Implementation of AdminService interface It
 * contains the actual logic of various signatures defined in the interface
 *
 * @author Gagandeep
 * @time 12:31:51 pm
 * @date 11-Feb-2020
 */
public class AdminServiceImpl extends UserServiceImpl implements AdminService {

	/** The user dao object to perform operation on user data */
	private UserDao userDao;

	/** The employee dao to perform operation on emplopyee data */
	private EmployeeDao employeeDao;

	/**
	 * Instantiates a new admin service impl using DaoImpl Singleton
	 */
	public AdminServiceImpl() {
		userDao = DaoImpl.getDaoImpl();
		employeeDao = DaoImpl.getDaoImpl();
	}

	/**
	 * Adds the employee.
	 *
	 * @param e the employee object
	 * @return the empId of the added employee
	 */
	public int addEmployee(Employee e) {
		int result = employeeDao.addEmployee(e);
		System.out.println("Employee with ID: " + result + " added successfully");
		return result;
	}

	/**
	 * Update employee.
	 *
	 * @param e the e
	 * @return the int
	 */
	public int updateEmployee(Employee e) {
		e = employeeDao.updateEmployee(e);
		System.out.println("Successfully updated emplyee with id: " + e.getEmpId());
		return e.getEmpId();
	}

	/**
	 * Delete employee.
	 *
	 * @param empId the emp id
	 * @return true, if successful
	 */
	public boolean deleteEmployee(int empId) {
		Employee employee = employeeDao.removeEmployee(empId);
		userDao.deleteUser(empId);
		if (employee != null) {
			System.out.println("Deleted employee with Id: " + empId);
			return true;
		} else {
			System.out.println("Error deleting employee");
			return false;
		}
	}

	/**
	 * Search employee.
	 *
	 * @param empId the emp id
	 * @return the employee
	 * @throws UserNotFoundException the user not found exception
	 */
	public Employee searchEmployee(int empId) throws UserNotFoundException {
		Employee employee = employeeDao.searchEmployee(empId);
		if (employee == null)
			throw new UserNotFoundException();
		return employee;
	}

	/**
	 * Show all employees.
	 *
	 * @return the list of all employees
	 * @throws UserNotFoundException the user not found exception
	 */
	public List<Employee> showAllEmployees() throws UserNotFoundException {
		HashMap<Integer, Employee> map = employeeDao.showAllEmployees();
		if (map.size() == 0) {
			throw new UserNotFoundException();
		} else {
			return map.values().stream().collect(Collectors.toList());
		}
	}

	/**
	 * Modify manager.
	 *
	 * @param empId     the emp id whose manager is to be changed
	 * @param managerId the manager id to replace the previous one
	 * @return the empId of the updated employee
	 */
	@Override
	public int modifyManager(int empId, int managerId) {
		Employee employee = employeeDao.searchEmployee(empId);
		try {
			if (employee == null)
				throw new UserNotFoundException();
			else {
				employee.setManagerId(managerId);
				employeeDao.updateEmployee(employee);
				System.out.println("Employees manager ID changed to " + managerId);
				return empId;
			}
		} catch (UserNotFoundException e) {
			System.out.println(e);
		}
		return empId;
	}

}
