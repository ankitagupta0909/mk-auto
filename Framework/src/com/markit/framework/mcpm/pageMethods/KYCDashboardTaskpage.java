package com.markit.framework.mcpm.pageMethods;

import java.awt.AWTException;
import java.awt.Robot;


import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.apache.poi.util.Internal;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;

public class KYCDashboardTaskpage {
	
	public class ALMQuestionnaireTaskpage {
		
		public void fillAnti_Money_Laundering_Questionnaire(WebDriver driver,String financialInstititionName,String locationName) throws InterruptedException
		{
			String value=null;
			boolean actVal;
			WebElement financial=WebUtil.findElement("KYCDashboard.xpath.FinancialInstitutionName", driver);
			financial.sendKeys(financialInstititionName);
			value=WebUtil.readWebElement("WebEdit",financial);
			ReportUtil.reportWebEditRead("Entered Financial Ins. Name",financial, financialInstititionName, value);
			WebElement location=WebUtil.findElement("KYCDashboard.xpath.LocationName", driver);
			location.sendKeys(locationName);
			value=WebUtil.readWebElement("WebEdit",location);
			ReportUtil.reportWebEditRead("Entered Financial Ins. Name",location, locationName, value);
			WebElement selectAll=WebUtil.findElement("KYCDashboard.xpath.YesToAll", driver);
			actVal=WebUtil.verifyWebElementExists("WebLink",selectAll);
			selectAll.click();		
			Thread.sleep(6000);
			ReportUtil.reportWebElement("Select All is clicked",actVal);
			WebElement confirm=WebUtil.findElement("KYCDashboard.xpath.Confirm", driver);
			actVal=WebUtil.verifyWebElementExists("WebLink",confirm);
			WebUtil.actionClass(confirm, driver);

			//WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.Confirm");
			//confirm.click();

			ReportUtil.reportWebElement("Confirm button is  clicked",actVal);
			Thread.sleep(8000);
			
		}
		

	}
	
	public class DocumentRequestTaskpage {
		
		public void fillDocumentRequestInfo(WebDriver driver) throws InterruptedException, IOException
		{
			/*WebUtil.clickedWithAction(driver, "KYCdashboard.xpath.ShareRegister");
			WebUtil.clickedWithAction(driver, "KYCdashboard.xpath.Certified");*/
			boolean actVal;
			//WebUtil.setFrame(driver, "MFrame");
			WebElement browse= WebUtil.findElement("KYCdashboard.xpath.BrowseDocRequest", driver);
			actVal=WebUtil.verifyWebElementExists("WebLink",browse);
			WebUtil.clickedWithAction(driver, "KYCdashboard.xpath.BrowseDocRequest");
			ReportUtil.reportWebElement("Clicked on Browse button",actVal);
			Thread.sleep(2000);		
			WebUtil.uploadDocumentAsperPassedBrowserName(driver, "chrome");
			Thread.sleep(2000);
			ReportUtil.reportStringMatch("Document is uploaded", "", "");
			WebUtil.clickedWithAction(driver, "KYCdashboard.xpath.Submit");
			Thread.sleep(5000);
			ReportUtil.reportStringMatch("Submit is clicked", "", "");
			try
			{
			boolean value=false;
			value=WebUtil.isElementPresent(driver, "KYCdashboard.xpath.CloseDocRequest");
			if(value)
			{
			WebUtil.clickedWithAction(driver, "KYCdashboard.xpath.CloseDocRequest");
			Thread.sleep(2000);
			}		
			ReportUtil.reportStringMatch("Closed is clicked", "", "");			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
			
		}
		

	}
	
	public class OutreachQuestionnaireTaskPage {
		
		public void outReachQuestionnaireFill(WebDriver driver) throws InterruptedException
		{
			System.out.println("In the method");
			
			String value=WebUtil.selectValueFromDropDownAsPerPassedIndex(driver, "KYCDashboard.xpath.EntityLeagalForm", 2);
			WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.Defous");
			ReportUtil.reportStringMatch("Selected Value in ENTITY'S LEGAL FORM", value, value);
		}
		public void submit(WebDriver driver) throws InterruptedException
		{
			boolean actVal;
			
			WebElement submit=WebUtil.findElement("KYCDashboard.xpath.Submit", driver);
			actVal=WebUtil.verifyWebElementExists("WebLink",submit);
			WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.Submit");
			System.out.println("Clicked on submit");
			ReportUtil.reportWebElement("Submit Button is clicked",actVal);
			Thread.sleep(10000);
			System.out.println("timeout");
		}

	}
	public class InternalConfirmationTaskpage {
		
