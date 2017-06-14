package com.NateraDemo.scripts;

import org.testng.annotations.Test;

import com.NateraDemo.helper.DemoHelper;
import com.NateraDemo.util.DriverTestCase;
import com.NateraDemo.util.PropertyReader;
public class Demo2 extends DriverTestCase
{
	String email=null;
	String password=null;
	DemoHelper demoHelper=null;
	PropertyReader propertyReader=null;

	@Test
	public void demo1() throws Exception
	{
		demoHelper = new DemoHelper(getWebDriver());
		propertyReader = new PropertyReader();
		
		email = propertyReader.readApplicationFile("Email");
		password = propertyReader.readApplicationFile("Password");
		System.out.println("SECOND_CLASS");		
		//Verify Title
		demoHelper.verifyPageTitle("My Natera - Patient Portal");
		
		//Verify Sign In Link
		demoHelper.verifyElementIsAvailable("SignInLink");

		//Click on Sign In button
		demoHelper.click("SignInLink");

		//Verify fields.
		demoHelper.verifyElementIsAvailable("UserEmail");

		demoHelper.logInToApplication(email,password);

		//Verify user login successfully
		demoHelper.verifyUserLoggedIn();

		//Click on Contact menu
		demoHelper.click("Contact");

		//Enter first name
		demoHelper.enterText("FirstName", "Naved");

		//Enter last name
		demoHelper.enterText("LastName", "Ali");

		//Enter email
		demoHelper.enterText("Email",email);

		//Enter Zip code
		demoHelper.enterText("Zip", "11225");

		//Enter message
		demoHelper.enterText("Message", "This is a Demo Message to Contact");

		//Click on Submit
		demoHelper.click("Submit");

		//Verify message is sent successfully.
		demoHelper.verifyMessageSent();

		//Click on 'Hi (username)'
		demoHelper.click("HiUserName");

		//Click o SignOut
		demoHelper.click("SignOut");

		//Verify user log out successfully
		demoHelper.verifyUserLoggedOut();

	}
}
