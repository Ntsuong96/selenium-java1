package pages.calculator.heroku;

import org.openqa.selenium.By;

import static utils.Browser.*;

public class JavaScriptAlertPage {

    public void open() {
        visit("https://the-internet.herokuapp.com/javascript_alerts");
    }

    public void clickOnAlertButton(String alertType) {
        click(By.xpath("//button[contains(text(), '"+alertType+"')]"));
    }

    public void interactWithAlert(String action) {
        if (action.equalsIgnoreCase("accept")) {
            acceptAlert();
        } else if (action.equalsIgnoreCase("dismiss")) {
            dismissAlert();
        }
    }

    public String getAlertResult() {
        return getText(By.id("result"));
    }
    public void setAlertText(String text) {
        sendKeysToAlert(text);
    }


}
