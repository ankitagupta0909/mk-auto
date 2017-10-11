package com.markit.common.framework.runtestsuit;


import java.io.IOException;
import java.util.Map;

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
import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;
import com.markit.framework.mcpm.pageMethods.NavigationPage;
import com.markit.framework.pega.testcases.PEGALoginTC;
import com.markit.kyc.framework.testcases.LoginTC;
import com.markit.mcpm.framework.testcases.MCPMLoginTC;




@Listeners({ ATUReportsListener.class, ConfigurationListener.class,MethodListener.class,com.markit.common.framework.util.Listener_ExcelReportGenerator.class })

public class RunTestSuit {
	{
		System.setProperty("atu.reporter.config","src/testdata/Reporter.properties");
	}

	public static WebDriver driver;


	@BeforeSuite
	public void init() throws IOException{
		String xpathPropFile = "src/testdata/XPath.properties";
		XPath.init(xpathPropFile);	
		Map<String,String> dbConfig=Config.loadConfig("src/testdata/DBconfig.properties");
		DBConnection.getDBInstance(dbConfig, "MCPM");	
		//Email.initMailConfig("src/testdata/Mail_sanity.properties");		
		Email.initMailConfig("src/testdata/Mail.properties");
		XPath.init(xpathPropFile);		
		ExcelUtil.removeAllRowsExceptHeader("src/testdata/testWorkbook.xlsx", "MethodTestStatus");
	} 
 
	@Test(priority=1)
	public void verifySPLogin_Prod() throws Exception{
	    String filePath="src/testdata/testWorkbook.xlsx";
		driver = LoginTC.tc_01_Login_KYC("tc_subscriber_scheduledLoginTask_prod",filePath, "Login");

		WebUtil.waitUntilElementPresent(driver, "KYC.xpath.DashboardChartHeading");
    	String act=WebUtil.readElementText(driver, "KYC.xpath.DashboardChartHeading");
    	ReportUtil.reportStringMatch("Verify heading of chart to ensure page loads", "Client Onboarding Status Aging", act);
    	WebUtil.closeBrowser(driver);
	}

	@Test(priority=1)
	public void verifyPegaLogin_Prod() throws Exception{
	    String filePath="src/testdata/testWorkbook.xlsx";
	    driver=PEGALoginTC.tc_01_Login_PEGA("tc_pega_scheduledLoginTask_prod",filePath,"Login");
	    WebUtil.verifyElementDisplayed(driver,"PEGA.xpath.Logout");
	    WebUtil.closeBrowser(driver);
	}
	
	@Test(priority=1)
	public void verifyKycMcpmLogin_Prod() throws Exception{
	    String filePath="src/testdata/testWorkbook.xlsx";
		driver = MCPMLoginTC.tc_03_Login_MCPM("tc_mcpm_scheduledLoginTask_prod",filePath, "Login");
		NavigationPage.setMcpmFrame(driver);
		
		NavigationPage nav=new NavigationPage();
		nav.navigateToKYCServices(driver);
		NavigationPage.verifyMcpmPageLoad(driver, "KYCDashboard.xpath.EntityFilter");
		WebUtil.waitUntilElementClickable(driver, "KYCDashboard.xpath.EntityFilter");
    	WebUtil.verifyElementDisplayed(driver, "KYCDashboard.xpath.EntityFilter");
    	WebUtil.closeBrowser(driver);
	}
	
	@Test(priority=2)
	public void verifySPLogin_UAT() throws Exception{
	    String filePath="src/testdata/testWorkbook.xlsx";
		driver = LoginTC.tc_01_Login_KYC("tc_subscriber_scheduledLoginTask_uat",filePath, "Login");

		WebUtil.waitUntilElementPresent(driver, "KYC.xpath.DashboardChartHeading");
    	String act=WebUtil.readElementText(driver, "KYC.xpath.DashboardChartHeading");
    	ReportUtil.reportStringMatch("Verify heading of chart to ensure page loads", "Client Onboarding Status Aging", act);
    	WebUtil.closeBrowser(driver);
	}
	
