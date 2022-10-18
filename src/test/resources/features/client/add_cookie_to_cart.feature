Feature: Add cookie to the cart

  Background:
    Given a user his cart and a valid cookie

  Scenario: all the cookies are available
    When  he add cookie to his cart
    Then a cookie is added to his cart

