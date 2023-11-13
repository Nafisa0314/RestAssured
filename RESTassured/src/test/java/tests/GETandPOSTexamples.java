package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class GETandPOSTexamples {
	
	@Test
	public void testGet() {
		baseURI = "https://reqres.in/api";
		given().
		get("/users?page=2").
		then().
		statusCode(200).body("data[0].first_name", equalTo("Michael")).
		body("data.first_name", hasItems("Lindsay","Tobias"));
	}
	
	@Test
	public void testPost() {
//		need to create body for post
//		Map<String,Object> map= new HashMap<String,Object>();
//		map.put("name", "Nafisa");
//		map.put("job", "learner");
//		System.out.println(map);
//		
//		JSONObject request = new JSONObject(map);
		
		JSONObject request = new JSONObject();
	
		request.put("name", "Nafisa");
		request.put("job", "learner");
		
		System.out.println(request.toJSONString());
		
		baseURI="https://reqres.in/api";
		given().
		header("Content-Type","application/JSON").
		body(request.toJSONString()).
		when().
		post("/users").
		then().
//		200-ok,201-created
		statusCode(201).
		log().all();
		
		
	}

}
