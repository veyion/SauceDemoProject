package com.qa.saucedemo.test;

import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.saucedemo.base.BaseTest;
import com.qa.saucedemo.pages.CartPage;
import com.qa.saucedemo.pages.CheckoutOverviewPage;
import com.qa.saucedemo.pages.ConstantPage;
import com.qa.saucedemo.pages.LoginPage;
import com.qa.saucedemo.pages.ProductsPage;

public class MainTest extends BaseTest {
	@Test
	public void MainTest() throws InterruptedException {
		SoftAssert SA = new SoftAssert();
		LoginPage loginPage = new LoginPage(driver);
		ProductsPage productsPage = new ProductsPage(driver);
		ConstantPage constantsPage = new ConstantPage(driver);
		CartPage cartPage = new CartPage(driver);
		CheckoutOverviewPage COP = new CheckoutOverviewPage(driver);

		loginPage.fullLogin("standard_user", "secret_sauce").chooseSelectDropDown("lohi").addItemCart("Backpack")
				.addItemCart("T-Shirt");
		// logs in first then sorts from low to high then adds both T-shirt and backpack
		// to cart
		List<String> itemsSelectedinProductsPage = productsPage.getTheNamesOfProductsSelected();
		itemsSelectedinProductsPage = itemsSelectedinProductsPage.stream().sorted().collect(Collectors.toList());
		// sorts the list back to natural order because the list in the cart page comes
		// in alphabetical order
		constantsPage.navigateToCartPage(); // navigates to cart page
		List<String> itemsSelectedinCartPage = cartPage.getTheNamesOfProductsSelected(); // list of items in cart page
		SA.assertEquals(itemsSelectedinProductsPage, itemsSelectedinCartPage);
		// asserts that items added are in cart
		cartPage.removeSingleItem("Backpack");       //removes item backpack
		constantsPage.navigateToProductPage().addItemCart("Jacket");
		List<String> sorteditemsInCart = constantsPage.navigateToCartPage().getTheNamesOfProductsSelected().stream()
				.sorted().collect(Collectors.toList());
		List<String> sortedItemsInCheckout = cartPage.navigateToInformationPage()
				.navigateToCheckOut("mr.", "random", "12323").sortedList();
		SA.assertEquals(sorteditemsInCart, sortedItemsInCheckout);
		// asserts you are purhcasing correct items that displayed in cart are displayed
		// in checkout overview page
		double sumBeforeTax = COP.totalPriceOfSelecteditems();
		System.out.println(COP.priceDisplayed());
		double calculatedTax = COP.calculateTax();
		double sumOfTotalAndTax = sumBeforeTax + calculatedTax;
		double sumOfTotalAndTaxDisplayed = COP.totalPriceWithTaxDisplayed();
		SA.assertEquals(sumOfTotalAndTaxDisplayed, sumOfTotalAndTax);
		//Asserts the total price 
		SA.assertAll();

	}

}
