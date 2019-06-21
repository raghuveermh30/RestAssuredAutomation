package com.qa.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBaseAPI {
	
	public static RequestSpecification httpRequest;
	public static Response httpResponse;
	public static String empId = "65547";
	
	public static Logger logger ;
	
	@BeforeClass
	public void setUp() {
		
		logger = Logger.getLogger(TestBaseAPI.class);
//Added Logger
		//PropertiesConfigurator is used to configure logger from properties file
        PropertyConfigurator.configure("C:\\Users\\raghuveer.mh\\eclipse-workspace\\RestAssuredAutomation\\src\\test\\java\\com\\qa\\employee\\testcases\\Log4j.properties");
 
        //Log in console in and log file
        logger.debug("Log4j appender configuration is successful !!");
		logger.setLevel(Level.DEBUG);
		
	}
	
	public static void delay() {
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

}
