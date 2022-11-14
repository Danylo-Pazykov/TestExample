package base;


import java.io.IOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

@Listeners
public class TestAllureListener extends BaseTest implements ITestListener {

  private static final Logger logger = LogManager.getLogger(TestAllureListener.class.getName());

  private static String getTestMethodName(ITestResult iTestResult) {
    return iTestResult.getMethod().getConstructorOrMethod().getName();
  }

  @Override
  public void onTestFailure(ITestResult iTestResult) {
    logger.info("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
    WebDriver driver = Driver.getInstance().getWebDriver();
    if (driver != null) {
      logger.info("Screenshot captured for test case:" + getTestMethodName(iTestResult));
      try {
        saveScreenshot(driver);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
