package com.NateraDemo.util;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.NateraDemo.util.PropertyReader;

public class DriverTestCase
{
	enum DriverType
	{Firefox,Chrome,IE}

	protected WebDriver driver;
	public String browser=null;
	public String ApplicationURL=null;

	@BeforeClass
	public void setUp() throws Exception
	{
		PropertyReader propertyReader = new PropertyReader();
		FirefoxProfile profile = new FirefoxProfile();
		String downloadFilepath = getFilePath()+File.separator+"Downloads"+File.separator;

		browser =propertyReader.readApplicationFile("Browser");
		ApplicationURL = propertyReader.readApplicationFile("ApplicationURL");
		
		if(DriverType.Firefox.toString().toUpperCase().equals(browser.toUpperCase()))
		{
			System.setProperty("webdriver.gecko.driver", getFilePath()+File.separator+"Drivers"+File.separator+"geckodriver.exe");
		
			//Download setting
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.dir", downloadFilepath);
			
			//profile.setPreference("browser.download.useDownloadDir", false);
			profile.setPreference("browser.download.manager.showWhenStarting", false);

			profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/vnd.ms-excel, application/x-download, application/msword, application/msexcel, application/xls, application/csv, application/ris, text/csv, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");

			driver = new FirefoxDriver(profile);
		}
		else
			if(DriverType.Chrome.toString().toUpperCase().equals(browser.toUpperCase()))
			{
				System.setProperty("webdriver.chrome.driver", getFilePath()+File.separator+"Drivers"+File.separator+"chromedriver.exe");

				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", downloadFilepath);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", chromePrefs);
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				cap.setCapability(ChromeOptions.CAPABILITY, options);

				driver = new ChromeDriver(cap);
			}
			else
				if(DriverType.IE.toString().toUpperCase().equals(browser.toUpperCase()))
				{
					System.setProperty("webdriver.ie.driver", getFilePath()+File.separator+"Drivers"+File.separator+"IEDriverServer.exe");
					driver = new InternetExplorerDriver();
				}
				else
				{
					System.setProperty("webdriver.gecko.driver", getFilePath()+File.separator+"Drivers"+File.separator+"geckodriver.exe");

					//Download setting
					profile.setPreference("browser.download.dir", downloadFilepath);
					profile.setPreference("browser.download.folderList", 2);
					profile.setPreference("browser.download.panel.shown",false);
					profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/zip");
					profile.setPreference("browser.download.manager.showWhenStarting", false);
					driver = new FirefoxDriver(profile);
				}
		driver.manage().window().maximize();
		driver.get(ApplicationURL);
	}

	@AfterClass
	public void tearDown()
	{
		getWebDriver().quit();
	}

	public WebDriver getWebDriver()
	{
		return driver;
	}

	public String getFilePath()
	{
		String filepath = "";
		final File file = new File("");
		final String absolutePathOfFirstFile = file.getAbsolutePath();
		filepath = absolutePathOfFirstFile.replaceAll("\\\\+", "/");
		return filepath;
	}
}
