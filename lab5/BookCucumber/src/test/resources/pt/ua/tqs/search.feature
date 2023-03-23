Feature: Book search
  To allow customers to find books, the library must offer seek methods.

  Scenario: Search books by publication year
     
    Given I have the following books in the library
      | title                                | author           | p_date |
      | The random book one                  | Guy Author       | 01-1-2005|
      | Another book?                        | Old Authon       | 01-03-1970|
      | The book                             | Im Author        | 01-02-2020|
        
    
    When the customer searches for books published between '1950' and '2010'
    
    Then 2 books should have been found
      And Book 1 should have the title 'The random book one'
      And Book 2 should have the title 'Another book?'
