import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTest {

      @Test
    public void testGetUsers() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("size()", greaterThan(0));
    }

    @Test
    public void testCreateUser() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        String requestBody = "{ \"name\": \"John Doe\", \"email\": \"john.doe@example.com\" }";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("John Doe"))
                .body("email", equalTo("john.doe@example.com"));
    }

    @Test
    public void testUpdateUser() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        String requestBody = "{ \"name\": \"Jane Doe\" }";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put("/users/1")
                .then()
                .statusCode(200)
                .body("name", equalTo("Jane Doe"));
    }

    @Test
    public void testDeleteUser() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .when()
                .delete("/users/1")
                .then()
                .statusCode(200);
    }
}