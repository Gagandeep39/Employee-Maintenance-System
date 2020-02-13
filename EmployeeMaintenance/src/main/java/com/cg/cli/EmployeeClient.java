
package com.cg.cli;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	private static void loginSystem() {
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
			showAdminMenu();
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
		if(employee.getEmpDesignation()==Designation.Manager)
			showManagerMenu(empId);
		else 
			showEmployeeMenu(empId);
		
	}

	/**
	 * @param empId 
	 * 
	 */
	private static void showEmployeeMenu(int empId) {
		Scanner console = new Scanner(System.in);
		while(true) {
			System.out.println("*********Employee Menu*********");
			System.out.println("1. Search for Employee");
			System.out.println("2. Apply For Leave");
			System.out.println("3. Log Out");
			System.out.println("4. Exit");
			int op = console.nextInt();
			switch (op) {
			case 1: searchEmployee(empId); break;
			case 2: applyForLeave(empId); break;
			case 3:
			System.out.println("Logging Out");
			loginSystem();
			break;
			case 4: 
				System.out.println("Powering Off...");
				System.exit(0);
				break;
			default:
				break;
			}
		}
		
	}

	/**
	 * @param empId 
	 * 
	 */
	private static void applyForLeave(int empId) {
		
		
		
		
//		LeaveHistory leaveHistory  = new LeaveHistory(empId, leaveBalance, noOfDaysApplied, dateFrom, dateTo, status)
		
	}

	/**
	 * 
	 */
	private static void searchEmployee(int empId) {

		Scanner console = new Scanner(System.in);
		while(true) {
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
			case 1: searchById(); break;
			case 2: searchByName(); break;
			case 3: searchByDepartment(); break;
			case 4: searchByGrade(); break;
			case 5: searchByMarriage(); break;
			case 6: showEmployeeMenu(empId); break;
			case 7: System.exit(0); break;

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
		int id = console.nextInt();
		Employee employee =  employeeService.searchEmployee(id);
		System.out.println(employee);
		
	}

	/**
	 * 
	 */
	private static void searchByMarriage() {
		Scanner console = new Scanner(System.in);
		MaritalStatus status = inputMaritalStatus();
		List<Employee> list = employeeService.searchEmployee(status);
		displayList(list);
		
	}

	/**
	 * 
	 */
	private static void searchByGrade() {
		Scanner console = new Scanner(System.in);
		GradeType gradeType = inputGrade();
		List<Employee> list = employeeService.searchEmployee(gradeType);
		displayList(list);
		
	}

	/**
	 * 
	 */
	private static void searchByDepartment() {
		Scanner console = new Scanner(System.in);
		int departmentId = inputDepartmentId();
		List<Employee> list = employeeService.searchEmployee(new Department(departmentId, ""));
		displayList(list);
	}

	/**
	 * 
	 */
	private static void searchByName() {

		Scanner console = new Scanner(System.in);
		String firstName;
		while(true) {
			System.out.print("Enter First Name: ");
			firstName = console.next();
			if(AdminService.validateName(firstName))
				break;
			else 
				System.out.println("Enter a valid name");
		}
		List<Employee> list = employeeService.searchEmployee(firstName);
		displayList(list);
		
	}

	/**
	 * @param list
	 */
	private static void displayList(List<Employee> list) {
		if(list.size()!=0) {
			System.out.println("Employees matching the Search Query are");
			list.forEach(System.out::println);
		}
		
	}

	/**
	 * @param empId 
	 * 
	 */
	private static void showManagerMenu(int empId) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	private static void showAdminMenu() {
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
				loginSystem();
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
	private static void addEmployee() {
		Scanner console = new Scanner(System.in);

		// Employee Info
		System.out.println("*********Fill Employee Information*********");
		String firstName = inputFirstName();
		String lastName = inputLastName();
		Date dateOfBirth = inputDoB();
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
	private static Date inputDoB() {
		Date d = null;
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Enter Date of Birth: ");
			String dob = scanner.next();
			if (AdminService.validateDate(dob)) {
				try {
					d = new SimpleDateFormat("dd-mm-yyyy").parse(dob);
				} catch (ParseException e) {
					System.out.println(e);
				}
				break;
			} else
				System.out.println("Enter date in valid format!");
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

	/**
	 * 
	 */
	private static void displayAllEmployee() {
		employeeService.showAllEmployees().forEach(System.out::println);
	}

}
