@Playwright
Feature: PlaywrightWeb_PoC

  @PlaywrightWeb
  Scenario: Playwright Web proof of concept
    Given Browser driver : "Chrome"
    Given User navigate to "https://Uibank.uipath.com/" using playwright
    When User enter the username and password for the application using playwright
    Then User should be able to apply for loan
