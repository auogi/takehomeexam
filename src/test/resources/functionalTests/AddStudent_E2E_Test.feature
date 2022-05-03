Feature: POST Student API testing

  Scenario: Add Student record with Complete Data
    Given I initiate Student Service endpoint
    When I set firstName as "test"
    And I set lastName as "test"
    And I set classRoom as "1C"
    And I set nationality is "American"
    And Send a POST HTTP request
    Then I recieve valid Response

  Scenario: Add Student record with no firstName
    Given I initiate Student Service endpoint
    And I set lastName as "test"
    And I set classRoom as "1C"
    And I set nationality is "American"
    And Send a POST HTTP request
    Then I recieve error response "first_name Cannot be blank"

  Scenario: Add Student record with no lastName
    Given I initiate Student Service endpoint
    And I set firstName as "test"
    And I set classRoom as "1C"
    And I set nationality is "American"
    And Send a POST HTTP request
    Then I recieve error response "last_name Cannot be blank"

  Scenario: Add Student record with no classRoom
    Given I initiate Student Service endpoint
    And I set firstName as "test"
    And I set lastName as "test"
    And I set nationality is "American"
    And Send a POST HTTP request
    Then I recieve error response "class Cannot be blank"

  Scenario: Add Student record with no nationatlity
    Given I initiate Student Service endpoint
    And I set firstName as "test"
    And I set lastName as "test"
    And I set classRoom as "1C"
    And Send a POST HTTP request
    Then I recieve error response "nationataliy Cannot be blank"