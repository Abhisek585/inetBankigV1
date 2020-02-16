package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.inetbanking.utilities.ReadConfig;

public class BaseClass {
	ReadConfig readConfig=new ReadConfig();
	public String baseUrl=readConfig.getApplicationUrl();
	public String userName=readConfig.getUserName();
	public String passWord=readConfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass	
	public void setUp(String Browser){
		/*//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Abhisek\\workspace\\HybridTestAutomationFramework\\inetBankigV1\\Drivers\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", readConfig.getchromepath());
		driver= new ChromeDriver();*/
		if(Browser.equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", readConfig.getchromepath());
			driver= new ChromeDriver();
		}
		else if(Browser.equals("Firefox")){
			System.setProperty("webdriver.gecko.driver", readConfig.getfirefoxpath());
			driver= new FirefoxDriver();
		}
		
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		
		logger=Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");
	
	}
	@AfterClass
		public void TearDown(){
		driver.quit();
	
	}
	
	public void takeScreenshot(WebDriver driver,String Tname) throws IOException{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File des=new File(System.getProperty("user.dir")+"/Screenshots/"+Tname+".png");
		FileUtils.copyFile(src, des);
		System.out.println("screenshot taken");
	}
	public String randomestring(){
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return generatedstring;
		
	}
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
}
