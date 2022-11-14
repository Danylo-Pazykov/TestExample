package base;

import com.google.common.io.Files;
import helpers.PropsConfig;
import io.qameta.allure.Attachment;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {

  public static final PropsConfig PROPS = ConfigFactory.create(PropsConfig.class);
  private static final Logger logger = LogManager.getLogger(BaseTest.class.getName());
  WebDriver driver;

  @BeforeTest
  public void setUp() {
    driver = Driver.getInstance().setWebDriver().getWebDriver();
    driver.manage().window().maximize();
    BasicConfigurator.configure();
    logger.info("Set up browser");
  }

  @AfterTest
  public void tearDown() {
    Driver.getInstance().deleteWebDriver();
    logger.info("Tear down browser");
  }

  @BeforeMethod
  public void openWebsite() {
    driver.get(PROPS.UI_URL_BASE());
    logger.info("Open website " + PROPS.UI_URL_BASE());
  }

  @Attachment(value = "Page screenshot", type = "image/png")
  public byte[] saveScreenshot(WebDriver driver) throws IOException {
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(screenshot,
        new File("./target/allure-results/screenshots//" + screenshot.getName()));
    return Files.toByteArray(screenshot);
  }

  public void switchToWindow(String windowTitle, int indexWindowsExpect) {
    waitForNumberOfWindowsToEqual(indexWindowsExpect);
    Set<String> windows = driver.getWindowHandles();
    for (String window : windows) {
      driver.switchTo().window(window);
      if (driver.getTitle().contains(windowTitle)) {
        return;
      }
      logger.info("Switch to window by name : " + windowTitle);
    }
  }

  public void switchToWindow(int index) {
    waitForNumberOfWindowsToEqual(index + 1);
    Set<String> windows = driver.getWindowHandles();
    int totalWin = windows.size();
    String winTitle = null;
    for (int i = 0; i < totalWin; i++) {
      if (i == index) {
        winTitle = windows.toArray()[i].toString();
      }
    }
    driver.switchTo().window(winTitle);
    logger.info("Switch to window by name : " + winTitle);
  }

  public void waitForNumberOfWindowsToEqual(int index) {
    new WebDriverWait(driver, Duration.ofSeconds(15)).until(
        ExpectedConditions.numberOfWindowsToBe(index));
    logger.info("Wait for number of windows to equal " + index);
  }
}
