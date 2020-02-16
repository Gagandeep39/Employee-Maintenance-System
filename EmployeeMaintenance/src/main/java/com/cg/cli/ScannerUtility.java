
package com.cg.cli;

import java.util.Scanner;

/**
 * @author Gagandeep
 * @time 8:35:10 am
 * @date 16-Feb-2020
 */
public class ScannerUtility {
	static Scanner scanner;
	
	private ScannerUtility() {
	}
	
	public static Scanner getScanner() {
		if(scanner==null)
			scanner = new Scanner(System.in);
		return scanner;
	}

}