	@Test(priority=2)
	public void verifyPegaLogin_UAT() throws Exception{
	    String filePath="src/testdata/testWorkbook.xlsx";
	    driver=PEGALoginTC.tc_01_Login_PEGA("tc_pega_scheduledLoginTask_uat",filePath,"Login");
	    WebUtil.verifyElementDisplayed(driver,"PEGA.xpath.Logout");
	    WebUtil.closeBrowser(driver);
	}
	
	@Test(priority=2)
	public void verifyKycMcpmLogin_UAT() throws Exception{
	    String filePath="src/testdata/testWorkbook.xlsx";
		driver = MCPMLoginTC.tc_03_Login_MCPM("tc_mcpm_scheduledLoginTask_uat",filePath, "Login");
		NavigationPage.setMcpmFrame(driver);
		
		NavigationPage nav=new NavigationPage();
		nav.navigateToKYCServices(driver);
		NavigationPage.verifyMcpmPageLoad(driver, "KYCDashboard.xpath.EntityFilter");
		WebUtil.waitUntilElementClickable(driver, "KYCDashboard.xpath.EntityFilter");
    	WebUtil.verifyElementDisplayed(driver, "KYCDashboard.xpath.EntityFilter");
    	WebUtil.closeBrowser(driver);
	}
	
/*	@Test(priority=3)
	public void verifySPLogin_QA() throws Exception{
	    String filePath="src/testdata/testWorkbook.xlsx";
		driver = LoginTC.tc_01_Login_KYC("tc_subscriber_scheduledLoginTask_qa",filePath, "Login");

		WebUtil.waitUntilElementPresent(driver, "KYC.xpath.DashboardChartHeading");
    	String act=WebUtil.readElementText(driver, "KYC.xpath.DashboardChartHeading");
    	ReportUtil.reportStringMatch("Verify heading of chart to ensure page loads", "Client Onboarding Status Aging", act);
    	WebUtil.closeBrowser(driver);
	}

	@Test(priority=3)
	public void verifyPegaLogin_QA() throws Exception{
	    String filePath="src/testdata/testWorkbook.xlsx";
	    driver=PEGALoginTC.tc_01_Login_PEGA("tc_pega_scheduledLoginTask_qa",filePath,"Login");
	    WebUtil.verifyElementDisplayed(driver,"PEGA.xpath.Logout");
	    WebUtil.closeBrowser(driver);
	}
	
	@Test(priority=3)
	public void verifyKycMcpmLogin_QA() throws Exception{
	    String filePath="src/testdata/testWorkbook.xlsx";
		driver = MCPMLoginTC.tc_03_Login_MCPM("tc_mcpm_scheduledLoginTask_qa",filePath, "Login");
		NavigationPage.setMcpmFrame(driver);
	
		NavigationPage nav=new NavigationPage();
		nav.navigateToKYCServices(driver);
		NavigationPage.verifyMcpmPageLoad(driver, "KYCDashboard.xpath.EntityFilter");
		WebUtil.waitUntilElementClickable(driver, "KYCDashboard.xpath.EntityFilter");
    	WebUtil.verifyElementDisplayed(driver, "KYCDashboard.xpath.EntityFilter");
    	WebUtil.closeBrowser(driver);
	}*/
	
	@AfterSuite
	public void updateResultAndSendEmail(){	
		try {
			ALMUtility.methodSheetUpdatewithTestStatus( "src/testdata/testWorkbook.xlsx","src/testdata/LoginmappingSheet.xlsx",
					  "LoginTCMapping", "FinalTestStatus", "MethodTestStatus");					
			Email.emailNotification("Login Verification-MCPM-KYC,PEGA and SP", "LoginmappingSheet.xlsx");			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/*String status=Email.readMethodTestStatus();	
if (status.equalsIgnoreCase("Passed")){}
else{
Email.emailNotification("Login Verification-MCPM-KYC,PEGA and SP", "LoginmappingSheet.xlsx");
}*/