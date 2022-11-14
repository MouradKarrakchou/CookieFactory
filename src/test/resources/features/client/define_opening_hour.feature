Feature: Define opening hour

  Background:
    Given a user
    And a valid cookie
    And a non-empty cart with 2 cookie
    And a store named "Antibes"
    And the store has no employ

  Scenario:
    Given a manager
    When the manager changes the opening time of the store from 10 to 16
    Then the schedule of the employees start from 10 to 16


