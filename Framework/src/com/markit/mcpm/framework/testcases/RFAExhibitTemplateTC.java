package com.markit.mcpm.framework.testcases;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import atu.testng.reports.logging.LogAs;

import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.framework.kyc.pageMethods.EntityScreener;
import com.markit.framework.mcpm.pageMethods.NavigationPage;
import com.markit.framework.mcpm.pageMethods.RFA;

public class RFAExhibitTemplateTC {

	Map<String,Integer> headerIndex=new HashMap<String,Integer>();

	public void init(WebDriver driver) throws InterruptedException{
		RFA exhibt=new RFA();
		exhibt.navigateExhibitTemp(driver);	
		headerIndex=exhibt.getColIndexMap(driver, "RFAExhib.xpath.FilterResultTable");
	}


	public  void tc_01_ExhibNameFilter(WebDriver driver) throws Exception{

		int colIndex=headerIndex.get("Exhibits Name");
		String ExhibFieldValue=RFA.ReadColValue(driver, colIndex, "RFAExhib.xpath.FilterResultTable");

		EntityScreener.setFilter(driver, "Exhibits Name", "RFA.xpath.Refdate", "RFA.xpath.RefdateChkbx", "RFA.xpath.RefdateText", ExhibFieldValue);
		WebUtil.click("RFAExhib.xpath.defocusFilter");
		RFA.VerifyFilterMatches(driver, "RFAExhib.xpath.FilterResultTable", "RFA.xpath.Refdate", colIndex, ExhibFieldValue);

	}

	public void tc_02_CreatedByFilter(WebDriver driver) throws Exception{

		int colIndex=headerIndex.get("Created By");
		String CreatedFieldValue=RFA.ReadColValue(driver, colIndex, "RFAExhib.xpath.FilterResultTable");

		EntityScreener.setFilter(driver, "Created By", "RFA.xpath.MasId", "RFA.xpath.MasIdChkbx", "RFA.xpath.MasIdText", CreatedFieldValue);
		WebUtil.click("RFAExhib.xpath.defocusFilter");
		RFA.VerifyFilterMatches(driver, "RFAExhib.xpath.FilterResultTable", "RFA.xpath.MasId", colIndex, CreatedFieldValue);

	}

	public void tc_03_PartyAFilter(WebDriver driver) throws Exception{

		int colIndex=headerIndex.get("Party A True/Legal Name");
		String CreatedFieldValue=RFA.ReadColValue(driver,colIndex , "RFAExhib.xpath.FilterResultTable");

		EntityScreener.setFilter(driver, "Created By", "RFA.xpath.PartyA", "RFA.xpath.PartyAChkbx", "RFA.xpath.PartyAText", CreatedFieldValue);
		WebUtil.click("RFAExhib.xpath.defocusFilter");
		RFA.VerifyFilterMatches(driver, "RFAExhib.xpath.FilterResultTable", "RFA.xpath.PartyA", colIndex, CreatedFieldValue);

	}

	public void tc_04_RefDateFilter(WebDriver driver) throws Exception{

		int colIndex=headerIndex.get("Ref. ISDA Date");
		String fieldValue=RFA.ReadColValue(driver, colIndex, "RFAExhib.xpath.FilterResultTable");

		EntityScreener.setFilter(driver, "Ref. Date", "RFAEx.xpath.RefDate", "RFAEx.xpath.RefDateChkbx", "RFAEx.xpath.RefDateText", fieldValue);
		WebUtil.click("RFAExhib.xpath.defocusFilter");
		RFA.VerifyFilterMatches(driver, "RFAExhib.xpath.FilterResultTable", "RFAEx.xpath.RefDate", colIndex,fieldValue);
	}

	public void tc_05_MasIdFilter(WebDriver driver) throws Exception{


		int colIndex=headerIndex.get("Masterlist Identifier");
		String fieldValue=RFA.ReadColValue(driver, colIndex, "RFAExhib.xpath.FilterResultTable");

		EntityScreener.setFilter(driver, "Masterlist Identifier", "RFAEx.xpath.MasId", "RFAEx.xpath.MasIdChkbx", "RFAEx.xpath.MasIdText", fieldValue);
		WebUtil.click("RFAExhib.xpath.defocusFilter");
		RFA.VerifyFilterMatches(driver, "RFAExhib.xpath.FilterResultTable", "RFAEx.xpath.MasId", colIndex,fieldValue);
	}

	public void tc_06_LinkedByFilter(WebDriver driver) throws Exception{


		int colIndex=headerIndex.get("Linked By");
		String fieldValue=RFA.ReadColValue(driver, colIndex, "RFAExhib.xpath.FilterResultTable");

		EntityScreener.setFilter(driver, "Linked By", "RFA.xpath.ReqStatus", "RFA.xpath.ReqStatusChkbx", "RFA.xpath.ReqStatusText", fieldValue);
		WebUtil.click("RFAExhib.xpath.defocusFilter");
		RFA.VerifyFilterMatches(driver, "RFAExhib.xpath.FilterResultTable", "RFA.xpath.ReqStatus", colIndex,fieldValue);
	}


	public void tc_createDeleteExhibitTemplate(WebDriver driver) {

		RFA r=new RFA();
		String exhbTem=r.createExhibitTemplate(driver);
		headerIndex=r.getColIndexMap(driver, "RFAExhib.xpath.FilterResultTable");
		int colIndex=headerIndex.get("Exhibits Name");
		String ExhibFieldValue=RFA.ReadColValue(driver, colIndex, "RFAExhib.xpath.FilterResultTable");
		ReportUtil.reportStringMatch("Verify Exhibit template created", exhbTem, ExhibFieldValue);
		
		WebUtil.wait(6000);

		try {
			
			tc_01_ExhibNameFilter(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
		r.deleteExhibitTemplate();

		String valText=WebUtil.readElementText(driver, "RFAExhib.xpath.NoResults");
		String expected="No search results found for this query.";
		ReportUtil.reportStringMatch("Verify Exhibit template deleted", expected, valText);

	}

	public void tc_createExhibitTemplateLinkMast(WebDriver driver,String entityIm){
	
		RFA r=new RFA();
		String exhbTem=r.createExhibitTemplate(driver);
		headerIndex=r.getColIndexMap(driver, "RFAExhib.xpath.FilterResultTable");
		int colIndex=headerIndex.get("Exhibits Name");
		String ExhibFieldValue=RFA.ReadColValue(driver, colIndex, "RFAExhib.xpath.FilterResultTable");
		ReportUtil.reportStringMatch("Verify Exhibit template created", exhbTem, ExhibFieldValue);
		
		WebUtil.wait(6000);
		
		r.linkMasterlistExhibit(driver,entityIm);
		WebUtil.click("RFAEx.xpath.BackToDash");
		WebUtil.wait(4000);
		

	}

}
