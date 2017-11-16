package com.testframework.pages;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


import com.relevantcodes.extentreports.LogStatus;
import com.testframework.core.Utilities;
import com.testframework.testscripts.ExtentManager;

import io.qameta.allure.Step;

public class HdfcErgoHomePage extends ExtentManager{

	
	
	@FindBy(id="ui-id-1")
	private WebElement lnk_buynow;

	@FindBy(id="middle_ddlProdcuts")
	private WebElement lnk_selectplan;

	@FindBy(id="middle_hrefBuyOnline")
	private WebElement btn_go;

	@FindBy(id="rblPolicyType_0")
	private WebElement rbtn_renewcar;
	
	@FindBy(id="divRegDatetext")
	private WebElement clk_reg;
	
	@FindBy(id="HeaderControl_slogan")
	private WebElement lbl_header;
	
	@FindBy(id="rdoVehicleOwnedByInd")
	private WebElement rbtn_ind;
		
	@FindBy(id="rdoEffDLYes")
	private WebElement rbtn_dlicence;
	
	@FindBy(id="txtRegDate")
	private WebElement txt_date;
	
	@FindBy(id="txtLocation")
	private WebElement txt_txtLocation;
		
	@FindBy(id="ddlManufacturer")
	private WebElement dd_manufacturer;
		
	@FindBy(id="ddlModel")
	private WebElement dd_Model;
		
	@FindBy(id="txtSumInsured")
	private WebElement txt_SumInsured;
		
	@FindBy(id="txtName")
	private WebElement txt_Name;
	
	@FindBy(id="txtMobileNo")
	private WebElement txt_MobileNo;
		
	@FindBy(id="txtEmail")
	private WebElement txt_Email;
		
	@FindBy(id="chkTerms")
	private WebElement chk_Terms;
	
	@FindBy(id="imgbtnCalculate")
	private WebElement lbl_Calculate;
		
	@FindBy(id="ContentPlaceHolder1_divLessNetPrm")
	private WebElement lbl_nerpremium;
	
	
	@FindBy(id="ContentPlaceHolder1_lblMorePremMsg")
	private WebElement lbl_premiumvalue;
	
	Logger logger = Logger.getLogger("HomePage");
	
	@Step("Select Plan")
	public boolean SelectPlan(String insurance , String date , String location , String manufacturer , String vehicleModel,String name,String mobile,String email ) 
	{	
		/*ExtentManager em = new ExtentManager();
		em.test.info("Calculated amount is");*/
		boolean flag = false;
		try{
			Utilities.delayFor(5000);			
			if(lnk_buynow.isDisplayed()){
			logger.info("********Application Launched Sucessfully*****");
			lnk_buynow.click();
			Utilities.delayFor(2000);			
			Utilities.delayFor(2000);
			Select sc = new Select(lnk_selectplan);
			sc.selectByVisibleText(insurance);
			logger.info("********Plan Selected Sucessfully*****");
			Utilities.delayFor(1000);
			btn_go.click();
			ArrayList<String> newTab = new ArrayList<String>(Utilities.getSeleniumWebDriver().getWindowHandles());
			Utilities.getSeleniumWebDriver().switchTo().window(newTab.get(1));
			Utilities.delayFor(2000);
			rbtn_renewcar.click();
			logger.info("********Renew Motor Policy from other Insurer*****");
			txt_date.sendKeys(date);
			clk_reg.click();
			Utilities.delayFor(2000);
			txt_txtLocation.sendKeys(location);
			Utilities.delayFor(3000);
			txt_txtLocation.clear();
			txt_txtLocation.sendKeys(location);
			Utilities.delayFor(5000);
			txt_txtLocation.sendKeys(Keys.ARROW_DOWN);
			Utilities.delayFor(2000);
			txt_txtLocation.sendKeys(Keys.ENTER);
			Utilities.delayFor(2000);
			
			Select a = new Select(dd_manufacturer);
			a.selectByVisibleText(manufacturer);
			
			Utilities.delayFor(5000);
			Select model = new Select(dd_Model);
			model.selectByVisibleText(vehicleModel);
			Utilities.delayFor(5000);
			txt_Name.sendKeys(name);
			txt_MobileNo.sendKeys(mobile);
			txt_Email.sendKeys(email);
			chk_Terms.click();
			lbl_Calculate.click();
			Utilities.delayFor(5000);
			if(lbl_nerpremium.getText().contains("Net Premium Amount")){
			lbl_premiumvalue.getText();
			logger.info("********Calculated amount is " + lbl_premiumvalue.getText());	
			test.log(LogStatus.INFO, "Calculated amount is" + lbl_premiumvalue.getText());
			}else{
				logger.info("********Calculation failure*****");
			}
			
			flag = true;			
		}
		}
		catch(Exception e)
		{
			logger.info("********Application Launchs Failure*****");
			flag = false;
		}
		return flag;
	}
}
