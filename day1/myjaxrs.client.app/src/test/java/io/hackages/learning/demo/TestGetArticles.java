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
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class TestGetArticles {

	@Test
	public void testMyResource() throws URISyntaxException {
		URI uri = new URI("http://localhost:8080/myjaxrs.server.app/webapi/myresource");
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
		URI uri = new URI("http://localhost:8080/myjaxrs.server.app/webapi/articles");
		given()
				.accept(ContentType.XML)
				.when()
				.get(uri)
				.then()
				.assertThat()
				.statusCode(HttpStatus.SC_OK)
				.and()
				.body("articles.article.size()", is(2))
		;
	}

	@Test
	public void testGetArticles2() throws URISyntaxException {
		URI uri = new URI("http://localhost:8080/myjaxrs.server.app/webapi/articles");
		given()
				.accept(ContentType.XML)
				.when()
				.get(uri)
				.then()
				.assertThat()
				.statusCode(HttpStatus.SC_OK)
				.and()
				.body("articles.article.body", hasItem(is("Hello world")))
				.and()
				.body("articles.article.body", hasItem(is("Hello Jersey")))
		;
	}
}
