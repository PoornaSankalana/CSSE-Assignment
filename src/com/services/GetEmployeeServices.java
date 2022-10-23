package com.services;

import org.xml.sax.SAXException;
import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.DriverManager;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.PreparedStatement;
import javax.xml.xpath.XPathExpressionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Statement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import com.util.*;
import com.model.*;

public class GetEmployeeServices extends UtilC {
	public static final Logger Log = Logger.getLogger(GetEmployeeServices.class.getName());

	private final ArrayList<Employee> employeeList = new ArrayList<Employee>();
	private static Connection connection;
	private static Statement statement;
	private PreparedStatement prepared_statement;

	public GetEmployeeServices() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
					properties.getProperty("password"));
		} catch (Exception e) {
			//Used loggers
			Log.log(Level.SEVERE, e.getMessage());
		}
	}

	public void EmployeesFormXML() {

		try {
			int size = UtilTransform.xmlXpaths().size();
			for (int i = 0; i < size; i++) {
				Map<String, String> map = UtilTransform.xmlXpaths().get(i);
				Employee EMPLOYEE = new Employee();
				EMPLOYEE.setEmployeeID(map.get("XpathEmployeeIDKey"));
				EMPLOYEE.setFullName(map.get("XpathEmployeeNameKey"));
				EMPLOYEE.setAddress(map.get("XpathEmployeeAddressKey"));
				EMPLOYEE.setFacultyName(map.get("XpathFacultyNameKey"));
				EMPLOYEE.setDepartment(map.get("XpathDepartmentKey"));
				EMPLOYEE.setDesignation(map.get("XpathDesignationKey"));
				employeeList.add(EMPLOYEE);
				System.out.println(EMPLOYEE.toString() + "\n");
			}
		} catch (Exception e) {
			//Used loggers
			Log.log(Level.SEVERE, e.getMessage()); 
		}
	}

	public void employeeTableCreate() {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(UtilQ.Q("q2"));
			statement.executeUpdate(UtilQ.Q("q1"));
		} catch (Exception e) {
			//Used loggers
			Log.log(Level.SEVERE, e.getMessage());
		}
	}

	public void EmployeeAdd() {
		try {
			prepared_statement = connection.prepareStatement(UtilQ.Q("q3"));
			connection.setAutoCommit(false);
			for(int i = 0; i < employeeList.size(); i++){
				Employee employee = employeeList.get(i);
				prepared_statement.setString(1, employee.getEmployeeID());
				prepared_statement.setString(2, employee.getFullName());
				prepared_statement.setString(3, employee.getAddress());
				prepared_statement.setString(4, employee.getFacultyName());
				prepared_statement.setString(5, employee.getDepartment());
				prepared_statement.setString(6, employee.getDesignation());
				prepared_statement.addBatch();
			}
			prepared_statement.executeBatch();
			connection.commit();
		} catch (Exception e) {
			//Used loggers
			Log.log(Level.SEVERE, e.getMessage());
		}
	}

	public void GetEmployeeByID(String eid) {

		Employee employee = new Employee();
		try {
			prepared_statement = connection.prepareStatement(UtilQ.Q("q4"));
			prepared_statement.setString(1, eid);
			ResultSet resultSet = prepared_statement.executeQuery();
			while (resultSet.next()) {
				employee.setEmployeeID(resultSet.getString(1));
				employee.setFullName(resultSet.getString(2));
				employee.setAddress(resultSet.getString(3));
				employee.setFacultyName(resultSet.getString(4));
				employee.setDepartment(resultSet.getString(5));
				employee.setDesignation(resultSet.getString(6));
			}
			ArrayList<Employee> arrayList = new ArrayList<Employee>();
			arrayList.add(employee);
			employeeOutput(arrayList);
		} catch (Exception ex) {
			Log.log(Level.SEVERE, ex.getMessage());
		}
	}

	public void EmployeeDelete(String eid) {

		try {
			prepared_statement = connection.prepareStatement(UtilQ.Q("q6"));
			prepared_statement.setString(1, eid);
			prepared_statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			Log.log(Level.SEVERE, e.getMessage());
		}
	}

	public void employeeDisplay() {

		ArrayList<Employee> arrayList = new ArrayList<Employee>();
		try {
			prepared_statement = connection.prepareStatement(UtilQ.Q("q5"));
			ResultSet resultSet = prepared_statement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setEmployeeID(resultSet.getString(1));
				employee.setFullName(resultSet.getString(2));
				employee.setAddress(resultSet.getString(3));
				employee.setFacultyName(resultSet.getString(4));
				employee.setDepartment(resultSet.getString(5));
				employee.setDesignation(resultSet.getString(6));
				arrayList.add(employee);
			}
		} catch (Exception e) {
			Log.log(Level.SEVERE, e.getMessage());
		}
		employeeOutput(arrayList);
	}
	
	public void employeeOutput(ArrayList<Employee> list){
		
		System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
				+ "Department" + "\t\t" + "Designation" + "\n");
		System.out
				.println("================================================================================================================");
		for(int i = 0; i < list.size(); i++){
			Employee employee = list.get(i);
			System.out.println(employee.getEmployeeID() + "\t" + employee.getFullName() + "\t\t"
					+ employee.getAddress() + "\t" + employee.getFacultyName() + "\t" + employee.getDepartment() + "\t"
					+ employee.getDesignation() + "\n");
			System.out
			.println("----------------------------------------------------------------------------------------------------------------");
		}
		
	}
}
