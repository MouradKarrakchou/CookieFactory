Feature: Add cookie to the cart

  Background:
    Given a user

  Scenario: all the cookies are available
    When  he add cookie to his cart
    Then he can watch the content of his cart

