package com.cybertek.tests.day02_headers_params;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import io.restassured.response.Response;



public class SpartanGetRequestsApiTest {


    String baseUrl = ConfigurationReader.getProperty("spartan.url");


    /**
     * When I send a GET request to
     * spartan_base_url/api/spartans
     * Then Response STATUS OE must be 200
     * And I should see all Spartans data in JSON format
     */

    @Test
    public void getAllSpartanTest(){
        Response response = get(baseUrl + "/api/spartans");
        System.out.println("status code=" + response.statusCode());
        assertEquals(200,response.statusCode());

        response.prettyPrint();
        assertTrue(response.asString().contains("Correy"));

}
    /**
     * Given Accept type is "application/json"
     * When I send a GET request to
     * spartan_base_url/api/spartans
     * Then Response STATUS CODE must be 200
     * And content type should be "application/json"
     */

    @Test
    public void allSpartansHeadersTest() {

        Response response = given().accept(ContentType.JSON).  //add header to request
                            when().get(baseUrl + "/api/spartans");                    // means,we are adding request header accept="appliocation/json"

        System.out.println("status code=" + response.statusCode());
        assertEquals(200,response.statusCode());
        System.out.println(response.contentType()); //read content type response header

        assertEquals("application/json",response.contentType());

        //print more response headers
        System.out.println("date header value  = " + response.getHeader("Date"));

        System.out.println("transfer ensoding  = " +response.getHeader("Transfer-Encoding"));
        //verify header "Date" response
        assertTrue(response.getHeaders().hasHeaderWithName("Date"));
    }


}
