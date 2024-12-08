package com.steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import utils.JsonFile_RW;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings(value = "unchecked")
public class Login {

    static RequestSpecification request;
    static Response response;
    static JsonFile_RW appData;
    String filePath = "/AppData.json";

    @Given("the login API endpoint is {string}")
    public void theLoginAPIEndpointIs(String url) {

        appData = new JsonFile_RW(filePath);

        RestAssured.baseURI = "https://api.freeapi.app";
        RestAssured.basePath = url;

        request = RestAssured.given()
                .header("content-type", "application/json").when();
    }

    @When("I send a POST request with valid login credentials")
    public void iSendAPOSTRequestWithValidLoginCredentials() {

        JSONObject loginDetails = new JSONObject();
        loginDetails.put("username", appData.getJsonDataAsMap("userName"));
        loginDetails.put("password", "Qwerty@123");

        response = request.body(loginDetails).post().then().extract().response();

    }

    @Then("the login response status code should be {int}")
    public void theLoginResponseStatusCodeShouldBe(Integer int1) {

        try {
            if(response.statusCode() != int1){

                System.out.println(response.statusCode() + " Success Message => " + response.jsonPath().getString("message"));
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    @Then("the response should contain a token")
    public void theResponseShouldContainAToken() {

        String token = response.jsonPath().getString("data.accessToken");

        if (token != null){
            System.out.println("Login user token => " + token);
        }
        System.out.println("Message => " + response.jsonPath().getString("message"));
    }

//    @loginIncorrectPassword

    @When("I send a POST request with incorrect password")
    public void iSendAPOSTRequestWithIncorrectPassword() {

        JSONObject loginDetails = new JSONObject();
        loginDetails.put("username", appData.getJsonDataAsMap("userName"));
        loginDetails.put("password", "Qwert@123");

        response = request.body(loginDetails).post().then().extract().response();

    }

    @Then("the response should contain an login error message {string}")
    public void theResponseShouldContainAnLoginErrorMessage(String message1) {

        String message = response.jsonPath().getString("message");

        if (message != null && message.equals(message1) ){

            System.out.println("Final response message => " + response.jsonPath().getString("message"));
        }

        String error = response.jsonPath().getString("errors[0].password");

        if (error != null){
            System.out.println("error message => " + error);
        }
    }

//    @loginNonExistentUser

    @When("I send a POST request with a non-existent username")
    public void iSendAPOSTRequestWithANonExistentUsername() {
        JSONObject loginDetails = new JSONObject();
        loginDetails.put("username", "123@gmail.com");
        loginDetails.put("password", "Qwerty@123");

        response = request.body(loginDetails).post().then().extract().response();

    }

//    @loginMissingPassword

    @When("I send a login POST request with missing password field")
    public void iSendALoginPOSTRequestWithMissingPasswordField() {

        JSONObject loginDetails = new JSONObject();
        loginDetails.put("username", appData.getJsonDataAsMap("userName"));
        loginDetails.put("password", "");

        response = request.body(loginDetails).post().then().extract().response();

    }

}

