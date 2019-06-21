package com.qa.test;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class PostApiTest {
	
	@Test
	
	public void registrationForm() {
		
		
		 RestAssured.baseURI="https://reqres.in/api";
		  
		  //Request object
		  RequestSpecification httpRequest=RestAssured.given();
		  
		   
		//creating the payload (json) via JSONObject
		
		JSONObject js = new JSONObject();
		js.put("FirstName", "JohnXYZ");
		js.put("LastName", "XYZJohn");
		js.put("Username", "JohnXYZ");
		js.put("Password", "JohnXYZxyx");
		js.put("EmailId", "JohnXYZ@gmail.com");
		
		//Request Payload along with the post Request and passing the header
		
		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(js.toJSONString());  // it will attach the above json into body
		
		Response httpRespone = httpRequest.request(Method.POST,"/users");
		
		// print the response
		
		String responseBody = httpRespone.getBody().asString();
		System.out.println("Response is " +responseBody);
		
		int statusCode = httpRespone.getStatusCode();
		System.out.println("status Code is : " + statusCode);
		Assert.assertEquals(statusCode, 201);
		
		String successCode  = httpRespone.jsonPath().get("SuccessCode");
		System.out.println("Sucess code "+successCode);
		//Assert.assertEquals(successCode, "OPERATION_SUCCESS");
		
		
	}

}
