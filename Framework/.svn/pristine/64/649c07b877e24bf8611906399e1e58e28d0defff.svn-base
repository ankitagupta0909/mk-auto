package com.markit.mcpm.framework.testcases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.framework.kyc.pageMethods.Loginpage;
import com.markit.framework.mcpm.pageMethods.MCPMLoginpage;

public class MCPMLoginTC {
	public static String browsername=null;

	public static WebDriver tc_03_Login_MCPM(String testcasename,String testdatasheetpath,String sheetname) 
	{
		WebDriver driver = null;
		ReportUtil.setRepHeader(testcasename);	
		List<String> testdata;
		testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,sheetname,"AutomationTestCaseName",testcasename);
		String url=WebUtil.getURL(testcasename,testdatasheetpath,sheetname);
		browsername=WebUtil.getBrowserName(testcasename,testdatasheetpath,sheetname);
		driver=WebUtil.openBrowser(browsername, url);
		MCPMLoginpage.login1(driver, testdata);				
		return driver;
	}
	
	public static WebDriver tc_01_Login_MCPMStage(String testcasename,String testdatasheetpath,String sheetname) 
	{WebDriver driver = null;
		ReportUtil.setRepHeader(testcasename);	
		List<String> testdata;
		testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,sheetname,"AutomationTestCaseName",testcasename);
		String url=WebUtil.getURL(testcasename,testdatasheetpath,sheetname);
		String browsername=WebUtil.getBrowserName(testcasename,testdatasheetpath,sheetname);
		 driver=WebUtil.openBrowser(browsername, url);
		MCPMLoginpage.mcpmStageLogin(driver, testdata);
		return driver;
				
	}
	
}
