package com.qa.employee.testcases;

import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.TestBaseAPI;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class PutEmployeeDetails extends TestBaseAPI{
	static RequestSpecification httpRequest;
	static Response httpResponse;

	String empName = RestUtils.empname();
	String age = RestUtils.empAge();
	String sal = RestUtils.empSal();
	
	@BeforeClass
	public void getEmployees() {
		logger.info("*********Get All the Employees**********");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		JSONObject js = new JSONObject();
		js.put("name", empName);
		js.put("age", age);
		js.put("salary", sal);

		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(js.toJSONString());  // it will attach the above json into body
		
		httpResponse = httpRequest.request(Method.PUT,"/update/" +empId);
		delay();
		
}
	
	@Test
	public void getResponseBody() {
		String responseBody = httpResponse.body().asString();
		System.out.println("Response is " +responseBody);
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(age), true);
		Assert.assertEquals(responseBody.contains(sal), true);
	}
	
	@Test
	public void getStatusCode() {
		int statusCode=httpResponse.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	
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
		System.out.println("content Length " +contentLength);
		//Assert.assertTrue(Integer.parseInt(contentLength)<1500);
	}
	
	@Test
	public void checkServerType() {
		logger.info("******* Checking the Server Type *******");
	String serverType = httpResponse.header("Server");
	logger.info("Server type is " +serverType);
	}


	
	@AfterClass
	public void tearDown() {
		logger.info("***************** Updating the each employee test is finished *******");
	}
	


}
