package common;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import static utils.Browser.captureScreenshot;

public class TestBase {
    @AfterMethod(alwaysRun = true)
    protected void takeScreenShotWhenFail(ITestResult result) {
        if(!result.isSuccess()){
            captureScreenshot(result.getName());
        }
    }

}
