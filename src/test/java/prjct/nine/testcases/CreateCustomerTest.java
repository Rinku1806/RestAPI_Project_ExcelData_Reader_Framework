package prjct.nine.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;
import prjct.api.blogic.CreateCustomerAPI;
import prjct.nine.base.testingBase;
import prjct.nine.utilities.DataUtil;
import prjct.nine.utilities.TestUtilities;

public class CreateCustomerTest extends testingBase {

	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void validateCreateCustomerBasicAuthByValidSecretKey(Hashtable<String, String> data) {
		softassert=new SoftAssert();
		Response response = CreateCustomerAPI.CreateCustomerBasicAuthByValidSecretKey(data);
		response.prettyPrint();		
		softassert.assertEquals(200, response.statusCode());
		softassert.assertTrue(TestUtilities.IskeyPresent(response.asString(),"email"));
		softassert.assertTrue(TestUtilities.IskeyPresent(response.asString(),"name"));
		softassert.assertEquals(data.get("email"), TestUtilities.getJsonKeyValue(response.asString(),"email"));
		softassert.assertEquals(data.get("name"), TestUtilities.getJsonKeyValue(response.asString(),"name"));
		softassert.assertAll();

	}
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void validateCreateCustomerBasicAuthByInValidSecretKey(Hashtable<String, String> data) {
		
		softassert=new SoftAssert();
		Response response = CreateCustomerAPI.CreateCustomerBasicAuthByInValidSecretKey(data);
		response.prettyPrint();
		System.out.println(response.statusCode());
		softassert.assertEquals(401, response.statusCode());
		softassert.assertAll();

	}

}
