package com.qa.hs.keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Base {

	
	public WebDriver driver;
	public Properties prop;
	 public WebDriver init_driver(String browsername) {
		 if(browsername.equals("chrome")) {
			 System.setProperty("webdriver.chrome.driver", "C:\\Users\\Kushan\\Desktop\\Selenium Setup\\SeleniumDrivers\\ChromeDriver\\chromedriver.exe");
			 if(prop.getProperty("headless").equals("yes")) {
				 ChromeOptions options = new ChromeOptions();
				 options.addArguments("--headless");
				 driver = new ChromeDriver(options);
			 }
			 else {
				 driver = new ChromeDriver();
			 }
		 }
		 else if(browsername.equals("firefox")) {
			 System.setProperty("webdriver.gecko.driver", "C:\\Users\\Kushan\\Desktop\\Selenium Setup\\Gekodriver\\geckodriver-v0.26.0-win64\\geckodriver.exe");
			 DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			 capabilities.setCapability("marionette",true);
			 driver = new FirefoxDriver(capabilities);
		 }
		 return driver;
	 }
	 
	 public Properties init_properties() {
		 prop = new Properties();
		 try {
			FileInputStream ip = new FileInputStream("C:\\Users\\Kushan\\eclipse-workspace\\KeywordDrivenHubspot.com\\src\\main\\java\\com\\qa\\hs\\keyword\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return prop;
	 }
}
