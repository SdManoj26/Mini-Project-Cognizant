package MiniProject;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentedReport {
	
	public static ExtentSparkReporter reporter;
	public static ExtentReports reports;
	public static ExtentTest testCase;
	
	
	
	public static void setupExtentReport()
	{
		reporter = new ExtentSparkReporter("C:\\Users\\2318744\\eclipse-workspace\\MINIPROJECT\\ExtentReports\\ExtentReport.html");
		
		reports = new ExtentReports();
		reports.attachReporter(reporter);
	}
	
	public static ExtentTest newTestCase(String testCaseName)
	{
		testCase = reports.createTest(testCaseName);
		return testCase;
	}
	
	public void extentReportLog(ExtentTest eduTest, String log)
	{
		eduTest.log(Status.INFO, log);
	}
	
	public static void extentReportPass(ExtentTest eduTest, String comment)
	{
		eduTest.pass(comment);
	}
	
	public void extentReportFail(ExtentTest eduTest, String comment)
	{
		eduTest.fail(comment);
	}
	
	public void extentReportFlush()
	{
		reports.flush();
	}

}
