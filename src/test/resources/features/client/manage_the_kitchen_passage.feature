Feature: Manage the kitchen passage

  Scenario: see order details
    Given an chef who is "AVAILABLE"
    And An order at the state "PENDING"
    When the chef is associate with an order
    Then the state of the order is "IN_PROGRESS"
