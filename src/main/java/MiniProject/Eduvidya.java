package MiniProject;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;

//import MiniProject.DriverClass;

public class Eduvidya {
	
	ExtentedReport extRep = new ExtentedReport();
	
	//Return Driver Method
	public WebDriver webDriver()
	{
		return DriverClass.selectDriver();
	}
	
	//Load Webpage Method
	public void loadPage(String Url, WebDriver dr)throws Exception
	{
		dr.get(Url);
		dr.manage().window().maximize();
		extRep.extentReportPass(extRep.newTestCase("Test1: Load Page"), "Page has been loaded");
		
	}
	
	//Skip Security Check Method
	public void skipSecurityCheck(WebDriver dr)throws Exception
	{
		ExtentTest test2 = extRep.newTestCase("Test2: Skip Security Check");
		dr.findElement(By.cssSelector("button[id='details-button']")).click();
		extRep.extentReportPass(test2, "Clicked on Advanced");
		
		dr.findElement(By.cssSelector("a[id='proceed-link']")).click();
		extRep.extentReportPass(test2, "Clicked on proceed");
		
		
	}
	
	//Navigate to Colleges page Method
	public void navigatePage1(WebDriver dr)throws Exception
	{
		dr.findElement(By.xpath("//a[text()='Colleges']")).click();
		extRep.extentReportPass(extRep.newTestCase("Test3: Click on College link in the Menu bar"), "Clicked on College tab and opened search college page");
		
	}
	
	//Select Course Method
	public Select selectCourse(WebDriver dr)
	{
		WebElement droplist=dr.findElement(By.xpath("//select[@id='ddl_Category']"));
	
		Select courseDrop = new Select(droplist);
		return courseDrop;
	}
	
	//Select City Method
	public Select selectCity(WebDriver dr)
	{
		WebElement droplist = dr.findElement(By.xpath("//select[@id='ddl_City']"));
		
		Select cityDrop = new Select(droplist);
		return cityDrop;
		
	}
	
	//Click Search Method 
	public void searchClick(WebDriver dr)
	{
		dr.findElement(By.id("btnSearch")).click();
		extRep.extentReportPass(extRep.newTestCase("Test6: Click on \"Search\" button"), "Search button has been clicked");
	}
	
	//Retrieving the search result web elements
	public List<WebElement> searchResults(WebDriver dr)
	{
		List<WebElement> clgNames = dr.findElements(By.cssSelector("div[class='contentblock'] a"));
		return clgNames;
	}
	
	
	//printing results in console
	public void printResults(String[] results)
	{
		for(String res : results)
		{
			System.out.println(res);
		}
	}
	
	//Storing retrieved results on an array
	public String[] getResultArray(List<WebElement> clgNames)
	{
		String[] results = new String[clgNames.size()];
		
		for(int i = 0; i<clgNames.size(); i++)
		{
			results[i] = clgNames.get(i).getText();
		}
		
		return results;
	}
	
	//closing the web driver
	public void closeBrowser(WebDriver dr)
	{
		dr.close();
	}
	
	//comparing the results
	public String verifyResults(String[] expRes, String[] actRes)
	{
		if(Arrays.equals(expRes,actRes))
		{
			extRep.extentReportPass(extRep.newTestCase("Test7: Verify Results"), "Test Passed: Result Matching");
			return "Test Passed : Results Matching";
		}
		else
		{
			extRep.extentReportFail(extRep.newTestCase("Test7: Verify Results"), "Test Failed: Result Not Matching");
			return "Test Failed : Results does'nt Match";
		}
	}

}
