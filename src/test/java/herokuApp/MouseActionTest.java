package herokuApp;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;

public class MouseActionTest {
    @Test
    void hoverTest() {
        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOption);
        driver.get("https://the-internet.herokuapp.com/hovers");

        Actions actions = new Actions(driver);
        WebElement avatar = driver.findElements(By.className("figure")).get(2);

        actions.moveToElement(avatar).perform();
        Assert.assertTrue(avatar.findElement(By.xpath(".//h5")).isDisplayed());
        driver.quit();
    }

    @Test
    void dragAndDropTest() {
        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOption);
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        Actions actions = new Actions(driver);
        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));

        actions.dragAndDrop(source, target).perform();
        Assert.assertEquals(target.getText(), "A");
        driver.quit();
    }

    @Test
    void horizontalSliderTest() {
        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOption);
        driver.get("https://the-internet.herokuapp.com/horizontal_slider");

        Actions actions = new Actions(driver);
        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
        actions.clickAndHold(slider).moveByOffset(slider.getSize().getWidth(), 0).release().perform();

        String value = driver.findElement(By.id("range")).getText();
        Assert.assertEquals(value, "5");
        driver.quit();
    }

    @Test
    void infiniteScrollTest() throws InterruptedException {
        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOption);
        driver.get("https://the-internet.herokuapp.com/infinite_scroll");

        Actions actions = new Actions(driver);
        for (int i = 0; i < 5; i++) {
            actions.scrollByAmount(0, 100).perform();
                Thread.sleep(1000); // Wait for the page to load
        }
        driver.quit();
    }

    @Test
    void contextMenuTest() {
        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOption);
        driver.get("https://the-internet.herokuapp.com/context_menu");

        Actions actions = new Actions(driver);
        WebElement box = driver.findElement(By.id("hot-spot"));
        actions.contextClick(box).perform();

        String alertText = driver.switchTo().alert().getText();
        Assert.assertEquals(alertText, "You selected a context menu");
        driver.switchTo().alert().accept();
        driver.quit();
    }

    @Test
    void keyPressTest() {
        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOption);
        driver.get("https://the-internet.herokuapp.com/key_presses");

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform();

        String result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, "You entered: ENTER");
        driver.quit();
    }
}
