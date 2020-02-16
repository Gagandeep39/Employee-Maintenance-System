
package com.cg.beans;

import java.time.LocalDate;

import com.cg.enums.Designation;
import com.cg.enums.Gender;
import com.cg.enums.GradeType;
import com.cg.enums.MaritalStatus;


/**
 * Employee bean that stores all necessary info of the Employee during Registration
 * 
 * @author Gagandeep
 * @time 8:08:25 pm
 * @date 10-Feb-2020
 */

public class Employee {
	
	/**
	 * Employee's ID
	 */
	private int empId;
	/**
	 * First name of Employee
	 */
	private String empFirstName;
	/**
	 * Last name of Emloyee
	 */
	private String empLastName;
	/**
	 * Date Of Birth of Employee
	 */
	private LocalDate empDateOfBirth;
	/**
	 * Date on which employee joined the organisation
	 */
	private LocalDate dateOfJoining;
	/**
	 * ID of department to which employee belong
	 */
	private int empDepartmentId;
	/**
	 * Grade of the employee (M1, M2, M3, M4, M5, M6, M7)
	 */
	private GradeType empGrade;
	/**
	 * Employees Designation
	 */
	private Designation empDesignation;
	/**
	 * Employee's salary
	 */
	private int empBasic;
	/**
	 * Employees gender
	 */
	private Gender empGender;
	/**
	 * Marital status of the Employee
	 */
	private MaritalStatus empMaritalStatus;
	/**
	 * Home address of the Employee
	 */
	private String empHomeAddress;
	/**
	 * Contact Number of the employee
	 */
	private String empContactNumber;
	/**
	 * ID of the Manager to which employee is assigned to
	 */
	private int managerId;
	
	/**
	 * Parameterized COnstructor
	 * 
	 * @param empId
	 * @param empFirstName
	 * @param empLastName
	 * @param empDateOfBirth
	 * @param empDepartmentId
	 * @param empGrade
	 * @param empDesignation
	 * @param empBasic
	 * @param empGender
	 * @param empMaritalStatus
	 * @param empHomeAddress
	 * @param empContactNumber
	 * @param managerId
	 */
	public Employee(int empId, String empFirstName, String empLastName, LocalDate empDateOfBirth,
			int empDepartmentId, GradeType empGrade, Designation empDesignation, int empBasic, Gender empGender,
			MaritalStatus empMaritalStatus, String empHomeAddress, String empContactNumber, int managerId) {
		super();
		this.empId = empId;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empDateOfBirth = empDateOfBirth;
		this.dateOfJoining = LocalDate.now();
		this.empDepartmentId = empDepartmentId;
		this.empGrade = empGrade;
		this.empDesignation = empDesignation;
		this.empBasic = empBasic;
		this.empGender = empGender;
		this.empMaritalStatus = empMaritalStatus;
		this.empHomeAddress = empHomeAddress;
		this.empContactNumber = empContactNumber;
		this.managerId = managerId;
	}
	
