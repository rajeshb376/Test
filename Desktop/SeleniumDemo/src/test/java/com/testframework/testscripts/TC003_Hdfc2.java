package com.testframework.testscripts;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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
import com.testframework.testdata.ParseFullCSVExample;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class TC003_Hdfc2 extends ExtentManager{
     
	public WebDriver driver;
	private static String crdateTime = new SimpleDateFormat("yyyyMMddHHmm'.html'").format(new Date());
	 @Parameters("browser") 
	  @BeforeClass	
	  @Step("Browser Launch")
	 
	  // Passing Browser parameter from TestNG xml
	  public void launchURL(String browser) throws IOException {
	  // Extent Initialization
	  extent = ExtentManager.GetExtent();
	  Logger logger=Logger.getLogger("TC003_Hdfc2");
	  // Browser Initialization
	  driver =   Utilities.initbrowser(browser);
      br = browser;	
	  //Launch URL
	  driver.get(Configuration.getProperty("SiteURl3"));
	  PropertyConfigurator.configure("./resource/log4j.properties");
	  driver.manage().window().maximize();
	  logger.info("Application is Launched");
	  }
	
	 @Test()
	 
	 @Step("HDFC ERGO Plan Selection")
	 @Description("HDFC ERGO Premium Calculate")
	 public void HDFCLogin() throws Exception {
		 /****************Read CSV Code *************************/
		
		 ParseFullCSVExample csv = new ParseFullCSVExample();
		 String[] retrow =  csv.getcsvData("./testdata/CSV/Test2.csv","TC01");
		 //CSV Header column names
		 //TCID,Insurance,date,location,manufacturer,vehiclemodel,name,mobile,email
		 String Insurance = retrow[1];
		 String date = retrow[2];
		 String location = retrow[3];
		 String manufacturer = retrow[4];
		 String vehiclemodel = retrow[5];
		 String name = retrow[6];
		 String mobile = retrow[7];
		 String email = retrow[8];
		 
		 test = extent.startTest("HDFC_ERGO","Calculate Premium - " + "[ " + br + " ]");
		  HdfcErgoHomePage hp = PageFactory.initElements(Utilities.getSeleniumWebDriver(), HdfcErgoHomePage.class);		  
		  if(hp.SelectPlan(Insurance, date, location, manufacturer, vehiclemodel, name, mobile, email) == true)
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
