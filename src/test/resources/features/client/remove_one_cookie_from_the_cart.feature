Feature: Remove one cookies from the cart

  Background:
    Given a user
    And a store named "Antibes"
    And an initialised cookie book
    And a valid cookie
    And a non-empty cart with 2 cookie


  Scenario: remove 1 cookie from a cart
    When  he remove 0 cookie from his cart
    Then his cart has 2 item less

  Scenario: remove 10 cookie from a cart
    When  he remove 10 cookie from his cart
    Then his cart has 0 item less

  Scenario: remove 10 cookie from a cart
    When  he remove 10 cookie from his cart
    Then his cart has 0 item less