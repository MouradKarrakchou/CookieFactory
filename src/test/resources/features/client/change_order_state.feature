Feature: Change the state of the order after a given time

  Background:
    Given a mocked user
    And an order with the state "PENDING"

  Scenario:
    When the order change to "READY"
    Then the order should be "READY"
    When the client overdue the timer
    Then the order should be "OBSOLETE"
