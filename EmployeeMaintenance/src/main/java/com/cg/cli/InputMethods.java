
package com.cg.cli;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.beans.LeaveHistory;
import com.cg.enums.Designation;
import com.cg.enums.Gender;
import com.cg.enums.GradeType;
import com.cg.enums.MaritalStatus;
import com.cg.service.AdminService;

/**
 * @author Gagandeep
 * @time 6:26:47 am
 * @date 15-Feb-2020
 */
public class InputMethods {

	/**
	 * @return
	 */
	public static String inputPassword() {
		Scanner console = new Scanner(System.in);
		String password = "";
		System.out.print("Enter Password: ");
		password = console.next();
		return password;
	}

	/**
	 * @return
	 */
	public static String inputUserName() {
		Scanner console = new Scanner(System.in);
		String userName = "";
		System.out.print("Enter Username: ");
		userName = console.next();
		return userName;
	}

	/**
	 * @return
	 */
	public static int inputManagerId() {
		Scanner console = new Scanner(System.in);
		int managerId = 0;
		while (true) {
			try {
				System.out.print("Enter Manager Id: ");
				managerId = console.nextInt();
				if (managerId == 0)
					return managerId;
				if (AdminService.validateManager(managerId))
					return managerId;
				else
					System.out.println("Enter a valid Manager ID or 0 for no Manager");
			} catch (InputMismatchException e) {
				return managerId = 0;
			}
		}
	}

	/**
	 * @return
	 */
	public static String inputPhoneNumber() {
		Scanner console = new Scanner(System.in);
		String phone = "";
		while (true) {
			System.out.print("Enter Phone Number: ");
			phone = console.next();
			if (AdminService.validateMobile(phone))
				return phone;
			else
				System.out.println("Enter a valid Phone Number");
		}
	}

	/**
	 * @return
	 */
	public static String inputHomeAddress() {

		Scanner console = new Scanner(System.in);
		String address = "";
		System.out.print("Enter Home Address: ");
		address = console.next();
		return address;
	}

	/**
	 * @return
	 */
	public static MaritalStatus inputMaritalStatus() {
		Scanner console = new Scanner(System.in);

		System.out.println("Marital Status Options are as follows");
		int i = 1;
		for (MaritalStatus g : MaritalStatus.values())
			System.out.println(i++ + ". " + g);
		while (true) {
			System.out.print("Select Marital Status: ");
			int op = console.nextInt();
			switch (op) {
			case 1:
				return MaritalStatus.Single;
			case 2:
				return MaritalStatus.Married;
			case 3:
				return MaritalStatus.Divorced;
			case 4:
				return MaritalStatus.Separated;
			case 5:
				return MaritalStatus.Widowed;

			default:
				System.out.println("Select a Valid Marital Status");
				break;
			}
		}
	}

	/**
	 * @return
	 */
	public static Gender inputGender() {
		Scanner console = new Scanner(System.in);
		while (true) {
			System.out.println("Gender 1. Male, 2. Femal");
			System.out.print("Enter Selection: ");
			int op = console.nextInt();
			switch (op) {
			case 1:
				return Gender.M;
			case 2:
				return Gender.F;
			default:
				System.out.println("Enter a valid value");
				break;
			}

		}
	}

	/**
	 * @param empGrade
	 * @return
	 */
	public static int inputSalary(GradeType empGrade) {
		Scanner console = new Scanner(System.in);
		int sal;
		while (true) {
			System.out.print("Enter Salary: ");
			sal = console.nextInt();
			if (AdminService.validateBasic(sal, empGrade))
				break;
		}
		return sal;
	}

