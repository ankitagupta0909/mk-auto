package com.markit.framework.pega.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import atu.testng.reports.logging.LogAs;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.framework.kyc.pageMethods.EntityScreener;
import com.markit.framework.kyc.pageMethods.Navigationbar;
import com.markit.framework.mcpm.pageMethods.NavigationPage;
import com.markit.framework.pega.pageMethods.PEGACases;
import com.markit.framework.pega.pageMethods.PEGANavigationBar;
import com.markit.framework.pega.pageMethods.PEGAUnassignedCases;
import com.markit.kyc.framework.testcases.EntityScreener_ViewSubscribed_TC;
import com.markit.mcpm.framework.testcases.KYCDashboardPageTC;

public class PEGASanityTC {
	PEGANavigationBar nav=new PEGANavigationBar();
	PEGAUnassignedCases unas=new PEGAUnassignedCases();
	PEGACases cases=new PEGACases();
	//String legalName="Auto Fetch QA 1389";
	//String legalName="Automation_Pls dont use_6";
	//String caseId="569885496411867";
	private static String docType=null;
	private static String docSource=null;
	private static String docSourceName=null;
	private static String editDocSourceName=null;
	private static String certifiedtrueCopy=null;
	private static String internallyConfirmed=null;
	private static String EntNameFieldValue=null;
	private List<String> expectedData=null;
	String legalName=tc_00_getLegalName();

	public String tc_00_getLegalName(){
		List<String> testdata=null;
		testdata = ExcelUtil.fn_GetTestData( "src/testdata/testWorkbook.xlsx","TestDataSheet","TestCaseName","tc_pega_SP_entityFilter");
		String legalName=ExcelUtil.fn_FetchFieldValue(testdata,"EntInptVal");
		return legalName;
	}

	public void tc_01_caseFetch(WebDriver driver){
		
		nav.navigationTopMenuTeamManager(driver, "Tools");

		WebUtil.waitUntilElementPresent(driver, "PEGA.xpath.CreateCase");
		WebUtil.click("PEGA.xpath.CreateCase");
		WebUtil.waitUntilElementPresent(driver, "PEGA.xpath.SearchCasebyName");

		//Set Web Edit and search by Entity name
		WebElement elem=WebUtil.findElement("PEGA.xpath.SearchCasebyName", driver);
		WebUtil.setWebEdit(elem, legalName);
		String actVal = WebUtil.readWebElement("WebEdit", elem);
		ReportUtil.reportStringMatch("Verify value has been set in webEdit", legalName, actVal);

		//Click Search button
		List<WebElement> element= WebUtil.findElements("PEGA.xpath.SearchCasebutton", driver);
		WebUtil.clickAt(element.get(2));
		WebUtil.waitUntilElementPresent(driver, "PEGA.xpath.SelectCaseToCreate");
		ReportUtil.reportWebElement("Verify Search button is clicked: ", true);
		WebUtil.wait(3000);

		// Click checkbox to select case and create it
		WebUtil.click("PEGA.xpath.SelectCaseToCreate");
		WebUtil.wait(8000);
		WebUtil.click("PEGA.xpath.CreateCaseButton");
		WebUtil.wait(10000);

	}

	public void tc_01_caseFetchbyId(WebDriver driver,String caseId){

		nav.navigationTopMenuTeamManager(driver, "Tools");

		WebUtil.waitUntilElementPresent(driver, "PEGA.xpath.CreateCase");
		WebUtil.click("PEGA.xpath.CreateCase");
		WebUtil.wait(5000);
		WebUtil.waitUntilElementPresent(driver, "PEGA.xpath.CreateCasebyId");

		//Set Web Edit and search by Entity name
		WebElement elem=WebUtil.findElement("PEGA.xpath.CreateCasebyId", driver);
		WebUtil.setWebEdit(elem, caseId);
		String actVal = WebUtil.readWebElement("WebEdit", elem);
		ReportUtil.reportStringMatch("Verify value has been set in webEdit", caseId, actVal);

		//Click Search button
		List<WebElement> element= WebUtil.findElements("PEGA.xpath.SearchCasebutton", driver);
		WebUtil.clickAt(element.get(1));
		WebUtil.waitUntilElementPresent(driver, "PEGA.xpath.SelectCaseToCreate");
		ReportUtil.reportWebElement("Verify Search button is clicked: ", true);

		WebUtil.wait(3000);
		// Click checkbox to select case and create it
		WebUtil.click("PEGA.xpath.SelectCaseToCreate");
		WebUtil.wait(8000);
		WebUtil.click("PEGA.xpath.CreateCaseButton");
		WebUtil.wait(10000);
	}


