Feature: edit cookie in the cookie book

  Background:
    Given an initialised cookie book
    And a cookie
    And a cookieBook

    #Le BrandManager n'existe plus !
  Scenario: add a cookie to the cookie book
    When when a brandManager add a cookie to the cookie book
    Then the cookkie is add to the cookie book

  Scenario: remove a cookie to the cookie book
    When when a brandManager remove a cookie to the cookie book
    Then the cookkie is remove to the cookie book