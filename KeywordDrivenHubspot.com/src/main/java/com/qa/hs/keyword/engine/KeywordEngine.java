package com.qa.hs.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hs.keyword.base.Base;

public class KeywordEngine {
	public WebDriver driver;
	public Properties prop;
	public static Workbook book;
	public static Sheet sheet;
	public Base baseclass;
	public WebElement ele;

	public final String SCENARIO_SHEET = "C:\\Users\\Kushan\\git\\KeyWordDrivenFWHubSpotApp\\KeywordDrivenHubspot.com\\src\\main\\java\\com\\qa\\hs\\keyword\\scenarios\\hubspot_scenarios.xlsx";

	public void startExecution(String sheetName) {
		FileInputStream file = null;

		try {
			file = new FileInputStream(SCENARIO_SHEET);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {

			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		int k = 0;
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			try {
				String locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();

				String locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
				String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
				String value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();
				System.out.println(value);
				switch (action) {
				case "open browser":
					baseclass = new Base();
					prop = baseclass.init_properties();
					if (value.isEmpty() || value.equals("NA")) {
						driver = baseclass.init_driver(prop.getProperty("browser"));
					} else {
						driver = baseclass.init_driver(value);
						Thread.sleep(3000);
					}
					break;
				case "enter url":
					if (value.isEmpty() || value.equals("NA")) {
						driver.get(prop.getProperty("url"));

					}

					else {
						driver.get(value);
						Thread.sleep(9000);
					}
					break;

				case "quit":
					driver.quit();
					break;
				default:
					break;
				}

				switch (locatorType) {
				case "id":
					ele = driver.findElement(By.id(locatorValue));
					if (action.equalsIgnoreCase("sendkeys")) {
						ele.clear();
						ele.sendKeys(value);
						Thread.sleep(3000);

					} else if (action.equalsIgnoreCase("click")) {
						ele.click();
						Thread.sleep(3000);
					} else if (action.equalsIgnoreCase("getText")) {
						String eletext = ele.getText();
						System.out.println("text from element is: " + eletext);
						Thread.sleep(3000);
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						ele.isDisplayed();
						Thread.sleep(3000);
					}
					locatorType = null;
					break;

				case "xpath":
					Thread.sleep(7000);
					ele = driver.findElement(By.xpath(locatorValue));
		
					if (action.equalsIgnoreCase("sendkeys")) {
						ele.clear();
						ele.sendKeys(value);
						Thread.sleep(3000);
					} else if (action.equalsIgnoreCase("click")) {
						ele.click();
						Thread.sleep(3000);
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						Thread.sleep(3000);
						ele.isDisplayed();
						System.out.println("element is displayed");

					} else if (action.equalsIgnoreCase("getText")) {
						String eletext = ele.getText();
						System.out.println("text from element is: " + eletext);
						Thread.sleep(3000);
					}
					System.out.println("successfully clicked on xpath element");
					locatorType = null;
					break;

				case "className":
					ele = driver.findElement(By.className(locatorValue));
					if (action.equalsIgnoreCase("sendkeys")) {
						ele.clear();
						ele.sendKeys(value);
						Thread.sleep(3000);
					} else if (action.equalsIgnoreCase("click")) {
						ele.click();
						Thread.sleep(3000);
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						ele.isDisplayed();
						Thread.sleep(3000);
					} else if (action.equalsIgnoreCase("getText")) {
						String eletext = ele.getText();
						System.out.println("text from element is: " + eletext);
						Thread.sleep(3000);
					}

					locatorType = null;
					break;
				case "linkText":
					ele = driver.findElement(By.linkText(locatorValue));
					ele.click();
					Thread.sleep(3000);
					locatorType = null;
					break;
				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
