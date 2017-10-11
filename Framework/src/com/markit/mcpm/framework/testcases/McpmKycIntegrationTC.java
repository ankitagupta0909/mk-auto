package com.markit.mcpm.framework.testcases;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.markit.common.framework.util.Config;
import com.markit.common.framework.util.DBConnection;
import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.framework.mcpm.pageMethods.EntityPage;
import com.markit.framework.mcpm.pageMethods.KYCEntityDetailsPage;
import com.markit.framework.mcpm.pageMethods.KYCdashboardpage;
import com.markit.framework.mcpm.pageMethods.MCPMDocumentsPage;
import com.markit.framework.mcpm.pageMethods.MCPMLoginpage;
import com.markit.framework.mcpm.pageMethods.NavigationPage;
import com.markit.framework.mcpm.pageMethods.KYCEntityDetailsPage.KYCDocumentsTabPage;

public class McpmKycIntegrationTC {
	public static Map<String,Integer> headerIndex=new HashMap<String,Integer>();
	public static String wbPath="src/testdata/TestData_EntityDoc.xlsx";
	public static String sheetName="Entity";
	Connection con=DBConnection.dbConnections.get("MCPM");
	NavigationPage nav=new NavigationPage();
	KYCdashboardpage dashboard= new KYCdashboardpage();
	MCPMDocumentsPage doc= new MCPMDocumentsPage();
	KYCEntityDetailsPage.KYCDocumentsTabPage kycDoc=new KYCEntityDetailsPage().new KYCDocumentsTabPage();
	private static String entityName=null;
	private static String macpmDocId=null;
	private static String kycDocId=null;
	public static Connection connection=null;
	private static String docType=null;
	private static String editDocType=null;
	private static String docDescription=null;
	private static String entityName2=null;
	int implWaitTime=20;
	
	public McpmKycIntegrationTC()
	{
		Map<String,String> dbConfig=Config.loadConfig("src/testdata/DBconfig.properties");
		DBConnection.getDBInstance(dbConfig, "KYC");
		connection=DBConnection.dbConnections.get("KYC");	
		docType=ExcelUtil.getColumnDataByColName(wbPath,"Document","DocType",1);
		editDocType=ExcelUtil.getColumnDataByColName(wbPath,"Document","EditDocType1",1);
		docDescription=ExcelUtil.getColumnDataByColName(wbPath,"Document","Description",1);

		
	}

	public void tc_01_verifyNewEntityOnKycDashboard(WebDriver driver){
		driver.manage().timeouts().implicitlyWait(implWaitTime, TimeUnit.SECONDS);
		NavigationPage nav=new NavigationPage();
		nav.navigateToCreateEntity(driver);
		EntityPage ep=new EntityPage();
	    entityName=ep.createEntity(driver,wbPath, sheetName,1);
		ep.saveEntityDetails(driver);

		KYCdashboardpage kyc = new KYCdashboardpage();
		nav.navigateToKYCServices(driver);

		headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.Table");
		int colIndex=headerIndex.get("Entity Name");		
		kyc.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		WebElement element =kyc.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		kyc.setValueInFilter(driver, element, entityName.trim());
		kyc.applyFliter(driver, entityName, "KYCDashboard.xpath.Defocus");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.Table", "Entity Name", colIndex, entityName);

	}

