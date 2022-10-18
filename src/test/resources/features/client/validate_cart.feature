Feature: Validate a cart

  Background:
    Given a user

  Scenario: his cart is not empty
    When  he validate his cart
    Then he can finalise his order

  Scenario: his cart is empty
    When  he validate his cart
    Then he can't finalise his order

