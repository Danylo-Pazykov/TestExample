package base;

import browser.ChromeBrowser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Driver {

  private static Driver instance;
  private WebDriver driver;

  private Driver() {
  }

  public static Driver getInstance() {
    if (instance == null) {
      instance = new Driver();
    }
    return instance;
  }

  public Driver setWebDriver() {
    String remote = System.getProperty("runType", "local");
    if (remote.equalsIgnoreCase("remote")) {
      driver = new ChromeBrowser().getRemoteWebDriver();
    } else {
      driver = new ChromeBrowser().getLocalWebDriver();
    }
    return this;
  }

  public WebDriver getWebDriver() {
    return driver;
  }

  public void deleteWebDriver() {
    if (driver != null) {
      driver.quit();
    }
    driver = null;
  }
}
