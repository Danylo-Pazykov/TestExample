package pages.elements;

public interface HamburgerMenuPageElements {

  String MAIN_MENU_ITEM_BY_NAME = "//a[@class='hmenu-item']//div[contains(text(),'%s')]";
  String SUB_MENU_ITEM_BY_NAME = "//ul[@class='hmenu hmenu-visible hmenu-translateX']//a[contains(text(),'%s')]";
}