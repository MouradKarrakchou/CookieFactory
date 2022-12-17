Feature: A client can check his last orders

  Background:
    Given a user
    And past orders

  Scenario: The client has a FidelityAccount
    Given a user with a fidelityAccount
    When a client ask for his history
    Then he gets all his past orders

  Scenario:
    When a client got a fidelity account
    Then he don't have a past order

  Scenario: The client doesn't have a FidelityAccount
    Given a user with no fidelityAccount
    When a client ask for his history
    Then he can't get an history