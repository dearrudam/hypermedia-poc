package com.github.dearrudam.boundary;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.net.URL;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest
public class RootResourcesTest {

    @TestHTTPResource("")
    URL url;

    @Test
    public void root() {
        given()
                .when().get("/api")
                .then()
                .statusCode(200)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                //.body( equalTo("{}"))
                .body(matchesJsonSchemaInClasspath("api.schema.json"))
                .body(not("{}"))
                .body("entities*.class", not(empty()))

        ;
    }

}