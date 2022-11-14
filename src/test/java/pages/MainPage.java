package pages;

import base.BasePage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import pages.elements.MainPageElements;

public class MainPage extends BasePage implements MainPageElements {

  private static final Logger logger = LogManager.getLogger(MainPage.class.getName());

  public MainPage() {
    super();
  }

  public WebElement getHamburgerMenuBtn() {
    return getElement(HAMBURGER_MENU_BTN);
  }

  public HamburgerMenuPage openTheHamburgerMenu() {
    getHamburgerMenuBtn().click();
    logger.info("Open the Hamburger Menu");
    return new HamburgerMenuPage();
  }
}