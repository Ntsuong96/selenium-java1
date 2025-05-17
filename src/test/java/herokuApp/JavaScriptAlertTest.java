package herokuApp;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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
    void verifyClickOnJSAlert() throws InterruptedException {
        Thread.sleep(5000);
        javaScriptAlertPage.clickOnAlertButton("Click for JS Alert");
        javaScriptAlertPage.interactWithAlert("accept");

        String resultText = javaScriptAlertPage.getAlertResult();
        Assert.assertTrue(resultText.contains("You successfully clicked an alert"));

    }
    @Test
    void verifyClickOnJSConfirmWithAccept() {

        javaScriptAlertPage.clickOnAlertButton("Click for JS Confirm");
        javaScriptAlertPage.interactWithAlert("accept");

        String resultText = javaScriptAlertPage.getAlertResult();
        Assert.assertTrue(resultText.contains("You clicked: Ok"));
    }
    @Test
    void verifyClickOnJSConfirmWithDismiss() {
        javaScriptAlertPage.clickOnAlertButton("Click for JS Confirm");
        javaScriptAlertPage.interactWithAlert("dismiss");

        String resultText = javaScriptAlertPage.getAlertResult();
        Assert.assertTrue(resultText.contains("You clicked: Cancel"));
    }

    @Test
    void verifyClickOnJSPrompt() {

        javaScriptAlertPage.clickOnAlertButton("Click for JS Prompt");
        javaScriptAlertPage.setAlertText("Hello");
        javaScriptAlertPage.interactWithAlert("accept");

        String resultText = javaScriptAlertPage.getAlertResult();
        Assert.assertTrue(resultText.contains("You entered: Hello"));
    }

    @AfterMethod
    public void tearDown() {
        quit();
    }
}
