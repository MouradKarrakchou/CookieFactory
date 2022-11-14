Feature: Notify a client by sms

  Background:
    Given a mocked user
    And an order with the state "PENDING"

  Scenario: Notify the client by sms when the given time elapsed
    When the order change to "READY"
    Then a notification should not be sent
    When the client overdue the timer
    Then a notification should have be sent