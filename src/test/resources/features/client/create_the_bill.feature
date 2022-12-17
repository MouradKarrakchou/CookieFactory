Feature: Create the bill

  Background:
    Given a user
    And an initialised cookie book
    And a store named "Antibes"
    And a valid cookie
    And a non-empty cart with 1 cookie


  Scenario: create the bill
    When he validate his cart
    Then the bill is created

