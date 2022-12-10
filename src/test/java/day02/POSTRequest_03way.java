package day02;

import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class POSTRequest_03way {
    /* Ways of creating post request
    1.using HashMap
    2.using org.json library
    3.using POJO(Plain old Java object)
    4.
    */
    @Test(priority = 1)
    void testUsingJson() {

        Pojo_PostRequest data = new Pojo_PostRequest();
        data.setName("Bahodur");
        data.setLocation("France");
        data.setPhone("2313536");

        String[] coursesArray = {"C","C++"};

        data.setCourses(coursesArray);

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

    class Pojo_PostRequest{
        String name;
        String location;
        String phone;
        String[] courses;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String[] getCourses() {
            return courses;
        }

        public void setCourses(String[] courses) {
            this.courses = courses;
        }
    }

}
