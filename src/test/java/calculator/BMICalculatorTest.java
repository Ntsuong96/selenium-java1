package calculator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.calculator.BMIPage;

import java.time.Duration;

import static utils.Browser.*;

public class BMICalculatorTest {

    BMIPage bmiPage;
    @BeforeMethod
    void setup() {
        bmiPage = new BMIPage();
        openBrowser("chrome");
        bmiPage.openPage();
        bmiPage.selectUnitMetric();
        bmiPage.clearForm();
    }

    @Test
    void verifyBIMCalculatorClassification() {

        fill(By.id("cage"), "25");
        if(!iSelected(By.id("csex1"))){
            click(By.id("csex1"));
        }
        fill(By.id("cheightmeter"),"175" );
        fill(By.id("ckg"),"70" );
        click(By.xpath("//input[@value='Calculate']"));
        String resultText = getText(By.cssSelector(".bigtext"));
        Assert.assertEquals(resultText,"BMI = 22.9 kg/m2   (Normal)");

    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                {"25","male", "175", "70", "BMI = 22.9 kg/m2   (Normal)"},
                {"25","male", "175", "90", "BMI = 29.4 kg/m2   (Overweight)"},
                {"25","male", "175", "100", "BMI = 32.7 kg/m2   (Obese Class I)"}
        };
    }


    @Test(dataProvider = "testData")
    void verifyNormalClassification(String age,String gender, String height, String weight, String expectedResult) {

        bmiPage.fillForm(age, gender, height, weight);
        Assert.assertEquals(bmiPage.getResultText(),expectedResult);
    }

    @AfterMethod
    void tearDown() {
            quit();
    }
}


