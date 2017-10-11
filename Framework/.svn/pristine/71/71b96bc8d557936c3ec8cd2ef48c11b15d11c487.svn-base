package com.markit.framework.pega.pageMethods;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import atu.testng.reports.logging.LogAs;

import com.markit.common.framework.runtestsuit.PegaRegression_RunTestSuit;
import com.markit.common.framework.util.DBConnection;
import com.markit.common.framework.util.DBUtility;
import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.framework.pega.testcases.PEGARegTC;

public class PEGACases<acceptTasksRequests_ViewButton> {

	private static String cobsid=null;
	Connection con=DBConnection.dbConnections.get("PEGA");
	public static WebDriver driver;
	public static final Logger log = Logger.getLogger("appLogger");
	ArrayList<String> expectedValues = new ArrayList<String>();

	public void createDocRequest(WebDriver driver){

		WebUtil.click("PEGACpoReq.xpath.ClickReqType");
		WebUtil.wait(2000);
		WebUtil.click("PEGACpoReq.xpath.SelectDocReq");
		WebUtil.wait(2000);
		WebUtil.click("PEGACpoReq.xpath.DocTypeAnnualReport");

		WebUtil.wait(3000);
		WebUtil.click("PEGACpoReq.xpath.Comments");
		WebElement edit= WebUtil.findElement("PEGACpoReq.xpath.Comments", driver);

		WebUtil.setWebEdit(edit, "Auto Comments");
		WebUtil.wait(3000);

		WebUtil.click("PEGACpoReq.xpath.ClickAnywhere");
		WebUtil.wait(5000);
		WebUtil.click("PEGACpoReq.xpath.AddButton");
		WebUtil.wait(4000);
	}

	public void createInfoRequest(WebDriver driver){

		WebUtil.click("PEGACpoReq.xpath.ClickReqType");
		WebUtil.wait(2000);
		WebUtil.click("PEGACpoReq.xpath.SelectInfoReq");


		WebUtil.wait(3000);
		WebElement edit1= WebUtil.findElement("PEGACpoReq.xpath.Comments", driver);
		WebUtil.setWebEdit(edit1, "Auto Comments");
		WebUtil.wait(3000);

		WebUtil.click("PEGACpoReq.xpath.ClickAnywhere");
		WebUtil.wait(5000);
		WebUtil.click("PEGACpoReq.xpath.AddButton");
		WebUtil.wait(6000);
	}

	public void createWolfsbergQues(WebDriver driver){

		WebUtil.click("PEGACpoReq.xpath.ClickReqType");
		WebUtil.wait(2000);
		WebUtil.click("PEGACpoReq.xpath.SelectDocReq");
		WebUtil.click("PEGACpoReq.xpath.DocTypeWolfsberg");
		WebUtil.wait(3000);
		WebElement edit1= WebUtil.findElement("PEGACpoReq.xpath.Comments", driver);
		WebUtil.setWebEdit(edit1, "Auto Comments");

		WebUtil.click("PEGACpoReq.xpath.ClickAnywhere");
		WebUtil.wait(5000);
		WebUtil.click("PEGACpoReq.xpath.AddButton");
		WebUtil.wait(6000);
	}

	public void createOwnershipTask(WebDriver driver){


		WebUtil.click("PEGACpoReq.xpath.ClickReqType");
		WebUtil.wait(2000);
		WebUtil.click("PEGACpoReq.xpath.SelectOwnershipReq");
		WebUtil.wait(3000);

		WebElement edit2= WebUtil.findElement("PEGACpoReq.xpath.Comments", driver);
		WebUtil.setWebEdit(edit2, "Auto Comments");
		WebUtil.wait(3000);

		WebUtil.click("PEGACpoReq.xpath.ClickAnywhere");
		WebUtil.wait(5000);
		WebUtil.click("PEGACpoReq.xpath.AddButton");
		WebUtil.wait(6000);
	}


	public void acceptAttributes(WebDriver driver,String xpathKey){

		List<WebElement> AcceptLinks=WebUtil.findElements(xpathKey, driver);
		for (int i=0; i<=AcceptLinks.size()-1; i++){
			/*AcceptLinks=WebUtil.findElements(xpathKey, driver);
			WebUtil.wait(3000);
			try{
			AcceptLinks.get(i).click();
			}catch(Exception e){
			log.error("Unable to click accept link no. "+i+ e.getMessage());
			List<WebElement> AcceptLinks_New=WebUtil.findElements(xpathKey, driver);
			AcceptLinks_New.get(i).click();
			}*/
			retryingElementClick(driver,xpathKey,i);
			WebUtil.wait(4000);
		}
		ReportUtil.reportWebElement("All attributes are accepted", true);
	}

