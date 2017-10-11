package com.markit.framework.mcpm.pageMethods;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.markit.common.framework.util.DBUtility;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;

public class CompanyActionsPage {
	
	public void navigateBackToDashBoard(WebDriver driver)
	{
		WebUtil.clickedWithAction(driver,"KYCDashBoard.xpath.BackToDashboardLink");
	}
	
	public class ContactsTab{

		public void navigateToContactsTab(WebDriver driver)
		{

			WebUtil.clickedWithAction(driver, "KYCDashBoard.xpath.Contacts");
			WebUtil.wait(3000);

		}

		public boolean verifyIfContactListIsEmpty(WebDriver driver)
		{
			int size=WebUtil.getSizeOfElement(driver, "KYCDashBoard.xpath.ContactsListEmpty");
			String text=null;
			boolean status=false; 
			if(size!=0)
			{
				text=WebUtil.readElementText(driver, "KYCDashBoard.xpath.ContactsListEmpty");
				ReportUtil.reportStringMatch("Verify the content dispay in contact list", "No contacts available, please add a contact using the Add Contact button below.", text);
				status=true;
			}
			return status;
		}
		
		public void deleteContactsFromCompayLevel(WebDriver driver)
		{
			boolean status=verifyIfContactListIsEmpty(driver);
			List<WebElement> ele=new ArrayList<WebElement>();
			WebElement close=null;
			if(status!=true)
			{
			 ele=WebUtil.findElements("AddContacts.xpath.DeleteContacts", driver);
			for(int i=1;i<=ele.size();i++)
			{
				
				close=driver.findElement(By.xpath("//tr[@ng-repeat='contact in contacts.contacts']["+i+"]//a[2]"));
				WebUtil.actionClass(close, driver);
				WebUtil.clickedWithAction(driver, "AddContacts.xpath.Confirm");
				WebUtil.wait(2000);
			}
			WebUtil.clickedWithAction(driver, "AddContacts.xpath.Submit");
			WebUtil.wait(2000);
			WebUtil.clickedWithAction(driver, "AddContacts.xpath.Confirm");
			WebUtil.wait(2000);
			WebUtil.clickedWithAction(driver, "AddContacts.xpath.Dismiss");
			WebUtil.wait(2000);
			WebUtil.clickedWithAction(driver,"KYCDashBoard.xpath.BackToDashboardLink");
			}
			else
			{
				WebUtil.clickedWithAction(driver,"KYCDashBoard.xpath.BackToDashboardLink");
				WebUtil.wait(4000);
			}
		}
	

		public void addContacts(WebDriver driver)
		{
			WebUtil.clickedWithAction(driver, "AddContacts.xpath.AddContactButton");
			int random=WebUtil.generateRandomNumber();
			String contactRole="Text Role"+"-"+random;
			String firstName="First Name Test"+"-"+random;
			String lastName="Last Name Test"+"-"+random;
			String phone="472364"+random;
			String email="comapnyAction"+random+"@markit.com";
			WebUtil.sendKeysWithAction(driver, "AddContacts.xpath.AddContactRole", contactRole);
			WebUtil.sendKeysWithAction(driver,"AddContacts.xpath.FirstName", firstName);
			WebUtil.sendKeysWithAction(driver,"AddContacts.xpath.LastName", lastName);
			WebUtil.sendKeysWithAction(driver,"AddContacts.xpath.Phone", phone);
			WebUtil.sendKeysWithAction(driver,"AddContacts.xpath.Email", email);
			WebUtil.clickedWithAction(driver, "AddContacts.xpath.Save");
			WebUtil.wait(3000);
			WebUtil.clickedWithAction(driver, "AddContacts.xpath.Submit");
			WebUtil.wait(2000);
			WebUtil.clickedWithAction(driver, "AddContacts.xpath.Confirm");
			WebUtil.wait(5000);
			WebUtil.clickedWithAction(driver, "AddContacts.xpath.Dismiss");
			WebUtil.wait(5000);
			navigateBackToDashBoard(driver);
		}
	}
	
	public class RequesteSignRole
	{	

		public void verify_RequesteSignRole(WebDriver driver)
		{
			WebUtil.clickedWithAction(driver, "KYCDashBoard.xpath.PreKYCRequestESignText");
			WebUtil.wait(2000);
			String text1=WebUtil.readElementText(driver, "RequestESignRole.xpath.PopText1");
			String text2=WebUtil.readElementText(driver, "RequestESignRole.xpath.PopText2");
			String text3=WebUtil.readElementText(driver, "RequestESignRole.xpath.PopText3");
			String text=text1+" " +text2+" "+text3;
			ReportUtil.reportStringMatch("Verify RequesteSignRole pop content when e-sign user is avilable", "You are not currently authorized to complete Internal Confirmation tasks. Please email support@kyc.com to request eSign privileges be added to your account.", text);
			WebUtil.clickedWithAction(driver, "RequestESignRole.xpath.Dismiss");
			WebUtil.wait(3000);
						
		}
		
