package com.qa.saucedemo.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.saucedemo.pages.LoginPage;

public class BaseTestLoggedIn {
	public WebDriver driver;

	public BasePage basePage;
	public Properties prop;

	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
	      driver  = basePage.init_driver();
	      LoginPage lp = new LoginPage(driver);
	      lp.fullLogin("standard_user", "secret_sauce");
	    
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
