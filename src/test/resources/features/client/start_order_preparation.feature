Feature: Start order preparation

  Background:
    Given an chef who is "AVAILABLE"
    And An order at the state "PENDING"

  Scenario: chef as an order to prepare

    When the chef is associate with an order
    Then the state of the order is "IN_PROGRESS"
