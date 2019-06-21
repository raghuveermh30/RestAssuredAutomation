package com.qa.datadriventest;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class DataDrivenAPIRequest {

	/*Pre-Requists
	 * Prepare the test Data file in the Excel
	 * Add the Apache POI Dependency to the POM
	 * Need to prepare the Utility File to read the Data from Excel file
	 *  Use TestNG with Data provider Method
*/	
	@Test(dataProvider = "empData")
	public void postNewEmployes(String EmpName, String EmpSalary, String EmpAge
) {
		
		RestAssured.baseURI= "http://dummy.restapiexample.com/api/v1";
		
		  
		  //Request object
		  RequestSpecification httpRequest=RestAssured.given();
		  
		  JSONObject js = new JSONObject();
		  js.put("name", EmpName);
		  js.put("salary", EmpSalary);
		  js.put("age", EmpAge);
		  
		  httpRequest.header("Content-Type","application/json");
		  httpRequest.body(js.toJSONString());
		  
		  Response httpResponse = httpRequest.request(Method.POST, "/create");
		  
		 String responseBody =  httpResponse.body().asString();
		 System.out.println("Response Body " +responseBody);
		 
		 Assert.assertEquals(responseBody.contains(EmpName), true);
		 Assert.assertEquals(responseBody.contains(EmpSalary), true);
		 Assert.assertEquals(responseBody.contains(EmpAge), true);
		 
		int statusCode = httpResponse.getStatusCode();
		System.out.println("Status Code " +statusCode);

		String successCode  = httpResponse.jsonPath().get("SuccessCode");
		System.out.println("Sucess code "+successCode);
	}

	@DataProvider(name = "empData")
	public Object [][] getEmpData() {
		
		Object data [][] =TestUtil.getTestData("EmpDetails");
	//	String empData [][]= {{"abc12","3000","32"},{"xyz12","3000","32"},{"pqr12","3000","32"}};
		return data;
	}
	
}
 