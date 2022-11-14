package pages;

import base.BasePage;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.elements.ProductPageElements;

public class ProductPage extends BasePage implements ProductPageElements {

  private static final Logger logger = LogManager.getLogger(ProductPage.class.getName());

  public ProductPage() {
    super();
  }

  public WebElement getAboutThisSection() {
    return getElement(ABOUT_THIS_SECTION);
  }

  public WebElement getAboutThisSectionName() {
    return getElement(ABOUT_THIS_SECTION_NAME);
  }

  public List<WebElement> getAboutThisSectionItems() {
    return getElements(ABOUT_THIS_SECTION_ITEMS);
  }

  private void logAllTextInSection() {
    List<WebElement> sectionItems = getAboutThisSectionItems();
    sectionItems.stream().map(WebElement::getText).forEach(logger::info);
  }

  public void verifySectionIsPresent(String sectionName) {
    scrollToElement(getAboutThisSection());
    Assert.assertTrue(getAboutThisSection().isDisplayed());
    logger.info("Verify section '" + sectionName + "' is displayed");
    Assert.assertEquals(getAboutThisSectionName().getText(), sectionName);
    logger.info(
        "Verify section name: " + sectionName + " equals section name in page: "
            + getAboutThisSectionName().getText());
    logger.info("Log all text in section " + sectionName + ":");
    logAllTextInSection();
  }
}