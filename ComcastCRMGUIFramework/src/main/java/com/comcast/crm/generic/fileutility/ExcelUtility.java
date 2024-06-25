package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.support.ui.Select;

public class ExcelUtility {

	
	public String getDataFromExcel(String sheetName,int rowNum, int cellNum) throws IOException {
		FileInputStream fis=new FileInputStream("./testdata/testScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		return data;
	}
	
	public int getRowcount(String sheetname) throws Throwable{
		FileInputStream fis=new FileInputStream("./testData/TestScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int rowCount=wb.getSheet(sheetname).getLastRowNum();
		return rowCount;
	}
	
	public void setDataIntoExcel(String sheetname, int rowNum ,int celNum, String data) throws Throwable{
		FileInputStream fis=new FileInputStream("./testdata/testScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		wb.getSheet(sheetname).createRow(rowNum).createCell(celNum).setCellValue(data);
		
		FileOutputStream fos=new FileOutputStream("./testdata/testScriptData.xlsx");
		wb.write(fos);
		wb.close();
		fos.close();
		fis.close();
	}
	
		public static void ReadDataBasedOnConditionTes(String expectedData,String sheetname ) throws Throwable {
			boolean flag=true;
			
			FileInputStream fis=new FileInputStream("./testData/TestScriptData.xlsx");
			Sheet sh=WorkbookFactory.create(fis).getSheet(sheetname);
			
			for(int i=0;i<=sh.getLastRowNum();i++) {
				String data="";
				try {
				data=sh.getRow(i).getCell(0).toString();
				if(data.equals(expectedData)) {
					flag=true;
					for(int j=1;j<=sh.getRow(j).getLastCellNum();j++) {
					data=sh.getRow(i).getCell(j).getStringCellValue();
					System.out.println(data);
					}
				}
				}catch (Exception e) {}
			}
		}
		
}
