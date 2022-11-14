package pages.elements;

public interface TelevisionPageElements {

  String FILTER_LIST_BY_CATEGORY_NAME = "//div[@class='a-section a-spacing-small' and span/text()='%s']//following-sibling::ul";
  String FILTER_BY_FILTER_NAME = "//span[@class='a-size-base a-color-base' and text()='%s']//parent::a//i";
  String SORT_BTN = "//span[@class='a-button-text a-declarative']";
  String SORT_FILTER_BTN_BY_FILTER_NAME = "//li[@class='a-dropdown-item' and a/text()='%s']";
  String SEARCH_RESULT_ITEM = "//div[@cel_widget_id='MAIN-SEARCH_RESULTS-%s']";
}