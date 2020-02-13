
package com.cg.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.cg.beans.Employee;
import com.cg.beans.User;
import com.cg.dao.DaoImpl;
import com.cg.dao.EmployeeDao;
import com.cg.dao.UserDao;
import com.cg.enums.Department;
import com.cg.exception.UserNotFoundException;

/**
 * @author Gagandeep
 * @time 12:31:51 pm
 * @date 11-Feb-2020
 */
public class AdminServiceImpl extends UserServiceImpl implements AdminService{
	
	private UserDao userDao;
	private EmployeeDao employeeDao;
	
	public AdminServiceImpl() {
		userDao = new DaoImpl();
		employeeDao = new DaoImpl();
	}

	
	//Ensure that the employee is created with same id as userId
	public int addEmployee(Employee e) {
		int result = employeeDao.addEmployee(e);
		System.out.println("Employee with ID: " + result + " added successfully");
		return result;
	}

	public int updateEmployee(Employee e) {
		e =  employeeDao.updateEmployee(e);
		System.out.println("Successfully updated emplyee with id: " + e.getEmpId());
		return e.getEmpId();
	}

	public boolean deleteEmployee(int empId) {
		Employee employee =	employeeDao.removeEmployee(empId);
		userDao.deleteUser(empId);
		if(employee!=null) {
			System.out.println("Deleted employee with Id: " + empId);
			return true;
		}
		else {
			System.out.println("Error deleting employee");
			return false;
		}
	}

	public Employee searchEmployee(int empId) {
		Employee employee = employeeDao.searchEmployee(empId);
		try {
			if(employee==null)
				throw new UserNotFoundException();
			return employee;
		} catch (UserNotFoundException e) {
			System.out.println(e);
		}
		return employee;
	}

	public List<Employee> showAllEmployees() {
		HashMap<Integer, Employee> map = employeeDao.showAllEmployees();
		if(map.size()==0)
			return null;
		else {
			return map.values().stream().collect(Collectors.toList());
		}
	}


	@Override
	public int modifyManager(int empId, int managerId) {
		Employee employee = employeeDao.searchEmployee(empId);
		try {
			if(employee==null)
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
		return 0;
	}



}