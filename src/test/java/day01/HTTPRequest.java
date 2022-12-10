package day01;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
/*
given()-content type, set cookies, add aouth, add param, set header info etc....
when()-get, post, put, delete
then()-validating status code, extract response, extract header and cookies and bodies
*/
public class HTTPRequest {
    int id;

    @Test(priority = 1)
    void getUsers(){
        given()
                .when().get("https://reqres.in/api/users?page=2")

                .then().statusCode(200)

                .body("page",equalTo(2))
                .log().all();
    }
    @Test(priority = 2)
    void createUSer(){
        HashMap data = new HashMap<>();
        data.put("name", "Bo");
        data.put("job","unemployed");


        id = given()
                .contentType("application/json")
                .body(data)

                .when().post("https://reqres.in/api/users")
                .jsonPath().getInt("id");

               /* .then().statusCode(201)
                .log().all();*/
    }
    @Test(priority = 3, dependsOnMethods = {"createUSer"})
    void updateUser(){

        HashMap data = new HashMap<>();
        data.put("name", "Ali");
        data.put("job","Delivery");

        given()
                .contentType("application/json")
                .body(data)

                .when().put("https://reqres.in/api/users/" + id)


                .then().statusCode(200)
                .log().all();
    }

    @Test(priority = 4)
    void  deleteUser(){
        given()

                .when().delete("https://reqres.in/api/users/" + id)

                .then().statusCode(204).log().all();
    }


}
