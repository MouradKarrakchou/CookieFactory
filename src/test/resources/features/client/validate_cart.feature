Feature: Validate a cart


  Scenario: his cart is not empty
    Given a user with a non-empty cart
    When  he validate his cart
    Then he can finalise his order

  Scenario: his cart is empty
    Given a user with a empty cart
    When  he validate his cart
    Then he can't finalise his order

