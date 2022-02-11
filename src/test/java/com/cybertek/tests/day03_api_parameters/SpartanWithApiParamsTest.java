package com.cybertek.tests.day03_api_parameters;

import com.cybertek.utilities.ConfigurationReader;
import com.sun.org.apache.xpath.internal.operations.And;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanWithApiParamsTest {

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("spartan.url");
    }
    /*
    Given Accept type is json
And path parameter id is 24
When I send request to /api/spartans/24
Then status code should be 200
And content type should be "application/json"
and json body should contain "Correy"
     */

    @Test
    public void getSpartanPathParamTest() {

        Response response = given().accept(ContentType.JSON).when().get("/api/spartans/25");
        System.out.println("response = " + response.getStatusCode());
        assertEquals(200, response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());
        assertEquals("application/json", response.contentType());
    }


    /**Given Accept type is json
    And path parameter id is 1000
    When I send request to /api/spartans/1000
     -------
    Then status code should be 404
    And content type should be "application/json"
    and json body should contain "Not Found"
     */
    @Test
    public void getSpartanPathParamNegativeTest() {

        Response response = given().accept(ContentType.JSON)
                . and().pathParam("id",1000)
                .when().get("/api/spartans/{id}");

        System.out.println("status line = " + response.statusLine());
        assertEquals(404,response.statusCode());

        System.out.println("content type = " + response.contentType());
        assertEquals("application/json", response.contentType());

        response.prettyPrint();
        assertTrue(response.asString().contains("Not Found"));




    }


}
