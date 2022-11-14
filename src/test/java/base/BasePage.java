package base;

import java.time.Duration;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

  private static final int TIMER = 15;
  private static final Logger logger = LogManager.getLogger(BasePage.class.getName());
  private final WebDriverWait wait;
  public WebDriver driver;

  public BasePage() {
    this.driver = Driver.getInstance().getWebDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(TIMER));
  }

  public WebElement getElement(String locator) {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    return driver.findElement(By.xpath(locator));
  }

  public List<WebElement> getElements(String locator) {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    return driver.findElements(By.xpath(locator));
  }

  public void scrollToElement(WebElement webElement) {
    Actions action = new Actions(driver);
    action.moveToElement(webElement).perform();
  }

  public void waitForRefreshPage(String previousUrl) {
    waitUpdateURL(previousUrl);
    waitForPageLoadComplete();
    logger.info("Wait for the refresh page");
  }

  public void waitForPageLoadComplete() {
    wait.until(driver1 -> String
        .valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
        .equals("complete"));
  }

  public void waitUpdateURL(String previousUrl) {
    ExpectedCondition<Boolean> urlIsCorrect = arg0 -> !driver.getCurrentUrl()
        .equalsIgnoreCase(previousUrl);
    wait.until(urlIsCorrect);
  }
}
