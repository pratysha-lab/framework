package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {//Step-1 create POM class
	
	//Step-2 Object identification
	@FindBy(css ="[class='dvHeaderText']")
	private WebElement headerInfo;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement actOrganizationName;
	
	@FindBy(id="mouseArea_Industry")
	private WebElement actindustryName;
	
	@FindBy(id="mouseArea_Type")
	private WebElement actTypeName;
	
	@FindBy(id="mouseArea_Phone")
	private WebElement actContactName;
	
	//Step-3 Object Initialization
	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//Step -4 Object Encapsulation
	public WebElement getHeaderInfo() {
		return headerInfo;
	}

	public WebElement getActOrganizationName() {
		return actOrganizationName;
	}
	
public WebElement getActindustryName() {
		return actindustryName;
	}

	public WebElement getActTypeName() {
	return actTypeName;
}

	public WebElement getActContactName() {
		return actContactName;
	}

	//Step=5 Object Utilization
	public String verificationHeader(String Headerinfo) {
		Headerinfo=headerInfo.getText();
		return Headerinfo;
	}
	
	public String actOrganizationName(String actOrgName) {
		actOrgName=actOrganizationName.getText();
		return actOrgName;
	}
	
	public String actIndustryName(String actIndustryName) {
		actIndustryName=actindustryName.getText();
		return actIndustryName;
	}
	
	public String actTypeName(String actTypeName) {
		actTypeName=actindustryName.getText();
		return actTypeName;
	}
	
	public String actContactName(String actContactName) {
		actContactName=this.actContactName.getText();
		return actContactName;
	}
	
}
