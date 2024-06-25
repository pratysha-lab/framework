package com.comcast.crm.Sampletest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebdriverUtility;
import com.comcast.crm.objectRepositoryUtility.ContactPage;
import com.comcast.crm.objectRepositoryUtility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepositoryUtility.HomePage;
import com.comcast.crm.objectRepositoryUtility.LoginPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationPage;

public class DeleteAllOrgAndContact {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {
			//Random Number
			JavaUtility jlib=new JavaUtility();
			int rint=jlib.getRandomNumber();
		
			//load common data from properties file
			PropertyFileUtility flib=new PropertyFileUtility();
			//Extract Data from Property file
			String browser=flib.getDataFromProperties("browser");
			String url=flib.getDataFromProperties("url");
			String login=flib.getDataFromProperties("login");
			String password=flib.getDataFromProperties("password");
			
			//load Test Script data from excel file
			ExcelUtility elib=new ExcelUtility();
			//Extract Data from Excel File
			String orgName=elib.getDataFromExcel("org", 10, 2)+rint;
			String DD=elib.getDataFromExcel("org", 10, 5);
	
			//Test Script-01
			WebDriver driver=null;//select the browser
			if(browser.contains("chrome")) {
			 driver=new ChromeDriver();
			}else if(browser.contains("firefox")) {
				driver=new FirefoxDriver();
			}else {
				driver=new EdgeDriver();
			}
		
			//load webdriver utility
			WebdriverUtility wlib=new WebdriverUtility();
			wlib.waitForPageToLoad(driver);
			wlib.windowMaximize(driver);
		
			//Step 1-perform login operation
			LoginPage lp=new LoginPage(driver);
			lp.loginToApp(url,login, password);
			
			//Step 2-Navigate to organization module
			HomePage hp=new HomePage(driver);
			hp.getOrganizationedt().click();
			//Delete All organization
			driver.findElement(By.xpath("//input[@name='selectall']")).click();
			driver.findElement(By.xpath("//table[@class=\"lvtBg\"]/descendant::input[@onclick=\"return mass_edit(this, 'massedit', 'Accounts', 'Marketing')\"]/preceding-sibling::input[@value='Delete']")).click();
			Alert al=driver.switchTo().alert();
			al.accept();
			
			
		
			
			hp.getContactEdt().click();
			
		
			
			//Signout
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
			Thread.sleep(2000);
			hp.perform_Signout();
			System.out.println("===Script Run Successful====");
	
			//post condition
			driver.quit();

	}

}
