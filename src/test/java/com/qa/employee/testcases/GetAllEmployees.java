package com.qa.employee.testcases;

import org.junit.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.TestBaseAPI;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class GetAllEmployees extends TestBaseAPI{
	
	@BeforeClass
	public void getEmployees() {
		logger.info("*********Get All the Employees**********");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		httpResponse = httpRequest.request(Method.GET,"/employees");
		delay();
		
	}

	@Test
	public void getCheckResponseBody() {
		logger.info("************** Checking the Response Body ********");
		String responseBody = httpResponse.body().asString();
		System.out.println("Response Body " +responseBody);
		logger.info("Response Body is " +responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	
	@Test
	public void checkStatusCode() {
		logger.info("******** Checking the status Code ******");
		int statusCode = httpResponse.getStatusCode();
		System.out.println("Status Code for the Response " +statusCode);
		logger.info("status Code "+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void checkResponseTime() {
		logger.info("******* Checking the Response Time *******");
		long responseTime = httpResponse.getTime();
		System.out.println("Response time " +responseTime);
		logger.info("Response Time is " +responseTime);
		/*if(responseTime>10000) {
			logger.warn("Response Time is greater than 2000");
			Assert.assertTrue(responseTime<10000);
		}*/
	}
		
	@Test
	public void getStatusLine() {
		logger.info("******* Checking the status Line *******");
		String statusLine = httpResponse.getStatusLine();
		System.out.println("Status Line is " +statusLine);
		logger.info("Status Line is " +statusLine);
		
	}
	
	//Validating the Headers
	@Test
	public void checkContentType() {
		logger.info("******* Checking the Content Type *******");
		String contentType = httpResponse.header("Content-Type");
		System.out.println("Content Type is " +contentType);
		logger.info("Content Type is " +contentType);
	
	}
	
	
	
	@Test
	public void checkServerType() {
		logger.info("******* Checking the Server Type *******");
	String serverType = httpResponse.header("Server");
	logger.info("Server type is " +serverType);
	}
	
	@AfterClass
	public void tearDown() {
		logger.info("***************** Getting the all employees test is finished *******");
	}
	
}
