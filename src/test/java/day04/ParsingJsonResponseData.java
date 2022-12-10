package day04;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class ParsingJsonResponseData {
    @Test
    void testJsonResponse(){
        // 1-way
         given()
                 .contentType("ContentType.JSON")

                .when()
                 .get("http://localhost:3000/store")

                .then()
                 .statusCode(200)
                 .header("Content-Type","application/json; charset=utf-8")
                 .body("book[2].title", equalTo("The lord of the ring"));
    }
    @Test
    void test2JsonResponse(){
        // 2-way

        Response res = given()
                .contentType("ContentType.JSON")

                .when()
                .get("http://localhost:3000/store");
        //Using testNG validating response
        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
        Assert.assertEquals(res.jsonPath().get("book[2].title").toString(),("The lord of the ring"));
    }
    @Test
    void test3JsonResponse(){
        // 3-way

        Response res = given()
                .contentType("ContentType.JSON")

                .when()
                .get("http://localhost:3000/store");
        //JSON object class

        JSONObject jsonObject = new JSONObject(res.toString());
        for (int i = 0; i < jsonObject.getJSONArray("book").length(); i++) {
          //  String bookTitle = jsonObject.getJSONArray("book").getJSONArray(i).get("title").toString();
        }
    }
}
