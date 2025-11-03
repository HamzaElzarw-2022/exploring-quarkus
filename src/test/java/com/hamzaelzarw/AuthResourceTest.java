package com.hamzaelzarw;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
class AuthResourceTest {

    @Test
    void registerThenLogin_returnsJwt() {
        // register
        given()
            .contentType("application/json")
            .body("{\"username\":\"u1\",\"password\":\"p1\"}")
        .when()
            .post("/auth/register")
        .then()
            .statusCode(200)
            .body(notNullValue());

        // login
        given()
            .contentType("application/json")
            .body("{\"username\":\"u1\",\"password\":\"p1\"}")
        .when()
            .post("/auth/login")
        .then()
            .statusCode(200)
            // JWT should contain two dots separating header.payload.signature
            .body(containsString("."));
    }
}

