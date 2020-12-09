package io.hackages.learning.demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

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
		given()
				.accept(ContentType.XML)
				.when()
				.get(uri)
				.then()
				.assertThat()
				.statusCode(HttpStatus.SC_OK)
				.and()
				.body("articles.article.size()", is(2))
				.and()
				.body("articles.article.body", hasItem(is("Hello world")))
				.and()
				.body("articles.article.body", hasItem(is("Hello Jersey")))
		;
	}
}
