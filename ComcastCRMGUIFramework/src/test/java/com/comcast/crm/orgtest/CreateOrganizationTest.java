
package com.comcast.crm.orgtest;

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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebdriverUtility;
import com.comcast.crm.listnerutility.ListenerImplementation;
import com.comcast.crm.objectRepositoryUtility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepositoryUtility.HomePage;
import com.comcast.crm.objectRepositoryUtility.LoginPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationPage;
/**
 * @author PavanKumar
 * This is Organuzation Module In which operation related to Organization is stored
 */

@Listeners(com.comcast.crm.listnerutility.ListenerImplementation.class)
public class CreateOrganizationTest extends BaseClass {
/**
 * @createOrganizationTestCase
 * @throws InterruptedException
 * @throws FileNotFoundException
 * @throws IOException
 */
	@Test(groups={"smokeTest","regressionTest"})
	public void CreateOrganizationTest() throws InterruptedException, FileNotFoundException, IOException {
		
		/*Extract Data from Excel File*/
		UtilityClassObject.getTest().log(Status.INFO, "read Data from excel");
		String orgName = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();

		/*Navigate to organization module*/
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Org Page");
		HomePage hp = new HomePage(driver);
		hp.getOrganizationedt().click();

		/* click On"create New organization" page by click on "+" image*/
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create Org Page");
		OrganizationPage ocp = new OrganizationPage(driver);
		ocp.getCreateOrgEdt().click();

		/*create organization with manadatory field*/
		UtilityClassObject.getTest().log(Status.INFO, "Create a new Org");
		CreateNewOrganizationPage op = new CreateNewOrganizationPage(driver);
		op.createOrganization(orgName);

		/* Verify Header msg*/
		UtilityClassObject.getTest().log(Status.INFO, orgName+"==>Create a New Org");
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String Headerinfo = oip.getHeaderInfo().getText();
		oip.verificationHeader(Headerinfo);
		boolean status=Headerinfo.contains(orgName);
		Assert.assertTrue(status);
		
		/*verify oragnization name*/
		UtilityClassObject.getTest().log(Status.PASS, orgName+"==>Ogr Name Verify");
		String actOrgName = oip.getActOrganizationName().getText();
		status=actOrgName.contains(orgName);
		SoftAssert soft=new SoftAssert();
		soft.assertTrue(status);
		soft.assertAll();
		
	}
	/**
	 * @CreateOrganizationWithPhoneNumberTest
	 * @throws IOException
	 */
	@Test(groups="regressionTest")
	public void CreateOrganizationWithPhoneNumberTest() throws IOException {

		/*load Test Script data from excel file*/
		UtilityClassObject.getTest().log(Status.INFO, "Read Data From External Files");
		String orgName = elib.getDataFromExcel("org", 7, 2) + jlib.getRandomNumber();
		String contact = elib.getDataFromExcel("org", 7, 3);

		
		/*Navigate to organization on Home page and click on "Organization"*/
		UtilityClassObject.getTest().log(Status.INFO, "Naviagte to Organization");
		HomePage hp = new HomePage(driver);
		hp.getOrganizationedt().click();

		/* In Organization Page Click on "Create New Organization" by click on"+" */
		UtilityClassObject.getTest().log(Status.INFO, "Create New Organization");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgEdt().click();

		/*create organization with Industry and contact Name*/
		UtilityClassObject.getTest().log(Status.INFO, "create organization with Industry and contact Name");
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrganization(orgName, contact);

		/*Verify industry and type*/
		UtilityClassObject.getTest().log(Status.INFO, "Verify Industry and Type");
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actContact = oip.actContactName(contact);
		boolean status=actContact.contains(contact);
		Assert.assertTrue(status);
	}
	/**
	 * @CreateOrganizationWithIndustryTest
	 * @throws IOException
	 */
	@Test(groups="regressionTest")
	public void CreateOrganizationWithIndustryTest() throws IOException {

		/* load Test Script data from excel file*/
		UtilityClassObject.getTest().log(Status.INFO, "Read Data from external files");
		String orgName = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();
		String industry = elib.getDataFromExcel("org", 4, 3);
		String type = elib.getDataFromExcel("org", 4, 4);

		/*Navigate to organization click on "Organization" image*/
		UtilityClassObject.getTest().log(Status.INFO, "Navigate To Organization");
		HomePage hp = new HomePage(driver);
		hp.getOrganizationedt().click();

		/*click on New Organization*/
		UtilityClassObject.getTest().log(Status.INFO, "Create New Organization");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgEdt().click();

		//create organization with Industry
		UtilityClassObject.getTest().log(Status.INFO, "Create Organizatiomn with Industry Name");
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrganization(orgName, industry, type);

		/*verify industry*/
		UtilityClassObject.getTest().log(Status.INFO, "Verify Industry");
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actIndustry = oip.getActindustryName().getText();
		boolean status=actIndustry.contains(industry);
		Assert.assertTrue(status);

		/*Verify Type*/
		UtilityClassObject.getTest().log(Status.INFO, "Verify Type");
		String actType = oip.getActTypeName().getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(type, actType);
		
	}

}
