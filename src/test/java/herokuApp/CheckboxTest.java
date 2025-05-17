package herokuApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.calculator.heroku.CheckBoxPage;

import static utils.Browser.*;

public class CheckboxTest {
    CheckBoxPage checkBoxPage;
    @BeforeClass
    void setup() {
        openBrowser("chrome");
        checkBoxPage = new CheckBoxPage();
        checkBoxPage.openCheckBoxPage();
    }
    @Test
    void verifyCheckBoxWorking(){
        checkBoxPage.selectCheckBox("1");
        Assert.assertTrue(checkBoxPage.isCheckBoxSelected("1"));
        checkBoxPage.selectCheckBox("2");
        Assert.assertTrue(checkBoxPage.isCheckBoxSelected("2"));
    }

    @Test
    void verifyCheckAllButtonWorking(){

        checkBoxPage.checkAll();
        Assert.assertTrue(checkBoxPage.isCheckBoxSelected("1"));
        Assert.assertTrue(checkBoxPage.isCheckBoxSelected("2"));
        Assert.assertTrue(checkBoxPage.isCheckBoxSelected("3"));

    }

    @Test
    void verifyUnCheckAllButtonWorking(){
        checkBoxPage.unCheckAll();
        Assert.assertFalse(checkBoxPage.isCheckBoxSelected("1"));
        Assert.assertFalse(checkBoxPage.isCheckBoxSelected("2"));
        Assert.assertFalse(checkBoxPage.isCheckBoxSelected("3"));
    }

    @AfterClass
    void tearDown() {
        quit();
    }
}
