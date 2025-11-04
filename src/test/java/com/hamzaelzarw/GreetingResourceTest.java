package com.hamzaelzarw;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
class GreetingResourceTest {
    @Test
    void publicHello_works() {
        given()
          .when().get("/hello/public")
          .then()
             .statusCode(200)
             .body(is("Hello, anonymous user!"));
    }

    @Test
    void secureHello_requiresJwt_andWorksWithJwt() {
        // Without token -> 401
        given()
            .when().get("/hello/secure")
            .then()
            .statusCode(401);

        // With token -> 200 and contains username
        String token = given()
                .contentType("application/json")
                .body("{\"username\":\"u2\",\"password\":\"p2\"}")
            .when()
                .post("/auth/register")
            .then()
                .statusCode(200)
                .extract().asString();
        // login to get jwt
        String jwt = given()
                .contentType("application/json")
                .body("{\"username\":\"u2\",\"password\":\"p2\"}")
            .when()
                .post("/auth/login")
            .then()
                .statusCode(200)
                .extract().path("token");

        given()
            .header("Authorization", "Bearer " + jwt)
        .when()
            .get("/hello/secure")
        .then()
            .statusCode(200)
            .body(containsString("Hello, authenticated user! u2"));
    }

}