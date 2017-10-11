package com.markit.common.framework.runtestsuit;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.openqa.selenium.WebDriver;
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
import com.markit.framework.mcpm.pageMethods.MCPMLoginpage;
import com.markit.framework.mcpm.pageMethods.NavigationPage;
import com.markit.framework.mcpm.pageMethods.SDLPage;
import com.markit.mcpm.framework.testcases.EntityTC;
import com.markit.mcpm.framework.testcases.MCPMLoginTC;
import com.markit.mcpm.framework.testcases.SDLTC;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
	MethodListener.class,
	com.markit.common.framework.util.Listener_ExcelReportGenerator.class })

public class MCPMSDL_RunTestSuit {

	{
		System.setProperty("atu.reporter.config","src/testdata/Reporter.properties");

	}
	public static WebDriver driver;

	static List<String> cpEntitiesName=new ArrayList<String>();
	static String mcpmCPCompName=null;
	static String myEntityName=null;
	static Connection con=null;
	@BeforeSuite
	public void init() throws IOException{
		String xpathPropFile = "src/testdata/XPath.properties";
		XPath.init(xpathPropFile);	
		Map<String,String> dbConfig=Config.loadConfig("src/testdata/DBconfig.properties");
		con=DBConnection.getDBInstance(dbConfig, "KYC");	
		Email.initMailConfig("src/testdata/Mail.properties");


	} 
	@Test(priority=1)
	public void createCPEntities()
	{
		SDLTC sdl=new SDLTC();
		driver=MCPMLoginTC.tc_03_Login_MCPM("tc_Login_MCPM_SS_QA","src/testdata/testWorkbook.xlsx","Login");
		mcpmCPCompName=MCPMLoginpage.companyName;
		cpEntitiesName=sdl.tc_createCPEnt(driver, cpEntitiesName);		
	}

	@Test(priority=2)
	public void fillJurisdictionRequired()
	{
		SDLTC sdl=new SDLTC();		
		sdl.checkJurisdictionRequired(driver, cpEntitiesName.get(0));
		WebUtil.closeBrowser(driver);

	}

	@Test(priority=3)
	public void createMyEntity()
	{
		SDLTC sdl=new SDLTC();
		EntityTC entTc=new EntityTC();		
		driver=MCPMLoginTC.tc_03_Login_MCPM("tc_Login_MCPM_BS_QA","src/testdata/testWorkbook.xlsx","Login");
		myEntityName=entTc.createEntity(driver);
		System.out.println("My true leagal Name: "+myEntityName);
		sdl.tc_createCPRelForMyEntity(driver, myEntityName, mcpmCPCompName,cpEntitiesName.get(0));

	}
	@Test(priority=4)
	public void verifyAPIResponceforSDLRel() throws Exception
	{
		SDLTC sdl=new SDLTC();
		sdl.verifyAPIResponseAfterRelCreated(con, myEntityName, cpEntitiesName.get(0));

	}
	@Test(priority=5)
	public void fillJurisdictionDetails() throws Exception
	{	
		SDLTC sdl=new SDLTC();
		sdl.tc_fillDetailsAndVerifyEntityScreenerwithProducerDashbaord(driver, myEntityName);


	}


	@Test(priority=6)
	public void verifyJurisdictionRequiredOnPrd()
	{
		SDLTC sdl=new SDLTC();		
		sdl.checkRedDotsOnProducerPage(driver, myEntityName);

	}

	@Test(priority=7)
	public void addASandQuesstionnaire(){
		SDLTC sdl=new SDLTC();	
		NavigationPage.setMcpmFrame(driver);
		sdl.tc_addSignatory(driver, myEntityName);
		sdl.tc_fillSDLQuest(driver);
		sdl.tc_addRelQuest(driver);		
		sdl.tc_verifyRelIconOnSDLProducer(driver);
	}

	@Test(priority=8)
	public void signAndShare(){

		SDLTC sdl=new SDLTC();
		sdl.tc_signAndShareAndVerify(driver);
	}

	@Test(priority=9)
	public void verifyAPIResponseAfterSignAndShare() throws Exception
	{	
		SDLTC sdl=new SDLTC();	
		String filePath="src/testdata/SDL_TestData.xlsx";
		sdl.verifyAPIResponseAfterSignAndShare(filePath,filePath);
	}

	@Test(priority=10)
	public void unshare() throws Exception
	{		   
		SDLTC sdl=new SDLTC();	
		sdl.tc_unShare(driver);	   	

	}
	@Test(priority=11)
	public void verifyAPIResponseAfterUnshare() throws Exception
	{		   
		SDLTC sdl=new SDLTC();		  
		sdl.verifyAPIResponseAfterUnshare();		
		sdl.signAndShareAll(driver);
	}

	@Test(priority=12)
	public void editDetails(){

		SDLTC sdl=new SDLTC();
		sdl.tc_editEntDetails(driver);		
		sdl.verifyActionrequired(driver);	
		sdl.signAndShareAll(driver);
	}	
	@Test(priority=14)
	public void verifyAPIResponseWhenEditedDetailsAreShared() throws Exception
	{
		SDLTC sdl=new SDLTC();	
		String filePath="src/testdata/SDL_TestData_Edited.xlsx";	
		String filePathGenReg="src/testdata/SDL_TestData.xlsx";	
		sdl.verifyAPIResponseAfterSignAndShare(filePath,filePathGenReg);		
	}	
	@Test(priority=15)
	public void editGeneralInfo()
	{	
		SDLTC sdl=new SDLTC();	
		String filePath="src/testdata/SDL_TestData_Edited.xlsx";	
		sdl.editGeneralReg(driver, filePath);
		sdl.verifyActionrequired(driver);
		sdl.signAndShareAll(driver);
	}
	@Test(priority=16)
	public void verifyAPIAfterGenDetsAreEditedNShared() throws Exception
	{
		SDLTC sdl=new SDLTC();		
		String filePath ="src/testdata/SDL_TestData_Edited.xlsx";	
		sdl.verifyAPIResponseAfterSignAndShare(filePath,filePath);

	}
	@Test(priority=17)
	public void createCPRelForSecondEnt()
	{

		SDLTC sdl=new SDLTC();
		sdl.createCPRelWithSecondEntity(driver);
		sdl.verifySecondEntityonSDL(driver);
	}
	@Test(priority=18)
	public void checkReceiverDashboard()
	{
		SDLTC sdl=new SDLTC();	
		sdl.verifyTheReceiverDashboard(driver, cpEntitiesName.get(0),"tc_Login_MCPM_SS_QA");
	}

	/*	@Test(priority=14)
	   public void exportResult()
		{

			SDLTC sdl=new SDLTC();
			NavigationPage nav=new NavigationPage();
			driver=MCPMLoginTC.tc_03_Login_MCPM("tc_Login_MCPM_BS_QA","src/testdata/testWorkbook.xlsx","Login");
			nav.navigateToSDL(driver);
			sdl.downloadExportSheet(driver);
		}
	 */





}