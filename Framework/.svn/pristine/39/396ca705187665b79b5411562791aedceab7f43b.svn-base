package com.markit.mcpm.framework.testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;
import com.markit.framework.kyc.pageMethods.Dashboard;
import com.markit.framework.kyc.pageMethods.EntityScreener;
import com.markit.framework.mcpm.pageMethods.EntityPage;
import com.markit.framework.mcpm.pageMethods.NavigationPage;
import com.markit.framework.mcpm.pageMethods.RFA;
import com.markit.framework.mcpm.pageMethods.RequestTrackerpage;

public class RFAHomePageTC {

	public static Map<String,Integer> headerIndex=new HashMap<String,Integer>();

	public void init(WebDriver driver) throws InterruptedException{
		NavigationPage nav= new NavigationPage();
		nav.navigateToRFA(driver);
		RFA home=new RFA();
		headerIndex=home.getColIndexMap(driver, "RFAhome.xpath.FilterResultTable");
	}

	public void tc_01_RefDataFilter(WebDriver driver) throws Exception{

		int colIndex=headerIndex.get("Reference Master\nAgreement Date");
		String FieldValue=RFA.ReadColValue(driver, colIndex, "RFAhome.xpath.FilterResultTable");

		EntityScreener.setFilter(driver, "Ref date", "RFA.xpath.Refdate", "RFA.xpath.RefdateChkbx", "RFA.xpath.RefdateText", FieldValue);
		WebUtil.click("RFA.xpath.defocusFilter");
		RFA.VerifyFilterMatches(driver, "RFAhome.xpath.FilterResultTable", "RFA.xpath.Refdate", colIndex, FieldValue);

	}


	public void tc_02_MasterlistIdFilter(WebDriver driver) throws Exception{

		int colIndex=headerIndex.get("Masterlist Identifier");
		String FieldValue=RFA.ReadColValue(driver, colIndex, "RFAhome.xpath.FilterResultTable");

		if (FieldValue.equals("")){
			ReportUtil.reportWebElement("Cannot apply and verify filter since no value retrieved", true);
		}
		else{
			EntityScreener.setFilter(driver, "Masterlist Id", "RFA.xpath.MasId", "RFA.xpath.MasIdChkbx", "RFA.xpath.MasIdText", FieldValue);
			WebUtil.click("RFA.xpath.defocusFilter");

			RFA.VerifyFilterMatches(driver, "RFAhome.xpath.FilterResultTable", "RFA.xpath.MasId", colIndex, FieldValue);
		}
	}

	public void tc_03_PartyAFilter(WebDriver driver) throws Exception{

		int colIndex=headerIndex.get("Party A");
		String FieldValue=RFA.ReadColValue(driver, colIndex, "RFAhome.xpath.FilterResultTable");

		EntityScreener.setFilter(driver, "Party A", "RFA.xpath.PartyA", "RFA.xpath.PartyAChkbx", "RFA.xpath.PartyAText", FieldValue);
		WebUtil.click("RFA.xpath.defocusFilter");
		RFA.VerifyFilterMatches(driver, "RFAhome.xpath.FilterResultTable", "RFA.xpath.PartyA", colIndex, FieldValue);

	}


	public void tc_06_ReqStatusFilter(WebDriver driver) throws Exception{

		int colIndex=headerIndex.get("Request Status");
		//String FieldValue=RFA.ReadColValue(driver, colIndex, "RFAhome.xpath.FilterResultTable");

		EntityScreener.selectFilter(driver, "RFA.xpath.ReqStatus");
		WebUtil.verifyChkboxCheck(driver, "RFA.xpath.ReqStatusChkbx");

		String FieldValue=WebUtil.readElementText(driver, "RFA.xpath.ReqStatus");
		WebUtil.click("RFA.xpath.defocusFilter");
		RFA.VerifyFilterMatches(driver, "RFAhome.xpath.FilterResultTable", "RFA.xpath.ReqStatus", colIndex, FieldValue);

	}


