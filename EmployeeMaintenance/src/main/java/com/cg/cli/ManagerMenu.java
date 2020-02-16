
package com.cg.cli;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.beans.LeaveHistory;
import com.cg.enums.LeaveStatus;

/**
 * ManagerMenu class shows list of operation that can be carried out by the
 * Manager Manager perform all employee task along with few extra tasks A switch
 * case based menu is shown to perform different operationa
 * 
 * @see EmployeeMenu
 * 
 * @author Gagandeep
 * @time 6:41:22 am
 * @date 15-Feb-2020
 */
public class ManagerMenu extends EmployeeMenu {

	/**
	 * Switch case based menu to execute various operation by the Manager
	 * 
	 * @param empId Tequired to carry out certain operation such as show
	 *              subEmployees, show managers own leave
	 * 
	 */
	public static void showManagerMenu(int empId) {
		while (true) {
			Scanner console = new Scanner(System.in);
			System.out.println("*********Manager Menu*********");
			System.out.println("1. Search for Employee");
			System.out.println("2. Apply For Leave");
			System.out.println("3. Show All My Leaves");
			System.out.println("4. Show All Sub-Employees Leave");
			System.out.println("5. Approve Leave");
			System.out.println("6. Show All Leaves");
			System.out.println("7. Log Out");
			System.out.println("8. Exit");
			if (console.hasNextInt()) {
				int op = console.nextInt();
				switch (op) {
				case 1:
					searchEmployee(empId);
					break;
				case 2:
					applyForLeave(empId);
					break;
				case 3:
					showAllMyLeaves(empId);
					break;
				case 4:
					showAllSubEmployeesLeaves(empId);
					break;
				case 5:
					approveLeave(empId);
					break;
				case 6:
					showAllLeaves();
				case 7:
					System.out.println("Logging Out");
					loginSystem();
					break;
				case 8:
					System.out.println("Powering Off...");
					System.exit(0);
					break;
				default:
					break;
				}
			} else
				System.out.println("Enter Integer Value");
		}

	}

	/**
	 * Displays a list of all Leaves stored in the system
	 */
	private static void showAllLeaves() {
		managerService.showAllLeaves().forEach(System.out::println);

	}

	/**
	 * Allows showing a list of all leaves requested by the subordinate employees
	 * 
	 * @param managerId Used to show leaves taken by all Sub-ordinates
	 */
	private static HashMap<Integer, LeaveHistory> showAllSubEmployeesLeaves(int managerId) {
		HashMap<Integer, LeaveHistory> leaveHisoryList = managerService.showAllLeavesOfSubEmployees(managerId);
		if (leaveHisoryList.isEmpty())
			System.out.println("No leave requested");
		else {
			leaveHisoryList.values().forEach(l -> {
				System.out.println(l);
			});
		}

		return leaveHisoryList;

	}

	/**
	 * Allows Approving/rejecting of leaves Manager can only approve leave of his
	 * own sub-ordinates
	 * 
	 * @param managerId Used to approve leave
	 * 
	 */
	private static void approveLeave(int managerId) {
		int leaveId = 0;
		HashMap<Integer, LeaveHistory> leaveHisoryList = showAllSubEmployeesLeaves(managerId);

		if (leaveHisoryList.size() != 0)
			while (true) {
				LeaveStatus status;
				Scanner console = new Scanner(System.in);
				try {
					System.out.print("Enter Leave ID: ");
					leaveId = console.nextInt();
					if (leaveHisoryList.get(leaveId) != null) {
						status = inputLeaveStatus();
						managerService.approveLeave(leaveId, status);
						break;
					} else
						System.out.println("Enter a LeaveID of your Employee");

				} catch (InputMismatchException e) {
					System.out.println("Enter an Integer Value");
				}

			}

	}

	/**
	 * Manager can either Approve the leave or reject the leave Input of status
	 * update is taken as integer via switchcase based menu
	 * 
	 * @return Leave status
	 * @see LeaveStatus
	 */
	private static LeaveStatus inputLeaveStatus() {
		System.out.println("Select Leave Status");
		System.out.println("1. Approve");
		System.out.println("2. Reject");
		while (true) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter Status: ");
			int op = scanner.nextInt();
			switch (op) {
			case 1:
				return LeaveStatus.Approved;
			case 2:
				return LeaveStatus.Rejected;

			default:
				System.out.println("Enter a valid status");
				break;
			}
		}
	}

}
