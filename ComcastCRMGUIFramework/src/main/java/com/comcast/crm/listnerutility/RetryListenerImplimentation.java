package com.comcast.crm.listnerutility;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListenerImplimentation implements IRetryAnalyzer{

	
	int count =0;
	int limitCount=5;
	@Override
	public boolean retry(ITestResult result) {
		if(count<limitCount) {
			count++;
			return true;
		}
		
		return false;
	}

}
