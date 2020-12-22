Feature: Validate Place API
  @AddPlace @combine
  Scenario Outline: Verify if Place is added successfully using Add Place API
    Given Add Place payload with "<name>" "<language>" "<address>"
    When  user calls "AddPlaceAPI" with http "POST" request
    Then API call should be successful with status code 200
    And "status" should be "OK" in response body
    And "scope" should be "APP" in response body
    And verify place_id created maps to "<name>" using "GetPlaceAPI"
    Examples:
      | name            | language | address                    |
      | Frontline house | French-IN | 29, side layout, cohen 09 |
      #| front line buxar |  English | 254, buxar layout, bihar  |
  @DeletePlace @combine
  Scenario: Verify if Place is deleted successfully using DeltePlace API
    Given DeletePlace payload
    When  user calls "DeletePlaceAPI" with http "POST" request
    Then API call should be successful with status code 200
    And "status" should be "OK" in response body