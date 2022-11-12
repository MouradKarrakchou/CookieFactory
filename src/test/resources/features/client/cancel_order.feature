Feature: Cancel order

  Scenario: the user wants to cancel an order at the state "PENDING"
    When the user try to cancel his order at the state "PENDING"
    Then the order is canceled
    And the user is notified

  Scenario: the user wants to cancel an order at the state "IN_PROGRESS"
    When the user try to cancel his order at the state "IN_PROGRESS"
    Then the order cannot be canceled
    And the user is notified