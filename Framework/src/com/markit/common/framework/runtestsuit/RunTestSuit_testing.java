package com.markit.common.framework.runtestsuit;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;

import com.markit.common.framework.util.Config;
import com.markit.common.framework.util.DBConnection;
import com.markit.common.framework.util.Email;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;
import com.markit.framework.mcpm.pageMethods.NavigationPage;
import com.markit.mcpm.framework.testcases.MCPMLoginTC;
//import com.markit.framework.mcpm.pageMethods.HomePage;
//import com.markit.framework.mcpm.pageMethods.MCPMLoginpage;


@Listeners({ ATUReportsListener.class, ConfigurationListener.class,MethodListener.class,com.markit.common.framework.util.Listener_ExcelReportGenerator.class })

public class RunTestSuit_testing {
	{
		System.setProperty("atu.reporter.config","src/testdata/Reporter.properties");
	}

	public static WebDriver driver;
    static Logger log = Logger.getLogger("appLogger");

  

	@BeforeSuite
	public void init() throws IOException{

		String xpathPropFile = "src/testdata/XPath.properties";
		XPath.init(xpathPropFile);	
		Map<String,String> dbConfig=Config.loadConfig("src/testdata/DBconfig.properties");
		DBConnection.getDBInstance(dbConfig, "KYC");	
		Email.initMailConfig("src/testdata/Mail.properties");
		
	}
	
