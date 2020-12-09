package io.hackages.learning.demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestGetArticles {

	@BeforeTest
	public void setUp () {
		RestAssured.baseURI = "http://localhost:8080/myjaxrs.server.app/webapi";
	}

	@Test
	public void testMyResource() throws URISyntaxException {
		URI uri = new URI("/myresource");
		given()
				.accept(ContentType.TEXT)
				.when()
				.get(uri)
				.then()
				.assertThat()
				.statusCode(HttpStatus.SC_OK)
				.and()
		.body( equalTo("Hello World!"))
		;
	}

	@Test
	public void testGetArticles() throws URISyntaxException {
		URI uri = new URI("/articles");
		Response response = given()
				.accept(ContentType.JSON)
				.when()
				.get(uri);

		System.out.println(response.body().prettyPrint());

		response
				.then()
				.assertThat()
				.statusCode(HttpStatus.SC_OK)
				.and()
				.body("size()", is(2))
				.and()
				.body("body", hasItem(is("Hello world")))
				.and()
				.body("body", hasItem(is("Hello Jersey")))
		;
	}

	@Test
	public void testPostArticle(){

		given()
				.accept(ContentType.JSON)
				.and()
				.contentType(ContentType.JSON)
				.and()
				.request()
				.body("{\n" +
					  "  \"body\": \"Renaud says Hello\"\n" +
					  "}")
				.when()
				.post("/articles")
				.then()
				.statusCode(HttpStatus.SC_NOT_ACCEPTABLE);

		Response post = given()
				.accept(ContentType.TEXT)
				.and()
				.contentType(ContentType.JSON)
				.and()
				.request()
				.body("{\n" +
					  "  \"body\": \"Renaud says Hello\"\n" +
					  "}")
				.when()
				.post("/articles");

		// Print response

		post.then()
			.statusCode(200)
			.and()
			.body(is("article added"));

		/*
		given()
				.accept(ContentType.JSON)
				.when()
				.get("/articles")
				.then()
				.body("size()", is(3));
*/

	}
}
