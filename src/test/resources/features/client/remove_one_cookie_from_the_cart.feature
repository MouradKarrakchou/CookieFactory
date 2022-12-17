Feature: Remove one cookies from the cart

  Background:
    Given a user
    And an initialised cookie book
    And a valid cookie
    And a non-empty cart with 2 cookie


  Scenario: remove one cookie from a cart
    When  he remove a cookie from his cart
    Then his cart has one item less


