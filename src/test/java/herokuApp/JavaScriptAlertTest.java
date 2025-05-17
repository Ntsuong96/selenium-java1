package herokuApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.calculator.heroku.JavaScriptAlertPage;

import static utils.Browser.openBrowser;
import static utils.Browser.quit;

public class JavaScriptAlertTest {

    JavaScriptAlertPage javaScriptAlertPage;
    @BeforeMethod
    public void setUp() {
        openBrowser("chrome");
        javaScriptAlertPage =new JavaScriptAlertPage();
        javaScriptAlertPage.open();
    }
    @Test
    void verifyClickOnJSAlert() {

        javaScriptAlertPage.clickOnAlertButton("Click for JS Alert");
        String alertText = javaScriptAlertPage.getAlertText();
        System.out.println("Alert text: " + alertText);
        javaScriptAlertPage.interactWithAlert("accept");

        String resultText = javaScriptAlertPage.getAlertResult();
        Assert.assertTrue(resultText.contains("You successfully clicked an alert"));

    }
    @Test
    void verifyClickOnJSConfirmWithAccept() {

        javaScriptAlertPage.clickOnAlertButton("Click for JS Confirm");
        String alertText = javaScriptAlertPage.getAlertText();
        System.out.println("Alert text: " + alertText);
        javaScriptAlertPage.interactWithAlert("accept");

        String resultText = javaScriptAlertPage.getAlertResult();
        Assert.assertTrue(resultText.contains("You clicked: Ok"));
    }
    @Test
    void verifyClickOnJSConfirmWithDismiss() {
        javaScriptAlertPage.clickOnAlertButton("Click for JS Confirm");
        String alertText = javaScriptAlertPage.getAlertText();
        System.out.println("Alert text: " + alertText);
        javaScriptAlertPage.interactWithAlert("dismiss");

        String resultText = javaScriptAlertPage.getAlertResult();
        Assert.assertTrue(resultText.contains("You clicked: Cancel"));
    }

    @Test
    void verifyClickOnJSPrompt() {

        javaScriptAlertPage.clickOnAlertButton("Click for JS Prompt");
        String alertText = javaScriptAlertPage.getAlertText();
        System.out.println("Alert text: " + alertText);
        javaScriptAlertPage.setAlertText("Hello");
        javaScriptAlertPage.interactWithAlert("accept");

        String resultText = javaScriptAlertPage.getAlertResult();
        Assert.assertTrue(resultText.contains("You entered: Hello"));
    }

    @BeforeMethod
    public void tearDown() {
        quit();
    }
}
