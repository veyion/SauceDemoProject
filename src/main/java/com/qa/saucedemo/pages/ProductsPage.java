package com.qa.saucedemo.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.saucedemo.base.BasePage;
import com.qa.saucedemo.utils.ElementUtil;
import com.qa.saucedemo.utils.JavaScriptUtil;

public class ProductsPage extends BasePage {
	private WebDriver driver;
	private By PriceOfProducts = By.xpath("//div[@class='inventory_item_price']");
	private By selectDropDown = By.xpath("//select[@class='product_sort_container']");
	private By checkoutIcon = By.id("shopping_cart_container");
	private By productImages = By.xpath("//div[@id='inventory_container']//img");
	private By productNameHeader = By.xpath("//div[@class='inventory_item_name']");
	private By addToCartButton = By.xpath("//*[@class='btn btn_primary btn_small btn_inventory']");
	private By cartNumbersDisplayed = By.xpath("//a[@class='shopping_cart_link']//span");
	private By wholeItemDescription = By.xpath("//*[@class='inventory_item_description']");
	private By itemsThatAreInCart = By.xpath("//*[@class='inventory_item_description']//*[text()='Remove']");
	private By itemName = By.xpath("//div[@class='inventory_item_name']");
	private By getTitleOfSelectedItems = By.xpath("//*[text()='Remove']/../..//div[@class='inventory_item_name']");
	private By pricesOfSelectedItems = By.xpath("//*[text()='Remove']/../..//div[@class='inventory_item_price']");
	private By customStringName = By
			.xpath("//*[contains(text(),)]/../../..//*[@class='btn btn_primary btn_small btn_inventory']");

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public void addItemToCart(int x) {
		// this method would add first product that comes first in particular order
		elementUtil.visibilityofAllElements(addToCartButton, 5);
		if (x > 0 && x < 7) {
			for (int i = 0; i < x; i++) {
				elementUtil.doClick(addToCartButton);
			}
		}

	}

	public ProductsPage addItemCart(String x) {
		scrollToAllItems();
		/*
		 * based on the string you provide it will add that partiular item like
		 * T-Shirt,Jacket,Backpack,Bike or Onesie
		 */
		elementUtil.visibilityofAllElements(itemName, 5);
		System.out.println(customStringName);
		attempt(x).stream().forEach(s -> s.click());
		return new ProductsPage(driver);

	}

	public String getProductPageTitle() {
		return elementUtil.waitForTitleToBePresent("Products", 3);

	}

	public int getNumberDisplayedInCart() {
		// will display the number of items displayed in the cart icon in top right of
		// ui
		// if quantity is 6 it should contain 6
		String total = elementUtil.doGetText(cartNumbersDisplayed);
		int number = Integer.parseInt(total);
		return number;
	}

	public ProductsPage chooseSelectDropDown(String value) {
		// For Select dropdown pass value like high low = hilo or Low to high = lohi
		elementUtil.doSelectByValue(selectDropDown, value);
		return new ProductsPage(driver);

	}

	public List<Double> newPricesAfterClickingOnSelect(String value) {
		chooseSelectDropDown(value);
		List<WebElement> allProductPrices = elementUtil.getElements(PriceOfProducts);
		return allProductPrices.stream().map(s -> s.getText()).map(s -> s.replaceAll("[^\\d.]", ""))
				.map(Double::parseDouble).collect(Collectors.toList());
		// this action will give the prices in order of how it comes up in UI after you
		// filter it by sorting

	}

	public double totalPriceOfSelecteditems() {
		double total = elementUtil.getElements(pricesOfSelectedItems).stream().map(s -> s.getText())
				.map(s -> s.replaceAll("[^\\d.]", "")).map(Double::parseDouble).mapToDouble(Double::valueOf).sum();
		return total;
	}

	public CartPage navigateToCartPage() {
		elementUtil.clickWhenReady(checkoutIcon, 2);
		return new CartPage(driver);
		// navigates to cart page before checkout
	}

	public int getProductImages() {
		int imagesCount = elementUtil.getElements(productImages).size();
		System.out.println("total images : " + imagesCount);
		return imagesCount;
		// can be used to check image count of the 6 items and can be used to assert
	}

	public String getProductInfoPageTitle(String productName) {
		return elementUtil.waitForTitleToBePresent(productName, 5);

	}

	public List<String> getTheNamesOfProductsSelected() {
		/*
		 * after you select on an item it will have remove icon instead of add to cart
		 * so this can test the names of the items selected are correct
		 */
		elementUtil.visibilityofAllElements(getTitleOfSelectedItems, 5);
		List<String> itemsSelected = elementUtil.getElements(getTitleOfSelectedItems).stream().map(s -> s.getText())
				.collect(Collectors.toList());
		return itemsSelected;

	}

	public void scrollToAllItems() {
		// this just scrolls to the view of all six items displayed fully
		JavaScriptUtil x = new JavaScriptUtil(driver);
		x.scrollPageHalfWayDown();
	}

	public List<WebElement> attempt(String x) {
		return elementUtil.getElements(By.xpath(
				"//*[contains(text(),'" + x + "')]/../../..//*[@class='btn btn_primary btn_small btn_inventory']"));
	}

	// *[text()='Remove']/../..//div[@class='inventory_item_name']
}
