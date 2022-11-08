# Created by moura at 02/11/2022
Feature: Choose a time slot

  Background:
    Given a user
    And a valid cookie
    And a non-empty cart
    And the antibes store

  Scenario: the user choose a valid time slot and validate his cart
    Given a valid time slot
    When a user chooses a time slot
    And he validate the cart
    Then the order is associated with the time slot

  Scenario: the user reserve a time slot for his cart
    Given a valid time slot
    When a user chooses a time slot
    Then the order is reserverd