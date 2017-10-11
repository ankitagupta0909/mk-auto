package com.markit.common.framework.runtestsuit;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.markit.common.framework.util.Config;
import com.markit.common.framework.util.DBConnection;
import com.markit.common.framework.util.Email;
import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;
import com.markit.mcpm.framework.testcases.KYCDashBoardRegressionTC;
import com.markit.mcpm.framework.testcases.MCPMLoginTC;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;


@Listeners({ ATUReportsListener.class, ConfigurationListener.class,MethodListener.class,com.markit.common.framework.util.Listener_ExcelReportGenerator.class })

public class KYCDashBoardRegressionTestSuit {
	{
		System.setProperty("atu.reporter.config","src/testdata/Reporter.properties");
	}

	public static WebDriver driver;
	KYCDashBoardRegressionTC kyc= new KYCDashBoardRegressionTC();
	
	@BeforeSuite
	public void init() throws IOException{
		String xpathPropFile = "src/testdata/XPath.properties";
		XPath.init(xpathPropFile);	
		Map<String,String> dbConfig=Config.loadConfig("src/testdata/DBconfig.properties");
		DBConnection.getDBInstance(dbConfig, "MCPM");	
		Email.initMailConfig("src/testdata/Mail_sanity.properties");		
		XPath.init(xpathPropFile);		
		ExcelUtil.removeAllRowsExceptHeader("src/testdata/testWorkbook.xlsx", "MethodTestStatus");
	} 

    @Test(priority=1)
	public void verify_CreateANewCompanyAndAssignAnUser() 
	{	driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_Admin_QA","src/testdata/testWorkbook.xlsx", "Login");
		kyc.tc_01_CreateACompanyAndChangeCompanyOFUser(driver);
		WebUtil.closeBrowser(driver);
	}
	@Test(priority=2)
	public void verify_TandCForKYCUser()
	{	driver=MCPMLoginTC.tc_03_Login_MCPM("tc_Login_KYCDashboardRegression","src/testdata/testWorkbook.xlsx", "Login");
		kyc.tc_02_TermOfUse(driver);
		WebUtil.closeBrowser(driver);
	}

