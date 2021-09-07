package com.qa.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.saucedemo.base.BasePage;
import com.qa.saucedemo.utils.ElementUtil;

public class InformationPage extends BasePage {
	private WebDriver driver;
	private By firstNameInput = By.xpath("//input[@data-test='firstName']");
	private By lastNameInput = By.xpath("//input[@data-test='lastName']");
	private By zipPostalInput = By.xpath("//input[@data-test='postalCode']");
	private By continueButton = By.xpath("//input[@id='continue']");

	public InformationPage(WebDriver driver) {
		// THIS PAGE is for elements that appear in all pages AFTER you are Logged IN
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public CheckoutOverviewPage navigateToCheckOut(String firstName, String LastName, String zipCode) {
		elementUtil.doSendKeys(firstNameInput, firstName);
		elementUtil.doSendKeys(lastNameInput, LastName);
		elementUtil.doSendKeys(zipPostalInput, zipCode);
		elementUtil.doClick(continueButton);
		return new CheckoutOverviewPage(driver);
		// adds all the info in information page to navigateToCheckout
	}

}
