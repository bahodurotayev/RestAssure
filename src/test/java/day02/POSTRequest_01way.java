package day02;

import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class POSTRequest_01way {
    /* Ways of creating post request
    1.using HashMap
    2.
    3.
    4.
    */
    @Test(priority = 1)
    void testPostUsingHashMap() {
        HashMap data = new HashMap();
        data.put("name", "Bahodur");
        data.put("location", "France");
        data.put("phone", "2313536");

        String[] courseArray = {"C", "C++"};
        data.put("courses", courseArray);

        given()
                .contentType("application/json")
                .body(data)

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
