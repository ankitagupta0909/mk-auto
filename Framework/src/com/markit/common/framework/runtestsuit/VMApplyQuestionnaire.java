package com.markit.common.framework.runtestsuit;

import java.io.IOException;
import java.sql.Connection;
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
import com.markit.mcpm.framework.testcases.MCPMLoginTC;
import com.markit.mcpm.framework.testcases.McpmKycIntegrationTC;
import com.markit.mcpm.framework.testcases.PermissionTC;
import com.markit.mcpm.framework.testcases.VMApplyQuestionnaireTC;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,MethodListener.class,com.markit.common.framework.util.Listener_ExcelReportGenerator.class })


public class VMApplyQuestionnaire {
	Connection con=null;
	{
		System.setProperty("atu.reporter.config",
				"src/testdata/Reporter.properties");
	}

	public static WebDriver driver;
	VMApplyQuestionnaireTC vm=new VMApplyQuestionnaireTC();
	
	@BeforeSuite
	public void init() throws IOException{
		String xpathPropFile = "src/testdata/XPath.properties";
		XPath.init(xpathPropFile);	
		Map<String,String> dbConfig=Config.loadConfig("src/testdata/DBconfig.properties");
		con=DBConnection.getDBInstance(dbConfig, "KYC");	
		Email.initMailConfig("src/testdata/Mail.properties");
	}


	@Test(priority=1)
	public void verifyNewEntityOnKYCDashbaord()
	{
		
		driver = MCPMLoginTC.tc_03_Login_MCPM("tc_login_VMApplyQuestionnaire","src/testdata/testWorkbook.xlsx", "Login");
		vm.applyQuestionnaire(driver,con);
		WebUtil.closeBrowser(driver);

	
		

	}
	

}
