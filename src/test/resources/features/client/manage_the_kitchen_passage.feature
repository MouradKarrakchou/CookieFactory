Feature: Manage the kitchen passage

  Scenario: see order details
    Given an chef who is "AVAILABLE"
    And An order at the state "PENDING"
    When the chef is associate with an order
    Then the state of the order is "IN_PROGRESS"


  Scenario: give the prepared order
    Given an chef who is "UNAVAILABLE"
    And An order at the state "READY"
    When  he give the order
    Then he can clean, help or take a break
