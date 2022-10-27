Feature: Remove one or more cookies from the cart

  Background:
    Given a user
    And a non-empty cart

  Scenario: remove one cookie from a cart
    When  he remove a cookie from his cart
    Then his cart has one item less

  Scenario: remove two cookies from a cart
    When  he remove two cookies from his cart
    Then his cart has two items less

