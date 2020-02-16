
package com.cg.service;

import java.util.List;

import com.cg.beans.Employee;
import com.cg.beans.LeaveHistory;
import com.cg.beans.Department;
import com.cg.enums.GradeType;
import com.cg.enums.MaritalStatus;
import com.cg.exception.LeaveException;
import com.cg.exception.UserNotFoundException;

/**
 * The Interface EmployeeService. Interface containing signatures of various
 * operation tat can be performed by employee
 *
 * @author Gagandeep
 * @time 2:20:07 pm
 * @date 11-Feb-2020
 */
public interface EmployeeService {

	/**
	 * Search employee basd on id
	 *
	 * @param empId the emp id
	 * @return the employee object
	 * @throws UserNotFoundException the user not found exception
	 */
	Employee searchEmployee(int empId) throws UserNotFoundException;

	/**
	 * Search employee based on name
	 *
	 * @param name the name
	 * @return the list mathicng the name in the query
	 * @throws UserNotFoundException the user not found exception
	 */
	List<Employee> searchEmployee(String name) throws UserNotFoundException;

	/**
	 * Search employee based on depaertment
	 *
	 * @param d the derpartmnet object whose ID will be used to search for employees
	 * @return the list matching the query
	 * @throws UserNotFoundException the user not found exception
	 */
	List<Employee> searchEmployee(Department d) throws UserNotFoundException;

	/**
	 * Search employee.
	 *
	 * @param g the gradetype
	 * @return the list matching the specific grade type
	 * @throws UserNotFoundException the user not found exception
	 */
	List<Employee> searchEmployee(GradeType g) throws UserNotFoundException;

	/**
	 * Search employee based on marriage status
	 *
	 * @param m the marital status of employees
	 * @return the list matching the mrital status
	 * @throws UserNotFoundException the user not found exception
	 */
	List<Employee> searchEmployee(MaritalStatus m) throws UserNotFoundException;

	/**
	 * Show all employees.
	 *
	 * @return the list of all employees
	 * @throws UserNotFoundException the user not found exception
	 */
	List<Employee> showAllEmployees() throws UserNotFoundException;

	/**
	 * Apply for leave.
	 *
	 * @param l the leave object containing leave data
	 * @see LeaveHistory
	 * @return the leaveId of the leave object created
	 */
	int applyForLeave(LeaveHistory l);

	/**
	 * Show leave history.
	 *
	 * @param empId the emp id whose levaes list is to be displayed
	 * @return the list of all leaves taken by the employee having id as emp id
	 * @throws LeaveException the leave exception
	 */
	List<LeaveHistory> showLeaveHistory(int empId) throws LeaveException;

}
