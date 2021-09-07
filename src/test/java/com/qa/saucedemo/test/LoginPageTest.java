package com.qa.saucedemo.test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.qa.saucedemo.base.BaseTest;
import com.qa.saucedemo.pages.LoginPage;
import com.qa.saucedemo.utils.ExcelUtil;

public class LoginPageTest extends BaseTest {

	@Test
	public void verifyTitleFromLogin() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		String Title = loginPage.getLoginPageTitle();
		assertEquals(Title, "Swag Labs");
	}

	@Test
	public void verifyCorrectlLogin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.fullLogin("standard_user", "secret_sauce");
	}
	
}
