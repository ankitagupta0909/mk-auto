package com.markit.common.framework.runtestsuit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

import com.markit.common.framework.util.ALMUtility;
import com.markit.common.framework.util.Config;
import com.markit.common.framework.util.DBConnection;
import com.markit.common.framework.util.Email;
import com.markit.common.framework.util.XPath;
import com.markit.mcpm.framework.testcases.MCPMLoginTC;
import com.markit.mcpm.framework.testcases.McpmKycIntegrationTC;
import com.markit.mcpm.framework.testcases.ThunderHead_TC;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,MethodListener.class,com.markit.common.framework.util.Listener_ExcelReportGenerator.class })

public class ThunderHead_RunTestSuit {
	{
		System.setProperty("atu.reporter.config","src/testdata/Reporter.properties");
	}

	public static WebDriver driver;
    Logger log = Logger.getLogger("appLogger");

  

	@BeforeSuite
	public void init() throws IOException{

		String xpathPropFile = "src/testdata/XPath.properties";
		XPath.init(xpathPropFile);	
		Map<String,String> dbConfig=Config.loadConfig("src/testdata/DBconfig.properties");
		DBConnection.getDBInstance(dbConfig, "MCPM");	
		Email.initMailConfig("src/testdata/Mail.properties");

	}

	@Test(priority=1)
	public void mcpmKycIntegrationEntities() throws Exception{
		ThunderHead_TC th=new ThunderHead_TC();
		driver= MCPMLoginTC.tc_03_Login_MCPM("tc_Login_TH_QA",
				"src/testdata/testWorkbook.xlsx", "Login");
	    th.tc_01_AddDocBox(driver);
	
	}
	
/*	@AfterSuite
	  
	  public void updateresult() throws FileNotFoundException, IOException{
	  
	  ALMUtility.methodSheetUpdatewithTestStatus( "src/testdata/testWorkbook.xlsx","src/testdata/MCPMmappingSheet.xlsx",
			  "IntegrationTestCaseMapping", "FinalTestStatus", "MethodTestStatus");
	  
	  String s=System.getProperty("user.dir")+"\\src\\testdata\\ALMUpdatebatchMCPM.bat";
	  String filePath=	s.replace("\\", "\\\\");

	  Runtime.getRuntime().exec("cmd.exe /C start " + filePath);
	 
	  Email.emailNotification("MCPM KYC Integration Suite", "MCPMmappingSheet.xlsx");
	 }*/
}
