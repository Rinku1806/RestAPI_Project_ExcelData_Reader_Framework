package prjct.nine.utilities;

import org.json.JSONObject;
import org.testng.annotations.Test;

import extentlisteners.ExtentListeners;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Base64;

public class TestUtilities {
	
	
	static String clientId = "AbiPol22A81By_-L9tfMQvToq-cTnDEjD37ZDZ82AMeS7lbJcVn0hEVbOc0DmYLZ9ZTASnWdWNC06D5x"; 
	static String sKey ="ENr_h-rO0U4rVingsVZp_dFHqIA_BPQHkE43t1q8PBy0HIrzibXmqaLr-p0DHNb4P5bBubCYydLs1AyQ";
	
	public static String encodedClientId = Base64.getEncoder().encodeToString(clientId.getBytes());
	public static String encodedPassword = Base64.getEncoder().encodeToString(sKey.getBytes());

	public static boolean IskeyPresent(String Jsonobject, String key) {
		
		ExtentListeners.testReport.get().info("Validating the presence of the "+ key + " in the response.");
		JSONObject jsobj = new JSONObject(Jsonobject);		
		return jsobj.has(key);
	}

	public static String  getJsonKeyValue(String Jsonobject, String key) {		
		
		JSONObject jsobj = new JSONObject(Jsonobject); 
		ExtentListeners.testReport.get().info("Validating the value of the Key : "+ (String) jsobj.get(key) + " in the response.");
		return (String) jsobj.get(key);

	}
	
	
	public static String getPayPalAuthenKey() {
		
		//RestAssured.baseURI = "https://api-m.sandbox.paypal.com/";		
		Response response  = given().param("grant_type","client_credentials").auth().preemptive().basic(encodedClientId, encodedPassword).post("https://api-m.sandbox.paypal.com/v1/oauth2/token");
		response.prettyPrint();
		System.out.println(response.statusCode());
		String access_token = response.jsonPath().get("access_token").toString();  
		return access_token;
	}
	
    


    
	
	
	
	

}
