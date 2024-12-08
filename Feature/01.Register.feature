
Feature: Validate Register Functionality

  @successfulRegistration
  Scenario: Successful registration
    Given the user registration API endpoint is "/api/v1/users/register"
    When I send a POST request with valid user details
    Then the response status code should be 201
    And the response should contain the user ID

  @registerWithoutEmailId
  Scenario: Registration with missing fields
    Given the user registration API endpoint is "/api/v1/users/register"
    When I send a POST request with missing email field
    Then the response status code should be 422
    And the response should contain an error message "Received data is not valid"

  @registerWithExistingEmailId
  Scenario: Registration with an already registered email
    Given the user registration API endpoint is "/api/v1/users/register"
    When I send a POST request with an already registered email
    Then the response status code should be 409
    And the response should contain an error message "User with email or username already exists"