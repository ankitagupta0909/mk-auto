package com.markit.framework.pega.testcases;


import java.util.ArrayList;
import java.util.List;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;
import com.markit.framework.pega.pageMethods.PEGACases;
import com.markit.framework.pega.pageMethods.PEGANavigationBar;
import com.markit.framework.pega.pageMethods.PEGAUnassignedCases;


public class PEGARegTC extends PEGACases {
	PEGANavigationBar nav=new PEGANavigationBar();
	PEGAUnassignedCases unas=new PEGAUnassignedCases();

	public void tc_claimUnassignedcaseId(WebDriver driver,String caseId){
		WebUtil.wait(6000);
		driver=switchDynamicFrames(driver);
		unas.applyFilter(driver, "PEGA.xpath.ClickCaseIdFilter",caseId);
		WebUtil.wait(6000);
		WebUtil.click("PEGA.xpath.ActionsButton");
		WebUtil.click("PEGA.xpath.ClaimButton");
		WebUtil.wait(5000);
	}

	public void tc_caseStart_bycaseId(WebDriver driver,String caseId){
		driver=switchDynamicFrames(driver);

		driver.switchTo().defaultContent();
		nav.navigationTopMenuTeamManager(driver, "Cases");
		driver=switchDynamicFrames(driver);
		unas.applyFilter(driver, "PEGA.xpath.ClickCaseIdFilter",caseId);
		WebUtil.wait(5000);
		WebUtil.click("PEGACases.xpath.Actionsbutton");
		WebUtil.waitUntilElementPresent(driver, "PEGACases.xpath.StartCase");
		WebUtil.click("PEGACases.xpath.StartCase");
		WebUtil.wait(30000);
		driver=switchDynamicFrames(driver);
	}

	
	
	public void tc_uploadDoc(WebDriver driver){
		driver=switchDynamicFrames(driver);
		enterDocUploadData(driver,"src/testdata/WFM_automation_Scenario.xlsx","DocUpload");
	}

	public void tc_enterTestData_Scenario1(WebDriver driver){		
		enterTestData(driver,"src/testdata/WFM_automation_Scenario.xlsx","Scenario_1_BrokerDealer");		
	}

	public void tc_sendCaseToSME(WebDriver driver){		
		driver=switchDynamicFrames(driver);
		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.wait(5000);
		WebUtil.click("PEGACase.xpath.SendToSME");
		WebUtil.wait(5000);
		WebUtil.click("PEGACase.xpath.ConfirmSendToSME");
		WebUtil.wait(8000);
	}


	public void tc_SMEacceptAttributes(WebDriver driver){
		try{	
			acceptAttributesAndTasks_withNavigation(driver, "PEGAAccept.xpath.verifyTaskRaisedSME","PEGAAccept.xpath.Approval");	
		}catch(Exception e){
			e.printStackTrace();

		}	
	}
	public void tc_SMEacceptTask(WebDriver driver){
		try{	
			acceptAttributesAndTasks(driver, "PEGAAccept.xpath.verifyTaskRaisedSME","PEGAAccept.xpath.Approval");	
		}catch(Exception e){
			e.printStackTrace();

		}	
	}

	public void tc_COacceptsTaskRequests(WebDriver driver){
		try{
			acceptTasksRequests_withNavigation(driver, "PEGAAccept.xpath.verifyTaskRaisedCO","PEGAAccept.xpath.Approval");
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public void tc_sendCaseToSMEFromCO(WebDriver driver){		
		driver=switchDynamicFrames(driver);
		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.wait(5000);
		WebUtil.click("PEGACase.xpath.SendToSME");
		WebUtil.wait(5000);
		WebUtil.click("PEGACase.xpath.ConfirmSendToSMEFromCO");
		WebUtil.wait(8000);
	}

	public void tc_sendToKYC(WebDriver driver){	
		driver=switchDynamicFrames(driver);
		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.wait(6000);
		WebUtil.click("PEGACase.xpath.SendToKYC");
		WebUtil.wait(6000);
		WebUtil.click("PEGACase.xpath.ConfirmSendToKYC");
		WebUtil.wait(8000);
	}

	public void tc_sendBackToSMEfromKYC(WebDriver driver){		
		driver=switchDynamicFrames(driver);
		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.wait(5000);
		WebUtil.click("PEGACase.xpath.SendToSME");
		WebUtil.wait(5000);
		WebUtil.click("PEGACase.xpath.ConfirmSendToKYC");
		WebUtil.wait(8000);
	}

	public void tc_SMEQAacceptsFinalTasks(WebDriver driver){
		try{
			acceptAttributes_withNavigation_AcceptHyperLink(driver);
		}
		catch(Exception e){
			e.printStackTrace();		
		}
	}

	public void tc_SMEsendsToQA(WebDriver driver){
		driver=switchDynamicFrames(driver);
		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.wait(3000);
		WebUtil.click("PEGACase.xpath.SendToQA");
		WebUtil.wait(3000);
		WebUtil.click("PEGACase.xpath.ConfirmSendToKYC");
		WebUtil.wait(8000);
	}
	
	public void tc_sendToClient(WebDriver driver){	
		driver=switchDynamicFrames(driver);
		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.wait(3000);
		WebUtil.clickedWithAction(driver, "PEGACase.xpath.SendToClient");
		WebUtil.wait(3000);	
		if(WebUtil.isElementPresent(driver, "PEGACase.xpath.ActionsRequired"))
		{		
			WebUtil.javaExecuter(driver, "PEGACase.xpath.CancelForActionsRequired");
			WebUtil.wait(2000);
			tc_SMEacceptTask(driver);
			WebUtil.click("PEGACase.xpath.SendAction");
			WebUtil.wait(3000);
			WebUtil.clickedWithAction(driver, "PEGACase.xpath.SendToClient");
			WebUtil.wait(3000);
		}
		else
		{
		WebUtil.click("PEGACase.xpath.ConfirmSendToKYC");
		WebUtil.wait(8000);
		}
		
	}


	public void tc_QAsendsToSP(WebDriver driver){
		driver=switchDynamicFrames(driver);
		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.wait(3000);
		WebUtil.click("PEGACase.xpath.SendToSP");
		WebUtil.wait(3000);
		WebUtil.click("PEGACase.xpath.ConfirmSendToKYC");
		WebUtil.wait(6000);

	}
	
	public void tc_caseClose(WebDriver driver,String caseId)
	{
		PEGARegTC pegaTc= new PEGARegTC();
		driver=switchDynamicFrames(driver);
		pegaTc.closeTheCaseAndStartAgain(driver,caseId);
	}
}
