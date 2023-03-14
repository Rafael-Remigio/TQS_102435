package pt.ua.tqs.webPages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class ChoosePage {
   private WebDriver driver;

   //Page URL
   private static String PAGE_URL="https://blazedemo.com/reserve.php";

   //Locators


   //Constructor
   public ChoosePage(WebDriver driver){
       this.driver=driver;
       driver.get(PAGE_URL);
       //Initialise Elements
       PageFactory.initElements(driver, this);
   }

   public void clickOnFindButton(String value){
        

        // Gets the form element from an ID and submits
        driver.findElement(By.id(value)).submit();


   }
}

