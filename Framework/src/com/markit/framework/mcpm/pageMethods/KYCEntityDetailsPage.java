package com.markit.framework.mcpm.pageMethods;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.markit.common.framework.util.DBConnection;
import com.markit.common.framework.util.DBUtility;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.mcpm.framework.testcases.KYCDashboardPageTC;

public class KYCEntityDetailsPage
{
	Connection con=DBConnection.dbConnections.get("KYC");
	
	public static void backtoKYCDashboard()
	{
		WebUtil.click("KYCDashBoard.xpath.BackToDashboardLink");
		WebUtil.wait(4000);
	}

	
	public class CommonDetails
	{
		
		public void validateEditedValuesFromKYC(WebDriver driver,String expectedEntType,String expectedCountry)
		{
			String actualValue=WebUtil.readElementText(driver, "KYCRegression.xpath.EntityTypeText");
			ReportUtil.reportStringMatch("Verify Edited Entity Type ", expectedEntType, actualValue);
			actualValue=WebUtil.readElementText(driver, "KYCRegression.xpath.CountryText");
			ReportUtil.reportStringMatch("Verify Edited Entity Country ", expectedCountry, actualValue);
		}
		
		public void verifyTheOpenTask(WebDriver driver,String expectedTaskValue)
		{
			String value=WebUtil.readElementText(driver,"KYCRegression.xpath.OpenTaskOnEntityPage");
			ReportUtil.reportStringMatch("Verify Edited Entity Type ", expectedTaskValue, value);
		}
		public void verifyListofPreKYCSteps(WebDriver driver)
		{
			List<WebElement> ele=new ArrayList<WebElement>();
			String text=null;
			ele=WebUtil.findElements("KYCRegression.xpath.PreKYCStepsList", driver);
			if(ele.size()!=0)
			{
				for(int i=0;i<ele.size();i++)
				{
					text=ele.get(i).getText().toString();
					ReportUtil.reportInputValueOnly("Verify Pre-KYC Steps", text, true);
					/*text=ele.get(i).findElement(By.xpath("//a[@class='title']")).getText();
					ReportUtil.reportInputValueOnly("Verify Pre-KYC Steps", text, true);*/
					
				}
				for(int i=1;i<=ele.size();i++)
				{
					text=driver.findElement(By.xpath("//div[@class='kyc-status ng-scope']//div[contains(@class,'status')]["+i+"]//span[contains(@class,'badge badge')]")).getAttribute("class");
					System.out.println(text);
					if(text.equalsIgnoreCase("badge badge-rejected ng-scope"))
					{
						ReportUtil.reportStringContainsMatch("Verify Pre-KYC Steps Graph Color", "Red", "Red");
					}
					else
					{
						ReportUtil.reportStringContainsMatch("Verify Pre-KYC Steps Graph Color", "Blue", "Blue");
					}
				}
			}
		}
		public void verifyPre_KYC_Steps(WebDriver driver)
		{
			
			String text=null;
			text=WebUtil.readElementText(driver, "KYCDashboard.xpath.PreKYCSteps1");
			ReportUtil.reportStringMatch("Verify Pre-KYC Steps Content ", "Countries & Industries Assessment", text);
			text=WebUtil.readElementText(driver, "KYCDashboard.xpath.PreKYCSteps2");
			ReportUtil.reportStringMatch("Verify Pre-KYC Steps Content ", "Documents", text);
			text=WebUtil.readElementText(driver, "KYCDashboard.xpath.PreKYCSteps3");
			ReportUtil.reportStringMatch("Verify Pre-KYC Steps Content ", "Permissions", text);
		}
		
		public WebDriver editEntitylink(WebDriver driver,String editLink,String editEntityTag,String close) 
		{
			WebElement editEntity=WebUtil.findElement(editLink,driver);
			boolean actVal;
			actVal = WebUtil.verifyWebElementExists("WebElement", editEntity);
			WebUtil.clickAt(editEntity);
			WebUtil.wait(4000);
			ReportUtil.reportWebElement("Edit Entity Link Is clicked", actVal);
			WebElement tag=WebUtil.findElement(editEntityTag,driver);
			if(tag.isDisplayed())
			{
				System.out.println("Element Name is: "+tag.getText());
				ReportUtil.reportWebElementExist("Edit Entity Pop Up is displayed", "Edit Entity Details", tag.getText());
			}
			WebElement closeButton=WebUtil.findElement(close,driver);
			actVal = WebUtil.verifyWebElementExists("WebElement", closeButton);
			WebUtil.clickAt(closeButton);
			WebUtil.wait(4000);
			ReportUtil.reportWebElement("Edit Entity Pop up is closed", actVal);;
			return driver;

		}
		