	/*	public void tc_07_verifyDraftStatusChart(WebDriver driver){

		String id=XPath.myprop.get("RFAStatus.Draft.fillid");
		Dashboard.mouseHoverChart(driver, id);

		String toolTipText=WebUtil.readElementText(driver, "RFAStatus.xpath.toolTip");
		String toolTip[] =RFA.mouseHoverReadValues(driver, toolTipText);

		Dashboard.mouseHoverRelease(driver,"RFA.xpath.defocusFilter");
		Dashboard.clickOnGraphSegments(driver, id);
		String rfaFilterLabel=WebUtil.readElementText(driver, "RFA.xpath.RFAlabel");


		String ReqStatusFilterText=WebUtil.readElementText(driver, "RFA.xpath.ReqStatus");


		}*/

	public void tc_08_verifySubmittedStatusChart(WebDriver driver){

		String id=XPath.myprop.get("RFAStatus.Submitted.fillid");

		Dashboard.mouseHoverChart(driver, id);
		String toolTipText=WebUtil.readElementText(driver, "RFAStatus.xpath.toolTip");
		String toolTip[] =RFA.mouseHoverReadValues(driver, toolTipText);

		Dashboard.mouseHoverRelease(driver,"RFA.xpath.defocusFilter");
		Dashboard.clickOnGraphSegments(driver, id);

		String rfaFilterLabel=WebUtil.readElementText(driver, "RFA.xpath.RFAlabel");
		ReportUtil.reportStringMatch("Verify number of RFA on chart and filter match", toolTip[0], rfaFilterLabel);

		String ReqStatusFilterText=WebUtil.readElementText(driver, "RFA.xpath.ReqStatus");
		ReportUtil.reportStringMatch("Verify RFA status on chart should be same on Filter", toolTip[1], ReqStatusFilterText);
		int colIndex=headerIndex.get("Request Status");
		RFA.VerifyFilterMatches(driver, "RFAhome.xpath.FilterResultTable", "RFA.xpath.ReqStatus", colIndex, ReqStatusFilterText);
	}

	public void tc_09_verifyCompletedStatusChart(WebDriver driver){

		String id=XPath.myprop.get("RFAStatus.Completed.fillid");

		Dashboard.mouseHoverChart(driver, id);
		String toolTipText=Dashboard.mouseHoverReadText(driver, "RFAStatus.xpath.toolTip");
		String toolTip[] =RFA.mouseHoverReadValues(driver, toolTipText);

		Dashboard.mouseHoverRelease(driver,"RFA.xpath.defocusFilter");
		Dashboard.clickOnGraphSegments(driver, id);

		String rfaFilterLabel=WebUtil.readElementText(driver, "RFA.xpath.RFAlabel");
		ReportUtil.reportStringMatch("Verify number on chart and filter match", toolTip[0], rfaFilterLabel);

		String ReqStatusFilterText=WebUtil.readElementText(driver, "RFA.xpath.ReqStatus");
		ReportUtil.reportStringMatch("Verify RFA status on chart should be same on Filter", toolTip[1], ReqStatusFilterText);

		int colIndex=headerIndex.get("Request Status");
		RFA.VerifyFilterMatches(driver, "RFAhome.xpath.FilterResultTable", "RFA.xpath.ReqStatus", colIndex, ReqStatusFilterText);
	}

	public void tc_pendingTasks_ExhibitCompletion(WebDriver driver) throws InterruptedException
	{
		WebElement graph=WebUtil.findElement("RFAPendingTask.xpath.ExhibitCompletion",driver);
		WebUtil.actionClass(graph, driver);
		Thread.sleep(7000);
		System.out.println("Clicked");
		String rfaFilterLabel=WebUtil.readElementText(driver, "RFA.xpath.RFAlabel");
		String Label=WebUtil.readElementText(driver, "RFAPendingTask.xpath.ExhibitCompletionlabel");
		ReportUtil.reportStringMatch("Verify number of RFA on chart and filter match", Label, rfaFilterLabel);
		RFA.validateTableContentAsPerCount(driver,Label);			
	}
	public void tc_pendingTasks_SendRFA(WebDriver driver) throws InterruptedException
	{
		WebElement graph=WebUtil.findElement("RFAPendingTask.xpath.SendRFALabel",driver);
		WebUtil.actionClass(graph, driver);
		Thread.sleep(7000);
		System.out.println("Clicked");
		String rfaFilterLabel=WebUtil.readElementText(driver, "RFA.xpath.RFAlabel");
		String Label=WebUtil.readElementText(driver, "RFAPendingTask.xpath.SendRFALabel");
		ReportUtil.reportStringMatch("Verify number of RFA on chart and filter match", Label, rfaFilterLabel);
		RFA.validateTableContentAsPerCount(driver,Label);
	}

