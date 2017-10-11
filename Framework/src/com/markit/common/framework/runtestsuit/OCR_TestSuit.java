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
import com.markit.mcpm.framework.testcases.OcrTC;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,MethodListener.class,com.markit.common.framework.util.Listener_ExcelReportGenerator.class })

public class OCR_TestSuit {
	{
		System.setProperty("atu.reporter.config","src/testdata/Reporter.properties");
	}

	public static WebDriver driver;
    Logger log = Logger.getLogger("appLogger");
    OcrTC ocr=new OcrTC();

  

	@BeforeSuite
	public void init() throws IOException
	{
		String xpathPropFile = "src/testdata/XPath.properties";
		XPath.init(xpathPropFile);	
		Map<String,String> dbConfig=Config.loadConfig("src/testdata/DBconfig.properties");
		DBConnection.getDBInstance(dbConfig, "MCPM");	
		Email.initMailConfig("src/testdata/Mail.properties");
	}

	@Test(priority=1)
	public void verify_CreateANewFCMEntityInCP() 
	{	
	driver= MCPMLoginTC.tc_03_Login_MCPM("tc_Login_MCPM_SS_QA","src/testdata/testWorkbook.xlsx", "Login");
	ocr.tc_01_createAFCMEntity(driver);
	WebUtil.closeBrowser(driver);
	}
	@Test(priority=2)
	public void verify_CreateANewCompanyAndAssignAnUser() 
	{	
	driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_Admin_QA","src/testdata/testWorkbook.xlsx", "Login");
	ocr.tc_02_createAnewCompanyAndSwitchUser(driver);
	WebUtil.closeBrowser(driver);
	}
	@Test(priority=3)
	public void verify_TermsAndConditions() 
	{	
	driver= MCPMLoginTC.tc_03_Login_MCPM("tc_Login_MCPM_BS_QA","src/testdata/testWorkbook.xlsx", "Login");
	ocr.tc_03_verifyTandC(driver);	
	}
	@Test(priority=4)
	public void verify_checkManadatoryFields() 
	{	
	ocr.tc_mandatory(driver);
	}
	@Test(priority=5)
	public void verify_fillOCRDetails() 
	{	
	ocr.tc_04_fillDetails(driver);
	}
	@Test(priority=6)
	public void verify_filledOCRDetailsOnMatchGrid() 
	{	
	ocr.tc_05_verifyThefilledDetailsOnMatchGrid(driver);
	}
	@Test(priority=7)
	public void verify_EditFromChevron() 
	{	
	ocr.tc_06_verifyTheEditFromChevron(driver);
	}
	@Test(priority=8)
	public void verify_EditDetailsOnMatchGrid() 
	{	
	ocr.tc_07_verifyTheEditedDetailsOnMatchGrid(driver);
	}
	@Test(priority=9)
	public void verify_AddDetailsFromMatchPage() 
	{	
	ocr.tc_08_verifyAdddetailsFromMatchPage(driver);
	}
	@Test(priority=10)
	public void verify_AddedDetailsFromMatchPageonChevron() 
	{	
	ocr.tc_09_verifyTheAddedDetailsOnChevron(driver);
	}
	@Test(priority=11)
	public void verify_DeleteControllerDetailsFromChevron() 
	{	
	ocr.tc_10_deleteControllerDetailsFromChevron(driver);
	}
	@Test(priority=11)
	public void verify_DeleteControllerDetailsOnMatch() 
	{	
	ocr.tc_11_verifyThedeletedControllerAvilableOnMatch(driver);
	}
	@Test(priority=12)
	public void verify_DownloadDetails() 
	{	
	ocr.tc_12_verifyDownloadDetailsFromControllerTab(driver);
	}



    /*  @AfterSuite	  
	  public void updateresult() throws FileNotFoundException, IOException{
	  
	  ALMUtility.methodSheetUpdatewithTestStatus( "src/testdata/testWorkbook.xlsx","src/testdata/MCPMmappingSheet.xlsx",
			  "IntegrationTestCaseMapping", "FinalTestStatus", "MethodTestStatus");	  
	  String s=System.getProperty("user.dir")+"\\src\\testdata\\ALMUpdatebatchMCPM.bat";
	  String filePath=	s.replace("\\", "\\\\");
	  Runtime.getRuntime().exec("cmd.exe /C start " + filePath);	 
	  Email.emailNotification("MCPM KYC Integration Suite", "MCPMmappingSheet.xlsx");
	 }*/
}