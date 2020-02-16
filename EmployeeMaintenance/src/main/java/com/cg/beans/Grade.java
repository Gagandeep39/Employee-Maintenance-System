
package com.cg.beans;

import com.cg.enums.GradeType;

/**
 * The Class Grade. Grade class is used to specify different salary bands with
 * different grade types DataRepository class stores salary related info
 * associated with each band
 * 
 * @see DataRepository
 *
 * @author Gagandeep
 * @time 8:19:58 pm
 * @date 10-Feb-2020
 */
public class Grade {

	/**
	 * The grade code It is the enum value
	 * 
	 * @see GradeType
	 */
	private GradeType gradeCode;

	/** The description of different Grades */
	private String description;

	/** The min salary that an employee of GradeX can have */
	private int minSalary;

	/** The max salary that an employee of gradeX can have */
	private int maxSalary;

	/**
	 * Instantiates a new grade.
	 *
	 * @param gradeCode   the grade code
	 * @param description the description
	 * @param minSalary   the min salary
	 * @param maxSalary   the max salary
	 */
	public Grade(GradeType gradeCode, String description, int minSalary, int maxSalary) {
		super();
		this.gradeCode = gradeCode;
		this.description = description;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
	}

	/**
	 * Gets the grade code.
	 *
	 * @return the grade code
	 */
	public GradeType getGradeCode() {
		return gradeCode;
	}

	/**
	 * Sets the grade code.
	 *
	 * @param gradeCode the new grade code
	 */
	public void setGradeCode(GradeType gradeCode) {
		this.gradeCode = gradeCode;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the min salary.
	 *
	 * @return the min salary
	 */
	public int getMinSalary() {
		return minSalary;
	}

	/**
	 * Sets the min salary.
	 *
	 * @param minSalary the new min salary
	 */
	public void setMinSalary(int minSalary) {
		this.minSalary = minSalary;
	}

	/**
	 * Gets the max salary.
	 *
	 * @return the max salary
	 */
	public int getMaxSalary() {
		return maxSalary;
	}

	/**
	 * Sets the max salary.
	 *
	 * @param maxSalary the new max salary
	 */
	public void setMaxSalary(int maxSalary) {
		this.maxSalary = maxSalary;
	}

}
