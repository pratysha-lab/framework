package com.comcast.crm.contacttest;

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
import com.comcast.crm.objectRepositoryUtility.ContactInformationPage;
import com.comcast.crm.objectRepositoryUtility.ContactPage;
import com.comcast.crm.objectRepositoryUtility.CreateNewContactPage;
import com.comcast.crm.objectRepositoryUtility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepositoryUtility.HomePage;
import com.comcast.crm.objectRepositoryUtility.LoginPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationPage;
/**
 * @author Pavankumar
 * 
 * 
 */
@Listeners(com.comcast.crm.listnerutility.ListenerImplementation.class)
public class CreateContactTest extends BaseClass {

	@Test(groups = {"smokeTest","regressionTest"})
	public void createContactTest() throws InterruptedException, FileNotFoundException, IOException {

		/*load Test Script data from excel file*/
		UtilityClassObject.getTest().log(Status.INFO, "Read Data From Excel");
		String lastname = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

		/* Step-2 In Home Page Navigate to Contacts and click*/
		UtilityClassObject.getTest().log(Status.INFO, "Naviagte to Contact");
		HomePage hp = new HomePage(driver);
		hp.getContactEdt().click();

		/*Step-3 click on Create contact*/
		UtilityClassObject.getTest().log(Status.INFO, "Click on create Contact");
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactedt().click();

		/*Step-4 create Contact with manadatory field*/
		UtilityClassObject.getTest().log(Status.INFO, "Create New Contact");
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createContactAndSave(lastname);

		/*Verify Header msg*/
		UtilityClassObject.getTest().log(Status.PASS, "Verify Header");
		ContactInformationPage cip = new ContactInformationPage(driver);
		String Headerinfo = cip.getHeaderInfoEdt().getText();
		boolean status = Headerinfo.contains(lastname);
		Assert.assertTrue(status);
		
		/* Verify contactName info Expected result*/
		UtilityClassObject.getTest().log(Status.PASS, "Verify Contact Name");
		String actlastName = cip.getActLastName().getText();
		SoftAssert soft = new SoftAssert();
		status = actlastName.contains(actlastName);
		soft.assertTrue(status);
		soft.assertAll();
	}

	@Test(groups = "regressionTest")
	public void createContactWithSupportDateTest() throws InterruptedException, IOException {
		/*load Test Script data from excel file*/
		UtilityClassObject.getTest().log(Status.INFO, "Read Data from Excel");
		String lastname = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();

		/* In HomePage Navigate to Contact*/
		UtilityClassObject.getTest().log(Status.INFO, "Naviagte To Contact");
		HomePage hp = new HomePage(driver);
		hp.getContactEdt().click();

		/* Step-3 In Contact Page click on "+" image*/
		UtilityClassObject.getTest().log(Status.INFO, "Create Contact");
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactedt().click();

		/*Date format*/
		String startDate = jlib.getSystemDateyyyyMMdd();
		String endDate = jlib.getRequiredDateyyyyMMdd(30);
		Thread.sleep(2000);
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createContactWithSupportDateAndSave(lastname, startDate, endDate);
		ContactInformationPage cip = new ContactInformationPage(driver);
		
		// Verify Start date
		UtilityClassObject.getTest().log(Status.PASS, "Verify Start date");
		String actStartDate = cip.getActStartDate().getText();
		Assert.assertEquals(actStartDate, startDate);

		// verfify End date
		UtilityClassObject.getTest().log(Status.PASS, "Verify End Date");
		String actEndDate = cip.getActEndDate().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actEndDate, endDate);
		soft.assertAll();

	}

	@Test(groups = "regressionTest")
	public void CreateContactWithOrgIntegTest() throws InterruptedException, IOException {

		/*load Test Script data from excel file*/
		UtilityClassObject.getTest().log(Status.INFO, "Read Data From Excel");
		String lastname = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String orgName = elib.getDataFromExcel("contact", 7, 3) + jlib.getRandomNumber();
		
		/*Navigate to "create New organization" page by click on "+" image*/
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to organization");
		HomePage hp = new HomePage(driver);
		hp.getOrganizationedt().click();

		/*Create Organization*/
		UtilityClassObject.getTest().log(Status.INFO, " Click on Create New Organization");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgEdt().click();

		/* create organization with manadatory field */
		UtilityClassObject.getTest().log(Status.INFO, "Create New Organization");
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrganization(orgName);

		/* Verify contact header msg*/
		UtilityClassObject.getTest().log(Status.INFO, "Verify Contact Header");
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String Headerinfo = oip.getHeaderInfo().getText();
		boolean status=Headerinfo.contains(orgName);
		Assert.assertTrue(status);
		Thread.sleep(2000);

		/* Navigate to "Contact" and create by click on "+" image*/
		UtilityClassObject.getTest().log(Status.INFO, "Navigate To Contact");
		hp.getContactEdt().click();
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactedt().click();

		/*create contact with manadatory field */
		UtilityClassObject.getTest().log(Status.INFO, "Create Contact with necessary fields");
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createContactWithOrgInteg(lastname, orgName);

		/*Verify contact header msg*/
		UtilityClassObject.getTest().log(Status.PASS, "Verify Contact Header");
		ContactInformationPage cip = new ContactInformationPage(driver);
		Headerinfo = cip.getHeaderInfoEdt().getText();
		status=Headerinfo.contains(lastname);
		Assert.assertTrue(status);

		/*Verify Organization */
		UtilityClassObject.getTest().log(Status.PASS, "Verfy Organization");
		String actOrg = oip.getActOrganizationName().getText();
		SoftAssert soft = new SoftAssert();
		status=actOrg.contains(orgName);
		soft.assertTrue(status);
		soft.assertAll();

	}

}
