package pages;

import base.BasePage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.elements.TelevisionPageElements;

public class TelevisionPage extends BasePage implements TelevisionPageElements {

  private static final Logger logger = LogManager.getLogger(TelevisionPage.class.getName());
  private String currentURL;

  public TelevisionPage() {
    super();
  }

  public WebElement getFilterListByCategoryName(String itemName) {
    return getElement(String.format(FILTER_LIST_BY_CATEGORY_NAME, itemName));
  }

  public WebElement getSortBtn() {
    return getElement(SORT_BTN);
  }

  public WebElement getSortFilterBtnByName(String itemName) {
    return getElement(String.format(SORT_FILTER_BTN_BY_FILTER_NAME, itemName));
  }

  public WebElement getSearchResultItemByIndex(String index) {
    return getElement(String.format(SEARCH_RESULT_ITEM, index));
  }

  public TelevisionPage selectCheckboxFilterByCategoryNameAndFilterName(String categoryName,
      String filterName) {
    scrollToElement(getFilterListByCategoryName(categoryName));
    getFilterListByCategoryName(categoryName).findElement(
        By.xpath(String.format(FILTER_BY_FILTER_NAME, filterName))).click();
    logger.info("Select filter " + filterName + " in the category " + categoryName);
    Assert.assertTrue(getSearchResultItemByIndex("1").isDisplayed());
    logger.info("Assert for the first element is displayed");
    return new TelevisionPage();
  }

  public TelevisionPage sortResultInPageByPriceName(String priceName) {
    currentURL = driver.getCurrentUrl();
    getSortBtn().click();
    logger.info("Open Sort dropdown");
    getSortFilterBtnByName(priceName).click();
    logger.info("Select price by price name: " + priceName);
    return this;
  }

  public ProductPage clickOnThePricedItemByIndexItem(String indexItem) {
    waitForRefreshPage(currentURL);
    getSearchResultItemByIndex(indexItem).click();
    logger.info("Open product page by product index " + indexItem);
    return new ProductPage();
  }
}