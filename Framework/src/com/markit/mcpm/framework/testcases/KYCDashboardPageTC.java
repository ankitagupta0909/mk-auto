package com.markit.mcpm.framework.testcases;


import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;

import com.markit.common.framework.util.ALMUtility;
import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.framework.mcpm.pageMethods.CompanyActionsPage;
import com.markit.framework.mcpm.pageMethods.EntityPage;
import com.markit.framework.mcpm.pageMethods.KYCDashboardTaskpage;
import com.markit.framework.mcpm.pageMethods.KYCEntityDetailsPage;


import com.markit.framework.mcpm.pageMethods.KYCdashboardpage;
import com.markit.framework.mcpm.pageMethods.MCPMLoginpage;
import com.markit.framework.mcpm.pageMethods.NavigationPage;


import com.markit.framework.mcpm.pageMethods.RFA;
import com.markit.common.framework.util.ReportUtil;
public class KYCDashboardPageTC{
	NavigationPage nav= new NavigationPage();
	KYCdashboardpage dashboard= new KYCdashboardpage();
	public static int countofEntity=0;
	public static String bankName="Abbey National";
	ArrayList<String> expectedValues=new ArrayList<String>();
	public static String entiyNameforTask=null;
	public static String nameofCertifier=null;
	public static String financialInstititionName=null;
	public static String location=null;
	KYCEntityDetailsPage.CommonDetails entitydetails= new KYCEntityDetailsPage().new CommonDetails();
	KYCEntityDetailsPage.KYCDocumentsTabPage kycDoc=new KYCEntityDetailsPage().new KYCDocumentsTabPage();
	KYCEntityDetailsPage.KYCPermissionPage per=new KYCEntityDetailsPage().new KYCPermissionPage(); 
	KYCEntityDetailsPage.Countries_Industries country=new KYCEntityDetailsPage().new Countries_Industries();
	public static Map<String,Integer> headerIndex=new HashMap<String,Integer>();

	public  ArrayList<String> loadTestData()
	{

		String nameOfEntity=null;


		//KYCPreQuestionnairePage kycEntity= new KYCPreQuestionnairePage();
		
		ArrayList<String> expectedValues = new ArrayList<String>();
		String  entityName=null;
		String displayName=null;
		String entitySubType=null;
		String kycReadiness=null;
		String mei=null;
		String lei=null;
		String ci=null;
		String country=null;
		String userNSame=null;
		String browser=null;
		String entityType=null;
		String userName=null;
		String entiyNameforTask=null;
		String nameofCertifier=null;
		String financialInstititionName=null;
		String location=null;
		Map<String,Integer> headerIndex=new HashMap<String,Integer>();
		ArrayList<String> excelvalues = new ArrayList<String>();
		List<String> tc1 =ExcelUtil.fn_GetTestData("src/testdata/testWorkbook.xlsx","TestDataSheet","TestCaseName","tc_01_Create_Entity");
		entityName=ExcelUtil.fn_FetchFieldValue(tc1,"EntityName");
		displayName=ExcelUtil.fn_FetchFieldValue(tc1,"DisplayName");
		entityType=ExcelUtil.fn_FetchFieldValue(tc1,"EntityType");
		entitySubType=ExcelUtil.fn_FetchFieldValue(tc1,"EntitySubType");
		kycReadiness=ExcelUtil.fn_FetchFieldValue(tc1,"KYC Readiness");
		mei=ExcelUtil.fn_FetchFieldValue(tc1,"MEI");
		lei=ExcelUtil.fn_FetchFieldValue(tc1,"LEI");
		ci=ExcelUtil.fn_FetchFieldValue(tc1,"CI");
		country=ExcelUtil.fn_FetchFieldValue(tc1,"Country");
		userName=ExcelUtil.fn_FetchFieldValue(tc1,"UploadedBy");
		excelvalues.add(entityName);
		excelvalues.add(displayName);
		excelvalues.add(country);
		excelvalues.add(entityType);
		excelvalues.add(entitySubType);
		excelvalues.add(lei);
		excelvalues.add(ci);
		excelvalues.add(mei);
		excelvalues.add(userName);	 
		return excelvalues;
	}




	public int tc_01_verifyEntityCountOnKYCDashboardPage(WebDriver driver) 
	{	//int countofEntity=0;
		nav.navigateToKYCServices(driver);
		WebUtil.wait(10000);
		NavigationPage.setMcpmFrame(driver);
		countofEntity=dashboard.kycReadinessCount(driver,"KYCDashboard.xpath.Readiness");
		WebUtil.wait(3000);
		dashboard.clickingAtCPLink(driver,"KYCDashboard.xpath.CP");		
		return countofEntity;
	}
	public WebDriver tc_02_verifyEntityCreation(WebDriver driver) 
	{

		expectedValues=loadTestData();	
		NavigationPage.setMcpmFrame(driver);
		nav.navigateToCreateEntity(driver);
		WebUtil.wait(5000);
		EntityPage entity= new EntityPage();
		entity.fillEntityData(driver, expectedValues);
		entity.saveEntityDetails(driver);
		WebUtil.wait(5000);
		return driver;

	}

	public WebDriver tc_03_verifyKYCreadinessCount(WebDriver driver) 
	{
		nav.navigateToKYCServices(driver);
		WebUtil.wait(10000);
		dashboard.kycReadinessCountValidation(driver,"KYCDashboard.xpath.Readiness",countofEntity);
		return driver;	

	}

	public WebDriver tc_04_verifyEntityNameFilter(WebDriver driver) 
	{
		NavigationPage.setMcpmFrame(driver);		
		expectedValues=loadTestData();
		dashboard.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		WebElement element =dashboard.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		dashboard.setValueInFilter(driver, element, expectedValues.get(0));
		dashboard.applyFliter(driver, expectedValues.get(0), "KYCDashboard.xpath.Defocus");
		dashboard.readWebTableRowsContent(driver,"KYCDashboard.xpath.TableHeader","KYCDashboard.xpath.TableContent");
		dashboard.validateTableContent(driver, expectedValues.get(0), expectedValues.get(3), expectedValues.get(7), expectedValues.get(5), "Action Required ?");
		dashboard.clickAtLinkToNavigateToKYCEntityDetailsPage(driver,"KYCDashboard.xpath.ActionRequiredLink" );
		return driver;
	}
	public void tc_05_verifyPreQuestionnaireDoesnotExit(WebDriver driver)
	{	
		ArrayList<String> tabsName=new ArrayList<String>();
		tabsName=dashboard.verifyNameofTabs(driver,"KYCEntityDetail.xpath.Tabs");
		ReportUtil.reportStringMatch("Verify the first Tab Name", "Documents", tabsName.get(0).toString());
		ReportUtil.reportStringMatch("Verify the Second Tab Name", "Permissions", tabsName.get(1).toString());
		entitydetails.verifyPre_KYC_Steps(driver);		
	}
	/*	public WebDriver tc_05_verifyPre_QuesstionnairePage(WebDriver driver) throws InterruptedException, IOException, SQLException
	{
		entity.verifyKYCEntityPage(driver, "KYCEntityDetails.xpath.MEI");
		entity.validateValuesofEntity(driver, expectedValues);
		WebUtil.wait(10000);
		entity.editEntitylink(driver, "KYCEntityDetail.xpath.EditLink", "KYCEditEntity.xpath.EditEntityTitle","KYCEditEntity.xpath.CloseButton");
		kycEntity.verifyNameofTabs(driver,"KYCEntityDetail.xpath.Tabs");
		kycEntity.fillPreQuesstionnaire(driver,"KYCEntityDetail.xpath.SelectButtons","KYCEntityDetail.xpath.Austria","KYCEntityDetail.xpath.BearerShares","KYCEntityDetail.xpath.YesOption","KYCEntityDetail.xpath.Save");
		entity.checkPreQuestionnaireStatus(driver, "KYCDocumentUpload.xpath.PreQuesstionnaireStatus");

		return driver;

	}*/

	public void tc_06_verifyKYCEntityDetails(WebDriver driver)
	{
		entitydetails.verifyKYCEntityPageLoaded(driver, "KYCEntityDetails.xpath.MEI");
		entitydetails.validateValuesofEntity(driver, expectedValues);
		WebUtil.wait(5000);
		entitydetails.editEntitylink(driver, "KYCEntityDetail.xpath.EditLink", "KYCEditEntity.xpath.EditEntityTitle","KYCEditEntity.xpath.CloseButton");
		WebUtil.wait(5000);
		
		

	}
	
