package io.hackages.learning.demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

public class TestProfiles {
	private static String BASE_URI = "http://localhost:8080/myjaxrs.server.app/webapi";

	@BeforeTest
	public void setUp(){
		RestAssured.baseURI = BASE_URI;
	}

	@Test
	public void getProfiles(){
		given()
				.accept(ContentType.JSON)
		.when()
				.get("/profiles")
		.then()
				.body("size()", is(2))
		.and()
				.body("userName", hasItems(is("X1RPN"), is("X1VBA")))
		.and()
				.body("email", hasItems(is("x1rpn@sfpd.be"), is("x1vba@sfpd.be")))
		;
	}

	@Ignore
	@Test
	public void getProfileByName(){
		given()
				.accept(ContentType.JSON)
				.when()
				.get("/profiles/X1RPN")
				.then()
				.body("size()", is(1))
				.and()
				.body("userName", hasItems(is("X1RPN")))
				.and()
				.body("email", hasItems(is("x1rpn@sfpd.be")))
		;
	}
}
