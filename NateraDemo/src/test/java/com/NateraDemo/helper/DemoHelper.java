package com.NateraDemo.helper;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.NateraDemo.locators.LocatorReader;
import com.NateraDemo.util.DriverHelper;

public class DemoHelper extends DriverHelper
{
	LocatorReader locatorReader;
	String locator=null;
	
	public DemoHelper(WebDriver driver)
	{
		super(driver);
		locatorReader = new LocatorReader("DemoLocator.xml");
	}
	
	public void logInToApplication(String email,String password)
	{
		enterText("UserEmail", email);
		enterText("UserPassword", password);
		click("SignInButton");
	}
	
	public void click(String element)
	{
		locator = locatorReader.getLocator(element);
		waitForElementPresent(locator, 50);
		waitForElementDisplayed(locator, 50);
		clickOn(locator);
	}
	
	public void enterText(String field, String text)
	{
		locator = locatorReader.getLocator(field);
		waitForElementPresent(locator, 50);
		waitForElementDisplayed(locator, 50);
		sendKeys(locator, text);
	}
	
	public void verifyElementIsAvailable(String element)
	{
		locator = locatorReader.getLocator(element);
		waitForWorkAroundTime(5000);
		Assert.assertTrue(isElementPresent(locator));
	}
	
	public void verifyPageTitle(String title)
	{
		waitForWorkAroundTime(2000);
		verifyTitle(title);
	}
	
	public void verifyUserLoggedIn()
	{
		locator = locatorReader.getLocator("HiUserName");
		waitForElementPresent(locator, 50);
		waitForElementDisplayed(locator, 50);
		verifyElementIsAvailable("HiUserName");
	}
	
	public void verifyUserLoggedOut()
	{
		locator = locatorReader.getLocator("SignInLink");
		waitForElementPresent(locator, 50);
		waitForElementDisplayed(locator, 50);
		verifyElementIsAvailable("SignInLink");
	}
	
	public void verifyMessageSent()
	{
		locator = locatorReader.getLocator("SuccessMessage");
		waitForElementPresent(locator, 50);
		waitForElementDisplayed(locator, 50);
		verifyElementIsAvailable("SuccessMessage");
	}
}