package io.hackages.learning.demo;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class SampleTest {

    @Test
    public void test_endpoint_name_expect_status_code_ok() throws URISyntaxException {
        /*
         * Given Accept the response in JSON format
         * When I perform the GET request
         * Then Assert That the status code is OK
         */
        URI uri = new URI("https://jsonplaceholder.typicode.com/todos/1");
        given()
            .accept(ContentType.JSON)
        .when()
            .get(uri)
        .then()
            .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
}