	public void tc_07_Countries_IndustriesDetails(WebDriver driver)
	{
		KYCDashboardTaskpage.InternalConfirmationTaskpage internal= new KYCDashboardTaskpage().new InternalConfirmationTaskpage();
		WebUtil.clickedWithAction(driver, "CountriesIndustries.xpath.Link");
		country.fill_Countries_Industries_details(driver);
		WebUtil.wait(7000);
		internal.scrollDown(driver);
		internal.fillInternalConfirmationForm(driver,"Monika");
		entitydetails.checkCountriesIndustriesStatus(driver, "CountriesIndustries.xpath.CountriesIndustriesStatus");
	}
	public WebDriver tc_08_verifyKYCDocumentsTabPage(WebDriver driver,Connection con) 
	{
		expectedValues=loadTestData();
		String docId=null;
		kycDoc.validateDocuemntPage(driver, "KYCDocumentUpload.xpath.ValidateElement");
		WebUtil.wait(2000);		
		kycDoc.uploadDocument(driver,"Articles of Incorporation" ,MCPMLoginTC.browsername);
		WebUtil.wait(5000);
		String count=kycDoc.dbValidationForDocumentCount(expectedValues.get(0));
		//ReportUtil.reportStringMatch("The count of documents after the first upload action (DB Validation)", "2", count);
		
		/*kycDoc.uploadDocument(driver, "Articles of Incorporation",MCPMLoginTC.browsername);
		WebUtil.wait(5000);
		count=kycDoc.dbValidationForDocumentCount(expectedValues.get(0));
		ReportUtil.reportStringMatch("The count of documents after second Upload action (DB Validation)", "3", count);*/
		WebUtil.wait(5000);
		String username=System.getProperty("user.name");
		String path="C:/Users/"+username+"/Downloads";
		if(MCPMLoginTC.browsername.equalsIgnoreCase("Chrome"))
		{
			WebUtil.emptyAFolder(path);
			docId=kycDoc.downloadFileForChrome(driver, "Articles of Incorporation");
			List<String> fileName=WebUtil.readFilesNameInAFolder(path);
			ReportUtil.reportStringMatch("Verify the downloaded file name", "Entity_batch_Upload.xlsx", fileName.get(0).toString());
		  
		}
		WebUtil.wait(2000);
		String userName=MCPMLoginpage.dbValidationforUserName(con);
		WebUtil.wait(2000);
		kycDoc.validateTheUploadedDocuemnt(driver, "Articles of Incorporation",userName );
		WebUtil.wait(5000);
		kycDoc.clickAtContinue(driver, "KYCDocumentUpload.xpath.Continue");
		WebUtil.wait(5000);
		entitydetails.checkDocumentStatus(driver, "KYCPermission.xpath.DocumentStatus");		
		return driver;
	}
	public WebDriver tc_09_verifyPermissionPage(WebDriver driver) 
	{
		per.validatePermissionPage(driver, "KYCPermission.xpath.ValidateElement");
		per.serchBankName(driver, "KYCPermission.xpath.SearchInput", bankName, "KYCPermission.xpath.SearchButton");
		WebUtil.wait(7000);
		entitydetails.checkPermissionStatus(driver, "KYCPermission.xpath.PermissionStatus");
		per.validateRequestKYCButton(driver, "KYCPermission.xpath.RequestKYCButton");
		WebUtil.wait(7000);
		per.validatePopUp(driver, "KYCPermission.xpath.PopUp", "KYCPermission.xpath.PopUpCancelButton");
		nav.navigateBackToDashBoard(driver, "KYCPermission.xpath.BackToDashBoard");
		dashboard.validateStateofEntityAfterCompltion(driver, "KYCPermission.xpath.ReadinessStatus");
		return driver;

	}

	
	
