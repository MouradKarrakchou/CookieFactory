Feature: Create the bill

  Background:
    Given a user
    And an initialised cookie book
    And a store named "Antibes"
    And a valid cookie


  Scenario: create the bill
    Given a non-empty cart with 1 cookie

    When he validate his cart
    Then the bill is created

  Scenario: create the bill but his cart is empty
    Given an empty cart
    When he validate his cart
    Then he can't receive a bill