		public boolean db_ValidatationForE_SignRole(Connection con,String userName)
		{
			
			List<String> role=new ArrayList<String>();
		    //List<String> uId=new ArrayList<String>();
		    List<String> userId= new ArrayList<String>();
			boolean status=false;
			//String value=null;
			if(con!=null)
			{
			String companyId=DBUtility.getColData(con,"select companyid from mc_users where username='"+userName+"' and deleted=0","companyid");
			 userId=DBUtility.getColDataAllRows(con,"select  DISTINCT(u.uid) from mc_users u inner join mc_companies c on (c.companyid=u.companyid) where c.companyid='"+companyId+"' and u.deleted=0","uid");
			
			/*for(int i=0;i<userId.size()-1;i++)
			{
				value=userId.get(i)+",";				
				uId.add(value);
				System.out.println(value);		
			
			}
			int last=uId.size();*/
			
			/*uId.add(userId.get(last-1));*/
			role=DBUtility.getColDataAllRows(con,"select r.* from mc_user_roles u inner join  mc_roles r on (r.id=u.rid and r.deleted=0) where u.uid in ("+userId.toString().replace("[", "").replace("]", "")+") and u.deleted=0 and r.name='mcpm.kyc.esign'","name");
			}
		
			if(role.size()!=0)
			{
				status=true;
			}
			
			return status;
		}
	}
	public class WolfsbergAML
	{
		public void verify_filling_Wolfsberg_Questionnaire(WebDriver driver)
		{
			String text=null;
			/*WebUtil.clickedWithAction(driver, "KYCDashBoard.xpath.PreKYCWolfsbergText");
			WebUtil.wait(4000);*/
			text=WebUtil.readElementText(driver,"Wolfsberg.xpath.HeaderofthePage");
			ReportUtil.reportStringMatch("Verify the Wolfsberg AML page is loaded ", "The Wolfsberg Group Anti-Money Laundering Questionnaire", text);
			WebUtil.setWebEdit("Wolfsberg.xpath.FinancialInstitutionInput", "ABC1");
			WebUtil.setWebEdit("Wolfsberg.xpath.LocationInput", "ABC2");
			WebUtil.clickedWithAction(driver, "Wolfsberg.xpath.YesToAll");
			WebUtil.ifElementIsEnabledThenClick(driver,"Wolfsberg.xpath.Confirm");	
			//WebUtil.clickedWithAction(driver, "MCPMCIA.xpath.SuccessfullyPopUpCancel");
			WebUtil.wait(9000);
			
			
		}
		
		public void cliclkAtWolfsbergQuestionnaire(WebDriver driver)
		{
			WebUtil.click("MCPMRegression.xpath.WolfsbergLink");
			WebUtil.wait(3000);
		}
	}
	
	public class CountriesIndustriesAssessment
	{
		public void fill_detailsofCountriesIndustriesAssessment(WebDriver driver)
		{
		/*	WebUtil.clickedWithAction(driver, "KYCDashBoard.xpath.PreKYCCountriesIndustriesText");	
			WebUtil.wait(8000);*/
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
			WebUtil.wait(9000);
			
			
		}
		
		public void clickAtCIALink()
		{
			WebUtil.click("MCPMRegression.xpath.CIALink");
			WebUtil.wait(5000);
		}
		
		public void verifyTheNonFundEntitiesOnCIATab(WebDriver driver,String entityName)
		{
			WebUtil.setWebEdit("MCPMCIA.xpath.SearchEntity", entityName);
			int size=WebUtil.getSizeOfElement(driver, "MCPMCIA.xpath.EntitiesName");
			if(size!=0)
			{
				ReportUtil.reportStringMatch("Entity Name Found under CIA", entityName, WebUtil.readElementText(driver, "MCPMCIA.xpath.EntitiesName"));
				
			}
			else
			{
				ReportUtil.reportWebElement("No match found", false);
			}
			
		}
		
		public void excludeEntity(WebDriver driver,String EntityName)
		{
			WebUtil.setWebEdit("MCPMCIA.xpath.SearchEntity", EntityName);
			WebUtil.clickedWithAction(driver, "MCPMCIA.xpath.EntitiesName");
			ReportUtil.reportWebElement("Entity is excluded", true);
			WebUtil.clickedWithAction(driver, "MCPMCIA.xpath.ClearSearch");
		}
		
		public void verifyEntityToExculdeList(WebDriver driver)
		{
			System.out.println("Test List");
			List<WebElement> entitynames=new ArrayList<WebElement>();
			entitynames=WebUtil.findElements("KYCCIA.xpath.ExcludeList", driver);
			System.out.println(entitynames.size());
			
			for(int i=0;i<entitynames.size();i++)
			{
				System.out.println(entitynames.get(i).getText());
			}
			
		}
	}
	
	public class Documents
	{
		public void uploadDocuments(WebDriver driver)
		{
			WebUtil.clickedWithAction(driver, "MCPMCADocument.xpath.Upload");
			WebUtil.selectValueFromDropDownAsPerPassedIndex(driver, "MCPMCADocument.xpath.SelectDocType", 3);
			WebUtil.clickedWithAction(driver, "MCPMCADocument.xpath.Browse");
			WebUtil.uploadDocumentAsperPassedBrowserName(driver, "Chrome");
			WebUtil.wait(5000);
			WebUtil.clickedWithAction(driver, "MCPMCADocument.xpath.Save");
			WebUtil.wait(3000);
			WebUtil.clickedWithAction(driver, "MCPMCIA.xpath.SuccessfullyPopUpCancel");
			WebUtil.wait(3000);
			navigateBackToDashBoard(driver);
		}
		public void clickAtDocumentsLink()
		{
			WebUtil.click("MCPMRegression.xpath.Documents");
			WebUtil.wait(3000);
		}
	}
}