		public void scrollDown(WebDriver driver) 
		{
			boolean actVal;
			NavigationPage.setMcpmFrame(driver);
			//WebUtil.setFrame(driver, "MFrame");
			//WebElement scroll = driver.findElement(By.xpath("//div[@class='scroll-element scroll-y scroll-scrolly_visible']//div[3]"));
			WebUtil.wait(4000);
			WebElement scroll=WebUtil.findElement("KYCDashboard.xpath.Scrollbar", driver);
			actVal=WebUtil.verifyWebElementExists("WebLink",scroll);
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			((JavascriptExecutor)driver).executeScript("scroll(0,600)");
			js.executeScript("arguments[0].scrollIntoView(true);",scroll);
			scroll.click();	
			WebUtil.scrollDown(driver, "KYCDashboard.xpath.Scrollbar");
			ReportUtil.reportWebElement("Page is scrolled down",actVal);
			
			//WebUtil.wait(2000);	
		}
		
		public void fillInternalConfirmationForm(WebDriver driver,String nameOfCertifier) 
		{
			boolean actVal;	
			
			WebElement accept=WebUtil.findElement("KYCDashboard.xpath.Accept", driver);
			actVal=WebUtil.verifyWebElementExists("WebLink",accept);
			accept.click();
			ReportUtil.reportWebElement("Accept is clicked",actVal);
			WebElement nameofCertifier=WebUtil.findElement("KYCDashboard.xpath.NameofCertifier", driver);
			nameofCertifier.sendKeys(nameOfCertifier);
			String actualName=WebUtil.readWebElement("WebEdit",nameofCertifier);
			ReportUtil.reportWebEditRead("Entered Certifier Name",nameofCertifier, nameOfCertifier, actualName);
			
			String value=WebUtil.selectValueFromDropDownAsPerPassedIndex(driver, "KYCDashboard.xpath.Role", 2);
			ReportUtil.reportStringMatch("Selected Role form TITLE / ROLE drop down", value, value);
			WebElement calender=WebUtil.findElement("KYCDashboard.xpath.Calander", driver);
			actVal=WebUtil.verifyWebElementExists("WebLink",calender);
			calender.click();
			ReportUtil.reportWebElement("Calander is clicked",actVal);
			WebElement confirm=WebUtil.findElement("KYCDashboard.xpath.Confirm", driver);
			actVal=WebUtil.verifyWebElementExists("WebLink",confirm);
			confirm.click();
			ReportUtil.reportWebElement("Confirm button is clicked",actVal);
			WebUtil.wait(5000);		
			WebElement popup=WebUtil.findElement("KYCDashboard.xpath.Popup", driver);
			actVal=WebUtil.verifyWebElementExists("WebLink",popup);
			popup.click();
			ReportUtil.reportWebElement("Pop Up is closed",actVal);
			WebUtil.wait(8000);
			
		}	
		public void clickAtInternalConfirmationNTask(WebDriver driver)
		{
			WebUtil.clickedWithAction(driver, "KYCRegression.xpath.InternalConfirmationLink");
			WebUtil.wait(5000);
		}
		
		public void bulkInternalConfimtion(WebDriver driver)
		{
			WebUtil.clickedWithAction(driver, "KYCRegression.xpath.ConfirmAllBIC");
			WebUtil.clickedWithAction(driver, "KYCRegression.xpath.ContinueBIC");
			WebUtil.wait(5000);
			fillInternalConfirmationForm(driver, "Monika");
		}
		

	}
	
	public class OwnershipStructureRequestTakspage
	{
		
