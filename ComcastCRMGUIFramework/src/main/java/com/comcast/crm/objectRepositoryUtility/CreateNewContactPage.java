package com.comcast.crm.objectRepositoryUtility;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebdriverUtility;
//step-1 create POM class
public class CreateNewContactPage {

	//step-2 Object Identification
	@FindBy(name="lastname")
	private WebElement lastnameEdt;
	
	@FindBy(id="jscal_field_support_start_date")
	private WebElement startDateEdt;
	
	@FindBy(id="jscal_field_support_end_date")
	private WebElement endDateEdt;
	
	@FindBy(xpath="//input[@value='Cancel  ']/preceding-sibling::input")
	private WebElement saveEdt;
	
	@FindBy(xpath="//input[@name='account_name'] /following-sibling::img")
	private WebElement organizationListEdt;
	
	@FindBy(name="search_text")
	private WebElement searchTextEdt;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	@FindBy(xpath="//a[text()='Organization Name ']/ancestor::tbody/tr/td/a[@id='1']")
	private WebElement selectOrg;
	
	//Step- 3 Object Initialization
	WebDriver driver;
	public CreateNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Step-4 Object Encapsulation
	public WebElement getLastnameEdt() {
		return lastnameEdt;
	}
	
	public WebElement getSaveEdt() {
		return saveEdt;
	}
	
	public WebElement getOrganizationListEdt() {
		return organizationListEdt;
	}
	
	public WebElement getSearchTextEdt() {
		return searchTextEdt;
	}
	
	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSelectOrg() {
		return selectOrg;
	}

	public WebElement getStartDateEdt() {
		return startDateEdt;
	}

	public WebElement getEndDateEdt() {
		return endDateEdt;
	}

	//Step-5 Object Utilization
	public void createContactAndSave(String lastname) {
		lastnameEdt.sendKeys(lastname);
		saveEdt.click();
	}
	
	public void createContactWithSupportDateAndSave(String lastname,String startDate,String endDate) {
		lastnameEdt.sendKeys(lastname);
		startDateEdt.clear();
		startDateEdt.sendKeys(startDate);
		endDateEdt.clear();
		endDateEdt.sendKeys(endDate);
		saveEdt.click();
	}
	
	public void createContactWithOrgInteg(String lastname,String orgName) throws InterruptedException {
		lastnameEdt.sendKeys(lastname);
		organizationListEdt.click();
		WebdriverUtility wlib=new WebdriverUtility();
		//switch to child
		wlib.switchToTabOnURL(driver, "Accounts&action");Thread.sleep(1000);
		searchTextEdt.sendKeys(orgName);Thread.sleep(1000);
		searchBtn.click();Thread.sleep(2000);
		selectOrg.click();
		//switch to parent
		wlib.switchToTabOnTitle(driver, "Contacts&action");
		saveEdt.click();
	}
	
}
