package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass{
	@Test
	public void loginTest() throws IOException{
		driver.get(baseUrl);
		logger.info("url is opened");
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(userName);
		logger.info("username provided ");
		lp.setPassword(passWord);
		logger.info("password provided");
		lp.clickSubmit();
		System.out.println(driver.getTitle());
		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")){
			Assert.assertTrue(true);
			logger.info("login test case passed");
		}else{
			takeScreenshot(driver,"loginTest");
			logger.info("login test case failed");
			Assert.assertTrue(false);
			
		}
	}

}
