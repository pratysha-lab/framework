package practice.Test;
/**
 * test class for contact module
 * @author Pavan
 */

import org.testng.annotations.Test;

import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.objectRepositoryUtility.LoginPage;

public class SearchContactTest extends BaseClass {
/**
 * Scenario: login()==>naviagteContact==>createContact()=verify
 */
	@Test
	public void searchcontactTest() {
		/*step1: Login to app*/
		LoginPage lp=new LoginPage(driver);
		
	}
}