	public void tc_10_verifyHeadersOfGraphs(WebDriver driver) throws InterruptedException
	{
		dashboard.readChartsHeaders(driver,"KYCDashboard.xpath.PriorityTaskHeader");
		dashboard.readChartsHeaders(driver,"KYCDashboard.xpath.KYCTaskHeader");
		dashboard.readChartsHeaders(driver,"KYCDashboard.xpath.KYCReadinessHeader");
		dashboard.readChartsHeaders(driver,"KYCDashboard.xpath.KYCReviewHeader");
	}
	public void tc_11_verifyPriorityTasksViewGraph(WebDriver driver) throws InterruptedException
	{
		KYCdashboardpage dashboard= new KYCdashboardpage();
		String text=null;
		String value=null;
		WebElement element=null;
		boolean ActVal=true;
		element=WebUtil.findElement("KYCDashboard.css.TenDaysGraph", driver);
		ActVal=WebUtil.verifyWebElementExists("WebElement", element);
		
		if(ActVal==true)
		{
		dashboard.verifyColorofCharts(driver,"KYCDashboard.css.TenDaysGraph","#DB4200","Priority Tasks View <10 Days");
		dashboard.verifyLabelOfGraph(driver,"KYCDashboard.xpath.TenDaysGraphLabel","< 10 days","Priority Tasks View <10 Days");
		dashboard.clickATCharts(driver,"KYCDashboard.css.TenDaysGraph");
		WebUtil.wait(3000);
		dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Tasks");
		text=WebUtil.readElementText(driver,"KYCDashboard.xpath.DueDateFilter");
        dashboard.validateDueDateForTenDays(text);
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskStateFilter");
        ReportUtil.reportStringMatch("Verify the filter Task State", "Open", value);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.TenDaysCount", "KYCDashboard.xpath.FilterMatches","Priority Tasks View <10 Days");
        dashboard.validateFiterResult(driver);
        dashboard.readContentOfColor(driver,"#DB4200","Priority Tasks View <10 Days");
        WebUtil.click("KYCDashboard.xpath.ClearFilter");
        WebUtil.wait(3000);
		}
       
		
		element=WebUtil.findElement("KYCDashboard.css.TentoThirtyDaysGraph", driver);
		ActVal=WebUtil.verifyWebElementExists("WebElement", element);
		int size=WebUtil.getSizeOfElement(driver, "KYCDashboard.css.TentoThirtyDaysGraph");
		if(ActVal==true)
		{
        dashboard.verifyColorofCharts(driver,"KYCDashboard.css.TentoThirtyDaysGraph","#F7C900","Priority Tasks View 10-30 Days");
        dashboard.verifyLabelOfGraph(driver,"KYCDashboard.xpath.TentoThirtyDaysGraphLabel","10-30 days", "Priority Tasks View 10-30 Days");
        dashboard.clickATCharts(driver,"KYCDashboard.css.TentoThirtyDaysGraph");
    	WebUtil.wait(3000);
    	dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Tasks");
        text=WebUtil.readElementText(driver,"KYCDashboard.xpath.DueDateFilter");
        dashboard.validateDueDateforTentoThirty(text);
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskStateFilter");
        ReportUtil.reportStringMatch("Verify the filter Task State", "Open", value);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.TentoThirtyDaysCount", "KYCDashboard.xpath.FilterMatches","Priority Tasks View 10-30 Days");
        dashboard.validateFiterResult(driver);
        dashboard.readContentOfColor(driver,"#F7C900","Priority Tasks View 10-30 Days");
        WebUtil.click("KYCDashboard.xpath.ClearFilter");
        WebUtil.wait(3000);
		}		
				
		element=WebUtil.findElement("KYCDashboard.css.TenOnetoNinetyDaysGraph", driver);
		ActVal=WebUtil.verifyWebElementExists("WebElement", element);
		
		if(ActVal==true)
		{        
        dashboard.verifyColorofCharts(driver,"KYCDashboard.css.TenOnetoNinetyDaysGraph","#76C000","Priority Tasks View 31-90 days");
        dashboard.verifyLabelOfGraph(driver,"KYCDashboard.xpath.ThirtyOnetoNinetyDaysGraphLabel","31-90 days","Priority Tasks View 31-90 days");
        dashboard.clickATCharts(driver,"KYCDashboard.css.TenOnetoNinetyDaysGraph");
        
        WebUtil.wait(3000);
        dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Tasks");
        text=WebUtil.readElementText(driver,"KYCDashboard.xpath.DueDateFilter");
        dashboard.validateDueDateforThirtyOneToNinety(text);
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskStateFilter");
        ReportUtil.reportStringMatch("Verify the filter Task State", "Open", value);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.ThirtyOnetoNinetyDaysCount", "KYCDashboard.xpath.FilterMatches","Priority Tasks View 31-90 days");
        dashboard.validateFiterResult(driver);
        dashboard.readContentOfColor(driver,"#76C000","Priority Tasks View 31-90 days");
        WebUtil.click("KYCDashboard.xpath.ClearFilter");
        
		}
		
	}
	public void tc_12_verifyKYCTasksGraph(WebDriver driver) throws InterruptedException
	{
		KYCdashboardpage dashboard= new KYCdashboardpage();
		String text=null;
		String value=null;
		String actualValueofPopUp=null;
		dashboard.clickATCharts(driver,"KYCDashboard.xpath.PermissionRequestLabel");
		text=WebUtil.readElementText(driver,"KYCDashboard.xpath.PermissionRequestLabel");
		WebUtil.wait(5000);
		if(text.equalsIgnoreCase("0"))
		{
			
			actualValueofPopUp=WebUtil.readElementText(driver,"KYCDashboard.xpath.NoRecords");
			WebUtil.wait(5000);
			ReportUtil.reportStringMatch("Verify the pop up when count is 0", "No tasks matching your criteria. Please clear filters to see all tasks.", actualValueofPopUp);
			
		}
		else
		{     
        
        dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Tasks");
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskStateFilter");
        ReportUtil.reportStringMatch("Verify the filter Task State", "Open", value);
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskTypeFilter");
        ReportUtil.reportStringMatch("Verify the filter Task Type", "Permission Request", value);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.PermissionRequestLabel", "KYCDashboard.xpath.FilterMatches","Permission Request");
		}
		
		//////////////////////////////////////////////////////
		dashboard.clickATCharts(driver,"KYCDashboard.xpath.DocumentRequestLabel");
		WebUtil.wait(5000);
		text=WebUtil.readElementText(driver,"KYCDashboard.xpath.DocumentRequestLabel");
		if(text.equalsIgnoreCase("0"))
		{
			
			actualValueofPopUp=WebUtil.readElementText(driver,"KYCDashboard.xpath.NoRecords");
			
			System.out.println(actualValueofPopUp);
			WebUtil.wait(5000);
			ReportUtil.reportStringMatch("Verify the pop up when count is 0", "No tasks matching your criteria. Please clear filters to see all tasks.", actualValueofPopUp);
		}
		else
		{
		      
        dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Tasks");
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskStateFilter");
        ReportUtil.reportStringMatch("Verify the filter Task State", "Open", value);
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskTypeFilter");
        ReportUtil.reportStringMatch("Verify the filter Task Type", "Document Request", value);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.DocumentRequestLabel", "KYCDashboard.xpath.FilterMatches","Document Request");
		}
		//////////////////////////////////////////////////////////////
		
		dashboard.clickATCharts(driver,"KYCDashboard.xpath.OutreachQuestionsLabel");
		WebUtil.wait(5000);
		text=WebUtil.readElementText(driver,"KYCDashboard.xpath.OutreachQuestionsLabel");
		if(text.equalsIgnoreCase("0"))
		{
			
			actualValueofPopUp=WebUtil.readElementText(driver,"KYCDashboard.xpath.NoRecords");
			WebUtil.wait(5000);
			ReportUtil.reportStringMatch("Verify the pop up when count is 0", "No tasks matching your criteria. Please clear filters to see all tasks.", actualValueofPopUp);
			
		}
		else
		{        
        dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Tasks");
        WebUtil.wait(5000);
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskStateFilter");
        ReportUtil.reportStringMatch("Verify the filter Task State", "Open", value);
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskTypeFilter");
        ReportUtil.reportStringMatch("Verify the filter Task Type", "Outreach Questions", value);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.OutreachQuestionsLabel", "KYCDashboard.xpath.FilterMatches","Outreach Questions");
		}
		
		dashboard.clickATCharts(driver,"KYCDashboard.xpath.AuthorisedSignatoryRequestLabel");
		WebUtil.wait(5000);
		text=WebUtil.readElementText(driver,"KYCDashboard.xpath.AuthorisedSignatoryRequestLabel");
		if(text.equalsIgnoreCase("0"))
		{
			
			actualValueofPopUp=WebUtil.readElementText(driver,"KYCDashboard.xpath.NoRecords");
			WebUtil.wait(5000);
			ReportUtil.reportStringMatch("Verify the pop up when count is 0", "No tasks matching your criteria. Please clear filters to see all tasks.", actualValueofPopUp);
			
		}
		
		else
		{        
        dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Tasks");
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskStateFilter");
        ReportUtil.reportStringMatch("Verify the filter Task State", "Open", value);
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskTypeFilter");
        WebUtil.wait(5000);
        ReportUtil.reportStringMatch("Verify the filter Task Type", "Authorised Signatory Request", value);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.AuthorisedSignatoryRequestLabel", "KYCDashboard.xpath.FilterMatches","Authorised Signatory Request");
		}
		//////////////////////////////////////////
		dashboard.clickATCharts(driver,"KYCDashboard.xpath.IdentityDetailRequestLabel");
		text=WebUtil.readElementText(driver,"KYCDashboard.xpath.IdentityDetailRequestLabel");
		WebUtil.wait(5000);
		if(text.equalsIgnoreCase("0"))
		{
			
			actualValueofPopUp=WebUtil.readElementText(driver,"KYCDashboard.xpath.NoRecords");
			WebUtil.wait(5000);
			ReportUtil.reportStringMatch("Verify the pop up when count is 0", "No tasks matching your criteria. Please clear filters to see all tasks.", actualValueofPopUp);
			
		}
		else
		{
		
        
        dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Tasks");
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskStateFilter");
        ReportUtil.reportStringMatch("Verify the filter Task State", "Open", value);
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskTypeFilter");
        ReportUtil.reportStringMatch("Verify the filter Task Type", "Identity Detail Request", value);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.IdentityDetailRequestLabel", "KYCDashboard.xpath.FilterMatches","Identity Detail Request");
		}
		
		dashboard.clickATCharts(driver,"KYCDashboard.xpath.OwnershipStructureRequestLabel");
        WebUtil.wait(5000);		
		text=WebUtil.readElementText(driver,"KYCDashboard.xpath.OwnershipStructureRequestLabel");
		if(text.equalsIgnoreCase("0"))
		{
			
			actualValueofPopUp=WebUtil.readElementText(driver,"KYCDashboard.xpath.NoRecords");
			WebUtil.wait(5000);
			ReportUtil.reportStringMatch("Verify the pop up when count is 0", "No tasks matching your criteria. Please clear filters to see all tasks.", actualValueofPopUp);
			
		}     
		else
		{
		
        dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Tasks");
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskStateFilter");
        ReportUtil.reportStringMatch("Verify the filter Task State", "Open", value);
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskTypeFilter");
        ReportUtil.reportStringMatch("Verify the filter Task Type", "Ownership Structure Request", value);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.OwnershipStructureRequestLabel", "KYCDashboard.xpath.FilterMatches","Ownership Structure Request");
		}
		/////////////////////////////////////////////////////
		
		/*dashboard.clickATCharts(driver,"KYCDashboard.xpath.ConfirmAnswersLabel");
        WebUtil.wait(5000);
		text=WebUtil.readElementText(driver,"KYCDashboard.xpath.ConfirmAnswersLabel");
		if(text.equalsIgnoreCase("0"))
		{
			
			actualValueofPopUp=WebUtil.readElementText(driver,"KYCDashboard.xpath.NoRecords");
			WebUtil.wait(5000);
			ReportUtil.reportStringMatch("Verify the pop up when count is 0", "No tasks matching your criteria. Please clear filters to see all tasks.", actualValueofPopUp);
			
		} 
		else
		{
		
        dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Tasks");
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskStateFilter");
        ReportUtil.reportStringMatch("Verify the filter Task State", "Open", value);
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskTypeFilter");
        ReportUtil.reportStringMatch("Verify the filter Task Type", "Confirm Answers", value);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.ConfirmAnswersLabel", "KYCDashboard.xpath.FilterMatches","Confirm Answers");
		}*/
		//////////////////////////////////
		
		dashboard.clickATCharts(driver,"KYCDashboard.xpath.InternalConfirmationLabel");
        WebUtil.wait(5000);
		text=WebUtil.readElementText(driver,"KYCDashboard.xpath.InternalConfirmationLabel");
		if(text.equalsIgnoreCase("0"))
		{
			
			actualValueofPopUp=WebUtil.readElementText(driver,"KYCDashboard.xpath.NoRecords");
			WebUtil.wait(5000);
			ReportUtil.reportStringMatch("Verify the pop up when count is 0", "No tasks matching your criteria. Please clear filters to see all tasks.", actualValueofPopUp);
			
		} 
		else
		{
    	
        dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Tasks");
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskStateFilter");
        ReportUtil.reportStringMatch("Verify the filter Task State", "Open", value);
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskTypeFilter");
        ReportUtil.reportStringMatch("Verify the filter Task Type", "Internal Confirmation", value);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.InternalConfirmationLabel", "KYCDashboard.xpath.FilterMatches","Internal Confirmation");
		}
		/////////////////////////////////////
		dashboard.clickATCharts(driver,"KYCDashboard.xpath.AMLQuestionnaireLabel");
        WebUtil.wait(5000);
		text=WebUtil.readElementText(driver,"KYCDashboard.xpath.AMLQuestionnaireLabel");
		if(text.equalsIgnoreCase("0"))
		{
			
			actualValueofPopUp=WebUtil.readElementText(driver,"KYCDashboard.xpath.NoRecords");
			WebUtil.wait(5000);
			ReportUtil.reportStringMatch("Verify the pop up when count is 0", "No tasks matching your criteria. Please clear filters to see all tasks.", actualValueofPopUp);
			
		} 
		else
		{
    	
        dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Tasks");
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskStateFilter");
        ReportUtil.reportStringMatch("Verify the filter Task State", "Open", value);
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskTypeFilter");
        ReportUtil.reportStringMatch("Verify the filter Task Type", "AML Questionnaire", value);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.AMLQuestionnaireLabel", "KYCDashboard.xpath.FilterMatches","AML Questionnaire");
        
        
		}
		////////////////////////////////////
	
		dashboard.clickATCharts(driver,"KYCDashboard.xpath.CountriesLabel");
        WebUtil.wait(5000);
		text=WebUtil.readElementText(driver,"KYCDashboard.xpath.CountriesLabel");
		if(text.equalsIgnoreCase("0"))
		{
			
			actualValueofPopUp=WebUtil.readElementText(driver,"KYCDashboard.xpath.NoRecords");
			WebUtil.wait(5000);
			ReportUtil.reportStringMatch("Verify the pop up when count is 0", "No tasks matching your criteria. Please clear filters to see all tasks.", actualValueofPopUp);
			
		} 
		else
		{
    	
        dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Tasks");
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskStateFilter");
        ReportUtil.reportStringMatch("Verify the filter Task State", "Open", value);
        value=WebUtil.readElementText(driver,"KYCDashboard.xpath.TaskTypeFilter");
        ReportUtil.reportStringMatch("Verify the filter Task Type", "Countries & Industries Assessment", value);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.CountriesLabel", "KYCDashboard.xpath.FilterMatches","Countries & Industries Assessment");
        
        
		}
	}
	public void tc_13_verifyKYCReadinessGraph(WebDriver driver) throws InterruptedException
	{
		KYCdashboardpage dashboard= new KYCdashboardpage();
		String text=null;
        dashboard.verifyColorofCharts(driver,"KYCDashboard.css.ActionRequiredGraph","#A0EBFE","KYC Readiness Action Required");
		dashboard.verifyLabelOfGraph(driver,"KYCDashboard.xpath.ActionRequiredLabel","Action Required","KYC Readiness Action Required");
		dashboard.clickATCharts(driver,"KYCDashboard.css.ActionRequiredGraph");
		WebUtil.wait(5000);
		
		dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Entities");
		text=WebUtil.readElementText(driver,"KYCDashboard.xpath.KYCReadinessFilter");
		ReportUtil.reportStringMatch("Verify the filter KYC readiness", "Action Required", text);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.ActionRequiredCount", "KYCDashboard.xpath.FilterMatches","KYC Readiness Action Required");
        
        dashboard.verifyColorofCharts(driver,"KYCDashboard.css.KYCPrepGraph","#02AAD1","KYC Prep Complete");
		dashboard.verifyLabelOfGraph(driver,"KYCDashboard.xpath.KYCPrepLabel","KYC Prep Complete","KYC Prep Complete");
		dashboard.clickATCharts(driver,"KYCDashboard.css.KYCPrepGraph");
		WebUtil.wait(5000);		
		dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Entities");
		text=WebUtil.readElementText(driver,"KYCDashboard.xpath.KYCReadinessFilter");
		ReportUtil.reportStringMatch("Verify the filter KYC readiness", "KYC Prep Complete", text);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.KYCPrepCount", "KYCDashboard.xpath.FilterMatches","KYC Prep Complete");
	}
	public void tc_14_verifyKYCReviewStatusGraph(WebDriver driver) throws InterruptedException
	{
		
		KYCdashboardpage dashboard= new KYCdashboardpage();
		String text=null;
		 int size;

		WebElement element=null;
		boolean ActVal=true;
		WebElement graph=WebUtil.findElement("KYCDashboard.xpath.KYCReviewStatusAvilable", driver);
	
		if(graph.isDisplayed())
		{
		
		size=WebUtil.getSizeOfElement(driver, "KYCDashboard.css.InProgressGraph");
		if(size!=0)
		{
        dashboard.verifyColorofCharts(driver,"KYCDashboard.css.InProgressGraph","#F0B893","KYC Review Status In Progress");
		dashboard.verifyLabelOfGraph(driver,"KYCDashboard.xpath.InProgressLabel","In Progress","KYC Review Status In Progress");
		dashboard.clickATCharts(driver,"KYCDashboard.css.InProgressGraph");
		WebUtil.wait(5000);
		System.out.println("time out");
		dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Entities");
		text=WebUtil.readElementText(driver,"KYCDashboard.xpath.KYCReviewStatusFilter");
		ReportUtil.reportStringMatch("Verify the filter KYC readiness", "In Progress", text);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.InProgressCount", "KYCDashboard.xpath.FilterMatches","KYC Review Status In Progress");
		}
	
		
		/*size=WebUtil.getSizeOfElement(driver, "KYCDashboard.css.RejectedGraph");
		if(size!=0)
		{
        dashboard.verifyColorofCharts(driver,"KYCDashboard.css.RejectedGraph","#E4813F","KYC Review Status Rejected");
		dashboard.verifyLabelOfGraph(driver,"KYCDashboard.xpath.RejectedLabel","Rejected","KYC Review Status Rejected");
		dashboard.clickATCharts(driver,"KYCDashboard.css.RejectedGraph");
		WebUtil.wait(5000);
		System.out.println("time out");
		dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Entities");
		text=WebUtil.readElementText(driver,"KYCDashboard.xpath.KYCReviewStatusFilter");
		ReportUtil.reportStringMatch("Verify the filter KYC readiness", "Rejected", text);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.RejectedCount", "KYCDashboard.xpath.FilterMatches","KYC Review Status Rejected");
		}*/		
		
		size=WebUtil.getSizeOfElement(driver, "KYCDashboard.css.CompletedGraph");
		if(size!=0)
		{
        dashboard.verifyColorofCharts(driver,"KYCDashboard.css.CompletedGraph","#B9591A","KYC Review Status Completed");
		dashboard.verifyLabelOfGraph(driver,"KYCDashboard.xpath.CompletedLabel","Completed","KYC Review Status Completed");
		dashboard.clickATCharts(driver,"KYCDashboard.css.CompletedGraph");
		WebUtil.wait(5000);
		System.out.println("time out");
		dashboard.activeTab(driver,"KYCDashboard.xpath.KYCDashboardTabs","Entities");
		text=WebUtil.readElementText(driver,"KYCDashboard.xpath.KYCReviewStatusFilter");
		ReportUtil.reportStringMatch("Verify the filter KYC readiness", "Completed", text);
        dashboard.verifyCountofAppliedFilter(driver,"KYCDashboard.xpath.CompletedCount", "KYCDashboard.xpath.FilterMatches","KYC Review Status Completed");
		}	
		}
		
	}
	
