
package com.cg.beans;

/**
 * The Class Department. It stores different department in the Organization Each
 * employee is associated with a Department
 *
 * @author Gagandeep
 * @time 8:05:38 pm
 * @date 10-Feb-2020
 */
public class Department {

	/** The department id. */
	private int departmentId;

	/** The department name. */
	private String departmentName;

	/**
	 * Instantiates a new department.
	 *
	 * @param departmentId   the department id
	 * @param departmentName the department name
	 */
	public Department(int departmentId, String departmentName) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

	/**
	 * Gets the department id.
	 *
	 * @return the department id
	 */
	public int getDepartmentId() {
		return departmentId;
	}

	/**
	 * Sets the department id.
	 *
	 * @param departmentId the new department id
	 */
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * Gets the department name.
	 *
	 * @return the department name
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * Sets the department name.
	 *
	 * @param departmentName the new department name
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * To display the string to the user
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + "]";
	}

}
