
package com.cg.cli;

import java.util.HashMap;
import java.util.Scanner;

import com.cg.beans.LeaveHistory;

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
			System.out.println("4. Approve Leave");
			System.out.println("5. Log Out");
			System.out.println("6. Exit");
			int op = console.nextInt();
			switch (op) {
			case 1:
				searchEmployee(empId);
				break;
			case 2:
				applyForLeave(empId);
				break;
			case 3:
				showAllLeaves(empId);
				break;
			case 4:
				approveLeave(empId);
				break;
			case 5:
				System.out.println("Logging Out");
				loginSystem();
				break;
			case 6:
				System.out.println("Powering Off...");
				System.exit(0);
				break;
			default:
				break;
			}
		}

	}

	/**
	 * @param managerId
	 * 
	 */
	private static void approveLeave(int managerId) {
		Scanner console = new Scanner(System.in);
		int leaveId = 0;
		HashMap<Integer, LeaveHistory> leaveHisoryList = managerService.showAllLeavesOfSubEmployees(managerId);
		System.out.println("Main: " + leaveHisoryList.size());
		if (leaveHisoryList.isEmpty())
			System.out.println("No leave requested");
		else {
			leaveHisoryList.values().forEach(l -> {
				System.out.println("HI" + l);
			});
		}
		while (true) {
			System.out.println("Enter Leave ID: ");
			leaveId = console.nextInt();
			if (leaveHisoryList.get(leaveId) != null) {
				break;
			} else
				System.out.println("Enter a valid Leave ID");

		}
		managerService.approveLeave(leaveId);
	}

}
