package com.swaglabs.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutOverViewPage {
	WebDriver driver;

	public CheckoutOverViewPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement get_Finish() throws Exception {
		return driver.findElement(By.xpath("//button[@id='finish']"));
	}

	public WebElement get_ConfirmationMessage() throws Exception {
		return driver.findElement(By.xpath("//h2[text()='THANK YOU FOR YOUR ORDER']"));
	}

		
	}

