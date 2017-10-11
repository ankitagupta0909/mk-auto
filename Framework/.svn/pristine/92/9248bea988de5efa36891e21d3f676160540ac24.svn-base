package com.markit.mcpm.framework.testcases;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.WebUtil;
import com.markit.framework.kyc.pageMethods.EntityScreener;
import com.markit.framework.mcpm.pageMethods.RFA;

public class RFAMasterlistTC {

	public static Map<String,Integer> headerIndex=new HashMap<String,Integer>();
	
	public void init(WebDriver driver) throws InterruptedException{
		RFA masterlist=new RFA();
		masterlist.navigateMasterlist(driver);
		headerIndex=masterlist.getColIndexMap(driver, "RFAMast.xpath.FilterResultTable");
	}
	
	public  void tc_01_RefDateFilter(WebDriver driver) throws Exception{
 
    int colIndex=headerIndex.get("Reference Master Agreement Date");
	String FieldValue=RFA.ReadColValue(driver, colIndex, "RFAMast.xpath.FilterResultTable");

    EntityScreener.setFilter(driver, "Reference Master Agreement Date", "RFA.xpath.Refdate", "RFA.xpath.RefdateChkbx", "RFA.xpath.RefdateText", FieldValue);
    WebUtil.click("RFAMast.xpath.defocusFilter");

    RFA.VerifyFilterMatches(driver, "RFAMast.xpath.FilterResultTable", "RFA.xpath.Refdate", colIndex, FieldValue);
	
	}

  public void tc_02_MastIdFilter(WebDriver driver) throws Exception{

	    int colIndex=headerIndex.get("Masterlist Identifier");
		String FieldValue=RFA.ReadColValue(driver, colIndex, "RFAMast.xpath.FilterResultTable");
	    EntityScreener.setFilter(driver, "Masterlist Identifier", "RFA.xpath.MasId", "RFA.xpath.MasIdChkbx", "RFA.xpath.MasIdText", FieldValue);
	    WebUtil.click("RFAMast.xpath.defocusFilter");

	    RFA.VerifyFilterMatches(driver, "RFAMast.xpath.FilterResultTable", "RFA.xpath.MasId", colIndex, FieldValue);
	   
		}
  
    
  public void tc_03_InvManagerFilter(WebDriver driver) throws Exception{

	    int colIndex=headerIndex.get("Investment Manager");
		String FieldValue=RFA.ReadColValue(driver, colIndex, "RFAMast.xpath.FilterResultTable");
	
	    EntityScreener.setFilter(driver, "Investment Manager", "RFA.xpath.PartyA", "RFA.xpath.PartyAChkbx", "RFA.xpath.PartyAText", FieldValue);
	    WebUtil.click("RFAMast.xpath.defocusFilter");

	    RFA.VerifyFilterMatches(driver, "RFAMast.xpath.FilterResultTable", "RFA.xpath.PartyA", colIndex, FieldValue);
	   
		}
  
  public void tc_04_PartyAFilter(WebDriver driver) throws Exception{

	    int colIndex=headerIndex.get("Party A");
		String FieldValue=RFA.ReadColValue(driver, colIndex, "RFAMast.xpath.FilterResultTable");
	
	    EntityScreener.setFilter(driver, "Party A", "RFAEx.xpath.RefDate", "RFAEx.xpath.RefDateChkbx", "RFAEx.xpath.RefDateText", FieldValue);
	    WebUtil.click("RFAMast.xpath.defocusFilter");

	    RFA.VerifyFilterMatches(driver, "RFAMast.xpath.FilterResultTable", "RFAEx.xpath.RefDate", colIndex, FieldValue);
	   
		}
  
  public void tc_05_VerifyMasterListContent(WebDriver driver)
  {
	  
	  String currentUser = System.getProperty("user.name");
	  String path="C:/Users/"+currentUser+"/Downloads";
	  WebUtil.deleteFilesFromAFloder(path);
	  RFA rfa=new RFA();
	  String expectedValue=null;
	  expectedValue=RFAHomePageTC.entityAdd;
	  rfa.downloadMasterList(driver);
	  rfa.verify_MasterList("Active", expectedValue,"Party B Account True Legal Name");
	  WebUtil.wait(2000);
	  expectedValue=RFAHomePageTC.entityDelete;
	  rfa.verify_MasterList("Removed", expectedValue,"Party B Account True Legal Name");
	  WebUtil.wait(2000);
	  expectedValue=RFAHomePageTC.entityModify;
	  rfa.verify_MasterList("Modified", expectedValue,"Current Party B Account True Legal Name");
	  
  }
}
