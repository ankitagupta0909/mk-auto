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
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;
import com.markit.mcpm.framework.testcases.MCPMLoginTC;
import com.markit.mcpm.framework.testcases.McpmKycIntegrationTC;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,MethodListener.class,com.markit.common.framework.util.Listener_ExcelReportGenerator.class })

public class McpmKycIntegration_RunTestSuit {
	{
		System.setProperty("atu.reporter.config","src/testdata/Reporter.properties");
	}

	public static WebDriver driver;
    Logger log = Logger.getLogger("appLogger");
    McpmKycIntegrationTC mcpm=null;

  

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

		driver= MCPMLoginTC.tc_03_Login_MCPM("tc_Login_MCPM_BS_QA",
				"src/testdata/testWorkbook.xlsx", "Login");
	     McpmKycIntegrationTC tcc=new McpmKycIntegrationTC();
		tcc.tc_01_verifyNewEntityOnKycDashboard(driver);
		tcc.tc_02_verifyEntityDetailsOnKYC(driver);
		tcc.tc_03_verifyEditedEntityDetailsOnMcpm(driver);
		tcc.tc_04_verifyEditedEntityDetailsOnKYC(driver);
		tcc.tc_05_verifyEntityNotInScope(driver);
		WebUtil.closeBrowser(driver);
	}

	
/*	@Test(priority=2)
	public void mcpmKycIntegrationDocuments() throws Exception{

		driver = MCPMLoginTC.tc_03_Login_MCPM("tc_Login_MCPM_BS_QA",
				"src/testdata/testWorkbook.xlsx", "Login");
		McpmKycIntegrationTC mcpm=new McpmKycIntegrationTC();
		mcpm.tc_06_addDoc(driver);
		
		mcpm.tc_07_verifyTheAddedDocOnKYC(driver);
		
	    mcpm.tc_08_verifyDocGetsDownloadedFromDashboard(driver);
		
		mcpm.tc_09_verifyEditDocWithKYCSupportedDocType(driver);
		mcpm.tc_10_verifyEditedDoconKYCDashboard(driver);
		mcpm.tc_11_deleteKYCDocFromMCPM(driver);
		mcpm.tc_12_verifyNoDocIsAvilableOnDashBoard(driver);
		mcpm.tc_13_verifyNonSupportedDocNotReflectedInKYC(driver);
		mcpm.tc_14_uploadDocFromKYC(driver);
		mcpm.tc_15_downloadDocFromMCPM(driver);	
		mcpm.tc_16_uploadDocForMultipleEntitiesAndVerifyOnDashboard(driver);
		mcpm.tc_17_unTagAnEntityAndValidate(driver);
		WebUtil.closeBrowser(driver);
		
	
	}*/
	@Test(priority=1)
	public void verifyNewEntityOnKYCDashbaord()
	{
		
		driver = MCPMLoginTC.tc_03_Login_MCPM("tc_Login_MCPM_BS_QA",
				"src/testdata/testWorkbook.xlsx", "Login");
	 mcpm=new McpmKycIntegrationTC();
	
		mcpm.tc_01_verifyNewEntityOnKycDashboard(driver);
		

	}
	
	@Test(priority=2)
	public void verifyEntityDetailsOnKYCDashbaord()
	{
	
		mcpm.tc_02_verifyEntityDetailsOnKYC(driver);

	}
	
	@Test(priority=3)
	public void verifyEditedEntityDetailsOnMCPM()
	{
	
		mcpm.tc_03_verifyEditedEntityDetailsOnMcpm(driver);

	}
	
	@Test(priority=4)
	public void verifyEditedEntityDetailsOnKYC()
	{
	
		mcpm.tc_04_verifyEditedEntityDetailsOnKYC(driver);

	}
	@Test(priority=4)
	public void verifyNotinScopeEntitiesOnKYC()
	{
	
		mcpm.tc_05_verifyEntityNotInScope(driver);
		WebUtil.closeBrowser(driver);

	}
	@Test(priority=5)
	public void verifyKYCDashboardForUploaedDocFromMCPM()
	{
		driver = MCPMLoginTC.tc_03_Login_MCPM("tc_Login_MCPM_BS_QA",
				"src/testdata/testWorkbook.xlsx", "Login");
		 mcpm=new McpmKycIntegrationTC();
	
        mcpm.tc_06_addDoc(driver);
		mcpm.tc_07_verifyTheAddedDocOnKYC(driver);

	}
	@Test(priority=6)
	public void verifyUploadedDocDownLoadedFromKYCDashboard()
	{	
		mcpm.tc_08_verifyDocGetsDownloadedFromDashboard(driver);

	}
	@Test(priority=7)
	public void verifyEditedDocIsAvailableOnKYCDashboard()
	{	
		mcpm.tc_09_verifyEditDocWithKYCSupportedDocType(driver);
		mcpm.tc_10_verifyEditedDoconKYCDashboard(driver);

	}
	@Test(priority=8)
	public void verifyDeletedDocIsNotAvailableOnKYCDashboard()
	{	
		mcpm.tc_11_deleteKYCDocFromMCPM(driver);
		mcpm.tc_12_verifyNoDocIsAvilableOnDashBoard(driver);

	}
	@Test(priority=9)
	public void verifyNonSupportedDocIsNotAvailableOnKYCDashboard()
	{	
		mcpm.tc_13_verifyNonSupportedDocNotReflectedInKYC(driver);
		

	}
	@Test(priority=10)
	public void verifyUploadDocFromKYCDashboardDownlaodedFromMCPM()
	{	
		mcpm.tc_14_uploadDocFromKYC(driver);
		mcpm.tc_15_downloadDocFromMCPM(driver);		
	}

	
/*	@Test(priority=11)
	public void verifyMutipleEntitiesTagToDocAndVerifyKYC()
	{	
		mcpm.tc_16_uploadDocForMultipleEntitiesAndVerifyOnDashboard(driver);
	
	}
	@Test(priority=12)
	public void verifyUntagEntityVerifyKYC()
	{	
		mcpm.tc_17_unTagAnEntityAndValidate(driver);
		WebUtil.closeBrowser(driver);
	
	}*/
	
@AfterSuite	  
	  public void updateresult() throws FileNotFoundException, IOException{
	  
	  ALMUtility.methodSheetUpdatewithTestStatus( "src/testdata/testWorkbook.xlsx","src/testdata/MCPMmappingSheet.xlsx",
			  "IntegrationTestCaseMapping", "FinalTestStatus", "MethodTestStatus");	  
	  String s=System.getProperty("user.dir")+"\\src\\testdata\\ALMUpdatebatchMCPM.bat";
	  String filePath=	s.replace("\\", "\\\\");
	  Runtime.getRuntime().exec("cmd.exe /C start " + filePath);	 
	  Email.emailNotification("MCPM KYC Integration Suite", "MCPMmappingSheet.xlsx");
	 }
}