	public void tc_02_caseAssign_firstTime(WebDriver driver){
		
		WebUtil.wait(6000);
		driver=nav.switchFrame1(driver);
		unas.applyFilter(driver, "PEGA.xpath.ClickEntityFilter",legalName);

		WebUtil.wait(6000);

		WebUtil.click("PEGA.xpath.ActionsButton");
		WebUtil.click("PEGA.xpath.ClaimButton");

		WebUtil.wait(5000);
		driver.switchTo().defaultContent();
		nav.navigationTopMenuTeamManager(driver, "Cases");
		driver=nav.switchFrame2(driver);

		unas.applyFilter(driver, "PEGA.xpath.ClickEntityFilter",legalName);
		WebUtil.wait(5000);
		WebUtil.click("PEGACases.xpath.Actionsbutton");
		WebUtil.waitUntilElementPresent(driver, "PEGACases.xpath.StartCase");
		WebUtil.click("PEGACases.xpath.StartCase");

		WebUtil.wait(15000);

	}
	
	public void tc_02_caseAssign(WebDriver driver){
		
		WebUtil.wait(6000);
		driver=nav.switchFrame0(driver);
		unas.applyFilter(driver, "PEGA.xpath.ClickEntityFilter",legalName);

		WebUtil.wait(6000);

		WebUtil.click("PEGA.xpath.ActionsButton");
		WebUtil.click("PEGA.xpath.ClaimButton");

		WebUtil.wait(5000);
		driver.switchTo().defaultContent();
		nav.navigationTopMenuTeamManager(driver, "Cases");
		driver=nav.switchFrame1(driver);

		unas.applyFilter(driver, "PEGA.xpath.ClickEntityFilter",legalName);
		WebUtil.wait(5000);
		WebUtil.click("PEGACases.xpath.Actionsbutton");
		WebUtil.waitUntilElementPresent(driver, "PEGACases.xpath.StartCase");
		WebUtil.click("PEGACases.xpath.StartCase");

		WebUtil.wait(15000);

	}

	public void tc_03_UploadDoc(WebDriver driver) throws IOException
	{
		expectedData = new ArrayList<String>();
		expectedData=expectedValue();

		
		//nav.switchFrameStartCases(driver);
		nav.switchFrame3(driver);
		
		//cd.sessionCheckOnCaseID(driver);
		cases.getCOBSID(driver);
		WebUtil.wait(5000);
		cases.uploadDoc(driver);
		WebUtil.wait(2000);
		cases.fillDetailsforUploadedDoc(driver, expectedData);
		WebUtil.wait(5000);
	}

	public void tc_04_EditDocumentDetails(WebDriver driver) 
	{
		String docId=cases.dbValidationForDocumentID();
		WebUtil.wait(7000);
		cases.editDoc(driver, "PEGACase.xpath.DocSourceName", editDocSourceName,docId);
		WebUtil.wait(5000);
	}


	public void tc_05_bulkAttachDocument(WebDriver driver) 
	{
		String docId=cases.dbValidationForDocumentID();
		WebUtil.wait(5000);
		cases.attachDoc(driver,docId);

	}


	public void tc_06_dataEnrichment(WebDriver driver)
	{
		//nav.switchFrameStartCases(driver);
		nav.switchFrame3(driver);
		WebUtil.wait(5000);
		cases.fillingLegalFormationData(driver);
		
		int random=WebUtil.generateRandomNumber();
		String controller="Controller"+random;	
		String bo="BeneficialOwner"+random;
		cases.addNonIndivKeyController(driver,controller);
		cases.addNonIndivBeneficialOwner(driver,bo);
	}

