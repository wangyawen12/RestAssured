package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import groovyjarjarasm.asm.commons.Method;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GetWeather {
	@Test
	
  public void getWeatherData() {
	  
//	  An alternative way to get response
//		Response r =RestAssured.given()
//        .pathParam("name", "Hayward")
//        .when()
//            .get("http://restapi.demoqa.com/utilities/weather/city/{name}");
//		System.out.println("Response Body is: " + r.asString());
//		
		
		
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		 
		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();
 
		// Make a GET request call directly by using RequestSpecification.get() method.
		// Make sure you specify the resource name.
		Response response = httpRequest.get("/Hayward");
		// Response.asString method will directly return the content of the body
		// as String.
		System.out.println("Response Body is =>  " + response.asString());
		int statusCode = response.getStatusCode();//get status code
		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
		
		// Reader header of a give name. In this line we will get
		// Header named Content-Type
		String contentType = response.header("Content-Type");
		System.out.println("Content-Type value: " + contentType);
	 
		// Reader header of a give name. In this line we will get
		// Header named Server
		//String serverType =  response.header("Server");
		//System.out.println("Server value: " + serverType);
	 
		// Reader header of a give name. In this line we will get
		// Header named Content-Encoding
		//String acceptLanguage = response.header("Content-Encoding");
		//System.out.println("Content-Encoding: " + acceptLanguage);
		
		// Get all the headers. Return value is of type Headers.
		// Headers class implements Iterable interface, hence we
		// can apply an advance for loop to go through all Headers
		// as shown in the code below
		Headers allHeaders = response.headers();
	 
		// Iterate over all the Headers
		for(Header header : allHeaders)
		{
			System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
		}
		
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
	 
		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		String city = jsonPathEvaluator.get("City");
	 
		// Let us print the city variable to see what we got
		System.out.println("City received from Response " + city);
	 
		// Validate the response
		Assert.assertEquals(city, "Hayward", "Correct city name received in the Response");
	 
  }
	
	
	

}
