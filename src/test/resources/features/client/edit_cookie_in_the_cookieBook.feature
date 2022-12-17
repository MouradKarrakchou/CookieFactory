Feature: edit cookie in the cookie book

  Background:
    Given a user
    And a store named "Antibes"
    And an initialised cookie book of the store
    And a valid cookie




  Scenario: add a cookie to the cookie book
    When when a brandManager add a cookie to the cookie book
    Then the cookie is add to the cookie book

  Scenario: add a cookie to the cookie book
    When when a brandManager add a cookie that is already in the cookie book
    Then the cookie book didn't changed

  Scenario: remove a cookie to the cookie book
    When when a brandManager remove a cookie to the cookie book
    Then the cookie is remove to the cookie book