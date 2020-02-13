
package com.cg.service;

import java.util.List;

import com.cg.beans.Employee;
import com.cg.beans.LeaveHistory;
import com.cg.enums.Department;
import com.cg.enums.GradeType;
import com.cg.enums.MaritalStatus;

/**
 * @author Gagandeep
 * @time 2:20:07 pm
 * @date 11-Feb-2020
 */
public interface EmployeeService {
	
	Employee searchEmployee(int empId);
	List<Employee> searchEmployee(String name);
	List<Employee> searchEmployee(Department d);
	List<Employee> searchEmployee(GradeType g);
	List<Employee> searchEmployee(MaritalStatus m);
	List<Employee> showAllEmployees();
	int applyForLeave(LeaveHistory l);
	List<LeaveHistory> showLeaveHistory(int empId);
	
	

	
	

}
