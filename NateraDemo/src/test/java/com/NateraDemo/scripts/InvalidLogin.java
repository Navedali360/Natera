package com.NateraDemo.scripts;

import org.testng.annotations.Test;

import com.NateraDemo.helper.DemoHelper;
import com.NateraDemo.util.DriverTestCase;
import com.NateraDemo.util.PropertyReader;
public class InvalidLogin extends DriverTestCase
{
	String email=null;
	String password=null;
	DemoHelper demoHelper=null;
	PropertyReader propertyReader=null;

	@Test
	public void invalidLogin() throws Exception
	{
		demoHelper = new DemoHelper(getWebDriver());
		propertyReader = new PropertyReader();
		
		email = propertyReader.readApplicationFile("Email");
		password = propertyReader.readApplicationFile("Password");
		System.out.println("INVALID_LOGIN");
		//Verify Title
		demoHelper.verifyPageTitle("My Natera - Patient Portal");

		//Verify Sign In Link
		demoHelper.verifyElementIsAvailable("SignInLink");

		//Click on Sign In button
		demoHelper.click("SignInLink");

		//Verify fields.
		demoHelper.verifyElementIsAvailable("UserEmail");

		//Enter invalid credentials
		demoHelper.logInToApplication(email,"WrongPassword");
		
		//Verify the validation message
		demoHelper.verifyElementIsAvailable("Alert");

	}
}
