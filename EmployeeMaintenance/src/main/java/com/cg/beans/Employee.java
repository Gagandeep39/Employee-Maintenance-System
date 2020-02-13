
package com.cg.beans;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import com.cg.enums.Designation;
import com.cg.enums.Gender;
import com.cg.enums.GradeType;
import com.cg.enums.MaritalStatus;


/**
 * @author Gagandeep
 * @time 8:08:25 pm
 * @date 10-Feb-2020
 * iii.	Employee: Emp_ID VARCHAR2(6), Emp_First_Name VARCHAR2(25), Emp_Last_Name VARCHAR2(25), Emp_Date_of_Birth DATE,
 *  Emp_Date_of_Joining DATE, Emp_Dept_ID int, Emp_Grade VARCHAR2(2), Emp_Designation VARCHAR2(50), Emp_Basic int,
 *  Emp_Gender VARCHAR2(1), Emp_Marital_Status VARCHAR2(1), Emp_Home_Address VARCHAR2(100), Emp_Contact_Num VARCHAR2(15), 
 *  Mgr_Id varchar2(6), foreign key(Mgr_Id) references employee(emp_ID)
 */
public class Employee {
	
	public static final int EMPLOYEE_ID_INIT = 100001;
	static int employeeIdAutoGen;
	static {
		employeeIdAutoGen = EMPLOYEE_ID_INIT;
	}
	
	
	private int empId;
	private String empFirstName;
	private String empLastName;
	private Date empDateOfBirth;
	private Date dateOfJoining;
	private int empDepartmentId;
	private GradeType empGrade;
	private Designation empDesignation;
	private int empBasic;
	private Gender empGender;
	private MaritalStatus empMaritalStatus;
	private String empHomeAddress;
	private String empContactNumber;
	private int managerId;
	
	
	public Employee(int empId, String empFirstName, String empLastName, Date empDateOfBirth,
			int empDepartmentId, GradeType empGrade, Designation empDesignation, int empBasic, Gender empGender,
			MaritalStatus empMaritalStatus, String empHomeAddress, String empContactNumber, int managerId) {
		super();
		this.empId = empId;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empDateOfBirth = empDateOfBirth;
		this.dateOfJoining = Calendar.getInstance().getTime();
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
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpFirstName() {
		return empFirstName;
	}
	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}
	public String getEmpLastName() {
		return empLastName;
	}
	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}
	public Date getEmpDateOfBirth() {
		return empDateOfBirth;
	}
	public void setEmpDateOfBirth(Date empDateOfBirth) {
		this.empDateOfBirth = empDateOfBirth;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public int getEmpDepartmentId() {
		return empDepartmentId;
	}
	public void setEmpDepartmentId(int empDepartmentId) {
		this.empDepartmentId = empDepartmentId;
	}
	public GradeType getEmpGrade() {
		return empGrade;
	}
	public void setEmpGrade(GradeType empGrade) {
		this.empGrade = empGrade;
	}
	public Designation getEmpDesignation() {
		return empDesignation;
	}
	public void setEmpDesignation(Designation empDesignation) {
		this.empDesignation = empDesignation;
	}
	public int getEmpBasic() {
		return empBasic;
	}
	public void setEmpBasic(int empBasic) {
		this.empBasic = empBasic;
	}
	public Gender getEmpGender() {
		return empGender;
	}
	public void setEmpGender(Gender empGender) {
		this.empGender = empGender;
	}
	public MaritalStatus getEmpMaritalStatus() {
		return empMaritalStatus;
	}
	public void setEmpMaritalStatus(MaritalStatus empMaritalStatus) {
		this.empMaritalStatus = empMaritalStatus;
	}
	public String getEmpHomeAddress() {
		return empHomeAddress;
	}
	public void setEmpHomeAddress(String empHomeAddress) {
		this.empHomeAddress = empHomeAddress;
	}
	public String getEmpContactNumber() {
		return empContactNumber;
	}
	public void setEmpContactNumber(String empContactNumber) {
		this.empContactNumber = empContactNumber;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empFirstName=" + empFirstName + ", empLastName=" + empLastName
				+ ", empDateOfBirth=" + empDateOfBirth + ", dateOfJoining=" + dateOfJoining + ", empDepartmentId="
				+ empDepartmentId + ", empGrade=" + empGrade + ", empDesignation=" + empDesignation + ", empBasic="
				+ empBasic + ", empGender=" + empGender + ", empMaritalStatus=" + empMaritalStatus + ", empHomeAddress="
				+ empHomeAddress + ", empContactNumber=" + empContactNumber + ", managerId=" + managerId + "]";
	}
	
	
	

}
