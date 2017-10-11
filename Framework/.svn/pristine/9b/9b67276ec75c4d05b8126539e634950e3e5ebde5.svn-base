package com.markit.mcpm.framework.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.framework.mcpm.pageMethods.AdminPage;
import com.markit.framework.mcpm.pageMethods.MCPMLoginpage;
import com.markit.framework.mcpm.pageMethods.NavigationPage;

public class AdminPageTC {
	
	private static String companyName=null;
	private static String companyType=null;
	private static String number=null;
	private static String email=null;
	AdminPage admin= new AdminPage();
	WebDriver driver=null;
	
	public AdminPageTC() 
	{
		
		List<String> tc1=null;
		tc1 = ExcelUtil.fn_GetTestData("src/testdata/testWorkbook.xlsx","TestDataSheet","TestCaseName","tc_01_Create_Company");
		companyName=ExcelUtil.fn_FetchFieldValue(tc1,"CompanyName");
		companyType=ExcelUtil.fn_FetchFieldValue(tc1,"CompanyType");
		number=ExcelUtil.fn_FetchFieldValue(tc1,"NumberofCompanies");
		email=ExcelUtil.fn_FetchFieldValue(tc1,"EmailAddress");
		

	}
	
	public List<String> gettestdata()
	{
		List<String> expectedValues=new ArrayList<String>();
		expectedValues.add(companyName);
		expectedValues.add(companyType);
		expectedValues.add(number);
		expectedValues.add(email);
		return expectedValues;
		
	}
	
	
	public void tc_01_CreateCompanyAndOneUserWithinCom(WebDriver driver) 
	{
		int limit=Integer.parseInt(number);
		String companyId=null;
		String emailId=null;
		String compName=null;
		WebUtil.wait(10000);
		NavigationPage nav= new NavigationPage();
		System.out.println("Test");
		nav.navigateToAdminPage(driver);		
		for(int i=1;i<=limit;i++)	
		{
		compName=admin.addCompany(driver, companyName, companyType,i);
		companyId=admin.dbValidationForCompany(compName);
		emailId=admin.addUser(driver,compName, email, i);
		admin.dbValidationForUser(emailId, companyId);
		}
	}
	
	public void tc_02_CreateCompanyAndMultipleUsers(WebDriver driver) 
	{
		int limit=Integer.parseInt(number);
		String companyId=null;
		String emailId=null;
		NavigationPage nav= new NavigationPage();
		System.out.println("Test");
		nav.navigateToAdminPage(driver);
		String comName=admin.addCompany(driver, companyName, companyType,1);
		companyId=admin.dbValidationForCompany(comName);
		for(int i=1;i<=limit;i++)	
		{		
		emailId=admin.addUser(driver,comName, email, i);
		admin.dbValidationForUser(emailId, companyId);
		}
	}

}
