package flight;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class BookingTest {

        WebDriver driver;
        WebDriverWait wait;



        @Test
        void buyOneWayTicket() throws InterruptedException {
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
                    .filter (e ->e.getText().equals("14"))
                    .findFirst()
                    .get()
                    .click();

            String departureDate = driver.findElement(By.id("roundtrip-date-depart"))
                    .getDomProperty("value");
            Assert.assertEquals(departureDate, "14/05/2025");

            driver.quit();
        }
    }

