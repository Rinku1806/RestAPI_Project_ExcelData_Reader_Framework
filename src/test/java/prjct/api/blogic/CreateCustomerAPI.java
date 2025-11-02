package prjct.api.blogic;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import extentlisteners.ExtentListeners;
import io.restassured.response.Response;
import prjct.nine.base.testingBase;

public class CreateCustomerAPI extends testingBase {

	public static Response CreateCustomerBasicAuthByValidSecretKey(Hashtable<String, String> data) {
		ExtentListeners.testReport.get().info("Creating Customer with these details : Name: " + data.get("name")
		+ "  Email Id: " + data.get("email"));
		Response response = given().auth().basic(prop.getProperty("prjct_secret_key"), "")
				.formParam("email", data.get("email")).formParam("description", data.get("description"))
				.formParam("name", data.get("name")).formParam("phone", data.get("phone"))
				.post(prop.getProperty("CreateUserEndPoint"));

		ExtentListeners.testReport.get().info("Response Code received is  :" + response.getStatusCode());
		return response;
	}

	public static Response CreateCustomerBasicAuthByInValidSecretKey(Hashtable<String, String> data) {
		ExtentListeners.testReport.get().info("Creating Customer with these details : Name: " + data.get("name")
				+ "  Email Id: " + data.get("email"));
		Response response = given().auth().basic(prop.getProperty("prjct_Invalid_secret_key"), "")
				.formParam("email", data.get("email")).formParam("description", data.get("description"))
				.formParam("name", data.get("name")).formParam("phone", data.get("phone"))
				.post(prop.getProperty("CreateUserEndPoint"));
		ExtentListeners.testReport.get().info("Response Code received is  :" + response.getStatusCode());
		return response;

	}

}