	public void tc_01_tasks_OutreachQuestionnaire(WebDriver driver) throws InterruptedException, AWTException, IOException
	{
		testDataDetails();
		
		KYCDashboardTaskpage.OutreachQuestionnaireTaskPage outreach= new KYCDashboardTaskpage().new OutreachQuestionnaireTaskPage();
		KYCDashboardTaskpage.InternalConfirmationTaskpage internal= new KYCDashboardTaskpage().new InternalConfirmationTaskpage();
		//InternalConfirmationTaskpage internal= new InternalConfirmationTaskpage();
		
		KYCdashboardpage kyc = new KYCdashboardpage();
		System.out.println("In the method");

	    //navigateToKYCAndSettingFilter(driver);

		kyc.clickAtLinkAsPerPassedName(driver, "Outreach Questions");
		NavigationPage.setMcpmFrame(driver);
		outreach.outReachQuestionnaireFill(driver);
		outreach.submit(driver);
		//WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.Close");
		internal.scrollDown(driver);
		internal.fillInternalConfirmationForm(driver,nameofCertifier);
	}
	public void tc_02_tasks_AMLQuestionnaire(WebDriver driver) throws InterruptedException, AWTException, IOException
	{
		testDataDetails();
		KYCDashboardTaskpage.ALMQuestionnaireTaskpage aml= new KYCDashboardTaskpage().new ALMQuestionnaireTaskpage();
		KYCDashboardTaskpage.InternalConfirmationTaskpage internal= new KYCDashboardTaskpage().new InternalConfirmationTaskpage();
		KYCdashboardpage kyc = new KYCdashboardpage();
		System.out.println("In the method");
		kyc.clickAtLinkAsPerPassedName(driver, "AML Questionnaire");
		NavigationPage.setMcpmFrame(driver);
		aml.fillAnti_Money_Laundering_Questionnaire(driver,financialInstititionName,location);
		//WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.Close");
		internal.scrollDown(driver);
		internal.fillInternalConfirmationForm(driver,nameofCertifier);
	}
	
