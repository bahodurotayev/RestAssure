package day03;


import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Cookies {
    @Test(priority = 1)
    void testCookies(){
        given()
                .when()
                .get("https://www.google.com/")

                .then()
                .cookie("AEC","AakniGPBBxWY63K5w-6_w4k3CvGfqDUpEAjEnyt4csdZD7OESozC6Fr4ww")
                .log().all();
    }
    @Test(priority = 2)
    void testCookiesInfo(){
        Response res = given()
                .when()
                .get("https://www.google.com/");

        //get single cookie_info
        /*String cookie_value = res.getCookie("AEC");
        System.out.println("cookie_value = " + cookie_value);*/

        //get multiple cookies_info
        Map<String,String> cookies_value = res.getCookies();
        for (Map.Entry<String, String> eachCookie : cookies_value.entrySet()) {
            System.out.println("Key : "+ eachCookie.getKey() + " : " + "Value : " + eachCookie.getValue());
        }

    }
}