		public void clickEditEntity(WebDriver driver){			
			WebUtil.click("KYCEntityDetail.xpath.EditLink");
			WebUtil.waitUntilElementClickable(driver, "KYCEditEntity.xpath.SaveButton");
			WebUtil.verifyElementDisplayed(driver, "KYCEditEntity.xpath.EditEntityTitle");			
		}
		
		public void saveEditedEntity(WebDriver driver){
			WebUtil.click("KYCEditEntity.xpath.SaveButton");
			WebUtil.waitUntilElementPresent(driver, "KYCEditEntity.xpath.CloseAfterSave");
			WebUtil.waitUntilElementClickable(driver, "KYCEditEntity.xpath.CloseAfterSave");
			WebUtil.click("KYCEditEntity.xpath.CloseAfterSave");
		}
		
		public void clickBackToDashboard(WebDriver driver){
			WebUtil.waitUntilElementPresent(driver, "KYC.xpath.BackToDashboard");
			WebUtil.waitUntilElementClickable(driver, "KYC.xpath.BackToDashboard");
			WebUtil.click("KYC.xpath.BackToDashboard");
			WebUtil.wait(5000);
		}
		
		public WebDriver verifyKYCEntityPageLoaded(WebDriver driver,String element)
		{
			WebElement elementToValidate=WebUtil.findElement(element,driver);
			if(elementToValidate.isDisplayed())
			{
			ReportUtil.reportWebElementExist("Verify KYC Entity Details page is loaded", "MEI", elementToValidate.getText());
			}
			return driver;

		}
		
		public WebDriver validateValuesofEntity(WebDriver driver,ArrayList<String> expectedResult)
		{
			ArrayList<String> actualValues = new ArrayList<String>();
			WebElement actEntityName=WebUtil.findElement("KYCEntityDetail.xpath.EntityName",driver);
			WebElement actMEIName=WebUtil.findElement("KYCEntityDetail.xpath.MEIName",driver);
			WebElement actLEIName=WebUtil.findElement("KYCEntityDetail.xpath.LEIName",driver);
			WebElement actEntityType=WebUtil.findElement("KYCEntityDetail.xpath.EntityType",driver);
			WebElement actCIName=WebUtil.findElement("KYCEntityDetail.xpath.CI",driver);
			WebElement actCountry=WebUtil.findElement("KYCEntityDetail.xpath.Country",driver);
			actualValues.add(actEntityName.getText());
			actualValues.add(actEntityType.getText());
			actualValues.add(actCountry.getText());
			actualValues.add(actMEIName.getText());
			actualValues.add(actLEIName.getText());
			actualValues.add(actCIName.getText());		
			ReportUtil.reportWebElementExist("Verify Entity Name on KYC Entity Details Page", expectedResult.get(0), actualValues.get(0));
			ReportUtil.reportWebElementExist("Verify MEI on KYC Entity Details Page", expectedResult.get(7), actualValues.get(3));
			ReportUtil.reportWebElementExist("Verify LEI on KYC Entity Details Page", expectedResult.get(5), actualValues.get(4));
			ReportUtil.reportWebElementExist("Verify Entity Type on KYC Entity Details Page", expectedResult.get(3), actualValues.get(1));
			ReportUtil.reportWebElementExist("Verify Country on KYC Entity Details Page", expectedResult.get(2), actualValues.get(2));
			ReportUtil.reportWebElementExist("Verify CI Name on KYC Entity Details Page", expectedResult.get(6), actualValues.get(5));
			return driver;

		}
		
		
		
