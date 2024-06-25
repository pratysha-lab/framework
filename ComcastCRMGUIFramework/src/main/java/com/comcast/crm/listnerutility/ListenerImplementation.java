/*
 * Author: PavanKumar
 * Contains Listerners Utility
 * 
 * */
package com.comcast.crm.listnerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
   /**
    * @author Pavankumar 
    * 
    * 
    */
public class ListenerImplementation implements ITestListener, ISuiteListener {

	public ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		// spark report config
		String time = new Date().toString().replace(" ", " _").replace(":", "_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report " + time + " .html");
		spark.config().setDocumentTitle("CRM Test suit Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add Env information & create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", System.getProperty("os.name"));
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("======>" + result.getMethod().getMethodName() + "<======START========>");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		Capabilities c=((RemoteWebDriver)UtilityClassObject.getDriver()).getCapabilities();
		report.setSystemInfo("BROWSER", c.getBrowserName());
		UtilityClassObject.getTest().log(Status.INFO, result.getMethod().getMethodName() + "===>Start<===");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("======>" + result.getMethod().getMethodName() + "<======Successfull========>");
		UtilityClassObject.getTest().log(Status.PASS, result.getMethod().getMethodName() + "===>Completed<===");
	}
	
	
	@Override
	public void onTestFailure(ITestResult result) {

		String testName = result.getMethod().getMethodName();
		String filepath = ((TakesScreenshot) UtilityClassObject.getDriver()).getScreenshotAs(OutputType.BASE64);

		String time = new Date().toString().replace(" ", " _").replace(":", "_");
		UtilityClassObject.getTest().addScreenCaptureFromBase64String(filepath, testName + "_" + time);
		UtilityClassObject.getTest().log(Status.FAIL, result.getMethod().getMethodName() + "===>Failed<===");

	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		report.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		UtilityClassObject.getTest().log(Status.SKIP, result.getMethod().getMethodName() + "===>Skipped<==");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		UtilityClassObject.getTest().log(Status.FAIL, result.getMethod().getMethodName() + "===>Failed<===");
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		UtilityClassObject.getTest().log(Status.FAIL, result.getMethod().getMethodName() + "===>Failed<===");
	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
