package com.tariq.isbnvalidatorapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IsbnControllerIT {
    @LocalServerPort
    private int port;

    @Test
    public void validateReturnsOkAndTrueWhenValidIsbn() throws Exception{
        given()
                .when()
                .get("http://localhost:" + port + "/api/isbn/validate/9185057819")
                .then()
                .body("valid", equalTo(true))
                .statusCode(200);
    }

    @Test
    public void validateReturnsOkAndFalseWhenInValidIsbn() throws Exception{
        given()
                .when()
                .get("http://localhost:" + port + "/api/isbn/validate/9111157819")
                .then()
                .body("valid", equalTo(false))
                .statusCode(200);
    }

    @Test
    public void validateReturnsBadRequestWhenInValidIsbnFormat() throws Exception{
        given()
                .when()
                .get("http://localhost:" + port + "/api/isbn/validate/9117819")
                .then()
                .statusCode(400);
    }
}
