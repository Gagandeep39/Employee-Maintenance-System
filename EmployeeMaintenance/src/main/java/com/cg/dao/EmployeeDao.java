
package com.cg.dao;

import java.util.HashMap;
import java.util.List;

import com.cg.beans.Employee;
import com.cg.beans.Department;
import com.cg.enums.GradeType;
import com.cg.enums.MaritalStatus;

/**
 * The Interface EmployeeDao.
 * It contains name of various operation that can be carried out on Employees
 *
 * @author Gagandeep
 * @time 8:25:51 pm
 * @date 10-Feb-2020
 * Search an employee details based on any of the fields - ID, First Name, Last Name, department, Grade, Marital Status. 
 * The following fields are provided to enable the user specify the search conditions:
 */
public interface EmployeeDao {
	
	/**
	 * Adds the employee.
	 *
	 * @param e the employee object to be inserted
	 * @return the employee id of the inserted emmployeee
	 */
	int addEmployee(Employee e);
	
	/**
	 * Removes the employee through its id
	 *
	 * @param empId the emp id
	 * @return the employee
	 */
	Employee removeEmployee(int empId);
	
	/**
	 * Update employee.
	 *
	 * @param e the eemployee object used for updateing
	 * @return the employee
	 */
	Employee updateEmployee(Employee e);
	
	/**
	 * Search employee.
	 *
	 * @param empId the emp id
	 * @return the employee having same ID
	 */
	Employee searchEmployee(int empId);
	
	/**
	 * Search employee based on string entered
	 *
	 * @param name the name
	 * @return list of employees matching the string query
	 */
	List<Employee> searchEmployee(String name);
	
	/**
	 * Search employee based on part of a name
	 *
	 * @param d the department ID whose id will be used to search employee
	 * @return the list of employee having department same as passed department ID
	 */
	List<Employee> searchEmployee(Department d);
	
	/**
	 * Search employee based on grade
	 *
	 * @param g the grade type object
	 * @return the list of employees matching the same grade
	 */
	List<Employee> searchEmployee(GradeType g);
	
	/**
	 * Search employee based on marital status
	 *
	 * @param m the m is the marital status to be search
	 * @return the list atching the marital status
	 */
	List<Employee> searchEmployee(MaritalStatus m);
	
	/**
	 * Show all employees.
	 *
	 * @return the hash map of all employees
	 */
	HashMap<Integer, Employee> showAllEmployees();
	
	

}
