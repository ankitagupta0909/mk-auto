package com.markit.mcpm.framework.testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.jar.Attributes.Name;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.framework.mcpm.pageMethods.AdminPage;
import com.markit.framework.mcpm.pageMethods.CompanyActionsPage;
import com.markit.framework.mcpm.pageMethods.EntityPage;
import com.markit.framework.mcpm.pageMethods.KYCDashboardTaskpage;
import com.markit.framework.mcpm.pageMethods.KYCEntityDetailsPage;
import com.markit.framework.mcpm.pageMethods.KYCdashboardpage;
import com.markit.framework.mcpm.pageMethods.MCPMLoginpage;
import com.markit.framework.mcpm.pageMethods.NavigationPage;
import com.markit.framework.mcpm.pageMethods.KYCDashboardTaskpage.InternalConfirmationTaskpage;
import com.markit.framework.mcpm.pageMethods.KYCEntityDetailsPage.CommonDetails;
import com.markit.framework.mcpm.pageMethods.KYCEntityDetailsPage.Countries_Industries;
import com.markit.framework.mcpm.pageMethods.KYCEntityDetailsPage.KYCPermissionPage;

public class KYCDashBoardRegressionTC {
	private static  String companyName=null;
	KYCdashboardpage dashboard= new KYCdashboardpage();
	public static String wbPath="src/testdata/TestData_EntityDoc.xlsx";
	public static String sheetName="Entity";
	AdminPage admin=new AdminPage();
	NavigationPage nav=new NavigationPage();
	private static List<String> userList= new ArrayList<>();
	CompanyActionsPage.CountriesIndustriesAssessment country=new CompanyActionsPage().new CountriesIndustriesAssessment();
	CompanyActionsPage.WolfsbergAML wolfsberg=new CompanyActionsPage().new WolfsbergAML();
	CompanyActionsPage.Documents documents=new CompanyActionsPage().new Documents();
	CompanyActionsPage.ContactsTab contact=new CompanyActionsPage().new ContactsTab();
	KYCEntityDetailsPage.CommonDetails comDet=new KYCEntityDetailsPage().new CommonDetails();
	KYCEntityDetailsPage.KYCDocumentsTabPage doc=new KYCEntityDetailsPage().new KYCDocumentsTabPage();
	KYCEntityDetailsPage.Countries_Industries con=new KYCEntityDetailsPage().new Countries_Industries();
	String exculdeEntityName=null;
	String nonExculdeEntityName=null;
	KYCDashboardTaskpage.InternalConfirmationTaskpage internal= new KYCDashboardTaskpage().new InternalConfirmationTaskpage();
	KYCDashboardTaskpage.PermissionRequest permisson= new KYCDashboardTaskpage().new PermissionRequest();
	KYCEntityDetailsPage.KYCPermissionPage per=new KYCEntityDetailsPage().new KYCPermissionPage(); 
	public static Map<String,Integer> headerIndex=new HashMap<String,Integer>();

	
	public KYCDashBoardRegressionTC()
	{
		List<String> tc1 =ExcelUtil.fn_GetTestData("src/testdata/testWorkbook.xlsx","TestDataSheet","TestCaseName","tc_TermsofUseKYC");
		userList=ExcelUtil.fn_FetchSimilarFieldValue(tc1,"UserName");
	}
	
	public void tc_01_CreateACompanyAndChangeCompanyOFUser(WebDriver driver)	
	{
		
		
		String compName= "Auto Company KYC"+WebUtil.generateRandomNumber();
		nav.navigateToAdminPage(driver);
		companyName=admin.addCompany(driver, compName, "BS", 1);
		WebUtil.wait(5000);
		admin.changeCompanyOfAUser(driver, userList.get(0), companyName);
		int size=admin.verifyIfUserHaveThePassedRole(driver, userList.get(0), "mcpm.kyc.esign");
		if(size!=0)
		{
			admin.removeRole(driver, "mcpm.kyc.esign");
		}
			
		admin.changeCompanyOfAUser(driver, userList.get(1), companyName);
		size=admin.verifyIfUserHaveThePassedRole(driver, userList.get(1), "bs.read");
		if(size!=0)
		{
			admin.removeRole(driver, "bs.read");
		}
	}
	
