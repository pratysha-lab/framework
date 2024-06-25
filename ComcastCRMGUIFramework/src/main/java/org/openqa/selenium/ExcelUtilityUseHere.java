package org.openqa.selenium;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class ExcelUtilityUseHere {
public static void main(String[] args) throws Throwable {
	ExcelUtility elib=new ExcelUtility();
	elib.ReadDataBasedOnConditionTes("tc_01"," org");
	
}
}