		public WebDriver checkDocumentStatus(WebDriver driver,String checkBoxPath)
		{
			WebElement statusOfDocument=WebUtil.findElement(checkBoxPath,driver);
			
			if(statusOfDocument.isDisplayed())
			{
				String value=statusOfDocument.getAttribute("ng-if");
				if(value.equalsIgnoreCase("entity.DOCUMENTSCOMPLETED"))
				{
					ReportUtil.reportStringMatch("The Status of Document under Pre-KYC Steps is Blue", "entity.DOCUMENTSCOMPLETED", value);
					
				}
				else
				{
				  ReportUtil.reportStringMatch("The Status of  Document is not Blue", "!entity.DOCUMENTSCOMPLETED", value);
					
				}
				
			}
			return driver;
		}
		
		public WebDriver checkPermissionStatus(WebDriver driver,String checkBoxPath)
		{
			WebElement statusOfDocument=WebUtil.findElement(checkBoxPath,driver);
			if(statusOfDocument.isDisplayed())
			{
				String value=statusOfDocument.getAttribute("ng-if");
				if(value.equalsIgnoreCase("entity.PERMISSIONSCOMPLETED"))
				{
					ReportUtil.reportStringMatch("The Status of Permission under Pre-KYC Steps is Blue", "entity.PERMISSIONSCOMPLETED", value);
					
				}
				else
				{
					ReportUtil.reportStringMatch("The Status of  Permission is not Blue", "!entity.PERMISSIONSCOMPLETED", value);
					
				}	
			}
			return driver;

		}
		
		public WebDriver checkCountriesIndustriesStatus(WebDriver driver,String checkBoxPath)
		{
			WebElement statusOfDocument=WebUtil.findElement(checkBoxPath,driver);
			if(statusOfDocument.isDisplayed())
			{
				String value=statusOfDocument.getAttribute("ng-if");
				if(value.equalsIgnoreCase("entity.CIACOMPLETEDFORENTITY"))
				{
					ReportUtil.reportStringMatch("The Status of Countries Industries under Pre-KYC Steps is Blue", "entity.CIACOMPLETEDFORENTITY", value);
					
				}
				else
				{
					ReportUtil.reportStringMatch("The Status of  Countries Industries is not Blue", "!entity.CIACOMPLETEDFORENTITY", value);
					
				}	
			}
			return driver;

		}
	}
	
	public class KYCDocumentsTabPage {

		//public static String entityId=null;


		public WebDriver validateDocuemntPage(WebDriver driver,String element)
		{
			WebElement elementName=WebUtil.findElement(element,driver);
			if(elementName.isDisplayed())
			{
				ReportUtil.reportStringMatch("Verify the Docuemnts Page is loaded", "Recommended KYC Documents", elementName.getText());
			}
			return driver;

		}

	/*	public WebDriver clickAtUploadDocument(WebDriver driver,String uploadButton) 
		{
			
			WebElement uploadElement=WebUtil.findElement(uploadButton,driver);
			boolean actVal;
			if(uploadElement.isDisplayed())
			{
				actVal = WebUtil.verifyWebElementExists("WebElement", uploadElement);
				WebUtil.actionClass(uploadElement, driver);
				WebUtil.wait(3000);
				ReportUtil.reportWebElement("Upload button is clicked", actVal);
			}
			return driver;

		}*/

		public String uploadDocument(WebDriver driver,String docName,String browserName) 
		{
			boolean actVal;
			WebElement ele=driver.findElement(By.xpath("//span[contains(text(),'"+docName+"')]/ancestor::tr//td[3]/a"));
			actVal = WebUtil.verifyWebElementExists("WebElement", ele);
			ele.click();
			ReportUtil.reportWebElement("Upload button is clicked", actVal);
			WebUtil.wait(3000);
			WebUtil.clickedWithAction(driver, "KYCDocumentUpload.xpath.Browser");
			WebUtil.wait(4000);	
			WebUtil.uploadDocumentAsperPassedBrowserName(driver, browserName);
			WebUtil.wait(4000);	
			WebUtil.clickedWithAction(driver, "KYCDocumentUpload.xpath.FileSave");
			WebUtil.wait(3000);	
			ele=driver.findElement(By.xpath("//a[contains(text(),'"+docName+"')]"));
			String value=ele.getAttribute("href");
			int i=value.lastIndexOf("documents");
			String docId=value.substring(i+10);
			System.out.println(docId);
			return docId;

		}
		
		


