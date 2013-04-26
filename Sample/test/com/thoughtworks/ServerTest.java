package com.thoughtworks;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class ServerTest {
    @Test
    public void proofForSupportPost() throws Exception {
        given().
                param("bicycle.wheelNum", "4").
                param("bicycle.wheel.radius", "30").
                expect().
                body(containsString("I can run!!!!")).
                body(containsString("Because I have 4 wheel")).
                body(containsString("And every wheel's radius is 30")).
                when().
                post("/Bicycle/canYouRun");
    }
}
