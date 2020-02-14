
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
		String firstName = inputFirstName();
		String lastName = inputLastName();
		LocalDate dateOfBirth = inputDoB();
		int departmentId = inputDepartmentId();
		GradeType empGrade = inputGrade();
		Designation designation = inputDesignation();
		int salary = inputSalary(empGrade);
		Gender gender = inputGender();
		MaritalStatus status = inputMaritalStatus();
		String address = inputHomeAddress();
		String phoneNumber = inputPhoneNumber();
		int managerId = inputManagerId();

		// USer Info
		String userName = inputUserName();
		String password = inputPassword();

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
		employee.setEmpFirstName(inputFirstName());
		employee.setEmpLastName(inputLastName());
		employee.setEmpDateOfBirth(inputDoB());
		employee.setEmpDepartmentId(inputDepartmentId());
		employee.setEmpGrade(inputGrade());
		employee.setEmpDesignation(inputDesignation());
		employee.setEmpBasic(inputSalary(employee.getEmpGrade()));
//		employee.setEmpGender(inputGender());	//Doesnt exactly makes sense #LGBT LOLOL XD
		employee.setEmpMaritalStatus(inputMaritalStatus());
		employee.setEmpHomeAddress(inputHomeAddress());
		employee.setEmpContactNumber(inputPhoneNumber());
		employee.setManagerId(inputManagerId());

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
		employee.setManagerId(inputManagerId());
		adminService.updateEmployee(employee);

	}
	
	/**
	 * 
	 */
	private static void displayAllEmployee() {
		employeeService.showAllEmployees().forEach(System.out::println);
	}

	/**
	 * @return
	 */
	private static String inputPassword() {
		Scanner console = new Scanner(System.in);
		String password = "";
		System.out.print("Enter Password: ");
		password = console.next();
		return password;
	}

	/**
	 * @return
	 */
	private static String inputUserName() {
		Scanner console = new Scanner(System.in);
		String userName = "";
		System.out.print("Enter Username: ");
		userName = console.next();
		return userName;
	}

	/**
	 * @return
	 */
	private static int inputManagerId() {
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
	private static String inputPhoneNumber() {
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
	private static String inputHomeAddress() {

		Scanner console = new Scanner(System.in);
		String address = "";
		System.out.print("Enter Home Address: ");
		address = console.next();
		return address;
	}

	/**
	 * @return
	 */
	private static MaritalStatus inputMaritalStatus() {
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
	private static Gender inputGender() {
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
	private static int inputSalary(GradeType empGrade) {
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
	private static Designation inputDesignation() {
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
	private static GradeType inputGrade() {
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
	private static int inputDepartmentId() {
		int id;
		Scanner console = new Scanner(System.in);
		while (true) {
			System.out.print("Enter Department Id: ");
			id = console.nextInt();
			if (AdminService.validateDepartment(id))
				break;
			else
				System.out.println("No Such Department Exist");
		}
		return id;
	}

	/**
	 * @return
	 */
	private static LocalDate inputDoB() {
		LocalDate d = null;
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Enter Date of Birth: ");
			String dob = scanner.next();
//			if (AdminService.validateDate(dob)) {
				d = LocalDate.parse(dob);
				break;
//			} else
//				System.out.println("Enter date in valid format!");
		}
		return d;

	}

	/**
	 * @return
	 */
	private static String inputLastName() {
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
	private static String inputFirstName() {
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

}