		public void fillIndiviualOSR(WebDriver driver,List<String> expectedValues) throws InterruptedException{
			

			WebElement element=null;
			String value=null;
			WebUtil.clickedWithAction(driver,"KYCTaskOSR.xpath.AddBO");
			WebUtil.selectValueFromDropDownAsPerPassedIndex(driver, "KYCTaskOSR.xpath.SelectType", 2);			
			WebUtil.waitUntilElementPresent(driver, "KYCTaskOSR.xpath.FirstName");
			WebElement firstName= WebUtil.findElement("KYCTaskOSR.xpath.FirstName", driver);
			int random=WebUtil.generateRandomNumber();
			String name=expectedValues.get(7)+random;
			WebUtil.setWebEdit(firstName, name);
			
			WebElement lastName= WebUtil.findElement("KYCTaskOSR.xpath.LastName", driver);
			WebUtil.setWebEdit(lastName, expectedValues.get(8));
			
			//WebUtil.clickedWithAction(driver,"KYCTaskOSR.xpath.SelectEntityNominee");
			value=WebUtil.selectValueFromDropDownAsPerPassedIndex(driver, "KYCTaskOSR.xpath.NIEntityNominee", 1);
			ReportUtil.reportStringMatch("Selected Value in Is this entity a nominee", value, value);
		
			/*WebUtil.clickedWithAction(driver, "KYCTaskOSR.xpath.ClickCountry");
			WebUtil.clickedWithAction(driver, "KYCTaskOSR.xpath.CountryInputBox");
			element=WebUtil.findElement("KYCTaskOSR.xpath.CountryInputBox", driver);
			element.sendKeys("Afghanistan");
			WebUtil.clickedWithAction(driver, "KYCTaskOSR.xpath.SelectCountry");
			WebUtil.clickedWithAction(driver,"KYCTaskOSR.xpath.defocusCountry");*/
			
			WebUtil.clickedWithAction(driver, "KYCTaskOSR.xpath.ClickCountry");
			WebUtil.clickedWithAction(driver, "KYCTaskOSR.xpath.CountryInputBox");
			element=WebUtil.findElement("KYCTaskOSR.xpath.CountryInputBox", driver);

			element.sendKeys("Afghanistan");
			System.out.println("Start");
			Thread.sleep(2000);
			WebUtil.clickedWithAction(driver, "KYCTaskOSR.xpath.IndiSelectCountry");		;
			WebUtil.clickedWithAction(driver,"KYCTaskOSR.xpath.defocusCountry");

		/*	element.sendKeys("Afghanistan");
			WebUtil.clickedWithAction(driver,"KYCTaskOSR.xpath.SelectCountry");
			WebUtil.click("KYCTaskOSR.xpath.defocusCountry");*/

			
			WebElement sourceWealth= WebUtil.findElement("KYCTaskOSR.xpath.SourceWealth", driver);
			WebUtil.setWebEdit(sourceWealth, expectedValues.get(9));
			
			WebElement ownershipPercent= WebUtil.findElement("KYCTaskOSR.xpath.OwnershipPercentage", driver);
			WebUtil.setWebEdit(ownershipPercent, expectedValues.get(10));

			WebUtil.clickedWithAction(driver,"KYCTaskOSR.xpath.IndiSubmit");
			
			
		}
		
		public void fillNon_IndiviualOSR(WebDriver driver,List<String> expectedValue) throws InterruptedException
		{
			WebElement element =null;
			String value=null;
			WebUtil.clickedWithAction(driver, "KYCTaskOSR.xpath.AddBO");
			Thread.sleep(2000);
			//WebUtil.waitUntilElementPresent(driver, "KYCTaskOSR.xpath.SelectNonIndividualType");
			WebUtil.selectValueFromDropDownAsPerPassedIndex(driver, "KYCTaskOSR.xpath.SelectType", 1);
			int random=WebUtil.generateRandomNumber();
			String name=expectedValue.get(0) + random;
			element=WebUtil.findElement("KYCTaskOSR.xpath.NILegalName", driver);
			WebUtil.setWebEdit(element,name);
			value=WebUtil.readWebElement("WebEdit",element);
			
			ReportUtil.reportWebEditRead("Entered legal name",element,name, value);			
			value=WebUtil.selectValueFromDropDownAsPerPassedIndex(driver, "KYCTaskOSR.xpath.NIPublicExchange", 1);
			ReportUtil.reportStringMatch("Selected Value in PublicExchance", value, value);
			element=WebUtil.findElement("KYCTaskOSR.xpath.NIPercentageOwnership", driver);
			WebUtil.setWebEdit(element,expectedValue.get(1));
			value=WebUtil.readWebElement("WebEdit",element);
			ReportUtil.reportWebEditRead("Entered Percentage ownership ",element, expectedValue.get(1), value);
			element=WebUtil.findElement("KYCTaskOSR.xpath.NIPrimaryLineofBusiness", driver);
			WebUtil.setWebEdit(element,expectedValue.get(2));
			value=WebUtil.readWebElement("WebEdit",element);
			ReportUtil.reportWebEditRead("Entered Primary Line of Business ",element, expectedValue.get(2), value);
			element=WebUtil.findElement("KYCTaskOSR.xpath.NILegalForm", driver);
			WebUtil.setWebEdit(element,expectedValue.get(3));
			value=WebUtil.readWebElement("WebEdit",element);
			ReportUtil.reportWebEditRead("Entered Legal Form",element, expectedValue.get(3), value);
			value=WebUtil.selectValueFromDropDownAsPerPassedIndex(driver, "KYCTaskOSR.xpath.NIEntityNominee", 1);
			ReportUtil.reportStringMatch("Selected Value in Is this entity a nominee", value, value);
			
			WebUtil.clickedWithAction(driver, "KYCTaskOSR.xpath.ClickCountry");
			WebUtil.clickedWithAction(driver, "KYCTaskOSR.xpath.CountryInputBox");
			element=WebUtil.findElement("KYCTaskOSR.xpath.CountryInputBox", driver);
			element.sendKeys("Afghanistan");
			WebUtil.clickedWithAction(driver,"KYCTaskOSR.xpath.IndiSelectCountry");
			WebUtil.clickedWithAction(driver,"KYCTaskOSR.xpath.defocusCountry");
			//WebUtil.click("KYCTaskOSR.xpath.defocusCountry");
			
			WebUtil.wait(5000);
			element=WebUtil.findElement("KYCTaskOSR.xpath.NIAddressLine1", driver);
			WebUtil.setWebEdit(element,expectedValue.get(4));
			value=WebUtil.readWebElement("WebEdit",element);
			ReportUtil.reportWebEditRead("Entered Address Line",element, expectedValue.get(4), value);
			
			element=WebUtil.findElement("KYCTaskOSR.xpath.NICity", driver);
			WebUtil.setWebEdit(element,expectedValue.get(5));
			value=WebUtil.readWebElement("WebEdit",element);
			ReportUtil.reportWebEditRead("Entered City",element, expectedValue.get(5), value);
			value=WebUtil.selectValueFromDropDownAsPerPassedIndex(driver, "KYCTaskOSR.xpath.NIState", 1);
			ReportUtil.reportStringMatch("Selected Value in State", value, value);
			
			element=WebUtil.findElement("KYCTaskOSR.xpath.NIZip", driver);
			WebUtil.setWebEdit(element,expectedValue.get(6));
			value=WebUtil.readWebElement("WebEdit",element);
			ReportUtil.reportWebEditRead("Entered Zipi",element, expectedValue.get(6), value);
			WebUtil.clickedWithAction(driver, "KYCTaskOSR.xpath.Submit");
			Thread.sleep(5000);
		
			WebUtil.clickedWithAction(driver,"KYCTaskOSR.xpath.SubmitFinal");
			Thread.sleep(5000);

		}

		
	}
	