	public void tc_07_taskCreation(WebDriver driver){

		String expectedStatus="STATUS: PENDING APPROVAL";

		//driver=nav.switchFrameStartCases(driver);
		nav.switchFrame3(driver);
		WebUtil.wait(4000);
		WebUtil.click("PEGACases.xpath.LegalFormationLink");
		WebUtil.wait(6000);

		WebUtil.click("PEGACases.xpath.LegalNameCPOLink");
		WebUtil.wait(6000);
		cases.createInfoRequest(driver);
		WebUtil.wait(6000);

		WebUtil.click("PEGACases.xpath.LegalFormCPOLink");
		WebUtil.wait(6000);
		cases.createDocRequest(driver);
		WebUtil.wait(6000);

		List<WebElement> Status= WebUtil.findElements("PEGACase.xpath.ReadStatus", driver);
		String InfoTaskStatus=Status.get(0).getText();
		String DocRequestTaskStatus=Status.get(1).getText();
		String WolfsbergRequestTaskStatus=Status.get(2).getText();

		ReportUtil.reportStringMatch("Verify Info req Task Status", expectedStatus, InfoTaskStatus);
		ReportUtil.reportStringMatch("Verify Doc req Task Status", expectedStatus, DocRequestTaskStatus);
		ReportUtil.reportStringMatch("Verify Wolfsberg req Task Status", expectedStatus, WolfsbergRequestTaskStatus);

		WebUtil.click("PEGACase.xpath.SaveContinue");
		WebUtil.wait(7000);
		WebUtil.click("PEGACases.xpath.OwnershipInfoLink");
		WebUtil.wait(7000);
		WebUtil.click("PEGACases.xpath.LegalNameCPOLink");
		WebUtil.wait(6000);
		cases.createOwnershipTask(driver);
		WebUtil.wait(6000);


		List<WebElement> OwnershipTask= WebUtil.findElements("PEGACase.xpath.ReadStatus", driver);
		String OwnershipTaskStatus=OwnershipTask.get(0).getText();
		ReportUtil.reportStringMatch("Verify Ownership req Task Status", expectedStatus, OwnershipTaskStatus);

		// Request outreach
		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.wait(6000);
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.RequestOutreach");
		WebUtil.click("PEGACase.xpath.RequestOutreach");
		WebUtil.wait(6000);
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.ConfirmRequestOutreach");
		WebUtil.click("PEGACase.xpath.ConfirmRequestOutreach");
		WebUtil.wait(7000);

	}

	public void tc_08_taskCompletionByCsa(WebDriver driver){

		String expectedStatus="STATUS: READY FOR OUTREACH";
		//Task Assignment
		tc_02_caseAssign(driver);
		driver=nav.switchFrame2(driver);
		//Task Completion
		
		//driver=nav.switchFrameStartCases(driver);
		driver=nav.switchFrame2(driver);
		WebUtil.wait(4000);
		WebUtil.click("PEGACases.xpath.LegalFormationLink");
		WebUtil.wait(6000);

		WebUtil.click("PEGACase.xpath.AcceptLegalName");
		WebUtil.wait(7000);
		WebUtil.click("PEGACase.xpath.AcceptLegalForm");
		WebUtil.wait(8000);
		WebUtil.click("PEGACase.xpath.AcceptLegalType");
		WebUtil.wait(7000);
		
		List<WebElement> Status= WebUtil.findElements("PEGACase.xpath.ReadStatus", driver);
		WebUtil.wait(6000);
		String InfoTaskStatus=Status.get(0).getText();
		String DocRequestTaskStatus=Status.get(1).getText();
		String WolfsbergRequestTaskStatus=Status.get(2).getText();

		ReportUtil.reportStringMatch("Verify Info req Task Status-CSA", expectedStatus, InfoTaskStatus);
		ReportUtil.reportStringMatch("Verify Doc req Task Status-CSA", expectedStatus, DocRequestTaskStatus);
		ReportUtil.reportStringMatch("Verify Wolfsberg req Task Status-CSA", expectedStatus, WolfsbergRequestTaskStatus);

		WebUtil.click("PEGACase.xpath.SaveContinue");
		WebUtil.wait(6000);

		WebUtil.click("PEGACases.xpath.OwnershipInfoLink");
		WebUtil.wait(9000);
		WebUtil.click( "PEGACase.xpath.AcceptOwnershipColl");
		//clickWithAction("PEGACase.xpath.AcceptOwnershipColl");
		WebUtil.wait(7000);

		WebElement OwnershipTask= WebUtil.findElement("PEGACase.xpath.ReadStatus", driver);
		WebUtil.wait(5000);
		String OwnershipTaskStatus=OwnershipTask.getText();
		ReportUtil.reportStringMatch("Verify Ownership req Task Status-CSA", expectedStatus, OwnershipTaskStatus);

		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.wait(5000);
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.SendToClient");
		WebUtil.click("PEGACase.xpath.SendToClient");
		WebUtil.wait(5000);
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.ConfirmSendToClient");
		WebUtil.click("PEGACase.xpath.ConfirmSendToClient");
		WebUtil.wait(8000);
	}


