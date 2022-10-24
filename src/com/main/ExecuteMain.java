package com.main;

import com.services.*;
import com.util.UtilTransform;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the main runnable class
 */

/**
 * ----Contributions----
 * Wilfred P.P.S - IT20601256
 * Wanni Arachchige H.S. - IT20606060
 * Pallewatta U.D.P. - IT20620820
 * Jayakody J.A.M.G. - IT20150648
 */
public class ExecuteMain {
	
	/*Initialize logger  */
	 private final static Logger Log =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	 // this is the main method
	public static void main(String[] args) {

		GetEmployeeServices employeeServices = new GetEmployeeServices();
		try {
			UtilTransform.requestTransform();
			employeeServices.EmployeesFromXML();
			employeeServices.employeeTableCreate();
			employeeServices.EmployeeAdd();
			employeeServices.GetEmployeeByID("EMP10004");
			employeeServices.EmployeeDelete("EMP10001");
			employeeServices.employeeDisplay();
		} catch (Exception e) {
			Log.log(Level.SEVERE, e.getMessage());
		}

	}

}
