package Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v133.emulation.Emulation;
import org.openqa.selenium.devtools.v133.network.Network;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import java.util.HashMap;
import java.util.Map;

public class ChromeTest {
    @Test
    void openWithDefaultMode() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/");
        Assert.assertEquals(driver.getTitle(), "Selenium");
        driver.quit();
    }
    @Test
    void openWithHeadlessMode() {
        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOption);
        driver.get("https://www.selenium.dev/");
        Assert.assertEquals(driver.getTitle(), "Selenium");
        driver.quit();
    }
    @Test
    void openWithMobileViewPort() {
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 430);
        deviceMetrics.put("height", 930);
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/");
        Assert.assertEquals(driver.getTitle(),"Selenium");
//        driver.quit();
    }
    @Test
    void openWithOldChromeVersion() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBrowserVersion("130");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/");
        Assert.assertEquals(driver.getTitle(),"Selenium");
//        driver.quit();
    }
    @Test
    void openBrowserWithFakeGeoLocation(){
        WebDriver driver = new ChromeDriver();
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        // Mountain view
        devTools.send(Emulation.setGeolocationOverride(
                Optional.of(37.386052),
                Optional.of(-122.083851),
                Optional.of(1)
        ));
        driver.get("https://the-internet.herokuapp.com/geolocation");
        driver.findElement(By.xpath("//button[.='Where am I?']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#lat-value")).getText(),"37.386052");
        Assert.assertEquals(driver.findElement(By.cssSelector("#long-value")).getText(),"-122.083851");

        driver.quit();
    }
    @Test
    void interceptionNetwork(){
        WebDriver driver = new ChromeDriver();
        DevTools devTool = ((HasDevTools) driver).getDevTools();

        devTool.createSession();
        devTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devTool.addListener(Network.requestWillBeSent(), requestSent -> {
            System.out.println("Request URL => " + requestSent.getRequest().getUrl());
            System.out.println("Request Method => " + requestSent.getRequest().getMethod());
            System.out.println("Request Headers => " + requestSent.getRequest().getHeaders().toString());
            System.out.println("------------------------------------------------------");
        });

        devTool.addListener(Network.responseReceived(), responseReceived -> {
            System.out.println("Response Url => " + responseReceived.getResponse().getUrl());
            System.out.println("Response Status => " + responseReceived.getResponse().getStatus());
            System.out.println("Response Headers => " + responseReceived.getResponse().getHeaders().toString());
            System.out.println("Response MIME Type => " + responseReceived.getResponse().getMimeType().toString());
            System.out.println("------------------------------------------------------");
        });

        driver.get("https://selenium.dev");
    }

}
