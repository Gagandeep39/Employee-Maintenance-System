
package com.cg.cli;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.beans.LeaveHistory;
import com.cg.enums.Designation;
import com.cg.enums.Gender;
import com.cg.enums.GradeType;
import com.cg.enums.MaritalStatus;
import com.cg.service.AdminService;

/**
 * InputMethods class contains different methods to take input from the user All
 * these static methods are used to take input from the user and validate it
 * These methods are called while performing operations via Emplee, Admin,
 * Manager
 * 
 * @see AdminMenu, EmployeeMenu, EmployeeMenu
 * 
 * @author Gagandeep
 * @time 6:26:47 am
 * @date 15-Feb-2020
 */
public class InputMethods {

	/**
	 * Takes password as user Input
	 * 
	 * @return Password entered by the User
	 */
	public static String inputPassword() {
		Scanner console = new Scanner(System.in);
		String password = "";
		System.out.print("Enter Password: ");
		password = console.next();
		return password;
	}

	/**
	 * Allows entering of username by the user
	 * 
	 * @return Username entered by user
	 */
	public static String inputUserName() {
		Scanner console = new Scanner(System.in);
		String userName = "";
		System.out.print("Enter Username: ");
		userName = console.next();
		return userName;
	}

	/**
	 * Enter Manager ID and validate if any such manager Exist or not and show
	 * appropriate message
	 * 
	 * @return managerId Entered by user
	 */
	public static int inputManagerId() {
		int managerId = 0;
		while (true) {
			Scanner console = new Scanner(System.in);
			System.out.print("Enter Manager Id: ");
			if (console.hasNextInt()) {
				managerId = console.nextInt();
				if (managerId == 0)
					return managerId;
				else if (AdminService.validateManager(managerId))
					return managerId;
				else
					System.out.println("Enter a valid Manager ID or 0 for no Manager");
			} else
				System.out.println("Enter an Integer Value");
		}
	}

	/**
	 * Allows Entering of Phone number by user and validation is performed
	 * 
	 * @return Phone number entered by user
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
	 * Entering of Home address via Console screen which is return to the caller
	 * method
	 * 
	 * @return Home address
	 */
	public static String inputHomeAddress() {

		Scanner console = new Scanner(System.in);
		String address = "";
		System.out.print("Enter Home Address: ");
		address = console.nextLine();
		return address;
	}

	/**
	 * Marital status is entered by the user in a form a integer value, where each
	 * value is associated with a different marital status Displays a list of all
	 * valid MaritalStatus Available
	 * 
	 * @see MaritalStatus
	 * @return Marital status
	 */
	public static MaritalStatus inputMaritalStatus() {
		System.out.println("Marital Status Options are as follows");
		int i = 1;
		for (MaritalStatus g : MaritalStatus.values())
			System.out.println(i++ + ". " + g);
		while (true) {
			Scanner console = new Scanner(System.in);
			System.out.print("Select Marital Status: ");
			if (console.hasNextInt()) {
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
			} else
				System.out.println("Enter an Integer");
		}
	}

	/**
	 * Enters Gender via switch case 1 -> Male, 2-> Female
	 * 
	 * @see Gender
	 * @return Gender of the EMployee
	 */
	public static Gender inputGender() {
		System.out.println("Gender 1. Male, 2. Female");
		while (true) {
			Scanner console = new Scanner(System.in);
			System.out.print("Enter Selection: ");
			if (console.hasNextInt()) {
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
			} else
				System.out.println("Enter an Integer Value");

		}
	}

	/**
	 * Allows Entering of Employee salary Different Employee grade have different
	 * salary band Entering salary of rong band will show an error along with the
	 * slary band
	 * 
	 * @see Grade
	 * @param empGrade Employee Grade is associated with a rangle of salary
	 * @return Basic Salary of The Employee
	 */
	public static int inputSalary(GradeType empGrade) {
		int sal;
		while (true) {
			Scanner console = new Scanner(System.in);
			System.out.print("Enter Salary: ");
			if (console.hasNextInt()) {
				sal = console.nextInt();
				if (AdminService.validateBasic(sal, empGrade))
					break;
			} else
				System.out.println("Enter an Integer value");
		}
		return sal;
	}

