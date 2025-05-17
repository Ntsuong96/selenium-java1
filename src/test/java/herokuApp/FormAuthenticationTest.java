package herokuApp;

import common.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.calculator.heroku.FormAuthenticationPage;

import java.lang.reflect.Method;

import static utils.Browser.*;

public class FormAuthenticationTest extends TestBase {
    FormAuthenticationPage formAuthenticationPage;
    @BeforeClass
    void setup() {
        openBrowser("chrome");
        formAuthenticationPage = new FormAuthenticationPage();
    }

    @Parameters({"browser"})
    @Test
    void tc01(String browser) {
        formAuthenticationPage
                .open()
                .login("tomsmith", "SuperSecretPassword!");

        Assert.assertEquals(getCurrentUrl(), "https://the-internet.herokuapp.com/secure");
        Assert.assertTrue(formAuthenticationPage.getWelcomeMessage().contains("Welcome to the Secure Area"));
    }

    @AfterClass
    void tearDown(){
        quit();
    }
}
