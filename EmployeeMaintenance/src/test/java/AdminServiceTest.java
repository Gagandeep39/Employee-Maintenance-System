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
 * @author Gagandeep
 * @time 12:09:27 pm
 * @date 16-Feb-2020
 */
public class AdminServiceTest {

	Employee employee;
	AdminService adminService;

	@Before
	public void init() {
		adminService = new AdminServiceImpl();
		employee = new Employee(100009, "FirstName", "LastName", LocalDate.of(1980, 03, 15), 1004, GradeType.M1,
				Designation.Manager, 4000000, Gender.M, MaritalStatus.Married, "Somewhere on Earth", "9967730494",
				100002);
	}

	/**
	 * 
	 */
	@Test
	public void testAddEmployee() {
		int id = adminService.addEmployee(employee);
		assertEquals(id, employee.getEmpId());
	}

	@Test
	public void testUpdateEmployee() {
		String oldName = "FirstName";
		employee.setEmpFirstName("UpdatedName");
		adminService.updateEmployee(employee);
		Employee updatedEmployee = DaoImpl.getDaoImpl().searchEmployee(employee.getEmpId());
		assertEquals("UpdatedName", updatedEmployee.getEmpFirstName());
	}

	@Test
	public void testDeleteEmployee() {
		DaoImpl.getDaoImpl().addEmployee(employee);
		System.out.println(employee.getEmpId());
		assertTrue(adminService.deleteEmployee(employee.getEmpId()));;
	}

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

	@Test
	public void testModifyManager() {
		adminService.modifyManager(100009, 100003);
		Employee actual = DaoImpl.getDaoImpl().searchEmployee(100009);
		assertEquals(100003, actual.getManagerId());

	}
	
	@Test(expected = UserNotFoundException.class)
	public void userNotFoundExcepTest() throws UserNotFoundException{
		adminService.searchEmployee(99999);
	}
	
	@After
	public void clearMem() {
		employee = null;
		adminService = null;
	}

}
