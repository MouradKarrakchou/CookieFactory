Feature: Manage the kitchen passage

  Scenario: see order details
    Given an available chef and a PENDING state order
    When  order state is PENDING
    Then the schedule associate the cook to the PENDING state order, which now is in the IN_PROGRESS state

  Scenario: give the prepared order
    Given an unavailable chef and a READY state order, and a possible other unavailable cook
    When  the order state is READY
    Then the cook associated to this command can clean, help or take a break

