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
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;
import com.markit.mcpm.framework.testcases.MCPMLoginTC;
import com.markit.mcpm.framework.testcases.PermissionTC;




@Listeners({ ATUReportsListener.class, ConfigurationListener.class,MethodListener.class,com.markit.common.framework.util.Listener_ExcelReportGenerator.class })

public class McpmDocAumPermission_RunTestSuit {
	{
		System.setProperty("atu.reporter.config","src/testdata/Reporter.properties");
	}

	public static WebDriver driver;
	PermissionTC tc=new PermissionTC();


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
	public void verify_docsPermissionLessThen20ToCP() throws Exception{
			
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_01_permissionLessThen20Doc(driver);
		WebUtil.closeBrowser(driver);
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_02_verifyLessThen20PermissionDocInCP(driver);
		WebUtil.closeBrowser(driver);
	}
	
    @Test(priority=2)
	public void verify_NonPermissionedCP() throws Exception{
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS2_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_03_verifyNonPermissionedCP(driver);
		WebUtil.closeBrowser(driver);
		
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_removeAccessOfDoc(driver);
		WebUtil.closeBrowser(driver);
		
	}
	
	@Test(priority=3)
	public void verify_20docsPermissionToCP() throws Exception{
		
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyTheCurrentDocCountInCP(driver);
		WebUtil.closeBrowser(driver);
		
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_04_permission20Doc(driver);
		WebUtil.closeBrowser(driver);
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyCountOfDocInCPAfterPermission(driver,20);
		WebUtil.closeBrowser(driver);	
		
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_removeAccessOfDoc(driver);
		WebUtil.closeBrowser(driver);
		
	}
	
	@Test(priority=4)
	public void verify_AlldocsPermission() throws Exception{
		
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyTheCurrentDocCountInCP(driver);
		WebUtil.closeBrowser(driver);
		
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_05_selectAllDoc_PermissionButton(driver);
		WebUtil.closeBrowser(driver);		

		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyCountOfDocInCPAfterPermission(driver,PermissionTC.numberOfDoc);
		WebUtil.closeBrowser(driver);	
		
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_removeAccessOfDoc(driver);
		WebUtil.closeBrowser(driver);
		
	}
	
	@Test(priority=5)
	public void verify_20DocEmailPermissionToCP() throws Exception{
		
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyTheCurrentDocCountInCP(driver);
		WebUtil.closeBrowser(driver);
		
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_06_20DocPermission_EmailButton(driver);
		WebUtil.closeBrowser(driver);		

		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyCountOfDocInCPAfterPermission(driver,20);
		WebUtil.closeBrowser(driver);	
		
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_removeAccessOfDoc(driver);
		WebUtil.closeBrowser(driver);
		
	}
	
	@Test(priority=6)
	public void verify_Less20AUMPermisson() throws Exception{
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyTheCurrentCountofAUMinCP(driver);
		WebUtil.closeBrowser(driver);
				
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_07_verifyLessThen20AUMandPermission(driver);
		WebUtil.closeBrowser(driver);
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyCountOfAUMAfeterPermissionInCP(driver,1);
		WebUtil.closeBrowser(driver);
		
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_removeAccessFromAUM(driver);
		WebUtil.closeBrowser(driver);
		
	}
	
	@Test(priority=7)
	public void verify_20AUMPermisson() throws Exception{
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyTheCurrentCountofAUMinCP(driver);
		WebUtil.closeBrowser(driver);
				
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_08_permission20AUM(driver);
		WebUtil.closeBrowser(driver);
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyCountOfAUMAfeterPermissionInCP(driver,20);
		WebUtil.closeBrowser(driver);
		
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_removeAccessFromAUM(driver);
		WebUtil.closeBrowser(driver);
		
	}
	
	@Test(priority=8)
	public void verify_SelectALLAUMPermisson() throws Exception{
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyTheCurrentCountofAUMinCP(driver);
		WebUtil.closeBrowser(driver);
				
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_09_selectAllAUM_PermissionButton(driver);
		WebUtil.closeBrowser(driver);
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyCountOfAUMAfeterPermissionInCP(driver,PermissionTC.numberOfDoc);
		WebUtil.closeBrowser(driver);
		
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_removeAccessFromAUM(driver);
		WebUtil.closeBrowser(driver);
		
	}
	
	@Test(priority=9)
	public void verify_20AUMPermissonEmail() throws Exception{
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyTheCurrentCountofAUMinCP(driver);
		WebUtil.closeBrowser(driver);
				
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_10_20AUMPermission_EmailButton(driver);
		WebUtil.closeBrowser(driver);
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyCountOfAUMAfeterPermissionInCP(driver,20);
		WebUtil.closeBrowser(driver);
		
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_removeAccessFromAUM(driver);
		WebUtil.closeBrowser(driver);
		
	}
	
	@Test(priority=10)
	public void verify_AllDOCPermissonEmail() throws Exception{
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyTheCurrentCountofAUMinCP(driver);
		WebUtil.closeBrowser(driver);
				
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_11_permmissionAllDocWithEmail(driver);
		WebUtil.closeBrowser(driver);
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyCountOfDocInCPAfterPermission(driver,20);
		WebUtil.closeBrowser(driver);
		
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_removeAccessOfDoc(driver);
		WebUtil.closeBrowser(driver);
		
	}
	
	@Test(priority=11)
	public void verify_AllAUMPermissonEmail() throws Exception{
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyTheCurrentCountofAUMinCP(driver);
		WebUtil.closeBrowser(driver);
				
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_12_permmissionAllAUMWithEmail(driver);
		WebUtil.closeBrowser(driver);
		
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_SS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_verifyCountOfAUMAfeterPermissionInCP(driver,20);
		WebUtil.closeBrowser(driver);
		
		driver= MCPMLoginTC.tc_01_Login_MCPMStage("tc_Login_MCPM_BS_QA_Stage","src/testdata/testWorkbook.xlsx", "Login");
		tc.tc_removeAccessFromAUM(driver);
		tc.cleanUp(driver);
		WebUtil.closeBrowser(driver);
		
	}



	
	
	
	
	@AfterSuite
	public void updateResultAndSendEmail(){	
		try {
			ALMUtility.methodSheetUpdatewithTestStatus( "src/testdata/testWorkbook.xlsx","src/testdata/MCPMmappingSheet.xlsx",
					  "DocAumPermission", "FinalTestStatus", "MethodTestStatus");					
			Email.emailNotification("Login Verification-MCPM-KYC,PEGA and SP", "MCPMmappingSheet.xlsx");			
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