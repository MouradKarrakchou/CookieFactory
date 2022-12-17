Feature: Notify client for a surprise basket

  Background:
    Given a user

  Scenario: notify client for a surprise basket
    Given the user as subscribe to a fidelity account
    When a new surprise basket is available
    Then the client in notified