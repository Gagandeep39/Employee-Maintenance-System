
package com.cg.cli;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.cg.beans.Employee;
import com.cg.beans.LeaveHistory;
import com.cg.enums.Department;
import com.cg.enums.GradeType;
import com.cg.enums.LeaveStatus;
import com.cg.enums.MaritalStatus;
import com.cg.exception.UserNotFoundException;
import com.cg.service.AdminService;

/**
 * @author Gagandeep
 * @time 10:31:24 pm
 * @date 14-Feb-2020
 */
public class EmployeeMenu extends EmployeeClient {
	/**
	 * @param empId
	 * 
	 */
	public static void showEmployeeMenu(int empId) {
		while (true) {
			Scanner console = new Scanner(System.in);
			System.out.println("*********Employee Menu*********");
			System.out.println("1. Search for Employee");
			System.out.println("2. Apply For Leave");
			System.out.println("3. Show All my Leaves");
			System.out.println("4. Log Out");
			System.out.println("5. Exit");
			if (console.hasNextInt()) {
				int op = console.nextInt();
				switch (op) {
				case 1:
					searchEmployee(empId);
					break;
				case 2:
					applyForLeave(empId);
					break;
				case 3:
					showAllMyLeaves(empId);
					break;
				case 4:
					System.out.println("Logging Out");
					loginSystem();
					break;
				case 5:
					System.out.println("Powering Off...");
					System.exit(0);
					break;
				default:
					break;
				}
			} else
				System.out.println("Enter an Integer Value");
		}

	}

	/**
	 * 
	 */
	protected static void searchEmployee(int empId) {

		Scanner console = new Scanner(System.in);
		while (true) {
			System.out.println("*********Employee Search*********");
			System.out.println("1. Search By Id");
			System.out.println("2. Search By Name");
			System.out.println("3. Search By Department");
			System.out.println("4. Search By Grade");
			System.out.println("5. Search By Marital Status");
			System.out.println("6. Previous Menu");
			System.out.println("7. Log Out");
			int op = console.nextInt();
			switch (op) {
			case 1:
				searchById();
				break;
			case 2:
				searchByName();
				break;
			case 3:
				searchByDepartment();
				break;
			case 4:
				searchByGrade();
				break;
			case 5:
				searchByMarriage();
				break;
			case 6:
				showEmployeeMenu(empId);
				break;
			case 7:
				System.exit(0);
				break;

			default:
				System.out.println("Enter a valid Selection");
				break;
			}
		}

	}

	/**
	 * 
	 */
	private static void searchById() {

		Scanner console = new Scanner(System.in);
		try {
			System.out.print("Enter ID: ");
			if (console.hasNextInt()) {
				int id = console.nextInt();
				Employee employee = employeeService.searchEmployee(id);
				System.out.println(employee);
			} else {
				System.out.println("Enter an Integer");
			}
		} catch (UserNotFoundException e) {
			System.out.println(e);
		}

	}

	/**
	 * 
	 */
	private static void searchByMarriage() {
		MaritalStatus status = InputMethods.inputMaritalStatus();
		try {
			List<Employee> list = employeeService.searchEmployee(status);
			displayList(list);
		} catch (UserNotFoundException e) {
			System.out.println(e);
		}

	}

	/**
	 * 
	 */
	private static void searchByGrade() {
		GradeType gradeType = InputMethods.inputGrade();
		List<Employee> list;
		try {
			list = employeeService.searchEmployee(gradeType);
			displayList(list);
		} catch (UserNotFoundException e) {
			System.out.println(e);
		}

	}

	/**
	 * 
	 */
	private static void searchByDepartment() {
		try {
			int departmentId = InputMethods.inputDepartmentId();
			List<Employee> list = employeeService.searchEmployee(new Department(departmentId, ""));
			displayList(list);
		} catch (UserNotFoundException e) {
			System.out.println(e);
		}
	}

	/**
	 * 
	 */
	private static void searchByName() {

		Scanner console = new Scanner(System.in);
		String firstName;
		while (true) {
			System.out.print("Enter First Name: ");
			firstName = console.next();
			if (AdminService.validateName(firstName))
				break;
			else
				System.out.println("Enter a valid name");
		}
		try {
			List<Employee> list = employeeService.searchEmployee(firstName);
			displayList(list);
		} catch (UserNotFoundException e) {
			System.out.println(e);
		}

	}

	/**
	 * @param empId
	 */
	public static void showAllMyLeaves(int empId) {
		List<LeaveHistory> list = employeeService.showLeaveHistory(empId);
		if (list != null) {
			list.forEach(System.out::println);
		}

	}

	/**
	 * @param empId
	 * 
	 */
	protected static void applyForLeave(int empId) {
		int leaveBalance = 0;
		List<LeaveHistory> history = employeeService.showLeaveHistory(empId);
		if (history == null || history.size() == 0) {
			leaveBalance = 12;
		} else
			leaveBalance = history.get(history.size() - 1).getLeaveBalance();
		int daysRequired = InputMethods.inputLeaveRequired(leaveBalance);
		LocalDate dateFrom = InputMethods.inputDateFrom();
		LocalDate dateTo = InputMethods.intputDateTo(daysRequired, dateFrom);

		LeaveHistory leaveHistory = new LeaveHistory(empId, leaveBalance, daysRequired, dateFrom, dateTo,
				LeaveStatus.Applied);
		int id = employeeService.applyForLeave(leaveHistory);
		System.out.println("Successfully created Leave with ID: " + id);

	}

	/**
	 * @param list
	 */
	private static void displayList(List<Employee> list) {
		if (list.size() != 0) {
			System.out.println("Employees matching the Search Query are");
			list.forEach(System.out::println);
		}

	}

}
