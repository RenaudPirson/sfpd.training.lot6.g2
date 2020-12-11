package io.hackages.learning.demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestArticleResource extends SetDataForEndToEndTests {

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

		response.body().prettyPrint();

		response
				.then()
				.assertThat()
				.statusCode(HttpStatus.SC_OK)
				.and()
				.body("items.size()", is(3))
				.and()
				.body("items.body", hasItem(is("Hello world")))
				.and()
				.body("items.body", hasItem(is("Hello Jersey")))
				.and()
				.body("items.body", hasItem(is("Hello New York")))
		;
	}

	@Test
	public void testPostArticle(){

		Response post = given()
				.accept(ContentType.JSON)
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
			.body("article.body", is("Renaud says Hello"));

	}

	@Test
	public void testPut(){

		Response put = given()
				.accept(ContentType.JSON)
				.and()
				.contentType(ContentType.JSON)
				.and()
				.request()
				.body("{\n" +
					  "  \"body\": \"Renaud says Hello modified\"\n" +
					  "}")
				.when()
				.put("/articles/2");

		put.prettyPrint();

		put.then()
				.statusCode(200)
				.and()
				.body("article.body", is("Renaud says Hello modified"));

		given()
				.accept(ContentType.JSON)
				.when()
				.get("/articles/2")
				.then()
				.body("article.body", is("Renaud says Hello modified"));
	}

	@Test
	public void testFilter_offset1_limit1(){
		Response response = given()
				.param("offset", 1)
				.param("limit", 1)
				.accept(ContentType.JSON)
				.when()
				.get("/articles");

		response.body().prettyPrint();

		response
				.then()
				.assertThat()
				.statusCode(HttpStatus.SC_OK)
				.and()
				.body("items.size()", equalTo(1))
				.and()
				.body("items.id", hasItem(is(2)))
		;
	}

	@Test
	public void testFilter_offset1_nolimit(){
		Response response = given()
				.param("offset", 1)
				.accept(ContentType.JSON)
				.when()
				.get("/articles");

		response.body().prettyPrint();

		response
				.then()
				.assertThat()
				.statusCode(HttpStatus.SC_OK)
				.and()
				.body("items.size()", equalTo(2))
		;
	}

	@Test
	public void testFilter_year_2020(){
		Response response = given()
				.param("year", 2020)
				.accept(ContentType.JSON)
				.when()
				.get("/articles");

		response.body().prettyPrint();

		response
				.then()
				.assertThat()
				.statusCode(HttpStatus.SC_OK)
				.and()
				.body("items.size()", equalTo(3))
		;
	}

	@Test
	public void testFilter_year_2000(){
		Response response = given()
				.param("year", 2000)
				.accept(ContentType.JSON)
				.when()
				.get("/articles");

		response.body().prettyPrint();

		response
				.then()
				.assertThat()
				.statusCode(HttpStatus.SC_OK)
				.and()
				.body("items.size()", equalTo(0))
		;
	}

	@Test
	public void testGetArticleNoContent(){
		given()
				.accept(ContentType.JSON)
				.when()
				.get("articles/500")
				.then()
				.statusCode(HttpStatus.SC_NO_CONTENT);
	}




}
