package herokuApp;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class WebTableTest {

    WebDriver driver;
    List<Person> personList;

    @BeforeClass
    void setup() {
        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("--headless");
        driver = new ChromeDriver(chromeOption);
        driver.get("https://the-internet.herokuapp.com/tables");
        personList = new ArrayList<>();
        driver.findElements(By.xpath("//table[@id='table1']/tbody/tr"))
                .forEach(row -> {
                    String lastName = row.findElement(By.xpath("./td[1]")).getText();
                    String firstName = row.findElement(By.xpath("./td[2]")).getText();
                    double due = Double.parseDouble(row.findElement(By.xpath("./td[4]"))
                            .getText().replace("$", ""));
                    personList.add(new Person(lastName, firstName, due));
                });
    }

    @Test
    void verifySelectDate() {
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
                .filter(e -> e.getText().equals("16"))
                .findFirst()
                .get()
                .click();

        String departureDate = driver.findElement(By.id("roundtrip-date-depart"))
                .getDomProperty("value");
        Assert.assertEquals(departureDate, "16/05/2025");

    }

    @Test
    void verifyMaxDuePerson() {

        List<Double> dueList = driver
                .findElements(By.xpath("//table[@id='table1']/tbody//tr/td[4]"))
                .stream()
                .map(cell -> Double.valueOf(cell.getText().replace("$", "")))
                .collect(Collectors.toList());
        double maxDue = dueList.stream().max(Double::compareTo).get();
        int rowIndex = dueList.indexOf(maxDue) + 1;
        String lastName = driver
                .findElement(By.xpath("//table[@id='table1']//tbody/tr[" + rowIndex + "]/td[1]"))
                .getText();
        String firstName = driver
                .findElement(By.xpath("//table[@id='table1']//tbody/tr[" + rowIndex + "]/td[2]"))
                .getText();
        String fullName = lastName + " " + firstName;
        System.out.println("The person who has largest due is: " + fullName);
        Assert.assertEquals(fullName, "Doe Jakson");

    }

    @Test
    public void tc06() {

        double maxDue = personList.stream().max(Comparator.comparing(Person::getDue)).get().getDue();
        List<String> ListPersonHaveMaxDue = personList.stream()
                .filter(p -> p.getDue() == maxDue)
                .map(Person::getFullName)
                .toList();
        Assert.assertEquals(ListPersonHaveMaxDue, List.of("Doe Jason"));

    }


    @Test
    public void tc07() {

        double minDue = personList.stream().min(Comparator.comparing(Person::getDue)).get().getDue();
        List<String> ListPersonHaveMinDue = personList.stream()
                .filter(p -> p.getDue() == minDue)
                .map(Person::getFullName)
                .toList();
        Assert.assertEquals(ListPersonHaveMinDue, List.of("Smith John", "Conway Tim"));
    }

    @AfterMethod
    void captureScreenshot(ITestResult testResult) {
        if(!testResult.isSuccess()){
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(String.format("target/screenshots/%s.png",testResult.getName(), System.currentTimeMillis()));

            try {
                FileUtils.copyFile(srcFile, destFile);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }

    @AfterClass(alwaysRun = true)
    void tearDown() {
        driver.quit();
    }
}

