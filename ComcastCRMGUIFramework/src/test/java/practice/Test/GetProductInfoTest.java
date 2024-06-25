package practice.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest {
@Test(dataProvider = "getData")
public void getProductInfoTest(String brand, String productName) throws InterruptedException {
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get("https://www.amazon.in/");
	
	//search Product
	driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(brand,Keys.ENTER);
	Thread.sleep(2000);
	//capture Product Info
	WebElement x=driver.findElement(By.xpath("//span[contains(text(),'"+productName+"')]/ancestor::div[@class='a-section a-spacing-small a-spacing-top-small' and not(contains(.,'Sponsored'))]/descendant::span[@class='a-price-whole']"));
	System.out.println(x.getText());
	driver.quit();
	
}
	@DataProvider
	public Object[][] getData() throws Throwable{
		ExcelUtility elib=new ExcelUtility();
		int rcount=elib.getRowcount("product");
	
		Object[][] objArr=new Object[rcount][2];
		for(int i=0;i<rcount;i++) {
		objArr[i][0]=elib.getDataFromExcel("product", i+1, 0);
		objArr[i][1]=elib.getDataFromExcel("product", i+1, 1);
		}
		return objArr;
	}
	
}

