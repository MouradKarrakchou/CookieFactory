Feature: Manage the kitchen passage

  Scenario: see order details
    Given an chef who is "AVAILABLE"
    And An order at the state "PENDING"
    When  order state is PENDING
    Then the schedule associates the chef to the PENDING state order, which now is in the IN_PROGRESS state


  Scenario: give the prepared order
    Given an chef who is "UNAVAILABLE"
    And his ready order
    When  he give the order
    Then he can clean, help or take a break