	public void tc_02_verifyEntityDetailsOnKYC(WebDriver driver){
		driver.manage().timeouts().implicitlyWait(implWaitTime, TimeUnit.SECONDS);

		KYCdashboardpage kyc = new KYCdashboardpage();

		try {
			kyc.clickAtEntityName(driver, "KYCDashboard.xpath.EntityLink");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String actEntName=WebUtil.readElementText(driver, "KYCEntityDetail.xpath.EntityName");
		String actMEI=WebUtil.readElementText(driver, "KYCEntityDetail.xpath.MEIName");
		String actLEI=WebUtil.readElementText(driver, "KYCEntityDetail.xpath.LEIName");
		String actEntType=WebUtil.readElementText(driver, "KYCEntityDetail.xpath.EntityType");
		String actCI=WebUtil.readElementText(driver, "KYCEntityDetail.xpath.CI");
		String actCountry=WebUtil.readElementText(driver, "KYCEntityDetail.xpath.Country");

		String expEntName=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "TrueLegalName", 1);
		String expCI=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "CI", 1);
		String expLEI=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "LEI", 1);
		String expMEI=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "MEI", 1);
		String expCountry=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "Country", 1);
		String expEntType=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "EntityType", 1);

		ReportUtil.reportStringMatch("Verify detail for EntityName on KYC page", expEntName, actEntName);
		ReportUtil.reportStringMatch("Verify detail for CI on KYC page", expCI, actCI);
		ReportUtil.reportStringMatch("Verify detail for LEI on KYC page", expLEI, actLEI);
		ReportUtil.reportStringMatch("Verify detail for MEI on KYC page", expMEI, actMEI);
		ReportUtil.reportStringMatch("Verify detail for Country on KYC page", expCountry, actCountry);
		ReportUtil.reportStringMatch("Verify detail for EntityType on KYC page", expEntType, actEntType);

	}
	
	

	public void tc_03_verifyEditedEntityDetailsOnMcpm(WebDriver driver){

		String kycEntType=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "EditEntTypeFromKYC", 1);
		String kycCountry=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "EditCountryFromKYC", 1);
		
		driver.manage().timeouts().implicitlyWait(implWaitTime, TimeUnit.SECONDS);
		NavigationPage.setMcpmFrame(driver);
		KYCdashboardpage kyc = new KYCdashboardpage();     	
     	KYCEntityDetailsPage.CommonDetails comDet=new KYCEntityDetailsPage().new CommonDetails();
     	WebUtil.waitUntilElementClickable(driver, "KYCEntityDetail.xpath.EditLink");
     	WebUtil.wait(5000);
     	comDet.clickEditEntity(driver);  
     	System.out.println("Entity type"+kycEntType+" and "+ kycCountry );
     	WebUtil.selectValueFromDropDownByVisibleText(driver, "KYCEditEntity.xpath.EntityType", kycEntType);
     	WebUtil.selectValueFromDropDownByVisibleText(driver, "KYCEditEntity.xpath.Country", kycCountry);
     	WebUtil.click("KYCEditEntity.xpath.Defocus");
     	comDet.saveEditedEntity(driver);
     	WebUtil.wait(5000);
     	comDet.clickBackToDashboard(driver);
     	WebUtil.wait(5000);     	
     	WebUtil.waitUntilElementClickable(driver, "KYCDashboard.xpath.CP");
     	kyc.clickingAtCPLink(driver,"KYCDashboard.xpath.CP");
     	WebUtil.wait(8000);
		NavigationPage n=new NavigationPage();
		EntityPage ep=new EntityPage();
		n.navigateToManageEntities(driver);
		NavigationPage.setMcpmFrame(driver);
		WebUtil.wait(2000);
		ep.setAndApplyFilter(driver, entityName, "Entity.name.LegalNameFilter");
		headerIndex=ep.getEntityColIndexMap(driver);
		int colIndex=headerIndex.get("True/Legal Name");		
		EntityPage.verifyFilterMatches(driver, "Entity.xpath.ResultGrid", "True/Legal Name", colIndex, entityName);
		colIndex=headerIndex.get("Entity Type");
		EntityPage.verifyFilterMatches(driver, "Entity.xpath.ResultGrid", "Entity Type", colIndex, kycEntType);
		colIndex=headerIndex.get("Country of Domicile");
		EntityPage.verifyFilterMatches(driver, "Entity.xpath.ResultGrid", "Country of Domicile", colIndex, kycCountry);
		
	}
	public void tc_04_verifyEditedEntityDetailsOnKYC(WebDriver driver){

		driver.manage().timeouts().implicitlyWait(implWaitTime, TimeUnit.SECONDS);
		NavigationPage.setMcpmFrame(driver);
		KYCdashboardpage kyc = new KYCdashboardpage();
		EntityPage ep=new EntityPage();
		NavigationPage n=new NavigationPage();
		int iRowNum=1;
		
        ep.clickEditEntity(driver);		
		String expCI=ep.editCI(driver, wbPath, sheetName, iRowNum);
		String expCountry=ep.editCountry(driver, wbPath, sheetName, iRowNum);
		ep.editDisplayName(driver, wbPath, sheetName, iRowNum);
		String expEntType=ep.editEntityTypeEligibleKYC(driver, wbPath, sheetName, iRowNum);
		String expEntName=ep.editLegalName(driver, wbPath, sheetName, iRowNum);
		ep.saveEntityDetails(driver);
		
		n.navigateToKYCServices(driver);
		headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.Table");
		int columnIndex=headerIndex.get("Entity Name");		
		kyc.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		WebElement element =kyc.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		kyc.setValueInFilter(driver, element, expEntName.trim());
		kyc.applyFliter(driver, expEntName, "KYCDashboard.xpath.Defocus");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.Table", "Entity Name", columnIndex, expEntName);

		try {
			kyc.clickAtEntityName(driver, "KYCDashboard.xpath.EntityLink");
			WebUtil.wait(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String actEntName=WebUtil.readElementText(driver, "KYCEntityDetail.xpath.EntityName");
		String actEntType=WebUtil.readElementText(driver, "KYCEntityDetail.xpath.EntityType");
		String actCI=WebUtil.readElementText(driver, "KYCEntityDetail.xpath.CI");
		String actCountry=WebUtil.readElementText(driver, "KYCEntityDetail.xpath.Country");

		ReportUtil.reportStringMatch("Verify detail for EntityName on KYC page", expEntName, actEntName);
		ReportUtil.reportStringMatch("Verify detail for CI on KYC page", expCI, actCI);
		ReportUtil.reportStringMatch("Verify detail for Country on KYC page", expCountry, actCountry);
		ReportUtil.reportStringMatch("Verify detail for EntityType on KYC page", expEntType, actEntType);


	}
	

	public void tc_05_verifyEntityNotInScope(WebDriver driver){
		driver.manage().timeouts().implicitlyWait(implWaitTime, TimeUnit.SECONDS);
		KYCdashboardpage kyc = new KYCdashboardpage();	
		String kycEntType=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "EditInvalidEntityTypeKYC", 1);
		String trueLegalName=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "TrueLegalName", 1);
			
		NavigationPage.setMcpmFrame(driver);   	
     	KYCEntityDetailsPage.CommonDetails comDet=new KYCEntityDetailsPage().new CommonDetails();
     	
     	WebUtil.waitUntilElementClickable(driver, "KYCEntityDetail.xpath.EditLink");
     	WebUtil.wait(5000);
     	comDet.clickEditEntity(driver);    	
     	WebUtil.selectValueFromDropDownByVisibleText(driver, "KYCEditEntity.xpath.EntityType", kycEntType);
     	comDet.saveEditedEntity(driver);
     	WebUtil.wait(5000);
     	comDet.clickBackToDashboard(driver);
     	WebUtil.wait(5000);
     	
     	///This should be removed once bug is fixed
     	WebUtil.refreshURL(driver);
    	
     	////////////////////////////////////////////
     	
     	NavigationPage.setMcpmFrame(driver);   
     	headerIndex=kyc.getColIndexMap(driver, "KYCDashboardFilter.xpath.Table");
		int columnIndex=headerIndex.get("Entity Name");		
		kyc.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		WebElement element =kyc.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		kyc.setValueInFilter(driver, element, trueLegalName.trim());
		kyc.applyFliter(driver, trueLegalName, "KYCDashboard.xpath.Defocus");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.Table", "Entity Name", columnIndex, trueLegalName);
     	columnIndex=headerIndex.get("KYC Readiness");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.Table", "KYC Readiness", columnIndex, "Not In Scope");
		columnIndex=headerIndex.get("Entity Type");
		KYCdashboardpage.VerifyFilterMatches(driver, "KYCDashboardFilter.xpath.Table", "Entity Type", columnIndex, kycEntType);
	}
	
	public void tc_06_addDoc(WebDriver driver)
	{
		NavigationPage nav=new NavigationPage();
		nav.navigateToCreateEntity(driver);
		EntityPage ep=new EntityPage();			
		entityName=ep.createEntity(driver,wbPath, "Entity",2);
		WebUtil.wait(2000);
		ep.saveEntityDetails(driver);
		WebUtil.wait(3000);
		nav.navigateToManageDocuments(driver);		
		System.out.println("Entity Name"+entityName);
        doc.addDocument(driver, entityName, docType, docDescription);
	    WebUtil.wait(7000);
	    macpmDocId=doc.dbValidationForDocId(con, MCPMLoginpage.username);
	    System.out.println("the doc id is "+macpmDocId);
	    WebUtil.wait(3000);
	    kycDocId= doc.dbKYCValidationforDocId(connection,macpmDocId);
	    System.out.println("the doc id of KYC "+kycDocId);
		
	}
	
	
	
	public void tc_07_verifyTheAddedDocOnKYC(WebDriver driver)
	{
		nav.navigateToKYCServices(driver);

		dashboard.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		WebElement element =dashboard.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		dashboard.setValueInFilter(driver, element, entityName);
		dashboard.applyFliter(driver, entityName, "KYCDashboard.xpath.Defocus");
		kycDoc.clickAtDocumentsTab(driver);
		System.out.println("KYC id"+kycDocId);
		kycDoc.verifyDocIDOfUploadedDoc(driver, kycDocId,docType);
		WebUtil.wait(3000);
		String userName=MCPMLoginpage.dbValidationforUserName(con);
		WebUtil.wait(5000);
		kycDoc.validateTheUploadedDocuemnt(driver, docType, userName);	
		
		
	}
	
	public void tc_08_verifyDocGetsDownloadedFromDashboard(WebDriver driver)
	{
		String username=System.getProperty("user.name");
		String path="C:/Users/"+username+"/Downloads";
		WebUtil.emptyAFolder(path);
		System.out.println("Document id is: "+docType);
		kycDoc.downloadFileForChrome(driver, docType);
		List<String> fileName=WebUtil.readFilesNameInAFolder(path);
		try{
		ReportUtil.reportStringMatch("Verify the downloaded file name", "Entity_batch_Upload.xlsx", fileName.get(0).toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public void tc_09_verifyEditDocWithKYCSupportedDocType(WebDriver driver)
	{
		KYCEntityDetailsPage.backtoKYCDashboard();	
		dashboard.clickingAtCPLink(driver,"KYCDashboard.xpath.CP");
		WebUtil.wait(3000);
		nav.navigateToManageDocuments(driver);
		System.out.println("MCPM id"+macpmDocId);
		doc.setDocumentIDfilter(driver, macpmDocId);
		docType=doc.editDocType(driver, editDocType);	   
	}
	
	public void tc_10_verifyEditedDoconKYCDashboard(WebDriver driver)
	{
		nav.navigateToKYCServices(driver);
		dashboard.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		WebElement element =dashboard.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		dashboard.setValueInFilter(driver, element, entityName);
		dashboard.applyFliter(driver, entityName, "KYCDashboard.xpath.Defocus");
		dashboard.clickAtLinkToNavigateToKYCEntityDetailsPage(driver, "KYCDashboard.xpath.ActionRequiredLink");
		kycDoc.clickAtDocumentsTab(driver);
		WebUtil.wait(3000);
		kycDocId= doc.dbKYCValidationforDocId(connection,macpmDocId);
		WebUtil.wait(5000);
		System.out.println("KYC id"+kycDocId);
		kycDoc.verifyDocIDOfUploadedDoc(driver, kycDocId,docType);	
		KYCEntityDetailsPage.backtoKYCDashboard();
	}
	
	public void tc_11_deleteKYCDocFromMCPM(WebDriver driver)
	{
	
		dashboard.clickingAtCPLink(driver,"KYCDashboard.xpath.CP");
		nav.navigateToManageDocuments(driver);
		doc.setDocumentIDfilter(driver, macpmDocId);
		doc.deleteDocument(driver);
		WebUtil.wait(5000);
		
	}
	
	public void tc_12_verifyNoDocIsAvilableOnDashBoard(WebDriver driver)
	{
		nav.navigateToKYCServices(driver);
		dashboard.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		WebElement element =dashboard.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		dashboard.setValueInFilter(driver, element, entityName);
		dashboard.applyFliter(driver, entityName, "KYCDashboard.xpath.Defocus");
		dashboard.clickAtLinkToNavigateToKYCEntityDetailsPage(driver, "KYCDashboard.xpath.ActionRequiredLink");
		kycDoc.clickAtDocumentsTab(driver);
		kycDoc.verifyNoDocIsUploaded(driver);
		
	}
	
	public void tc_13_verifyNonSupportedDocNotReflectedInKYC(WebDriver driver)
	{
		KYCEntityDetailsPage.backtoKYCDashboard();
		dashboard.clickingAtCPLink(driver,"KYCDashboard.xpath.CP");
		nav.navigateToManageDocuments(driver);
		doc.addDocument(driver, entityName, "Addendum", docDescription);
		nav.navigateToKYCServices(driver);
		dashboard.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		WebElement element =dashboard.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		dashboard.setValueInFilter(driver, element, entityName);
		dashboard.applyFliter(driver, entityName, "KYCDashboard.xpath.Defocus");
		dashboard.clickAtLinkToNavigateToKYCEntityDetailsPage(driver, "KYCDashboard.xpath.ActionRequiredLink");
		kycDoc.clickAtDocumentsTab(driver);
		kycDoc.verifyNoDocIsUploaded(driver);
		
		
	}
	
	public void tc_14_uploadDocFromKYC(WebDriver driver)
	{
		String docIdofKYC=null;
		
		docIdofKYC=kycDoc.uploadDocument(driver,"Articles of Incorporation",MCPMLoginTC.browsername);
		String mcpmDocId=doc.dbValidationForMCPMDocID(connection,docIdofKYC);
		KYCEntityDetailsPage.backtoKYCDashboard();
		dashboard.clickingAtCPLink(driver,"KYCDashboard.xpath.CP");
		nav.navigateToManageDocuments(driver);
		doc.setDocumentIDfilter(driver, mcpmDocId);
		
	}
	
	public void tc_15_downloadDocFromMCPM(WebDriver driver)
	{
		String username=System.getProperty("user.name");
		String path="C:/Users/"+username+"/Downloads";
		WebUtil.emptyAFolder(path);
		doc.downloadMCPMDoc(driver);
		List<String> fileName=WebUtil.readFilesNameInAFolder(path);
		ReportUtil.reportStringMatch("Verify the downloaded file name", "Entity_batch_Upload.xlsx", fileName.get(0).toString());
		doc.clearAppliedFilter(driver);
	}
	
	public void tc_16_uploadDocForMultipleEntitiesAndVerifyOnDashboard(WebDriver driver)
	{
		NavigationPage nav=new NavigationPage();
		nav.navigateToCreateEntity(driver);
		EntityPage ep=new EntityPage();
		entityName2=ep.createEntity(driver,wbPath, "Entity",3);
		ep.saveEntityDetails(driver);
		System.out.println("The entity 1:"+entityName+" and entity 2 "+entityName2);
		docType=ExcelUtil.getColumnDataByColName(wbPath,"Document","DocType",1);

		nav.navigateToManageDocuments(driver);
		WebElement element=null;
		String userName=null;
		WebUtil.wait(5000);
		doc.addDocWithMultipleEnties(driver,3);
		macpmDocId=doc.dbValidationForDocId(con, MCPMLoginpage.username);
		nav.navigateToKYCServices(driver);
		
		dashboard.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		element =dashboard.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		dashboard.setValueInFilter(driver, element, entityName);
		dashboard.applyFliter(driver, entityName, "KYCDashboard.xpath.Defocus");
		dashboard.clickAtLinkToNavigateToKYCEntityDetailsPage(driver,"KYCDashboard.xpath.ActionRequiredLink" );
		kycDoc.clickAtDocumentsTab(driver);
		userName=MCPMLoginpage.dbValidationforUserName(con);
		System.out.println(userName+" and doc type is "+docType);
		kycDoc.validateTheUploadedDocuemnt(driver, docType, userName);			
		KYCEntityDetailsPage.backtoKYCDashboard();	
		dashboard.clearFilter();			
		dashboard.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		element =dashboard.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		dashboard.setValueInFilter(driver, element, entityName2);
		dashboard.applyFliter(driver, entityName2, "KYCDashboard.xpath.Defocus");
		dashboard.clickAtLinkToNavigateToKYCEntityDetailsPage(driver,"KYCDashboard.xpath.ActionRequiredLink" );
		kycDoc.clickAtDocumentsTab(driver);
		WebUtil.wait(3000);
		userName=MCPMLoginpage.dbValidationforUserName(con);			
		kycDoc.validateTheUploadedDocuemnt(driver, docType, userName);			
		KYCEntityDetailsPage.backtoKYCDashboard();		

	}
	
	public void tc_17_unTagAnEntityAndValidate(WebDriver driver)
	{
		
		dashboard.clickingAtCPLink(driver,"KYCDashboard.xpath.CP");
		nav.navigateToManageDocuments(driver);
		doc.setDocumentIDfilter(driver, macpmDocId);
		doc.selectFirstCheckBox();
		doc.editADocToUntagAnEntity(driver, entityName);
		nav.navigateToKYCServices(driver);
		dashboard.clickAtFilters(driver,"KYCDashboard.xpath.EntityFilter");
		WebElement element =dashboard.clickAtInputBoxOfFilter(driver, "KYCDashboard.xpath.EntityEdit");
		dashboard.setValueInFilter(driver, element, entityName2);
		dashboard.applyFliter(driver, entityName2, "KYCDashboard.xpath.Defocus");
		dashboard.clickAtLinkToNavigateToKYCEntityDetailsPage(driver,"KYCDashboard.xpath.ActionRequiredLink" );
		kycDoc.clickAtDocumentsTab(driver);
		kycDoc.verifyNoDocIsUploaded(driver);
		KYCEntityDetailsPage.backtoKYCDashboard();
		
	}
	
	
}

