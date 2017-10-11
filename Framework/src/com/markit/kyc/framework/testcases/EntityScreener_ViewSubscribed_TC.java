package com.markit.kyc.framework.testcases;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.framework.kyc.pageMethods.EntityScreener;
import com.markit.framework.kyc.pageMethods.Navigationbar;



public class EntityScreener_ViewSubscribed_TC {
	public static String EntNameFieldValue=null;

	public void tc_01_EntityNameFilter(WebDriver driver, String testcasename, String testdatasheetpath) throws Exception{
		List<String> testdata;
		testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,"TestDataSheet","TestCaseName",testcasename);
		Navigationbar.EntityNav(driver);
		//Set filter value
		String EntNameFieldValue=ExcelUtil.fn_FetchFieldValue(testdata,"EntInptVal");
		System.out.println(EntNameFieldValue);
		driver=EntityScreener.setFilter(driver, "Enity Name", "EntityScreener.xpath.EntNameLabel", "EntityScreener.xpath.EntNameChkbox", "EntityScreener.xpath.EntNameTextBox", EntNameFieldValue);
		//EntityScreener.defocusFilter(driver);
		EntityScreener.readKYCFilterLabel(driver, "EntityScreener.xpath.EntNameLabel", "Span", EntNameFieldValue);
		EntityScreener.VerifyFilterMatches(driver, "EntityScreener.xpath.EntityFilterResultTable", "EntityScreener.xpath.EntNameLabel", "Entity Name", EntNameFieldValue);
	}
	
	public void tc_02_MEIFilter(WebDriver driver, String testcasename, String testdatasheetpath) throws Exception{
		List<String> testdata;
		testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,"TestDataSheet","TestCaseName",testcasename);
		Navigationbar.EntityNav(driver);
		//Set filter value
		String MEIFieldValue=ExcelUtil.fn_FetchFieldValue(testdata,"MEIInptVal");
		driver=EntityScreener.setFilter(driver, "MEI", "EntityScreener.xpath.MEILabel", "EntityScreener.xpath.MEIChkbox", "EntityScreener.xpath.MEIFltrTextBox", MEIFieldValue);
		EntityScreener.defocusFilter(driver);
		EntityScreener.readKYCFilterLabel(driver, "EntityScreener.xpath.MEILabel", "Span", MEIFieldValue);
		EntityScreener.VerifyFilterMatches(driver, "EntityScreener.xpath.FilterResultTable", "EntityScreener.xpath.MEILabel", "MEI", MEIFieldValue);
	
	}
	
	public void tc_03_LEIFilter(WebDriver driver, String testcasename, String testdatasheetpath) throws Exception{
		List<String> testdata;
		testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,"TestDataSheet","TestCaseName",testcasename);
		Navigationbar.EntityNav(driver);
		//Set filter value
		String LEIFieldValue=ExcelUtil.fn_FetchFieldValue(testdata,"LEIInptVal");
		driver=EntityScreener.setFilter(driver, "LEI", "EntityScreener.xpath.LEILabel", "EntityScreener.xpath.LEIChkbox", "EntityScreener.xpath.LEITextBox", LEIFieldValue);
		EntityScreener.defocusFilter(driver);
		EntityScreener.readKYCFilterLabel(driver, "EntityScreener.xpath.LEILabel", "Span", LEIFieldValue);
		EntityScreener.VerifyFilterMatches(driver, "EntityScreener.xpath.FilterResultTable", "EntityScreener.xpath.LEILabel", "LEI", LEIFieldValue);
	}
	

	
	public void tc_04_TaxIDFilter(WebDriver driver, String testcasename, String testdatasheetpath) throws Exception{
		List<String> testdata;
		testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,"TestDataSheet","TestCaseName",testcasename);
		Navigationbar.EntityNav(driver);
		//Set filter value
		String TaxIDFieldValue=ExcelUtil.fn_FetchFieldValue(testdata,"TaxIDInptVal");
		driver=EntityScreener.setFilter(driver, "TaxID/EIN", "EntityScreener.xpath.TaxIDLabel", "EntityScreener.xpath.TaxIDChkbox", "EntityScreener.xpath.TaxIDFltrTextBox", TaxIDFieldValue);
		EntityScreener.defocusFilter(driver);
		EntityScreener.readKYCFilterLabel(driver, "EntityScreener.xpath.TaxIDLabel", "Span", TaxIDFieldValue);
		EntityScreener.VerifyFilterMatches(driver, "EntityScreener.xpath.FilterResultTable", "EntityScreener.xpath.TaxIDLabel", "Tax ID / EIN", TaxIDFieldValue);
	    
		
	}
	
	public void tc_05_VerifyReports(WebDriver driver) throws Exception
	{
		tc_01_EntityNameFilter(driver, "tc_01_EntityNameFilter", "src/testdata/testWorkbook.xlsx");
		EntityScreener es= new EntityScreener();
		es.clickAtEntityNameLink(driver, EntNameFieldValue);
		Thread.sleep(2000);
		es.clickAtReports(driver);
	}
	
}
