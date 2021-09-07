package com.qa.saucedemo.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.saucedemo.base.BasePage;
import com.qa.saucedemo.utils.ElementUtil;

public class CartPage extends BasePage {
	private WebDriver driver;
	private By productName = By.xpath("//div[@class='inventory_item_name']");
	private By getNamesOfSelectedItems = By.xpath("//div[@class='inventory_item_name']");
	private By pricesOfSelectedItems = By.xpath("//div[@class='inventory_item_price']");
	private By removeItem = By.xpath("//button[@class='btn btn_secondary btn_small cart_button']");
	private By checkoutButton = By.xpath("//button[@id='checkout']");

	public CartPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public List<String> getTheNamesOfProductsSelected() {
		/*
		 * after you select on an item it will have remove icon instead of add to cart
		 * so this can test the names of the items selected are correct
		 */
		elementUtil.visibilityofAllElements(getNamesOfSelectedItems, 4);
		List<String> itemsSelected = elementUtil.getElements(getNamesOfSelectedItems).stream().map(s -> s.getText())
				.collect(Collectors.toList());
		return itemsSelected;

	}

	public List<String> getThePricesOfProductsSelected() {
		/*
		 * after you select on an item it will have remove icon instead of add to cart
		 * so this can test the names of the items selected are correct
		 */
		List<String> itemsSelected = elementUtil.getElements(getNamesOfSelectedItems).stream().map(s -> s.getText())
				.collect(Collectors.toList());
		return itemsSelected;

	}

	public double totalPriceOfSelecteditems() {
		double total = elementUtil.getElements(pricesOfSelectedItems).stream().map(s -> s.getText())
				.map(s -> s.replaceAll("[^\\d.]", "")).map(Double::parseDouble).mapToDouble(Double::valueOf).sum();
		return total;
	}

	public void removeSingleItem(String x) {
		// removes one item at a time if both t-shirts are in cart will only remove the
		// first shirt in order
		List<WebElement> items = elementUtil.getElements(getNamesOfSelectedItems);
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getText().contains(x)) {
				elementUtil.visibilityofAllElements(removeItem, 5);
				elementUtil.getElements(removeItem).get(i).click();
				break;
			}
		}
	}

	public InformationPage navigateToInformationPage() {
		elementUtil.doClick(checkoutButton);
		return new InformationPage(driver);

	}

}
