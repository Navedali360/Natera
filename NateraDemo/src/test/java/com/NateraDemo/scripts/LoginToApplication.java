package com.NateraDemo.scripts;

import org.testng.annotations.Test;

import com.NateraDemo.helper.DemoHelper;
import com.NateraDemo.util.DriverTestCase;
import com.NateraDemo.util.PropertyReader;
public class LoginToApplication extends DriverTestCase
{
	String email=null;
	String password=null;
	DemoHelper demoHelper=null;
	PropertyReader propertyReader=null;

	@Test
	public void loginToApplication() throws Exception
	{
		demoHelper = new DemoHelper(getWebDriver());
		propertyReader = new PropertyReader();
		
		System.out.println("Login To Application with Invalid Credentials");
		
		email = propertyReader.readApplicationFile("Email");
		password = propertyReader.readApplicationFile("Password");
		
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
		
		//Verify user login successfully
		demoHelper.verifyUserLoggedIn();

	}
}
