#faite en double a supprimer en fonction si tout le monde est d'accord, ---> cf manage the kitchen
Feature: Retrieve order

  Scenario: a client retrieve his order with a bill
    Given A store
    And A order at the state "READY"
    When client retrieve his order
    Then the state of the order is "RETRIEVE"