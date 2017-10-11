package com.markit.framework.pega.testcases;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;

import com.markit.framework.pega.pageMethods.PEGALogin;

public class PEGALoginTC {

	public static String docType=null;
	public static String docSource=null;
	public static String docSourceName=null;
	public static String editDocSourceName=null;
	public static String certifiedtrueCopy=null;
	public static String internallyConfirmed=null;
	public List<String> expectedData=null;
	
	

	
	
	public static WebDriver tc_01_Login_PEGA(String testcasename,String testdatasheetpath,String sheetname) throws Exception
	{
		ReportUtil.setRepHeader(testcasename);	
		List<String> testdata=ExcelUtil.fn_GetTestData(testdatasheetpath,sheetname,"AutomationTestCaseName",testcasename);
		String url=WebUtil.getURL(testcasename,testdatasheetpath,sheetname);
		String browsername=WebUtil.getBrowserName(testcasename,testdatasheetpath,sheetname);
		WebDriver driver=WebUtil.openBrowser(browsername, url);
		PEGALogin.loginPega(driver, testdata);
		
		return driver;
	}
	
	
}