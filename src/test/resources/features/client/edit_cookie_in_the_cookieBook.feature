Feature: edit cookie in the cookie book

  Background:
    Given a cookie
    Given a storeLocattion
    And brandManager
    And a cookieBook

  Scenario: add a cookie to the cookie book
    When when a brandManager add a cookie to the cookie book
    Then the cookkie is add to the cookie book

  Scenario: remove a cookie to the cookie book
    When when a brandManager remove a cookie to the cookie book
    Then the cookkie is remove to the cookie book