		public WebDriver clickAtContinue(WebDriver driver,String continueButton) 
		{
			WebElement continuebutton=WebUtil.findElement(continueButton,driver);
			boolean actVal;
			actVal = WebUtil.verifyWebElementExists("WebElement", continuebutton);

			WebUtil.actionClass(continuebutton, driver);
			WebUtil.wait(5000);
			ReportUtil.reportWebElement("Clicked on Continue button of Documents", actVal);
			return driver;

		}

		public String downloadFileForChrome(WebDriver driver,String documentName) 
		{
			WebElement ele=driver.findElement(By.xpath("//a[contains(text(),'"+documentName+"')]/ancestor::tr//td[2]/a"));
			String value= ele.getAttribute("href");	
			int j=value.lastIndexOf("documents");			
			String docId=value.substring(j+10);
			System.out.println(docId);
			boolean actVal;
			actVal = WebUtil.verifyWebElementExists("WebElement", ele);
			WebUtil.actionClass(ele, driver);
			WebUtil.wait(5000);
			ReportUtil.reportWebElement("Download button is clicked for Chrome Browser", actVal);
			return docId;

		}
		public void verifyfileGetsDownloadedforChrome(WebDriver driver,String docId)
		{
			WebElement ele=null;
			WebElement subEle=null;	
			boolean actVal=false;
			WebElement documentAvilable=driver.findElement(By.xpath("//a[contains(@href,'"+docId+"')]"));
			if(documentAvilable.isDisplayed())
			{
				WebUtil.wait(2000);
				ele=driver.findElement(By.xpath("//a[contains(@href,'"+docId+"')]/parent::td/parent::tr"));
				subEle=ele.findElement(By.xpath("//td[2]/a"));
				
				actVal = WebUtil.verifyWebElementExists("WebElement", subEle);
				subEle.click();
				ReportUtil.reportWebElement("Download Button is clicked", actVal);
			}
			String path1=System.getProperty("user.dir");
			
		}

