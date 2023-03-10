Feature: Order a party cookie

  Background:
    Given a user
    And a store named "Antibes"
    And an initialised cookie book
    And an empty cart
    And a valid cookie

  Scenario: he order a classic party cookie L
    When  he order "L" a party cookie "Cookie au chocolat" customized with additional M&Ms
    And he validate his cart
    Then the bill is created
    Then the price of the partyCookie is 3.40


  Scenario: he order a classic party cookie XL
    When  he order "XL" a party cookie "Cookie au chocolat" customized with additional M&Ms
    And he validate his cart
    Then the bill is created
    Then the price of the partyCookie is 4.2


  Scenario: he order a classic party cookie XXL
    When  he order "XXL" a party cookie "Cookie au chocolat" customized with additional M&Ms
    And he validate his cart
    Then the bill is created
    Then the price of the partyCookie is 5.0


