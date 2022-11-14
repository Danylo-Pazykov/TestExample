package tests;

import base.BaseTest;
import base.TestAllureListener;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ProductPage;

@Listeners(TestAllureListener.class)
public class ProductPageTest extends BaseTest {

  private static final String MAIN_MENU_NAME = "TV, Appliances, Electronics";
  private static final String SUB_MENU_NAME = "Televisions";
  private static final String CATEGORY_NAME = "Brands";
  private static final String FILTER_NAME = "Samsung";
  private static final String PRICE_NAME = "Price: High to Low";
  private static final String INDEX_PRICED_ITEM = "2";
  private static final String SECTION_NAME = "About this item";


  @Description("Verify display section 'About this item' and log this section text")
  @Test(priority = 1)
  public void verifyDisplaySectionAboutThisItemAndLogThisSectionText() {
    new MainPage()
        .openTheHamburgerMenu()
        .selectItemInMainMenuByName(MAIN_MENU_NAME)
        .selectItemInSubMenuByName(SUB_MENU_NAME)
        .selectCheckboxFilterByCategoryNameAndFilterName(CATEGORY_NAME, FILTER_NAME)
        .sortResultInPageByPriceName(PRICE_NAME)
        .clickOnThePricedItemByIndexItem(INDEX_PRICED_ITEM);
    switchToWindow(1);
    new ProductPage()
        .verifySectionIsPresent(SECTION_NAME);

  }

}
