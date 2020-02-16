
package com.cg.cli;

import java.util.Scanner;

import com.cg.beans.Employee;
import com.cg.beans.User;
import com.cg.enums.Designation;
import com.cg.exception.UserNotFoundException;
import com.cg.service.AdminService;
import com.cg.service.AdminServiceImpl;
import com.cg.service.EmployeeService;
import com.cg.service.EmployeeServiceImpl;
import com.cg.service.ManagerService;
import com.cg.service.ManagerServiceImpl;
import com.cg.service.UserService;
import com.cg.service.UserServiceImpl;

/**
 * EmployeeClient.java is the starting point of the program It shows the Menu
 * for Login System and carry out different operations in console window
 * 
 * @author Gagandeep
 * @time 7:35:43 pm
 * @date 12-Feb-2020
 * 
 *       TODO - Add Approve or rejected for leaves - DONE 
 *       TODO - Add validation for 6 digit Employee ID - DONE (since Autogen)
 *       TODO - Try implementing Singleton for Scanner - DONE (not possible) 
 *       TODO - hide password while entering - DONE (not possible in eclipse) 
 *       TODO - try using username for login - DONE
 *       TODO - show welcome screen with username - DONE 
 *       TODO - Create test cases in JUNIT - DONE 
 *       TODO - Add Documentations - DONE
 *       TODO - Make a test Suite - DONE
 *       TODO -ask sir correct way to implement Log4J
 */

public class EmployeeClient {

	/**
	 * Referene to AdminService interface to carry operations of an Admin
	 */
	static AdminService adminService = new AdminServiceImpl();
	/**
	 * Reference to EmployeeService to carry operations of an Employee
	 */
	static EmployeeService employeeService = new EmployeeServiceImpl();
	/**
	 * Reference to ManagerService to carry operation of a Manager
	 */
	static ManagerService managerService = new ManagerServiceImpl();
	/**
	 * Reference to UserService to carry Login related operation
	 */
	static UserService userService = new UserServiceImpl();

	/**
	 * Starting point of the Employee Management System
	 * 
	 * @param args Passed in command line (not used)
	 */
	public static void main(String[] args) {
		loginSystem();
	}

	/**
	 * Allows Logging into the system by Entering credentials (username and
	 * password)
	 */
	public static void loginSystem() {
		Scanner console = new Scanner(System.in);
		int userId = 0;
		String password;
		User user = null;
		while (true) {

			System.out.println("*********Login System*********");
			System.out.print("Enter Username: ");
			String uname = console.next();
			System.out.print("Enter Password: ");
			password = console.next();
			user = userService.login(uname, password);
			if (user != null)
				break;

			
		}

		switch (user.getUserType()) {
		case Admin:
			AdminMenu.showAdminMenu();
			break;
		case Employee:
			checkManagerOrEmployee(user.getUserId());
			break;
		default:
			break;
		}

	}

	/**
	 * 
	 * @param empId Used to check whether Employee is a regular employee or Manager
	 *              and show menu based on Designation value
	 */
	private static void checkManagerOrEmployee(int empId) {

		Employee employee;
		try {
			employee = employeeService.searchEmployee(empId);
			if (employee.getEmpDesignation() == Designation.Manager)
				ManagerMenu.showManagerMenu(empId);
			else
				EmployeeMenu.showEmployeeMenu(empId);
		} catch (UserNotFoundException e) {
			System.out.println(e);
		}

	}

}
