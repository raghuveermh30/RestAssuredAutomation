package com.qa.test;



import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GetApiRequest {
	
	@Test
	 void getweatherDetails()
	 {
	  //Specify base URI
	  RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
	  
	  //Request object
	  RequestSpecification httpRequest=RestAssured.given();
	  
	  //Response object
	  Response httpResponse=httpRequest.request(Method.GET,"/Hyderabad");
	  
	  //print response in console window
	  String responseBody = httpResponse.getBody().asString();
	  
	  System.out.println("Response Body " +responseBody);
	  
	 int statusCode =  httpResponse.getStatusCode();
	 System.out.println("Status Code is " +statusCode);
	 Assert.assertEquals(statusCode, 200);
	 
	String statusLine = httpResponse.getStatusLine();
	System.out.println("Status Line of the response is  : " +statusLine);
	
	
	  
	  
	 }
	  

}
