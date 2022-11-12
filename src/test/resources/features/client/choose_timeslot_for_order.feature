# Created by moura at 02/11/2022
Feature: Choose a time slot

  Background:
    Given a user
    And a valid cookie
    And a non-empty cart with 2 cookie
    And a store named "Antibes"

  Scenario:the user choose a valid interval and validate his cart
    Given a valid interval
    When a user chooses an interval
    And he validate his cart
    Then the order is associated with the Time slots composing the interval are


  Scenario:the user choose a valid interval and validate his cart
    Given a valid interval
    When a user chooses an interval
    Then the Time slots composing the interval are set to reserved