	/**
	 * @return Employee ID
	 */
	public int getEmpId() {
		return empId;
	}
	/**
	 * 
	 * @param empId Sets Employee Id
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	/**
	 * 
	 * @return First name of Employee
	 */
	public String getEmpFirstName() {
		return empFirstName;
	}
	/**
	 * 
	 * @param empFirstName Sets the empFirstName
	 */
	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}
	/**
	 * 
	 * @return lastName of Employee
	 */
	public String getEmpLastName() {
		return empLastName;
	}
	/**
	 * 
	 * @param empLastName Sets the empLastName 
	 */
	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}
	/**
	 * 
	 * @return Date of Birth
	 */
	public LocalDate getEmpDateOfBirth() {
		return empDateOfBirth;
	}
	/**
	 * 
	 * @param empDateOfBirth Sets the Date Of Birth
	 */
	public void setEmpDateOfBirth(LocalDate empDateOfBirth) {
		this.empDateOfBirth = empDateOfBirth;
	}
	/**
	 * 
	 * @return Date of Joining
	 */
	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}
	/**
	 * 
	 * @param dateOfJoiningOfJoining Sets the dateOfJoining
	 */
	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	/**
	 * 
	 * @return Department ID
	 */
	public int getEmpDepartmentId() {
		return empDepartmentId;
	}
	/**
	 * 
	 * @param empDepartmentId Sts department Id
	 */
	public void setEmpDepartmentId(int empDepartmentId) {
		this.empDepartmentId = empDepartmentId;
	}
	/**
	 * 
	 * @return Employees Grade
	 */
	public GradeType getEmpGrade() {
		return empGrade;
	}
	/**
	 * 
	 * @param empGrade sets Employee Grade
	 */
	public void setEmpGrade(GradeType empGrade) {
		this.empGrade = empGrade;
	}
	/**
	 * 
	 * @return Employee's Designation
	 */
	public Designation getEmpDesignation() {
		return empDesignation;
	}
	/**
	 * 
	 * @param empDesignation Set Employee Designation
	 */
	public void setEmpDesignation(Designation empDesignation) {
		this.empDesignation = empDesignation;
	}
	/**
	 * 
	 * @return Basic Salary of Employee
	 */
	public int getEmpBasic() {
		return empBasic;
	}
	/**
	 * 
	 * @param empBasic Set Employee Salary
	 */
	public void setEmpBasic(int empBasic) {
		this.empBasic = empBasic;
	}
	/**
	 * 
	 * @return Gender of the Employee
	 */
	public Gender getEmpGender() {
		return empGender;
	}
	/**
	 * 
	 * @param empGender Set Gender of Employee
	 */
	public void setEmpGender(Gender empGender) {
		this.empGender = empGender;
	}
	/**
	 * 
	 * @return Marital Status of Employee
	 */
	public MaritalStatus getEmpMaritalStatus() {
		return empMaritalStatus;
	}
	/**
	 * 
	 * @param empMaritalStatus Set Marital Status of Employee
	 */
	public void setEmpMaritalStatus(MaritalStatus empMaritalStatus) {
		this.empMaritalStatus = empMaritalStatus;
	}
	/**
	 * 
	 * @return Home Address of Employee
	 */
	public String getEmpHomeAddress() {
		return empHomeAddress;
	}
	/**
	 * 	
	 * @param empHomeAddress Sets Home Address of the Employee
	 */
	public void setEmpHomeAddress(String empHomeAddress) {
		this.empHomeAddress = empHomeAddress;
	}
	/**
	 * 
	 * @return Employee's Contact number
	 */
	public String getEmpContactNumber() {
		return empContactNumber;
	}
	/**
	 * 
	 * @param empContactNumber Set Employees contact number
	 */
	public void setEmpContactNumber(String empContactNumber) {
		this.empContactNumber = empContactNumber;
	}
	/**
	 * 
	 * @return Manager ID of the Employee
	 */
	public int getManagerId() {
		return managerId;
	}
	/**
	 * 
	 * @param managerId Sets Manager ID of Employee
	 */
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	
	/**
	 * {@inheritDoc}
	 * @return A string consisting of all values associated with the Employee Object
	 */
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empFirstName=" + empFirstName + ", empLastName=" + empLastName
				+ ", empDateOfBirth=" + empDateOfBirth + ", dateOfJoining=" + dateOfJoining + ", empDepartmentId="
				+ empDepartmentId + ", empGrade=" + empGrade + ", empDesignation=" + empDesignation + ", empBasic="
				+ empBasic + ", empGender=" + empGender + ", empMaritalStatus=" + empMaritalStatus + ", empHomeAddress="
				+ empHomeAddress + ", empContactNumber=" + empContactNumber + ", managerId=" + managerId + "]";
	}
	
	
	

}
