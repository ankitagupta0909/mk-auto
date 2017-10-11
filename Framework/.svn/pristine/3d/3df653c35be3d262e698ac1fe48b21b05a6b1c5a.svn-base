package com.markit.kyc.framework.testcases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.jfree.util.Log;
import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.framework.kyc.pageMethods.Admin;
import com.markit.framework.kyc.pageMethods.Loginpage;
import com.markit.framework.mcpm.pageMethods.MCPMLoginpage;

public class Admin_TC extends Admin{
	
	public WebDriver tc_Login_AdminPotal(String testcasename,String testdatasheetpath,String sheetname) 
	{
		WebDriver driver = null;
		ReportUtil.setRepHeader(testcasename);	
		List<String> testdata;
		testdata = ExcelUtil.fn_GetTestData(testdatasheetpath,sheetname,"AutomationTestCaseName",testcasename);
		String url=WebUtil.getURL(testcasename,testdatasheetpath,sheetname);
		String browsername = WebUtil.getBrowserName(testcasename,testdatasheetpath,sheetname);
		driver=WebUtil.openBrowser(browsername, url);
		Loginpage.login(driver, testdata);				
		return driver;
	}
	
	
	public  String tc_BatchUploadEntity(WebDriver driver) throws IOException  {
		
		String legalName="";
		try{
	    legalName=writeCsv("src/testdata/BatchUploadEntityKYC.csv","legalNameUpdate");
	    WebUtil.wait(5000);
		System.out.println(legalName);
		navigateToBatchTab();
		uploadEntityTemplate();
		WebUtil.wait(7000);
		}catch(Exception e){
			Log.error("Batch upload failed"+e.getMessage());
		}finally{
		setCsvDefaultState("src/testdata/BatchUploadEntityKYC.csv",legalName,"legalNameUpdate");
		System.out.println("reverted");
		}
		return legalName;
		
	}
	
	
}