	public void tc_03_tasks_DocumentRequest(WebDriver driver) throws InterruptedException, AWTException, IOException
	{
		KYCDashboardTaskpage.DocumentRequestTaskpage doc= new KYCDashboardTaskpage().new DocumentRequestTaskpage();
		KYCDashboardTaskpage.InternalConfirmationTaskpage internal= new KYCDashboardTaskpage().new InternalConfirmationTaskpage();
		KYCdashboardpage kyc = new KYCdashboardpage();
		int size=kyc.getLinksAsPerPassedName(driver, "Document Request");
		System.out.println("size "+size);
		for(int i=1;i<=size;i++)
		{
		kyc.clickAtLinkAsPerPassedName(driver, "Document Request");
		kyc.checkPleaseConfirmCheckbox(driver);
		doc.fillDocumentRequestInfo(driver);		
		WebUtil.wait(8000);
		boolean value=false;
		value=WebUtil.isElementPresent(driver, "InteralConfirmation.xpath.Header");
		if(value)
		{
			internal.scrollDown(driver);
			internal.fillInternalConfirmationForm(driver,nameofCertifier);
		}
		kyc.uncheckAndCheckOpenfromTaskStateFilter(driver);
		}
		
	}
	
	public void tc_04_tasks_OwnershipStructureRequest(WebDriver driver) throws InterruptedException, AWTException, IOException
	{
		List<String> expectedValues=testDataDetails();
		//navigateToKYCAndSettingFilter(driver);
		KYCDashboardTaskpage.OwnershipStructureRequestTakspage osr= new KYCDashboardTaskpage().new OwnershipStructureRequestTakspage();
		KYCDashboardTaskpage.InternalConfirmationTaskpage internal= new KYCDashboardTaskpage().new InternalConfirmationTaskpage();
		KYCdashboardpage kyc = new KYCdashboardpage();
		System.out.println("In the method");
		kyc.clickAtLinkAsPerPassedName(driver, "Ownership Structure Request");		
		NavigationPage.setMcpmFrame(driver);
		osr.fillIndiviualOSR(driver, expectedValues);
		WebUtil.wait(4000);
		NavigationPage.setMcpmFrame(driver);
		osr.fillNon_IndiviualOSR(driver, expectedValues);	
		//WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.Close");
		internal.scrollDown(driver);
		internal.fillInternalConfirmationForm(driver,nameofCertifier);
		
		
	}
	
	public void tc_05_tasks_CountriesIndustriesAssessment(WebDriver driver) 
	{
		List<String> expectedValues=testDataDetails();
		//navigateToKYCAndSettingFilter(driver);
		KYCDashboardTaskpage.CountriesIndustriesAssessment countryObj= new KYCDashboardTaskpage().new CountriesIndustriesAssessment();
		KYCDashboardTaskpage.InternalConfirmationTaskpage internal= new KYCDashboardTaskpage().new InternalConfirmationTaskpage();
		KYCdashboardpage kyc = new KYCdashboardpage();
		kyc.clickAtLinkAsPerPassedName(driver, "Countries & Industries Assessment");		
		NavigationPage.setMcpmFrame(driver);
		countryObj.checkOptionFromCountriesIndustriesAssessmentPopUp(driver);
		countryObj.fill_CountriesIndustriesAssessment_details(driver);
		WebUtil.wait(4000);	
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.Close");
		internal.scrollDown(driver);
		internal.fillInternalConfirmationForm(driver,nameofCertifier);
		
		
	}
	

	
	
	public List<String> testDataDetails() 
	{
      
		
		List<String> tc1 =ExcelUtil.fn_GetTestData("src/testdata/testWorkbook.xlsx","TestDataSheet","TestCaseName","tc_KYCDashBoard_TaskComplete");
		List<String> tc2 =ExcelUtil.fn_GetTestData("src/testdata/testWorkbook.xlsx","TestDataSheet","TestCaseName","tc_KYCDashBoard_TaskCompleteforOSR");
		List<String> testData=new ArrayList<String>();
		String value=null;
		entiyNameforTask=ExcelUtil.fn_FetchFieldValue(tc1,"EntityName");
		nameofCertifier=ExcelUtil.fn_FetchFieldValue(tc1,"NameOfCertifier");
		financialInstititionName=ExcelUtil.fn_FetchFieldValue(tc1,"AMLFinancialInstititionName");
		location=ExcelUtil.fn_FetchFieldValue(tc1,"AMLLocation");
		value=ExcelUtil.fn_FetchFieldValue(tc2,"NI_LegalName");
		testData.add(value);
		value=ExcelUtil.fn_FetchFieldValue(tc2,"NI_PercentageOwnership");
		testData.add(value);
		value=ExcelUtil.fn_FetchFieldValue(tc2,"NI_PrimaryLine");
		testData.add(value);
		value=ExcelUtil.fn_FetchFieldValue(tc2,"NI_LegalForm");
		testData.add(value);
		value=ExcelUtil.fn_FetchFieldValue(tc2,"NI_RegisteredAddress");
		testData.add(value);
		value=ExcelUtil.fn_FetchFieldValue(tc2,"NICity");
		testData.add(value);
		value=ExcelUtil.fn_FetchFieldValue(tc2,"NI_ZipCode");
		testData.add(value);
		value=ExcelUtil.fn_FetchFieldValue(tc2,"I_FirstName");
		testData.add(value);
		value=ExcelUtil.fn_FetchFieldValue(tc2,"I_LastName");
		testData.add(value);
		value=ExcelUtil.fn_FetchFieldValue(tc2,"I_SourceofWealth");
		testData.add(value);
		value=ExcelUtil.fn_FetchFieldValue(tc2,"I_PercentageOwnership");
		testData.add(value);
		
		return testData;
	}
	
	public void navigateToKYCAndSettingFilter(WebDriver driver) 
	{
		NavigationPage nav= new NavigationPage();
		KYCdashboardpage kyc = new KYCdashboardpage();
		nav.navigateToKYCServices(driver);
		WebUtil.wait(10000);
		System.out.println("Time out for KYC dashboard page");
		kyc.clickonTaskTab(driver);
		WebUtil.wait(2000);
		kyc.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		WebUtil.wait(1000);
		WebElement element =kyc.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		kyc.setValueInFilter(driver, element, entiyNameforTask);
		WebUtil.wait(2000);
		kyc.applyFliter(driver, entiyNameforTask, "KYCDashboard.xpath.Defocus");
		WebUtil.wait(4000);
		
	}
	

