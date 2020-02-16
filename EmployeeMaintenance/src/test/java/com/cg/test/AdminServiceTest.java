import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.beans.Employee;
import com.cg.dao.DaoImpl;
import com.cg.enums.Designation;
import com.cg.enums.Gender;
import com.cg.enums.GradeType;
import com.cg.enums.MaritalStatus;
import com.cg.exception.UserNotFoundException;
import com.cg.service.AdminService;
import com.cg.service.AdminServiceImpl;

/**
 * The Class AdminServiceTest.
 * Used to generate Admin Service class methods
 *
 * @author Gagandeep
 * @time 12:09:27 pm
 * @date 16-Feb-2020
 */
public class AdminServiceTest {

	/** The employee. */
	Employee employee;
	
	/** The admin service. */
	AdminService adminService;

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		adminService = new AdminServiceImpl();
		employee = new Employee(100009, "FirstName", "LastName", LocalDate.of(1980, 03, 15), 1004, GradeType.M1,
				Designation.Manager, 4000000, Gender.M, MaritalStatus.Married, "Somewhere on Earth", "9967730494",
				100002);
	}

	/**
	 * Test add employee.
	 */
	@Test
	public void testAddEmployee() {
		int id = adminService.addEmployee(employee);
		assertEquals(id, employee.getEmpId());
	}

	/**
	 * Test update employee.
	 */
	@Test
	public void testUpdateEmployee() {
		String oldName = "FirstName";
		employee.setEmpFirstName("UpdatedName");
		adminService.updateEmployee(employee);
		Employee updatedEmployee = DaoImpl.getDaoImpl().searchEmployee(employee.getEmpId());
		assertEquals("UpdatedName", updatedEmployee.getEmpFirstName());
	}

	/**
	 * Test delete employee.
	 */
	@Test
	public void testDeleteEmployee() {
		DaoImpl.getDaoImpl().addEmployee(employee);
		System.out.println(employee.getEmpId());
		assertTrue(adminService.deleteEmployee(employee.getEmpId()));;
	}

	/**
	 * Test search employee.
	 */
	@Test
	public void testSearchEmployee() {
		DaoImpl.getDaoImpl().addEmployee(employee);
		Employee searchedEmployee = null;
		try {
			searchedEmployee = adminService.searchEmployee(100009);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(100009, searchedEmployee.getEmpId());

	}

	/**
	 * Test modify manager.
	 */
	@Test
	public void testModifyManager() {
		adminService.modifyManager(100009, 100003);
		Employee actual = DaoImpl.getDaoImpl().searchEmployee(100009);
		assertEquals(100003, actual.getManagerId());

	}
	
	/**
	 * User not found excep test.
	 *
	 * @throws UserNotFoundException the user not found exception
	 */
	@Test(expected = UserNotFoundException.class)
	public void userNotFoundExcepTest() throws UserNotFoundException{
		adminService.searchEmployee(99999);
	}
	

	/**
	 * Test show all employees.
	 *
	 * @throws UserNotFoundException the user not found exception
	 */
	public void testShowAllEmployees() throws UserNotFoundException {
		assertEquals(DaoImpl.getDaoImpl().showAllEmployees().size(), adminService.showAllEmployees().size());
	}
	
	/**
	 * Clear mem.
	 */
	@After
	public void clearMem() {
		employee = null;
		adminService = null;
	}

}
