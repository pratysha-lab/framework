package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Step-01 Create POM class
public class HomePage {

//Step-2 object identification
	@FindBy(linkText="Organizations")
	private WebElement organizationedt;
	
	@FindBy(linkText="Contacts")
	private WebElement contactEdt;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignsEdt;
	
	@FindBy(linkText="More")
	private WebElement moreEdt;
	
	@FindBy(css="[src='themes/softed/images/user.PNG']")
	private WebElement signoutlink;
	
	@FindBy(xpath = "//a[contains(text(),'Sign Out') and @class='drop_down_usersettings']")
	private WebElement signoutBtnEdt;

	@FindBy(linkText = "Products")
	private WebElement productsLinkt;
	
	//step-3 Object Initialization
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//Step-4 Object Encapsulation
	public WebElement getOrganizationedt() {
		return organizationedt;
	}
	
	
	public WebElement getContactEdt() {
			return contactEdt;
		}
	
	public WebElement getMoreEdt() {
		return moreEdt;
	}
	
	public WebElement getCampaignsEdt() {
		return campaignsEdt;
	}
	
	public WebElement signoutlink() {
		return signoutlink;
	}
	
	public WebElement getSignoutBtnEdt() {
		return signoutBtnEdt;
	}

	public WebElement getProductsLinkt() {
		return productsLinkt;
	}

	//Step-5 Object Utilization not required because business library required when multiple action we need to combined
	public void HomePageOperation() {
			organizationedt.click();
	}
	
	public void ProductPageOperation() {
		productsLinkt.click();
	}
	
	//Step-5 Object Utilization
	public void navigateToCampaignPage() {
		Actions act=new Actions(driver);
		act.moveToElement(moreEdt).perform();
		campaignsEdt.click();
	}
	
	public void perform_Signout() throws InterruptedException {
//		//Actions act=new Actions(driver);
//		act.moveToElement(signoutlink).perform();Thread.sleep(1000);
//		act.click(signoutBtnEdt).perform();
		signoutlink.click();
		signoutBtnEdt.click();
	}
	
	
	

	
	
}