	/**
	 * Allows entering of designation in he form of Integer Each integer is
	 * associated with a designation
	 * 
	 * @see Designation
	 * @return Designation of Employee
	 */
	public static Designation inputDesignation() {

		System.out.println("Grade Options are as follows");
		int i = 1;
		for (Designation g : Designation.values())
			System.out.println(i++ + ". " + g);
		while (true) {
			Scanner console = new Scanner(System.in);
			System.out.print("Select Designation: ");
			if (console.hasNextInt()) {
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
			} else
				System.out.println("Enter Integer value");
		}
	}

	/**
	 * Allows entering of grade as an integer Each grade is associated with aan
	 * Integer Based of Employee Grade it will be assigned different salary
	 * 
	 * @see GradeType
	 * @return Employee Grade
	 */
	public static GradeType inputGrade() {

		System.out.println("Grade Options are as follows");
		int i = 1;
		for (GradeType g : GradeType.values())
			System.out.println(i++ + "." + g);
		while (true) {
			Scanner console = new Scanner(System.in);
			System.out.print("Select Grade: ");
			if (console.hasNextInt()) {
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
			} else
				System.out.println("Enter Integer Value");
		}
	}

	/**
	 * Allows Entering of department ID Validation of ID is perrformed followed by
	 * which a list of valid IDs is shown
	 * 
	 * @return Department ID
	 */
	public static int inputDepartmentId() {
		int id;
		while (true) {
			Scanner console = new Scanner(System.in);
			try {
				System.out.print("Enter Department Id: ");
				if (console.hasNextInt()) {
					id = console.nextInt();
					if (AdminService.validateDepartment(id))
						break;
				} else
					System.out.println("Enter an Integer");
			} catch (InputMismatchException e) {
				System.out.println("Department ID must be an Integer value");
				console.close();
			}

		}
		return id;
	}

	/**
	 * Allows entering of Date of Birth Default format is (yyyy-mm-dd) A validation
	 * is performed for the entered date, followed by age validation where age must
	 * be 18<=age<=58
	 * 
	 * @return Date of Birth
	 */
	public static LocalDate inputDoB() {
		LocalDate d = null;
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Enter Date of Birth: ");
			String dob = scanner.next();
			if (AdminService.validateDate(dob)) {
				d = LocalDate.parse(dob);
				if (AdminService.validateAge(d))
					break;
			}
		}
		return d;

	}

	/**
	 * Allows entering name of User Provides basic name validation
	 * 
	 * @return Last name of Employee
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
	 * Allows entering of first name of User Basic name validation is performed
	 * 
	 * @return First name of Employee
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
	 * Calculated the end date of Leave based on start date and number of days
	 * requested
	 * 
	 * @param daysRequired
	 * @param dateFrom
	 * @return End date of Leave
	 */
	public static LocalDate intputDateTo(int daysRequired, LocalDate dateFrom) {
		LocalDate date = dateFrom.plusDays(daysRequired);
		return date;
	}

	/**
	 * Allows entering of Start date to request for a leave
	 * 
	 * @return Start date of Leave requested
	 */
	public static LocalDate inputDateFrom() {
		LocalDate d = null;
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Enter Start date (yyyy-mm-dd): ");
			String dob = scanner.next();
			if (AdminService.validateDate(dob)) { // needs to be fixed
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
	 * Calculates the number of holidays the employee can take based on previous
	 * leaves and curently requested leave Max leave available are 12
	 * 
	 * @param history
	 * @param leaveRequired
	 * @return Number of leave Left
	 */
	public static int inputLeaveBalance(int leaveRequired, LeaveHistory history) {
		if (history == null)
			return 12 - leaveRequired;
		else
			return history.getLeaveBalance() - leaveRequired;
	}

	/**
	 * Enter the number of leave required by the user
	 * 
	 * @param leaveBalance Max number of leaves that the Employee can take
	 * @return Number of leves required by the Employee
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
