package com.util;

import javax.xml.xpath.XPathFactory;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.w3c.dom.Document;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import com.main.*;

public class UtilTransform extends UtilC {

	private static final ArrayList<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();

	private static Map<String, String> map = null;

	public static void requestTransform() throws Exception {

		Source employeeRequest = new StreamSource(new File("src/com/config/EmployeeRequest.xml"));
		Source employeeModified = new StreamSource(new File("src/com/config/Employee-modified.xsl"));
		Result employeeResponse = new StreamResult(new File("src/com/config/EmployeeResponse.xml"));
		
		TransformerFactory.newInstance().newTransformer(employeeModified).transform(employeeRequest, employeeResponse);
	}
	
	/**
	 * @throws Exception
	 */

	public static ArrayList<Map<String, String>> xmlXpaths() throws Exception {

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse("src/com/config/EmployeeResponse.xml");
		XPath xPath = XPathFactory.newInstance().newXPath();
		int n = Integer.parseInt((String) xPath.compile("count(//Employees/Employee)").evaluate(document, XPathConstants.STRING));
		
		for (int i = 1; i <= n; i++) {
			map = new HashMap<String, String>();
			map.put("XpathEmployeeIDKey", (String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeID/text()")
					.evaluate(document, XPathConstants.STRING));
			map.put("XpathEmployeeNameKey", (String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeFullName/text()")
					.evaluate(document, XPathConstants.STRING));
			map.put("XpathEmployeeAddressKey",
					(String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeFullAddress/text()").evaluate(document,
							XPathConstants.STRING));
			map.put("XpathFacultyNameKey", (String) xPath.compile("//Employees/Employee[" + i + "]/FacultyName/text()")
					.evaluate(document, XPathConstants.STRING));
			map.put("XpathDepartmentKey", (String) xPath.compile("//Employees/Employee[" + i + "]/Department/text()")
					.evaluate(document, XPathConstants.STRING));
			map.put("XpathDesignationKey", (String) xPath.compile("//Employees/Employee[" + i + "]/Designation/text()")
					.evaluate(document, XPathConstants.STRING));
			arrayList.add(map);
		}
		
		return arrayList;
	}
}
