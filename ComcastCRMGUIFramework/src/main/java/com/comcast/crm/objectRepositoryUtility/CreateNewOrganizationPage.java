package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
//Step 1- create POM class
public class CreateNewOrganizationPage {

//Step 2-Object Identification
@FindBy(name="accountname")
private WebElement organizationNameEdt;

@FindBy(name="industry")
private WebElement industryNameEdt;

@FindBy(xpath = "//input[@value='Cancel  ']/preceding-sibling::input")
private WebElement saveEdt;

@FindBy(name="accounttype")
private WebElement typeindustryEdt;

@FindBy(id="phone")
private WebElement contactEdt;
//Step 3-Object Initialization
WebDriver driver;
public CreateNewOrganizationPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

//Step-4 Object Encapsulation
public WebElement getOrganizationNameEdt() {
	return organizationNameEdt;
}

public WebElement getIndustryNameEdt() {
	return industryNameEdt;
}

public WebElement getTypeindustryEdt() {
	return typeindustryEdt;
}

public WebElement getContactEdt() {
	return contactEdt;
}

public WebElement getSaveEdt() {
	return saveEdt;
}

//Step 5 Object utilization
public void createOrganization(String orgName) {
	organizationNameEdt.sendKeys(orgName);
	saveEdt.click();
}

public void createOrganization(String orgName,String contact) {
	organizationNameEdt.sendKeys(orgName);
	contactEdt.click();
	contactEdt.sendKeys(contact);
	saveEdt.click();
}

public void createOrganization(String orgName,String industry,String type) {
	organizationNameEdt.sendKeys(orgName);
	Select slt=new Select(industryNameEdt);
	slt.selectByVisibleText(industry);
	slt=new Select(typeindustryEdt);
	slt.selectByVisibleText(type);
	saveEdt.click();
}

public void createOrganization(String orgName,String industry,String type,String contact) throws InterruptedException {
	organizationNameEdt.sendKeys(orgName);
	Thread.sleep(1000);
	Select slt=new Select(industryNameEdt);
	slt.selectByVisibleText(industry);Thread.sleep(1000);
	slt=new Select(typeindustryEdt);
	slt.selectByVisibleText(type);
	contactEdt.click();
	contactEdt.sendKeys(contact);
	saveEdt.click();
}
}
