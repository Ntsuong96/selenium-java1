package pages.calculator.heroku;

import org.openqa.selenium.By;

import static utils.Browser.*;

public class CheckBoxPage {
    public void openCheckBoxPage() {
        visit("https://moatazeldebsy.github.io/test-automation-practices/#/checkboxes");
    }

   public void selectCheckBox(String index) {
        if (!iSelected(By.xpath("//input[@data-test='checkbox-checkbox"+index+"']"))) {
            click(By.xpath("//input[@data-test='checkbox-checkbox"+index+"']"));
        }
    }

    public boolean isCheckBoxSelected(String index) {
        By checkbox = By.xpath("//input[@data-test='checkbox-checkbox"+index+"']");
        return iSelected(checkbox);
    }
    public void checkAll() {
        click(By.xpath("//button[@data-test='check-all-button']"));
    }
    public void unCheckAll() {
        click(By.xpath("//button[@data-test='uncheck-all-button']"));
    }

}
