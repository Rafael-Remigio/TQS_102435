package pt.ua.tqs;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItems;


public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    void RestAPICode()
    {
            String url = "https://jsonplaceholder.typicode.com/todos";

            given().
            when().
            get(url).
            then().
            statusCode(200);

    }

    @Test
    void testEchoService() {
        given().
        when().get("https://jsonplaceholder.typicode.com/todos")
                .then().body("[3].title", equalTo("et porro tempora"));
    }


    @Test
    void testHasItems() {
        given().
        when().get("https://jsonplaceholder.typicode.com/todos")
                .then().body("", hasItems(
                    allOf(
                        hasEntry("id", "198"),
                        hasEntry("id", "199")
                    )
              ));
    }


}
