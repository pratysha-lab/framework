package com.comcast.crm.generic.webdriverutility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebdriverUtility {
public void waitForPageToLoad(WebDriver driver) {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
}

public void windowMaximize(WebDriver driver) {
	driver.manage().window().maximize();
}

public void waitForElementPresent(WebDriver driver, WebElement element) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	wait.until(ExpectedConditions.visibilityOf(element));
}

public void switchToTabOnURL(WebDriver driver,String partialurl) {
	Set<String> wids=driver.getWindowHandles();
	Iterator<String> itr=wids.iterator();
	
	while(itr.hasNext()) {
		String windowID=itr.next();
		driver.switchTo().window(windowID); 
		
		String acturl=driver.getCurrentUrl();
		if (acturl.contains(partialurl)) {
			break;
		}
	}
}
	
	public void switchToTabOnTitle(WebDriver driver,String partialTitle) {
		Set<String> wids=driver.getWindowHandles();
		Iterator<String> itr=wids.iterator();
		
		while(itr.hasNext()) {
			String windowID=itr.next();
			driver.switchTo().window(windowID); 
			
			String acturl=driver.getCurrentUrl();
			if (acturl.contains(partialTitle)) {
				break;
			}
		}
}
	
	public void switchtoFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	
	public void switchtoFrame(WebDriver driver, String nameID) {
		driver.switchTo().frame(nameID);
	}
	
	public void switchtoFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	
	public void switchtoAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void switchtoAlertAndCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public void select(WebElement element,String text) {
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	
	public void select(WebElement element, int index) {
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	
	
	public void mouseMoveOnElement(WebDriver driver, WebElement element) {
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	public void doubleclickMouse(WebDriver driver, WebElement element) {
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	public void getScreenShotOfWebpage(WebDriver driver,String path) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File temp=ts.getScreenshotAs(OutputType.FILE);
		File perm=new File(path);
		FileHandler.copy(temp, perm);
	}
	
	public void getScreenShotOfWebElement(WebDriver driver,WebElement element, String path) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File temp=element.getScreenshotAs(OutputType.FILE);
		File perm=new File(path);
		FileHandler.copy(temp, perm);
	}
	
	public void scrollByJavaScriptExecutor(WebDriver driver,String scrollbyscript) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript(scrollbyscript);
	}
	
	public void scrollToJavaScriptExecutor(WebDriver driver,String scrolltoscript) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript(scrolltoscript);
	}
	
	public void argumentJavaScriptExecutor(WebDriver driver,String argument) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript(argument);
	}
	
}
