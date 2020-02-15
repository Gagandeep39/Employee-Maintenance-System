
package com.cg.cli;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.beans.Employee;
import com.cg.beans.User;
import com.cg.enums.Designation;
import com.cg.enums.Gender;
import com.cg.enums.GradeType;
import com.cg.enums.MaritalStatus;
import com.cg.enums.UserType;
import com.cg.service.AdminService;

/**
 * @author Gagandeep
 * @time 10:08:40 pm
 * @date 14-Feb-2020
 */
public class AdminMenu extends EmployeeClient{
	
	/**
	 * 
	 */
	static void showAdminMenu() {
		Scanner console = new Scanner(System.in);
		while (true) {
			System.out.println("*********ADMIN Menu*********");
			System.out.println("1. Add Employee");
			System.out.println("2. Modify Employee Details");
			System.out.println("3. Modify Manager");
			System.out.println("4. Display All Employees");
			System.out.println("5. Log Out");
			System.out.println("6. Exit");
			System.out.print("Input: ");
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

		}

	}
	
	/**
	 * 
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

		// USer Info
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
	 * 
	 */
	private static void updateEmployee() {
		Scanner console = new Scanner(System.in);
		int empId;
		Employee employee;
		while (true) {
			System.out.print("Enter Employee ID to Update Data: ");
			empId = console.nextInt();
			employee = adminService.searchEmployee(empId);
			if (employee != null)
				break;
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
	 * 
	 */
	private static void modifyManager() {
		Scanner console = new Scanner(System.in);
		int empId;
		Employee employee;
		while (true) {
			System.out.print("Enter Employee ID whose Manager is to be Updated: ");
			empId = console.nextInt();
			employee = adminService.searchEmployee(empId);
			if (employee != null)
				break;
		}
		employee.setManagerId(InputMethods.inputManagerId());
		adminService.updateEmployee(employee);

	}
	
	/**
	 * 
	 */
	private static void displayAllEmployee() {
		adminService.showAllEmployees().forEach(System.out::println);
	}

}
