package com.markit.kyc.framework.testcases;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;
import com.markit.framework.kyc.pageMethods.Dashboard;
import com.markit.framework.kyc.pageMethods.EntityScreener;
import com.markit.framework.kyc.pageMethods.Navigationbar;

public class Dashboard_AnnualReviewChart_TC {

	//Verify Account opening in progress section by reading its hover text and verifying its filter on entity screener page
	public void tc_01_verifyAccOpeningInProg(WebDriver driver) throws Exception{


		WebUtil.waitUntilElementPresent(driver, "AccOpInProg.xpath.legend");	
		
		String legendText=Dashboard.readLegends(driver, "AccOpInProg.xpath.legend");
		String id=XPath.myprop.get("AnnualReview.accountOpening.fillid");
		Dashboard.mouseHoverChart(driver,id);
		String toolTipText=Dashboard.mouseHoverReadText(driver, "Annualreview.xpath.toolTip");

		Dashboard.mouseHoverRelease(driver,"hover.xpath.release");
		Dashboard.clickOnGraphSegments(driver, id);	

		ReportUtil.reportStringContainsMatch("Verify legend text exist in hover text", toolTipText, legendText);	

		String dashboardNumber =Dashboard.mouseHoverReadNumber(driver, toolTipText);
		
		Thread.sleep(10000);
	
		String entityScreenerNumber=EntityScreener.readCountofMatches(driver, "EntityScreener.xpath.matches");

		ReportUtil.reportStringMatch("Annual Review:Verify Entity Count on Dashboard and Screener", dashboardNumber, entityScreenerNumber);

		Navigationbar.DashboardNav(driver);
		

	}

	//Verify Ready for Review section by reading its hover text and verifying its filter on entity screener page
	public void tc_02_verifyReadyForReview(WebDriver driver) throws Exception{

		//Thread.sleep(20000);
		WebUtil.waitUntilElementPresent(driver, "ReadyforRev.xpath.legend");
		String legendText=Dashboard.readLegends(driver, "ReadyforRev.xpath.legend");
		String id=XPath.myprop.get("AnnualReview.readyforReview.fillid");
		Dashboard.mouseHoverChart(driver,id);
		String toolTipText=Dashboard.mouseHoverReadText(driver, "Annualreview.xpath.toolTip");

		Dashboard.mouseHoverRelease(driver,"hover.xpath.release");
		Dashboard.clickOnGraphSegments(driver, id);	

		ReportUtil.reportStringContainsMatch("Verify legend text exist in hover text", toolTipText, legendText);	

		String dashboardNumber =Dashboard.mouseHoverReadNumber(driver, toolTipText);
		Thread.sleep(20000);
		String entityScreenerNumber=EntityScreener.readCountofMatches(driver, "EntityScreener.xpath.matches");

		ReportUtil.reportStringMatch("Annual Review:Verify Entity Count on Dashboard and Screener", dashboardNumber, entityScreenerNumber);

		Navigationbar.DashboardNav(driver);

	}

	//Verify In progress section by reading its hover text and verifying its filter on entity screener page
	public void tc_03_verifyInProgress(WebDriver driver) throws Exception{

		//Thread.sleep(20000);
		WebUtil.waitUntilElementPresent(driver, "InProg.xpath.legend");
		String legendText=Dashboard.readLegends(driver, "InProg.xpath.legend");
		String id=XPath.myprop.get("AnnualReview.inProgress.fillid");
		Dashboard.mouseHoverChart(driver,id);
		String toolTipText=Dashboard.mouseHoverReadText(driver, "Annualreview.xpath.toolTip");

		Dashboard.mouseHoverRelease(driver,"hover.xpath.release");
		Dashboard.clickOnGraphSegments(driver, id);	

		ReportUtil.reportStringContainsMatch("Verify legend text exist in hover text", toolTipText, legendText);	

		String dashboardNumber =Dashboard.mouseHoverReadNumber(driver, toolTipText);
		Thread.sleep(20000);
		String entityScreenerNumber=EntityScreener.readCountofMatches(driver, "EntityScreener.xpath.matches");

		ReportUtil.reportStringMatch("Annual Review:Verify Entity Count on Dashboard and Screener", dashboardNumber, entityScreenerNumber);

		Navigationbar.DashboardNav(driver);

	}

	//Verify Reviewed section by reading its hover text and verifying its filter on entity screener page
	public void tc_04_verifyReviewed(WebDriver driver) throws Exception{

		//Thread.sleep(20000);
		WebUtil.waitUntilElementPresent(driver, "Reviewed.xpath.legend");
		String legendText=Dashboard.readLegends(driver, "Reviewed.xpath.legend");
		String id=XPath.myprop.get("AnnualReview.reviewed.fillid");
		Dashboard.mouseHoverChart(driver,id);
		String toolTipText=Dashboard.mouseHoverReadText(driver, "Annualreview.xpath.toolTip");

		Dashboard.mouseHoverRelease(driver,"hover.xpath.release");
		Dashboard.clickOnGraphSegments(driver, id);	

		ReportUtil.reportStringContainsMatch("Verify legend text exist in hover text", toolTipText, legendText);	

		String dashboardNumber =Dashboard.mouseHoverReadNumber(driver, toolTipText);
		Thread.sleep(20000);
		String entityScreenerNumber=EntityScreener.readCountofMatches(driver, "EntityScreener.xpath.matches");

		ReportUtil.reportStringMatch("Annual Review:Verify Entity Count on Dashboard and Screener", dashboardNumber, entityScreenerNumber);

		Navigationbar.DashboardNav(driver);

	}
	
	//Verify Pre Account opening section by reading its hover text and verifying its filter on entity screener page
	public void tc_05_verifyPreAccOpening(WebDriver driver) throws Exception{

		//Thread.sleep(20000);
		WebUtil.waitUntilElementPresent(driver, "PreAccOp.xpath.legend");
		String legendText=Dashboard.readLegends(driver, "PreAccOp.xpath.legend");
		String id=XPath.myprop.get("AnnualReview.preAccount.fillid");
		Dashboard.mouseHoverChart(driver,id);
		String toolTipText=Dashboard.mouseHoverReadText(driver, "Annualreview.xpath.toolTip");

		Dashboard.mouseHoverRelease(driver,"hover.xpath.release");
		Dashboard.clickOnGraphSegments(driver, id);	

		ReportUtil.reportStringContainsMatch("Verify legend text exist in hover text", toolTipText, legendText);	

		String dashboardNumber =Dashboard.mouseHoverReadNumber(driver, toolTipText);
		//String dashboardNumber="540";
		Thread.sleep(20000);
		String entityScreenerNumber=EntityScreener.readCountofMatches(driver, "EntityScreener.xpath.matches");

		ReportUtil.reportStringMatch("Annual Review:Verify Entity Count on Dashboard and Screener", dashboardNumber, entityScreenerNumber);

		WebUtil.closeBrowser(driver);
		

	}
}