	public void tc_02_TermOfUse(WebDriver driver)
	{
		
		MCPMLoginpage m=new MCPMLoginpage();
		m.mcpmTOU(driver);
		NavigationPage n = new NavigationPage();
		n.navigateToKYCServices(driver);
		KYCdashboardpage k= new KYCdashboardpage();
		k.kycTOU(driver);
		WebUtil.wait(5000);
		KYCEntityDetailsPage e = new KYCEntityDetailsPage();
		dashboard.clickingAtCPLink(driver,"KYCDashboard.xpath.CP");		
	}
	
	/*public void tc_03_verifyIfUserHasBSRole(WebDriver driver)
	{
		WebUtil.refreshURL(driver);
		WebUtil.wait(10000);
		nav.navigateToAdminPage(driver);		
		int size=admin.verifyIfUserHaveThePassedRole(driver, userList.get(1), "bs.read");
		if(size!=0)
		{
			admin.removeRole(driver, "bs.read");
		}
		
	}*/
	
	public void tc_04_verifyNonKYCUser(WebDriver driver)
	{
		nav.nameofApperaingMenu(driver);
	}
	
	public void tc_05_verifyBSReadRole(WebDriver driver)
	{
		AdminPage admin=new AdminPage();
		nav.navigateToAdminPage(driver);
		admin.addNewRole(driver, userList.get(1), "bs.read");
	}
	public void tc_06_verifyBSReadKycDashbaord(WebDriver driver)
	{
		nav.nameofApperaingMenu(driver);
		
	}
	
