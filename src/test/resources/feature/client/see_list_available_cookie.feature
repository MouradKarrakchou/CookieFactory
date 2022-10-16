Feature: See list of available cookies

  Background:
    Given a client

  Scenario: all ingredients are available
    When  requests the cookie list
    Then he receive the entire list

