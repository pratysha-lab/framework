package practice.Test;


import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.baseTest.BaseClass;
@Listeners(com.comcast.crm.listnerutility.ListenerImplementation.class)
public class InvoiceTest extends BaseClass {
	
	
	@Test()
	public void activateSim() {
		System.out.println("execute createInvoice Test");
		Assert.assertEquals("" , "CRM vTiger");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
		
	}
@Test
public void createInvoice() {
	System.out.println("execute createInvoice Test");
	String actTitle=driver.getTitle();
	Assert.assertEquals(actTitle, "CRM vTiger");
	System.out.println("Step-1");
	System.out.println("Step-2");
	System.out.println("Step-3");
	System.out.println("Step-4");
}

@Test
public void createInvoiceWithContactTest() {
	System.out.println("execute create Invoice");
	
}

}
