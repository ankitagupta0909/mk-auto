package com.markit.common.framework.runtestsuit;

import java.io.FileNotFoundException;
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
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;
import com.markit.framework.pega.testcases.PEGALoginTC;
import com.markit.framework.pega.testcases.PEGASanityTC;
import com.markit.kyc.framework.testcases.LoginTC;
import com.markit.mcpm.framework.testcases.MCPMLoginTC;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,MethodListener.class,com.markit.common.framework.util.Listener_ExcelReportGenerator.class })

public class Pega_RunTestSuit {
	{
		System.setProperty("atu.reporter.config","src/testdata/Reporter.properties");
	}

	public static WebDriver driver;

	@BeforeSuite
	public void init() throws IOException{
		String xpathPropFile = "src/testdata/XPath.properties";
		XPath.init(xpathPropFile);	
		Map<String,String> dbConfig=Config.loadConfig("src/testdata/DBconfig.properties");
		DBConnection.getDBInstance(dbConfig, "PEGA");	
		Email.initMailConfig("src/testdata/Mail.properties");

	} 

	////////////////////////////////////////////////////////////////
	
	@Test(priority=1)
	public void pegaSanity_part1() throws Exception{	
		PEGASanityTC tc= new PEGASanityTC();
		driver=PEGALoginTC.tc_01_Login_PEGA("tc_01_Login_PEGA_TM","src/testdata/testWorkbook.xlsx","Login");
		
        tc.tc_01_caseFetch(driver);
        WebUtil.wait(7000);
		tc.tc_02_caseAssign_firstTime(driver);
		tc.tc_03_UploadDoc(driver);
		tc.tc_04_EditDocumentDetails(driver);
		tc.tc_05_bulkAttachDocument(driver);
		tc.tc_06_dataEnrichment(driver);		
	    tc.tc_07_taskCreation(driver);
		WebUtil.closeBrowser(driver);
		
		driver=PEGALoginTC.tc_01_Login_PEGA("tc_01_Login_PEGA_CSA","src/testdata/testWorkbook.xlsx","Login");
		WebUtil.wait(6000);
		tc.tc_08_taskCompletionByCsa(driver);
		WebUtil.closeBrowser(driver);
	     	} 


	@Test(priority=2)
	public void pegaSanity_part2() throws Exception{
		PEGASanityTC tc= new PEGASanityTC();
		driver=MCPMLoginTC.tc_03_Login_MCPM("tc_03_Login_PEGA_MCPM",
				"src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_09_kyc_completeTask(driver, null);
		WebUtil.closeBrowser(driver);
	}
	
	@Test(priority=3)
	public void pegaSanity_part3() throws Exception{
		PEGASanityTC tc= new PEGASanityTC();
		driver=PEGALoginTC.tc_01_Login_PEGA("tc_01_Login_PEGA_CSA","src/testdata/testWorkbook.xlsx","Login");
		tc.tc_10_CSAacceptsCompletedTasks(driver);
		WebUtil.closeBrowser(driver);
		
		driver=PEGALoginTC.tc_01_Login_PEGA("tc_01_Login_PEGA_TM","src/testdata/testWorkbook.xlsx","Login");
		tc.tc_11_sendCaseToSME(driver);
		WebUtil.closeBrowser(driver);
		
		driver=PEGALoginTC.tc_01_Login_PEGA("tc_01_Login_PEGA_SME","src/testdata/testWorkbook.xlsx","Login");
		tc.tc_12_caseAcceptedBySME(driver);
		//tc.tc_12_caseAcceptedBySME_UAT(driver);
		WebUtil.closeBrowser(driver);
		
		driver=PEGALoginTC.tc_01_Login_PEGA("tc_01_Login_PEGA_QA","src/testdata/testWorkbook.xlsx","Login");
		tc.tc_13_caseAcceptedByQA(driver);
		//tc.tc_13_caseAcceptedByQA_UAT(driver);
		WebUtil.closeBrowser(driver);
		
		driver=LoginTC.tc_01_Login_KYC("tc_03_Login_PEGA_KYC", "src/testdata/testWorkbook.xlsx","Login");
		tc.tc_14_VerifyReports(driver);
		WebUtil.closeBrowser(driver);
	
	}
	
	@Test(priority=4)
	public void dbDetails(){
		PEGASanityTC tc= new PEGASanityTC();
		tc.tc_14_verifyDbDetails();
	}
	
	@AfterSuite
	  
	  public void updateresult() throws FileNotFoundException, IOException{
	  
	  ALMUtility.methodSheetUpdatewithTestStatus( "src/testdata/testWorkbook.xlsx","src/testdata/PEGAmappingSheet.xlsx",
			  "PEGATestCaseMapping", "FinalTestStatus", "MethodTestStatus");
	  
	  String s=System.getProperty("user.dir")+"\\src\\testdata\\ALMUpdatebatchPEGA.bat";
	  String filePath=	s.replace("\\", "\\\\");

	  Runtime.getRuntime().exec("cmd.exe /C start " + filePath);
	 
	  Email.emailNotification("PEGA Suite", "PEGAmappingSheet.xlsx");
	 }
}
	

	////////////////////////////////////////////////////////////////////////////////
	

	