package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class XMLSchemaValidator {
	@Test
	public void schemaValidation() throws Exception {
		baseURI = "https://ecs.syr.edu/faculty/fawcett/Handouts/cse775/code/calcWebService";

		File file = new File("./SoapRequest/Add.xml");

		if (file.exists()) {
			System.out.println(" >>file exists<< ");
		}
		FileInputStream fileInputStream = new FileInputStream(file);
		String requestbody = IOUtils.toString(fileInputStream, "UTF-8");

		given().contentType("text/xml").accept(ContentType.XML).body(requestbody).

				when().post("/Calc.asmx").

				then().statusCode(200).log().all().
				and().
				body("//*AddResult.text()", equalTo("5")).
				and().
				assertThat().
				body(matchesDtdInClasspath("Calculator.xsd"));
	}

}