	public void tc_09_kyc_completeTask(WebDriver driver,String entityName){

		KYCDashboardPageTC kyc=new KYCDashboardPageTC();
		try {
			kyc.testDataDetails();
			NavigationPage.setMcpmFrame(driver);
			kyc.navigateToKYCAndSettingFilterAsPerPassedName(driver,entityName);
		    kyc.tc_01_tasks_OutreachQuestionnaire(driver);
			kyc.tc_03_tasks_DocumentRequest(driver);
		
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ReportUtil.reportElementException("Exception KYC complete Tasks", LogAs.FAILED);
		} 


	}

	public void tc_10_CSAacceptsCompletedTasks(WebDriver driver){


		String expectedStatus="STATUS: CLIENT RESPONSE RECEIVED";


		//Task Start
		nav.navigationTopMenuTeamManager(driver, "Cases");
		//driver=nav.switchFrameCases(driver);
		driver=nav.switchFrame1(driver);

		unas.applyFilter(driver, "PEGA.xpath.ClickEntityFilter",legalName);

		WebUtil.click("PEGACases.xpath.Actionsbutton");
		WebUtil.waitUntilElementPresent(driver, "PEGACases.xpath.StartCase");
		WebUtil.click("PEGACases.xpath.StartCase");

		WebUtil.wait(15000);

		//Task Completion
		driver=nav.switchFrame2(driver);
		WebUtil.wait(2000);
		WebUtil.click("PEGACases.xpath.LegalFormationLink");
		WebUtil.wait(6000);
		WebUtil.click("PEGACase.xpath.CaseTab");

		List<WebElement> Status= WebUtil.findElements("PEGACase.xpath.ReadStatus", driver);
		WebUtil.wait(3000);
		String InfoTaskStatus=Status.get(0).getText();
		String DocRequestTaskStatus=Status.get(1).getText();
		String WolfsbergRequestTaskStatus=Status.get(2).getText();

		ReportUtil.reportStringMatch("Verify Info req Task Status post completion from KYC dashboard", expectedStatus, InfoTaskStatus);
		ReportUtil.reportStringMatch("Verify Doc req Task Status post completion from KYC dashboard", expectedStatus, DocRequestTaskStatus);
		ReportUtil.reportStringMatch("Verify Wolfsberg req Task Status post completion from KYC dashboard", expectedStatus, WolfsbergRequestTaskStatus);

		WebUtil.click("PEGACase.xpath.AcceptLegalName");
		WebUtil.wait(8000);
		WebUtil.click("PEGACase.xpath.AcceptLegalForm");
		WebUtil.wait(8000);
		WebUtil.click("PEGACase.xpath.AcceptLegalType");
		WebUtil.wait(8000);
	

		expectedStatus="STATUS: CLIENT RESPONSE ACCEPTED";
		List<WebElement> NewStatus= WebUtil.findElements("PEGACase.xpath.ReadStatus", driver);
		WebUtil.wait(6000);
		InfoTaskStatus=NewStatus.get(0).getText();
		DocRequestTaskStatus=NewStatus.get(1).getText();
		WolfsbergRequestTaskStatus=NewStatus.get(2).getText();

		ReportUtil.reportStringMatch("Verify Info req Task Status-CSA", expectedStatus, InfoTaskStatus);
		ReportUtil.reportStringMatch("Verify Doc req Task Status-CSA", expectedStatus, DocRequestTaskStatus);
		ReportUtil.reportStringMatch("Verify Wolfsberg req Task Status-CSA", expectedStatus, WolfsbergRequestTaskStatus);

		WebUtil.click("PEGACase.xpath.SaveContinue");
		WebUtil.wait(6000);

		WebUtil.click("PEGACases.xpath.OwnershipInfoLink");
		WebUtil.wait(6000);
		WebUtil.click("PEGACase.xpath.AcceptOwnershipColl");
		WebUtil.wait(6000);

		List<WebElement> OwnershipTask= WebUtil.findElements("PEGACase.xpath.ReadStatus", driver);
		WebUtil.wait(6000);
		String OwnershipTaskStatus=OwnershipTask.get(0).getText();
		ReportUtil.reportStringMatch("Verify Ownership req Task Status-CSA", expectedStatus, OwnershipTaskStatus);

		
		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.wait(6000);
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.SendToKYC");
		WebUtil.click("PEGACase.xpath.SendToKYC");
		WebUtil.wait(6000);
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.ConfirmSendToKYC");
		WebUtil.click("PEGACase.xpath.ConfirmSendToKYC");
		WebUtil.wait(6000);

	}