	public void tc_07_createFundandNonFundEntities(WebDriver driver)
	{
		
		nav.navigateToCreateEntity(driver);
		EntityPage ep=new EntityPage();	
		for(int i=4;i<=7;i++)
		{
		ep.createEntity(driver,wbPath, sheetName,i);
		WebUtil.wait(2000);
		ep.saveEntityDetails(driver);
		WebUtil.wait(3000);		
		if(i==7)
		{
			break;
		}
		nav.navigateToCreateEntity(driver);
		}
	
		
	}
	public void tc_08_verifyNBFIEntitiesInComAction(WebDriver driver)
	{
		nav.navigateToKYCServices(driver);
		exculdeEntityName=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "TrueLegalName", 6);
		nonExculdeEntityName=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "TrueLegalName", 7);
		nav.navigateToKYCDashboardTools(driver);
		country.clickAtCIALink();
		country.verifyTheNonFundEntitiesOnCIATab(driver, exculdeEntityName);
		country.verifyTheNonFundEntitiesOnCIATab(driver, nonExculdeEntityName);
		country.excludeEntity(driver,exculdeEntityName);
		country.fill_detailsofCountriesIndustriesAssessment(driver);
		WebUtil.clickedWithAction(driver, "KYCRegression.xpath.DismissCandI");
		comDet.clickBackToDashboard(driver);
   	    WebUtil.wait(4000);
		
	}
	
	public void tc_09_verifyEsignRole(WebDriver driver)
	{
		dashboard.clickonTaskTab(driver);
		dashboard.clickAtLinkAsPerPassedName(driver, "Internal Confirmation");
		String text=WebUtil.readElementText(driver, "KYCRegression.xpath.PermissionErrorMsg");
		ReportUtil.reportStringMatch("Verify the error msg", "You are not currently authorized to complete Internal Confirmation tasks. Please email kycsprofile@kyc.com to request eSign privileges be added to your account.", text);
		/*internal.scrollDown(driver);
		internal.fillInternalConfirmationForm(driver, "Monika");*/
	}
	
	public void assignESignRole(WebDriver driver)
	{
		AdminPage admin=new AdminPage();
		nav.navigateToAdminPage(driver);
		admin.addNewRole(driver, userList.get(0), "mcpm.kyc.esign");
	}
	public void verifyCompleteCandA(WebDriver driver)
	{
		
		nav.navigateToKYCServices(driver);
		dashboard.clickonTaskTab(driver);
		dashboard.clickAtLinkAsPerPassedName(driver, "Countries & Industries Assessment");
		country.fill_detailsofCountriesIndustriesAssessment(driver);
		internal.scrollDown(driver);
		internal.fillInternalConfirmationForm(driver, "Monika");
		dashboard.clickonEntityTab(driver);
	}
	
	public void tc_10_verifyCompleteWolfsbergAMLQuestionnaire(WebDriver driver)
	{
		nav.navigateToKYCDashboardTools(driver);
		wolfsberg.cliclkAtWolfsbergQuestionnaire(driver);
		wolfsberg.verify_filling_Wolfsberg_Questionnaire(driver);
		internal.scrollDown(driver);
		internal.fillInternalConfirmationForm(driver, "Monika");
		
	}
	
	public void tc_11_verifyDocumentsTab(WebDriver driver)
	{
		nav.navigateToKYCDashboardTools(driver);
		documents.clickAtDocumentsLink();
		documents.uploadDocuments(driver);
	}
	
	public void tc_12_verifyContactsTab(WebDriver driver)
	{
		nav.navigateToKYCDashboardTools(driver);
		contact.navigateToContactsTab(driver);
		contact.addContacts(driver);
	}
	
	public void tc_13_verifyFundEntity(WebDriver driver)
	{
		NavigationPage.setMcpmFrame(driver);
		String entityName=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "TrueLegalName", 4);
		String kycEntType=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "EntityType", 4);
		dashboard.setFilter(driver, "KYCDashboardFilter.xpath.EntityTypeFilter", kycEntType, "KYCDashboard.xpath.Defocus");
		dashboard.clickAtLinkToNavigateToKYCEntityDetailsPage(driver, "KYCDashboard.xpath.ActionRequiredLink");
		NavigationPage.setMcpmFrame(driver);
		comDet.verifyTheOpenTask(driver, "No open tasks");
		comDet.verifyListofPreKYCSteps(driver);
		comDet.clickBackToDashboard(driver);
		dashboard.clearFilter();
	}
	
	public void tc_14_verifyExculdedNonFundEntity(WebDriver driver)
	{
	
		NavigationPage.setMcpmFrame(driver);
		dashboard.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		WebElement element =dashboard.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		dashboard.setValueInFilter(driver, element, exculdeEntityName);
		dashboard.applyFliter(driver, exculdeEntityName, "KYCDashboard.xpath.Defocus");
		dashboard.clickAtLinkToNavigateToKYCEntityDetailsPage(driver, "KYCDashboard.xpath.ActionRequiredLink");
		NavigationPage.setMcpmFrame(driver);
		comDet.verifyTheOpenTask(driver, "Countries & Industries Assessment");
		comDet.verifyListofPreKYCSteps(driver);
		comDet.clickBackToDashboard(driver);
		dashboard.clearFilter();
	}
	
	public void tc_15_verifyNonExculdedNonFundEntity(WebDriver driver)
	{
	
		NavigationPage.setMcpmFrame(driver);
		dashboard.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		WebElement element =dashboard.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		dashboard.setValueInFilter(driver, element, nonExculdeEntityName);
		dashboard.applyFliter(driver, exculdeEntityName, "KYCDashboard.xpath.Defocus");
		dashboard.clickAtLinkToNavigateToKYCEntityDetailsPage(driver, "KYCDashboard.xpath.ActionRequiredLink");
		NavigationPage.setMcpmFrame(driver);
		comDet.verifyTheOpenTask(driver, "No open tasks");
		comDet.verifyListofPreKYCSteps(driver);
		comDet.clickBackToDashboard(driver);
		dashboard.clearFilter();
	}
	
	public void tc_16_verifyEditEntity(WebDriver driver)
	{
		
		String kycEntType=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "EditEntTypeFromKYC", 6);
		String kycCountry=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "EditCountryFromKYC", 6);
		NavigationPage.setMcpmFrame(driver);
		dashboard.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		WebElement element =dashboard.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		dashboard.setValueInFilter(driver, element, exculdeEntityName);
		dashboard.applyFliter(driver, exculdeEntityName, "KYCDashboard.xpath.Defocus");
		dashboard.clickAtLinkToNavigateToKYCEntityDetailsPage(driver, "KYCDashboard.xpath.ActionRequiredLink");
		NavigationPage.setMcpmFrame(driver);	     	
     	WebUtil.waitUntilElementClickable(driver, "KYCEntityDetail.xpath.EditLink");
     	WebUtil.wait(5000);
     	comDet.clickEditEntity(driver);  
    	WebUtil.selectValueFromDropDownByVisibleText(driver, "KYCEditEntity.xpath.EntityType", kycEntType);
     	WebUtil.selectValueFromDropDownByVisibleText(driver, "KYCEditEntity.xpath.Country", kycCountry);
     	WebUtil.click("KYCEditEntity.xpath.Defocus");
     	comDet.saveEditedEntity(driver);
     	WebUtil.wait(5000);
     	comDet.clickBackToDashboard(driver);
     	dashboard.clickAtLinkToNavigateToKYCEntityDetailsPage(driver, "KYCDashboard.xpath.ActionRequiredLink");
     	comDet.validateEditedValuesFromKYC(driver, kycEntType, kycCountry);
	}
	