		public WebDriver downloadFileForFF(WebDriver driver,String element) 
		{
			WebElement downloadButton=WebUtil.findElement(element,driver);

			boolean actVal;
			actVal = WebUtil.verifyWebElementExists("WebElement", downloadButton);
			WebUtil.actionClass(downloadButton, driver);
			WebUtil.wait(2000);
			ReportUtil.reportWebElement("Download button is clicked for FF", actVal);
			try {
				Runtime.getRuntime().exec("src/testdata/AutoItScripts/DownloadFileforFF.exe");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebUtil.wait(5000);
			System.out.println("File downloaded");

			return driver;

		}
	/*	public WebDriver validateTheUploadedDocuemnt(WebDriver driver,String userNamePath,String date,String expectedUserName)
		{
			Date cur_dt = new Date();
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			String strTodaysDate = dateFormat.format(cur_dt);
			WebElement userName=WebUtil.findElement(userNamePath,driver);
			String actualuserName=userName.getText();
			ReportUtil.reportStringMatch("Verify the Uploaded By User Name", expectedUserName, actualuserName);
			WebElement currentDate=WebUtil.findElement(date,driver);
			String actualDate=currentDate.getText();
			ReportUtil.reportStringMatch("Date Uploaded", strTodaysDate, actualDate);
			//System.out.println("Verify the date of the element actual: "+actualDate+" Expected: "+strTodaysDate);
				if(expectedUserName.equalsIgnoreCase(actualuserName)&&actualDate.equalsIgnoreCase(strTodaysDate))
			{
				System.out.println("The user name and uploaded date: "+" "+strTodaysDate);
			}

			return driver;

		}*/
		
		public WebDriver validateTheUploadedDocuemnt(WebDriver driver,String docType,String expectedUserName)
		{
			WebElement ele=null;
			WebElement subEle=null;
			Date cur_dt = new Date();
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			String strTodaysDate = dateFormat.format(cur_dt);
			ele=driver.findElement(By.xpath("//a[contains(text(),'"+docType+"')]/ancestor::tr"));
			subEle=ele.findElement(By.xpath("//td[5]/span"));
			String actualValue=subEle.getText();
			ReportUtil.reportStringMatch("Verify the Uploaded By User Name", expectedUserName, actualValue);
			subEle=ele.findElement(By.xpath("//td[7]/span"));
			actualValue=subEle.getText();
			ReportUtil.reportStringMatch("Date Uploaded", strTodaysDate, actualValue);
			
			return driver;

		}
		
		public  String dbValidationForDocumentCount(String entity_Name)
		{

			//Connection con=DBUtility.kycDBConnection();
			String entityName=entity_Name;
			String entityId=null;
			//DBUtility.getRSexecuteQuery(con,"select * from V_entity where entity_id=41659914506386");
			entityId=DBUtility.getColData(con,"select * from v_entity_screener where entity_name='"+entityName+"'","entity_id");
			String count=DBUtility.getColData(con,"select count(*) from entity_doc where entity_id='"+entityId+"'","count(*)");
			return count;

		}
		
		public void clickAtDocumentsTab(WebDriver driver)
		{
			WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.DocumentsTab");
			WebUtil.wait(3000);
		}
		
		public void verifyDocIDOfUploadedDoc(WebDriver driver,String docId,String expectedDocType)
		{
			WebElement ele=null;
			WebElement subEle=null;
			WebUtil.wait(2000);
			WebElement documentAvilable=driver.findElement(By.xpath("//a[contains(@href,'"+docId+"')]"));
			if(documentAvilable.isDisplayed())
			{
				WebUtil.wait(3000);
				ele=driver.findElement(By.xpath("//a[contains(@href,'"+docId+"')]/parent::td/parent::tr"));
				subEle=ele.findElement(By.xpath("//td[1]//a"));
				String acutalDoc=subEle.getText();
				ReportUtil.reportStringMatch("Verify Document Type", expectedDocType, acutalDoc);
				subEle=ele.findElement(By.xpath("//td[2]/a"));
				String value=subEle.getAttribute("href");			
				int i=value.lastIndexOf("documents");
				String actualDocId=value.substring(i+10);
				ReportUtil.reportStringMatch("Verify the DocumentID on KYC Dashbaord", docId, actualDocId);
			}			
			
		}
		
		public void verifyNoDocIsUploaded(WebDriver driver)
		{
			List<WebElement> tables=WebUtil.findElements("KYCDashboard.xpath.Tables", driver);
			List<WebElement> rows=null;
			List<WebElement> expand=null;
			String text=null;
			System.out.println("The list size: "+tables.size());
			
			rows=driver.findElements(By.xpath("//table[@class='simple-table'][1]//tbody//tr[@ng-repeat='doc in documents.recommended']"));
			System.out.println("The list size: "+rows.size());
			for(int i=1;i<=rows.size();i++)
			{
				text=driver.findElement(By.xpath("//table[@class='simple-table']//tbody//tr[@ng-repeat='doc in documents.recommended']["+i+"]//td[5]//span")).getText();
				
				ReportUtil.reportStringMatch("Verify the no document is uploaded", "", text);
			}

			expand=driver.findElements(By.xpath("//div[contains(@ng-repeat,'category in documents.additional')]"));
			System.out.println("The list size: "+expand.size());
			for(int j=1;j<=expand.size();j++)
			{
				rows=driver.findElements(By.xpath("//div[contains(@ng-repeat,'category in documents.additional')]["+j+"]//table//tbody//tr"));
				for(int k=1;k<=rows.size();k++)
				{
					text=driver.findElement(By.xpath("//div[contains(@ng-repeat,'category in documents.additional')]["+j+"]//table//tbody//tr["+k+"]//td[5]/span")).getText();
					
					ReportUtil.reportStringMatch("Verify the no document is uploaded", "", text);
				}

			}
		}
		}
		
	
	
	public class KYCPermissionPage {
		
		
		
		Connection con=DBConnection.dbConnections.get("KYC");
		public void clickAtPermissionTab(WebDriver driver)
		{
             WebUtil.clickedWithAction(driver, "KYCRegression.xpath.PermissionTab");
             WebUtil.wait(3000);
		}
		public WebDriver validatePermissionPage(WebDriver driver,String element)
		{
			
			WebElement elementName=WebUtil.findElement(element,driver);
			if(elementName.isDisplayed())
			{
				ReportUtil.reportStringMatch("Verify the Permission page is loaded", "PERMISSIONED COUNTERPARTIES", elementName.getText());
				
			}
			return driver;

		}



