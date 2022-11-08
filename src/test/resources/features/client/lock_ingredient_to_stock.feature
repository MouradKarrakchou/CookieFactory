Feature: Lock an ingredient to the stock

  Background:
    Given an empty stock
    And 500 grams of "chocolate"

  Scenario: Add an ingredient to an empty stock
    When 1000 grams of "flour" is added to the stock
    Then the stock has 1000 grams of "flour"

  Scenario: Lock a given quantity of the previously added ingredient
    When 250 grams of "chocolate" is locked
    Then we can not lock 251 grams of "chocolate"
    And the stock has 250 grams of "chocolate"
    Then we can lock 250 grams of "chocolate"