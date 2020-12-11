package io.hackages.learning.demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestComments extends SetDataForEndToEndTests {

	@Test
	public void testGetArticleCommentById(){
		given()
				.accept(ContentType.JSON)
				.when()
				.get("articles/1/comments/2")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.body("text", equalTo("I hate this article : 2/10"));
	}

	@Test
	public void testPutComment(){
		Response put = given()
				.accept(ContentType.JSON)
				.and()
				.contentType(ContentType.JSON)
				.and()
				.request()
				.body("{\n" +
					  "  \"text\": \"Finally, article is bad\"\n" +
					  "}")
				.when()
				.put("/articles/1/comments/1");

		// Print response

		put.then()
				.statusCode(200)
				.and()
				.body("text", is("Finally, article is bad"));

		given()
				.accept(ContentType.JSON)
				.when()
				.get("/articles/1/comments/1")
				.then()
				.body("text", is("Finally, article is bad"));
	}


	@Test
	public void testGetArticleCommentsEmpty(){
		Response response = given()
				.accept(ContentType.JSON)
				.when()
				.get("/articles/2/comments");

		response.body().prettyPrint();

		response
				.then()
				.assertThat()
				.statusCode(HttpStatus.SC_OK)
				.and()
				.body("size()", equalTo(0))
		;
	}

	@Test
	public void testAddArticleComment(){
		Response response = given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.and()
				.request()
				.body("{\n" +
					  "  \"text\": \"Delivered on time\"\n" +
					  "}")
				.when()
				.post("/articles/3/comments");

		response.body().prettyPrint();

		response
				.then()
				.assertThat()
				.statusCode(HttpStatus.SC_OK)
				.and()
				.body("text", equalTo("Delivered on time"));

		Response articleResponse = given()
				.accept(ContentType.JSON)
				.get("/articles/3");

		articleResponse.body().prettyPrint();
	}

	@Test
	public void testGetArticleComments(){
		Response response = given()
				.accept(ContentType.JSON)
				.when()
				.get("/articles/1/comments");

		response.body().prettyPrint();

		response
				.then()
				.assertThat()
				.statusCode(HttpStatus.SC_OK)
				.and()
				.body("size()", equalTo(2))
		;
	}

}