		public WebDriver serchBankName(WebDriver driver,String inputBox,String bankName,String searchbutton) 
		{
			WebElement searchEdit=WebUtil.findElement(inputBox,driver);
			WebElement searchButton=WebUtil.findElement(searchbutton,driver);
			boolean actVal;

			if(searchEdit.isDisplayed())
			{

				WebUtil.setWebEdit(searchEdit, bankName);
				WebUtil.wait(3000);
				String value=WebUtil.readWebElement("WebEdit",searchEdit);
				ReportUtil.reportWebEditRead( "Entered Bank Name",searchEdit, bankName, value);
				actVal = WebUtil.verifyWebElementExists("WebElement", searchButton);
				WebUtil.actionClass(searchButton, driver);
				ReportUtil.reportWebElement("Search button is clicked", actVal);
				
			}

			WebElement option= driver.findElement(By.xpath("//a[contains(text(),'"+bankName+"')]"));
			actVal = WebUtil.verifyWebElementExists("WebElement", option);
			WebUtil.actionClass(option, driver);
			ReportUtil.reportWebElement("Bank Name is selected", actVal);
			return driver;

		}

		public WebDriver validateRequestKYCButton(WebDriver driver,String element)
		{
			
			WebElement requestButton=WebUtil.findElement(element,driver);

			boolean actVal;

			if(requestButton.isDisplayed())
			{
				System.out.println("Request KYC is visable: "+requestButton.getText());
				//ReportUtil.reportStringMatch("Request KYC Button is visible", "Request KYC",requestButton.getText());
				actVal = WebUtil.verifyWebElementExists("WebElement", requestButton);
				WebUtil.clickAt(requestButton);
				WebUtil.wait(3000);
				ReportUtil.reportWebElement("Request KYC Button is clicked", actVal);
			}
			return driver;

		}

		public WebDriver validatePopUp(WebDriver driver,String element,String cancel) 
		{
			WebElement popup=WebUtil.findElement(element,driver);
			WebElement cancelButton=WebUtil.findElement(cancel,driver);
			boolean actVal;
			if(popup.isDisplayed())
			{
				System.out.println("Pop Up is visable: "+popup.getText());
				ReportUtil.reportStringMatch("Verify the Pop Up is content", "Your request for KYC Services has been successfully submitted", popup.getText());
				actVal = WebUtil.verifyWebElementExists("WebElement", cancelButton);
				WebUtil.actionClass(cancelButton, driver);
				WebUtil.wait(2000);
				ReportUtil.reportWebElement("Pop Up is closed", actVal);
				System.out.println("Flow completed");
			}
			return driver;

		}


	/*	public String dbValidationForSubscriber() throws SQLException
		{
			//Connection con=DBUtility.kycDBConnection();
			String bank=KYCDashboardPageTC.bankName;
			System.out.println(bank);
			String eID=KYCDocumentUploadPage.entityId;
			System.out.println(eID);
			//DBUtility.getRSexecuteQuery(con,"select * from V_entity where entity_id=41659914506386");
			String subID=DBUtility.getColData(con,"select * from subscriber where name='"+bank+"'","SUBSCRIBER_ID");
			System.out.println("The DB  Value:"+subID);
			String subscriberID=DBUtility.getColData(con,"select * from entity_authorize_subscriber where entity_id="+eID+"and SUBSCRIBER_ID="+subID+"","SUBSCRIBER_ID");
			System.out.println(subscriberID);
			if(subID.equalsIgnoreCase(subscriberID))
			{
				System.out.println("ID matches");
			}
			else 
			{
				System.out.println("ID does not match");
			}
			return subscriberID;


		}*/

	}
	
	public class  Countries_Industries
	{
		public void fill_Countries_Industries_details(WebDriver driver)
		{
			
			WebUtil.wait(3000);
			int size=WebUtil.getSizeOfElement(driver, "CountriesIndustries.xpath.CompleteNew");
			if(size!=0)
			{
			WebUtil.clickedWithAction(driver, "CountriesIndustries.xpath.CompleteNew");
			WebUtil.clickedWithAction(driver, "CountriesIndustries.xpath.Submit");	
			WebUtil.wait(10000);
			}
			
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
			//WebUtil.clickedWithAction(driver, "KYCRegression.xpath.Dismiss");
			
			
		}
	}


}
