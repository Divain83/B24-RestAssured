package com.cybertek.tests.day04_ords_path;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSGetRequestsTest {
    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("ords.url");
    }

    /**
     * Given accept type is json
     * When user send get request to /ords/hr/regions
     * Status code should be 200
     * Content type should be "application/json"
     * And body should contain "Europe"
     */


    @DisplayName("GET Request to /ords/hr/regions") // make it more readable
    @Test
    public void getAllRegionsTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/regions");

        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());


        System.out.println("Content type = " + response.contentType());
        assertEquals("application/json", response.contentType());
        response.prettyPrint();

        assertTrue(response.asString().contains("Europe"));
    }


    @DisplayName("GET Request to /ords/hr/regions/1")
    @Test
    /**
     * Given accept type is json
     * And Path param value is 1
     * When user send get request to /ords/hr/regions/{region_id}
     * Status code should be 200
     * Content type should be "application/json"
     * And body should contain "Europe"
     */
    public  void getSingleRegionsWithParam(){

        int regionId = 1;

     Response response = given().accept(ContentType.JSON)
             .and().pathParam("id", 1)
             .when().get("/regions/{id}");
            // .when().get("/regions/1"); ----> we cna do it like that
        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println("Content type = " + response.contentType());
        assertEquals("application/json", response.contentType());
        response.prettyPrint();

        assertTrue(response.asString().contains("Europe"));
    }


    /**
     * Given accept type is json
     * And query param q="{"region_name": "Americas"}"
     * When user send get request to /ords/hr/regions
     * Status code should be 200
     * Content type should be "application/json"
     * And region name should be "Americas"
     * And region id should be "2"
     */

    @DisplayName("GET Request to /ords/hr/regions with query param")
    @Test
    public void getRegionWithQueryParam() {
    Response response = given().accept(ContentType.JSON)
            .and().queryParam("q","{\"region_name\": \"Americas\"}")
            .when().get("regions");

        System.out.println("status code = " + response.statusCode());
        assertEquals(200,response.statusCode());
        System.out.println("content type = "+ response.contentType());
        assertEquals("application/json", response.contentType());
        response.prettyPrint();
        assertTrue(response.asString().contains("America"));
        assertTrue(response.asString().contains("2"));

    }

}

