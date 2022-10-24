package com.model;

/**
 * This is for the employee model class
 */
public class Employee {

	public String EmployeeID;
	public String FullName;
	public String Address;
	public String FacultyName;
	public String Department;
	public String Designation;
	public String getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getFacultyName() {
		return FacultyName;
	}
	public void setFacultyName(String facultyName) {
		FacultyName = facultyName;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getDesignation() {
		return Designation;
	}
	public void setDesignation(String designation) {
		Designation = designation;
	}
	@Override
	public String toString() {
		
		return "Employee ID = " + EmployeeID + "\n" + "FullName = " + FullName + "\n" + "Address = " + Address + "\n"
				+ "Faculty Name = " + FacultyName + "\n" + "Department = " + Department + "\n" + "Designation = "
				+ Designation;
	}
}
