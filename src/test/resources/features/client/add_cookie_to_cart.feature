Feature: Add cookie to the cart

  Background:
    Given a user
    And a store named "Antibes"
    And an initialised cookie book of the store
    And a valid cookie
    And a non-empty cart with 2 cookie


  Scenario: all the cookies are available
    When  he add cookie to his cart
    Then a cookie is added to his cart


  Scenario: all ingredients are available
    Given the stock contain ingredients for "Cookie au chocolat"
    When  he requests the cookie list
    Then he receive the entire list
