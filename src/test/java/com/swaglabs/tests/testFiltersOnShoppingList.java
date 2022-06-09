package com.swaglabs.tests;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.automation.common.SetWebDrivers;
import com.swaglabs.pagefactory.LoginPage;


public class testFiltersOnShoppingList {
	
	private static Logger logger = Logger.getLogger(testFiltersOnShoppingList.class.getName());
	
	@BeforeClass
	public void startBrowser() {
		SetWebDrivers.driver.navigate().to("https://www.saucedemo.com/");
    }
	@DataProvider(name = "FilterBasedOnPriceLowToHigh")
	public static Object[][] priceFilterLoHi() {
		return new Object[][] { { "standard_user","secret_sauce"}};
	}

	/**
	 * This Scenario is to validate The filters on the shopping list page
	 * to validate if the sorting is working based on the filter selection
	 */
	@Test (dataProvider = "FilterBasedOnPriceLowToHigh")
	public void validateSortByPriceLowToHigh(String userName, String password) throws Exception
	{
		LoginPage loginpage = new LoginPage(SetWebDrivers.driver);
		loginpage.loginToSwagLabs(userName, password);
		Select sortOption= new Select(SetWebDrivers.driver.findElement(By.xpath("//select[@class='product_sort_container']")));
		sortOption.selectByValue("lohi");
		List<WebElement> priceList = SetWebDrivers.driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		System.out.println("Number of elements:" +priceList.size());
		for(int i=0;i< priceList.size()-1;i++)
		{
			for(int j=i+1;j<priceList.size();j++)
			{
				String lowerVal=priceList.get(i).getText().replace("$","");
				String greaterVal=priceList.get(j).getText().replace("$","");
				Assert.assertTrue(Double.parseDouble(lowerVal)<=Double.parseDouble(greaterVal));
				 System.out.println("Price List from Low to High:" + priceList.get(i).getText());
				 System.out.println("Price List from Low to High:" + priceList.get(j).getText());
			}	
		}
		}
	
	@DataProvider(name = "FilterBasedOnPriceHighToLow")
	public static Object[][] priceFilterHiLo() {
		return new Object[][] { { "standard_user","secret_sauce"}};
	}

	@Test (dataProvider = "FilterBasedOnPriceHighToLow")
	public void validateSortByPriceHighToLow(String userName, String password) throws Exception
	{
		LoginPage loginpage = new LoginPage(SetWebDrivers.driver);
		loginpage.loginToSwagLabs(userName, password);
		Select sortOption= new Select(SetWebDrivers.driver.findElement(By.xpath("//select[@class='product_sort_container']")));
		sortOption.selectByValue("hilo");
		List<WebElement> priceList = SetWebDrivers.driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		System.out.println("Number of elements:" +priceList.size());
		for(int i=0;i< priceList.size()-1;i++)
		{
			for(int j=i+1;j<priceList.size();j++)
			{
				String lowerVal=priceList.get(i).getText().replace("$","");
				String greaterVal=priceList.get(j).getText().replace("$","");
				Assert.assertTrue(Double.parseDouble(lowerVal)>=Double.parseDouble(greaterVal));
				 System.out.println("Price List from Low to High:" + priceList.get(i).getText());
				 System.out.println("Price List from Low to High:" + priceList.get(j).getText());
			}	
		}
		}
	
	@AfterClass
	public void tearDown()
	{
		SetWebDrivers.driver.quit();
	}

}
