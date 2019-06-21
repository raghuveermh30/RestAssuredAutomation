package com.qa.employee.testcases;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	public static String empname() {
	String generatedString =	RandomStringUtils.randomAlphabetic(1);
	return("John" + generatedString);
	}
	
	public static String empSal() {
		String generatedNumber = RandomStringUtils.randomNumeric(5);
		return(generatedNumber);
	}

	public static String empAge() {
		String generatedNumber =RandomStringUtils.randomNumeric(2);
		return generatedNumber;
	}
}
