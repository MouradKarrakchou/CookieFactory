Feature: Add cookie to the cart

  Background:
    Given a user
    And a valid cookie
    And a non-empty cart


  Scenario: all the cookies are available
    When  he add cookie to his cart
    Then a cookie is added to his cart

