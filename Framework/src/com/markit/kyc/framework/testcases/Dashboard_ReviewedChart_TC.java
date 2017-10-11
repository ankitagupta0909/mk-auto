package com.markit.kyc.framework.testcases;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;
import com.markit.framework.kyc.pageMethods.Dashboard;
import com.markit.framework.kyc.pageMethods.EntityScreener;
import com.markit.framework.kyc.pageMethods.Navigationbar;

public class Dashboard_ReviewedChart_TC {
	
	
	//Verify Completed section by reading its hover text and verifying its filter on entity screener page
	public void tc_01_verifyCompleted(WebDriver driver) throws Exception{

		WebUtil.waitUntilElementPresent(driver, "Completed.xpath.legend");

		String legendText=Dashboard.readLegends(driver, "Completed.xpath.legend");
		String id=XPath.myprop.get("Reviewed.comp.fillid");
		
		Dashboard.mouseHoverduplicateColorChart(driver, id);
		String toolTipText=Dashboard.mouseHoverReadText(driver, "ReviewedEntities.xpath.toolTip");

		Dashboard.mouseHoverRelease(driver,"hover.xpath.release");
		Dashboard.clickOnduplicateColorGraphSegments(driver, id);	

		ReportUtil.reportStringContainsMatch("Verify legend text exist in hover text", toolTipText, legendText);	

		String dashboardNumber =Dashboard.mouseHoverReadNumber(driver, toolTipText);
		Thread.sleep(10000);
		String entityScreenerNumber=EntityScreener.readCountofMatches(driver, "EntityScreener.xpath.matches");

		ReportUtil.reportStringMatch("Reviewed Entities:Verify Entity Count on Dashboard and Screener", dashboardNumber, entityScreenerNumber);

		Navigationbar.DashboardNav(driver);
		//WebUtil.closeBrowser(driver);
		
		
	}

	
	//Verify Rejected section by reading its hover text and verifying its filter on entity screener page
	public void tc_02_verifyRejected(WebDriver driver) throws Exception{

		WebUtil.waitUntilElementPresent(driver, "Rejected.xpath.legend");
		String legendText=Dashboard.readLegends(driver, "Rejected.xpath.legend");
		String id=XPath.myprop.get("Reviewed.rej.fillid");
		Dashboard.mouseHoverChart(driver,id);
		String toolTipText=Dashboard.mouseHoverReadText(driver, "ReviewedEntities.xpath.toolTip");

		Dashboard.mouseHoverRelease(driver,"hover.xpath.release");
		Dashboard.clickOnGraphSegments(driver, id);	

		ReportUtil.reportStringContainsMatch("Verify legend text exist in hover text", toolTipText, legendText);	

		String dashboardNumber =Dashboard.mouseHoverReadNumber(driver, toolTipText);
		Thread.sleep(15000);
		String entityScreenerNumber=EntityScreener.readCountofMatches(driver, "EntityScreener.xpath.matches");

		ReportUtil.reportStringMatch("Reviewed Entities:Verify Entity Count on Dashboard and Screener", dashboardNumber, entityScreenerNumber);

		WebUtil.closeBrowser(driver);
		

	}
}
