package herokuApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class DropDownTest {
    /**
     *

     Open browser

     Navigate to https://the-internet.herokuapp.com/dropdown

     Select "option 1"

     Validate "option 1" is selected
     */
    @Test
    void tc03(){
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://the-internet.herokuapp.com/dropdown");

        Select select = new Select(driver.findElement(By.id("dropdown")));
        select.selectByVisibleText("Option 1");

        Assert.assertTrue(driver.findElement(By.xpath("//select/option[contains(text(), 'Option 1')]")).isSelected(), "Option 1 is not selected");
        driver.quit();
    }
    @Test
    void ableSelectMultipleOptions(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://output.jsbin.com/osebed/2");

        Select select = new Select(driver.findElement(By.id("fruits")));
        Assert.assertTrue(select.isMultiple());

        select.selectByVisibleText("Banana");
        select.selectByVisibleText("Apple");
        select.selectByVisibleText("Orange");

        Assert.assertTrue(driver.findElement(By.xpath("//select/option[contains(text(), 'Banana')]")).isSelected(), "Banana is not selected");
        Assert.assertTrue(driver.findElement(By.xpath("//select/option[contains(text(), 'Apple')]")).isSelected(), "Apple is not selected");
        Assert.assertTrue(driver.findElement(By.xpath("//select/option[contains(text(), 'Orange')]")).isSelected(), "Orange is not selected");
    }

    @Test
    void verifyTextFieldDisabled() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dropdown");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertFalse(driver.findElement(By.cssSelector("form#input-example input")).isEnabled());

        driver.findElement(By.cssSelector("form#input-example button")).click();
        Thread.sleep(5000);

        Assert.assertTrue(driver.findElement(By.cssSelector("form#input-example input")).isEnabled());

        driver.quit();
    }
}