	@Test
	public void verifyKycMcpmLogin_QA() throws Exception{
	    String filePath="src/testdata/testWorkbook.xlsx";
		driver = MCPMLoginTC.tc_03_Login_MCPM("tc_mcpm_scheduledLoginTask_qa",filePath, "Login");
		NavigationPage.setMcpmFrame(driver);
	
		NavigationPage nav=new NavigationPage();
		nav.navigateToKYCServices(driver);
		WebUtil.waitUntilElementClickable(driver, "KYCDashboard.xpath.EntityFilter");
    	WebUtil.verifyElementDisplayed(driver, "KYCDashboard.xpath.EntityFilter");
    	WebUtil.closeBrowser(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@Test
	public void abc() throws Exception{
		
		driver=
				MCPMLoginTC.tc_01_Login_MCPMStage("tc_mcpm_scheduledLoginTask_qa", "src/testdata/testWorkbook.xlsx", "Login");
		*/
		
		
/*		Workbook wb = ExcelUtil.getWorkBookObject("C:\\Users\\ankita.gupta\\Documents\\TestData_EntityDoc.xlsx");
		int rowNo=0;
		Row row=wb.getSheet("Sheet1").getRow(rowNo);
		int colNoEntName=ExcelUtil.getColumnNumberByColumnName("TrueLegalName", row);*/
		/*Workbook wbookObj=ExcelUtil.getWorkBookObject("C:\\Users\\ankita.gupta\\Documents\\TestData_EntityDoc.xlsx");
		Sheet sheetObj=wbookObj.getSheet("Entity");
		int rows=sheetObj.getPhysicalNumberOfRows();*/
		
		
/*		String wbPath="C:\\Users\\ankita.gupta\\Documents\\TestData_EntityDoc.xlsx";
		String sheetName="Entity";
		int rows=ExcelUtil.countOfTheRowsInSheet(wbPath,sheetName);
			for(int i=1;i<=rows;i++){
		String a=ExcelUtil.getColumnDataByColName(wbPath,sheetName,"TrueLegalName",i);
		System.out.println(a);*/
		
		
/*		
		driver= MCPMLoginTC.tc_03_Login_MCPM("tc_03_Login_2_MCPM",
				"src/testdata/testWorkbook.xlsx", "Login");
	     McpmKycIntegrationTC tcc=new McpmKycIntegrationTC();
		tcc.tc_01_verifyNewEntityOnKycDashboard(driver);
		tcc.tc_02_verifyEntityDetailsOnKYC(driver);
		tcc.tc_03_verifyEditedEntityDetailsOnMcpm(driver);
		tcc.tc_04_verifyEditedEntityDetailsOnKYC(driver);
		tcc.tc_05_verifyEntityNotInScope(driver);
	}	*/
		/*System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
		driver =new ChromeDriver();
		driver.get("http://ufeqa.markit.com");*/
		
		
		//excelReportName();

		


//	}
	
	/*
	public void unPermissonAllDocs(WebDriver driver)
	{
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.Permisson");
		WebUtil.wait(3000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.YesPermission");
		WebUtil.wait(3000);
		List <WebElement> chkbx=WebUtil.findElements("MCPMPerm.xpath.SelectAll", driver);
		chkbx.get(0).click();
		WebUtil.wait(3000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.RemovePermission");
		WebUtil.wait(3000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.YesPermission");
		WebUtil.wait(3000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.NoteOk");
		WebUtil.wait(3000);
	}
	


	public void sendEmailDG(String searchDG){

	WebUtil.javaExecuter(driver, "Doc.xpath.EmailButton");
	WebUtil.click("EmailDoc.xpath.To");
	WebUtil.setWebEdit("EmailDoc.xpath.TypeDistInp", "Distribution");
	WebUtil.wait(3000);
	WebUtil.click("EmailDoc.xpath.selectType");
	WebUtil.wait(5000);
	WebUtil.setWebEdit("EmailDoc.xpath.EnterSearchName", searchDG);
	WebUtil.sendEnterKeyWebEdit(driver,"EmailDoc.xpath.EnterSearchName");
	WebUtil.wait(5000);
	emailSearchContactGrid(driver,1,"EmailDoc.xpath.SelectRecord",searchDG);
	WebUtil.wait(5000);
	WebUtil.click("EmailDoc.xpath.Okbutton");
	WebUtil.wait(5000);
	WebUtil.click("EmailDoc.xpath.Sendbutton");
	WebUtil.wait(5000);
	WebUtil.click("EmailDoc.xpath.SentOK");
	WebUtil.wait(5000);
	}
	
	
	public static void emailSearchContactGrid(WebDriver driver,
			int colIndex, String WebTableXpathKey, String searchText) {
		WebElement table = WebUtil.findElement(WebTableXpathKey, driver);
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		String tab = XPath.myprop.get(WebTableXpathKey);
		List<String> cellValues = new ArrayList<String>();
		int rowsize = rows.size();
		for (int j = 2; j <= rowsize; j++) {
			WebElement rowValue = driver.findElement(By.xpath(tab
					+ "/tr[" + j + "]/td["+ colIndex +"]/div[contains(text(),'"+searchText+"')]"));
			
			Actions actions = new Actions(driver);
			actions.moveToElement(rowValue).doubleClick().build().perform();
			String sCellValue = rowValue.getText();
			System.out.println(sCellValue);
		}
	}

		*/


////////////////////////////////////////////////////////////////	
/*public  String excelReportName(){
String strRes="C:\\AutomationWorkspace\\Framework\\Results\\Reporter\\Results\\Run_181";
String filePath= strRes.replace("\\", "\\\\");
String value=null;
File file = new File(filePath);	
File[] files = file.listFiles();
for ( File eachfile : files) {
  if (eachfile.getName().endsWith(".xlsx")){
   value=eachfile.getName();
   System.out.println(eachfile.getName());
  }
}
return value;
}	*/
/////////////////////////////////////////////////////////////////////

 
}
	///////////////////////////////////////////////////////////////
	
	/*@Test
	public void verifyRFAHomePageFilters() throws Exception {
=======
		
		XPath.init(xpathPropFile);	
>>>>>>> .r3317

		driver = MCPMLoginTC.tc_03_Login_MCPM("tc_03_Login_1_MCPM",
				"src/testdata/testWorkbook.xlsx", "Login");

<<<<<<< .mine
		RFA r=new RFA();
		RFAHomePageTC tc1 = new RFAHomePageTC();
		tc1.init(driver);
		tc1.tc_03_PartyAFilter(driver);
		r.clearFilters(driver);

		WebUtil.closeBrowser(driver);
	}
	
	
	////////////////////////////////////////////////////////////////////////////////
	
	@AfterSuite

	public void updateresult() throws FileNotFoundException, IOException{
=======
	} 
 
	@Test
	public void verifyStageAdmin() throws Exception

		
		


		
	}
	
	{
		WebDriver driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_01_Login_MCPMStage","src/testdata/testWorkbook.xlsx", "Login");
		NavigationPage nav= new NavigationPage();
		//AdminPage a= new AdminPage();
		AdminPageTC a= new AdminPageTC();
>>>>>>> .r3317
		
<<<<<<< .mine
		ALMUtility.methodSheetUpdatewithTestStatus(
				  "src/testdata/testWorkbook.xlsx","src/testdata/MCPMmappingSheet.xlsx",
				  "RFA", "FinalTestStatus", "MethodTestStatus");
		 Runtime.getRuntime().exec("cmd /c start C:\\AutomationWorkspace\\Framework\\src\\testdata\\ALMUpdatebatch.bat");
	
	}
	*/
	
	

/*		
 * 
		log.debug("starting test "+ "abc");		
		
		log.debug("ending  testing abc");
		
		AdminPage p=new AdminPage();
		p.dbValidationForCompany("TEST MDE COMPANY");
		*/
/*		driver= MCPMLoginTC.tc_03_Login_MCPM("tc_03_Login_2_MCPM",
				"src/testdata/testWorkbook.xlsx", "Login");*/
/*		String a=WebUtil.returnXPathVal("RFA.xpath.SelectPartyARel_part1");
		String b=WebUtil.returnXPathVal("RFA.xpath.SelectPartyARel_part2");
		String im="AutoEnt3_-27214";
		String xpath= a+im+b;
		
		
		System.out.println(xpath);*/
		//driver.findElement(By.xpath(xpath));
		/*NavigationPage np=new NavigationPage();
		np.navigateToRFA(driver);
		NavigationPage.setMcpmFrame(driver);
	
	WebElement e=WebUtil.findElement("RFA.xpath.SelectPatyARel1", driver);
	WebUtil.elementHighlight(e);
	e.click();*/
	
		///Code to remove hardcoding
/*		System.out.println(System.getProperty("user.dir"));
		
		String s=System.getProperty("user.dir")+"\\src\\testdata\\ALMUpdatebatchPEGA.bat";
		String filePath=	s.replace("\\", "\\\\");

		Runtime.getRuntime().exec("cmd.exe /C start " + filePath);*/

		
		/*	String currentTimeMillis = new Long(System.currentTimeMillis()).toString();
	System.out.println(currentTimeMillis);
	


		
ArrayList<String> entityName=new ArrayList<String>();
ArrayList<String> lei=new ArrayList<String>();
ArrayList<String> entityType=new ArrayList<String>();

int randNo=WebUtil.generateRandomNumber();
for(int j=0;j<=3;j++){
entityName.add("AutoEnt"+j+"_"+randNo);
String LEI = WebUtil.generateRandomString(false, true,16);
lei.add("AUTO"+LEI);
}

for(int j=0;j<=2;j++){
entityType.add("Government");
}
entityType.add("Investment Management Company");

System.out.println(entityName.size());

 
try {
	Workbook wb = ExcelUtil.getWorkBookObject("D:\\MCPM\\Automation\\TestScenarios\\entity bulk.xlsx");
	int rowNo=0;
	Row row=wb.getSheet("Sheet1").getRow(rowNo);
	int colNoEntName=ExcelUtil.getColumnNumberByColumnName("EntityName", row);
	int colNoLEI=ExcelUtil.getColumnNumberByColumnName("LEI", row);
	int colNoEntityType=ExcelUtil.getColumnNumberByColumnName("EntityType", row);
	int colNoLegalName=ExcelUtil.getColumnNumberByColumnName("TrueLegalName", row);
	ExcelUtil.setCellValueBulkUpload(rowNo, colNoEntName, wb, entityName);
	ExcelUtil.setCellValueBulkUpload(rowNo, colNoLegalName, wb, entityName);
	ExcelUtil.setCellValueBulkUpload(rowNo, colNoLEI, wb, lei);
	ExcelUtil.setCellValueBulkUpload(rowNo, colNoEntityType, wb, entityType);
	
	ExcelUtil.writeExcel("D:\\MCPM\\Automation\\TestScenarios\\entity bulk.xlsx", wb);
	
}  catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	ReportUtil.reportElementException("Write Excel exception", LogAs.FAILED);
} 


try {
	Workbook wb = ExcelUtil.getWorkBookObject("D:\\MCPM\\Automation\\TestScenarios\\Onboarding22.xlsx");
	int rowNo=0;
	Row row=wb.getSheet("Sheet1").getRow(rowNo);
	int colNoIM=ExcelUtil.getColumnNumberByColumnName("InvestmentManager ", row);
	int colNoPartyB=ExcelUtil.getColumnNumberByColumnName("Party B Accounts True Legal Name", row);
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	Date date = new Date();
	String sDate= dateFormat.format(date);
			
	System.out.println(sDate);
	int colNoDate=ExcelUtil.getColumnNumberByColumnName("Reference Date", row);
			
	ArrayList<String> IM=new ArrayList<String>();
	ArrayList<String> dt=new ArrayList<String>();
	for(int i=0;i<=1;i++){
		
		IM.add(entityName.get(3));
		dt.add(sDate);
	}
	
	System.out.println(IM.size());
	ArrayList<String> entityNameUpdate=new ArrayList<String>();
	String entityDelete=entityName.get(0);
	String entityModify=entityName.get(1);
	entityNameUpdate.add(entityName.get(0));
	entityNameUpdate.add(entityName.get(1));
	System.out.println(entityNameUpdate.size());
	
	ExcelUtil.setCellValueBulkUpload(rowNo, colNoIM, wb, IM);
	ExcelUtil.setCellValueBulkUpload(rowNo, colNoPartyB, wb, entityNameUpdate);
	ExcelUtil.setCellValueBulkUpload(rowNo, colNoDate, wb, dt);
	
	ExcelUtil.writeExcel("D:\\MCPM\\Automation\\TestScenarios\\Onboarding22.xlsx", wb);
	
}  catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	ReportUtil.reportElementException("Write Excel exception", LogAs.FAILED);
} */

		
		//RFAHomePageTC tc=new RFAHomePageTC();
		
		
	//	tc.tc_bulkUploadEntityMasterlistTemplatesUpdate();

	
/*	@AfterSuite

	public void updateresult() throws FileNotFoundException, IOException{
		
		ALMUtility.methodSheetUpdatewithTestStatus(
				  "src/testdata/testWorkbook.xlsx","src/testdata/MCPMmappingSheet.xlsx",
				  "RFA", "FinalTestStatus", "MethodTestStatus");
		  String s=System.getProperty("user.dir")+"\\src\\testdata\\ALMUpdatebatchMCPM.bat";
		  String filePath=	s.replace("\\", "\\\\");

		  Runtime.getRuntime().exec("cmd.exe /C start " + filePath);

	
	}
*/
	/*public  void setCellValueBulkUpload(int rowNo,int colNo, Workbook wb,ArrayList<String> list){
		
		for(int i=0;i<=list.size()-1;i++){
			Row createRow=wb.getSheet("Sheet1").getRow(++rowNo);
			//Row createRow=wb.getSheet("Sheet1").createRow(++rowNo);
			Cell cell=createRow.createCell(colNo);
			cell.setCellValue(list.get(i));
		}
		
	}
	
	public static void writeExcel(String filePath,Workbook wb){
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filePath);
			wb.write(fos);
			fos.close();
			wb.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ReportUtil.reportElementException("Write Excel exception", LogAs.FAILED);
		} 
	
	}*/
	

	
	

	
	
	
	
	
	
	

	/*	@Test

	public void verifyAnnualReviewChart() throws Exception{
		driver=LoginTC.tc_01_Login_KYC("tc_01_Login_KYC","src/testdata/testWorkbook.xlsx","Login");
		Dashboard_AnnualReviewChart_TC arc= new Dashboard_AnnualReviewChart_TC();
		arc.tc_01_verifyAccOpeningInProg(driver);
		//arc.tc_02_verifyReadyForReview(driver);
		//arc.tc_03_verifyInProgress(driver);
		//arc.tc_04_verifyReviewed(driver);
		//arc.tc_05_verifyPreAccOpening(driver);
		WebUtil.closeBrowser(driver);
=======
		
		System.out.println("Test Add Company");
		a.tc_01_CreateCompanyAndOneUserWithinCom(driver);
		//WebDriver driver=MCPM_login.mcpmLogin();
		/*KYCDashboardPageTC k= new KYCDashboardPageTC();
		WebUtil.setFrame(driver, "MFrame");
		k.tc_10_verifyKYCTasksGraph(driver);*/
		//r.verify_MasterList(driver,"Active");
		//BatchUploadEntitypage b= new BatchUploadEntitypage();
		//b.batchUploadEntity(driver, "e");
		

	


	/*@Test
	public void verifyCreatecompnayAndUsers() throws Exception
	{	*/
		/*LoginTC l= new LoginTC();
		driver = LoginTC.tc_01_Login_KYC("tc_01_Login_KYC",
				"src/testdata/testWorkbook.xlsx", "Login");
		EntityScreener_ViewSubscribed_TC entity= new EntityScreener_ViewSubscribed_TC();
		entity.tc_05_VerifyReports(driver);*/
		/*PEGATestCases pega= new PEGATestCases();
		//driver=PEGATestCases.tc_01_Login_PEGA("tc_01_Login_PEGA","src/testdata/testWorkbook.xlsx","Login");
		WebDriver driver=LoginPega.pega();
		//pega.uploadDoc(driver);
*/	
        //driver=MCPMLoginTC.tc_03_Login_MCPM("tc_03_Login_KYC","src/testdata/testWorkbook.xlsx","Login");
		//Thread.sleep(1000);
		//WebDriver driver=MCPM_login.mcpmLogin();
		/*driver = MCPMLoginTC.tc_03_Login_MCPM("tc_01_Login_MCPM",
				"src/testdata/testWorkbook.xlsx", "Login");
		KYCDashboardPage dashboard= new KYCDashboardPage();
		dashboard.tc_01_verifyEntityCountOnKYCDashboardPage(driver);
		//dashboard.tc_09_verifyPriorityTasksViewGraph(driver);
		WebUtil.clickedWithAction(driver,"KYCDashboard.xpath.OutreachQuestionsGraph");
		Thread.sleep(10000);
		KYCDashboardTaskpage kyc= new KYCDashboardTaskpage();
		kyc.clickAtLinkAsPerPassedName(driver, "Outreach Questions");
		kyc.outReachQuestionnaireFill(driver);
		kyc.scrollDown(driver);
	 
		*/
		//System.out.println("AML");


		//WebDriver driver=MCPM_login.mcpmLogin();
		/*WebDriver driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_01_Login_MCPMStage","src/testdata/testWorkbook.xlsx", "Login");
		NavigationPage nav= new NavigationPage();
		AdminPage a= new AdminPage();
		nav.navigateToAdminPage(driver);
		System.out.println("Time out");
		System.out.println("Test Add Company");
		a.addCompany(driver, "Test Auto SS", "Sellside");*/
		/*AdminPageTC a= new AdminPageTC();
		driver=MCPMLoginTC.tc_01_Login_MCPMStage("tc_01_Login_MCPMStage","src/testdata/testWorkbook.xlsx", "Login");
		a.tc_01_CreateCompanyAndOneUserWithinCom(driver);*/
		//a.tc_02_CreateCompanyAndMultipleUsers(driver);
		
		//KYCDashboardPageTC k= new KYCDashboardPageTC();
		
		
		
		//k.tc_filters_Relationships(driver);
	   //k.tc_01_filter_EntityName(driver);
		/*k.tc_02_filter_EntityType(driver);
		k.tc_03_filter_KYCReadiness(driver);*/
		//k.tc_04_filter_KYCReviewStatus(driver);
		/*k.tc_05_filter_MEI(driver);
		k.tc_06_filter_LEI(driver);
		k.tc_07_filter_ClientIdentifier(driver);
		k.tc_08_filter_TaskTab_EntityName(driver);
		k.tc_09_filter_TaskTab_TaskType(driver);
		k.tc_10_filter_TaskTab_DocumentType(driver);
		k.tc_11_filter_TaskTab_MEI(driver);
		k.tc_12_filter_TaskTab_LEI(driver);
		k.tc_13_filter_TaskTab_ClientIdentifier(driver);
		k.tc_14_filter_TaskTab_DueDate(driver);
		k.tc_15_filter_TaskTab_TaskState(driver);*/
	/*	WebDriver driver=MCPM_login.mcpmLogin();
		NavigationPage nav=new NavigationPage();
		nav.navigateToBulkUploadOfRFA(driver);
		RFA r= new RFA();
		r.uploadRFAOnboardingFile(driver,"RFA");
		}*/
		
	

/*	@Test
	public void verifyPega() throws Exception
	{
		PEGATestCases pega= new PEGATestCases();
		WebDriver driver=LoginPega.pega();
		WebUtil.setFrame(driver, "PegaGadget0Ifr");
		WebElement act=driver.findElement(By.xpath("//tr[@id='$PMyWorkList$ppxResults$l8']//td[@class='kyc-button-ctrl gridCell ']//button"));
		WebUtil.actionClass(act, driver);
		WebElement start=WebUtil.findElement("PEGACase.xpath.StartButton", driver);
		WebUtil.actionClass(start, driver);
		Thread.sleep(15000);
		System.out.println("Time out again");
		pega.tc_04_UploadDoc(driver);
		Thread.sleep(10000);
		System.out.println("Time out again");
		pega.tc_05_EditDocumentDetails(driver);
		Thread.sleep(10000);
		System.out.println("Time out again");
		pega.tc_06_AttachDocument(driver);
		Thread.sleep(10000);
		System.out.println("Time out again");
		pega.tc_07_dataEnrichment(driver);
		Thread.sleep(10000);
		
	}*/
	


		
/*		
		WebDriver driver=MCPM_login.mcpmLogin();
		System.out.println("Start RFA");
		WebUtil.setFrame(driver, "MFrame");
		//WebElement ele=driver.findElement(By.xpath("//div[@class='col-xs-12'][2]/div"));
		WebElement ele=driver.findElement(By.cssSelector("#pdf-holder"));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollBy(0,250)", "");
		//jse.executeScript("arguments[0].scrollIntoView(true);",ele);
		//jse.executeScript("window.scrollTo(0, document.body.scrollHeight)",ele);
		String test = ele.getCssValue("overflow-y");
		System.out.println("property value: "+test);
		
		//jse.executeScript("scroll(0,400)");
	    //jse.executeScript("window.scrollBy(0,400)", ele);
		 Actions dragger = new Actions(driver);
	        WebElement draggablePartOfScrollbar = ele;

	        // drag downwards
	      

	        // now drag opposite way (downwards)
	        int numberOfPixelsToDragTheScrollbarDown = -50;
	        for (int i=500;i>10;i=i+numberOfPixelsToDragTheScrollbarDown){
	        // this causes a gradual drag of the scroll bar, -10 units at a time
	        dragger.moveToElement(draggablePartOfScrollbar).clickAndHold().moveByOffset(0,numberOfPixelsToDragTheScrollbarDown).release().perform();
	        Thread.sleep(1000L);
	        }*/
	
		/*String currentUser = System.getProperty("user.name");
		String path="C:/Users/"+currentUser+"/Downloads/Masterlist.xlsx";
		List<String>data=new ArrayList<>();
		data=	ExcelUtil.getData(path, "Active");
		String value=ExcelUtil.isValueExist(data, "Test RFA entity 02");
		System.out.println("value: "+value);*/

		//WebUtil.deleteFilesFromAFloder(path);
	

