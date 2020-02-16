
package com.cg.repository;

import java.time.LocalDate;
import java.util.HashMap;

import com.cg.beans.Employee;
import com.cg.beans.LeaveHistory;
import com.cg.beans.User;
import com.cg.beans.Department;
import com.cg.enums.Designation;
import com.cg.enums.Gender;
import com.cg.beans.Grade;
import com.cg.enums.GradeType;
import com.cg.enums.LeaveStatus;
import com.cg.enums.MaritalStatus;
import com.cg.enums.UserType;


/**
 * The Class DataRepository.
 * Data repository is used to create and store dummy data in the system
 * It prevents entering of data every single time the application is ran
 * It is used as a substitute to persistant data (database)
 *
 * @author Gagandeep
 * @time 11:36:26 pm
 * @date 10-Feb-2020
 */
public class DataRepository {

	/** The user map to store user data */
	private static HashMap<Integer, User> userMap;
	
	/** The employee map to store employee data */
	private static HashMap<Integer, Employee> employeeMap;
	
	/** The department map to store various department */
	private static HashMap<Integer, Department> departmentMap;
	
	/** The grade map to store various grades and various salary bands associated with it */
	private static HashMap<GradeType, Grade> gradeMap;
	
	/** The leave map containing leave data */
	private static HashMap<Integer, LeaveHistory> leaveMap;
	static {
		prepareHashMap();
	}


	/**
	 * Prepare hash map.
	 * Add data in various maps
	 */
	private static void prepareHashMap() {
		userMap = new HashMap<Integer, User>();
		employeeMap = new HashMap<Integer, Employee>();
		departmentMap = new HashMap<Integer, Department>();
		gradeMap = new HashMap<GradeType, Grade>();
		leaveMap = new HashMap<Integer, LeaveHistory>();

		User u1 = new User("admin", "123456", UserType.Admin);
		User u2 = new User("gagan", "123456", UserType.Employee);
		User u3 = new User("pranav", "123456", UserType.Employee);
		User u4 = new User("palak", "123456", UserType.Employee);
		
		Employee e2 = new Employee(u2.getUserId(), "Gagandeep", "Singh", LocalDate.parse("1999-05-14"), 1002, GradeType.M7, Designation.Manager,
				100000, Gender.M, MaritalStatus.Single, "Planet earth", "12345677", 0);
		Employee e3 = new Employee(u3.getUserId(), "Pranav", "Karmarkar", LocalDate.parse("1998-03-21"), 1002, GradeType.M7, Designation.Developer,
				100000, Gender.M, MaritalStatus.Single, "Planet earth", "12345677", 100002);
		Employee e4 = new Employee(u4.getUserId(), "Palak", "Surname", LocalDate.parse("1998-03-21"), 1002, GradeType.M7, Designation.Developer,
				100000, Gender.F, MaritalStatus.Single, "Planet earth", "12345677", 100002);

		userMap.put(u1.getUserId(), u1);
		userMap.put(u2.getUserId(), u2);
		userMap.put(u3.getUserId(), u3);
		userMap.put(u4.getUserId(), u4);

		employeeMap.put(e2.getEmpId(), e2);
		employeeMap.put(e3.getEmpId(), e3);
		employeeMap.put(e4.getEmpId(), e4);

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

		LeaveHistory l1 = new LeaveHistory(e2.getEmpId(), 12, 3, LocalDate.parse("2020-04-12"),
				LocalDate.parse("2020-04-15"), LeaveStatus.Applied);
		LeaveHistory l2 = new LeaveHistory(e2.getEmpId(), 9, 3, LocalDate.parse("2020-04-18"),
				LocalDate.parse("2020-04-21"), LeaveStatus.Applied);
		LeaveHistory l3 = new LeaveHistory(e3.getEmpId(), 12, 3, LocalDate.parse("2020-05-01"),
				LocalDate.parse("2020-04-04"), LeaveStatus.Applied);
		LeaveHistory l4 = new LeaveHistory(e3.getEmpId(), 9, 3, LocalDate.parse("2020-06-20"),
				LocalDate.parse("2020-06-23"), LeaveStatus.Applied);
		LeaveHistory l5 = new LeaveHistory(e3.getEmpId(), 6, 3, LocalDate.parse("2020-07-12"),
				LocalDate.parse("2020-07-15"), LeaveStatus.Applied);
		leaveMap.put(l1.getLeaveId(), l1);
		leaveMap.put(l2.getLeaveId(), l2);
		leaveMap.put(l3.getLeaveId(), l3);
		leaveMap.put(l4.getLeaveId(), l4);
		leaveMap.put(l5.getLeaveId(), l5);

	}

	/**
	 * Gets the grade list.
	 *
	 * @return the grade list
	 */
	public static HashMap<GradeType, Grade> getGradeList() {
		return gradeMap;
	}

	/**
	 * Gets the user list.
	 *
	 * @return the user list
	 */
	public static HashMap<Integer, User> getUserList() {
		return userMap;

	}

	/**
	 * Gets the employee list.
	 *
	 * @return the employee list
	 */
	public static HashMap<Integer, Employee> getEmployeeList() {
		return employeeMap;

	}

	/**
	 * Gets the department list.
	 *
	 * @return the department list
	 */
	public static HashMap<Integer, Department> getDepartmentList() {
		return departmentMap;
	}

	/**
	 * Gets the leave map.
	 *
	 * @return the leave map
	 */
	public static HashMap<Integer, LeaveHistory> getLeaveMap() {
		return leaveMap;

	}

}
