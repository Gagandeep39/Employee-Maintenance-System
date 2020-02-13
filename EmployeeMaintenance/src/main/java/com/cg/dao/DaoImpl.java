
package com.cg.dao;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.cg.beans.Employee;
import com.cg.beans.LeaveHistory;
import com.cg.beans.User;
import com.cg.enums.Department;
import com.cg.enums.GradeType;
import com.cg.enums.MaritalStatus;
import com.cg.repository.DataRepository;

/**
 * @author Gagandeep
 * @time 11:25:55 am
 * @date 11-Feb-2020
 * We are making one SIngle Dao lass because there might be assiciatiojn etween Collection list. 
 * During Database we will make mltiple dao class
 */
public class DaoImpl implements UserDao, EmployeeDao, LeaveDao {
	private HashMap<Integer, User> userMap;
	private HashMap<Integer, Employee> employeeMap;
	private HashMap<Integer, LeaveHistory> leaveMap;
	
	public DaoImpl() {
		userMap = DataRepository.getUserList();
		employeeMap = DataRepository.getEmployeeList();
		leaveMap = DataRepository.getLeaveMap();
		
	}

	public int createLeave(LeaveHistory leave) {
		leaveMap.put(leave.getEmpId(), leave);
		return leave.getEmpId();
	}

	public int updateLeaves(LeaveHistory leave) {
		leaveMap.put(leave.getEmpId(), leave);
		return leave.getEmpId();
	}

	public LeaveHistory fetchLeaveHistory(int leaveId) {
		return leaveMap.get(leaveId);
	}

	public HashMap<Integer, LeaveHistory> showAllLeaves() {
		return leaveMap;
	}

	public int addEmployee(Employee e) {
		employeeMap.put(e.getEmpId(), e);
		return e.getEmpId();
	}

	public Employee removeEmployee(int empId) {
		Employee employee = employeeMap.remove(empId);
		return employee;
	}

	public Employee updateEmployee(Employee e) {
		Employee employee = employeeMap.put(e.getEmpId(), e);
		return employee;
	}

	public Employee searchEmployee(int empId) {
		Employee employee = employeeMap.get(empId);
		return employee;
	}

	public List<Employee> searchEmployee(String name) {
		return employeeMap.values().stream().filter(e->e.getEmpFirstName().contains(name)).collect(Collectors.toList());
	}

	/**
	 * 
	 * Implementation must retrn a list since multipe employees can belong to same Dept, grade
	 */
	public List<Employee> searchEmployee(Department d) {
		return employeeMap.values().stream().filter(e->e.getEmpDepartmentId()==d.getDepartmentId()).collect(Collectors.toList());
	}
	
	/**
	 * 
	 * Implementation must retrn a list since multipe employees can belong to same Dept, grade
	 */
	public List<Employee> searchEmployee(GradeType g) {
		return employeeMap.values().stream().filter(e->e.getEmpGrade()==g).collect(Collectors.toList());
	}

	/**
	 * 
	 * Implementation must retrn a list since multipe employees can belong to same Dept, grade
	 */
	public List<Employee> searchEmployee(MaritalStatus m) {
		return employeeMap.values().stream().filter(e->e.getEmpMaritalStatus()==m).collect(Collectors.toList());
	}

	public HashMap<Integer, Employee> showAllEmployees() {
		return employeeMap;
	}

	public HashMap<Integer, User> getUserMap() {
		return userMap;
	}

	public User fetchUser(int userId) {
		User user = userMap.get(userId);
		return user;
	}

	public int addUser(User user) {
		userMap.put(user.getUserId(), user);
		return user.getUserId();
	}

	public int updateUser(User user) {
		return user.getUserId();
	}

	public User deleteUser(int userId) {
		User u = userMap.remove(userId);
		return u;
	}

	

}
