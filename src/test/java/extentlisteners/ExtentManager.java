package extentlisteners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentManager {

	
	private static ExtentReports extent;

	public static ExtentReports createInstance(String fileName) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Team", "APITestingAutomation");
		extent.setSystemInfo("CompanyName", "JPet");
		extent.setSystemInfo("Build Date", getBuildDate());

		return extent;
	}

	private static String getBuildDate() {
		Date d = new Date();
		String tempdate = d.toString().replace(":", "_").replace(" ", "_");
		return tempdate;
	}

	public static String srcfileName;

	public static String ForLatest = getBuildDate();

}
