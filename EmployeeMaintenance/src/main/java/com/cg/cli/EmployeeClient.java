
package com.cg.cli;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.beans.Employee;
import com.cg.beans.LeaveHistory;
import com.cg.beans.User;
import com.cg.enums.Department;
import com.cg.enums.Designation;
import com.cg.enums.Gender;
import com.cg.enums.GradeType;
import com.cg.enums.LeaveStatus;
import com.cg.enums.MaritalStatus;
import com.cg.enums.UserType;
import com.cg.service.AdminService;
import com.cg.service.AdminServiceImpl;
import com.cg.service.EmployeeService;
import com.cg.service.EmployeeServiceImpl;
import com.cg.service.ManagerService;
import com.cg.service.ManagerServiceImpl;
import com.cg.service.UserService;
import com.cg.service.UserServiceImpl;

/**
 * @author Gagandeep
 * @time 7:35:43 pm
 * @date 12-Feb-2020
 */
public class EmployeeClient {

	static AdminService adminService = new AdminServiceImpl();
	static EmployeeService employeeService = new EmployeeServiceImpl();
	static ManagerService managerService = new ManagerServiceImpl();
	static UserService userService = new UserServiceImpl();

	public static void main(String[] args) {
		loginSystem();
	}

	/**
	 * 
	 */
	public static void loginSystem() {
		Scanner console = new Scanner(System.in);
		int userId = 0;
		String password;
		User user = null;
		while (true) {

			try {
				System.out.println("*********Login System*********");
				System.out.print("Enter User ID: ");
				userId = console.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
			System.out.print("Enter Password: ");
			password = console.next();
			user = userService.login(userId, password);
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
	 * @param i
	 * 
	 */
	private static void checkManagerOrEmployee(int empId) {

		Employee employee = employeeService.searchEmployee(empId);
		if (employee.getEmpDesignation() == Designation.Manager)
			ManagerMenu.showManagerMenu(empId);
		else
			EmployeeMenu.showEmployeeMenu(empId);

	}

	






	

	

	




	
}
