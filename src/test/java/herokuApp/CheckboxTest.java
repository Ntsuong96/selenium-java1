package herokuApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckboxTest {
    @Test
    void tc02(){
/*
        Open browser

        Navigate to https://the-internet.herokuapp.com/checkboxes

        Check on checkbox1

        Verify checkbox1 is checked

        Check on checkbox2

        Verify checkbox2 is checked
 */
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/checkboxes/");
        driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]")).isSelected(), "Checkbox 1 is not selected");
        driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]")).isSelected(), "Checkbox 2 is not selected");
    }

    @Test
    void verifyCheckAllButtonWorking(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://moatazeldebsy.github.io/test-automation-practices/#/checkboxes");

        driver.findElement(By.xpath("//button[@data-test='check-all-button']")).click();
        
        Assert.assertTrue(driver.findElement(By.xpath("//input[@data-test='checkbox-checkbox1']")).isSelected(), "Checkbox 1 is not selected");
        Assert.assertTrue(driver.findElement(By.xpath("//input[@data-test='checkbox-checkbox2']")).isSelected(), "Checkbox 2 is not selected");
        Assert.assertTrue(driver.findElement(By.xpath("//input[@data-test='checkbox-checkbox3']")).isSelected(), "Checkbox 3 is not selected");
    }

    @Test
    void verifyUnCheckAllButtonWorking(){
        WebDriver driver = new FirefoxDriver();
        driver.get("https://the-internet.herokuapp.com/checkboxes/");
        if(!driver.findElement(By.xpath("//input[@data-test='checkbox-checkbox1']")).isSelected()){
            driver.findElement(By.xpath("//button[@data-test='check-all-button']")).click();
        }
        driver.findElement(By.xpath("//button[@data-test='uncheck-all-button']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@data-test='checkbox-checkbox1']")).isSelected(), "Checkbox 1 is not selected");
        Assert.assertTrue(driver.findElement(By.xpath("//input[@data-test='checkbox-checkbox2']")).isSelected(), "Checkbox 2 is not selected");
        Assert.assertTrue(driver.findElement(By.xpath("//input[@data-test='checkbox-checkbox3']")).isSelected(), "Checkbox 3 is not selected");
    }
}
