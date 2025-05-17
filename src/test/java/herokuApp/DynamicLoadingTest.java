package herokuApp;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class DynamicLoadingTest {
    @Test
    void verifyDanamicLoading() {
        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOption);
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        driver.findElement(By.xpath("//button[contains(text(), 'Start')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));

        String finishTest = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish"))).getText();
        Assert.assertTrue(finishTest.contains("Hello World!"));

        driver.quit();
    }

    @Test
    void captureScreenshot() {
        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOption);
        driver.get("https://the-internet.herokuapp.com/context_menu");

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(String.format("target/screenshots/%s.png", System.currentTimeMillis()));

        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
           throw new RuntimeException();
        }

        driver.quit();
    }
}
