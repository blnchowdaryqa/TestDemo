package com.swaglabs.tests;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.automation.common.SetWebDrivers;
import com.swaglabs.pagefactory.LoginPage;
import com.swaglabs.pagefactory.ProductsListPage;

public class testAddItemsToCartAndVerify {
	
	private static Logger logger = Logger.getLogger(testAddItemsToCartAndVerify.class.getName());
	
	@BeforeClass
	public void startBrowser() {
		SetWebDrivers.driver.get("https://www.saucedemo.com/");
    }
	@DataProvider(name = "addProducts")
	public static Object[][] addItemsToCart() {
		return new Object[][] { { "standard_user","secret_sauce","Products","Sauce Labs Backpack"},
								{"standard_user","secret_sauce","Products","Sauce Labs Bike Light"}};
	}

	/**
	 * This Scenario is to validate Adding an item to cart and verify the items price against shopping list
	 * And removing the item from the cart to see if user is able to remove the item
	 */
	@Test (dataProvider = "addProducts")
	public void validateAdd_RemoveProductsInCart(String userName, String password, String expectedTitle, String itemName) throws Exception
	{
		LoginPage loginpage = new LoginPage(SetWebDrivers.driver);
		ProductsListPage products = new ProductsListPage(SetWebDrivers.driver);
		String prodictPageTitle=loginpage.loginToSwagLabs(userName, password);
		System.out.println("prodictPageTitle : "+prodictPageTitle);		
		logger.info("prodictPageTitle : "+prodictPageTitle);
		Assert.assertEquals(prodictPageTitle.toUpperCase(), expectedTitle.toUpperCase(),"Seems like Login failed, user unable to navigate to home page");
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
		products.removeItemFromCart(itemName);
		Assert.assertTrue(SetWebDrivers.driver.findElements(By.xpath("//span[@class='shopping_cart_badge']")).size() != 0,"Unable to remove the Items Added to cart");
	}
	
	
	@AfterClass
	public void tearDown()
	{
		SetWebDrivers.driver.quit();
	}

}
