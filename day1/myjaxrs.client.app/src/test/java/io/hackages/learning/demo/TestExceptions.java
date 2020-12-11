package io.hackages.learning.demo;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class TestExceptions extends AbstractArticleTest{

	@Test
	public void testGetArticle_NO_CONTENT(){
		given()
				.get("/articles/56")
				.then()
				.statusCode(HttpStatus.SC_NO_CONTENT)
			;

	}

	@Test
	public void testGetComment_NO_CONTENT(){
		given()
				.get("/articles/1/comments/600")
				.then()
				.statusCode(HttpStatus.SC_NO_CONTENT)
		;

	}

	@Test
	public void testWrongUrl(){
		given()
				.get("/qsdqsdqsdsqd")
				.then()
				.statusCode(HttpStatus.SC_NOT_FOUND)
		;

	}

	@Test
	public void testPutIllegalId_NOT_FOUNT(){
		given()
				.contentType(ContentType.JSON)
				.body("{\"body\" : \"update this article's description\"}")
				.put("/articles/56")
				.then()
				.statusCode(HttpStatus.SC_NOT_FOUND)
				.body(Matchers.equalTo("article with id 56 does not exist"))
		;
	}

	@Test
	public void testUpdateInexistingCommentExistingArticle(){
		given()
				.contentType(ContentType.JSON)
				.body("{\"text\" : \"update this article's description\"}")
				.put("/articles/1/comments/600")
				.then()
				.statusCode(HttpStatus.SC_NOT_FOUND)
				.body(Matchers.equalTo("comment with id 600 for article with id 1 does not exist"))
		;
	}

	@Test
	public void testAddCommentToNotExistingArticle(){
		given()
				.contentType(ContentType.JSON)
				.body("{\"text\" : \"new comment\"}")
				.post("/articles/56/comments/")
				.then()
				.statusCode(HttpStatus.SC_NOT_FOUND)
				.body(Matchers.equalTo("article with id 56 does not exist"))
		;
	}
}
