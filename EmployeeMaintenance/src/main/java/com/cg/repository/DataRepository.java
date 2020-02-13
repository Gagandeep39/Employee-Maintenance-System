
package com.cg.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

import com.cg.beans.Employee;
import com.cg.beans.User;
import com.cg.enums.Department;
import com.cg.enums.Designation;
import com.cg.enums.Gender;
import com.cg.enums.Grade;
import com.cg.enums.GradeType;
import com.cg.enums.MaritalStatus;
import com.cg.enums.UserType;

/**
 * @author Gagandeep
 * @time 11:36:26 pm
 * @date 10-Feb-2020
 */
public class DataRepository {
	
	private static HashMap<Integer, User> userMap;
	private static HashMap<Integer, Employee> employeeMap;
	private static HashMap<Integer, Department> departmentMap;
	private static HashMap<GradeType, Grade> gradeMap;
	static {
		prepareHashMap();
	}
	/**
	 * 
	 */
	private static void prepareHashMap() {
		userMap = new HashMap<Integer, User>();
		employeeMap = new HashMap<Integer, Employee>();
		departmentMap = new HashMap<Integer, Department>();
		gradeMap = new HashMap<GradeType, Grade>();
		
		User u1 = new User("gagandeep", "123456", UserType.Admin);
		User u2 = new User("pranav", "123456", UserType.Admin);
		User u3 = new User("palak", "123456", UserType.Admin);
		User u4 = new User("Tanmay", "123456", UserType.Employee);
		User u5 = new User("uma", "123456", UserType.Employee);
		
		LocalDate date = null;
		

			date = LocalDate.parse("1999-05-14");
		
		Employee e1 = new Employee(u1.getUserId(), "Gagandeep", "Singh", date, 1002, GradeType.M7, Designation.Manager, 100000, Gender.M, MaritalStatus.Single, "Planet earth", "12345677", 0);
		Employee e2 = new Employee(u2.getUserId(), "pranav", "Singh", date, 1002, GradeType.M7,Designation.Developer, 100000, Gender.M, MaritalStatus.Single, "Planet earth", "12345677", 1001);
	
		
		
		userMap.put(u1.getUserId(), u1);
		userMap.put(u2.getUserId(), u2);
		userMap.put(u3.getUserId(), u3);
		userMap.put(u4.getUserId(), u4);
		userMap.put(u5.getUserId(), u5);
		
		
		employeeMap.put(e1.getEmpId(), e1);
		employeeMap.put(e2.getEmpId(), e2);
		
		
		departmentMap.put(1001, new Department(1001, "IT"));
		departmentMap.put(1002, new Department(1002, "HR"));
		departmentMap.put(1003, new Department(1003, "Accounts"));
		departmentMap.put(1004, new Department(1004, "Marketing"));
		departmentMap.put(1005, new Department(1005, "Training"));
		departmentMap.put(1006, new Department(1006, "Troubleshooting"));
		
		gradeMap.put(GradeType.M1, new Grade(GradeType.M1, "Beginner", 250000, 400000));
		gradeMap.put(GradeType.M2, new Grade(GradeType.M2, "Trainee", 400001, 1000000));
		gradeMap.put(GradeType.M3, new Grade(GradeType.M3, "Employee", 1000001, 1500000));
		gradeMap.put(GradeType.M4, new Grade(GradeType.M4, "Senior Employee", 1500001, 2000000));
		gradeMap.put(GradeType.M5, new Grade(GradeType.M5, "Intermediate Employee", 2000001, 2500000));
		gradeMap.put(GradeType.M6, new Grade(GradeType.M6, "Professional Employee", 2500001, 3500000));
		gradeMap.put(GradeType.M7, new Grade(GradeType.M7, "Highly Professional Employee", 3500001, 4000000));
		
		
	}
	
	public static HashMap<GradeType, Grade> getGradeList() {
		return gradeMap;
	}
	
	/**
	 * 
	 */
	public static HashMap<Integer, User> getUserList() {
		return userMap;

	}
	
	/**
	 * 
	 */
	public static HashMap<Integer, Employee> getEmployeeList() {
		return employeeMap;

	}
	
	public static HashMap<Integer, Department> getDepartmentList() {
		return departmentMap;
	}

}
