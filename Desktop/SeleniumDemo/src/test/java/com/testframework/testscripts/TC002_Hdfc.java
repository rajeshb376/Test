/*package com.testframework.testscripts;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.testframework.core.Configuration;
import com.testframework.core.Utilities;
import com.testframework.pages.HdfcErgoHomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class TC002_Hdfc extends ExtentManager{

	public WebDriver driver;
	 @Parameters("browser") 
	  @BeforeClass	
	  @Step("Browser Launch")
	  // Passing Browser parameter from TestNG xml
	  public void launchURL(String browser) throws IOException {
	  // Extent Initialization
	  extent = ExtentManager.GetExtent();
	  // Browser Initialization
	  driver =   Utilities.initbrowser(browser);
      br = browser;	
	  //Launch URL
	  driver.get(Configuration.getProperty("SiteURl3"));
	  driver.manage().window().maximize();
	  }
	
	 @Test()
	 @Step("HDFC ERGO Plan Selection")
	 @Description("HDFC ERGO Premium Calculate")
	  public void HDFCLogin() throws InterruptedException {
		  test = extent.startTest("HDFC_ERGO","Calculate Premium - " + "[ " + br + " ]");
		  HdfcErgoHomePage hp = PageFactory.initElements(Utilities.getSeleniumWebDriver(), HdfcErgoHomePage.class);		  
		  if(hp.SelectPlan() == true)
			  test.log(LogStatus.PASS, "Plan Selection Sucess");
		  else{
			  test.log(LogStatus.FAIL, "Plan Selection fail");
		  }		  
	  }
	 @Step("Check of calculation result and report generation")
	  @AfterClass 
	  public void afterTest() {
		 // driver.close();
		  extent.flush();
		  //extent.close();
		  driver.quit();
         System.out.println("Test Finished");
		}	
}
*/