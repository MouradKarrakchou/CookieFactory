Feature Create the bill

  Background:
    Given a user
    And An order at the state "PENDING"


  Scenario: create the bill
    When the user validate his order
    Then the bill is created