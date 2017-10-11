package com.markit.kyc.framework.testcases;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.framework.kyc.pageMethods.EntityScreener;
import com.markit.framework.kyc.pageMethods.Navigationbar;

public class EntityScreener_ViewIndividuals_TC {
	
	public void tc_01_IndivNameFilter(WebDriver driver, String testcasename, String testdatasheetpath) throws Exception{
		List<String> testdata;

        testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,"TestDataSheet","TestCaseName",testcasename);
		Navigationbar.navigateToViewIndividuals(driver);
		//Set filter value
		String IndivNameFieldValue=ExcelUtil.fn_FetchFieldValue(testdata,"IndivNameVal");
		driver=EntityScreener.setFilter(driver, "Individual Name", "EntityScreener.xpath.IndividualNameLabel", "EntityScreener.xpath.IndividualNameChkbx", "EntityScreener.xpath.IndividualNameText", IndivNameFieldValue);
		EntityScreener.defocusFilter(driver);
		EntityScreener.readKYCFilterLabel(driver, "EntityScreener.xpath.IndividualNameLabel", "Span", IndivNameFieldValue);
		EntityScreener.VerifyFilterMatches(driver, "EntityScreener.xpath.EntityFilterResultTable", "EntityScreener.xpath.IndividualNameLabel", "Individual Name", IndivNameFieldValue);
	
	}
	
	public void tc_02_IndivMEIFilter(WebDriver driver, String testcasename, String testdatasheetpath) throws Exception{
		List<String> testdata;

        testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,"TestDataSheet","TestCaseName",testcasename);
		Navigationbar.navigateToViewIndividuals(driver);
		//Set filter value
		String IndivMEIFieldValue=ExcelUtil.fn_FetchFieldValue(testdata,"IndivMEIVal");
		driver=EntityScreener.setFilter(driver, "Individual MEI", "EntityScreener.xpath.IndividualMEILabel", "EntityScreener.xpath.IndividualMEIChkbx", "EntityScreener.xpath.IndividualMEIText", IndivMEIFieldValue);
		EntityScreener.defocusFilter(driver);
		EntityScreener.readKYCFilterLabel(driver, "EntityScreener.xpath.IndividualMEILabel", "Span", IndivMEIFieldValue);
		EntityScreener.VerifyFilterMatches(driver, "EntityScreener.xpath.FilterResultTable", "EntityScreener.xpath.IndividualMEILabel", "Individual MEI", IndivMEIFieldValue);
	}
	
	public void tc_03_CountryFilter(WebDriver driver, String testcasename, String testdatasheetpath) throws Exception{
		List<String> testdata;

        testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,"TestDataSheet","TestCaseName",testcasename);
		Navigationbar.navigateToViewIndividuals(driver);
		//Set filter value
		String CountryFieldValue=ExcelUtil.fn_FetchFieldValue(testdata,"CountryVal");
		driver=EntityScreener.setFilter(driver, "Country", "EntityScreener.xpath.CountryLabel", "EntityScreener.xpath.CountryChkbx", "EntityScreener.xpath.CountryText", CountryFieldValue);
		EntityScreener.defocusFilter(driver);
		EntityScreener.readKYCFilterLabel(driver, "EntityScreener.xpath.CountryLabel", "Span", CountryFieldValue);
		EntityScreener.VerifyFilterMatches(driver, "EntityScreener.xpath.FilterResultTable", "EntityScreener.xpath.CountryLabel", "Country", CountryFieldValue);
	}
	
	public void tc_04_RoleFilter(WebDriver driver, String testcasename, String testdatasheetpath) throws Exception{
		List<String> testdata;

        testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,"TestDataSheet","TestCaseName",testcasename);
		Navigationbar.navigateToViewIndividuals(driver);
		//Select filter value
		String RoleValue=ExcelUtil.fn_FetchFieldValue(testdata,"RoleVal");
		driver=EntityScreener.selectFilter(driver, "EntityScreener.xpath.RoleLabel");
		EntityScreener.selectChkbx(driver, "EntityScreener.xpath.RoleChkbx");
		EntityScreener.defocusFilter(driver);
		EntityScreener.readKYCFilterLabel(driver, "EntityScreener.xpath.RoleLabel", "Span", RoleValue);
		EntityScreener.VerifyFilterMatches(driver, "EntityScreener.xpath.FilterResultTable", "EntityScreener.xpath.RoleLabel", "Role", RoleValue);
	}
	
	public void tc_05_EntityNameFilter(WebDriver driver, String testcasename, String testdatasheetpath) throws Exception{
		List<String> testdata;

        testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,"TestDataSheet","TestCaseName",testcasename);
		Navigationbar.navigateToViewIndividuals(driver);
		//Set filter value
		String ENameFieldValue=ExcelUtil.fn_FetchFieldValue(testdata,"EntNameVal");
		driver=EntityScreener.setFilter(driver, "Entity Name", "EntityScreener.xpath.EntityNameLabel", "EntityScreener.xpath.EntityNameChkbx", "EntityScreener.xpath.EntityNameText", ENameFieldValue);
		EntityScreener.defocusFilter(driver);
		EntityScreener.readKYCFilterLabel(driver, "EntityScreener.xpath.EntityNameLabel", "Span", ENameFieldValue);
		EntityScreener.VerifyFilterMatches(driver, "EntityScreener.xpath.FilterResultTable", "EntityScreener.xpath.EntityNameLabel", "Entity Name", ENameFieldValue);
	}
	
	public void tc_06_EntityMEIFilter(WebDriver driver, String testcasename, String testdatasheetpath) throws Exception{
		List<String> testdata;

        testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,"TestDataSheet","TestCaseName",testcasename);
		Navigationbar.navigateToViewIndividuals(driver);
		//Set filter value
		String EMEIFieldValue=ExcelUtil.fn_FetchFieldValue(testdata,"EntMEIVal");
		driver=EntityScreener.setFilter(driver, "Entity MEI", "EntityScreener.xpath.EntityMEILabel", "EntityScreener.xpath.EntityMEIChkbx", "EntityScreener.xpath.EntityMEIText", EMEIFieldValue);
		EntityScreener.defocusFilter(driver);
		EntityScreener.readKYCFilterLabel(driver, "EntityScreener.xpath.EntityMEILabel", "Span", EMEIFieldValue);
		EntityScreener.VerifyFilterMatches(driver, "EntityScreener.xpath.FilterResultTable", "EntityScreener.xpath.EntityMEILabel", "Entity MEI", EMEIFieldValue);
	}
}



