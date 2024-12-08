Feature: User Login API
  As a user
  I want to log in to the system
  So that I can access the application securely

  @loginValidCredentials
  Scenario: Successful login
    Given the login API endpoint is "/api/v1/users/login"
    When I send a POST request with valid login credentials
    Then the login response status code should be 200
    And the response should contain a token

  @loginIncorrectPassword
  Scenario: Login with incorrect password
    Given the login API endpoint is "/api/v1/users/login"
    When I send a POST request with incorrect password
    Then the login response status code should be 401
    And the response should contain an login error message "Invalid user credentials"

  @loginNonExistentUser
  Scenario: Login with non-existent username
    Given the login API endpoint is "/api/v1/users/login"
    When I send a POST request with a non-existent username
    Then the login response status code should be 404
    And the response should contain an login error message "User does not exist"

  @loginMissingPassword
  Scenario: Login with missing password field
    Given the login API endpoint is "/api/v1/users/login"
    When I send a login POST request with missing password field
    Then the login response status code should be 422
    And the response should contain an login error message "Received data is not valid"
