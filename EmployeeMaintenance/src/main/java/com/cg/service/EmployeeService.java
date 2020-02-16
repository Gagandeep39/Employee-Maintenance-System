
package com.cg.service;

import java.util.List;

import com.cg.beans.Employee;
import com.cg.beans.LeaveHistory;
import com.cg.enums.Department;
import com.cg.enums.GradeType;
import com.cg.enums.MaritalStatus;
import com.cg.exception.LeaveException;
import com.cg.exception.UserNotFoundException;

/**
 * @author Gagandeep
 * @time 2:20:07 pm
 * @date 11-Feb-2020
 */
public interface EmployeeService {
	
	Employee searchEmployee(int empId) throws UserNotFoundException;
	List<Employee> searchEmployee(String name) throws UserNotFoundException;
	List<Employee> searchEmployee(Department d) throws UserNotFoundException;
	List<Employee> searchEmployee(GradeType g) throws UserNotFoundException;
	List<Employee> searchEmployee(MaritalStatus m) throws UserNotFoundException;
	List<Employee> showAllEmployees() throws UserNotFoundException;
	int applyForLeave(LeaveHistory l);
	List<LeaveHistory> showLeaveHistory(int empId) throws LeaveException;
	
	

	
	

}
