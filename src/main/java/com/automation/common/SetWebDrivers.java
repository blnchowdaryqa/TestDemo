package com.automation.common;

import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class SetWebDrivers {
	public static InputStream systemPropertyInputStream;
	public static Logger logger = Logger.getLogger(SetWebDrivers.class.getName());
	public static Properties symProPath = new Properties();
	public static WebDriver driver;
	public static String browser;

	@Parameters("browser")
	@BeforeSuite
	public void preSetUpSuite(String brwser) {
		browser = brwser;
	}

	@BeforeTest
	public void setWebDrivers() throws Exception {
		if (browser.trim().equalsIgnoreCase("Chrome")) {
			if(OSDetector().equalsIgnoreCase("windows")) {
				System.setProperty("webdriver.chrome.driver",SetWebDrivers.class.getResource("/thirdparty/chromedriver.exe").getFile());	
			}
			else if (OSDetector().equalsIgnoreCase("mac")) {
				System.setProperty("webdriver.chrome.driver","/usr/local/chromedriver_mac");
			}
			driver = new ChromeDriver();
		}
		
		else if (browser.trim().equalsIgnoreCase("Firefox"))
		{
			if(OSDetector().equalsIgnoreCase("windows")) 
			{
				System.setProperty("webdriver.gecko.driver",SetWebDrivers.class.getResource("/thirdparty/geckodriver.exe").getFile());
			}
			else if (OSDetector().equalsIgnoreCase("Mac")) 
			{
			System.setProperty("webdriver.gecko.driver", "/usr/local/geckodriver_mac");
			}
			driver = new FirefoxDriver();
		}
	}
	
	@AfterTest
	public void kilDriver(){
		driver.quit();
	}

	
	
	public static String OSDetector() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			return "Windows";
		} else if (os.contains("nux") || os.contains("nix")) {
			return "Linux";
		} else if (os.contains("mac") || os.contains("darwin")) {
			return "Mac";
		} else if (os.contains("sunos")) {
			return "Solaris";
		} else {
			return "Other";
		}
	}

}