	public void navigateToKYCAndSettingFilterAsPerPassedName(WebDriver driver,String entiyNameforTask) 
	{
		NavigationPage nav= new NavigationPage();
		KYCdashboardpage kyc = new KYCdashboardpage();
		nav.navigateToKYCServices(driver);
		WebUtil.wait(10000);
		System.out.println("Time out for KYC dashboard page");
		kyc.clickonTaskTab(driver);
		WebUtil.wait(2000);
		kyc.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		WebUtil.wait(1000);
		WebElement element =kyc.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		kyc.setValueInFilter(driver, element, entiyNameforTask);
		WebUtil.wait(2000);
		kyc.applyFliter(driver, entiyNameforTask, "KYCDashboard.xpath.Defocus");
		WebUtil.wait(4000);
		
	}
	public void tc_01_filter_EntityName (WebDriver driver) 
	{
		KYCdashboardpage kyc = new KYCdashboardpage();
        NavigationPage nav= new NavigationPage();
		
		kyc.clickonEntityTab(driver);
		//WebUtil.wait(5000);
		headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.Table");
		int colIndex=headerIndex.get("Entity Name");
		String FieldValue=KYCdashboardpage.ReadColValue(driver, colIndex, "KYCDashboardFilter.xpath.Table");
		System.out.println("value "+FieldValue);
		
		kyc.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		WebUtil.wait(1000);
		WebElement element =kyc.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		kyc.setValueInFilter(driver, element, FieldValue.trim());
		WebUtil.wait(2000);
		kyc.applyFliter(driver, FieldValue, "KYCDashboard.xpath.Defocus");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.Table", "Entity Name", colIndex, FieldValue);
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		
		
	}
	
	public void tc_02_filter_EntityType (WebDriver driver) 
	{
		KYCdashboardpage kyc = new KYCdashboardpage();
		NavigationPage.setMcpmFrame(driver);
		headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.Table");
		int colIndex=headerIndex.get("Entity Type");
		String FieldValue=KYCdashboardpage.ReadColValue(driver, colIndex, "KYCDashboardFilter.xpath.Table");
		System.out.println("value "+FieldValue);
		kyc.setFilter(driver, "KYCDashboardFilter.xpath.EntityTypeFilter", FieldValue, "KYCDashboard.xpath.Defocus");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.Table", "Entity Type", colIndex, FieldValue);
		kyc.exportSetFilterData(driver, "Entities");
		WebUtil.wait(4000);	
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		
	}
	
	public void tc_03_filter_KYCReadiness (WebDriver driver) 
	{
		KYCdashboardpage kyc = new KYCdashboardpage();
		NavigationPage.setMcpmFrame(driver);
		headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.Table");
		int colIndex=headerIndex.get("KYC Readiness");
		String FieldValue=KYCdashboardpage.ReadColValue(driver, colIndex, "KYCDashboardFilter.xpath.Table");
		System.out.println("value is : "+FieldValue);	
		String valueToBeSelected=WebUtil.removeThepassedItemFromString(FieldValue, "→");
		System.out.println("value "+valueToBeSelected);
		kyc.setFilter(driver, "KYCDashboardFilter.xpath.KYCReadinessFilter",valueToBeSelected.trim() , "KYCDashboard.xpath.Defocus");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.Table", "KYC Readiness", colIndex, FieldValue);
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		
	}
	
	public void tc_04_filter_KYCReviewStatus (WebDriver driver) 
	{
		KYCdashboardpage kyc = new KYCdashboardpage();
		NavigationPage.setMcpmFrame(driver);
		WebElement ele=driver.findElement(By.xpath("//span[contains(text(),'KYC Review Status')]"));
		ele.click();
		WebUtil.wait(3000);
		headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.Table");
		int colIndex=headerIndex.get("KYC Review Status");
		String FieldValue=KYCdashboardpage.ReadColValue(driver, colIndex, "KYCDashboardFilter.xpath.Table");
		System.out.println("value of "+FieldValue);
		kyc.setFilter(driver, "KYCDashboardFilter.xpath.KYCReviewStatusFilter", FieldValue, "KYCDashboard.xpath.Defocus");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.Table", "KYC Review Status", colIndex, FieldValue);
  	    WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
  	  WebUtil.wait(4000);
		
		
	}
	public void tc_05_filter_MEI (WebDriver driver) 
	{
		KYCdashboardpage kyc = new KYCdashboardpage();
		WebUtil.clickedWithAction(driver, "KYCDashboardFilter.xpath.MEISorting");
		headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.Table");
		int colIndex=headerIndex.get("MEI");
		String FieldValue=KYCdashboardpage.ReadColValue(driver, colIndex, "KYCDashboardFilter.xpath.Table");
		System.out.println("value "+FieldValue);
		kyc.clickAtFilters(driver,"KYCDashboardFilter.xpath.MEIFilter");
		WebUtil.wait(1000);
		WebElement element =kyc.clickAtInputBoxOfFilter(driver, "KYCDashboardFilter.xpath.MEIFilterInputBox");
		kyc.setValueInFilter(driver, element, FieldValue.trim());
		WebUtil.wait(2000);
		kyc.applyFliter(driver,FieldValue , "KYCDashboard.xpath.Defocus");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.Table", "MEI", colIndex, FieldValue);
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		
		
	}
	
	public void tc_06_filter_LEI (WebDriver driver) 
	{
		KYCdashboardpage kyc = new KYCdashboardpage();
		NavigationPage.setMcpmFrame(driver);
		headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.Table");
		int colIndex=headerIndex.get("LEI");
		String FieldValue=KYCdashboardpage.ReadColValue(driver, colIndex, "KYCDashboardFilter.xpath.Table");
		System.out.println("value "+FieldValue);
		kyc.clickAtFilters(driver,"KYCDashboardFilter.xpath.LEIFilter");
		WebUtil.wait(1000);
		WebElement element =kyc.clickAtInputBoxOfFilter(driver, "KYCDashboardFilter.xpath.LEIFilterInputBox");
		kyc.setValueInFilter(driver, element, FieldValue.trim());
		WebUtil.wait(2000);
		kyc.applyFliter(driver,FieldValue , "KYCDashboard.xpath.Defocus");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.Table", "LEI", colIndex, FieldValue);
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		
		
	}
	
	public void tc_07_filter_ClientIdentifier (WebDriver driver) 
	{
		KYCdashboardpage kyc = new KYCdashboardpage();
		NavigationPage.setMcpmFrame(driver);
		//WebUtil.clickedWithAction(driver, "KYCDashboardFilter.xpath.CISorting");
		headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.Table");
		int colIndex=headerIndex.get("Client Identifier");
		String FieldValue=KYCdashboardpage.ReadColValue(driver, colIndex, "KYCDashboardFilter.xpath.Table");
		System.out.println("value "+FieldValue);
		kyc.clickAtFilters(driver,"KYCDashboardFilter.xpath.ClientIdentifierFilter");
		WebUtil.wait(1000);
		WebElement element =kyc.clickAtInputBoxOfFilter(driver, "KYCDashboardFilter.xpath.ClientIdentifierFilterInputBox");
		kyc.setValueInFilter(driver, element, FieldValue.trim());
		WebUtil.wait(2000);
		kyc.applyFliter(driver,FieldValue , "KYCDashboard.xpath.Defocus");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.Table", "Client Identifier", colIndex, FieldValue);
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		
		
	}
	
	public void tc_08_filters_Relationships(WebDriver driver) 
	{
	System.out.println("Test");
	NavigationPage.setMcpmFrame(driver);
	WebUtil.clickedWithAction(driver, "KYCDashboardFilter.xpath.RelationshipFilter");
	WebUtil.clickedWithAction(driver, "KYCDashboardFilter.xpath.RelationshipFilterOption");
	WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.Defocus");
	String value=WebUtil.readElementText(driver, "KYCDashboardFilter.xpath.RelationshipFilterInputBox");
	System.out.println("element is avilable"+value);
	WebUtil.clickedWithAction(driver, "KYCDashboardFilter.xpath.EntityNameLink");
	WebUtil.clickedWithAction(driver, "KYCDashboardFilter.xpath.PermissionsTab");
	WebElement ele=driver.findElement(By.xpath("//div[@class='scrollable ng-isolate-scope']//ul//li//div[contains(text(),'"+value+"')]"));
	if(ele.isDisplayed())
	{
		ReportUtil.reportStringMatch("Verify the selected relationship is avilable", value, value);
	}	
	
	WebUtil.clickedWithAction(driver, "KYCDashboardFilter.css.Back");
	WebUtil.wait(4000);
}
	
