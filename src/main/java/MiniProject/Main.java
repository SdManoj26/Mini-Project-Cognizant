package MiniProject;

import java.io.File;
import java.io.IOException;
import java.time.Duration;



import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Main {
	
	public static WebDriver dr;
	public static String[] results, expRes;
	static String pageUrl, courseName, cityName, filePath, testResult;
	
	
	
	
	//Set Course Name and City Name to the variables
	public static void setCourseAndCityName(String course, String city)
	{
		courseName = course;
		cityName = city;
		
	}
	
	//Capture Screenshot method
	public static void captureScreenShot(String filePath)throws IOException
	{
		TakesScreenshot takeSS = (TakesScreenshot)dr;
		File src = takeSS.getScreenshotAs(OutputType.FILE);
		File trg = new File(filePath);
		FileUtils.copyFile(src, trg);
	}
	
	//Main Method
	public static void main(String args[]) throws Exception
	{
		
		ExtentedReport extRep = new ExtentedReport();
		Eduvidya edu = new Eduvidya();
		ExcelUtility xl = new ExcelUtility();
		
		//Retrieving input data from the ExcelFile
		xl.getExcelInputs();
		setCourseAndCityName(xl.courceName(), xl.cityName());
		
		//Setting up new Report
		extRep.setupExtentReport();
		
		//Creating a webdriver
		dr = edu.webDriver();
		dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//load Eduvidya Page
		pageUrl = "https://www.eduvidya.com/";
		edu.loadPage(pageUrl, dr);
		
		//skip the Security Check page
		edu.skipSecurityCheck(dr);
		
		//Capture Screenshot and navigate to Colleges page
		captureScreenShot("C:\\Users\\2318744\\eclipse-workspace\\MINIPROJECT\\Screenshots\\openApplication.png");
		edu.navigatePage1(dr);
		
		//Capture ScreenShot of Colleges page
		captureScreenShot("C:\\Users\\2318744\\eclipse-workspace\\MINIPROJECT\\Screenshots\\SelectCollege.png");
		
		//Select Course from the DropDownBox
		edu.selectCourse(dr).selectByVisibleText(courseName);
		extRep.extentReportPass(extRep.newTestCase("Test4: Select Course"), "Course has been selected");
		
		//Select City form the DropDownBox
		edu.selectCity(dr).selectByVisibleText(cityName);
		extRep.extentReportPass(extRep.newTestCase("Test5: Select City"), "City has been selected");
		
		//Capture Screenshot of inputs and click Search
		captureScreenShot("C:\\Users\\2318744\\eclipse-workspace\\MINIPROJECT\\Screenshots\\selectCourseAndCity.png");
		edu.searchClick(dr);
		
		//Print the results and store it in an array
		captureScreenShot("C:\\Users\\2318744\\eclipse-workspace\\MINIPROJECT\\Screenshots\\Results.png");
		results = edu.getResultArray(edu.searchResults(dr));
		edu.printResults(results);
		
		//Store the results in the Output Excel File
		xl.putExcelOutput(results);
		
		//Get the expected results from the OutPut Excel File
		expRes = xl.getExpectedResults(results.length);
		
		//Verify the Expected results and Actual Results and store the test results in Excel
		xl.verifyResults(results.length);
		
		//Verify the Results and print in console
		testResult = edu.verifyResults(expRes, results);
		System.out.println(" ");
		System.out.println(testResult);
		
		//Close the Browser
		edu.closeBrowser(dr);
		
		//Flush the Report
		extRep.extentReportFlush();
	}

}
