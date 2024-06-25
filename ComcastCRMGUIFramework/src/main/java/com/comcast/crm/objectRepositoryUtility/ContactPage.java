package com.comcast.crm.objectRepositoryUtility;
//step-1 Create POM class

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

	//Step-2 Object Identification
	@FindBy(css="[title='Create Contact...']")
	private WebElement createContactedt;
	
	//Step-3 Object Initialization
	WebDriver driver;
	public ContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void setCreateContactedt(WebElement createContactedt) {
		this.createContactedt = createContactedt;
	
	}

	//Step-4 Object Encapsulation
	public WebElement getCreateContactedt() {
		return createContactedt;
	}
	
}