	public void tc_pendingTasks_ElectronicSign(WebDriver driver) throws InterruptedException
	{
		WebElement graph=WebUtil.findElement("RFAPendingTask.xpath.ElectronicSignLabel",driver);
		WebUtil.actionClass(graph, driver);
		Thread.sleep(7000);
		System.out.println("Clicked");
		String rfaFilterLabel=WebUtil.readElementText(driver, "RFA.xpath.RFAlabel");
		String Label=WebUtil.readElementText(driver, "RFAPendingTask.xpath.ElectronicSignLabel");
		ReportUtil.reportStringMatch("Verify number of RFA on chart and filter match", Label, rfaFilterLabel);
		RFA.validateTableContentAsPerCount(driver,Label);
	}

	public void tc_10_verifyPartCompletedStatusChart(WebDriver driver){

		String id=XPath.myprop.get("RFAStatus.PartCompleted.fillid");

		Dashboard.mouseHoverChart(driver, id);
		String toolTipText=Dashboard.mouseHoverReadText(driver, "RFAStatus.xpath.toolTip");
		String toolTip[] =RFA.mouseHoverReadValues(driver, toolTipText);

		Dashboard.mouseHoverRelease(driver,"RFA.xpath.defocusFilter");
		Dashboard.clickOnGraphSegments(driver, id);

		String rfaFilterLabel=WebUtil.readElementText(driver, "RFA.xpath.RFAlabel");
		ReportUtil.reportStringMatch("Verify number on chart and filter match", toolTip[0], rfaFilterLabel);

		String ReqStatusFilterText=WebUtil.readElementText(driver, "RFA.xpath.ReqStatus");
		ReportUtil.reportStringMatch("Verify RFA status on chart should be same on Filter", toolTip[1], ReqStatusFilterText);

		int colIndex=headerIndex.get("Request Status");
		RFA.VerifyFilterMatches(driver, "RFAhome.xpath.FilterResultTable", "RFA.xpath.ReqStatus", colIndex, ReqStatusFilterText);
	}

	public void tc_11_verifyRecalledStatusChart(WebDriver driver){

		String id=XPath.myprop.get("RFAStatus.Recalled.fillid");

		Dashboard.mouseHoverChart(driver, id);
		String toolTipText=Dashboard.mouseHoverReadText(driver, "RFAStatus.xpath.toolTip");
		String toolTip[] =RFA.mouseHoverReadValues(driver, toolTipText);

		Dashboard.mouseHoverRelease(driver,"RFA.xpath.defocusFilter");
		Dashboard.clickOnGraphSegments(driver, id);

		String rfaFilterLabel=WebUtil.readElementText(driver, "RFA.xpath.RFAlabel");
		ReportUtil.reportStringMatch("Verify number on chart and filter match", toolTip[0], rfaFilterLabel);

		String ReqStatusFilterText=WebUtil.readElementText(driver, "RFA.xpath.ReqStatus");
		ReportUtil.reportStringMatch("Verify RFA status on chart should be same on Filter", toolTip[1], ReqStatusFilterText);

		int colIndex=headerIndex.get("Request Status");
		RFA.VerifyFilterMatches(driver, "RFAhome.xpath.FilterResultTable", "RFA.xpath.ReqStatus", colIndex, ReqStatusFilterText);
	}

	public void tc_12_verifyRejectedStatusChart(WebDriver driver){

		String id=XPath.myprop.get("RFAStatus.Rejected.fillid");

		Dashboard.mouseHoverChart(driver, id);
		String toolTipText=Dashboard.mouseHoverReadText(driver, "RFAStatus.xpath.toolTip");
		String toolTip[] =RFA.mouseHoverReadValues(driver, toolTipText);

		Dashboard.mouseHoverRelease(driver,"RFA.xpath.defocusFilter");
		Dashboard.clickOnGraphSegments(driver, id);

		String rfaFilterLabel=WebUtil.readElementText(driver, "RFA.xpath.RFAlabel");
		ReportUtil.reportStringMatch("Verify number on chart and filter match", toolTip[0], rfaFilterLabel);

		String ReqStatusFilterText=WebUtil.readElementText(driver, "RFA.xpath.ReqStatus");
		ReportUtil.reportStringMatch("Verify RFA status on chart should be same on Filter", toolTip[1], ReqStatusFilterText);

		int colIndex=headerIndex.get("Request Status");
		RFA.VerifyFilterMatches(driver, "RFAhome.xpath.FilterResultTable", "RFA.xpath.ReqStatus", colIndex, ReqStatusFilterText);
	}