	public void tc_11_sendCaseToSME(WebDriver driver){

		//Task Start
		nav.navigationTopMenuTeamManager(driver, "Cases");
		//driver=nav.switchFrameCases(driver);
		driver=nav.switchFrame1(driver);

		unas.applyFilter(driver, "PEGA.xpath.ClickEntityFilter",legalName);

		WebUtil.click("PEGACases.xpath.Actionsbutton");
		WebUtil.waitUntilElementPresent(driver, "PEGACases.xpath.StartCase");
		WebUtil.click("PEGACases.xpath.StartCase");

		WebUtil.wait(15000);

		//Verify Status of tasks
		//driver=nav.switchFrameStartCases(driver);
		driver=nav.switchFrame2(driver);
		
		WebUtil.wait(2000);
		WebUtil.click("PEGACases.xpath.LegalFormationLink");
		WebUtil.wait(5000);
		WebUtil.click("PEGACase.xpath.CaseTab");
		WebUtil.wait(3000);

		String expectedStatus="STATUS: CLIENT RESPONSE ACCEPTED";
		List<WebElement> NewStatus= WebUtil.findElements("PEGACase.xpath.ReadStatus", driver);
		String InfoTaskStatus=NewStatus.get(0).getText();
		String DocRequestTaskStatus=NewStatus.get(1).getText();
		String WolfsbergRequestTaskStatus=NewStatus.get(2).getText();

		ReportUtil.reportStringMatch("Verify Info req Task Status-KYC TM", expectedStatus, InfoTaskStatus);
		ReportUtil.reportStringMatch("Verify Doc req Task Status-KYC TM", expectedStatus, DocRequestTaskStatus);
		ReportUtil.reportStringMatch("Verify Wolfsberg req Task Status-KYC TM", expectedStatus, WolfsbergRequestTaskStatus);

		WebUtil.click("PEGACases.xpath.OwnershipInfoLink");
		WebUtil.wait(5000);

		List<WebElement> OwnershipTask= WebUtil.findElements("PEGACase.xpath.ReadStatus", driver);
		String OwnershipTaskStatus=OwnershipTask.get(0).getText();
		ReportUtil.reportStringMatch("Verify Ownership req Task Status-KYC TM", expectedStatus, OwnershipTaskStatus);

		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.SendToSME");
		WebUtil.wait(3000);
		WebUtil.click("PEGACase.xpath.SendToSME");
		WebUtil.wait(3000);
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.ConfirmSendToSME");
		WebUtil.click("PEGACase.xpath.ConfirmSendToSME");
		WebUtil.wait(6000);

	}

	public void tc_12_caseAcceptedBySME(WebDriver driver){

		tc_02_caseAssign(driver);

		//Verify Status of tasks
		//driver=nav.switchFrameStartCases(driver);
		
		driver=nav.switchFrame2(driver);
		WebUtil.wait(2000);

		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.wait(3000);
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.AcceptAll");
		WebUtil.click("PEGACase.xpath.AcceptAll");
		WebUtil.wait(3000);

		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.wait(3000);
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.SendToQA");
		WebUtil.click("PEGACase.xpath.SendToQA");
		WebUtil.wait(3000);
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.ConfirmSendToSME");
		WebUtil.click("PEGACase.xpath.ConfirmSendToSME");
		WebUtil.wait(6000);

	}

