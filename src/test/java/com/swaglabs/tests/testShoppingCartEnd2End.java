package com.swaglabs.tests;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.automation.common.SetWebDrivers;
import com.swaglabs.pagefactory.CheckoutInfoPage;
import com.swaglabs.pagefactory.CheckoutOverViewPage;
import com.swaglabs.pagefactory.LoginPage;
import com.swaglabs.pagefactory.ProductsListPage;
import com.swaglabs.pagefactory.YourCartPage;

public class testShoppingCartEnd2End {
	
	private static Logger logger = Logger.getLogger(testShoppingCartEnd2End.class.getName());
	
	@BeforeClass
	public void startBrowser() {
		SetWebDrivers.driver.get("https://www.saucedemo.com/");
    }
	@DataProvider(name = "addProducts")
	public static Object[][] addItemsToCart() {
		return new Object[][] { { "standard_user","secret_sauce","Products","Sauce Labs Backpack","James","Smith","67263"}};}

	/**
	 * This Scenario is to validate Adding an item to a shopping cart and 
	 * Placing an order by providing checkout info and validate if the order was placed successfully 
	 */
	@Test (dataProvider = "addProducts")
	public void validateAdd_RemoveProductsInCart(String userName, String password, String expectedTitle, String itemName, String firstName, String lastName, String pinCode) throws Exception
	{
		LoginPage loginpage = new LoginPage(SetWebDrivers.driver);
		ProductsListPage products = new ProductsListPage(SetWebDrivers.driver);
		YourCartPage cart = new YourCartPage(SetWebDrivers.driver);
		CheckoutInfoPage checkoutInfo = new CheckoutInfoPage(SetWebDrivers.driver);
		CheckoutOverViewPage checkoutOverView = new CheckoutOverViewPage(SetWebDrivers.driver);
		String prodictPageTitle=loginpage.loginToSwagLabs(userName, password);
		System.out.println("prodictPageTitle : "+prodictPageTitle);		
		logger.info("prodictPageTitle : "+prodictPageTitle);
		itemName = itemName.replaceAll(" ", "-");
		itemName = itemName.toLowerCase();
		try
		{
		products.addItemtoCart(itemName).click();
		}
		catch (Exception e) {
			products.removeItemFromCart(itemName).click();
			products.addItemtoCart(itemName).click();
		}
		String itemPriceInListpage=products.getInventoryPrice(itemName).getText();	
		products.shoppingCartLink().click();
		String ItemPriceinCart=products.getInventoryPrice(itemName).getText();
		Assert.assertEquals(itemPriceInListpage,ItemPriceinCart,"Item Price Mismatch from Shopping page to Cart");
		logger.info("Item Price In Shopping List : "+itemPriceInListpage);
		logger.info("Item Price In Cart : "+ItemPriceinCart);
		cart.get_CheckOutButton().click();
		checkoutInfo.get_FirstNameField().sendKeys(firstName);
		checkoutInfo.get_LastNameField().sendKeys(lastName);
		checkoutInfo.get_PostalCodeField().sendKeys(pinCode);
		checkoutInfo.get_ContinueButton().click();
		checkoutOverView.get_Finish().click();
		Assert.assertEquals(checkoutOverView.get_ConfirmationMessage().getText(), "THANK YOU FOR YOUR ORDER");
		
	}
		
	
	
	@AfterClass
	public void tearDown()
	{
		SetWebDrivers.driver.quit();
	}

}
