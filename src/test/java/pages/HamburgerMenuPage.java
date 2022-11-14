package pages;

import base.BasePage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import pages.elements.HamburgerMenuPageElements;

public class HamburgerMenuPage extends BasePage implements HamburgerMenuPageElements {

  private static final Logger logger = LogManager.getLogger(HamburgerMenuPage.class.getName());

  public HamburgerMenuPage() {
    super();
  }

  public WebElement getMainMenuItem(String itemName) {
    return getElement(String.format(MAIN_MENU_ITEM_BY_NAME, itemName));
  }

  public WebElement getSubMenuItem(String itemName) {
    return getElement(String.format(SUB_MENU_ITEM_BY_NAME, itemName));
  }

  public HamburgerMenuPage selectItemInMainMenuByName(String itemName) {
    scrollToElement(getMainMenuItem(itemName));
    getMainMenuItem(itemName).click();
    logger.info("Select " + itemName + " in main menu");
    return this;
  }

  public TelevisionPage selectItemInSubMenuByName(String itemName) {
    scrollToElement(getSubMenuItem(itemName));
    getSubMenuItem(itemName).click();
    logger.info("Select " + itemName + " in sub menu");
    return new TelevisionPage();
  }

}