/*	public void tc_17_verifyCIAFromEntityDetailsPage(WebDriver driver)
	{
		
		NavigationPage.setMcpmFrame(driver);	   
		WebUtil.clickedWithAction(driver, "CountriesIndustries.xpath.Link");
		WebUtil.wait(5000);
		con.fill_Countries_Industries_details(driver);
		WebUtil.wait(7000);
		internal.scrollDown(driver);
		internal.fillInternalConfirmationForm(driver,"Monika");
		comDet.checkCountriesIndustriesStatus(driver, "CountriesIndustries.xpath.CountriesIndustriesStatus");
		
	}*/
	
	
	public void tc_18_verifyUploadAndDownloadFile(WebDriver driver)
	{
		NavigationPage.setMcpmFrame(driver);
        doc.uploadDocument(driver, "Articles of Incorporation", "Chrome");		
		WebUtil.wait(5000);
		String username=System.getProperty("user.name");
		String path="C:/Users/"+username+"/Downloads";			
		if(MCPMLoginTC.browsername.equalsIgnoreCase("Chrome"))	
		{			
			WebUtil.emptyAFolder(path);	
			doc.downloadFileForChrome(driver, "Articles of Incorporation");
			List<String> fileName=WebUtil.readFilesNameInAFolder(path);
			ReportUtil.reportStringMatch("Verify the downloaded file name", "Entity_batch_Upload.xlsx", fileName.get(0).toString());
		  
		}
		comDet.verifyListofPreKYCSteps(driver);
		
	}
	
	public WebDriver tc_19_verifyPermissionPage(WebDriver driver) 
	{
		per.clickAtPermissionTab(driver);
		per.validatePermissionPage(driver, "KYCPermission.xpath.ValidateElement");
		per.serchBankName(driver, "KYCPermission.xpath.SearchInput", "Abbey National", "KYCPermission.xpath.SearchButton");
		WebUtil.wait(7000);
		comDet.checkPermissionStatus(driver, "KYCPermission.xpath.PermissionStatus");
		per.validateRequestKYCButton(driver, "KYCPermission.xpath.RequestKYCButton");
		WebUtil.wait(7000);
		per.validatePopUp(driver, "KYCPermission.xpath.PopUp", "KYCPermission.xpath.PopUpCancelButton");
		nav.navigateBackToDashBoard(driver, "KYCPermission.xpath.BackToDashBoard");	
		dashboard.validateStateofEntityAfterCompltion(driver, "KYCPermission.xpath.ReadinessStatus");
		return driver;

	}	
	public void tc_20_verifyTheTaskTabDefaultState(WebDriver driver) 
	{
		dashboard.clickonTaskTab(driver);
		dashboard.verifyTheTaskState(driver, "Open");		
	}
	
	public void tc_20_verifyInternalConfirmationTask(WebDriver driver) 
	{
		dashboard.clearFilter();
		internal.clickAtInternalConfirmationNTask(driver);
		dashboard.clickAtLinkAsPerPassedName(driver, "Internal Confirmation");
		internal.scrollDown(driver);
		internal.fillInternalConfirmationForm(driver, "Monika");		
	}
	
	public void tc_21_verifyKYCReadinessGraph(WebDriver driver) 
	{
		NavigationPage.setMcpmFrame(driver);
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
	
	
	public void tc_22_verifyTraining(WebDriver driver)
	{
		NavigationPage.setMcpmFrame(driver);
		nav.navigateToKYCTrainingAddingNewEntity(driver);
		String urlname=WebUtil.storeOldURL(driver);
		WebUtil.verifyURLMatches(driver, "https://markit.webex.com", "Training");
		WebUtil.switchtoOldUrl(driver, urlname);
	}
	public void tc_23_verifyAuditRepot(WebDriver driver)
	{
		String path=null;
		nav.navigateToKYCServices(driver);
		if(MCPMLoginTC.browsername.equalsIgnoreCase("Chrome"))	
		{
			path=WebUtil.downloadFolderPathAsperCurrentUser(driver);
			WebUtil.emptyAFolder(path);				
		}
		nav.navigateToKYCReportsAudit(driver);
		List<String> fileName=WebUtil.readFilesNameInAFolder(path);
		String expectedName="KYC_Audit_Report_"+WebUtil.getTheCurrentDateAsperpassedFormat("MM-dd-yy")+".xlsx";
		ReportUtil.reportStringMatch("Verify the downloaded file name", expectedName, fileName.get(0).toString());
		
	}
	
	public void tc_24_BulkInternalConfirmation(WebDriver driver)
	{
		String text=null;
		text=WebUtil.readElementText(driver, "KYCRegression.xpath.InternalConfirmationCount");
		nav.navigateToKYCDashboardBulkInternalConfirmation(driver);
		if(text.equalsIgnoreCase("0"))
		{
			
			text=WebUtil.readElementText(driver, "KYCRegression.xpath.InternalConfirmationErrorMsg");
			ReportUtil.reportStringMatch("Verify Bulk Internal Confirmation Error", "There are no open Internal Confirmation tasks for completion.", text);
		}
		else
		{
		internal.bulkInternalConfimtion(driver);	
		text=WebUtil.readElementText(driver, "KYCRegression.xpath.InternalConfirmationCount");
		ReportUtil.reportStringMatch("Verify Bulk Internal Confirmation Count", "0", text);
		}
	}
	
	public void tc_25_bulkPermisisonRequest(WebDriver driver)
	{
		String text=null;
		text=WebUtil.readElementText(driver, "KYCRegression.xpath.PermissionRequestCount");
		nav.navigateToKYCDashboardBulkPermissionRequests(driver);
		if(text.equalsIgnoreCase("0"))
		{
			
			text=WebUtil.readElementText(driver, "KYCRegression.xpath.InternalConfirmationErrorMsg");
			ReportUtil.reportStringMatch("Verify Bulk Internal Confirmation Error", "There are no open Permission Request tasks for completion.", text);
		}
		else
		{
		
		permisson.bulkPermissionrequest(driver);	
		permisson.verifyPermissionTaskCountAfterBP(driver);
		}
		
	}
	
	public void tc_26_impersonateCompany(WebDriver driver)
	{
		nav.navigateToKYCVerification(driver);
		dashboard.impersonationACompanyAndVerify(driver, "MCPM_KYC dashboard");
	}
	
	public void tc_01_filter_EntityName (WebDriver driver) 
	{
		NavigationPage.setMcpmFrame(driver);
		KYCdashboardpage kyc = new KYCdashboardpage();        		
		kyc.clickonEntityTab(driver);
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
		WebUtil.wait(4000);
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
	NavigationPage.setMcpmFrame(driver);
	WebUtil.clickedWithAction(driver, "KYCDashboardFilter.xpath.RelationshipFilter");
	WebUtil.clickedWithAction(driver, "KYCDashboardFilter.xpath.RelationshipFilterOption");
	WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.Defocus");
	String value=WebUtil.readElementText(driver, "KYCDashboardFilter.xpath.RelationshipFilterInputBox");
	System.out.println("element is avilable"+value);
	WebUtil.clickedWithAction(driver, "KYCDashboardFilter.xpath.EntityNameLink");
	WebUtil.wait(3000);
	WebUtil.clickedWithAction(driver, "KYCDashboardFilter.xpath.PermissionsTab");
	WebElement ele=driver.findElement(By.xpath("//div[@class='scrollable ng-isolate-scope']//ul//li//div[contains(text(),'"+value+"')]"));
	if(ele.isDisplayed())
	{
		ReportUtil.reportStringMatch("Verify the selected relationship is avilable", value, value);
	}	
	
	WebUtil.clickedWithAction(driver, "KYCDashboardFilter.css.Back");
	WebUtil.wait(4000);
}
	
}
