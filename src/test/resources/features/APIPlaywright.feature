@PlaywrightAPI
Feature: PlaywrightAPI_PoC

  @PlaywrightAPIGet
  Scenario: Playwright API Proof of Concept
    Given User performs get request with the base url "https://api.dictionaryapi.dev/api/v2/entries/en" with endpoint "/Willful"
    Then validate the response
  @PlaywrightAPIPost
  Scenario: Playwright API Proof of Concept
    Given User performs post request with the base url "https://jsonplaceholder.typicode.com" with endpoint "/posts"
    Then validate the response