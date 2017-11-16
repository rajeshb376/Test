package com.testframework.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.testframework.core.Utilities;

public class HomePage {
	
	
	@FindBy(id="nav-your-amazon")
	private WebElement lnk_login;
		
	@FindBy(id = "ap_email")
	private WebElement txt_email;
	
	@FindBy(xpath = ".//*[@id='ap_password']")
	private WebElement txt_password;
	
	@FindBy(id = "signInSubmit")
	private WebElement btn_Login;
	
	@FindBy(id = "twotabsearchtextbox")
	private WebElement lnk_searchbar;
	
	@FindBy(id = "quartsPagelet")
	private WebElement txt_span;
	
	
	@FindBy(id = "nav-link-yourAccount")
	private WebElement lnk_orders;
	
	@FindBy(id = "nav-item-signout")
	private WebElement lnk_signout;
	
	
	static final Logger logger = LoggerFactory.getLogger(HomePage.class);




	public boolean login(String email , String password)	
	{
		boolean flag = false;
		try{
			
			Utilities.delayFor(5000);
			
				logger.info("********Application Launched Sucessfully*****");
				lnk_login.click();
				Utilities.delayFor(5000);
			    txt_password.sendKeys(password);
				txt_email.sendKeys(email);												
				btn_Login.click();
				Utilities.delayFor(3000);
				if(lnk_searchbar.isDisplayed())		
				flag = true;			
		}
		
		catch(Exception e)
		{
			logger.info("********Application login Failure*****");
			flag = false;
		}
	return flag;
	}
	
	public boolean landingpage(String domain)	
	{
		boolean flag = false;
		try{
			if(lnk_searchbar.isDisplayed())		
			{
				lnk_searchbar.sendKeys(domain);
				lnk_searchbar.sendKeys(Keys.ENTER);
				//verify span text
				Utilities.delayFor(5000);
				
				if(txt_span.getText().contains("Showing most relevant results"))
			     {
					System.out.println("Found search Element");
				 logger.info("*******Element found on search page*******");
				 flag = true;
			     }
				 
			}   
			else{
				 logger.info("*******Element not present in search page*******");
				 flag = false;
			}
			
			
			Actions actions = new Actions(Utilities.getSeleniumWebDriver());
			actions.moveToElement(lnk_orders).build().perform();
			Utilities.delayFor(2000);
			if(lnk_signout.isDisplayed())
			{
				lnk_signout.click();
				System.out.println("Application signout");
			}
			
			
			
			
		}	
		catch(Exception e)
		{
			logger.info("*******Element are not present on search page*******");
			 flag = false;
		}
	return flag;
	}	
}
