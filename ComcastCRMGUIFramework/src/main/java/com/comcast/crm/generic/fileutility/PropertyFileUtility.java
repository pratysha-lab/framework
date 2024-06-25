package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {
	
	public String getDataFromProperties(String key) throws IOException {
		
		FileInputStream fis=new FileInputStream("./configAppData/LoginData.properties");
		Properties pObj=new Properties();
		pObj.load(fis);
		String data=pObj.getProperty(key);
		
		return data ;
	}
}
