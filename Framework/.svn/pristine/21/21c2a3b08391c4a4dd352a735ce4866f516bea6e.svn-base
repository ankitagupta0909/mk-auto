package com.markit.mcpm.framework.testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;
import com.markit.framework.kyc.pageMethods.EntityScreener;
import com.markit.framework.mcpm.pageMethods.NavigationPage;
import com.markit.framework.mcpm.pageMethods.RFA;

public class RFALetterTemplateTC {

	Map<String,Integer> headerIndex=new HashMap<String,Integer>();

	public void init(WebDriver driver) throws InterruptedException{
		RFA letter=new RFA();
		letter.navigateLetterTemp(driver);	
		headerIndex=letter.getColIndexMap(driver, "RFAletter.xpath.FilterResultTable");
	}

	public  void tc_01_TempNameFilter(WebDriver driver) throws Exception{

		int colIndex=headerIndex.get("Letter Template Name");
		String FieldValue=RFA.ReadColValue(driver, colIndex, "RFAletter.xpath.FilterResultTable");

		EntityScreener.setFilter(driver, "Template Name", "RFA.xpath.Refdate", "RFA.xpath.RefdateChkbx", "RFA.xpath.RefdateText", FieldValue);
		WebUtil.click("RFAletter.xpath.defocusFilter");
		RFA.VerifyFilterMatches(driver, "RFAletter.xpath.FilterResultTable", "RFA.xpath.Refdate", colIndex, FieldValue);

	}

	public void tc_02_LastEditedByFilter(WebDriver driver) throws Exception{

		int colIndex=headerIndex.get("Last Edited By");
		String FieldValue=RFA.ReadColValue(driver, colIndex, "RFAletter.xpath.FilterResultTable");

		EntityScreener.setFilter(driver, "Last Edited By", "RFA.xpath.MasId", "RFA.xpath.MasIdChkbx", "RFA.xpath.MasIdText", FieldValue);
		WebUtil.click("RFAletter.xpath.defocusFilter");
		RFA.VerifyFilterMatches(driver, "RFAletter.xpath.FilterResultTable", "RFA.xpath.MasId", colIndex, FieldValue);

	}


	public void tc_03_CreatedByFilter(WebDriver driver) throws Exception{

		int colIndex=headerIndex.get("Created By");
		String FieldValue=RFA.ReadColValue(driver, colIndex, "RFAletter.xpath.FilterResultTable");

		EntityScreener.setFilter(driver, "Created By", "RFA.xpath.PartyA", "RFA.xpath.PartyAChkbx", "RFA.xpath.PartyAText", FieldValue);
		WebUtil.click("RFAletter.xpath.defocusFilter");
		RFA.VerifyFilterMatches(driver, "RFAletter.xpath.FilterResultTable", "RFA.xpath.PartyA", colIndex, FieldValue);

	}

	public void tc_createDeleteLetterTemplate(WebDriver driver) {

		NavigationPage nav = new NavigationPage();
		nav.navigateToRFA(driver);

		RFA r=new RFA();
		String tempName=r.createLetterTemplate(driver, "PBA");
		headerIndex=r.getColIndexMap(driver, "RFAletter.xpath.FilterResultTable");
		int colIndex=headerIndex.get("Letter Template Name");
		String createdTempName=RFA.ReadColValue(driver, colIndex, "RFAletter.xpath.FilterResultTable");
		ReportUtil.reportStringMatch("Verify template created", tempName, createdTempName);
		WebUtil.wait(6000);

		try {
			tc_01_TempNameFilter(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}

		r.deleteLetterTemplate();
		String valText=WebUtil.readElementText(driver, "RFAletter.xpath.NoResults");
		String expected="No search results found for this query.";
		ReportUtil.reportStringMatch("Verify letter template deleted", expected, valText);
		//r.createLetterTemplate(driver, "All");
	}


	public String tc_createLetterTemplate(WebDriver driver){

		NavigationPage nav = new NavigationPage();
		nav.navigateToRFA(driver);

		RFA r=new RFA();
		String tempName=r.createLetterTemplate(driver, "All");
		headerIndex=r.getColIndexMap(driver, "RFAletter.xpath.FilterResultTable");
		int colIndex=headerIndex.get("Letter Template Name");
		String createdTempName=RFA.ReadColValue(driver, colIndex, "RFAletter.xpath.FilterResultTable");
		ReportUtil.reportStringMatch("Verify template created", tempName, createdTempName);
		WebUtil.wait(6000);
		return createdTempName;

	}



	/*public void tc_createRFA(WebDriver driver){
		RFA r =new RFA();
		NavigationPage.setMcpmFrame(driver);
		WebUtil.click("RFA.xpath.CreateRFAlink");
		WebUtil.wait(4000);
		WebUtil.click("RFA.xpath.CreateViaPartyA");
		WebUtil.wait(4000);
		WebUtil.click("RFA.xpath.SelectPartyARel");
		WebUtil.wait(4000);
		WebUtil.click("RFA.xpath.CreateRFANextButton");
		WebUtil.wait(4000);
		WebUtil.click("RFA.xpath.SelectLetterTemp");

		String letterTemp="AutomationLetterTemp_-1992715971";
		r.selectLetterTemplate(driver, letterTemp);
		WebUtil.click("RFA.xpath.CreateRFANextButton");
		WebUtil.wait(4000);


		r.selectPartyBaccounts(driver,"27RFA_38","ADD");
		r.selectPartyBaccounts(driver,"27RFA_47","REMOVE");
		r.selectPartyBaccounts(driver,"27RFA_48","MODIFY");
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
		headerIndex=r.getColIndexMap(driver, "RFAletter.xpath.FilterResultTable");
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

		//Accept all the Party B accounts
		WebUtil.clickonListofLinks(driver, "RFA.xpath.Accept");
		WebUtil.click("RFA.xpath.SaveRespSS");


	}*/


}
