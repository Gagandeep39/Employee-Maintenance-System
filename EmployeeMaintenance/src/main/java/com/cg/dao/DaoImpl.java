
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

// TODO: Auto-generated Javadoc
/**
 * DaoImpl is a class that implements 3 different Interfaces and allows data access operation .
 *
 * @author Gagandeep
 * @see EmployeeDao, LeaveDao, UserDao
 * @time 11:25:55 am
 * @date 11-Feb-2020
 * We are making one SIngle Dao lass because there might be assiciatiojn etween Collection list. 
 * During Database we will make mltiple dao class
 */
public class DaoImpl implements UserDao, EmployeeDao, LeaveDao {
	
	/** userMap is a HashMap of All employees, admin that are a part of this system Integer -> userId, User-> User Object. */
	private static HashMap<Integer, User> userMap;
	
	/** employeeMap is a HashMap containing all Emmployees registreed in this system Integer -> empId, Employee -> employee object. */
	private static  HashMap<Integer, Employee> employeeMap;
	
	/** leaveMap is a HashMap containing all leaves requested by various employees Integer -> leaveId, LeaveHistory -> leaveObject. */
	private static HashMap<Integer, LeaveHistory> leaveMap;
	
	/** daoSingleton is an instance of DaoImpl that allows creatin of a singleton pattern. */
	/**
	 * DaoImpl
	 */
	static DaoImpl daoSingleton;
	
	/**
	 * Initializes DaoImpl object if null else returns previously created object.
	 *
	 * @return DeaImpl object
	 */
	public static DaoImpl getDaoImpl() {
		if(daoSingleton==null)
			daoSingleton = new DaoImpl();
		return daoSingleton;
	}
	
	/**
	 * Private constructo to ensure Object of a class is not Instantiated
	 * Populates userMap, employeeMap, leaveMap via DataRepository.
	 *
	 * @see DataRepository Class that populations the system with some dummy data
	 */
	private DaoImpl() {
		userMap = DataRepository.getUserList();
		employeeMap = DataRepository.getEmployeeList();
		leaveMap = DataRepository.getLeaveMap();
		
	}

	/**
	 * Inserts LeaveHistory object in hashmap.
	 *
	 * @param leave Inserts Leave related info in leave HashMap
	 * @return leaveId of the LeaveHistory object inserted
	 */
	public int createLeave(LeaveHistory leave) {
		leaveMap.put(leave.getLeaveId(), leave);
		return leave.getLeaveId();
	}

	/**
	 * Update leaves status in leaveMap
	 *
	 * @param leave the leave object will override previous leave
	 * @return leaveId After updating leave status, the leave Id is returned
	 */
	public int updateLeaves(LeaveHistory leave) {
		leaveMap.put(leave.getLeaveId(), leave);
		return leave.getLeaveId();
	}

	/**
	 * Fetch leave history from leaveMap based on leaveId
	 * A basic search operation
	 *
	 * @param leaveId the leave id
	 * @return the leaveHistory Object from the leaveMap
	 */
	public LeaveHistory fetchLeaveHistory(int leaveId) {
		return leaveMap.get(leaveId);
	}

	/**
	 * Show all leaves.
	 *
	 * @return HashMap containing all LeaveDetails in the system
	 */
	public HashMap<Integer, LeaveHistory> showAllLeaves() {
		return leaveMap;
	}

	/**
	 * Inserts new employee in the employeeMap
	 *
	 * @param e Employee object to be inserted in the employeeMap
	 * @return empId of the inserted employee
	 */
	public int addEmployee(Employee e) {
		employeeMap.put(e.getEmpId(), e);
		return e.getEmpId();
	}

	/**
	 * Removes the employee from employeeMap using empId
	 *
	 * @param empId the emp id through which the employee will be removed
	 * @return the employee which has been deleted
	 */
	public Employee removeEmployee(int empId) {
		Employee employee = employeeMap.remove(empId);
		return employee;
	}

	/**
	 * Update employee data in the Hashmap by overwriting
	 *
	 * @param e the employee object which wi=hich will override the previous object
	 * @return the employee after successful update
	 */
	public Employee updateEmployee(Employee e) {
		Employee employee = employeeMap.put(e.getEmpId(), e);
		return employee;
	}

	/**
	 * Search employee.
	 *
	 * @param empId the emp id using which the employee info will be fethed
	 * @return the employee object containing all employee data whose id is equal to the requested Id
	 */
	public Employee searchEmployee(int empId) {
		Employee employee = employeeMap.get(empId);
		return employee;
	}

	/**
	 * Search employee by name
	 * Case insensitive search by employee
	 *
	 * @param name the search parameter
	 * @return the list containg employee whose name contains the entered string
	 */
	public List<Employee> searchEmployee(String name) {
		return employeeMap.values().stream().filter(e->e.getEmpFirstName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
	}

	/**
	 * Implementation must retrn a list, since multipe employees can belong to same Dept, grade.
	 * List of Employees having departmentId equal to d's ID will be fetched
	 *
	 * @param d is the Department object 
	 * @return the list of employees
	 */
	public List<Employee> searchEmployee(Department d) {
		return employeeMap.values().stream().filter(e->e.getEmpDepartmentId()==d.getDepartmentId()).collect(Collectors.toList());
	}
	
	/**
	 * Implementation must retrn a list since multipe employees can belong to same Dept, grade.
	 *
	 * @param g the g
	 * @return the list
	 */
	public List<Employee> searchEmployee(GradeType g) {
		return employeeMap.values().stream().filter(e->e.getEmpGrade()==g).collect(Collectors.toList());
	}

	/**
	 * Implementation must return a list since multipe employees can belong to same Dept, grade.
	 *
	 * @param m the marital status 
	 * @return the list having marital status equal to specified marital status
	 */
	public List<Employee> searchEmployee(MaritalStatus m) {
		return employeeMap.values().stream().filter(e->e.getEmpMaritalStatus()==m).collect(Collectors.toList());
	}

	/**
	 * Show all employees in the system
	 *
	 * @return the hash map containng all emplyees in the system
	 */
	public HashMap<Integer, Employee> showAllEmployees() {
		return employeeMap;
	}

	/**
	 * Fecthes a list of all users in the system
	 *
	 * @return the user map containg all employees, admin in the syetm
	 */
	public HashMap<Integer, User> getUserMap() {
		return userMap;
	}

	/**
	 * Fetch user based on Id
	 *
	 * @param userId the user id used for login validation
	 * @return the user object containg username and password of the employee
	 */
	public User fetchUser(int userId) {
		User user = userMap.get(userId);
		return user;
	}

	/**
	 * Adds the user to the userMap
	 * Used during registration of an employee to the system
	 *
	 * @param user object to be inserted in the hashmap
	 * @return the userId of user that is added
	 */
	public int addUser(User user) {
		userMap.put(user.getUserId(), user);
		return user.getUserId();
	}

	/**
	 * Update user.
	 *
	 * @param user the user that overrides previous user
	 * @return the userId of the employee
	 */
	public int updateUser(User user) {
		userMap.put(user.getUserId(), user);
		return user.getUserId();
	}

	/**
	 * Delete user from the userMap based on userId
	 * Performed along with deleteEmployee
	 *
	 * @param userId the user id of user who is to be deleted
	 * @return the user object that has been removed from the map
	 */
	public User deleteUser(int userId) {
		User u = userMap.remove(userId);
		return u;
	}

	

}
