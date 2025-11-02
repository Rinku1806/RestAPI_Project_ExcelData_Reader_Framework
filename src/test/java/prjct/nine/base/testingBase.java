package prjct.nine.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import extentlisteners.ExtentManager;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import prjct.nine.utilities.ExcelReader;

public class testingBase {

	public static Properties prop;
	public static String dataSheetPath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\testData\\testData.xlsx";
	public static ExcelReader xlReader;
	protected SoftAssert softassert;
	public static String secretKey = System.getenv("PRJCT_SECRET_KEY");
	public static String invalidKey = System.getenv("PRJCT_INVALID_SECRET_KEY");

	@BeforeSuite
	public void setUp() throws IOException {

		// Read from environment variables first, fallback to properties file

		prop = new Properties();
		xlReader = new ExcelReader(dataSheetPath);
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\prjctConfigProp.properties");
		prop.load(file);
		RestAssured.baseURI = prop.getProperty("BaseURI");
		RestAssured.basePath = prop.getProperty("BasePath");
		
		if (secretKey == null || invalidKey == null) {
			
		    secretKey = secretKey != null ? secretKey : prop.getProperty("prjct_secret_key");
		    invalidKey = invalidKey != null ? invalidKey : prop.getProperty("prjct_Invalid_secret_key");
		}
		

	}

	@BeforeTest
	public void validationON() {

	}

	@AfterTest
	public void validationOff() {

	}

	@AfterSuite
	public void tearDown() {

	}

}
