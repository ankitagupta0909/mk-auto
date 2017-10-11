package com.markit.common.framework.runtestsuit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.markit.common.framework.util.ALMUtility;
import com.markit.common.framework.util.Config;
import com.markit.common.framework.util.DBConnection;
import com.markit.common.framework.util.Email;
import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;
import com.markit.framework.mcpm.pageMethods.NavigationPage;
import com.markit.mcpm.framework.testcases.MCPMLoginTC;
import com.markit.mcpm.framework.testcases.RFAHomePageTC;
import com.markit.mcpm.framework.testcases.RFALetterTemplateTC;
import com.markit.mcpm.framework.testcases.RFAMasterlistTC;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
	MethodListener.class,
	com.markit.common.framework.util.Listener_ExcelReportGenerator.class })


public class RFA_RunTestSuit {
	{
		System.setProperty("atu.reporter.config",
				"src/testdata/Reporter.properties");
	}

	public static WebDriver driver;

	@BeforeSuite
	public void init() throws IOException{
		String xpathPropFile = "src/testdata/XPath.properties";
		XPath.init(xpathPropFile);	
		Map<String,String> dbConfig=Config.loadConfig("src/testdata/DBconfig.properties");
		//DBConnection.getDBInstance(dbConfig, "PEGA");	
		Email.initMailConfig("src/testdata/Mail.properties");
		ExcelUtil.removeAllRowsExceptHeader("src/testdata/testWorkbook.xlsx", "MethodTestStatus");
	}


	// //////////////////////////////////////////////////////////////

	/*@Test(priority=11)
	public void verifyRFAHomePageFilters() throws Exception {

		driver = MCPMLoginTC.tc_03_Login_MCPM("tc_RfaFilters_MCPM",
				"src/testdata/testWorkbook.xlsx", "Login");

		RFA r=new RFA();
		RFAHomePageTC tc1 = new RFAHomePageTC();
		tc1.init(driver);
		tc1.tc_01_RefDataFilter(driver);
		r.clearFilters(driver);
		tc1.tc_02_MasterlistIdFilter(driver);
		r.clearFilters(driver);
		tc1.tc_03_PartyAFilter(driver);
		r.clearFilters(driver);
		tc1.tc_06_ReqStatusFilter(driver);
		r.clearFilters(driver);
		WebUtil.closeBrowser(driver);
	}

	@Test(priority=10)
	public void verifyRFALetterTempFilters() throws Exception {
		driver = MCPMLoginTC.tc_03_Login_MCPM("tc_RfaFilters_MCPM",
				"src/testdata/testWorkbook.xlsx", "Login");

		RFALetterTemplateTC tc2=new RFALetterTemplateTC();
		RFA r=new RFA();
		NavigationPage.setMcpmFrame(driver);
		tc2.init(driver);
		tc2.tc_01_TempNameFilter(driver);
		r.clearFilters(driver);
		tc2.tc_02_LastEditedByFilter(driver);
		r.clearFilters(driver);
		tc2.tc_03_CreatedByFilter(driver);
		WebUtil.closeBrowser(driver);
	}

	@Test(priority=9)
	public void verifyRFAExhibitTempFilters() throws Exception {
		driver = MCPMLoginTC.tc_03_Login_MCPM("tc_RfaFilters_MCPM",
				"src/testdata/testWorkbook.xlsx", "Login");

		RFAExhibitTemplateTC tc3 = new RFAExhibitTemplateTC();
		RFA r=new RFA();
		NavigationPage.setMcpmFrame(driver);
		//tc3.init(driver);
		//tc3.tc_01_ExhibNameFilter(driver);
		//r.clearFilters(driver);
		tc3.tc_02_CreatedByFilter(driver);
		r.clearFilters(driver);
		tc3.tc_03_PartyAFilter(driver);
		r.clearFilters(driver);
		tc3.tc_04_RefDateFilter(driver);
		r.clearFilters(driver);
		tc3.tc_05_MasIdFilter(driver);
		r.clearFilters(driver);
		tc3.tc_06_LinkedByFilter(driver);
		WebUtil.closeBrowser(driver);
	}

	@Test(priority=8)
	public void verifyRFAMasterlistFilters() throws Exception {
		driver = MCPMLoginTC.tc_03_Login_MCPM("tc_RfaFilters_MCPM",
				"src/testdata/testWorkbook.xlsx", "Login");

		RFAMasterlistTC tc4=new RFAMasterlistTC();
		RFA r=new RFA();
		NavigationPage.setMcpmFrame(driver);
		tc4.init(driver);
		//tc4.tc_01_RefDateFilter(driver);
		//r.clearFilters(driver);
		tc4.tc_02_MastIdFilter(driver);
		r.clearFilters(driver);
		tc4.tc_03_InvManagerFilter(driver);
		r.clearFilters(driver);
		tc4.tc_04_PartyAFilter(driver);
		WebUtil.closeBrowser(driver);
	}


	@Test(priority=7)
	public void verifyRFAHomePageCharts() throws Exception {

		driver = MCPMLoginTC.tc_03_Login_MCPM("tc_RfaFilters_MCPM",
				"src/testdata/testWorkbook.xlsx", "Login");
		RFA r=new RFA();
		RFAHomePageTC tc=new RFAHomePageTC();
		NavigationPage.setMcpmFrame(driver);
		tc.init(driver);

		tc.tc_08_verifySubmittedStatusChart(driver);
		r.clearFilters(driver);
		tc.tc_09_verifyCompletedStatusChart(driver);
		r.clearFilters(driver);
		tc.tc_10_verifyPartCompletedStatusChart(driver);
		r.clearFilters(driver);
		tc.tc_11_verifyRecalledStatusChart(driver);
		r.clearFilters(driver);
		tc.tc_12_verifyRejectedStatusChart(driver);
		r.clearFilters(driver);
	}	
	
		@Test(priority=6)
	public void verifyPendingTaskCharts() throws Exception {

		driver = MCPMLoginTC.tc_03_Login_MCPM("tc_RfaFilters_MCPM",
				"src/testdata/testWorkbook.xlsx", "Login");
		RFA r=new RFA();
		RFAHomePageTC tc=new RFAHomePageTC();
		NavigationPage.setMcpmFrame(driver);
		tc.init(driver);
		
		tc.tc_pendingTasks_ElectronicSign(driver);
		r.clearFilters(driver);
		tc.tc_pendingTasks_ExhibitCompletion(driver);
		r.clearFilters(driver);
		tc.tc_pendingTasks_SendRFA(driver);
		r.clearFilters(driver);
		
	}
*/	 