	/**
	 * @return
	 */
	public static Designation inputDesignation() {
		Scanner console = new Scanner(System.in);

		System.out.println("Grade Options are as follows");
		int i = 1;
		for (Designation g : Designation.values())
			System.out.println(i++ + ". " + g);
		while (true) {
			System.out.print("Select Designation: ");
			int op = console.nextInt();
			switch (op) {
			case 1:
				return Designation.Trainee;
			case 2:
				return Designation.Manager;
			case 3:
				return Designation.Assistant;
			case 4:
				return Designation.Tester;
			case 5:
				return Designation.Developer;
			case 6:
				return Designation.DataScientist;

			default:
				System.out.println("Select a Valid Designation");
				break;
			}
		}
	}

	/**
	 * @return
	 */
	public static GradeType inputGrade() {
		Scanner console = new Scanner(System.in);

		System.out.println("Grade Options are as follows");
		int i = 1;
		for (GradeType g : GradeType.values())
			System.out.println(i++ + "." + g);
		while (true) {
			System.out.print("Select Grade: ");
			int op = console.nextInt();
			switch (op) {
			case 1:
				return GradeType.M1;
			case 2:
				return GradeType.M2;
			case 3:
				return GradeType.M3;
			case 4:
				return GradeType.M4;
			case 5:
				return GradeType.M5;
			case 6:
				return GradeType.M6;
			case 7:
				return GradeType.M7;

			default:
				System.out.println("Select a Valid grade");
				break;
			}
		}
	}

	/**
	 * @return
	 */
	public static int inputDepartmentId() {
		int id;
		Scanner console = new Scanner(System.in);
		while (true) {
			System.out.print("Enter Department Id: ");
			id = console.nextInt();
			if (AdminService.validateDepartment(id))
				break;
		}
		return id;
	}

	/**
	 * @return
	 */
	public static LocalDate inputDoB() {
		LocalDate d = null;
		Scanner scanner = new Scanner(System.in);
		while (true) {
				System.out.println("Enter Date of Birth: ");
				String dob = scanner.next();
				if(AdminService.validateDate(dob)) {
					d = LocalDate.parse(dob);
					if(AdminService.validateAge(d))
						break;
				}
		}
		return d;

	}

	/**
	 * @return
	 */
	public static String inputLastName() {
		String name = "";
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Enter Last name: ");
			name = scanner.next();
			if (AdminService.validateName(name))
				break;
			else
				System.out.println("Enter valid name!");
		}
		return name;
	}

	/**
	 * @return
	 */
	public static String inputFirstName() {
		String name = "";
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Enter First name: ");
			name = scanner.next();
			if (AdminService.validateName(name))
				break;
			else
				System.out.println("Enter valid name!");
		}
		return name;
	}

	/**
	 * @param daysRequired
	 * @param dateFrom
	 * @return
	 */
	public static LocalDate intputDateTo(int daysRequired, LocalDate dateFrom) {
		LocalDate date = dateFrom.plusDays(daysRequired);
		return date;
	}

	/**
	 * @return
	 */
	public static LocalDate inputDateFrom() {
		LocalDate d = null;
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Enter Start date (yyyy-mm-dd): ");
			String dob = scanner.next();
			if (AdminService.validateDate(dob)) {	//needs to be fixed
			d = LocalDate.parse(dob);
			if (!LocalDate.now().isAfter(d))
				break;
			else
				System.out.println("Input date must be after Today's date");
			}
		}
		return d;
		
	}

	/**
	 * @param history
	 * @param leaveRequired
	 * @return
	 */
	public static int inputLeaveBalance(int leaveRequired, LeaveHistory history) {
		if (history == null)
			return 12 - leaveRequired;
		else
			return history.getLeaveBalance() - leaveRequired;
	}

	/**
	 * @param empId
	 * @return
	 */
	public static int inputLeaveRequired(int leaveBalance) {
		int leaveRequired;
		while (true) {
			Scanner console = new Scanner(System.in);
			try {
				System.out.print("Enter number of days: ");
				leaveRequired = console.nextInt();
				if (leaveRequired <= leaveBalance)
					break;
				else
					System.out.println("Insufficient Leaves");
			} catch (InputMismatchException e) {
				System.out.println("Enter an Integer value!");
				console.reset();
			}

		}
		return leaveRequired;
	}

}
