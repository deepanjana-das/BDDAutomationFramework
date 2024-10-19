Feature: Perform multiple operations with web table
  Scenario: Print table elements
    Given Open browser and go to web page url "https://googlechromelabs.github.io/chrome-for-testing/"
    When First table elements are present
    Then Read all row and col values from that table