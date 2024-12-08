package com.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import utils.JsonFile_RW;

import java.util.Random;

@SuppressWarnings(value = "unchecked")
public class Registration {

    JSONObject object;
    static RequestSpecification request;
    static Response response;
    static JsonFile_RW appData;
    String filePath = "/AppData.json";

    @Given("the user registration API endpoint is {string}")
    public void theUserRegistrationAPIEndpointIs(String pathUrl) {

        try {

            appData = new JsonFile_RW(filePath);

            RestAssured.baseURI = "https://api.freeapi.app";

            RestAssured.basePath = pathUrl;

            request = RestAssured.given()
                    .header("content-type", "application/json").when();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @When("I send a POST request with valid user details")
    public void iSendAPOSTRequestWithValidUserDetails() {

        try {
            object = new JSONObject();

            Random random = new Random();

            int id = random.nextInt(100, 9999999);
            String emailId = "demo"+id+"@gmail.com";
            String userName = "username"+id;

            object.put("email", emailId);
            object.put("password", "Qwerty@123");
            object.put("role", "ADMIN");
            object.put("username", userName);

            appData.jsonData.put("emailId", emailId);
            appData.jsonData.put("userName", userName);

            request.body(object.toJSONString());

            response = request.post().then().extract().response();



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(Integer int1) {

        try {
            if(response.statusCode() != int1){

                System.out.println(response.statusCode() + " Success Message => " + response.jsonPath().getString("message"));
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    @Then("the response should contain the user ID")
    public void theResponseShouldContainTheUserID() {

        try {
            if (response.jsonPath().getString("data.user._id") != null){

                String userId = response.jsonPath().getString("data.user._id");

                appData.jsonData.put("userId", userId);

                appData.jsonPayloadWriter();

                System.out.println("Registration successful user id =>" + userId);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    @registerWithoutEmailId

    @When("I send a POST request with missing email field")
    public void iSendAPOSTRequestWithMissingField() {

        try {

            object = new JSONObject();

            Random random = new Random();

            int id = random.nextInt(100, 9999999);

            object.put("email", "");
            object.put("password", "Qwerty@123");
            object.put("role", "ADMIN");
            object.put("username", "username"+id);

            request.body(object.toJSONString());

            response = request.post().then().extract().response();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    @Then("the response should contain an error message {string}")
    public void theResponseShouldContainAnErrorMessage(String message) {

        try {

            String errorOne = response.jsonPath().getString("errors[0].email");
            String errorTwo = response.jsonPath().getString("errors[1].email");
            String message1 = response.jsonPath().getString("message");

            if (errorOne != null || errorTwo != null){
                System.out.println("error one => " + errorOne);
                System.out.println("error two => " + errorTwo);

            }

            if (message1 != null && message1.equals(message)){
                System.out.println("error message => " + message1);
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

//    registerWithExistingEmailId

    @When("I send a POST request with an already registered email")
    public void iSendAPOSTRequestWithAnAlreadyRegisteredEmail() {

        try {

            object = new JSONObject();

            Random random = new Random();

            int id = random.nextInt(100, 9999999);

            object.put("email", "demo1@gmail.com");
            object.put("password", "Qwerty@123");
            object.put("role", "ADMIN");
            object.put("username", "username"+id);

            request.body(object.toJSONString());

            response = request.post().then().extract().response();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }

}
