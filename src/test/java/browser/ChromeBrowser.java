package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeBrowser {

  private WebDriver driver;

  @Step
  public static ChromeOptions getChromeOptions() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-gpu");
    options.addArguments("--disable-popup-blocking");
    options.addArguments("--allow-failed-policy-fetch-for-test");
    options.addArguments("--disable-browser-side-navigation");
    options.addArguments("--disable-extensions");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--no-sandbox");
    options.addArguments("--incognito");
    options.addArguments("--disable-notifications");
    options.addArguments("--disable-popup-blocking", "true");
    options.addArguments("--disable-infobars");
    Map<String, Object> prefs = new HashMap<String, Object>();
    prefs.put("credentials_enable_service", false);
    prefs.put("profile.password_manager_enabled", false);
    prefs.put("profile.default_content_setting_values.notifications", 2);
    options.setExperimentalOption("prefs", prefs);
    return options;
  }

  @Step
  public WebDriver getRemoteWebDriver() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("enableVNC", true);
    capabilities.setCapability("browser", "chrome");
    capabilities.setCapability("version", "88.0");
    capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
    try {
      driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    } catch (MalformedURLException e) {
      System.out.println("Invalid grid URL");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return driver;
  }

  @Step
  public WebDriver getLocalWebDriver() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    return driver;
  }

}
