package com.testframework.core;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.google.common.collect.ImmutableMap;


/*****************************************************************************************
 * Class Name		: Utilities
 * Description 		: This class contains utilities or common operations 
 * Author 			: QA-Masters
 * Change History	:
 *              Date                     Modified By                  Change Description
 *             *****                      *********                   ***************
 *            22/11/2016                 QA-Masters                    Initial Draft
 *
 ******************************************************************************************/

public class Utilities 
{
	// class variable class variable
	final static String alphaNumerics = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
	final static String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	final static Random rand = new Random();

	// consider using a Map<String,Boolean> to say whether the identifier is being used or not 
	final static Set<String> identifiers = new HashSet<String>();

	/*******************************************************
	 * Function Name: captureScreen
	 * Description : Screenshots are captured in this function
	 ********************************************************/

	public static void captureScreen(String fileName)
	{
		try
		{
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle screenRectangle = new Rectangle(screenSize);
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screenRectangle);
			File tmpFile = new File(fileName);
			ImageIO.write(image, "png", tmpFile);
		}
		catch (Exception ex)
		{
			
		}
	}

	/*******************************************************
	 * Function Name: getDuration
	 * Description : Execution time/duration is captured in this function
	 ********************************************************/

	public static String getDuration(Date start_Date, Date end_Date) 
	{
		long diff = end_Date.getTime() - start_Date.getTime();
		long diffhrs = diff / (60 * 60 * 1000);
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffSecs = diff / (1000) % 60 % 60;
		return diffhrs + " Hrs " + diffMinutes + " Mins " + diffSecs + " secs.";
	}

	

	/**********************************************************************************************
	 * Function Name	: generateRandomStringwithNumerics
	 * Description 	: This function is used for generating random string value 
	 * 					consisting of Characters and numerics 
	 ***********************************************************************************************/

	public static String generateRandomStringwithNumerics()
	{
		StringBuilder builder = new StringBuilder();
		try
		{
			while(builder.toString().length() == 0) 
			{
				for(int i = 0; i < 9; i++) 
				{
					builder.append(alphaNumerics.charAt(rand.nextInt(alphaNumerics.length())));
				}
				if(identifiers.contains(builder.toString())) {
					builder = new StringBuilder();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return builder.toString();
	}

	/**********************************************************************************************
	 * Function Name	: generateRandomStringwithNumerics
	 * Description 	: This function is used for generating random string value 
	 * 					consisting of only Characters 
	 ***********************************************************************************************/

	public static String generateRandomStringwithoutNumerics()
	{
		StringBuilder builder = new StringBuilder();
		try
		{
			while(builder.toString().length() == 0) 
			{
				for(int i = 0; i < 9; i++) 
				{
					builder.append(alphabets.charAt(rand.nextInt(alphabets.length())));
				}
				if(identifiers.contains(builder.toString())) {
					builder = new StringBuilder();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return builder.toString();
	}

	/******************************************************************************************
	 * Function Name: delayFor
	 * Description :  This function is used for delaying or waiting for given amount of time
	 ******************************************************************************************/

	public static void delayFor(long milliSec)
	{
		try {
			Thread.sleep(milliSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/***************************************************************
	 * Function Name: InvokeProcess
	 * Description :  This function is used for invoking a process
	 ***************************************************************/

	public static void InvokeProcess(String ProcessName, String args) 
	{
		try {
			ProcessBuilder p = new ProcessBuilder();
			p.command(ProcessName, args);
			p.start();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
    private static WebDriver driver;
	
	public static WebDriver getSeleniumWebDriver()
	{
		if (driver == null)
		{
			
			return null;
		}
		return driver;
	}
	
	
	
	public  static WebDriver initbrowser(String br) 
	{
		 if (br.equalsIgnoreCase("firefox")) 
			{
				if(System.getProperty("os.name").startsWith("Windows"))
				{
					System.setProperty("webdriver.gecko.driver", "resource/WindowsDrivers/GeckoDriver/geckodriver.exe");
				}
				else
				{
					System.setProperty("webdriver.gecko.driver", "resource/linuxdrivers/geckodriver");
					String Xport = System.getProperty("Importal.xvfb.id",":1");
					FirefoxBinary ffbinary = new FirefoxBinary();
					ffbinary.setEnvironmentProperty("DISPLAY", Xport);

				}
				DesiredCapabilities caps = DesiredCapabilities.firefox();
				caps.setCapability("marionette", true);
				caps.setCapability("browserName","firefox");
				caps.setCapability("version",56);
				caps.setCapability("platform","ANY");
				driver = new FirefoxDriver();
			

	  // If browser is IE, then do this	  

	  }
	  else if (br.equalsIgnoreCase("ie")) { 

		  // Here I am setting up the path for my IEDriver

		  System.setProperty("webdriver.ie.driver", "resource/drivers/IEDriverServer.exe");

		  driver = new InternetExplorerDriver();

	  } 
	  else if (br.equalsIgnoreCase("chrome")) 
		{
			File file;
			if(System.getProperty("os.name").startsWith("Windows"))
			{
				file = new File("resource/WindowsDrivers/ChromeDriver/chromedriver.exe");
			}
			else 
			{
				file = new File("resource/linuxdrivers/chromedriver");
				ChromeDriverService chromeDriverService = new ChromeDriverService.Builder().usingDriverExecutable(file).usingAnyFreePort().withEnvironment(ImmutableMap.of("DISPLAY", ":1")).build();
				try {
					chromeDriverService.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				driver = new ChromeDriver(chromeDriverService);
			}
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("test-type");
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			caps.setCapability("ignoreProtectedModeSettings", true);
			driver = new ChromeDriver(caps);
		}
		 return driver;
	}
}

