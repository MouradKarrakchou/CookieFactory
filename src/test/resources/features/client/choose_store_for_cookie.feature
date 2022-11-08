
Feature: Choose store for cookie

  Background:
    Given a user
    And a valid cookie
    And a non-empty cart

  Scenario: we choose a valid store
    When we choose a valid store
    Then the right store is selected in the cart

  Scenario: we choose an invalid store
    When we choose an invalid store
    Then an InvalidStoreException is triggered