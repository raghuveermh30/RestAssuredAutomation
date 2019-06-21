package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestWithHeader {
	
	@Test
	public void getGoogleMapApi() {
		
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response httpResponse = httpRequest.request(Method.GET, "/Hyderabad");
		
		String responseBody = httpResponse.getBody().asString();
		
		System.out.println("Response Body : " +responseBody);
		
		//Validating the Headers
		
		String contentType = httpResponse.header("Content-Type");
		System.out.println("Content Type " +contentType);
		Assert.assertEquals(contentType, "application/json");
		
		String contentEncoding = httpResponse.header("Content-Encoding");
		System.out.println("Content-Encoding " +contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
		
		System.out.println("***************");
		//Printing all the Headers
		
		Headers allHeaders = httpResponse.headers();
		
		System.out.println("Headers are :  " );
		
		for(Header header :allHeaders) {
			System.out.println(header.getName()+"  " +header.getValue());
			
		}
		
		//Verifying the Json Response
		
		System.out.println("********************");
		
		 	 Assert.assertEquals(responseBody.contains("Hyderabad"),true);
		 	 
		 // comparing the Indivudual Class
		 	 System.out.println("Comparing the each value in the response json");
		 	 
		JsonPath json = httpResponse.jsonPath();
		
		System.out.println(json.get("City"));
		System.out.println(json.get("Temperature"));
		System.out.println(json.get("Humidity"));
		System.out.println(json.get("WeatherDescription"));
		System.out.println(json.get("WindSpeed"));
		System.out.println(json.get("WindDirectionDegree"));
		
		
		
		
		
		
		
		
	}

}
