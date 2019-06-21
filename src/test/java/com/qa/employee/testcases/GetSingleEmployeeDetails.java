package com.qa.employee.testcases;

import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.TestBaseAPI;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class GetSingleEmployeeDetails extends TestBaseAPI{
	
	@BeforeClass
	public void getEmployees() {
		logger.info("*********Get All the Employees**********");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		httpResponse = httpRequest.request(Method.GET,"/employee/" +empId);
		delay();
}
	
	@Test
	
	public void getResponseBody() {
		String responseBody = httpResponse.body().asString();
		Assert.assertEquals(responseBody.contains(empId), true);
	}
	
	@Test
	public void getStatusCode() {
		int statusCode = httpResponse.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void getResponseTime() {
		long responseTime = httpResponse.getTime();
		System.out.println("Response time " +responseTime);
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
		public void contentLength() {
			String contentLength = httpResponse.header("Content-Length");
			Assert.assertTrue(Integer.parseInt(contentLength)<1500);
		}
		
		@Test
		public void checkServerType() {
			logger.info("******* Checking the Server Type *******");
		String serverType = httpResponse.header("Server");
		logger.info("Server type is " +serverType);
		}
		
		@AfterClass
		public void tearDown() {
			logger.info("***************** Getting the each employee test is finished *******");
		}
		
	
	
}
