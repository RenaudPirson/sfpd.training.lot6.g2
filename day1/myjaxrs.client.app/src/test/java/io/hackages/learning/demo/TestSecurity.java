package io.hackages.learning.demo;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class TestSecurity {
	@BeforeTest
	public void setUp () {
		RestAssured.baseURI = "http://localhost:8080/myjaxrs.server.app/webapi";
	}

	@Test
	public void testNoAuthHeader(){
		given().when()
		.get("/articles")
		.then()
		.statusCode(HttpStatus.SC_UNAUTHORIZED)
				;
	}
}
