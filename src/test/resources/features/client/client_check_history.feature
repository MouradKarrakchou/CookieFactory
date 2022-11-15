Feature: A client can check his last orders

  Background:
    Given a user
    And past orders

  Scenario:
    When a client ask for his history
    Then he gets all his past orders


