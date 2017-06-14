package com.NateraDemo.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;


public abstract class DriverHelper
{
	// Define objects
	protected WebDriver driver;

	// Declare objects
	public DriverHelper(WebDriver webdriver)
	{
		driver = webdriver;
	}

	// Return web driver object
	public WebDriver getWebDriver() 
	{
		return driver;  
	}

	// Print message on screen
	public void log(String logMsg) 
	{
		System.out.println(logMsg);
	}

	// Handle locator type
	public By byLocator(String locator) 
	{
		By result = null;

		if (locator.startsWith("//")) 
		{
			result = By.xpath(locator);
		} 
		else if (locator.startsWith("css=")) 
		{
			result = By.cssSelector(locator.replace("css=", ""));
		} 
		else if (locator.startsWith("#")) 
		{
			result = By.name(locator.replace("#", ""));
		} 
		else if (locator.startsWith("link=")) 
		{
			result = By.linkText(locator.replace("link=", ""));
		} 
		else {
			result = By.id(locator.replace("id=", ""));
		}
		return result;
	}

	// Assert element present
	public Boolean isElementPresent(String locator) 
	{
		Boolean result = false;
		try 
		{
			getWebDriver().findElement(byLocator(locator));
			result = true;
		} 
		catch (Exception ex) 
		{
		}
		return result;
	}

	// Wait for element present
	public void waitForElementPresent(String locator, int timeout) 
	{
		for (int i = 0; i < timeout; i++) 
		{
			if (isElementPresent(locator)) 
			{
				break;
			}

			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	// Wait for element displayed
		public void waitForElementDisplayed(String locator, int timeout) 
		{
			for (int i = 0; i < timeout; i++) 
			{
				if (getWebDriver().findElement(By.xpath(locator)).isDisplayed()) 
				{
					break;
				}

				try 
				{
					Thread.sleep(1000);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}

	// Handle click action
	public void clickOn(String locator) 
	{
		this.waitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"
				+ locator + " Not found");
		WebElement el = getWebDriver().findElement(byLocator(locator));
		el.click();
	}

	// Handle send keys action
	public void sendKeys(String locator, String  str) 
	{
		this.waitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :" + locator + " Not found");
		WebElement el = getWebDriver().findElement(byLocator(locator));
		el.clear();
		el.sendKeys(str);
	}

	// Store text from a locatorl
	public String getText(String locator) {
		waitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"
				+ locator + " Not found");
		String text = getWebDriver().findElement(byLocator(locator)).getText();
		return text;
	}

	// Get attribute value
	public String getAttribute(String locator, String attribute) 
	{
		waitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"
				+ locator + " Not found");
		String text = getWebDriver().findElement(byLocator(locator))
				.getAttribute(attribute);
		return text;
	}

	public Integer getXpathCount(String locator)
	{
		waitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"
				+ locator + " Not found");
		int a = driver.findElements(By.xpath(locator)).size();
		return a;
	}

	public void waitForWorkAroundTime(int timeout)
	{
		try 
		{
			Thread.sleep(timeout);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

	public void verifyTitle(String title)
	{
		String actualTitle=getWebDriver().getTitle();
		Assert.assertTrue(actualTitle.contains(title));
	}
	
	public void mouseHover(String locator)
	{
        WebElement el = getWebDriver().findElement(By.xpath(locator));
        Actions builder = new Actions(getWebDriver());
        builder.moveToElement(el).build().perform();
	}
	
	public int getElementSize(String locator)
	{
		return getWebDriver().findElements(By.xpath(locator)).size();
	}
}