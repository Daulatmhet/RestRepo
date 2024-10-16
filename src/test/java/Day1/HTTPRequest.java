package Day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import static io.restassured.matcher.RestAssuredMatchers.*;

/*

given() content Typee , set , set cookies ,add auth , add param , set headers info etc...

when()
get, post, put , delete

then()
Validate status code , extract response , extract headers cookies and response body.





*/

public class HTTPRequest {
	
	
	int id ;
	
	@Test(priority = 1)
	void getUser()
	{
		
		given()
		
		.when()
		.get("https://reqres.in/api/users?page=2")
		
		.then()
		.statusCode(200)
		.body("page", equalTo(2))
		 .log().all();
		 
		
	}
	
	
	@Test(priority = 2)
	void CreateUser()
	{
		
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("name", "sanket");
		hash.put("job", "QA");
		
		
	 id =	given()
		 .contentType("application/json")
		 .body(hash)
		
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		
		
		
		
//		.then()
		//.statusCode(201)
		//.log().all();
	}
	
	
	@Test(priority = 3,dependsOnMethods = {"CreateUser"})
	void UpdateUser()
	{
		
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("name", "John");
		hash.put("job", "Police");
		
		
		given()
				 .contentType("application/json")
				 .body(hash)
				
				.when()
				.put("https://reqres.in/api/users/"+id)
				
				.then()
				.statusCode(200)
		         .log().all();
				
		 
		
		
	}
	
	
	
	
	

}
