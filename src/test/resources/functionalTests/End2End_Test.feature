Feature: Student API testing

Scenario: Add Student record
  Given I Set POST student service api endpoint
  When I Set request HEADER
  And Send a POST HTTP request
  Then I recieve valid Response