Feature: Retrieve order

  Background:
    Given a chef, a client, a client order, a store and the order list of a store including that of the client who is READY

  Scenario: retrieve a order of the right store
    When  a chef informs the system that he has given the order
    Then the order is removed from the customer's order list and current orders, added to its history and its status changes to RETRIEVE
