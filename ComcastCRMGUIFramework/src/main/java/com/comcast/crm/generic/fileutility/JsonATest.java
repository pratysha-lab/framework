package com.comcast.crm.generic.fileutility;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

public class JsonATest {
	public static void main(String[] args) throws IOException, ParseException {
		
	JsonUtility jlib=new JsonUtility();
	double data=jlib.getDatafromJsonFileNum("timeout");
	System.out.println(data);
	System.out.println((long)data);
	
	}
}
