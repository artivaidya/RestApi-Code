package rest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class Test1 {
	int id; // global variable
	@Test (priority=1)
	void getUser()
	{
		given() //for define precondition
		
		.when() //for request like get,post,put,delete
			.get("https://reqres.in/api/users?page=2")
		
		.then() //validation
			.statusCode(200)/// validation 
			.body("page", equalTo(2))
			.log().all();// to printing the whole response in console window
	}
	
	@Test(priority=2)// (enabled=false) it will skipped wont be 
	void createUser() {
		
		HashMap<String, String> data=new HashMap<String ,String>(); //for generating data we using hashmap
		data.put("name", "Arti");
		data.put("Job", "QA");
		
		id=given() //id value what we getting it storing
			.contentType("application/json")
			.body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");//to capture id from response
		
		
		//.then() its a comment
		//.statusCode(201)
		//.log().all();
	}
	
	@Test (priority=3,dependsOnMethods = {"createUser"})//if ceateUser method is passed then only it will execute ,we using dependsonmethod annation from testng
	void updateUser() {
		HashMap<String, String> data=new HashMap<String ,String>(); //for generating data we using hashmap
		data.put("name", "John");
		data.put("Job", "Analyst");
		
		given() //id value what we getting it storing
			.contentType("application/json")
			.body(data)
		
		.when()
			.put("https://reqres.in/api/users/"+ id)
		
		.then()
			
				.statusCode(200)
				.log().all();
		
	}
	
	@Test(priority=4)
	void deleteUser() {
		
		given()
		
		.when()
				.delete("https://reqres.in/api/users/" +id)
		.then()
				.statusCode(204)
				.log().all();
	}


	

}
