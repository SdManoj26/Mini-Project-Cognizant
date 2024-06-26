package MiniProject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public String inFilePath, outFilePath, courseName, cityName;
	public String[] expRes;
	
	
	XSSFWorkbook inBook, outBook;
	XSSFSheet input, output;
	XSSFRow inRow, outRow, headRow;
	XSSFCell courseCol, cityCol, actResultCell,expResultCell, noCell;
	XSSFCellStyle passStyle, failStyle;
	
	
	//Get Input Data from Excel
	public void getExcelInputs() throws IOException
	{
		inFilePath = "C:\\Users\\2318744\\eclipse-workspace\\MINIPROJECT\\DataSheets\\InputData.xlsx";
		
		FileInputStream inFile = new FileInputStream(inFilePath);
		
		inBook = new XSSFWorkbook(inFile);
		input = inBook.getSheet("Input");
		inRow = input.getRow(1);
		courseCol = inRow.getCell(0);
		cityCol = inRow.getCell(1);
	}
	
	//Get Expected Results from the Output Sheet
	public String[] getExpectedResults(int len) throws IOException
	{
		expRes = new String[len];
		inFilePath = "C:\\Users\\2318744\\eclipse-workspace\\MINIPROJECT\\DataSheets\\OutputData.xlsx";
		
		FileInputStream inFile = new FileInputStream(inFilePath);
		
		inBook = new XSSFWorkbook(inFile);
		input = inBook.getSheet("Output");
		
		for(int i=1; i<=len; i++)
		{
			inRow = input.getRow(i);
			expResultCell = inRow.getCell(1);
			expRes[i-1] = expResultCell.getStringCellValue();
		}
		return expRes;
	}
	
	//Verify the Expected Results and Actual Results
	public void verifyResults(int len)throws IOException
	{
		outFilePath = "C:\\Users\\2318744\\eclipse-workspace\\MINIPROJECT\\DataSheets\\OutputData.xlsx";
		
		FileInputStream outFile = new FileInputStream(outFilePath);
		outBook = new XSSFWorkbook(outFile);
	    output = outBook.getSheet("Output");
	    passStyle = outBook.createCellStyle();
	    failStyle = outBook.createCellStyle();
	    passStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    passStyle.setFillForegroundColor(IndexedColors.GREEN.index);
	    failStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    failStyle.setFillForegroundColor(IndexedColors.RED.index);
	    
	    
	    for(int i = 1; i<= len; i++)
	    {
	    	outRow = output.getRow(i);
	    	expResultCell = outRow.getCell(1);
	    	expRes[i-1] = expResultCell.getStringCellValue();
	    	actResultCell = outRow.getCell(2);
	    	noCell = outRow.createCell(3);
	    	if(expResultCell.getStringCellValue().equals(actResultCell.getStringCellValue()))
	    	{
	    		noCell.setCellValue("Passed");
	    		noCell.setCellStyle(passStyle);
	    	}
	    	else
	    	{
	    		noCell.setCellValue("Fail");
	    		noCell.setCellStyle(failStyle);
	    	}
	    }
	    
	    outFile.close();
	    
	    FileOutputStream outputFile = new FileOutputStream(outFilePath);
		
		outBook.write(outputFile);
		
		outputFile.close();
		outBook.close();
		
		
		
	}
	
	//Store the Actual Results in the Sheet
	public void putExcelOutput(String[] results) throws IOException
	{
		outFilePath = "C:\\Users\\2318744\\eclipse-workspace\\MINIPROJECT\\DataSheets\\OutputData.xlsx";
		
		FileInputStream outFile = new FileInputStream(outFilePath);
		
		outBook = new XSSFWorkbook(outFile);
	    output = outBook.getSheet("Output");
		
		for(int i = 0; i <results.length; i++)
		{
			outRow = output.getRow(i+1);
			if(outRow == null) {
		        outRow = output.createRow(i);
		    }
			
			actResultCell = outRow.getCell(2);
			if(actResultCell == null) {
		        actResultCell = outRow.createCell(2);
		    }
			actResultCell.setCellValue(results[i]);
			
			noCell = outRow.getCell(0);
			if(noCell == null)
			{
				noCell = outRow.createCell(0);
			}
			noCell.setCellValue(i+1);
			
			
		}
		
		outFile.close();
		
		FileOutputStream outputFile = new FileOutputStream(outFilePath);
		
		outBook.write(outputFile);
		
		outputFile.close();
		outBook.close();
		
	}
	
	//Return Course Name
	public String courceName()
	{
		courseName = courseCol.getStringCellValue();
		return courseName;
	}
	
	//Return City Name
	public String cityName()
	{
		cityName = cityCol.toString();
		return cityName;
	}

}
