Feature: Add cookie to the cart

  Background:
    Given a user

  Scenario: all the cookies are available
    When  he add cookie to his cart
    Then a cookie is added to his cart