	public class CountriesIndustriesAssessment
	{
		public void fill_CountriesIndustriesAssessment_details(WebDriver driver)
		{		
			try{
					
			WebUtil.clickedWithAction(driver, "CountriesIndustries.xpath.Area");
			WebUtil.setWebEdit("CountriesIndustries.xpath.Activity", "ABC1");
			//WebUtil.sendKeysWithAction(driver, "CountriesIndustries.xpath.Activity", "ABC1");
			WebUtil.clickedWithAction(driver, "CountriesIndustries.xpath.EuropeCheckbox");
			WebUtil.clickedWithAction(driver, "CountriesIndustries.xpath.Jurisdiction");
			WebUtil.clickedWithAction(driver, "CountriesIndustries.xpath.EconomicSanctions");
			WebUtil.selectValueFromDropDownAsPerPassedIndex(driver, "CountriesIndustries.xpath.NumberOfEmp", 1);
			WebUtil.sendKeysWithAction(driver,"CountriesIndustries.xpath.Authorisation", "ABC2");
			WebUtil.sendKeysWithAction(driver,"CountriesIndustries.xpath.Cuba1", "ABC3");
			WebUtil.sendKeysWithAction(driver,"CountriesIndustries.xpath.Cuba2", "ABC4");
			WebUtil.sendKeysWithAction(driver,"CountriesIndustries.xpath.Cuba3", "ABC5");
			WebUtil.sendKeysWithAction(driver,"CountriesIndustries.xpath.Cuba4", "ABC6");			
			WebUtil.clickedWithAction(driver, "CountriesIndustries.xpath.Submit");
			WebUtil.wait(2000);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
		}
		
		public void checkOptionFromCountriesIndustriesAssessmentPopUp(WebDriver driver)
		{
			int size=WebUtil.getSizeOfElement(driver, "CountriesIndustries.xpath.PopUp");
			
			if(size!=0)
			{
				try
				{
					WebUtil.click("CountriesIndustries.xpath.PopUpCompleteNew");
					WebUtil.click("CountriesIndustries.xpath.PopUpSubmit");
					WebUtil.wait(14000);
				}
				
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	public class PermissionRequest
	{
		public void bulkPermissionrequest(WebDriver driver)
		{
			WebUtil.clickedWithAction(driver, "KYCRegression.xpath.ConfirmAllBP");
			WebUtil.clickedWithAction(driver, "KYCRegression.xpath.AllowBP");
			WebUtil.clickedWithAction(driver, "KYCRegression.xpath.ConfirmBP");
			
		}
		
		public void verifyPermissionTaskCountAfterBP(WebDriver driver)
		{
			WebUtil.refreshURL(driver);
			WebUtil.wait(6000);
			String text=WebUtil.readElementText(driver, "KYCRegression.xpath.PermissionRequestCount");
			ReportUtil.reportStringMatch("Verify Bulk Permission Request Count", "0", text);
		}
	}
	

	
}