	@Test(priority=1)
	public void rfaSanityTest_bsSendRFA(){


		driver= MCPMLoginTC.tc_03_Login_MCPM("tc_03_Login_2_MCPM",
				"src/testdata/testWorkbook.xlsx", "Login");

		RFAHomePageTC tc=new RFAHomePageTC();
		RFALetterTemplateTC tc1=new RFALetterTemplateTC();
		

		NavigationPage.setMcpmFrame(driver);
		tc.tc_bulkUploadEntityMasterlistTemplatesUpdate();
		tc.tc_bulkUploads(driver);
		String letterTemp=tc1.tc_createLetterTemplate(driver);
		
		tc.tc_createRFA(driver,letterTemp);
		tc.tc_editLinkedExhibit(driver);
		tc.tc_esign(driver);
		tc.tc_recallEditSendRFA(driver);

	}

	@Test(priority=2)
	public void rfaSanityTest_SSRejectRFA(){


		driver= MCPMLoginTC.tc_03_Login_MCPM("tc_03_Login_SS_MCPM",
				"src/testdata/testWorkbook.xlsx", "Login");

		NavigationPage.setMcpmFrame(driver);
		RFAHomePageTC tc=new RFAHomePageTC();
		NavigationPage np=new NavigationPage();
		np.navigateToRFA(driver);
		NavigationPage.setMcpmFrame(driver);
		tc.tc_rejectRFA(driver);
		
	}

	@Test(priority=3)
	public void rfaSanityTest_bsReSendRFA(){

		driver= MCPMLoginTC.tc_03_Login_MCPM("tc_03_Login_2_MCPM",
				"src/testdata/testWorkbook.xlsx", "Login");

        NavigationPage.setMcpmFrame(driver);
		RFAHomePageTC tc=new RFAHomePageTC();
		NavigationPage np=new NavigationPage();
		np.navigateToRFA(driver);
		NavigationPage.setMcpmFrame(driver);
		tc.tc_editSendRFA(driver);
	}


	@Test(priority=4)
	public void rfaSanityTest_SSAcceptsAllPartyB(){
		driver= MCPMLoginTC.tc_03_Login_MCPM("tc_03_Login_SS_MCPM",
				"src/testdata/testWorkbook.xlsx", "Login");
				
		NavigationPage.setMcpmFrame(driver);

		RFAHomePageTC tc=new RFAHomePageTC();
		NavigationPage np=new NavigationPage();
		np.navigateToRFA(driver);
		NavigationPage.setMcpmFrame(driver);
		tc.tc_SSresponse(driver);
		tc.tc_esign(driver);
	}
	

	
	

	@Test(priority=5)
	public void rfaSanityTest_bsVerifyMasterlist(){

		driver= MCPMLoginTC.tc_03_Login_MCPM("tc_03_Login_2_MCPM",
				"src/testdata/testWorkbook.xlsx", "Login");

        NavigationPage.setMcpmFrame(driver);
		NavigationPage np=new NavigationPage();
		np.navigateToRFA(driver);
		NavigationPage.setMcpmFrame(driver);
		RFAMasterlistTC masterLiast=new RFAMasterlistTC();
		masterLiast.tc_05_VerifyMasterListContent(driver);
		
	}	
	
@AfterMethod
public void closeBrowser(){
	WebUtil.closeBrowser(driver);
}

/*	@Test(priority=6)
	public void createAndDeleteTemplates(){
		
		driver= MCPMLoginTC.tc_03_Login_MCPM("tc_03_Login_2_MCPM",
				"src/testdata/testWorkbook.xlsx", "Login");

		RFALetterTemplateTC tc1=new RFALetterTemplateTC();
		RFAExhibitTemplateTC tc2 = new RFAExhibitTemplateTC();
		tc1.tc_createDeleteLetterTemplate(driver);
		tc2.tc_createDeleteExhibitTemplate(driver);
		WebUtil.closeBrowser(driver);
		
		
	}*/
	
	@AfterSuite
	  
	  public void updateresult() throws FileNotFoundException, IOException{
	  
	  ALMUtility.methodSheetUpdatewithTestStatus( "src/testdata/testWorkbook.xlsx","src/testdata/MCPMmappingSheet.xlsx",
			  "RFA", "FinalTestStatus", "MethodTestStatus");
	  
	  String s=System.getProperty("user.dir")+"\\src\\testdata\\ALMUpdatebatchMCPM.bat";
	  String filePath=	s.replace("\\", "\\\\");

	  Runtime.getRuntime().exec("cmd.exe /C start " + filePath);
	 
	  Email.emailNotification("RFA Regression Suite", "MCPMmappingSheet.xlsx");
	 }
}




