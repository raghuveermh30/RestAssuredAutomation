package com.qa.employee.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.TestBaseAPI;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteEmployees extends TestBaseAPI{
	
	static RequestSpecification httpRequest;
	static Response httpResponse;
	
	@BeforeClass
	public void getEmployees() {
		logger.info("*********Delete All the Employees**********");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		httpResponse = httpRequest.request(Method.GET,"/employees");
		delay();
		JsonPath jsonPath = httpResponse.jsonPath();
		String empId= jsonPath.get("[0].id");
		httpResponse = httpRequest.request(Method.DELETE, "/delete/"+empId);
		delay();
}
	
	@Test
	public void getStatusCode() {
	int statusCode	=httpResponse.getStatusCode();
	System.out.println("Status Code for the response is " +statusCode);
	Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void getResonseBody() {
	String responseBody = 	httpResponse.body().asString();
	System.out.println("The response body is " +responseBody);
	Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
	}
	
	@Test
	public void getStatusLine() {
		String statusLine = httpResponse.statusLine();
		System.out.println("StatusLine of the respons is " +statusLine);
	}
	
	@Test
	public void getStatusTime() {
	long responseTime =	httpResponse.getTime();
	System.out.println("Response Time is " +responseTime);
	}

}
