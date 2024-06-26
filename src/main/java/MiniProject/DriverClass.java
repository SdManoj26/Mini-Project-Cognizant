package MiniProject;

import java.util.Scanner;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


public class DriverClass {
	
	public static WebDriver driver;
	public static int browser;
	
	
	public static WebDriver chromeBrowser()
	{
		driver = new ChromeDriver();
		return driver;
	}
	
	public static WebDriver edgeBrowser()
	{
		driver = new EdgeDriver();
		return driver;
	}
	public static WebDriver selectDriver()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Select the Browser:\n 1.Chrome\n 2.Edge");
		browser = sc.nextInt();
		
		if( browser == 1)
		{
			chromeBrowser();
		}
		else if(browser == 2)
		{
			edgeBrowser();
		}
		else
		{
			System.out.println("Invalid ");
		}
		return driver;
	}

}
