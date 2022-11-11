Feature: Receive a discount after ordering 30 cookies

  Background:
    Given a user
    And a store named "Antibes"
    And a valid cookie
    And a non-empty cart with 30 cookie


  Scenario: client has a fidelity account
    Given a fidelity account
    When he validate his cart
    Then he receive a discount for his next order

