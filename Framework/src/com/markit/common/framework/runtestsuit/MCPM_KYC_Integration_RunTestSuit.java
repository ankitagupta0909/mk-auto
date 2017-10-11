package com.markit.common.framework.runtestsuit;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.markit.common.framework.util.Config;
import com.markit.common.framework.util.DBConnection;
import com.markit.common.framework.util.Email;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;
import com.markit.mcpm.framework.testcases.MCPMLoginTC;
import com.markit.mcpm.framework.testcases.McpmKycIntegrationTC;

public class MCPM_KYC_Integration_RunTestSuit {
	
	{
		System.setProperty("atu.reporter.config",
				"src/testdata/Reporter.properties");
	}

	public static WebDriver driver;
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

	}
	@Test(priority=5)
	public void verifyKYCDashboardForUploaedDocFromMCPM()
	{
	
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

	
	@Test(priority=11)
	public void verifyMutipleEntitiesTagToDocAndVerifyKYC()
	{	
		mcpm.tc_16_uploadDocForMultipleEntitiesAndVerifyOnDashboard(driver);
	
	}
	@Test(priority=11)
	public void verifyUntagEntityVerifyKYC()
	{	
		mcpm.tc_17_unTagAnEntityAndValidate(driver);
		WebUtil.closeBrowser(driver);
	
	}
}
