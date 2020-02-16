import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.beans.Employee;
import com.cg.beans.LeaveHistory;
import com.cg.dao.DaoImpl;
import com.cg.enums.Designation;
import com.cg.enums.Gender;
import com.cg.enums.GradeType;
import com.cg.enums.LeaveStatus;
import com.cg.enums.MaritalStatus;
import com.cg.exception.UserNotFoundException;
import com.cg.service.AdminService;
import com.cg.service.AdminServiceImpl;
import com.cg.service.EmployeeService;
import com.cg.service.EmployeeServiceImpl;
import com.cg.service.ManagerService;
import com.cg.service.ManagerServiceImpl;

/**
 * @author Gagandeep
 * @time 3:21:55 pm
 * @date 16-Feb-2020
 */
public class ManagerServiceTest {
//	int approveLeave(int leaveId, LeaveStatus status);
//	List<LeaveHistory> showAllLeaves();	//
//	HashMap<Integer, LeaveHistory> showAllLeavesOfSubEmployees(int managerId); //All em[ployes working under this manager will be displayed, has to be implemented

	ManagerService managerService;
	AdminService adminService;
	Employee employee;
	LeaveHistory leaveHistory1;

	@Before
	public void init() {
		adminService = new AdminServiceImpl();
		managerService = new ManagerServiceImpl();
		employee = new Employee(100009, "FirstName", "LastName", LocalDate.of(1980, 03, 15), 1001, GradeType.M1,
				Designation.Manager, 4000000, Gender.M, MaritalStatus.Single, "Somewhere on Earth", "9967730494",
				100002);
	
		
		leaveHistory1 = new LeaveHistory(100009, 12, 4, LocalDate.of(2020, 8, 12), LocalDate.of(2020, 8, 16), LeaveStatus.Applied);
		adminService.addEmployee(employee);
		
		managerService.applyForLeave(leaveHistory1);
	}
	
	@Test
	public void testLeaveApply() {
		int leaveId=  managerService.approveLeave(leaveHistory1.getLeaveId(), LeaveStatus.Applied);
		assertEquals(LeaveStatus.Applied, DaoImpl.getDaoImpl().showAllLeaves().get(leaveId).getStatus());;
	}
	
	@Test
	public void testLeaveReject() {
		int leaveId=  managerService.approveLeave(leaveHistory1.getLeaveId(), LeaveStatus.Rejected);
		assertEquals(LeaveStatus.Rejected, DaoImpl.getDaoImpl().showAllLeaves().get(leaveId).getStatus());;
	}
	
	@Test
	public void showAllMyLeaves() {
		List<LeaveHistory> xHistories = managerService.showAllLeaves();
		assertEquals(DaoImpl.getDaoImpl().showAllLeaves().size(), xHistories.size());
	}
	
	@Test
	public void testShowAllSubLeaves() {
		HashMap<Integer, LeaveHistory> map  = managerService.showAllLeavesOfSubEmployees(100002);
		HashMap<Integer, Employee> map2 = DaoImpl.getDaoImpl().showAllEmployees();
		List<LeaveHistory> xHistories =  map.values().stream().filter(e->{
			int id = e.getEmpId();
			if(map2.get(id).getManagerId()==100002)
				return true;
			return false;
		}).collect(Collectors.toList());
		assertEquals(xHistories.size(), map.size());;
	}
	
	@After
	public void cleanMem() {
		employee = null;
		managerService = null;
		adminService = null;
	}
	
	
}
