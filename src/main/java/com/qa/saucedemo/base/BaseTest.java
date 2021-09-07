package com.qa.saucedemo.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


public class BaseTest {

	public WebDriver driver;

	public BasePage basePage;
	public Properties prop;

	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
	      driver  = basePage.init_driver();
	    
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
