Feature: Remove one cookies from the cart

  Background:
    Given a user2
    And a non-empty cart

  Scenario: remove one cookie from a cart
    When  he remove a cookie from his cart
    Then his cart has one item less


