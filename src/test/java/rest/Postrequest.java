package rest;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
//	//post request body using hashMap

public class Postrequest {
	@Test(priority=2)// (enabled=false) it will skipped wont be 
	void createuser() {
		int id;
		
		HashMap<String, String> data=new HashMap<String ,String>(); //for generating data we using hashmap
		data.put("name", "Arti");
		data.put("Job", "QA");
		
		id=given() //id value what we getting it storing
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");//to capture id from response
		
		
		//.then()
			//.statusCode(201)
			//.log().all();
	}
	//post request body using org.json library
	// add library cd Json to the Pom.xml
	@Test
	void testpost() {
		JSONObject data=new JSONObject();// make object of JSONObject class
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "12345");
		
		String courseArr[]= {"C","C++"};//create java array for adding courses name
		
		data.put("courses", courseArr);
		
		given()
		.contentType("application/json")
		.body(data.toString());//if u are creating data by Jsonobect then u can pass data in string format
		
	
		
	}
	//Post request body Using pojo class (plain OLd java object)
	//this will use encapsulation concept of java so u will creat one pojo class with gettter and setter class
	
	@Test
	void pojo() {
		PojoClass data= new PojoClass();
		data.setName("Scott");
		data.setLocation("France");
		data.setPhone("12345");
		String couseArr[]= {"c","C++"};
		data.setCourses(couseArr);
		given()
		.when()
		.then();
			
	}
	//post request body using external json file
	//we are keeping data in external file like body.jsom
	@Test
	void exJson() throws FileNotFoundException {
		
		File f=new File(".\\body.json");
		FileReader fr= new FileReader(f);
		JSONTokener jt= new JSONTokener(fr);
		JSONObject data=new JSONObject(jt);
		
		
		given()
		.contentType("application/json")
		.body(data.toString());
	}
	
	

	
	
	

}
