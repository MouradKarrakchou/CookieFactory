Feature: Choose a time slot

  Background:
    Given a user
    And an initialised cookie book
    And a valid cookie
    And a non-empty cart with 2 cookie
    And a store named "Antibes"
    And the store has no employ

  Scenario:the user ask for the list of Intervals
    Given an employee with disponibility only from 10 to 11
    When a user ask for 30 minute intervals possible
    Then he gets only intervals starting and finishing in the 10 to 11 time period with a 30 minute duration
    And he gets the right number of disponibility for a 1 hours disponibility with a 30 min interval

  Scenario:the user ask for the list of Intervals
    Given an employee with disponibility only from 14 to 17
    When a user ask for 40 minute intervals possible
    Then he gets only intervals starting and finishing in the 14 to 17 time period with a 45 minute duration
    And he gets the right number of disponibility for a 3 hours disponibility with a 40 min interval

  Scenario:the user ask for the list of Intervals
    Given an employee with disponibility only from 8 to 10
    Given an employee with disponibility only from 10 to 12
    When a user ask for 40 minute intervals possible
    Then he gets only intervals starting and finishing in the 8 to 12 time period with a 45 minute duration
    And he gets no intervals starting before 10 and finishing after

  Scenario:the user chooses a valid interval and validate his cart
    Given a valid interval
    When a user chooses an interval
    And he validate his cart
    Then the order is associated with the Time slots composing the interval are


  Scenario:the user chooses a valid interval and validate his cart
    Given a valid interval
    When a user chooses an interval
    Then the Time slots composing the interval are set to reserved