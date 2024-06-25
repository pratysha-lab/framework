package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebdriverUtility;

//Step 1 Create POM class
public class OrganizationPage{
	
//Step 2 Object Identification
	@FindBy(css="img[title='Create Organization...']")
	private WebElement createOrgEdt;
	
	@FindBy(name="search_text")
	private WebElement searchEdt;
	
	@FindBy(xpath="//div[@id='basicsearchcolumns_real']/select[@name='search_field']")
	private WebElement searchdd;
	
	@FindBy(name="submit")
	private WebElement searchBtn;

//Step 3 Object Initialization
	WebDriver driver;
	public OrganizationPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
}
	
//Step 4 Object Encapsulation
	public WebElement getCreateOrgEdt() {
		return createOrgEdt;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchdd() {
		return searchdd;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}
	
//Step 5 Object Utilization
	public void deleteOrgName(String orgName,String DD) {
		getSearchEdt().sendKeys(orgName);
		WebdriverUtility wlib=new WebdriverUtility();
		wlib.select(searchdd, DD);
		getSearchBtn().click();
		
		
	}
	
	

}
