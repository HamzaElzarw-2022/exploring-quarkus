package com.hamzaelzarw;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class CategoryServiceBookingTest {

    @Test
    void listCategories_andServices_seededViaImportSql() {
        given()
        .when().get("/categories")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(3))
            .body("name", hasItems("Cleaning", "Plumbing", "Electrical"));

        // Find services for category 1 (Cleaning)
        given()
        .when().get("/categories/1/services")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(2))
            .body("name", hasItems("Basic House Cleaning", "Deep Cleaning"));

        // List all services
        given()
        .when().get("/services")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(6));
    }

    @Test
    void createAndListBookings_requiresAuth() {
        // register + login to get token
        given()
            .contentType("application/json")
            .body("{\"username\":\"booker\",\"password\":\"secret\"}")
        .when()
            .post("/auth/register")
        .then()
            .statusCode(200);

        String jwt = given()
            .contentType("application/json")
            .body("{\"username\":\"booker\",\"password\":\"secret\"}")
        .when()
            .post("/auth/login")
        .then()
            .statusCode(200)
            .extract().path("token");

        // create booking for service 1
        String body = "{\"serviceId\":1,\"scheduledAt\":\"2030-01-01T10:00:00\",\"address\":\"123 Main St\",\"notes\":\"Ring bell\"}";
        given()
            .header("Authorization", "Bearer " + jwt)
            .contentType("application/json")
            .body(body)
        .when()
            .post("/bookings")
        .then()
            .statusCode(200)
            .body("serviceId", equalTo(1))
            .body("serviceName", not(isEmptyOrNullString()));

        // list my bookings
        given()
            .header("Authorization", "Bearer " + jwt)
        .when()
            .get("/bookings")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(1))
            .body("[0].address", equalTo("123 Main St"));
    }
}

