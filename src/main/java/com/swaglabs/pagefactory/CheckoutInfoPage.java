package com.swaglabs.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutInfoPage {
	WebDriver driver;

	public CheckoutInfoPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement get_FirstNameField() throws Exception {
		return driver.findElement(By.xpath("//input[@id='first-name']"));
	}

	public WebElement get_LastNameField() throws Exception {
		return driver.findElement(By.xpath("//input[@id='last-name']"));
	}

	public WebElement get_PostalCodeField() throws Exception {
		return driver.findElement(By.xpath("//input[@id='postal-code']"));
	}
	
	public WebElement get_ContinueButton()
	{
		return driver.findElement(By.xpath("//input[@id='continue']"));
	}
		
	}

