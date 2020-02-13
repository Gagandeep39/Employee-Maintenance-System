
package com.cg.dao;

import java.util.HashMap;
import java.util.List;

import com.cg.beans.Employee;
import com.cg.enums.Department;
import com.cg.enums.GradeType;
import com.cg.enums.MaritalStatus;

/**
 * @author Gagandeep
 * @time 8:25:51 pm
 * @date 10-Feb-2020
 * Search an employee details based on any of the fields - ID, First Name, Last Name, department, Grade, Marital Status. 
 * The following fields are provided to enable the user specify the search conditions:
 */
public interface EmployeeDao {
	int addEmployee(Employee e);
	Employee removeEmployee(int empId);
	Employee updateEmployee(Employee e);
	Employee searchEmployee(int empId);
	List<Employee> searchEmployee(String name);
	List<Employee> searchEmployee(Department d);
	List<Employee> searchEmployee(GradeType g);
	List<Employee> searchEmployee(MaritalStatus m);
	HashMap<Integer, Employee> showAllEmployees();
	
	

}
