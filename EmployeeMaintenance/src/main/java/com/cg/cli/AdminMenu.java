
package com.cg.cli;

import java.time.LocalDate;
import java.util.Scanner;

import com.cg.beans.Employee;
import com.cg.beans.User;
import com.cg.enums.Designation;
import com.cg.enums.Gender;
import com.cg.enums.GradeType;
import com.cg.enums.MaritalStatus;
import com.cg.enums.UserType;
import com.cg.exception.UserNotFoundException;

/**
 * Admin Menu allows carrying of different operation by admin such as add,
 * delete, search, modify employee
 * 
 * @author Gagandeep
 * @time 10:08:40 pm
 * @date 14-Feb-2020
 */
public class AdminMenu extends EmployeeClient {

	/**
	 * A switch case menu shown to user who is an Admin
	 */
	static void showAdminMenu() {
		while (true) {
			Scanner console = new Scanner(System.in);
			System.out.println("*********ADMIN Menu*********");
			System.out.println("1. Add Employee");
			System.out.println("2. Modify Employee Details");
			System.out.println("3. Modify Manager");
			System.out.println("4. Display All Employees");
			System.out.println("5. Log Out");
			System.out.println("6. Exit");
			System.out.print("Input: ");
			if (console.hasNextInt()) {
				int op = console.nextInt();
				switch (op) {
				case 1:
					addEmployee();
					break;
				case 2:
					updateEmployee();
					break;
				case 3:
					modifyManager();
					break;
				case 4:
					displayAllEmployee();
					break;
				case 5:
					System.out.println("Logging Out");
					EmployeeClient.loginSystem();
					break;
				case 6:
					System.out.println("Powering Off...");
					System.exit(0);
					break;

				default:
					break;
				}
			} else {
				System.out.println("Enter an Integer Value");
			}

		}

	}

	/**
	 * Allows adding of New employee in the System
	 * Different methods are called to take input from console screen
	 * Basic validations are performed while taking user input
	 * @see InputMethods Class contaning various methods to take input from console screen
	 */
	private static void addEmployee() {
		Scanner console = new Scanner(System.in);
		// Employee Info
		System.out.println("*********Fill Employee Information*********");
		String firstName = InputMethods.inputFirstName();
		String lastName = InputMethods.inputLastName();
		LocalDate dateOfBirth = InputMethods.inputDoB();
		int departmentId = InputMethods.inputDepartmentId();
		GradeType empGrade = InputMethods.inputGrade();
		Designation designation = InputMethods.inputDesignation();
		int salary = InputMethods.inputSalary(empGrade);
		Gender gender = InputMethods.inputGender();
		MaritalStatus status = InputMethods.inputMaritalStatus();
		String address = InputMethods.inputHomeAddress();
		String phoneNumber = InputMethods.inputPhoneNumber();
		int managerId = InputMethods.inputManagerId();

		// User Info
		String userName = InputMethods.inputUserName();
		String password = InputMethods.inputPassword();

		User user = new User(userName, password, UserType.Employee);
		userService.createUser(user);
		Employee employee = new Employee(user.getUserId(), firstName, lastName, dateOfBirth, departmentId, empGrade,
				designation, salary, gender, status, address, phoneNumber, managerId);
		adminService.addEmployee(employee);
		System.out.println("Successfully Created Employee with ID: " + user.getUserId());
	}

	/**
	 * Update the data associated with the employee Enter the ID of employee whose
	 * data is to be updated Various methods are called to take other info such as
	 * name, age, address etc as iput from user
	 */
	private static void updateEmployee() {
		int empId;
		Employee employee;
		while (true) {
			Scanner console = new Scanner(System.in);
			try {
				System.out.print("Enter Employee ID to Update Data: ");
				if (console.hasNextInt()) {
					empId = console.nextInt();
					employee = adminService.searchEmployee(empId);
					if (employee != null)
						break;
				}
			} catch (UserNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		employee.setEmpFirstName(InputMethods.inputFirstName());
		employee.setEmpLastName(InputMethods.inputLastName());
		employee.setEmpDateOfBirth(InputMethods.inputDoB());
		employee.setEmpDepartmentId(InputMethods.inputDepartmentId());
		employee.setEmpGrade(InputMethods.inputGrade());
		employee.setEmpDesignation(InputMethods.inputDesignation());
		employee.setEmpBasic(InputMethods.inputSalary(employee.getEmpGrade()));
//		employee.setEmpGender(inputGender());	//Doesnt exactly makes sense #LGBT LOLOL XD
		employee.setEmpMaritalStatus(InputMethods.inputMaritalStatus());
		employee.setEmpHomeAddress(InputMethods.inputHomeAddress());
		employee.setEmpContactNumber(InputMethods.inputPhoneNumber());
		employee.setManagerId(InputMethods.inputManagerId());

		adminService.updateEmployee(employee);

	}

	/**
	 * Change the manager of the Employee to which he reports by changing manager ID
	 * Basic validation is performed to ensure that such a Manager exists
	 * 
	 * @exception UserNotFoundException Thrown when no manager with entered ID is
	 *                                  available
	 */
	private static void modifyManager() {
		int empId;
		Employee employee;
		while (true) {
			Scanner console = new Scanner(System.in);
			try {
				System.out.print("Enter Employee ID whose Manager is to be Updated: ");
				if (console.hasNextInt()) {
					empId = console.nextInt();
					employee = adminService.searchEmployee(empId);
					if (employee != null)
						break;
				}
			} catch (UserNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		employee.setManagerId(InputMethods.inputManagerId());
		adminService.updateEmployee(employee);

	}

	/**
	 * Display the List of All employees in the system
	 * @exception UserNotFoundException thrown when no data is Employee list is empty
	 */
	private static void displayAllEmployee() {
		try {
			adminService.showAllEmployees().forEach(System.out::println);
		} catch (UserNotFoundException e) {
			System.out.println(e);
		}
	}

}
