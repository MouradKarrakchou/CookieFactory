Feature: See list of available cookies

  Background:
    Given a user2

  Scenario: all ingredients are available
    When  he requests the cookie list
    Then he receive the entire list

