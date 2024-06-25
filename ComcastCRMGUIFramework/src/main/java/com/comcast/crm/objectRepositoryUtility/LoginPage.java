package com.comcast.crm.objectRepositoryUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.WebdriverUtility;
//Rule-1 create a separate java class
/**
 * @author PavanKumar
 * 
 * contains Login Page element and Business libarary
 * 
 */
public class LoginPage extends WebdriverUtility {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// Rule-2 Object Identification

	@FindAll({ @FindBy(name = "user_name"), @FindBy(xpath = "//input[@type='text']") })
	private WebElement usernameEdt;

	@FindAll({ @FindBy(name = "user_password"), @FindBy(xpath = "//input[@type='password']") })
	private WebElement passwordEdt;

	@FindBy(id = "submitButton")
	private WebElement loginBtn;

	// Rule-3 Object initialization

	// Rule-4 Object Utilization
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
/**
 * Login to application based n username password, url argument
 * @param url
 * @param login
 * @param password
 * @throws IOException
 */
	
	
	
	// Rule-5 we can provide action
	public void loginToApp(String url, String login, String password) throws IOException {
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(login);
		passwordEdt.sendKeys(password);
		loginBtn.click();

	}

}
