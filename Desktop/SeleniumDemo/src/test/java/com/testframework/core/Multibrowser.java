/*package com.testframework.core;


import java.io.IOException;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Parameters;

import org.testng.annotations.Test;


public class Multibrowser {

public WebDriver driver;

  @Parameters("browser")

  @BeforeClass

  // Passing Browser parameter from TestNG xml

  public void beforeTest(String browser) throws IOException {

  // If the browser is Firefox, then do this
	 WebDriver driver =   Utilities.initbrowser(browser);
     System.out.println(driver);
  // Doesn't the browser type, lauch the Website

  driver.get("http://www.store.demoqa.com"); 

  }

  // Once Before method is completed, Test method will start

  @Test
  public void login() throws InterruptedException {

	  System.out.println("Test Login");
	driver.findElement(By.xpath(".//*[@id='account']/a")).click();

    driver.findElement(By.id("log")).sendKeys("testuser_1");

    driver.findElement(By.id("pwd")).sendKeys("Test@123");

    driver.findElement(By.id("login")).click();

	}  

  @AfterClass public void afterTest() {

		driver.quit();

	}

}*/