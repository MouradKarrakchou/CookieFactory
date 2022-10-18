Feature: Manage the kitchen passage

  Background:
    Given a cook

  Scenario: see order details
    When  order state is PENDING
    Then the schedule associate the cook to the PENDING state order

  Scenario: give the prepared order
    When  the order state is READY
    Then the cook associated to this comand can clean, help or take a break

