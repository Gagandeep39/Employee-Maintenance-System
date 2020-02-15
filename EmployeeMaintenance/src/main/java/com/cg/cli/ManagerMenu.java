
package com.cg.cli;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.beans.LeaveHistory;
import com.cg.enums.LeaveStatus;

/**
 * @author Gagandeep
 * @time 6:41:22 am
 * @date 15-Feb-2020
 */
public class ManagerMenu extends EmployeeMenu {

	/**
	 * @param empId
	 * 
	 */
	public static void showManagerMenu(int empId) {
		Scanner console = new Scanner(System.in);
		while (true) {
			System.out.println("*********Manager Menu*********");
			System.out.println("1. Search for Employee");
			System.out.println("2. Apply For Leave");
			System.out.println("3. Show All My Leaves");
			System.out.println("4. Show All Sub-Employees Leave");
			System.out.println("5. Approve Leave");
			System.out.println("6. Log Out");
			System.out.println("7. Exit");
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
				System.out.println("Logging Out");
				loginSystem();
				break;
			case 7:
				System.out.println("Powering Off...");
				System.exit(0);
				break;
			default:
				break;
			}
		}

	}

	/**
	 * @param empId
	 */
	private static HashMap<Integer, LeaveHistory> showAllSubEmployeesLeaves(int managerId) {
		int leaveId = 0;
		HashMap<Integer, LeaveHistory> leaveHisoryList = managerService.showAllLeavesOfSubEmployees(managerId);
//		System.out.println("Main: " + leaveHisoryList.size());
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
	 * @param managerId
	 * 
	 */
	private static void approveLeave(int managerId) {
		int leaveId = 0;
		HashMap<Integer, LeaveHistory> leaveHisoryList = showAllSubEmployeesLeaves(managerId);

		while (true) {
			LeaveStatus status ;
			Scanner console = new Scanner(System.in);
			try {
				System.out.println("Enter Leave ID: ");
				leaveId = console.nextInt();
				if (leaveHisoryList.get(leaveId) != null) {
					status = inputLeaveStatus();
					managerService.approveLeave(leaveId, status);
					break;
				} else
					System.out.println("Enter a valid Leave ID");

			} catch (InputMismatchException e) {
				System.out.println("Enter an Integer Value");
			}

		}

	}

	/**
	 * @return
	 */
	private static LeaveStatus inputLeaveStatus() {
		System.out.println("Select Leave Status");
		System.out.println("1. Approve");
		System.out.println("2. Reject");
		while(true) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter Status: ");
			int op = scanner.nextInt();
			switch (op) {
			case 1: return LeaveStatus.Approved;
			case 2: return LeaveStatus.Rejected;

			default:System.out.println("Enter a valid status");
				break;
			}
		}
	}

}
