Feature: Order a party cookie

  Background:
    Given a user
    And a store named "Antibes"
    And an empty cart
    And a valid cookie


  Scenario: he order a classic party cookie XL
    When  he order "XL" a party cookie "Cookie au chocolat" customized with additional M&Ms
    And he validate his cart
    Then the bill is created