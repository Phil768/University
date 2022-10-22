Feature: MarketAlertUM interaction

  In order to check the alerts from e-commerce websites
  As a user of this website
  I want to be able to login, view and upload alerts

  Scenario: Valid login
    Given I am a user of marketalertum
    When I login using "01150cc0-eff8-4df5-a549-eb18cf7c6184"
    Then I should get a login status of "True"

  Scenario: Invalid login
    Given I am a user of marketalertum
    When I login using ""
    Then I should get a login status of "False"

    Scenario: Alert layout
      Given I am an administrator of the website and I upload 3 alerts
      Given I am a user of marketalertum
      When I view a list of alerts "01150cc0-eff8-4df5-a549-eb18cf7c6184"
      Then each alert should contain an icon, which would bring the total number of images present to 6
      And each alert should contain a heading, which would bring the total number of headers present to 3
      And each alert should contain a description
      And each alert should contain an image, which would bring the total number of images present to 6
      And each alert should contain a price
      And each alert should contain a link to the original product website, which would bring the total number of anchors present to 3

  Scenario: Alert limit
    Given I am an administrator of the website and I upload more than 5 alerts
    Given I am a user of marketalertum
    When I view a list of alerts using "01150cc0-eff8-4df5-a549-eb18cf7c6184"
    Then I should see 5 alerts
