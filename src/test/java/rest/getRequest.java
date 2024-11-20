package rest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class getRequest {
	
	@Test
	void Request() {
		Response response =RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");
		// Response is a class provided by Restassured that store response recieved by server
		System.out.println("Status code:" + response.getStatusCode());
		
		System.out.println("Response Body: "+ response.getBody().asString());
		
		//response.getStatusCode(): Retrieves the HTTP status code.
		//response.getBody().asString(): Converts the response body to a String.	
		
	}
	
	@Test
	void validate() {
		given()
	
		.when()
			.get("https://jsonplaceholder.typicode.com/posts/1")
		
		.then()
			.statusCode(200)
			.body("userId", equalTo(1))
			.body("Title", containsString("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
		
		
		
	}
	@Test
	void reuestWithJsonPaylod() {
		
	}
	
	
	
	
	
	
	
	

}
