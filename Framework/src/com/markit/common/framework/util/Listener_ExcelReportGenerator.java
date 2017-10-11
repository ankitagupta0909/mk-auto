package com.markit.common.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.xml.sax.SAXException;



public class Listener_ExcelReportGenerator implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {		
	}
	@Override
	public void onTestSuccess(ITestResult result) {

		int method_status_id=result.getStatus();
		String method_status=returnStatusName(method_status_id);		
		String method_name=result.getName();
		System.out.println("printing name "+method_name);
		System.out.println("printing Status "+method_status);

		try {		   
			generateExcelReport(method_name,method_status);			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	@Override
	public void onTestFailure(ITestResult result) {

		int method_status_id=result.getStatus();
		String method_status=returnStatusName(method_status_id);	
		String method_name=result.getName();
		System.out.println("printing name "+method_name);
		System.out.println("printing Status "+method_status);		
		try {		   
			generateExcelReport(method_name,method_status);			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		int method_status_id=result.getStatus();
		String method_status=returnStatusName(method_status_id);		
		String method_name=result.getName();
		System.out.println("printing name "+method_name);
		System.out.println("printing Status "+method_status);		
		try {		   
			generateExcelReport(method_name,method_status);			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {		
	}
	@Override
	public void onStart(ITestContext context) {		
	}
	@Override
	public void onFinish(ITestContext context) {		
	}


	public static void generateExcelReport(String test_method_name,String test_method_status) throws ParserConfigurationException, SAXException, IOException{

		FileInputStream fis = new FileInputStream(new File("src/testdata/testWorkbook.xlsx"));
		XSSFWorkbook book=new XSSFWorkbook(fis);
		XSSFSheet sheet=book.getSheet("MethodTestStatus");

		int	rowIndex=sheet.getPhysicalNumberOfRows();
		XSSFRow row=sheet.createRow(rowIndex++);

		XSSFCell cel_name=row.createCell(0);
		cel_name.setCellValue(test_method_name);
		XSSFCell cel_status=row.createCell(1);
		cel_status.setCellValue(test_method_status);
		fis.close();

		FileOutputStream fos = new FileOutputStream("src/testdata/testWorkbook.xlsx");
		book.write(fos);
		fos.close();
		book.close();
		System.out.println( " is successfully written");
	}



	public static String returnStatusName (int method_status_id){
		switch(method_status_id) {
		case 1:
			return "Pass";
		case 2:
			return "Fail";
		case 3:
			return "Skip";
		case 4:
			return "SUCCESS_PERCENTAGE_FAILURE";
		case 16:
			return "Started";
		}
		return null;	
	}	
}