	public static String entityDelete;
	public static String entityModify;
	public static String entityAdd;
	public static String entityIM;
	public void tc_bulkUploadEntityMasterlistTemplatesUpdate(){

		EntityPage ent=new EntityPage();

		ArrayList<String> entityName=new ArrayList<String>();
		ArrayList<String> lei=new ArrayList<String>();
		ArrayList<String> entityType=new ArrayList<String>();

		ent.generateNameLEIforBulkUpload(entityName, lei, 3);
		ent.generateEntityTypeforBulkUpload(entityType, 2);
		entityType.add("Investment Management Company");


		ent.bulkUploadEntityTemplateUpdate("src/testdata/Templates/Entity_batch_Upload.xlsx", entityName, lei, entityType);
		
		RFA r=new RFA();
		r.bulkUploadMasterlistTemplateUpdate("src/testdata/Templates/OnboardingRFA.xlsx", entityName);
	
		entityDelete=entityName.get(0);
		entityModify=entityName.get(1);
		entityAdd=entityName.get(2);
		entityIM=entityName.get(3);
		
		System.out.println(entityDelete);
		System.out.println(entityModify);
		System.out.println(entityAdd);
	}

	
	public void tc_bulkUploads(WebDriver driver){
		RequestTrackerpage rt=new RequestTrackerpage();
		EntityPage e=new EntityPage();
		RFA r=new RFA();
		e.batchUploadEntity(driver, "ent");
		WebUtil.wait(4000);
		r.uploadRFAOnboardingFile(driver, "mandatory");
		rt.checkStatusOfRAFandEntitybatch(driver);
	}
	public void tc_createRFA(WebDriver driver,String letterTemp){
		
		RFAExhibitTemplateTC tc2 = new RFAExhibitTemplateTC();
		tc2.tc_createExhibitTemplateLinkMast(driver,entityIM);
		
		RFA r =new RFA();
		NavigationPage.setMcpmFrame(driver);
		WebUtil.click("RFA.xpath.CreateRFAlink");
		WebUtil.wait(4000);
		WebUtil.click("RFA.xpath.CreateViaPartyA");
		WebUtil.wait(4000);
		
		String selPartA_1=WebUtil.returnXPathVal("RFA.xpath.SelectPartyARel_part1");
		String selPartA_2=WebUtil.returnXPathVal("RFA.xpath.SelectPartyARel_part2");
		String xpath= selPartA_1+entityIM+selPartA_2;
		
		WebElement SelPartyA=driver.findElement(By.xpath(xpath));
		SelPartyA.click();
		WebUtil.wait(4000);
		
		//WebUtil.click("RFA.xpath.SelectPartyARel");
		//WebUtil.wait(4000);
		WebUtil.click("RFA.xpath.CreateRFANextButton");
		WebUtil.wait(4000);
		WebUtil.click("RFA.xpath.SelectLetterTemp");

		r.selectLetterTemplate(driver, letterTemp);
		WebUtil.click("RFA.xpath.CreateRFANextButton");
		WebUtil.wait(4000);


		r.selectPartyBaccounts(driver,entityAdd,"ADD");
		r.selectPartyBaccounts(driver,entityDelete,"REMOVE");
		r.selectPartyBaccounts(driver,entityModify,"MODIFY");
		WebUtil.click("RFA.xpath.CreateRFANextButton");
		WebUtil.wait(4000);

		//WebUtil.selectValueFromDropDownAsPerPassedVaule(driver, "RFA.xpath.SelectModType", "Both");
		WebUtil.click("RFA.xpath.SelectModTypeBoth");


		WebElement EVCcomm=WebUtil.findElement("RFA.xpath.EVCcomments", driver);
		WebUtil.setWebEdit(EVCcomm, "AutoComment");
		WebUtil.wait(3000);

		WebElement FNC=WebUtil.findElement("RFA.xpath.LegalNameChange", driver);
		WebUtil.setWebEdit(FNC, "_autoupdate");
		WebUtil.wait(3000);

		WebUtil.click("RFA.xpath.CreateRFANextButton");
		WebUtil.wait(4000);

		WebUtil.click("RFA.xpath.CreatedRfaSave");
		WebUtil.wait(4000);
		WebUtil.click("RFA.xpath.CloseRFApopup");
		WebUtil.wait(4000);
	}


