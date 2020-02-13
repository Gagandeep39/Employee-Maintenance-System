
package com.cg.enums;

/**
 * @author Gagandeep
 * @time 8:19:58 pm
 * @date 10-Feb-2020
 */
public class Grade {
	private GradeType gradeCode;
	private String description;
	private int minSalary;
	private int maxSalary;
	public Grade(GradeType gradeCode, String description, int minSalary, int maxSalary) {
		super();
		this.gradeCode = gradeCode;
		this.description = description;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
	}
	public GradeType getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(GradeType gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(int minSalary) {
		this.minSalary = minSalary;
	}
	public int getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(int maxSalary) {
		this.maxSalary = maxSalary;
	}
	
	

}
