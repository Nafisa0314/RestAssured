package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.baseURI;

import java.io.File;
import java.io.FileInputStream;

public class SoapXMLRequest {
	
	@Test
	public void validateSoapXML() throws Exception {
		baseURI= "https://ecs.syr.edu/faculty/fawcett/Handouts/cse775/code/calcWebService";
		
		File file = new File("./SoapRequest/Add.xml");
		
		if(file.exists()) {
			System.out.println(" >>file exists<< ");
		}
		FileInputStream fileInputStream= new FileInputStream(file);
		String requestbody = IOUtils.toString(fileInputStream,"UTF-8");
		
		given().
		contentType("text/xml").accept(ContentType.XML).
		body(requestbody).
		
		when().
		post("/Calc.asmx").
		
		then().
		statusCode(200).
		log().all().
		and().
		body("//*AddResult.text()",equalTo("5"));
	}

}