	public void tc_editLinkedExhibit(WebDriver driver){
		NavigationPage.setMcpmFrame(driver);
		WebUtil.click("RFA.xpath.EditLinkExhib");
		WebUtil.wait(5000);

		WebElement e=WebUtil.findElement("RFA.xpath.ExhibScrollRight", driver);
		Actions actions = new Actions(driver);
		actions.moveToElement(e).click().perform();
		WebUtil.wait(3000);

		WebElement table = WebUtil.findElement("RFA.xpath.ExhibTable", driver);
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		String tab = WebUtil.returnXPathVal("RFA.xpath.ExhibTable");
		List<WebElement> headerCols = null;
		headerCols = rows.get(0).findElements(By.tagName("td"));

		int rowsize = rows.size();
		int colsize=headerCols.size();
		for (int j = 2; j <= rowsize; j++) {
			for(int col=6;col<=colsize-1;col++){
				WebElement rowValue = driver.findElement(By.xpath(tab
						+ "/tr[" + j + "]/td[" + col + "]/input"));

				actions.moveToElement(rowValue).click().perform();
				actions.sendKeys(rowValue, "data"+j+col).build().perform();
				WebUtil.wait(3000);

			}
		}

		WebUtil.wait(5000);
		WebUtil.click("RFA.xpath.ExhibSave");
		WebUtil.wait(5000);
	}

	public void tc_esign(WebDriver driver){
		RFA r=new RFA();
		r.esignAndSendRFA(driver);
	}


	public void tc_recallEditSendRFA(WebDriver driver){

		RFA r=new RFA();
		r.recallRFA();

		headerIndex=r.getColIndexMap(driver, "RFAhome.xpath.FilterResultTable");
		int colIndex=headerIndex.get("Request Status");
		String reqStatus=RFA.ReadColValue(driver, colIndex, "RFAhome.xpath.FilterResultTable");
		ReportUtil.reportStringMatch("Verify status is Recalled", "Recalled", reqStatus);

		r.editRFA();
		r.esignAndSendRFA(driver);

	}

	public void tc_rejectRFA(WebDriver driver){
		WebUtil.wait(5000);
		RFA r=new RFA();
		r.rejectRFA(driver);

		headerIndex=r.getColIndexMap(driver, "RFAhome.xpath.FilterResultTable");
		int colIndex=headerIndex.get("Request Status");
		String reqStatus=RFA.ReadColValue(driver, colIndex, "RFAhome.xpath.FilterResultTable");
		ReportUtil.reportStringMatch("Verify status is Rejected on SS", "Rejected", reqStatus);
	}

	public void tc_editSendRFA(WebDriver driver){
		WebUtil.wait(5000);
		RFA r=new RFA();
		headerIndex=r.getColIndexMap(driver, "RFAhome.xpath.FilterResultTable");
		int colIndex=headerIndex.get("Request Status");
		String reqStatus=RFA.ReadColValue(driver, colIndex, "RFAhome.xpath.FilterResultTable");
		ReportUtil.reportStringMatch("Verify status is Rejected on BS", "Rejected", reqStatus);

		r.editRFA();
		String draftStatus=RFA.ReadColValue(driver, colIndex, "RFAhome.xpath.FilterResultTable");
		ReportUtil.reportStringMatch("Verify status is DRAFT on BS after Edit", "Draft", draftStatus);
		r.esignAndSendRFA(driver);
	}


	public void tc_SSresponse(WebDriver driver){
		NavigationPage.setMcpmFrame(driver);
		WebUtil.click("RFA.xpath.EditResp");

		WebUtil.wait(5000);
		//Accept all the Party B accounts
		WebUtil.clickonListofLinks(driver, "RFA.xpath.Accept");
		WebUtil.scrollBarUp(driver);		
		WebUtil.click("RFA.xpath.SaveRespSS");
		WebUtil.wait(5000);

	}
}
