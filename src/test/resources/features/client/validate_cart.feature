Feature: Validate a cart


  Scenario: his cart is not empty
    Given a user with a non-empty cart
    When  he validate his cart
    Then his order is created



