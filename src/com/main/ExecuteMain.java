package com.main;

import com.services.*;
import com.util.UtilTransform;

import java.util.logging.Level;
import java.util.logging.Logger; 

public class ExecuteMain {
	
	/*Initialize logger  */
	 private final static Logger Log =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GetEmployeeServices employeeServices = new GetEmployeeServices();
		try {
			UtilTransform.requestTransform();
			employeeServices.EmployeesFormXML();
			employeeServices.employeeTableCreate();
			employeeServices.EmployeeAdd();
//			employeeServices.GetEmployeeByID("EMP10004");
//			employeeServices.EmployeeDelete("EMP10001");
			employeeServices.employeeDisplay();
		} catch (Exception e) {
			Log.log(Level.SEVERE, e.getMessage());
		}

	}

}
