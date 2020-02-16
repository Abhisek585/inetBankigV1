package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {
	
	@Test(dataProvider="LoginData")
	public void LoginDDT(String user,String Pass) throws IOException{
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		logger.info("username provided ");
		lp.setPassword(Pass);
		logger.warn("password provided");
		lp.clickSubmit();
		
		if(isAlertPresent()==true){
			
			driver.switchTo().alert().accept();
			takeScreenshot(driver,"LoginDDT");
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);	
			logger.info("invalid credential");
			
			
			}
		
		
		else if(isAlertPresent()==false){
			Assert.assertTrue(true);
			logger.info("valid credential");
			lp.clicklogout();
			takeScreenshot(driver,"LoginDDT");
			driver.switchTo().alert().accept();
			
			driver.switchTo().defaultContent();
			
			
		}
		
	}
	
	public boolean isAlertPresent(){
		try{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e){
			return false;
		}
	}
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/TestData.xlsx";
		
		int rowcount=XLUtils.getRowCount(path, "Sheet1");
		int columncount=XLUtils.getCellCount(path, "Sheet1", 1);
		String loginData[][]=new String[rowcount][columncount];
		
		for(int i=1;i<=rowcount;i++){
			for(int j=0;j<columncount;j++){
				
				loginData[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
				
				}
			}
		return loginData;
	}
	
	

}
