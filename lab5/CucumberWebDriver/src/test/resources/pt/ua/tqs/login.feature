Feature: Blaze Demo Usage

  Scenario: Successful Usage
    When I navigate to "https://blazedemo.com/"
    And I choose the origin:"Portland" and the destiny:"Dublin" 
    And I select one of the fligths
    And I write my name: "Rafael" 
    And I write my address:"casa em aveiro"
    And I write my city:"aveiro"
    And I write my State:"aveiro"
    And I write my Zip:"123456"
    And I select my Card Type:"American Express"
    And I write my Card Number:"123456"
    And I write my Credit Card month:"01"
    And I write my Credit Card Year:"2025"
    And I write the Name on the Card:"Rafael"
    And Click to confirm
    Then I should be in the "BlazeDemo Confirmation" page

  
 