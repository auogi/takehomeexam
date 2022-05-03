Feature: PUT Student API testing

  Scenario: Update a Student that exist
    Given I initiate Student Service endpoint
    When I set id as "1"
    And I set firstName as "test"
    And I set lastName as "test"
    And I set classRoom as "1C"
    And I set nationality is "American"
    And Send a POST HTTP request
    Then I recieve valid Response

  Scenario: Update a Student that exist
    Given I initiate Student Service endpoint
    And I set lastName as "test"
    And I set classRoom as "1C"
    And I set nationality is "American"
    And Send a POST HTTP request
    Then I recieve error response "first_name Cannot be blank"