	@Test(priority=4)
	public void verify_NonKYCUserDoesHaveKYCMenu()
	{	driver= MCPMLoginTC.tc_03_Login_MCPM("tc_Login_KYCDashboardRegression2","src/testdata/testWorkbook.xlsx", "Login");
		kyc.tc_04_verifyNonKYCUser(driver);	
		WebUtil.closeBrowser(driver);
	}
	@Test(priority=5)
	public void verify_UserWithBSReadGetsKYCMenu()
	{
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_Admin_QA","src/testdata/testWorkbook.xlsx", "Login");
		kyc.tc_05_verifyBSReadRole(driver);
		WebUtil.closeBrowser(driver);
		driver=MCPMLoginTC.tc_03_Login_MCPM("tc_Login_KYCDashboardRegression2","src/testdata/testWorkbook.xlsx", "Login");
		kyc.tc_06_verifyBSReadKycDashbaord(driver);
		WebUtil.closeBrowser(driver);
	}
	@Test(priority=6)
	public void verify_CreateNBFIandFundEntity()
	{
		driver=MCPMLoginTC.tc_03_Login_MCPM("tc_Login_KYCDashboardRegression","src/testdata/testWorkbook.xlsx", "Login");
		kyc.tc_07_createFundandNonFundEntities(driver);
	}
	@Test(priority=7)
	public void verify_NBFIEntityAvilableinCAndI()
	{		
		//driver=MCPMLoginTC.tc_03_Login_MCPM("tc_Login_KYCDashboardRegression","src/testdata/testWorkbook.xlsx", "Login");
		kyc.tc_08_verifyNBFIEntitiesInComAction(driver);
	}
	@Test(priority=8)
	public void verify_EsignRole()
	{		
		kyc.tc_09_verifyEsignRole(driver);
		WebUtil.closeBrowser(driver);
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_AdminToStage","src/testdata/testWorkbook.xlsx", "Login");
		kyc.assignESignRole(driver);
		WebUtil.closeBrowser(driver);
	}
	@Test(priority=9)
	public void verify_CompleteCandI()
	{	
		driver=MCPMLoginTC.tc_03_Login_MCPM("tc_Login_KYCDashboardRegression","src/testdata/testWorkbook.xlsx", "Login");
		kyc.verifyCompleteCandA(driver);
	}
	@Test(priority=10)
	public void verify_WolfsbergQuestionnaireComplete()
	{	
		
		kyc.tc_10_verifyCompleteWolfsbergAMLQuestionnaire(driver);
	}
	@Test(priority=11)
	public void verify_DocumentsComplete()
	{		
		kyc.tc_11_verifyDocumentsTab(driver);
	}
	@Test(priority=12)
	public void verify_ContactsComplete()
	{		
		kyc.tc_12_verifyContactsTab(driver);
	}
	@Test(priority=13)
	public void verify_FundEntity()
	{		
		kyc.tc_13_verifyFundEntity(driver);
	}
	@Test(priority=14)
	public void verify_ExculedEntity()
	{	
		
		kyc.tc_14_verifyExculdedNonFundEntity(driver);
	}
	@Test(priority=15)
	public void verify_NonExculedEntity()
	{		
		kyc.tc_15_verifyNonExculdedNonFundEntity(driver);
	}
	@Test(priority=16)
	public void verify_EditEntity()
	{	
		kyc.tc_16_verifyEditEntity(driver);
	}
/*	@Test(priority=17)
	public void verify_CompleteCandIforExcyldedEntity()
	{		
		kyc.tc_17_verifyCIAFromEntityDetailsPage(driver);
	}*/
	@Test(priority=18)
	public void verify_UploadDocumentAndVerify()
	{		
		kyc.tc_18_verifyUploadAndDownloadFile(driver);
	}
	@Test(priority=19)
	public void verify_PermissionTab()
	{		
		kyc.tc_19_verifyPermissionPage(driver);
	}
	@Test(priority=20)
	public void verify_TaskTabDefaultState()
	{		
		kyc.tc_20_verifyTheTaskTabDefaultState(driver);
	}
	@Test(priority=21)
	public void verify_readinessGraph()
	{	
		
		kyc.tc_21_verifyKYCReadinessGraph(driver);
	}
	@Test(priority=22)
	public void verify_Training()
	{	
		
		kyc.tc_22_verifyTraining(driver);
		WebUtil.closeBrowser(driver);
	}
	@Test(priority=23)
	public void verify_Auditreport()
	{	
		driver=MCPMLoginTC.tc_03_Login_MCPM("tc_Login_KYCDashboardRegression","src/testdata/testWorkbook.xlsx", "Login");	
		kyc.tc_23_verifyAuditRepot(driver);
	}
	@Test(priority=24)
	public void verify_BulkInternalConfirmation()
	{	
		kyc.tc_24_BulkInternalConfirmation(driver);
	}
	@Test(priority=25)
	public void verify_BulkPermissionRequest()
	{	
		kyc.tc_25_bulkPermisisonRequest(driver);
	}
	@Test(priority=26)
	public void verify_Impersonate()
	{	
		kyc.tc_26_impersonateCompany(driver);
	}
/*	@Test(priority=27)
	public void verify_Filterse()
	{	
		driver=MCPMLoginTC.tc_03_Login_MCPM("tc_Login_KYCDashboardRegression","src/testdata/testWorkbook.xlsx", "Login");	
		kyc.tc_01_filter_EntityName(driver);
		kyc.tc_02_filter_EntityType(driver);
		kyc.tc_03_filter_KYCReadiness(driver);
		kyc.tc_04_filter_KYCReviewStatus(driver);
		kyc.tc_05_filter_MEI(driver);
		kyc.tc_06_filter_LEI(driver);
		kyc.tc_07_filter_ClientIdentifier(driver);
		kyc.tc_08_filters_Relationships(driver);
		
	}*/
}
