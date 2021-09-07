package com.qa.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.saucedemo.base.BasePage;
import com.qa.saucedemo.utils.ElementUtil;

public class ConstantPage extends BasePage {
	private WebDriver driver;
	private By menuButton = By.xpath("//button[@id='react-burger-menu-btn']");
	private By allItems = By.xpath("//a[@id='inventory_sidebar_link']");
	private By logoutButton = By.xpath("//a[@id='logout_sidebar_link']");
	private By cart = By.xpath("//a[@class='shopping_cart_link']");

	public ConstantPage(WebDriver driver) {
		// THIS PAGE is for elements that appear in all pages AFTER you are Logged IN
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public ProductsPage navigateToProductPage() {
		clickMenuIcon();
		elementUtil.doClick(allItems);
		return new ProductsPage(driver);
	}

	public void clickMenuIcon() {
		// three menu icon top right
		elementUtil.doClick(menuButton);
	}

	public CartPage navigateToCartPage() {
		elementUtil.doClick(cart);
		return new CartPage(driver);
	}

	public LoginPage navigateToLoginPage() {
		clickMenuIcon();
		elementUtil.doClick(logoutButton);
		return new LoginPage(driver);
	}

}
