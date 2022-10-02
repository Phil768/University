Feature: Simple calculations

  In order to help me with my calculations
  As a user of the calculator
  I want to be able to add, subtract, multiply and divide

  Scenario: Simple addition
    Given I am using the calculator
    When I add 5 and 2
    Then I should get the added result of 7

  Scenario: Simple subtraction
    Given I am using the calculator
    When I subtract 7 from 11
    Then I should get the difference result of 4

  Scenario: Simple division
    Given I am using the calculator
    When I divide 8 by 2
    Then I should get the divided result of 4

  Scenario: Simple multiplication
    Given I am using the calculator
    When I multiply 7 by 2
    Then I should get the multiplied result of 14


    