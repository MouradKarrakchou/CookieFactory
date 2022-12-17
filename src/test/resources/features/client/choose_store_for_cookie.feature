Feature: Choose store for cookie

  Background:
    Given a user
    And an initialised cookie book
    And a valid cookie
    And a non-empty cart with 2 cookie

  Scenario: we choose a valid store
    When we choose a valid store
    Then the right store is selected in the cart

  Scenario: we choose an invalid store
    When we choose an invalid store
    Then an InvalidStoreException is triggered