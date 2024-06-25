package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Step-1 Create POM class
public class ContactInformationPage {

	//Step-2 Object Identification
	@FindBy(css="[class='dvHeaderText']")
	private WebElement headerInfoEdt;
	
	@FindBy(id="mouseArea_Last Name")
	private WebElement actLastName;
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement actStartDate;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement actEndDate;
	
	//Step-3 Object Initiaization
	WebDriver driver;
	public ContactInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Step-4 Object Encapsulation
	public WebElement getHeaderInfoEdt() {
		return headerInfoEdt;
	}
	
	public WebElement getActLastName() {
		return actLastName;
	}
	
	public WebElement getActStartDate() {
		return actStartDate;
	}

	public WebElement getActEndDate() {
		return actEndDate;
	}

	//Step-4 Object Encutilization
	public String verifyHeaderContact(String headerinfo) {
		headerinfo=headerInfoEdt.getText();
		return headerinfo;
	}
	
	public String verifyActLastName(String actLastName) {
		actLastName=this.actLastName.getText();
		return actLastName;
	}
	
	public String verifyStartDate(String actStartDate) {
		actStartDate=this.actStartDate.getText();
		return actStartDate;
	}
	
	public String verifyEndDate(String actEndDate) {
		actEndDate=this.actEndDate.getText();
		return actEndDate;
	}
	
	
	
}
