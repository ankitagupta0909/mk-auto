package com.markit.common.framework.runtestsuit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.TakesScreenshot;
import org.apache.bcel.generic.Select;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

import com.markit.common.framework.util.Config;
import com.markit.common.framework.util.DBConnection;
import com.markit.common.framework.util.Email;
import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;
import com.markit.framework.pega.pageMethods.PEGANavigationBar;
import com.markit.framework.pega.pageMethods.PEGAUnassignedCases;
import com.markit.framework.pega.testcases.PEGALoginTC;
import com.markit.framework.pega.testcases.PEGARegTC;
import com.markit.framework.pega.testcases.PEGASanityTC;
import com.markit.kyc.framework.testcases.Admin_TC;
import com.markit.mcpm.framework.testcases.MCPMLoginTC;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,MethodListener.class,com.markit.common.framework.util.Listener_ExcelReportGenerator.class })

public class AdminPotal_RunTestSuit {
	{
		System.setProperty("atu.reporter.config","src/testdata/Reporter.properties");
	}
	Admin_TC admin=new Admin_TC();
	public static WebDriver driver;
	public static final Logger log = Logger.getLogger("appLogger");
	String caseId="920690220917279";
	
	@BeforeSuite
	public void init() throws IOException{
		String xpathPropFile = "src/testdata/XPath.properties";
		XPath.init(xpathPropFile);	
		Email.initMailConfig("src/testdata/Mail.properties");
	} 
	

	////////////////////////////////////////////////////////////////

@Test(priority=1)
	public void pegaRegression() throws Exception{	
		
	driver=admin.tc_Login_AdminPotal("tc_Login_AdminPotal_QA","src/testdata/testWorkbook.xlsx", "Login");
	
	

    	   
	}
		
	
}
////////////////////////////////////////////////////////////////////////////////


