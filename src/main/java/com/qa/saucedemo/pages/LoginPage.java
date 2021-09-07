package com.qa.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.saucedemo.base.BasePage;
import com.qa.saucedemo.utils.ElementUtil;

public class LoginPage extends BasePage {
	private WebDriver driver;
	private By userName = By.xpath("//input[@id='user-name']");
	private By passWord = By.xpath("//input[@id='password']");
	private By loginButton = By.xpath("//input[@id='login-button']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getLoginPageTitle() {
		return elementUtil.waitForTitleToBePresent("Swag Labs", 3);
	}

	public ProductsPage fullLogin(String username, String password) {
		elementUtil.waitForElementToBeVisible(this.userName, 5);
		elementUtil.doSendKeys(this.userName, username);
		elementUtil.doSendKeys(this.passWord, password);
		elementUtil.doClick(this.loginButton);
		return new ProductsPage(driver);
	}

}
