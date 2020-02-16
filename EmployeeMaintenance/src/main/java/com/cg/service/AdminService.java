
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
import com.cg.repository.DataRepository;

/**
 * @author Gagandeep
 * @time 12:17:40 pm
 * @date 11-Feb-2020
 */

//Try using range later ON
public interface AdminService {

	int addEmployee(Employee e);

	int updateEmployee(Employee e);

	boolean deleteEmployee(int empId);

	Employee searchEmployee(int empId);

	int modifyManager(int empId, int managerId);

	List<Employee> showAllEmployees();

	// Validations
	String namePattern = "[A-Za-z]{4,}";
	String mobilePattern = "[7-9][0-9]{9}";
	String emailPattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
	String datePattern = "^\\d{4}-\\d{1,2}-\\d{1,2}$"; // '\\d'-> represents digit
	String departmentPattern = "[1]";

	static boolean validateName(String name) {
		return name.matches(namePattern);
	}

	static boolean validateMobile(String mobile) {
		return mobile.matches(mobilePattern);
	}

	static boolean validateEmail(String email) {
		return email.matches(emailPattern);
	}

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
	 * @param sal
	 * @param empGrade
	 * @return
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
	 * @param managerId
	 * @return
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
	 * @param d
	 * @return
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