	public void uploadDoc(WebDriver driver)
	{
		boolean actVal;
		WebElement upload=WebUtil.findElement("PEGACase.xpath.UploadDoc", driver);
		WebUtil.actionClass(upload, driver);
		WebUtil.wait(2000);
		actVal=WebUtil.verifyWebElementExists("WebLink",upload);
		ReportUtil.reportWebElement("Upload document button is clicked",actVal);
		System.out.println("Clicked Upload button");
		WebElement browse=WebUtil.findElement("PEGACase.xpath.BrowseButton", driver);
		WebUtil.actionClass(browse, driver);
		WebUtil.wait(2000);
		actVal=WebUtil.verifyWebElementExists("WebLink",upload);
		ReportUtil.reportWebElement("Browse button is clicked",actVal);
		System.out.println("Clicked Browse button");
        WebUtil.uploadDocumentAsperPassedBrowserName(driver, "chrome");
		WebUtil.wait(2000);
		ReportUtil.reportWebElementExist("The document is uploaded", "", "");
	}
	
	public void fillDocDetailsforCase(WebDriver driver,Map<String,String> data)
	{
	
		WebUtil.selectValueFromDropDownByVisibleText(driver, "PEGACase.xpath.DocType", data.get("Document Type"));
		WebUtil.selectValueFromDropDownByVisibleText(driver, "PEGACase.xpath.DocSource", data.get("Document Source"));
		WebUtil.clickedWithAction(driver, "PEGACase.xpath.Comments");
		System.out.print("");
		WebUtil.inputValue("PEGACase.xpath.DocSourceName",data.get("Document Source Name"));		
		WebUtil.selectValueFromDropDownByVisibleText(driver, "PEGACase.xpath.CertifiedTrueCopy",data.get("Is Certified True Copy"));
		WebUtil.selectValueFromDropDownByVisibleText(driver, "PEGACase.xpath.InternallyConfirmed", data.get("Is Internally Confirmed"));
		WebUtil.clickedWithAction(driver, "PEGACase.xpath.UploadButton");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "PEGACase.xpath.OK");
	}
	

	public void fillDetailsforUploadedDoc(WebDriver driver,List<String> expectedValues) 
	{
		String value=null;
		WebElement element=null;
		String actualvalue=null;
		boolean actVal;
		try{
			WebUtil.ifElementIsEnabledThenClick(driver, "PEGACase.xpath.DocType");
			value=WebUtil.selectValueFromDropDownAsPerPassedIndex(driver, "PEGACase.xpath.DocType", 5);
		}
		catch (StaleElementReferenceException e)
		{
			e.getMessage();
		}

		element=WebUtil.findElement("PEGACase.xpath.DocType", driver); 
		value=WebUtil.readWebElement("WebEdit",element);
		ReportUtil.reportWebEditRead("Enter Document Type",element, value, value);
		try{
			WebUtil.wait(2000);
			WebUtil.clickedWithAction(driver, "PEGACase.xpath.DocSource");
			WebUtil.selectValueFromDropDownAsPerPassedVaule(driver, "PEGACase.xpath.DocSource", expectedValues.get(1));
		}
		catch (StaleElementReferenceException e)
		{
			e.getMessage();
		}
		element=WebUtil.findElement("PEGACase.xpath.DocSource", driver); 
		value=WebUtil.readWebElement("WebEdit",element);
		ReportUtil.reportWebEditRead("Enter Docuemnt Source",element, expectedValues.get(1), value);
		WebUtil.wait(2000);
		WebElement sourceName=WebUtil.findElement("PEGACase.xpath.DocSourceName", driver);
		sourceName.click();
		sourceName.sendKeys(expectedValues.get(2));

		value=WebUtil.readWebElement("WebEdit",sourceName);
		ReportUtil.reportWebEditRead("Enter Docuemnt Source name",element, expectedValues.get(2), value);


		WebUtil.clickedWithAction(driver, "PEGACase.xpath.CaptureDate");
		WebUtil.wait(2000);
		/*WebUtil.clickedWithAction(driver, "PEGACase.xpath.CurrentDate");
		WebUtil.wait(2000);
		System.out.println("Date Selected");*/
		ReportUtil.reportWebElementExist("Current date is selected", "", "");
		/* WebUtil.clickedWithAction(driver, "PEGACase.xpath.ExpirationeDate");
		  WebUtil.wait(2000);
		  WebUtil.clickedWithAction(driver, "PEGACase.xpath.CurrentDate");*/
		try{
			WebUtil.clickedWithAction(driver, "PEGACase.xpath.CertifiedTrueCopy");
			actualvalue=WebUtil.selectValueFromDropDownAsPerPassedVaule(driver, "PEGACase.xpath.CertifiedTrueCopy", expectedValues.get(3));
		}
		catch (StaleElementReferenceException e)
		{
			e.getMessage();
		}
		ReportUtil.reportStringMatch("Selected value in Certified True Copy", expectedValues.get(3), actualvalue);
		try{
			WebUtil.clickedWithAction(driver, "PEGACase.xpath.InternallyConfirmed");
			actualvalue=WebUtil.selectValueFromDropDownAsPerPassedVaule(driver, "PEGACase.xpath.InternallyConfirmed", expectedValues.get(4));
		}
		catch (StaleElementReferenceException e)
		{
			e.getMessage();
		}
		ReportUtil.reportStringMatch("Selected value in Internally Confirmed", expectedValues.get(4), actualvalue);
		element=WebUtil.findElement("PEGACase.xpath.UploadButton", driver);
		actVal=WebUtil.verifyWebElementExists("WebLink",element);
		WebUtil.clickedWithAction(driver, "PEGACase.xpath.UploadButton");
		ReportUtil.reportWebElement("Clicked On Upload Button",actVal);
		WebUtil.wait(5000);
		WebUtil.clickedWithAction(driver, "PEGACase.xpath.Close");

	}
	public void attachDoc(WebDriver driver,String docId) 
	{
		//WebUtil.clickedWithAction(driver, "PEGA.xpath.AttachDoc");
		WebElement element=WebUtil.findElement("PEGA.xpath.AttachDoc", driver);
		WebUtil.actionClass(element, driver);
		ReportUtil.reportStringContainsMatch("Attach Document is clicked", "", "");
		WebUtil.wait(5000);
		System.out.println("Attached doc is clicked");
		WebUtil.clickedWithAction(driver, "PEGA.xpath.CheckBox");
		ReportUtil.reportStringContainsMatch("Field name is Selected", "", "");
		WebUtil.wait(7000);
		WebElement docCheckbox=driver.findElement(By.xpath("//span[contains(text(),'"+docId+"')]/parent::div/parent::td/preceding-sibling::td//input[2]"));
		WebUtil.actionClass(docCheckbox, driver);
		ReportUtil.reportStringContainsMatch("Document is selected", "", "");
		WebUtil.wait(5000);
		WebUtil.clickedWithAction(driver, "PEGA.xpath.AddDoc");
		ReportUtil.reportStringContainsMatch("Add Documents is clicked", "", "");
		System.out.println("uploaded");
		WebUtil.wait(7000);
		String subdocid=docId.substring(11);
		System.out.println("Sub doc id: "+subdocid);
		WebElement ele=driver.findElement(By.xpath("//a[contains(text(),'"+subdocid+"')]"));
		String text= ele.getText();
		ReportUtil.reportStringContainsMatch("Uploaded document id", text, text);
		System.out.println("Doc name displayed"+ text);

	}

	public String getCOBSID(WebDriver driver)
	{
		cobsid=WebUtil.readElementText(driver, "PEGA.xpath.COBSID");
		String label=WebUtil.readElementText(driver, "PEGA.xpath.Label");
		System.out.println("CobsID: "+cobsid+"And Label: "+label );
		return cobsid;

	}

	public  String dbValidationForDocumentID()
	{
		String cobID=cobsid;
		//DBUtility.getRSexecuteQuery(con,"select * from V_entity where entity_id=41659914506386");
		String sql = "select  * from  (Select * from case_doc where case_id="+cobID+""+" order by  doc_id desc) where rownum=1";
		String docid=DBUtility.getColData(con,sql,"DOC_ID");
		System.out.println("doc id: "+docid );
		System.out.println(sql);
		return docid;

	}

	public void sessionCheckOnCaseID(WebDriver driver) throws IOException
	{
		System.out.println("in check method");
		int size=WebUtil.getSizeOfElement(driver, "PEGACase.xpath.Error");
		System.out.println("Size of the element"+size);
		if(size!=0)
		{

			boolean actVal;
			System.out.println("Element is displayed");
			WebElement proceed=WebUtil.findElement("PEGACase.xpath.EndOtherSessionButton", driver);
			actVal=WebUtil.verifyWebElementExists("WebLink",proceed);
			WebUtil.clickedWithAction(driver, "PEGACase.xpath.EndOtherSessionButton");
			System.out.println("Element is clicked");
			ReportUtil.reportWebElement("Click At Proceed ",actVal);
			WebUtil.wait(10000);
			System.out.println("time out");
			/*uploadDocButton(driver);
			fillDetailsforUploadedDoc(driver);*/
		}
		/*else
		{
			System.out.println("In else block");
			uploadDocButton(driver);
			fillDetailsforUploadedDoc(driver);

		}*/
	}

	public void editDoc(WebDriver driver,String elementPath,String valueToBeEdited,String docId) 
	{
		boolean actVal;
		String actaulvalue;
		WebElement element;
		System.out.println("in edit method");
		String subdocid=docId.substring(11);
		System.out.println("Sub doc id: "+subdocid);
		//span[contains(text(),'6071')]/parent::span/parent::div/parent::td/preceding-sibling::td//img
		WebElement editButton=driver.findElement(By.xpath("//span[contains(text(),'"+subdocid+"')]/parent::span/parent::div/parent::td/preceding-sibling::td//img"));
		actVal=WebUtil.verifyWebElementExists("WebLink",editButton);
		WebUtil.actionClass(editButton, driver);
		ReportUtil.reportWebElement("Clicked on the Edit document button",actVal);
		System.out.println("Clicked");


		if(elementPath.equalsIgnoreCase("PEGACase.xpath.DocSourceName"))
		{
			WebElement docSurName= WebUtil.findElement("PEGACase.xpath.DocSourceName", driver);
			docSurName.clear();
			docSurName.sendKeys(valueToBeEdited);
			actaulvalue=WebUtil.readWebElement("WebEdit",docSurName);
			ReportUtil.reportWebEditRead("Edited Docuemnt Source name",docSurName, valueToBeEdited, actaulvalue);

		}
		else
		{
			WebUtil.clickedWithAction(driver, elementPath);
			String act=WebUtil.selectValueFromDropDownAsPerPassedVaule(driver, "PEGACase.xpath.DocType", valueToBeEdited);
			ReportUtil.reportStringMatch("Edited value of Drop Downs", valueToBeEdited, act);
		}
		System.out.println("element is clicked");
		element=WebUtil.findElement("PEGA.xpath.UpdateButton", driver);
		actVal=WebUtil.verifyWebElementExists("WebLink",element);
		WebUtil.clickedWithAction(driver, "PEGA.xpath.UpdateButton");		 	
		ReportUtil.reportWebElement("Clicked On Update button",actVal);

		WebUtil.wait(5000);

		WebUtil.clickedWithAction(driver, "PEGA.xpath.EditCloase");
		WebUtil.wait(5000);
	}

	public void fillingLegalFormationData(WebDriver driver) 
	{
		WebUtil.clickedWithAction(driver, "PEGA.xpath.LegalFormation");
		//System.out.println("Legal formation is clicked");
		WebUtil.wait(5000);		
		searchMEI(driver);
		WebUtil.selectValueFromDropDownAsPerPassedIndex(driver, "PEGA.xpath.EntityType", 2);
		System.out.println("Entity type is clicked");
		WebUtil.wait(6000);
		WebUtil.clickedWithAction(driver, "PEGA.xpath.SaveContinue");
		System.out.println("Save anc continue is clicked");
		WebUtil.wait(6000);




	}
	
	public String searchMEI(WebDriver driver){
		String mei="";
		try{
			//WebUtil.wait(5000);
		WebUtil.clickedWithAction(driver, "PEGACase.xpath.SearchMEIButton");
		WebUtil.wait(5000);
		WebUtil.selectValueFromDropDownAsPerPassedIndex(driver, "PEGA.xpath.DocumentIDSelect", 1);
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "PEGA.xpath.Create");
		WebUtil.wait(10000);
		String statement=WebUtil.readElementText(driver, "PEGACase.xpath.MEIText");
		mei=WebUtil.extractSubStringBetweenDoubleQuotes(statement);
		System.out.println("the value of mei is: "+mei);				
		if(mei==null){
			WebUtil.clickedWithAction(driver,"PEGACases.xpath.ClickMEILink");	
		}
		else{
			WebUtil.clickedWithAction(driver, "PEGA.xpath.CreateOk");
		}
		WebUtil.wait(8000);
		
		}catch(Exception e){
		WebElement close=WebUtil.findElement("PEGACases.xpath.CloseModalOutreach", driver);
		close.click();
		log.error("search MEI method error"+e.getMessage());
		e.printStackTrace();
		}
		return mei;
	}


	public void checkSearchMEIError(WebDriver driver) 
	{
		int size=WebUtil.getSizeOfElement(driver, "PEGA.xpath.SearchMEIError");
		if(size!=0)
		{
			WebUtil.clickedWithAction(driver, "PEGA.xpath.SearchMEICloase");
		}
	}

	public void addNonIndivKeyController(WebDriver driver,String controllerName){

		WebUtil.click("PEGACases.xpath.KeyControllerLink");
		WebUtil.wait(6000);
		WebUtil.click("PEGACase.xpath.AddKeyController");
		WebUtil.wait(6000);
		WebUtil.click("PEGACase.xpath.KeyControllerType");
		WebUtil.wait(7000);

		WebElement country=WebUtil.findElement("PEGACaseAddController.xpath.Country", driver);
		WebUtil.setWebEdit(country, "Afghanistan");
		WebUtil.wait(4000);
		WebUtil.click("PEGACaseAddController.xpath.SelectCountry");
		WebUtil.wait(6000);

		WebElement legalName=WebUtil.findElement("PEGACaseAddController.xpath.legalName", driver);
		WebUtil.setWebEdit(legalName, controllerName);
		WebUtil.wait(7000);


		WebElement position=WebUtil.findElement("PEGACaseAddController.xpath.PositionRole", driver);
		position.click();
		WebUtil.wait(6000);

		WebElement state=WebUtil.findElement("PEGACaseAddController.xpath.State", driver);
		WebUtil.setWebEdit(state, "state");
		WebUtil.wait(7000);

		WebUtil.clickedWithAction(driver, "PEGACaseAddController.xpath.legalName");

		ReportUtil.reportInputValueOnly("Key Controller Type", "Non Individual", true);
		ReportUtil.reportInputValueOnly("Enter Key controller name", controllerName, true);
		String mei=searchMEI(driver);
		ReportUtil.reportInputValueOnly("Key Controller: "+controllerName+ "has MEI", mei, true);


		WebUtil.click("PEGACaseAddController.xpath.Save");
		WebUtil.wait(7000);
	}

	public void addNonIndivBeneficialOwner(WebDriver driver,String boName){

		WebUtil.click("PEGACases.xpath.OwnershipInfoLink");
		WebUtil.wait(7000);
		WebUtil.click("PEGACase.xpath.AddOwner");
		WebUtil.wait(6000);
		WebUtil.click("PEGACase.xpath.KeyControllerType");
		WebUtil.wait(5000);

		WebElement country=WebUtil.findElement("PEGACaseAddController.xpath.Country", driver);
		WebUtil.setWebEdit(country, "Afghanistan");
		WebUtil.wait(4000);
		WebUtil.click("PEGACaseAddController.xpath.SelectCountry");
		WebUtil.wait(7000);

		List<WebElement> percentageFieldList=WebUtil.findElements("PEGACaseAddOwner.xpath.PercentOwnership", driver);

		WebUtil.setWebEdit(percentageFieldList.get(0), "20");
		WebUtil.wait(6000);
		WebUtil.setWebEdit(percentageFieldList.get(1), "20");
		WebUtil.wait(6000);

		WebElement legalName=WebUtil.findElement("PEGACaseAddController.xpath.legalName", driver);
		WebUtil.setWebEdit(legalName, boName);
		WebUtil.wait(6000);


		WebElement state=WebUtil.findElement("PEGACaseAddController.xpath.State", driver);
		WebUtil.setWebEdit(state, "state");
		WebUtil.wait(7000);

		ReportUtil.reportInputValueOnly("Beneficial Owner Type", "Non Individual", true);
		ReportUtil.reportInputValueOnly("Enter Beneficial Owner name", boName, true);
		String mei=searchMEI(driver);
		ReportUtil.reportInputValueOnly("Beneficial Owner:"+boName+ "has MEI", mei, true);
		WebUtil.wait(7000);

		WebUtil.click("PEGACaseAddOwner.xpath.Save");
		WebUtil.wait(7000);

	}

	public static void enterDocUploadData(WebDriver driver,String wbPath,String sheetName)
	{
		try{
		PEGACases  pega= new PEGACases();
		Map<String,String> data=new  HashMap<String,String>();	
		Workbook wbObj=ExcelUtil.getWorkBookObject(wbPath);
		Sheet wbSheet=wbObj.getSheet(sheetName);
			
		for (int i=1;i<=wbSheet.getLastRowNum();i++){
			String value=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "TestID", i);
			System.out.println(value);
			data =ExcelUtil.getRowDataAndMapWithColName(wbPath,sheetName,i);
			pega.uploadDoc(driver);
			pega.fillDocDetailsforCase(driver, data);					
		}	
		//pega.searchMEI(driver);		
		}catch(Exception e){
			log.error("Inside enter doc upload data" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void enterTestData(WebDriver driver,String wbPath,String sheetName)
	{
		PEGACases  pega= new PEGACases();
		Map<String,String> data=new HashMap<String,String>();
		Workbook wbObj=ExcelUtil.getWorkBookObject(wbPath);
		Sheet wbSheet=wbObj.getSheet(sheetName);

		for (int i=1;i<=wbSheet.getPhysicalNumberOfRows()-1;i++){
			String att_id=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "Attribute_IDs", i);
			System.out.println(att_id);
			try{
				data =ExcelUtil.getRowDataAndMapWithColName(wbPath,sheetName,i);
			}
			catch(Exception e){
				log.error("Excel reading error during enter Test data"+e.getMessage()); 
			}
			String fieldType=data.get("Attribute_Type");
			String pathKey=data.get("Path_Key");
			String value=data.get("Value");
			String outreachReqType=data.get("OutreachReqType");
			String outreachXpath=data.get("OutreachPathKey");
			String docId=data.get("DocId");
			String comments=data.get("Comments");
			String commentType=data.get("InternalCommentType");
			String icXpath=data.get("InternalCommentsPathKey");
			String icsubType=data.get("InternalComment_SubType");
			String attachDocXpath=data.get("AttachDocPathKey");
			String docIdAttribute=data.get("AttachDocSerialId");
		
			if(value!=null &value.equals("MEI")){
			pega.searchMEI(driver);
			}
			else if(att_id.equals("2.4") ||(att_id.equals("2.9"))){
				System.out.println("No change in fields"+att_id);
			}
			else{				
				performActionByFieldType(driver,fieldType,pathKey,value);	
			}
			WebUtil.wait(6000);				
			createCustomOutreachRequest(driver,outreachReqType,outreachXpath,docId,comments);
			addComments(driver,icXpath,icsubType,commentType,comments);	
			attachDocToAttribute(driver, attachDocXpath, docIdAttribute);
		}		
		try {
			wbObj.close();
		} catch (IOException e) {
			log.error("unable to close excel"+e.getMessage());			
		}
	}


	public static void performActionByFieldType(WebDriver driver,String fieldType,String pathKey,String value){
          PEGACases pega= new PEGACases();
	try{	
		switch(fieldType){

		case "Textbox":
			if(value.equals("")){
				System.out.println("field has not been set since the value is blank");
			}
			else if(WebUtil.isElementClickable(driver, pathKey))
			{
				WebUtil.clearWebEdit(pathKey);	
				WebUtil.wait(5000);
				WebUtil.setWebEdit(pathKey, value);
				WebUtil.click("PEGA.xpath.CaseTabTitle");
			}
			else
			{
				pega.closeTheCaseAndStartAgain(driver,PegaRegression_RunTestSuit.caseId);
			}
			break;

		case "Link":
			WebUtil.click(pathKey);
			break;

		case "Dropdown":
			if(value.equals("")){
				System.out.println("field has not been set since the value is blank");
			}else{
				WebUtil.selectValueFromDropDownAsPerPassedVaule(driver,pathKey, value);
			}
			break;

		case"Dropdown_VT":
			if(value.equals("")){
				System.out.println("field has not been set since the value is blank");
			}else{
				WebUtil.selectValueFromDropDownByVisibleText(driver,pathKey, value);
			}
			break;
			
		case"Dropdown_In":
			WebUtil.selectValueFromDropDownAsPerPassedIndex(driver, pathKey, 1);
			break;	

		default:
			log.error("performActionByFieldType Method-Field Type is either blank/null or does not match the defined cases");
			break;	
		}
	}catch(Exception e){
		log.error("cant perform action by field type: "+e.getMessage());
		ReportUtil.reportElementException(e.getMessage(), LogAs.FAILED);
	}	
	}


	public void selectDocInDocReq(WebDriver driver,String docId){
		WebElement elem = driver.findElement(By.id("pySelected"+docId));
		elem.click();
	}

	public void createCustomOutreachRequest(WebDriver driver,String outreachReqType,String outreachXpath,String docId,String comments){
		
	try{	
		if(outreachReqType==null){
			log.info("No outreach request");
		}
		else if (outreachReqType.equalsIgnoreCase("DocRequest")){				
			WebUtil.click(outreachXpath);
			WebUtil.wait(5000);
			WebUtil.click("PEGACpoReq.xpath.ClickReqType");
			WebUtil.wait(5000);
			WebUtil.click("PEGACpoReq.xpath.SelectDocReq");
			WebUtil.wait(5000);
			selectDocInDocReq(driver,docId);			
			WebUtil.wait(5000);
			WebUtil.click("PEGACpoReq.xpath.Comments");
			WebElement docReqComment= WebUtil.findElement("PEGACpoReq.xpath.Comments", driver);
			WebUtil.setWebEdit(docReqComment,comments);
			WebUtil.wait(5000);
			WebUtil.click("PEGACpoReq.xpath.ClickAnywhere");
			WebUtil.wait(5000);
			WebUtil.click("PEGACpoReq.xpath.AddButton");
			WebUtil.wait(10000);
		}
		else if(outreachReqType.equalsIgnoreCase("InfoRequest")){
			WebUtil.click(outreachXpath);
			WebUtil.wait(5000);
			WebUtil.click("PEGACpoReq.xpath.ClickReqType");
			WebUtil.wait(5000);
			WebUtil.click("PEGACpoReq.xpath.SelectInfoReq");
			WebUtil.wait(5000);
			WebElement infoReqComment= WebUtil.findElement("PEGACpoReq.xpath.Comments", driver);
			WebUtil.setWebEdit(infoReqComment, comments);
			WebUtil.wait(5000);
			WebUtil.click("PEGACpoReq.xpath.ClickAnywhere");
			WebUtil.wait(5000);
			WebUtil.click("PEGACpoReq.xpath.AddButton");
			WebUtil.wait(10000);
		}
	}catch(Exception e){
		WebElement close=WebUtil.findElement("PEGACases.xpath.CloseModalOutreach", driver);
		close.click();
		log.error("issue with Outreach request"+outreachXpath+e.getMessage());
		e.printStackTrace();
	}	
	}

	public void addComments(WebDriver driver,String icXpath,String icsubType,String commentType,String comments){

	try{	
		if(commentType==null){
			log.info("No comments required for IC");
		}
		else if (commentType.equalsIgnoreCase("Internal")){
			WebUtil.click(icXpath);
			WebUtil.wait(5000);
			WebElement internalComm= WebUtil.findElement("PEGACpoReq.xpath.Comments", driver);
			WebUtil.setWebEdit(internalComm, comments);
			WebUtil.wait(5000);
			WebUtil.click("PEGACases.xpath.SubmitIC");
			WebUtil.wait(5000);
		}
		else if (commentType.equalsIgnoreCase("Bank")){
			WebUtil.click(icXpath);
			WebUtil.wait(5000);
			WebUtil.click("PEGACases.id.BankRadioButton");
			WebUtil.wait(5000);

			if (icsubType.trim().equalsIgnoreCase("Exception")){
				WebUtil.click("PEGACases.xpath.BankSubType");	
				WebUtil.selectValueFromDropDownAsPerPassedVaule(driver, "PEGACases.xpath.BankSubType","Exception");	
			}
			else if(icsubType.trim().equalsIgnoreCase("Note")){
				WebUtil.click("PEGACases.xpath.BankSubType");	
				WebUtil.selectValueFromDropDownAsPerPassedVaule(driver, "PEGACases.xpath.BankSubType","Note");	
			}
			WebUtil.wait(5000);
			WebElement comment_Internal= WebUtil.findElement("PEGACpoReq.xpath.Comments", driver);
			WebUtil.setWebEdit(comment_Internal, comments);
			WebUtil.wait(5000);
			WebUtil.click("PEGACases.xpath.SubmitIC");
			WebUtil.wait(5000);
		}	
		
	}catch(Exception e){
		WebElement closeIc=WebUtil.findElement("PEGACases.xpath.CloseModalOutreach", driver);
		closeIc.click();
		log.error("issue with internal comments"+icXpath+e.getMessage());
		e.printStackTrace();
	}	
		/*		else if(commentType.equalsIgnoreCase("Bank")){		
			WebUtil.click("PEGACases.id.PolicyClarRadioButton");
			WebUtil.setWebEdit("PEGACases.id.PolicyClarQuestion", InptVal)
	}*/
	}

	public void attachDocToAttribute(WebDriver driver,String attachDocXpath,String docId){		
	try{	
		if(docId==null){
			log.info("Attach document to attribute is Not required");
		}
		else{
		WebUtil.click(attachDocXpath);
		WebUtil.wait(5000);
		selectDocInDocReq(driver,docId);
		WebUtil.wait(2000);
		WebUtil.click("PEGACases.xpath.SaveAttachDocAttribute");
		WebUtil.wait(5000);		
		}
	}catch(Exception e){
		log.error("issue with attaching doc to attribute"+attachDocXpath+e.getMessage());
		e.printStackTrace();
	}
	}
	
	public WebDriver switchDynamicFrames(WebDriver driver){
		driver.switchTo().defaultContent();
	    List<WebElement> iframe=driver.findElements(By.tagName("iframe"));
	    int iframeNo=iframe.size()-1;
	    
	    for(int i=0;i<=iframeNo;i++)
	    System.out.println(iframe.get(i));
	    
		String id=iframe.get(iframeNo).getAttribute("id");
		WebElement e=driver.findElement(By.id(id));
		
		driver.switchTo().frame(e);
		return driver;
	}
	
	public void acceptTasksRequests_withNavigation(WebDriver driver,String pathKeyTasksRaised,String acceptPathKey)
	{
		List<WebElement> e=WebUtil.findElements("PEGACases.xpath.AttributePerTab", driver);	
		for(int i=0;i<e.size();i++){
		e=WebUtil.findElements("PEGACases.xpath.AttributePerTab", driver);	
		String text=e.get(i).getAttribute("text");
		if(text.equals(null)|text.equals("null") ){			
		}else{
			e.get(i).click();
			WebUtil.wait(4000);
			acceptTasksRequests_withoutNavigation(driver,pathKeyTasksRaised,acceptPathKey);		
			acceptTasksRequests_ViewButton(driver, pathKeyTasksRaised,acceptPathKey);
		}	
		}
	}
	
	public void acceptAttributesAndTasks_withNavigation(WebDriver driver,String pathKeyTasksRaised,String acceptPathKey)
	{
		List<WebElement> e=WebUtil.findElements("PEGACases.xpath.AttributePerTab", driver);	
		for(int i=1;i<e.size();i++){
		e=WebUtil.findElements("PEGACases.xpath.AttributePerTab", driver);	
		String text=e.get(i).getAttribute("text");
		if(text.equals(null)|text.equals("null") ){			
		}else{
			e.get(i).click();
			WebUtil.wait(4000);
			acceptAttributes(driver, "PEGACase.xpath.Accept");	
			acceptTasksRequests_withoutNavigation(driver,pathKeyTasksRaised,acceptPathKey);
			acceptAttributesAndTaskRequests_ViewButton(driver, pathKeyTasksRaised,acceptPathKey);
		}
		}
	}
	
	public void acceptAttributesAndTasks(WebDriver driver,String pathKeyTasksRaised,String acceptPathKey)
	{
		List<WebElement> e=WebUtil.findElements("PEGACases.xpath.AttributePerTab", driver);	
		for(int i=1;i<e.size();i++){
		e=WebUtil.findElements("PEGACases.xpath.AttributePerTab", driver);	
		String text=e.get(i).getAttribute("text");
		if(text.equals(null)|text.equals("null") ){			
		}else{
			e.get(i).click();
			WebUtil.wait(2000);
			acceptAttributesAndTaskRequests_ViewButton(driver, pathKeyTasksRaised,acceptPathKey);
		}
		}
	}
	
	public void acceptAttributes_withNavigation_AcceptHyperLink(WebDriver driver)
	{
		List<WebElement> e=WebUtil.findElements("PEGACases.xpath.AttributePerTab", driver);	
		for(int i=0;i<e.size();i++){
		e=WebUtil.findElements("PEGACases.xpath.AttributePerTab", driver);	
		String text=e.get(i).getAttribute("text");
		if(text.equals(null)|text.equals("null") ){			
		}else{
			e.get(i).click();
			WebUtil.wait(4000);
			acceptAttributes(driver, "PEGACase.xpath.Accept");	
			acceptAttributes_ViewButton_AcceptHyperLink(driver);
		}
		}	
	}
	
	
	public void acceptTasksRequests_withoutNavigation(WebDriver driver,String pathKeyTasksRaised,String acceptPathKey){
		//List<WebElement> acceptList=new ArrayList<WebElement>();
		int verifyTaskRaised=0;
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);  
		try
		{
		verifyTaskRaised=WebUtil.returnSizeOfList(pathKeyTasksRaised);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
		System.out.println("Size of acc "+verifyTaskRaised);
		for(int j=0;j<verifyTaskRaised;j++)
		{
			//acceptList=WebUtil.findElements(acceptPathKey, driver);
			//WebUtil.clickAt(acceptList.get(j));
			retryingElementClickForTasks(driver,acceptPathKey);
			WebUtil.wait(4000);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
		}
	}
	
	public void acceptAttributesAndTaskRequests_ViewButton(WebDriver driver, String pathKeyTasksRaised,String acceptPathKey){
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);  
		try{
		List<WebElement> ViewButns=WebUtil.findElements("PEGACase.xpath.View", driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		if(ViewButns.size()>0){
		for (int i=0; i<=ViewButns.size()-1; i++){
			//ViewButns=WebUtil.findElements("PEGACase.xpath.View", driver);
			//ViewButns.get(i).click();
			retryingElementClick(driver,"PEGACase.xpath.View",i);
			WebUtil.wait(4000);
			acceptAttributes(driver, "PEGACase.xpath.Accept");
			acceptTasksRequests_withoutNavigation(driver,pathKeyTasksRaised,acceptPathKey);
		}	
	   }
	}catch(Exception e){
		e.printStackTrace();
	}
	}
		
	public void acceptAttributes_ViewButton_AcceptHyperLink(WebDriver driver){
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);  
		try{
		List<WebElement> ViewBtns=WebUtil.findElements("PEGACase.xpath.View", driver);	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		if(ViewBtns.size()>0){
		for (int i=0; i<=ViewBtns.size()-1; i++){
			//ViewBtns=WebUtil.findElements("PEGACase.xpath.View", driver);
			//ViewBtns.get(i).click();
			retryingElementClick(driver,"PEGACase.xpath.View",i);
			WebUtil.wait(4000);
			acceptAttributes(driver, "PEGACase.xpath.Accept");
		}	
	   }
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	}
	
	public void acceptTasksRequests_ViewButton(WebDriver driver, String pathKeyTasksRaised,String acceptPathKey){
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);  
		try{
		List<WebElement> ViewButtons=WebUtil.findElements("PEGACase.xpath.View", driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		if(ViewButtons.size()>0){
		for (int i=0; i<=ViewButtons.size()-1; i++){
			//ViewButtons=WebUtil.findElements("PEGACase.xpath.View", driver);
			//ViewButtons.get(i).click();
			retryingElementClick(driver,"PEGACase.xpath.View",i);
			WebUtil.wait(4000);
			acceptTasksRequests_withoutNavigation(driver,pathKeyTasksRaised,acceptPathKey);
		}		
	  }
	}catch(Exception e){
		e.printStackTrace();
	}
	}	
	
	public boolean retryingElementClick( WebDriver driver,String xpathKey,int i) {
        boolean result = false;
        int attempts = 0;
        while(attempts <= 2) {
            try {
            	List<WebElement> elem=WebUtil.findElements(xpathKey, driver);
            	WebUtil.wait(3000);
                elem.get(i).click();
                result = true;
                break;
            } catch(StaleElementReferenceException ex) {
            }
            attempts++;
        }
        return result;
}
	
	public boolean retryingElementClickForTasks( WebDriver driver,String xpathKey) {
        boolean result = false;
        int attempts = 0;
        while(attempts <= 2) {
            try {
            	List<WebElement> elem=WebUtil.findElements(xpathKey, driver);
            	WebUtil.wait(3000);
                elem.get(0).click();
                result = true;
                break;
            } catch(StaleElementReferenceException ex) {
            }
            attempts++;
        }
        return result;
}
	
	public void closeTheCaseAndStartAgain(WebDriver driver,String caseId)
	{
		PEGARegTC pegaTc= new PEGARegTC();
		driver=switchDynamicFrames(driver);
		String tabName= WebUtil.readElementText(driver, "PEGA.xpath.CurrentTab");
		System.out.println("The tab name is: "+tabName);
		driver=switchDynamicFrames(driver);
		WebUtil.click("PEGACase.xpath.SendAction");
		WebUtil.wait(2000);
		driver=switchDynamicFrames(driver);
		WebUtil.click("PEGACase.xpath.CloseTheCase");
		WebUtil.wait(2000);
		driver=switchDynamicFrames(driver);
		WebUtil.click("PEGACase.xpath.CloseOption");
		WebUtil.wait(7000);
		driver=switchDynamicFrames(driver);
		pegaTc.tc_caseStart_bycaseId(driver,caseId);
		WebElement ele=driver.findElement(By.xpath("//table[@id='bodyTbl_right']//div[@class='field-item dataValueRead']//span[contains(text(),'"+tabName.trim()+"')]"));;
		if(ele.isDisplayed())
		WebUtil.actionClass(ele, driver);
	}
}
