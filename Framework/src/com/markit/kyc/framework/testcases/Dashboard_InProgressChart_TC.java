package com.markit.kyc.framework.testcases;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;
import com.markit.framework.kyc.pageMethods.Dashboard;
import com.markit.framework.kyc.pageMethods.EntityScreener;
import com.markit.framework.kyc.pageMethods.Navigationbar;

public class Dashboard_InProgressChart_TC {

	//Verify Due Diligence section by reading its hover text and verifying its filter on entity screener page
	public void tc_01_verifyDueDilig(WebDriver driver) throws Exception{

		WebUtil.waitUntilElementPresent(driver, "DueDilig.xpath.legend");
		String legendText=Dashboard.readLegends(driver, "DueDilig.xpath.legend");
		String id=XPath.myprop.get("InProg.DD.fillid");
		Dashboard.mouseHoverduplicateColorChart(driver, id);
		String toolTipText=Dashboard.mouseHoverReadText(driver, "EntitiesInProg.xpath.toolTip");

		Dashboard.mouseHoverRelease(driver,"hover.xpath.release");
		Dashboard.clickOnduplicateColorGraphSegments(driver, id);	

		ReportUtil.reportStringContainsMatch("Verify legend text exist in hover text", toolTipText, legendText);	

		String dashboardNumber =Dashboard.mouseHoverReadNumber(driver, toolTipText);
		Thread.sleep(15000);
		String entityScreenerNumber=EntityScreener.readCountofMatches(driver, "EntityScreener.xpath.matches");

		ReportUtil.reportStringMatch("Entities In Progress:Verify Entity Count on Dashboard and Screener", dashboardNumber, entityScreenerNumber);

		Navigationbar.DashboardNav(driver);
		

	}
	
	//Verify Outreach section by reading its hover text and verifying its filter on entity screener page
	public void tc_02_verifyOutreach(WebDriver driver) throws Exception{

		WebUtil.waitUntilElementPresent(driver, "Outreach.xpath.legend");
		String legendText=Dashboard.readLegends(driver, "Outreach.xpath.legend");
		String id=XPath.myprop.get("InProg.Outreach.fillid");
		Dashboard.mouseHoverChart(driver,id);
		String toolTipText=Dashboard.mouseHoverReadText(driver, "EntitiesInProg.xpath.toolTip");

		Dashboard.mouseHoverRelease(driver,"hover.xpath.release");
		Dashboard.clickOnGraphSegments(driver, id);	

		ReportUtil.reportStringContainsMatch("Verify legend text exist in hover text", toolTipText, legendText);	

		String dashboardNumber =Dashboard.mouseHoverReadNumber(driver, toolTipText);
		Thread.sleep(15000);
		String entityScreenerNumber=EntityScreener.readCountofMatches(driver, "EntityScreener.xpath.matches");

		ReportUtil.reportStringMatch("Entities In Progress:Verify Entity Count on Dashboard and Screener", dashboardNumber, entityScreenerNumber);

		Navigationbar.DashboardNav(driver);
		

	}
	
	//Verify QA section by reading its hover text and verifying its filter on entity screener page
	public void tc_03_verifyQA(WebDriver driver) throws Exception{

		WebUtil.waitUntilElementPresent(driver, "QA.xpath.legend");
		String legendText=Dashboard.readLegends(driver, "QA.xpath.legend");
		String id=XPath.myprop.get("InProg.QA.fillid");
		Dashboard.mouseHoverChart(driver,id);
		String toolTipText=Dashboard.mouseHoverReadText(driver, "EntitiesInProg.xpath.toolTip");

		Dashboard.mouseHoverRelease(driver,"hover.xpath.release");
		Dashboard.clickOnGraphSegments(driver, id);	

		ReportUtil.reportStringContainsMatch("Verify legend text exist in hover text", toolTipText, legendText);	

		String dashboardNumber =Dashboard.mouseHoverReadNumber(driver, toolTipText);
		Thread.sleep(15000);
		String entityScreenerNumber=EntityScreener.readCountofMatches(driver, "EntityScreener.xpath.matches");

		ReportUtil.reportStringMatch("Entities In Progress:Verify Entity Count on Dashboard and Screener", dashboardNumber, entityScreenerNumber);

		WebUtil.closeBrowser(driver);
		

	}
}
