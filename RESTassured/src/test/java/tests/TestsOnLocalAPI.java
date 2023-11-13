package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class TestsOnLocalAPI {
	
//	@Test
	public void get() {
		baseURI="http://localhost:3000";
		given().
		get("/users").
		then().statusCode(200).
		log().all();
		
	}
//	@Test
	public void post() {
		JSONObject request=new JSONObject();
		request.put("firstName", "Thomas");
		request.put("lastName", "Edison");
		request.put("subjectId", "2");
		
		
		baseURI="http://localhost:3000";
		given().
		header("Content-Type","application/JSON").
		body(request.toJSONString()).
		when().
		post("/users").
		then().statusCode(201).
		log().all();
		
	}
//	@Test
	public void put() {
		JSONObject request=new JSONObject();
		request.put("firstName", "Albert");
		request.put("lastName", "Einstein");
		request.put("subjectId", "2");
		
		
		baseURI="http://localhost:3000";
		given().
		header("Content-Type","application/JSON").
		body(request.toJSONString()).
		when().
		put("/users/7").
		then().statusCode(200).
		log().all();
		
	}
	
//	@Test
	public void patch() {
		JSONObject request=new JSONObject();
		request.put("firstName", "Thomas");
		
		baseURI="http://localhost:3000";
		given().
		header("Content-Type","application/JSON").
		body(request.toJSONString()).
		when().
		patch("/users/7").
		then().statusCode(200).
		log().all();
		
	}
	@Test
	public void delete() {
		
		baseURI="http://localhost:3000";
		when().
		delete("/users/7").
		then().statusCode(200).
		log().all();
		
	}

}
