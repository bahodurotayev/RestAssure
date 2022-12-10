package day03;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Logging {
    @Test
    void testLog(){
        given()
                .when().get("https://reqres.in/api/users?page=2")
                .then()
                .log().all();
        /*.body()
          .cookies()
          .headers()
          .statusCode().
        */

    }
}
