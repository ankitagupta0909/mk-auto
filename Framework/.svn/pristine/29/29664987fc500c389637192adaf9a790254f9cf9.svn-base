package com.markit.kyc.framework.testcases;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.framework.kyc.pageMethods.EntityScreener;
import com.markit.framework.kyc.pageMethods.Navigationbar;

public class EntityScreener_SrchUnsub_TC {

	public void tc_01_Unsub_ENameFilter(WebDriver driver, String testcasename, String testdatasheetpath) throws Exception{
		List<String> testdata;

        testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,"TestDataSheet","TestCaseName",testcasename);
		Navigationbar.navigateToSrchUnsub(driver);
		//Set filter value
		String EnameFieldValue=ExcelUtil.fn_FetchFieldValue(testdata,"UnsubEVal");
		EntityScreener.EnterFilter(driver, "EntityScreener.xpath.EnterEntText", EnameFieldValue, "EntityScreener.xpath.Srchbtn");
		
		EntityScreener.VerifyFilterMatches(driver, "EntityScreener.xpath.EntityFilterResultTable", "EntityScreener.xpath.SearchMEI", "Entity Name", EnameFieldValue);
	}
	public void tc_02_Unsub_MEIFilter(WebDriver driver, String testcasename, String testdatasheetpath) throws Exception{
		List<String> testdata;

        testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,"TestDataSheet","TestCaseName",testcasename);
		Navigationbar.navigateToSrchUnsub(driver);
		//Set filter value
		String MEIFieldValue=ExcelUtil.fn_FetchFieldValue(testdata,"UnsubMEIVal");
		driver=EntityScreener.selectFilter(driver, "EntityScreener.xpath.SearchMEI");
		EntityScreener.EnterFilter(driver, "EntityScreener.xpath.EnterText", MEIFieldValue, "EntityScreener.xpath.Srchbtn");
		
		EntityScreener.VerifyFilterMatches(driver, "EntityScreener.xpath.FilterResultTable", "EntityScreener.xpath.SearchMEI", "MEI", MEIFieldValue);
	}
	
	public void tc_03_Unsub_LEIFilter(WebDriver driver, String testcasename, String testdatasheetpath) throws Exception{
		List<String> testdata;

        testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,"TestDataSheet","TestCaseName",testcasename);
		Navigationbar.navigateToSrchUnsub(driver);
		//Set filter value
		String LEIFieldValue=ExcelUtil.fn_FetchFieldValue(testdata,"UnsubLEIVal");
		driver=EntityScreener.selectFilter(driver, "EntityScreener.xpath.SearchLEI");
		EntityScreener.EnterFilter(driver, "EntityScreener.xpath.EnterText", LEIFieldValue, "EntityScreener.xpath.Srchbtn");
		
		EntityScreener.VerifyFilterMatches(driver, "EntityScreener.xpath.FilterResultTable", "EntityScreener.xpath.SearchLEI", "LEI", LEIFieldValue);

	}
	
	public void tc_04_Unsub_TaxIDFilter(WebDriver driver, String testcasename, String testdatasheetpath) throws Exception{
		List<String> testdata;

        testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,"TestDataSheet","TestCaseName",testcasename);
		Navigationbar.navigateToSrchUnsub(driver);
		//Set filter value
		String TaxIDFieldValue=ExcelUtil.fn_FetchFieldValue(testdata,"UnsubTaxVal");
		driver=EntityScreener.selectFilter(driver, "EntityScreener.xpath.SearchLEI");
		EntityScreener.EnterFilter(driver, "EntityScreener.xpath.EnterText", TaxIDFieldValue, "EntityScreener.xpath.Srchbtn");
		
		EntityScreener.VerifyFilterMatches(driver, "EntityScreener.xpath.FilterResultTable", "EntityScreener.xpath.SearchTax", "Tax ID / EIN", TaxIDFieldValue);
}
	public void tc_05_Unsub_RegIDFilter(WebDriver driver, String testcasename, String testdatasheetpath) throws Exception{
		List<String> testdata;

        testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,"TestDataSheet","TestCaseName",testcasename);
		Navigationbar.navigateToSrchUnsub(driver);
		//Set filter value
		String RegIDFieldValue=ExcelUtil.fn_FetchFieldValue(testdata,"UnsubRegVal");
		driver=EntityScreener.selectFilter(driver, "EntityScreener.xpath.SearchLEI");
		EntityScreener.EnterFilter(driver, "EntityScreener.xpath.EnterText", RegIDFieldValue, "EntityScreener.xpath.Srchbtn");
		
		EntityScreener.VerifyFilterMatches(driver, "EntityScreener.xpath.FilterResultTable", "EntityScreener.xpath.SearchReg", "Registration ID", RegIDFieldValue);
}
}

