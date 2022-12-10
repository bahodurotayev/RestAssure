package day03;


import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.Test;



import java.util.Map;

import static io.restassured.RestAssured.given;

public class Headers {
    @Test(priority = 1)
    void testHeaders(){
        given()
                .when()
                .get("https://www.google.com/")

                .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .header("Content-Encoding", "gzip")
                .header("Server", "gws");

    }
    @Test(priority = 2)
    void testHeaderInfo(){
        Response res = given()
                .when()
                .get("https://www.google.com/");

        //get single Header info
        /*String header_value =  res.getHeader("Content-Type");
        System.out.println("header_value = " + header_value);*/

        //get multiple headers info
        io.restassured.http.Headers headers_value = res.getHeaders();
        for (Header header :headers_value) {
            System.out.println(header.getName() + " : " + header.getValue());
        }
    }
}
