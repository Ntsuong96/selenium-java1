package herokuApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NestedFrameTest {
    @Test
    void tc06() {
        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOption);
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        Assert.assertTrue(driver.findElement(By.xpath("//body[contains(text(), 'LEFT')]")).getText().contains("LEFT"));

        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-middle");
        Assert.assertTrue(driver.findElement(By.id("content")).getText().contains("MIDDLE"));

        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-right");
        Assert.assertTrue(driver.findElement(By.xpath("//body[contains(text(), 'RIGHT')]")).getText().contains("RIGHT"));

        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-bottom");
        Assert.assertTrue(driver.findElement(By.xpath("//body[contains(text(), 'BOTTOM')]")).getText().contains("BOTTOM"));

        driver.quit();
    }
}
