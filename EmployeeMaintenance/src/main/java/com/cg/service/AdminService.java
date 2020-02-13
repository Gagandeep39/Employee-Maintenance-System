
package com.cg.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cg.beans.Employee;
import com.cg.beans.User;
import com.cg.enums.Department;
import com.cg.enums.Designation;
import com.cg.enums.Grade;
import com.cg.enums.GradeType;
import com.cg.exception.UserNotFoundException;
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


	//Validations
	String namePattern = "[A-Za-z]{4,}";
	String mobilePattern = "[7-9][0-9]{9}";
	String emailPattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
	String datePattern = "^\\d{1,2}-\\d{1,2}-\\d{4}$";	// '\\d'-> represents digit
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
		return dob.matches(datePattern);
	}
	static boolean validateDepartment(int num) {
		List<Department> departmentList = DataRepository.getDepartmentList().values().stream().collect(Collectors.toList());
		for (Department department : departmentList) {
			if(department.getDepartmentId()==num)
				return true;
		}
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
		Optional<Grade> grade = gradeList.stream().filter(g->g.getGradeCode()==empGrade).findAny();
		if(sal>=grade.get().getMinSalary() && sal<=grade.get().getMaxSalary())
			return true;
		else 
			System.out.println( empGrade + " Employee's salary must be between " + grade.get().getMinSalary() + " and  " + grade.get().getMaxSalary());
		return false;
	}

	/**
	 * @param managerId
	 * @return
	 */
	static boolean validateManager(int managerId) {
		List<Employee> managerList = DataRepository.getEmployeeList().values().stream().filter(e->e.getEmpDesignation()==Designation.Manager).collect(Collectors.toList());
		for (Employee employee : managerList) {
			if(employee.getEmpId()==managerId)
				return true;
		}
		System.out.println("*********List of Manager Name and ID*********");
		managerList.forEach(m->{
			System.out.println(m.getEmpId() +  " - " + m.getEmpFirstName() + " " + m.getEmpLastName());
		});
		return false;
	}

}
