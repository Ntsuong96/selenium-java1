package herokuApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class HyperLinkTest {

    @Test
    void verifyAbleNaviagteHyperLink() {
        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOption);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/status_codes");

        // Click on "200"
        // <a href="status_codes/200">200</a>
        driver.findElement(By.linkText("200")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/status_codes/200");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("content"))));
        Assert.assertTrue(driver.findElement(By.id("content")).getText().contains("This page returned a 200 status code."));


        //Click on "301"
        driver.navigate().back();
        driver.findElement(By.linkText("301")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/status_codes/301");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("content"))));
        Assert.assertTrue(driver.findElement(By.id("content")).getText().contains("This page returned a 301 status code."));

        //Click on "404"
        driver.navigate().back();
        driver.findElement(By.linkText("404")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/status_codes/404");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("content"))));
        Assert.assertTrue(driver.findElement(By.id("content")).getText().contains("This page returned a 404 status code."));

        //Click on "500"
        driver.navigate().back();
        driver.findElement(By.linkText("500")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/status_codes/500");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("content"))));
        Assert.assertTrue(driver.findElement(By.id("content")).getText().contains("This page returned a 500 status code."));

    }
}
