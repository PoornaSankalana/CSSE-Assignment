package com.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.Properties;
import com.util.*;

/**
 * this method is for the catch properties from config.properties
 */
public class UtilC {

	public static final Properties properties = new Properties();

	static {
		try {
			properties.load(UtilQ.class.getResourceAsStream("../config/config.properties"));
		} catch (Exception e) {
			
		}
	}
}