	public void tc_09_filter_TaskTab_EntityName (WebDriver driver) 
	{
		KYCdashboardpage kyc = new KYCdashboardpage();
		
		kyc.clickonTaskTab(driver);
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		WebElement e=WebUtil.findElement("KYCDashboardFilter.xpath.TaskStateFilter", driver);
		ReportUtil.reportStringMatch("Verify the detault value of Task State filter", "Open", e.getText());
		headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.TaskTable");
		int colIndex=headerIndex.get("Entity Name");
		String FieldValue=KYCdashboardpage.ReadColValue(driver, colIndex, "KYCDashboardFilter.xpath.TaskTable");
		System.out.println("value "+FieldValue);
		kyc.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		WebUtil.wait(1000);
		WebElement element =kyc.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		kyc.setValueInFilter(driver, element, FieldValue.trim());
		//WebUtil.wait(2000);
		kyc.applyFliter(driver, FieldValue, "KYCDashboard.xpath.Defocus");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.TaskTable", "Entity Name", colIndex, FieldValue);
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		
		
	}
	
	public void tc_10_filter_TaskTab_TaskType (WebDriver driver) 
	{
		KYCdashboardpage kyc = new KYCdashboardpage();
		NavigationPage.setMcpmFrame(driver);
		
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.TaskTable");
		int colIndex=headerIndex.get("Task Type");
		String FieldValue=KYCdashboardpage.ReadColValue(driver, colIndex, "KYCDashboardFilter.xpath.TaskTable");
		System.out.println("value "+FieldValue.trim());
		String valueToBeSelected=WebUtil.truncateNewLineFromPassedString(FieldValue);
		valueToBeSelected=WebUtil.removeThepassedItemFromString(valueToBeSelected, "→");
		if(FieldValue.contains("Internal Confirmation"))
		{
			FieldValue=FieldValue.replaceAll("[\n\r]", "");
			FieldValue=FieldValue.substring(1, 21);
			System.out.println("value "+FieldValue);
		}
		kyc.setFilter(driver, "KYCDashboardFilter.xpath.TaskTypeFilter", valueToBeSelected.trim(), "KYCDashboard.xpath.Defocus");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.TaskTable", "Task Type", colIndex, FieldValue);
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);	
	}
	
	public void tc_11_filter_TaskTab_DocumentType (WebDriver driver) 
	{
		KYCdashboardpage kyc = new KYCdashboardpage();
		NavigationPage.setMcpmFrame(driver);		
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.TaskTable");
		int colIndex=headerIndex.get("Task Details");
		String FieldValue=KYCdashboardpage.ReadColValue(driver, colIndex, "KYCDashboardFilter.xpath.TaskTable");
		System.out.println("value "+FieldValue);
		kyc.setFilter(driver, "KYCDashboardFilter.xpath.DocumentTypeFilter", FieldValue, "KYCDashboard.xpath.Defocus");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.TaskTable", "Task Details", colIndex, FieldValue);
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		
	}
	public void tc_12_filter_TaskTab_MEI (WebDriver driver) 
	{
		KYCdashboardpage kyc = new KYCdashboardpage();
		NavigationPage.setMcpmFrame(driver);
		kyc.clickonTaskTab(driver);
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		NavigationPage.setMcpmFrame(driver);
		headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.TaskTable");
		int colIndex=headerIndex.get("MEI");
		String FieldValue=KYCdashboardpage.ReadColValue(driver, colIndex, "KYCDashboardFilter.xpath.TaskTable");
		System.out.println("value "+FieldValue);
		kyc.clickAtFilters(driver,"KYCDashboardFilter.xpath.MEIFilter");
		WebUtil.wait(1000);
		WebElement element =kyc.clickAtInputBoxOfFilter(driver, "KYCDashboardFilter.xpath.MEIFilterInputBox");
		kyc.setValueInFilter(driver, element, FieldValue.trim());
		WebUtil.wait(2000);
		kyc.applyFliter(driver,FieldValue , "KYCDashboard.xpath.Defocus");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.TaskTable", "MEI", colIndex, FieldValue);
		kyc.exportSetFilterData(driver, "Tasks");
		WebUtil.wait(4000);	
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);		
	}
	
	public void tc_13_filter_TaskTab_LEI (WebDriver driver) 
	{
		KYCdashboardpage kyc = new KYCdashboardpage();
		NavigationPage.setMcpmFrame(driver);
		kyc.clickonTaskTab(driver);
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.TaskTable");
		int colIndex=headerIndex.get("LEI");
		String FieldValue=KYCdashboardpage.ReadColValue(driver, colIndex, "KYCDashboardFilter.xpath.TaskTable");
		System.out.println("value "+FieldValue);
		kyc.clickAtFilters(driver,"KYCDashboardFilter.xpath.LEIFilter");
		WebUtil.wait(1000);
		WebElement element =kyc.clickAtInputBoxOfFilter(driver, "KYCDashboardFilter.xpath.LEIFilterInputBox");
		kyc.setValueInFilter(driver, element, FieldValue.trim());
		WebUtil.wait(2000);
		kyc.applyFliter(driver,FieldValue , "KYCDashboard.xpath.Defocus");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.TaskTable", "LEI", colIndex, FieldValue);
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		
		
	}
	public void tc_14_filter_TaskTab_ClientIdentifier (WebDriver driver) 
	{
		KYCdashboardpage kyc = new KYCdashboardpage();
		NavigationPage.setMcpmFrame(driver);
		kyc.clickonTaskTab(driver);
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.TaskTable");
		int colIndex=headerIndex.get("Client Identifier");
		String FieldValue=KYCdashboardpage.ReadColValue(driver, colIndex, "KYCDashboardFilter.xpath.TaskTable");
		System.out.println("value "+FieldValue);
		kyc.clickAtFilters(driver,"KYCDashboardFilter.xpath.ClientIdentifierFilter");
		WebUtil.wait(1000);
		WebElement element =kyc.clickAtInputBoxOfFilter(driver, "KYCDashboardFilter.xpath.ClientIdentifierFilterInputBox");
		kyc.setValueInFilter(driver, element, FieldValue.trim());
		WebUtil.wait(2000);
		kyc.applyFliter(driver,FieldValue , "KYCDashboard.xpath.Defocus");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.TaskTable", "Client Identifier", colIndex, FieldValue);
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
	}
	
	public void tc_15_filter_TaskTab_DueDate (WebDriver driver) 
	{
		KYCdashboardpage kyc = new KYCdashboardpage();
		
		NavigationPage.setMcpmFrame(driver);
		kyc.clickonTaskTab(driver);
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.TaskTable");
		int colIndex=headerIndex.get("Due Date");
		WebUtil.clickedWithAction(driver, "KYCDashboardFilter.xpath.DueDateFilter");
		WebUtil.clickedWithAction(driver, "KYCDashboardFilter.xpath.DueDateFrom");
		WebUtil.clickedWithAction(driver, "KYCDashboardFilter.xpath.DueDateTo");
		WebUtil.clickedWithAction(driver, "KYCDashboardFilter.xpath.SubmitDueDate");		
		String FieldValue=WebUtil.getTheCurrentDateAsperpassedFormat("MM/dd/yyyy");
		int size=WebUtil.getSizeOfElement(driver, "KYCDashboardFilter.xpath.NoRecordFilter");
		System.out.println("Size of the element: "+size);
		if(size==0)
		{
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.TaskTable", "Client Identifier", colIndex, FieldValue);
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		}
		else
		{
			WebElement e=WebUtil.findElement("KYCDashboardFilter.xpath.NoRecordFilter", driver);
			ReportUtil.reportStringMatch("Flter is empty", e.getText(), e.getText());
		}
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
	}
	public void tc_16_filter_TaskTab_TaskState (WebDriver driver) 
	{
		KYCdashboardpage kyc = new KYCdashboardpage();
		NavigationPage.setMcpmFrame(driver);
		kyc.clickonTaskTab(driver);
		
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.TaskTable");
		int colIndex=headerIndex.get("Task State");
		String FieldValue=KYCdashboardpage.ReadColValue(driver, colIndex, "KYCDashboardFilter.xpath.TaskTable");
		System.out.println("value "+FieldValue);
		kyc.setFilter(driver, "KYCDashboardFilter.xpath.TaskStateFilter", FieldValue, "KYCDashboard.xpath.Defocus");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.TaskTable", "Task State", colIndex, FieldValue);
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.ClearFilter");
		WebUtil.wait(4000);
		
	} 
	
	
	public void tc_15_verify_PreKYCChecklistGraphAvilable(WebDriver driver)
	{
		nav.navigateToKYCServices(driver);
		WebUtil.wait(10000);
		NavigationPage.setMcpmFrame(driver);
		
		String text=null;
		text=WebUtil.readElementText(driver, "KYCDashBoard.xpath.PreKYCChecklistHeader");
		ReportUtil.reportStringMatch("Verify the header of Pre-KYC Checklist graph", "Pre-KYC Checklist", text);
		text=WebUtil.readElementText(driver, "KYCDashBoard.xpath.PreKYCAssignPrimaryText");
		ReportUtil.reportStringMatch("Verify the label of Pre-KYC Checklist graph", "Assign Primary Contact", text);
		text=WebUtil.readElementText(driver, "KYCDashBoard.xpath.PreKYCRequestESignText");
		ReportUtil.reportStringMatch("Verify the label of Pre-KYC Checklist graph", "Request eSign Role", text);
		text=WebUtil.readElementText(driver, "KYCDashBoard.xpath.PreKYCCountriesIndustriesText");
		ReportUtil.reportStringMatch("Verify the label of Pre-KYC Checklist graph", "Countries & Industries Assessment", text);
		text=WebUtil.readElementText(driver, "KYCDashBoard.xpath.PreKYCWolfsbergText");
		ReportUtil.reportStringMatch("Verify the label of Pre-KYC Checklist graph", "Wolfsberg Questionnaire", text);	
	}
	
	public void tc_16_verify_PreKYCChecklist_AssignPrimaryContact_Graph_IsBlue(WebDriver driver)
	{
		CompanyActionsPage.ContactsTab contacts=new CompanyActionsPage().new ContactsTab();
		String text=null;		
		KYCdashboardpage kyc= new KYCdashboardpage();
		CompanyActionsPage ca=new CompanyActionsPage();
		WebUtil.wait(3000);
		/*text=WebUtil.readElementText(driver, "KYCDashBoard.xpath.PreKYCAssignPrimaryCount");
		ReportUtil.reportStringMatch("Verify AssignPrimaryContact graph number", "1", text);*/
		text=WebUtil.readElementText(driver, "KYCDashBoard.xpath.PreKYCAssignPrimaryText");
		ReportUtil.reportStringMatch("Verify the label of AssignPrimaryContact graph", "Assign Primary Contact", text);
		nav.navigateToKYCDashboardTools(driver);
		contacts.navigateToContactsTab(driver);
		contacts.deleteContactsFromCompayLevel(driver);
		WebUtil.wait(3000);			
		text=kyc.verifyTheColorOfPreKYCChecklistGraph(driver,"KYCDashBoard.xpath.PreKYCAssignPrimaryGraph");
		ReportUtil.reportStringMatch("Verify the color of the Graph", "Blue", text);		
		
	}
	
	public void tc_17_verify_AddCompanyContacts_GraphIsGreen(WebDriver driver)
	{
		String text=null;
		KYCdashboardpage kyc= new KYCdashboardpage();
		WebUtil.clickedWithAction(driver, "KYCDashBoard.xpath.PreKYCAssignPrimaryText");	
		text=WebUtil.readElementText(driver, "AddContacts.xpath.CompanyAction");
		ReportUtil.reportStringMatch("Verify page is loaded", "Company Actions", text);
		CompanyActionsPage.ContactsTab contacts=new CompanyActionsPage().new ContactsTab();
		CompanyActionsPage ca=new CompanyActionsPage();
		contacts.addContacts(driver);
		ca.navigateBackToDashBoard(driver);
		WebUtil.wait(5000);		
		text=kyc.verifyTheColorOfPreKYCChecklistGraph(driver,"KYCDashBoard.xpath.PreKYCAssignPrimaryGraph");
		ReportUtil.reportStringMatch("Verify the color of the Graph", "Green", text);
	    
	}
	
	public void tc_18_verify_RequesteSignRole(WebDriver driver,Connection con)
	{
		String text=null;
		CompanyActionsPage.RequesteSignRole role= new CompanyActionsPage().new RequesteSignRole();
		KYCdashboardpage kyc= new KYCdashboardpage();
		/*text=WebUtil.readElementText(driver, "KYCDashBoard.xpath.PreKYCRequestESignCount");
		ReportUtil.reportStringMatch("Verify RequesteSignRole graph number", "2", text);*/
		text=WebUtil.readElementText(driver, "KYCDashBoard.xpath.PreKYCRequestESignText");
		ReportUtil.reportStringMatch("Verify the label of RequesteSignRole graph", "Request eSign Role", text);
		boolean status=role.db_ValidatationForE_SignRole(con,MCPMLoginpage.username);
		if(status==true)
		{		
		text=kyc.verifyTheColorOfPreKYCChecklistGraph(driver,"KYCDashBoard.xpath.PreKYCRequestESignGraph");
		ReportUtil.reportStringMatch("Verify the color of the Graph", "Green", text);
		}
		else
		{		
		role.verify_RequesteSignRole(driver);		
		text=kyc.verifyTheColorOfPreKYCChecklistGraph(driver,"KYCDashBoard.xpath.PreKYCRequestESignGraph");
		ReportUtil.reportStringMatch("Verify the color of the Graph", "Blue", text);
		}
	}
	
	public void tc_19_verify_CountriesIndustriesAssessment_Graph_AddDetails(WebDriver driver)
	{
		String text=null;
		KYCdashboardpage kyc= new KYCdashboardpage();
		KYCDashboardTaskpage.InternalConfirmationTaskpage internal= new KYCDashboardTaskpage().new InternalConfirmationTaskpage();
		CompanyActionsPage.CountriesIndustriesAssessment country=new CompanyActionsPage().new CountriesIndustriesAssessment();
		/*text=WebUtil.readElementText(driver, "KYCDashBoard.xpath.PreKYCCountriesIndustriesCount");
		ReportUtil.reportStringMatch("Verify RequesteSignRole graph number", "3", text);*/
		text=WebUtil.readElementText(driver, "KYCDashBoard.xpath.PreKYCCountriesIndustriesText");
		ReportUtil.reportStringMatch("Verify the label of CountriesIndustriesAssessment graph", "Countries & Industries Assessment", text);
		country.fill_detailsofCountriesIndustriesAssessment(driver);
		WebUtil.wait(4000);
		internal.scrollDown(driver);
		internal.fillInternalConfirmationForm(driver,"Monika");
		text=dashboard.verifyTheColorOfPreKYCChecklistGraph(driver,"KYCDashBoard.xpath.PreKYCCountriesIndustriesGraph");
		ReportUtil.reportStringMatch("Verify the color of the Graph", "Green", text);
	}
	
	public void tc_20_verify_Add_WolfsbergQuestionnaire_Graph_Details(WebDriver driver)
	{
		KYCDashboardTaskpage.InternalConfirmationTaskpage internal= new KYCDashboardTaskpage().new InternalConfirmationTaskpage();
		String text=null;
		CompanyActionsPage.WolfsbergAML wolf=new CompanyActionsPage().new WolfsbergAML();
		/*text=WebUtil.readElementText(driver, "KYCDashBoard.xpath.PreKYCWolfsbergCount");
		ReportUtil.reportStringMatch("Verify RequesteSignRole graph number", "4", text);*/
		text=WebUtil.readElementText(driver, "KYCDashBoard.xpath.PreKYCWolfsbergText");
		ReportUtil.reportStringMatch("Verify the label of WolfsbergQuestionnaire graph", "Wolfsberg Questionnaire", text);
		wolf.verify_filling_Wolfsberg_Questionnaire(driver);
		WebUtil.wait(4000);
		internal.scrollDown(driver);
		internal.fillInternalConfirmationForm(driver,"Monika");
		text=dashboard.verifyTheColorOfPreKYCChecklistGraph(driver,"KYCDashBoard.xpath.PreKYCWolfsbergGraph");
		ReportUtil.reportStringMatch("Verify the color of the Graph", "Green", text);
	}
	
	
	
	
	

}
