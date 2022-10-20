Feature: Manage the kitchen passage

  Scenario: see order details
    Given an available chef and a PENDING state order
    When  order state is PENDING
    Then the schedule associates the chef to the PENDING state order, which now is in the IN_PROGRESS state


  Scenario: give the prepared order
    Given an unavailable chef
    And his ready order
    When  he give the order
    Then he can clean, help or take a break