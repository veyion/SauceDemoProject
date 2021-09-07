package com.qa.saucedemo.test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.saucedemo.base.BaseTest;
import com.qa.saucedemo.base.BaseTestLoggedIn;
import com.qa.saucedemo.pages.LoginPage;
import com.qa.saucedemo.pages.ProductsPage;

public class ProductPageTest extends BaseTestLoggedIn {

	@Test
	public void verifyAscendingOrderOfPrices() {
		ProductsPage productsPage = new ProductsPage(driver);
		List<Double> pricesSorted = productsPage.newPricesAfterClickingOnSelect("lohi");
		List<Double> SortAscending = pricesSorted.stream().sorted().collect(Collectors.toList());
		Assert.assertEquals(pricesSorted, SortAscending);
	}

	@Test
	public void verifyDescendingOrderOfPrices() {
		ProductsPage productsPage = new ProductsPage(driver);
		List<Double> pricesSorted = productsPage.newPricesAfterClickingOnSelect("hilo");
		List<Double> SortDescending = pricesSorted.stream().sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		Assert.assertEquals(pricesSorted, SortDescending);
	}

	@Test
	public void verifyTheCountOfImages() {
		ProductsPage productsPage = new ProductsPage(driver);
		int count = productsPage.getProductImages();
	//	Assert.assertEquals(count, 6);
	}

	@Test
	public void clickOnMultipleProducts() {
		ProductsPage productsPage = new ProductsPage(driver);
		productsPage.addItemToCart(6);
		int total = productsPage.getNumberDisplayedInCart();
	    Assert.assertEquals(total, 6);
	}
	// After clicking on multiple products it checks if the icon on the topright
	// matches the amount of items you clicked



	
	
	public void verifyItemTshirtAdded() {
		SoftAssert sa = new SoftAssert();
		ProductsPage productsPage = new ProductsPage(driver);
		productsPage.addItemCart("T-Shirt");
		List<String> x = productsPage.getTheNamesOfProductsSelected();
		for (int i = 0; i < x.size(); i++) {
			sa.assertEquals(x.get(i).contains("T-Shirt"), true);
		}
		sa.assertAll();
		// checks if it adds the correct item T-shirt
	}
}
