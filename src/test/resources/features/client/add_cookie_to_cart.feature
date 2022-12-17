Feature: Add cookie to the cart

  Background:
    Given a user
    And a store named "Antibes"
    And an initialised cookie book of the store
    And a valid cookie

  Scenario: all the cookies are available
    When  he add 1 cookie to his cart
    Then there is 1 cookie in the cart

  Scenario: not enough ingredient
    When  he add 2 cookie to his cart
    Then there is 0 cookie in the cart

  Scenario: all ingredients are available
    Given the stock contain ingredients for "Cookie au chocolat"
    When  he requests the cookie list
    Then he can choose the cookie

  Scenario: all ingredients are available
    Given an store with an empty store
    When  he requests the cookie list
    Then he can't choose the cookie