package com.testframework.testscripts;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.testframework.core.Configuration;
import com.testframework.core.Utilities;
import com.testframework.pages.HomePage;
import com.testframework.testdata.TestDataProvider;

public class TC001_Amazon extends ExtentManager{
	public WebDriver driver;
	@Parameters("browser")
	@BeforeClass	
	// Passing Browser parameter from TestNG xml
	public void launchURL(String browser) throws IOException {
		// Extent Initialization
		  extent = ExtentManager.GetExtent();
		  // Browser Initialization
		  driver =   Utilities.initbrowser(browser);
		  //incase of sigle browser is used for testing entire app uncomment below line
		   // extent.addSystemInfo("Browser",browser);
	      br = browser;	
		  //Launch URL
		  driver.get(Configuration.getProperty("SiteURl1"));
		  driver.manage().window().maximize();
		  
	}

	@Test(dataProvider="TestData", dataProviderClass=TestDataProvider.class)
	public void AmazonLogin(HashMap<String, String> testData) throws InterruptedException {
		test = extent.startTest("Amazon", "Verify Login - " + "[ " + br + " ]");
		HomePage hp = PageFactory.initElements(Utilities.getSeleniumWebDriver(), HomePage.class);

		String email = testData.get("Email");
		String password = testData.get("Password");
		String domain = testData.get("Domain");

		if(hp.login(email, password))
			test.log(LogStatus.PASS, "Login Sucess");
		else{			 
			test.log(LogStatus.FAIL, "Test Login fail");
		}
		if(hp.landingpage(domain))
			test.log(LogStatus.PASS, "Verification Sucessfull");
		else{
			test.log(LogStatus.FAIL, "Test Verification fail");
		}
		driver.get(Configuration.getProperty("SiteURl1"));
	}
	@AfterClass 
	public void afterTest() {
		// driver.close();
		extent.flush();
		//extent.close();
		driver.quit();
		System.out.println("Test Finished");
	}
}
