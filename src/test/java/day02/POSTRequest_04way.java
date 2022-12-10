package day02;


import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class POSTRequest_04way {
    /* Ways of creating post request
    1.using HashMap
    2.using org.json library
    3.using POJO(Plain old Java object)
    4.using external json.file
    */
    @Test(priority = 1)
    void testUsingJson() throws FileNotFoundException {

        File file = new File("body.json");
        FileReader fileReader = new FileReader(file);
        JSONTokener jsonTokener = new JSONTokener(fileReader);
        JSONObject data = new JSONObject(jsonTokener);

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
