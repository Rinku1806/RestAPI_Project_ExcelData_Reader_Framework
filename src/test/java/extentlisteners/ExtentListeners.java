package extentlisteners;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentListeners implements ITestListener,ISuiteListener {

	/*
	 * public WebDriver driver;
	 * 
	 * 
	 * public ExtentListeners(WebDriver driver) { super(driver); this.driver =
	 * driver; // TODO Auto-generated constructor stub }
	 */

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	public static ExtentReports extent = ExtentManager
			.createInstance(System.getProperty("user.dir") + "\\ExtentReports\\" + fileName);
	
	
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest> ();

	public static ExtentTest test;

	public void onTestStart(ITestResult result) {

		//String param = (String) result.getParameters()[0];
		Object [] params = result.getParameters();
		System.out.println(params[0].toString());
		System.out.println(result.getTestClass().getName());
		System.out.println(result.getMethod().getMethodName());
		//test = extent.createTest(result.getTestClass().getName() + "     @TestCase : "+ result.getMethod().getMethodName() + "----"+ params[0].toString());
		test = extent.createTest(result.getTestClass().getName() + "     @TestCase : "+ result.getMethod().getMethodName());
		testReport.set(test);
	}

	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.pass(m);
		//testReport.get().pass(m);
	}

	public void onTestFailure(ITestResult result) {

		// ReportNG
		//ExtentManager.captureScreenshot();
		System.out.println("Inside on Test FAilures");
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("<a href=" + ExtentManager.srcfileName + " target=\"_blank\">Screenshot link</a>");
		Reporter.log("<br>");
		Reporter.log("<a href=" + ExtentManager.srcfileName + " target=\"_blank\"><img src=" + ExtentManager.srcfileName
				+ " height=200 width=200></a>");

		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		test.fail(excepionMessage);

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " FAILED" + "</b>";

		/*
		 * String screenshots = ExtentManager.srcfileName;
		 * testReport.get().fail("<b><font color=red>" + "Screenshot of failure" +
		 * "</font></b><br>",
		 * MediaEntityBuilder.createScreenCaptureFromPath(screenshots).build());
		 */

		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		test.log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		test.skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	
	  public void onStart(ITestContext context) {
	  System.out.println("In the start method of extent listener");
	  
	  }
	 

	public void onFinish(ITestContext context) {

		if (extent != null) {

			extent.flush();
		}
		
		File sourceFile = new File(System.getProperty("user.dir") + "//ExtentReports//" +"Extent_"+ExtentManager.ForLatest+".html");
		File targetDir = new File(System.getProperty("user.dir") + "//ExtentReports//Latest");

		if (targetDir.isDirectory() && targetDir.listFiles().length > 0) {
			try {
				FileUtils.cleanDirectory(targetDir);
				System.out.println("Directory was not empty and contents deleted.");
			} catch (IOException e) {
				System.err.println("Error cleaning directory: " + e.getMessage());
			}
		}

		try {
			FileUtils.copyFileToDirectory(sourceFile, targetDir);
			System.out.println("Folder contents copied successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		

	}

}
