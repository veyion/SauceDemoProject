package com.qa.saucedemo.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.saucedemo.base.BasePage;
import com.qa.saucedemo.utils.ElementUtil;

public class CheckoutOverviewPage extends BasePage {
	private WebDriver driver;
	private By priceOfProducts = By.xpath("//div[@class='inventory_item_price']");
	private By itemNames = By.xpath("//div[@class='inventory_item_name']");
	private By subtotal = By.xpath("//div[@class='summary_subtotal_label']");
	private By tax = By.xpath("//div[@class='summary_tax_label']");
	private By subtotalWithTax = By.xpath("//div[@class='summary_total_label']");

	public CheckoutOverviewPage(WebDriver driver) {
		// THIS PAGE is for elements that appear in all pages AFTER you are Logged IN
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public List<String> sortedList() {
		return elementUtil.getElements(itemNames).stream().map(s -> s.getText()).sorted().collect(Collectors.toList());
	}

	public double totalPriceOfSelecteditems() {
		double total = elementUtil.getElements(priceOfProducts).stream().map(s -> s.getText())
				.map(s -> s.replaceAll("[^\\d.]", "")).map(Double::parseDouble).mapToDouble(Double::valueOf).sum();
		return total;
	}

	public double priceDisplayed() {
		String price = elementUtil.getElement(subtotal).getText().replaceAll("[^\\d.]", "");
		return Double.parseDouble(price);
		// price displayed

	}

	public double taxDisplayed() {
		String Tax = elementUtil.getElement(tax).getText().replaceAll("[^\\d.]", "");
		return Double.parseDouble(Tax);
		// the taxdisplayed
	}

	public double calculateTax() {
		double d = priceDisplayed() * .08;
		double a = Math.round(d * 100) / 100.0;
		System.out.println(a + " " + d);
		return a;
		// caluclates manually the 8 percent tax and used in maintest to assert the
		// calcluations are correct

	}

	public double totalPriceWithTaxDisplayed() {
		// RETURNS the final price with all items and tax calculated in page
		String price = elementUtil.getElement(subtotalWithTax).getText().replaceAll("[^\\d.]", "");
		return Double.parseDouble(price);

	}

}
