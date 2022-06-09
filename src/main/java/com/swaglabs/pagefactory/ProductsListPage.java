package com.swaglabs.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsListPage {
	WebDriver driver;

	public ProductsListPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement get_ProductsPageTitle() throws Exception {
		return driver.findElement(By.xpath("//span[text()='Products']"));
	}

	public WebElement addItemtoCart(String itemName) throws Exception {
		return driver.findElement(By.xpath("//button[@id='add-to-cart-"+itemName+"']"));
	}

	public WebElement removeItemFromCart(String itemName) throws Exception {
		return driver.findElement(By.xpath("//button[@id='remove-"+itemName+"']"));
	}
	
	public WebElement shoppingCartLink()
	{
		return driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
	}
	public WebElement getInventoryPrice(String itemName)
	{
		return driver.findElement(By.xpath("//button[@id='remove-"+itemName+"']//preceding::div[@class='inventory_item_price']"));
	}
		
	}

