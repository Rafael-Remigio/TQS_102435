package pt.ua.tqs.openair;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;


import io.github.bonigarcia.seljup.SeleniumJupiter;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ExtendWith(SeleniumJupiter.class)
public class InterfaceTests {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @BeforeEach
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  
  @AfterEach
  public void tearDown() {
    driver.quit();
  }
  //@Disabled
  //@Test
  public void flights() {
    driver.get("http://localhost:3000");
          driver.manage().window().setSize(new Dimension(550, 691));
          driver.findElement(By.cssSelector(".App-header > div:nth-child(1)")).click();
          driver.findElement(By.id("location")).click();
          driver.findElement(By.id("location")).sendKeys("Aveiro");
          driver.findElement(By.cssSelector("input:nth-child(2)")).click();
         

          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          
          driver.findElement(By.cssSelector(".App-header")).click();
          driver.findElement(By.cssSelector("h3")).click();
          driver.findElement(By.cssSelector("h3")).click();
          {
            String value = driver.findElement(By.cssSelector("h3")).getText();
            assertThat(value, is("Results for Aveiro (40.7275536,-8.5209649)"));
          }
          driver.findElement(By.cssSelector("button")).click();
          driver.findElement(By.cssSelector("p:nth-child(2)")).click();
          driver.findElement(By.cssSelector(".App-header")).click();
          driver.findElement(By.cssSelector("div:nth-child(3)")).click();
          driver.findElement(By.cssSelector(".App-header > div:nth-child(1)")).click();
          driver.findElement(By.id("location")).click();
          driver.findElement(By.id("location")).sendKeys("invalid-location");
          driver.findElement(By.id("location")).sendKeys(Keys.ENTER);


          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }

          assertThat(driver.switchTo().alert().getText(), is("Service Unavailable, try again later"));
  }
}