	public void tc_13_caseAcceptedByQA(WebDriver driver){

		tc_02_caseAssign(driver);

		//Verify Status of tasks
		//driver=nav.switchFrameStartCases(driver);
		
		driver=nav.switchFrame2(driver);
		WebUtil.wait(2000);

		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.wait(3000);
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.AcceptAll");
		WebUtil.click("PEGACase.xpath.AcceptAll");
		WebUtil.wait(3000);


		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.wait(3000);
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.SendToSP");
		WebUtil.click("PEGACase.xpath.SendToSP");
		WebUtil.wait(3000);
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.ConfirmSendToSME");
		WebUtil.click("PEGACase.xpath.ConfirmSendToSME");
		WebUtil.wait(6000);


	}

	public void tc_14_VerifyReports(WebDriver driver) throws Exception
	{

		EntityScreener es= new EntityScreener();
		EntityScreener_ViewSubscribed_TC esTc=new EntityScreener_ViewSubscribed_TC();
		esTc.tc_01_EntityNameFilter(driver, "tc_pega_SP_entityFilter", "src/testdata/testWorkbook.xlsx");
		es.clickAtEntityNameLink(driver, EntNameFieldValue);
		WebUtil.wait(4000);
		es.clickAtReports(driver);
	}



	public List<String> expectedValue() throws IOException
	{
		List<String> expectedValues=new ArrayList<String>();

		List<String> tc1 =ExcelUtil.fn_GetTestData("src/testdata/testWorkbook.xlsx","TestDataSheet","TestCaseName","tc_pega_fill_Details_Of_UploadedDoc");
		List<String> tc2 =ExcelUtil.fn_GetTestData("src/testdata/testWorkbook.xlsx","TestDataSheet","TestCaseName","tc_pega_Edit_Details_of_UploadedDoc");
		docType=ExcelUtil.fn_FetchFieldValue(tc1,"DocumentType");
		docSource=ExcelUtil.fn_FetchFieldValue(tc1,"Document_Source");
		docSourceName=ExcelUtil.fn_FetchFieldValue(tc1,"Document_Source_Name");
		editDocSourceName=ExcelUtil.fn_FetchFieldValue(tc2,"Document_Source_Name");
		certifiedtrueCopy=ExcelUtil.fn_FetchFieldValue(tc1,"Is_Certified_True_Copy");
		internallyConfirmed=ExcelUtil.fn_FetchFieldValue(tc1,"Is_Internally_Confirmed");
		expectedValues.add(docType);
		expectedValues.add(docSource);
		expectedValues.add(docSourceName);
		expectedValues.add(certifiedtrueCopy);
		expectedValues.add(internallyConfirmed);
		return expectedValues;

	}


	public void tc_12_caseAcceptedBySME_UAT(WebDriver driver){

	    tc_02_caseAssign(driver);

		//Verify Status of tasks
		//driver=nav.switchFrameStartCases(driver);
		
		driver=nav.switchFrame2(driver);
		WebUtil.wait(2000);

		 WebUtil.click("PEGACases.xpath.LegalFormationLink");
		 WebUtil.wait(5000);
		 cases.acceptAttributes(driver,"PEGACase.xpath.Accept");

		 WebUtil.click("PEGACases.xpath.AddressInfoLink");
		 WebUtil.wait(5000);
		 WebUtil.click("PEGAAddressContact.xpath.ViewButton");
		 WebUtil.wait(4000);
		 cases.acceptAttributes(driver,"PEGACase.xpath.Accept");

		 WebUtil.click("PEGACases.xpath.CorpRegLink");
		 WebUtil.wait(5000);
		 cases.acceptAttributes(driver,"PEGACase.xpath.Accept");

		 WebUtil.click("PEGACases.xpath.OwnershipInfoLink");
		 WebUtil.wait(5000);
		 cases.acceptAttributes(driver,"PEGACase.xpath.Accept");
		 
		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.wait(3000);
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.SendToQA");
		WebUtil.click("PEGACase.xpath.SendToQA");
		WebUtil.wait(3000);
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.ConfirmSendToSME");
		WebUtil.click("PEGACase.xpath.ConfirmSendToSME");
		WebUtil.wait(6000);

	}
	
