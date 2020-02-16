package com.cg.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.beans.Employee;
import com.cg.beans.LeaveHistory;
import com.cg.dao.DaoImpl;
import com.cg.beans.Department;
import com.cg.enums.Designation;
import com.cg.enums.Gender;
import com.cg.enums.GradeType;
import com.cg.enums.LeaveStatus;
import com.cg.enums.MaritalStatus;
import com.cg.exception.LeaveException;
import com.cg.exception.UserNotFoundException;
import com.cg.service.AdminService;
import com.cg.service.AdminServiceImpl;
import com.cg.service.EmployeeService;
import com.cg.service.EmployeeServiceImpl;

/**
 * The Class EmployeeServiceTest.
 * Used to test Employee Service class methods
 *
 * @author Gagandeep
 * @time 1:27:03 pm
 * @date 16-Feb-2020
 */
public class EmployeeServiceTest {

	/** The employee service. */
	EmployeeService employeeService;
	
	/** The admin service. */
	AdminService adminService;
	
	/** The employee. */
	Employee employee;
	
	/** The employee 2. */
	Employee employee2;
	
	/** The employee 3. */
	Employee employee3;

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		adminService = new AdminServiceImpl();
		employeeService = new EmployeeServiceImpl();
		employee = new Employee(100009, "FirstName", "LastName", LocalDate.of(1980, 03, 15), 1001, GradeType.M1,
				Designation.Manager, 4000000, Gender.M, MaritalStatus.Single, "Somewhere on Earth", "9967730494",
				100002);
		employee2 = new Employee(100010, "Stefani", "Germanotta", LocalDate.of(1985, 03, 18), 1001, GradeType.M1,
				Designation.Manager, 4000000, Gender.M, MaritalStatus.Married, "Somewhere on Earth", "9967630494",
				100002);
		employee3 = new Employee(100011, "Ste", "LastName", LocalDate.of(1980, 05, 01), 1003, GradeType.M1,
				Designation.Manager, 4000000, Gender.M, MaritalStatus.Married, "Somewhere on Earth", "9966660494",
				100002);
		adminService.addEmployee(employee);
		adminService.addEmployee(employee2);
		adminService.addEmployee(employee3);
	}

	/**
	 * Test search employee.
	 *
	 * @throws UserNotFoundException the user not found exception
	 */
	@Test
	public void testSearchEmployee() throws UserNotFoundException {
		Employee searchedEmployee = employeeService.searchEmployee(100010);
		assertEquals(100010, searchedEmployee.getEmpId());
	}

	/**
	 * Test search exception.
	 *
	 * @throws UserNotFoundException the user not found exception
	 */
	@Test(expected = UserNotFoundException.class)
	public void testSearchException() throws UserNotFoundException {
		employeeService.searchEmployee(999);
	}

	/**
	 * Test search name.
	 *
	 * @throws UserNotFoundException the user not found exception
	 */
	@Test
	public void testSearchName() throws UserNotFoundException {
		List<Employee> list = employeeService.searchEmployee("st");
		for (Employee e : list) {
			assertTrue(e.getEmpFirstName().toLowerCase().contains("st"));
		}
	}

	/**
	 * Test search marital status.
	 *
	 * @throws UserNotFoundException the user not found exception
	 */
	@Test
	public void testSearchMaritalStatus() throws UserNotFoundException {
		List<Employee> list = employeeService.searchEmployee(MaritalStatus.Single);
		for (Employee e : list) {
			assertTrue(e.getEmpMaritalStatus() == MaritalStatus.Single);
		}
	}

	/**
	 * Test search department.
	 *
	 * @throws UserNotFoundException the user not found exception
	 */
	@Test
	public void testSearchDepartment() throws UserNotFoundException {

		List<Employee> list = employeeService.searchEmployee(new Department(1002, ""));
		for (Employee e : list) {
			assertTrue(e.getEmpDepartmentId() == 1002);
		}
	}
	
	/**
	 * Test search grade.
	 *
	 * @throws UserNotFoundException the user not found exception
	 */
	@Test
	public void testSearchGrade() throws UserNotFoundException {

		List<Employee> list = employeeService.searchEmployee(GradeType.M1);
		for (Employee e : list) {
			assertTrue(e.getEmpGrade() == GradeType.M1);
		}
	}
	
	/**
	 * Test search grade exception.
	 *
	 * @throws UserNotFoundException the user not found exception
	 */
	@Test(expected = UserNotFoundException.class)
	public void testSearchGradeException() throws UserNotFoundException {

		List<Employee> list = employeeService.searchEmployee(GradeType.M6);
		System.out.println(list.size());
		for (Employee e : list) {
			assertTrue(e.getEmpGrade() == GradeType.M6);
		}
	}
	
	/**
	 * Test search department exception.
	 *
	 * @throws UserNotFoundException the user not found exception
	 */
	@Test (expected = UserNotFoundException.class)
	public void testSearchDepartmentException() throws UserNotFoundException {

		List<Employee> list = employeeService.searchEmployee(new Department(99999, null));
		for (Employee e : list) {
			assertTrue(e.getEmpDepartmentId() == 1002);
		}
	}
	
	/**
	 * Test search marriage exception.
	 *
	 * @throws UserNotFoundException the user not found exception
	 */
	@Test (expected = UserNotFoundException.class)
	public void testSearchMarriageException() throws UserNotFoundException {

		List<Employee> list = employeeService.searchEmployee(MaritalStatus.Separated);
		for (Employee e : list) {
			assertTrue(e.getEmpMaritalStatus() == MaritalStatus.Separated);
		}
	}
	
	/**
	 * Test add leave.
	 */
	@Test
	public void testAddLeave() {
		LeaveHistory l = new LeaveHistory(100003, 12, 4, LocalDate.of(2020, 4, 14), LocalDate.of(2020, 4, 14), LeaveStatus.Applied);
		int leave = employeeService.applyForLeave(l);
		assertEquals(leave, l.getLeaveId());
	}
	
	/**
	 * Test show leave history.
	 *
	 * @throws LeaveException the leave exception
	 */
	@Test
	public void testShowLeaveHistory() throws LeaveException {
		List<LeaveHistory> leaveHistories = employeeService.showLeaveHistory(100003);
		for (LeaveHistory leaveHistory : leaveHistories) {
			assertEquals(100003, leaveHistory.getEmpId());
		}
	}
	
	/**
	 * Test show all employees.
	 *
	 * @throws UserNotFoundException the user not found exception
	 */
	@Test
	public void testShowAllEmployees() throws UserNotFoundException {
		assertEquals(DaoImpl.getDaoImpl().showAllEmployees().size(), employeeService.showAllEmployees().size());
	}
	

	/**
	 * Clean mem.
	 */
	@After
	public void cleanMem() {
		employee = null;
		employee2 = null;
		employee3 = null;
		employeeService = null;
		adminService = null;
	}

}
