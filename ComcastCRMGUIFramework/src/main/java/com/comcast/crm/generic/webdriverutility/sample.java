package com.comcast.crm.generic.webdriverutility;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import bsh.Remote;

public class sample {
public static void main(String[] args) {
//	WebDriver d=new ChromeDriver();
//	
		Capabilities c = ((RemoteWebDriver)UtilityClassObject.getDriver()).getCapabilities();
		
		System.out.println(c.getBrowserVersion());
		System.out.println(c.getPlatformName());
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("os.arch"));
		System.out.println(System.getProperty("os.version"));
//		d.quit();
}
}
