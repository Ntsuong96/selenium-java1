package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Browser {

    private static WebDriver driver;
    public static WebDriverWait wait;
    public static void openBrowser(String browser) {
        switch (browser.toLowerCase()){
            case "chrome":
                ChromeOptions chromeOption = new ChromeOptions();
                chromeOption.addArguments("--headless");
                driver = new ChromeDriver(chromeOption);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                driver = new ChromeDriver();
                break;
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public static WebDriver getDriver() {
        return driver;
    }

    public static void visit(String url) {
        driver.get(url);
    }
    public static void quit() {
            driver.quit();
    }
    public static void click (By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
    public static void fill(By by, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(text);
    }
    public static boolean iSelected(By by){
        return driver.findElement(by).isSelected();
    }
    public static void check (By by){
        if (!iSelected(by)) {
            click(by);
        }
    }
    public static String getText(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).getText();
    }
    public static String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    public static void captureScreenshot(String fileName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(String.format("target/screenshots/%s.png",fileName, System.currentTimeMillis()));
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    public static void acceptAlert() {
        driver.switchTo().alert().accept();
    }
    public static void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }
    public static String getAlertText() {
        return driver.switchTo().alert().getText();
    }
    public static void sendKeysToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);
    }
}
