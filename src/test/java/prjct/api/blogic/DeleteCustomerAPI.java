package prjct.api.blogic;

import static io.restassured.RestAssured.*;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import extentlisteners.ExtentListeners;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import prjct.api.blogic.CreateCustomerAPI;
import prjct.nine.base.testingBase;
import prjct.nine.utilities.DataUtil;

public class DeleteCustomerAPI extends testingBase {

	
	public static Response DeleteCustomerByID(Hashtable<String, String> data) {
		ExtentListeners.testReport.get().info("Deleting the Customer with Customer id : " + data.get("Id"));
		Response response = given().auth().basic(prop.getProperty("prjct_secret_key"), "").delete(prop.getProperty("CreateUserEndPoint")+"/"+data.get("Id"));
		return response;


	}
}
