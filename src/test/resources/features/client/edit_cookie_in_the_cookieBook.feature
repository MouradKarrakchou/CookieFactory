Feature: edit cookie in the cookie book

  Background:
    Given a user
    And a store named "Antibes"
    And an initialised cookie book of the store
    And a valid cookie



    #Le BrandManager n'existe plus !
  Scenario: add a cookie to the cookie book
    When when a brandManager add a cookie to the cookie book
    Then the cookkie is add to the cookie book

  Scenario: remove a cookie to the cookie book
    When when a brandManager remove a cookie to the cookie book
    Then the cookkie is remove to the cookie book