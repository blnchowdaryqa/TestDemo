package com.swaglabs.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.common.SetWebDrivers;

import dev.failsafe.internal.util.Assert;

public class LoginPage {
	WebDriver driver;
ProductsListPage products = new ProductsListPage(SetWebDrivers.driver);
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement get_userName() throws Exception {
		return driver.findElement(By.id("user-name"));
	}

	public WebElement getPassword() throws Exception {
		return driver.findElement(By.id("password"));
	}

	public WebElement getloginButton() throws Exception {
		return driver.findElement(By.id("login-button"));
	}

	public String loginToSwagLabs(String userName, String Password) throws InterruptedException, Exception
	{
	SetWebDrivers.driver.navigate().to("https://www.saucedemo.com/");
	get_userName().clear();
	get_userName().sendKeys("standard_user");
	getPassword().clear();
	getPassword().sendKeys("secret_sauce");
	getloginButton().click();	
	String pageTitle=products.get_ProductsPageTitle().getText();
	return pageTitle;
	}
}
