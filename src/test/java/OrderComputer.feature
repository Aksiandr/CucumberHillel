Feature: Order


  Scenario: Add computer to Cart
    Given User opens site to order computer
    When User builds his own computer
    Then User adds built computer to cart
    Then User navigates to Cart
    And User checks if order is correct