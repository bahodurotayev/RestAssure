package day02;

import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class POSTRequest_02way {
    /* Ways of creating post request
    1.using HashMap
    2.using org.json library
    3.
    4.
    */
    @Test(priority = 1)
    void testUsingJson() {

        JSONObject data = new JSONObject();
        data.put("name","Bahodur");
        data.put("location", "France");
        data.put("phone", "2313536");

        String[] coursesArray = {"C", "C++"};
        data.put("courses", coursesArray);

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")


                .then()
                .statusCode(201)
                .body("name", equalTo("Bahodur"))
                .body("location", equalTo("France"))
                .body("phone", equalTo("2313536"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
    }
    @Test(priority = 2)
    void testDelete(){
        given()
                .when()
                .delete("http://localhost:3000/students/4")

                .then()
                .statusCode(200);

    }

}
