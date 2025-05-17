package herokuApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicAuthenticationTest {
    @Test
    void verifyLoginSuccessWithValidCredentials() {
        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOption);

        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        String welcomeText = driver.findElement(By.tagName("p")).getText();

        Assert.assertTrue(welcomeText.contains("Congratulations! You must have the proper credentials."));

        driver.quit();
    }
}
