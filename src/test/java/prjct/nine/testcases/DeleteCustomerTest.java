package prjct.nine.testcases;

import static io.restassured.RestAssured.*;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import extentlisteners.ExtentListeners;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import prjct.api.blogic.CreateCustomerAPI;
import prjct.api.blogic.DeleteCustomerAPI;
import prjct.nine.base.testingBase;
import prjct.nine.utilities.DataUtil;
import prjct.nine.utilities.TestUtilities;

public class DeleteCustomerTest extends testingBase {

	
	/*
	 * @Test(dataProviderClass = DataUtil.class, dataProvider = "dp") public void
	 * validateCustomerDeletionFromRecords(Hashtable<String, String> data) {
	 * softassert=new SoftAssert(); Response response =
	 * DeleteCustomerAPI.DeleteCustomerByID(data); response.prettyPrint();
	 * softassert.assertEquals(200, response.statusCode()); softassert.assertAll();
	 * }
	 */

	
	  @Test public void validateAccessToken() { softassert=new SoftAssert();
	  softassert.assertEquals("sgasdga",TestUtilities.getPayPalAuthenKey());
	  softassert.assertAll();
	  
	  }
	 
	
	
	

	
	@Test(priority=2)	
	public void createOrder() {
		
		String access_token = "A21AALykdA4tNwoV6E5jwys5pDncmPiomxvtpKEkEkMu7WrlWmRRB9DQWfnEDpvrYV64WBSchQ67MWa0O-KvLtlCqNZz8DhJg";
		
		String jsonBody = "{\r\n"
				+ "  \"intent\": \"CAPTURE\",\r\n"
				+ "  \"purchase_units\": [\r\n"
				+ "    {     \r\n"
				+ "      \"amount\": {\r\n"
				+ "        \"currency_code\": \"USD\",\r\n"
				+ "        \"value\": \"100.00\"\r\n"
				+ "      }\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		
		//RestAssured.baseURI = "https://api-m.sandbox.paypal.com";
		//RestAssured.baseURI = "/v1";
		Response response = given().contentType(ContentType.JSON).auth().oauth2(access_token).body(jsonBody).post("https://api-m.sandbox.paypal.com/v2/checkout/orders");
		response.prettyPrint();	
		System.out.println(response.statusCode());
		
	}
	
	
	
}
