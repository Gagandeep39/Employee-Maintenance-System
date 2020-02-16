
package com.cg.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cg.beans.Employee;
import com.cg.enums.Department;
import com.cg.enums.Designation;
import com.cg.enums.Grade;
import com.cg.enums.GradeType;
import com.cg.exception.UserNotFoundException;
import com.cg.repository.DataRepository;

/**
 * The Interface AdminService. IT consists signature of various operation
 * carried out by admin It also stores various validations
 *
 * @author Gagandeep
 * @time 12:17:40 pm
 * @date 11-Feb-2020
 */

public interface AdminService {

	/**
	 * Adds the employee in the Map by calling dao method
	 *
	 * @param e the employee object storing data enterined by user
	 * @return empId of employee that was inserted
	 */
	int addEmployee(Employee e);

	/**
	 * Update employee based on employee id by xalling dao method
	 *
	 * @param e the employee object to replace the old object
	 * @return the employeeId
	 */
	int updateEmployee(Employee e);

	/**
	 * Delete employee based on emp iD by calling dao method
	 *
	 * @param empId the emp id
	 * @return true, if successful and false, if failure
	 */
	boolean deleteEmployee(int empId);

	/**
	 * Search employee based on ID
	 *
	 * @param empId the emp id
	 * @return the employee
	 * @throws UserNotFoundException the user not found exception if no user matches
	 *                               the search query
	 */
	Employee searchEmployee(int empId) throws UserNotFoundException;

	/**
	 * Modify manager is used to change the manager id of any employee
	 *
	 * @param empId     the emp id
	 * @param managerId the manager id
	 * @return the int
	 */
	int modifyManager(int empId, int managerId);

	/**
	 * Show all employees.
	 *
	 * @return the list of all employees in the system
	 * @throws UserNotFoundException the user not found exception
	 */
	List<Employee> showAllEmployees() throws UserNotFoundException;

	/** The name pattern. */
	String namePattern = "[A-Za-z]{4,}";

	/**
	 * The mobile pattern Must start from 7 or 8 or 9 follwed by 9 more digits only
	 */
	String mobilePattern = "[7-9][0-9]{9}";

	/** The email pattern. */
	String emailPattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";

	/**
	 * The date pattern. yyyy-mm-dd
	 */
	String datePattern = "^\\d{4}-\\d{1,2}-\\d{1,2}$"; // '\\d'-> represents digit

	/** The department pattern. */
	String departmentPattern = "[1]";

	/**
	 * Validate name.
	 *
	 * @param name the name to be validated by matching to namePattern
	 * @return true, if successful
	 */
	static boolean validateName(String name) {
		return name.matches(namePattern);
	}

	/**
	 * Validate mobile by matching with mobilePattern regular expression
	 *
	 * @param mobile the mobile number to be validated
	 * @return true, if successful
	 */
	static boolean validateMobile(String mobile) {
		return mobile.matches(mobilePattern);
	}

	/**
	 * Validate email by comparing it with emailPatter
	 *
	 * @param email the email to be validated
	 * @return true, if successful
	 */
	static boolean validateEmail(String email) {
		return email.matches(emailPattern);
	}

	/**
	 * Validate date by comparing it with dataPattern format Validates if date
	 * entered for a specific month is correct Ensure the month is between 1-12
	 *
	 * @param dob the date of birth to be validted
	 * @return true, if successful
	 */
	static boolean validateDate(String dob) {
		if (dob.matches(datePattern)) {
			String[] date = dob.split("-");
			Integer[] dayInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
			if (Integer.parseInt(date[1]) > 12) {
				System.out.println("Invalid month");
				return false;
			} else if (!(Integer.parseInt(date[2]) < dayInMonth[Integer.parseInt(date[1]) - 1])) {
				System.out.println(
						"Month " + date[1] + " has only " + dayInMonth[Integer.parseInt(date[1]) - 1] + " days");
				return false;
			}
			return true;
		} else {
			System.out.println("Pattern must be (yyyy-mm-dd)");
			return false;
		}
	}

	/**
	 * Validate department, ensure that the entered dapartent ID actually exists
	 *
	 * @param num the departmentId to be validated
	 * @return true, if successful
	 */
	static boolean validateDepartment(int num) {
		List<Department> departmentList = DataRepository.getDepartmentList().values().stream()
				.collect(Collectors.toList());
		for (Department department : departmentList) {
			if (department.getDepartmentId() == num)
				return true;
		}
		System.out.println("Invalid Department Number!!");
		System.out.println("*********Valid List of Departments Are*********");
		departmentList.forEach(System.out::println);
		return false;
	}

	/**
	 * Validate basic salary by compring it with the associated band based on grade
	 *
	 * @param sal      the salary enterred by user
	 * @param empGrade the emp grade to specifiy the band
	 * @return true, if successful
	 */
	static boolean validateBasic(double sal, GradeType empGrade) {
		List<Grade> gradeList = DataRepository.getGradeList().values().stream().collect(Collectors.toList());
		Optional<Grade> grade = gradeList.stream().filter(g -> g.getGradeCode() == empGrade).findAny();
		if (sal >= grade.get().getMinSalary() && sal <= grade.get().getMaxSalary())
			return true;
		else
			System.out.println(empGrade + " Employee's salary must be between " + grade.get().getMinSalary() + " and  "
					+ grade.get().getMaxSalary() + " for a Grade " + empGrade + " employee");
		return false;
	}

	/**
	 * Validate manager by checking if the manager Id ctually exists or not
	 *
	 * @param managerId the manager id to be validated
	 * @return true, if successful
	 */
	static boolean validateManager(int managerId) {
		List<Employee> managerList = DataRepository.getEmployeeList().values().stream()
				.filter(e -> e.getEmpDesignation() == Designation.Manager).collect(Collectors.toList());
		for (Employee employee : managerList) {
			if (employee.getEmpId() == managerId)
				return true;
		}
		System.out.println("*********List of Manager Name and ID*********");
		managerList.forEach(m -> {
			System.out.println(m.getEmpId() + " - " + m.getEmpFirstName() + " " + m.getEmpLastName());
		});
		return false;
	}

	/**
	 * Validate age Age must be less than 58 Age must be greater than 18
	 *
	 * @param d the date by which age is calculated
	 * @return true, if successful
	 */
	static boolean validateAge(LocalDate d) {
		LocalDate currentDate = LocalDate.now();
		Period period = Period.between(d, currentDate);
		if (period.getYears() >= 18 && period.getYears() <= 58)
			return true;
		else {
			System.out.println("Employee must be between 18-58 years");
		}
		return false;
	}

}
