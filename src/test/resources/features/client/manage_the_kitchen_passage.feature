Feature: Manage the kitchen passage

  Scenario: the order change state because stat of the chef is available
    Given a chef who is "AVAILABLE"
    And An order at the state "PENDING"
    When the chef is associate with an order
    Then the state of the order is "IN_PROGRESS"

  Scenario: the order doesn't change state because the chef is unavailable
    Given a chef who is "UNAVAILABLE"
    And An order at the state "PENDING"
    When the chef is associate with an order
    Then the state of the order is "PENDING"

  Scenario: the order doesn't change state because already in progress
    Given a chef who is "AVAILABLE"
    And An order at the state "IN_PROGRESS"
    When the chef is associate with an order
    Then the state of the order is "IN_PROGRESS"