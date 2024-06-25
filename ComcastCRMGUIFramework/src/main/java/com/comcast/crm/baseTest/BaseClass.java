package com.comcast.crm.baseTest;

import java.io.IOException;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.JsonUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebdriverUtility;
import com.comcast.crm.objectRepositoryUtility.HomePage;
import com.comcast.crm.objectRepositoryUtility.LoginPage;
/**
 * @author Pavan kumar Rajbhar
 * 	Base class contains configuration annotations of TestNg for example during the test development every test scripts have some common precondition and
 *  post condition like launching the browser and closing the browser, login/logout for the application, these configurations are repeated in each test script so such type of configurations will be present in base class. 
 */
//@Listeners(com.comcast.crm.listnerutility.ListenerImplementation.class)
public class BaseClass {
	/*create Object*/
	public DataBaseUtility dlib=new DataBaseUtility();
	public PropertyFileUtility pflib=new PropertyFileUtility();
	public JsonUtility jsonlib=new JsonUtility();
	public ExcelUtility elib=new ExcelUtility();
	public JavaUtility jlib=new JavaUtility();
	public WebdriverUtility wlib=new WebdriverUtility();
	public WebDriver driver;
	
	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void configBS() throws SQLException 
	{
		System.out.println("=====Connect To DB, Report Config=====");
		dlib.getDbConnection();
	}
	
	/*
	@Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	public void configBC(String Browser) throws IOException
	{
		System.out.println("==Launch the Browser==");
		String browser=Browser;
		if(browser.contains("chrome")) {
			 driver=new ChromeDriver();
			}else if(browser.contains("firefox")) {
				driver=new FirefoxDriver();
			}else {
				driver=new EdgeDriver();
			}
				sdriver=driver;
	}*/
	
	
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	public void configBC() throws IOException
	{
		System.out.println("==Launch the Browser==");
		String browser=pflib.getDataFromProperties("browser");
		if(browser.contains("chrome")) {
			 driver=new ChromeDriver();
			}else if(browser.contains("firefox")) {
				driver=new FirefoxDriver();
			}else {
				driver=new EdgeDriver();
			}
		
		UtilityClassObject.setDriver(driver);
	}

	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void congifBM() throws IOException
	{
		System.out.println("==LogIn==");
		String url=pflib.getDataFromProperties("url");
		String username=pflib.getDataFromProperties("login");
		String password=pflib.getDataFromProperties("password");
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(url, username, password);
	}
	
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void configAM() throws InterruptedException 
	{
		System.out.println("==LogOut==");
		HomePage hp=new HomePage(driver);
		hp.perform_Signout();
	}
	
	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void configAC() throws InterruptedException 
	{
		System.out.println("==Close Browser==");
		Thread.sleep(2000);
		driver.quit();
	}
	
	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void configAS() throws Throwable
	{
		System.out.println("=====Close To DB, Report backUp");
		dlib.closeDbconnection();
	}
}
