package io.hackages.learning.demo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public abstract class AbstractArticleTest {

	@BeforeTest
	public void setUp () {
		RestAssured.baseURI = "http://localhost:8080/myjaxrs.server.app/webapi";
	}

	@BeforeMethod
	public void resetDb() {

		given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.when()
				.get("/admin/resetDB");
		getDefaultArticles().forEach(this::insertArticle);
		getDefaultComments().forEach(this::insertComments);

	}

	void insertComments(Object[] comment){
		given().contentType(ContentType.JSON)
				.body(comment[1])
				.when()
				.post("/articles/"+ comment[0] + "/comments");
	}

	void insertArticle(String article){
		given().contentType(ContentType.JSON)
				.body(article)
				.when()
				.post("/articles");
	}


	public List<String> getDefaultArticles() {
		return Arrays.asList(
				"{\n" +
				"      \"body\": \"Hello world\",\n" +
				"      \"comments\":       [\n" +
				"                  {\n" +
				"            			\"text\": \"I like this article : 8/10\"\n" +
				"         			},\n" +
				"                  {\n" +
				"            			\"text\": \"I hate this article : 2/10\"\n" +
				"         			}\n" +
				"      ]\n" +
				"   }",
				"{\n" +
				"      \"body\": \"Hello Jersey\"\n" +
				"   }",
				"{\n" +
				"      \"body\": \"Hello New York\"\n" +
				"   }"
		);
	}

	public List<Object[]> getDefaultComments(){
		List<Object[]> comments = new ArrayList<>();
		comments.add(
				new Object [] {Integer.valueOf(1), "{\"text\": \"I like this article : 8/10\"}"});

		comments.add(
				new Object [] {Integer.valueOf(1),  "{\"text\": \"I hate this article : 2/10\"}"});
		return comments;
	}

}