	public void tc_13_caseAcceptedByQA_UAT(WebDriver driver){

		tc_02_caseAssign(driver);

		//Verify Status of tasks
		//driver=nav.switchFrameStartCases(driver);
		
		driver=nav.switchFrame2(driver);
		WebUtil.wait(2000);

		 WebUtil.click("PEGACases.xpath.LegalFormationLink");
		 WebUtil.wait(5000);
		 cases.acceptAttributes(driver,"PEGACase.xpath.Accept");

		 WebUtil.click("PEGACases.xpath.AddressInfoLink");
		 WebUtil.wait(5000);
		 WebUtil.click("PEGAAddressContact.xpath.ViewButton");
		 WebUtil.wait(4000);
		 cases.acceptAttributes(driver,"PEGACase.xpath.Accept");

		 WebUtil.click("PEGACases.xpath.CorpRegLink");
		 WebUtil.wait(5000);
		 cases.acceptAttributes(driver,"PEGACase.xpath.Accept");

		 WebUtil.click("PEGACases.xpath.OwnershipInfoLink");
		 WebUtil.wait(5000);
		 cases.acceptAttributes(driver,"PEGACase.xpath.Accept");


		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.wait(3000);
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.SendToSP");
		WebUtil.click("PEGACase.xpath.SendToSP");
		WebUtil.wait(3000);
		WebUtil.waitUntilElementPresent(driver, "PEGACase.xpath.ConfirmSendToSME");
		WebUtil.click("PEGACase.xpath.ConfirmSendToSME");
		WebUtil.wait(6000);


	}
	
	public void tc_14_verifyDbDetails(){
		EntityScreener e=new EntityScreener();
		e.dbSubscriberAttribute("500", legalName);
	}

	/********* Code to Accept attributes individually
	 * cases.acceptAttributes(driver,"PEGACase.xpath.Accept");

WebUtil.click("PEGACases.xpath.LegalFormationLink");
WebUtil.wait(5000);
cases.acceptAttributes(driver,"PEGACase.xpath.Accept");

WebUtil.click("PEGACases.xpath.AddressInfoLink");
WebUtil.wait(5000);
WebUtil.click("PEGAAddressContact.xpath.ViewButton");
WebUtil.wait(4000);
cases.acceptAttributes(driver,"PEGACase.xpath.Accept");

WebUtil.click("PEGACases.xpath.CorpRegLink");
WebUtil.wait(5000);
cases.acceptAttributes(driver,"PEGACase.xpath.Accept");

WebUtil.click("PEGACases.xpath.FinancialInfoLink");
WebUtil.wait(5000);
cases.acceptAttributes(driver,"PEGACase.xpath.Accept");


WebUtil.click("PEGACases.xpath.OwnershipInfoLink");
WebUtil.wait(5000);
cases.acceptAttributes(driver,"PEGACase.xpath.Accept");*/

	/*WebUtil.setFrame(driver, "MFrame");

WebUtil.click("KYCTaskOSR.xpath.AddBO");
WebUtil.waitUntilElementPresent(driver, "KYCTaskOSR.xpath.SelectIndividualType");
WebUtil.click("KYCTaskOSR.xpath.SelectIndividualType");

WebUtil.waitUntilElementPresent(driver, "KYCTaskOSR.xpath.FirstName");
WebElement firstName= WebUtil.findElement("KYCTaskOSR.xpath.FirstName", driver);
WebUtil.setWebEdit(firstName, "firstName");

WebElement lastName= WebUtil.findElement("KYCTaskOSR.xpath.LastName", driver);
WebUtil.setWebEdit(lastName, "lastName");

WebUtil.click("KYCTaskOSR.xpath.SelectEntityNominee");
WebUtil.click("KYCTaskOSR.xpath.ClickCountry");
WebUtil.click("KYCTaskOSR.xpath.SelectCountry");
WebUtil.click("KYCTaskOSR.xpath.defocusCountry");

WebElement sourceWealth= WebUtil.findElement("KYCTaskOSR.xpath.SourceWealth", driver);
WebUtil.setWebEdit(sourceWealth, "sourceWealth");

WebElement ownershipPercent= WebUtil.findElement("KYCTaskOSR.xpath.OwnershipPercentage", driver);
WebUtil.setWebEdit(ownershipPercent, "22");

WebUtil.click("KYCTaskOSR.xpath.Submit");*/
}




