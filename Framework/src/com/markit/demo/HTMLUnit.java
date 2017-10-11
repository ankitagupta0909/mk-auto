package com.markit.demo;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

import com.markit.common.framework.util.Config;
import com.markit.common.framework.util.Email;
import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;
import com.markit.framework.pega.pageMethods.PEGALogin;
import com.markit.framework.pega.testcases.PEGALoginTC;
import com.markit.framework.pega.testcases.PEGARegTC;
import com.markit.framework.pega.testcases.PEGASanityTC;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,MethodListener.class,com.markit.common.framework.util.Listener_ExcelReportGenerator.class })

public class HTMLUnit {
		{
			System.setProperty("atu.reporter.config","src/testdata/Reporter.properties");
		}

		public static WebDriver driver;
		public static final Logger log = Logger.getLogger("appLogger");
		@BeforeSuite
		public void init() throws IOException{
			String xpathPropFile = "src/testdata/XPath.properties";
			XPath.init(xpathPropFile);	
			Map<String,String> dbConfig=Config.loadConfig("src/testdata/DBconfig.properties");
			//DBConnection.getDBInstance(dbConfig, "PEGA");	
			Email.initMailConfig("src/testdata/Mail.properties");

		} 

		////////////////////////////////////////////////////////////////

		@Test(priority=1)
		public void pegaRegression() throws Exception{	
			PEGASanityTC tc= new PEGASanityTC();
			PEGARegTC tc1=new PEGARegTC();
			/*driver=PEGALoginTC.tc_01_Login_PEGA("tc_01_Login_PEGA_TM","src/testdata/testWorkbook.xlsx","Login");*/

			driver=PEGALoginTC.tc_01_Login_PEGA("tc_01_Login_PEGA_TM_UAT_New","src/testdata/testWorkbook.xlsx","Login");

			/*		String caseId="876670584795765";
	        tc.tc_01_caseFetchbyId(driver,caseId);
	        WebUtil.wait(7000);*/

			//tc1.tc_caseAssign_bycaseId(driver);
			tc1.tc_enterTestData_Scenario1(driver);
		} 

}
