Feature: Starting an order as a chef
  
  Background:
    Given an chef who is "AVAILABLE"
    And An order at the state "PENDING"
    
  Scenario:
    When the chef is associate with an order
    And the chef is asking for work
    Then the state of the chef should be "UNAVAILABLE"
    And the state of the order is "IN_PROGRESS"

    When the chef terminate his current order
    Then the state of the order is "READY"