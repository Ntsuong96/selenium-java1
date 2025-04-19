package herokuApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class WebTableTest {
    @Test
    void verifySelectDate() {
        /*
        Create test cases:
        open browser
        navigate to https://www.vietnamairlines.com/vn/vi/Home
        select 1 chieu
        select day 7/4/2025
        verify day selected
         */
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.vietnamairlines.com/vn/vi/Home");

        wait.until(ExpectedConditions
                .visibilityOf(driver.findElement(By.xpath("//*[text()='Đồng ý']"))))
                .click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("roundtrip-date-depart")))).click();
        driver.findElement(By.xpath("//ul[@id=\"bookYourTripType\"]//a[@id='oneway']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("roundtrip-date-depart")))).click();
        driver.findElements(By.cssSelector(".ui-datepicker-group-first a")).stream()
                        .filter(e -> e.getText().equals("14"))
                                .findFirst()
                                        .get()
                                                .click();

        String departureDate = driver.findElement(By.id("roundtrip-date-depart"))
                .getDomProperty("value");
        Assert.assertEquals(departureDate, "14/04/2025");

        driver.quit();
    }

    @Test
    void verifyMaxDuePerson() {
        /*

        Open browser

        Navigate to https://the-internet.herokuapp.com/tables

        Focus on table 1

        The person who has largest due is "Doe Jacson"
         */
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/tables");

        /*
        1. get row index of max due -> get lastname/ firstname of max due
        due column xpath //table[@id='table1']//tbody/tr/td[4] > row_index
        lastname column xpath //table[@id='table1']//tbody/tr[row_index]/td[1]
        firstname column xpath //table[@id='table1']//tbody/tr[row_index]/td[1]
         */


    }
}
