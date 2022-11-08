Feature: Add cookie to the cart

  Background:
    Given a user
    And a valid cookie
    And a store named "Antibes"
    And a non-empty cart


  Scenario: all the cookies are available
    When  he add cookie to his cart
    Then a cookie is added to his cart


  Scenario: all ingredients are available
    When  he requests the cookie list
    Then he receive the entire list
