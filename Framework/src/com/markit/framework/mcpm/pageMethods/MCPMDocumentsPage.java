package com.markit.framework.mcpm.pageMethods;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.markit.common.framework.util.DBUtility;
import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;

public class MCPMDocumentsPage {
	static String documentId=null;


	public void addDocument(WebDriver driver,String entityName, String docType,String docDescription)
	{	
		WebElement ele=null;
		WebUtil.click("MCPMDoc.xpath.AddDocument");
		/*WebUtil.setWebEdit("MCPMDoc.name.EntityName", entityName);*/
		WebUtil.wait(2000);
		try
		{

			WebUtil.click("MCPMDoc.xpath.SearchEntityIcon");
			WebUtil.setWebEdit("MCPMDoc.name.EntityNameInput", entityName);
			WebUtil.wait(2000);
			WebUtil.findElement("MCPMDoc.name.EntityNameInput", driver).sendKeys(Keys.ENTER);
			WebUtil.click("MCPMDoc.xpath.SelectEntityName");
			WebUtil.wait(2000);
			WebUtil.click("MCPMDoc.xpath.OkEntity");
			WebUtil.wait(2000);

		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
		}
		WebUtil.wait(2000);

		/*String docCategory=ExcelUtil.getColumnDataByColName(wbPath,sheetName,"DocCat",iRowNum);
		WebUtil.setWebEdit("MCPMDoc.name.DocCategory", docCategory);
		ele=driver.findElement(By.xpath("//div[@class='list-ct']//ul/li[contains(text(),'"+docCategory+"')]"));
	    ele.click();
		WebUtil.wait(2000);*/

		WebUtil.setWebEdit("MCPMDoc.name.DocType", docType);
		ele=driver.findElement(By.xpath("//div[@class='list-ct']//ul/li[contains(text(),'"+docType+"')]"));
		ele.click();
		WebUtil.wait(2000);		
		WebUtil.setWebEdit("MCPMDoc.name.DocDescription", docDescription);
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.DocBrowse");
		WebUtil.uploadDocumentAsperPassedBrowserName(driver, "Chrome");	
		WebUtil.wait(7000);
		String shareInternally=WebUtil.getAttributeValue(driver, "MCPMDoc.id.ShareInternally", "class");
		WebUtil.wait(2000);
		if(shareInternally.equalsIgnoreCase("x-field x-form-item x-field-default"))
		{
			WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.ShareInternallyCheckBox");
		}
		WebUtil.wait(2000);
		WebUtil.click("MCPMDoc.xpath.Save");
		WebUtil.wait(9000);
		WebUtil.click("MCPMDoc.xpath.Ok");
		WebUtil.wait(3000);
		WebUtil.click("MCPMDoc.xpath.Exit");
		WebUtil.wait(5000);

	}


	public String dbValidationForDocId(Connection con,String userName)
	{
		String companyId=null;

		if(con!=null)
		{
			companyId=DBUtility.getColData(con,"select companyid from mc_users where username='"+userName+"' and deleted=0","companyid");
			documentId=DBUtility.getColData(con,"select top 1 docid from mc_unidoc where companyid='"+companyId+"'and deleted=0 order by creation_date desc","docid");
		}
		return documentId;		
	}

	public String dbKYCValidationforDocId(Connection con,String mcpmDocId)
	{

		String docId=null;
		if(con!=null)
		{		
			docId=DBUtility.getColData(con,"select doc_id from (select  * from doc where external_doc_id='"+mcpmDocId+"' order by created_dt desc) where rownum<=1","DOC_ID");
		}
		return docId;		
	}

	public String dbValidationForMCPMDocID(Connection con,String kycDocID)
	{

		String docId=null;
		if(con!=null)
		{		
			docId=DBUtility.getColData(con,"select external_doc_id from doc where doc_id='"+kycDocID+"'","external_doc_id");
		}
		return docId;		
	}

	public void setDocumentIDfilter(WebDriver driver, String docId)
	{
		WebUtil.setWebEdit("MCPMDoc.name.DocIDFilter", docId);
		WebUtil.wait(3000);
		WebUtil.click("MCPMDOC.xpath.ApplyFilter");
		WebUtil.wait(3000);
	}

	public void downloadMCPMDoc(WebDriver driver)
	{
		WebUtil.click("MCPMDoc.xpath.DownloadDoc");
		WebUtil.wait(3000);
	}



	public void selectFirstCheckBox()
	{
		WebUtil.click("MCPMDOC.xpath.DocCheckBox");
	}
	public void selectAllCheckBox()
	{
		WebUtil.click("MCPMDoc.xpath.SelectAll");
	}

	public String editDocType(WebDriver driver,String editDoc)
	{
		WebElement ele=null;
		selectFirstCheckBox();
		WebUtil.click("MCPMDOC.xpath.EditButton");
		WebUtil.wait(7000);
		WebUtil.setWebEdit("MCPMDoc.name.DocType", editDoc);

		WebUtil.wait(2000);
		WebUtil.click("MCPMDoc.xpath.Save");
		WebUtil.wait(5000);
		WebUtil.click("MCPMDoc.xpath.Ok");
		WebUtil.wait(3000);
		WebUtil.click("MCPMDoc.xpath.Exit");
		WebUtil.wait(3000);
		return editDoc;

	}

	public void deleteDocument(WebDriver driver)
	{
		selectFirstCheckBox();
		WebUtil.click("MCPMDOC.xpath.Delete");
		WebUtil.wait(3000);
		WebUtil.click("MCPMDOC.xpath.YesDelete");
		WebUtil.wait(5000);

	}
	public void addDocWithMultipleEnties(WebDriver driver,int entiyNumber)
	{
		WebElement ele=null;
		System.out.println();
		String wbPath="src/testdata/TestData_EntityDoc.xlsx";
		String sheetNameDoc="Document";
		String sheetNameEntity="Entity";
		String entityName=null;	
		String docType=ExcelUtil.getColumnDataByColName(wbPath,sheetNameDoc,"DocType",1);
		String docDescription=ExcelUtil.getColumnDataByColName(wbPath,sheetNameDoc,"Description",1);	
		WebUtil.wait(4000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.AddDocument");
		WebUtil.wait(2000);
		WebUtil.click("MCPMDoc.xpath.SearchEntityIcon");
		for(int i=2;i<=entiyNumber;i++)
		{ 	   
			entityName=ExcelUtil.getColumnDataByColName(wbPath,sheetNameEntity,"TrueLegalName",i);		
			WebUtil.setWebEdit("MCPMDoc.name.EntityNameInput", entityName);
			WebUtil.wait(2000);
			WebUtil.click("MCPMDoc.xpath.SelectEntityName");
		}

		WebUtil.click("MCPMDoc.xpath.OkEntity");
		WebUtil.wait(2000);		

		WebUtil.setWebEdit("MCPMDoc.name.DocType", docType);
		ele=driver.findElement(By.xpath("//div[@class='list-ct']//ul/li[contains(text(),'"+docType+"')]"));
		ele.click();
		WebUtil.wait(2000);		
		WebUtil.setWebEdit("MCPMDoc.name.DocDescription", docDescription);
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.DocBrowse");
		WebUtil.uploadDocumentAsperPassedBrowserName(driver, "Chrome");	
		WebUtil.wait(5000);
		WebUtil.click("MCPMDoc.xpath.Save");
		WebUtil.wait(7000);
		WebUtil.click("MCPMDoc.xpath.Ok");
		WebUtil.wait(3000);
		WebUtil.click("MCPMDoc.xpath.Exit");
		WebUtil.wait(3000);

	}

	public void editADocToUntagAnEntity(WebDriver driver,String entityName)
	{
		WebUtil.click("MCPMDOC.xpath.EditButton");
		WebUtil.wait(5000);
		WebUtil.click("MCPMDoc.xpath.SearchEntityIcon");
		WebUtil.wait(2000);		
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.ClearButton");
		System.out.println(entityName);
		WebUtil.setWebEdit("MCPMDoc.name.EntityNameInput", entityName);
		WebUtil.wait(2000);
		WebUtil.click("MCPMDoc.xpath.SelectEntityName");
		WebUtil.click("MCPMDoc.xpath.OkEntity");
		WebUtil.wait(2000);		
		WebUtil.click("MCPMDoc.xpath.Save");
		WebUtil.wait(7000);
		WebUtil.click("MCPMDoc.xpath.Ok");
		WebUtil.wait(3000);
		WebUtil.click("MCPMDoc.xpath.Exit");
		WebUtil.wait(3000);

	}	

	public void clearAppliedFilter(WebDriver driver)
	{
		WebUtil.click("MCPM.xpath.ClearFilter");
		WebUtil.click("MCPMDOC.xpath.ApplyFilter");
		WebUtil.wait(4000);
	}
	public void selectPassedNumberofCheckBox(WebDriver driver,int count)
	{
		
		List<WebElement> chkbox=driver.findElements(By.xpath("//table[@class='x-grid-table x-grid-table-resizer']//div[@class='x-grid-row-checker']"));
		for(int i=0;i<=count-1;i++){
			chkbox=driver.findElements(By.xpath("//table[@class='x-grid-table x-grid-table-resizer']//div[@class='x-grid-row-checker']"));
			chkbox.get(i).click();
		}
		WebUtil.wait(5000);
	}

	public void permissonDocSelectAll(WebDriver driver,String dgName)
	{
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.Permisson");
		WebUtil.wait(3000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.YesPermission");
		WebElement ele=driver.findElement(By.xpath("//div[@id='mde.appDocumentPermissionDialog']//table//tr//td//div[contains(text(),'"+dgName+"')]"));
		WebUtil.actionClass(ele, driver);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.GrantPermisson");
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.YesPermission");
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.NoteOk");
		WebUtil.wait(3000);
	}

	public void permissonDoc(WebDriver driver,String dgName)
	{
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.Permisson");
		WebUtil.wait(2000);
		//WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.YesToPermission");
		WebElement ele=driver.findElement(By.xpath("//div[@id='mde.appDocumentPermissionDialog']//table//tr//td//div[contains(text(),'"+dgName+"')]"));
		WebUtil.actionClass(ele, driver);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.GrantPermisson");
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.YesPermission");
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.NoteOk");
		WebUtil.wait(5000);
	}

	public void setEntityFilter(String entityName)
	{
		WebUtil.setWebEdit("MCPMDoc.name.EntityName", entityName);
		WebUtil.wait(3000);
		WebUtil.click("MCPMDOC.xpath.ApplyFilter");
		WebUtil.wait(3000);
	}

	public void unPermissonAllDocs(WebDriver driver)
	{
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.Permisson");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.YesPermission");
		WebUtil.wait(2000);
		List <WebElement> chkbx=WebUtil.findElements("MCPMPerm.xpath.SelectAll", driver);
		chkbx.get(0).click();
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.RemovePermission");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.YesPermission");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.NoteOk");
		WebUtil.wait(2000);
	}

	public void sendEmailDG(WebDriver driver,String searchDG){

		WebUtil.javaExecuter(driver, "Doc.xpath.EmailButton");
		//WebUtil.clickedWithAction(driver, "Doc.xpath.EmailButton");
		WebUtil.wait(5000);
		int i=WebUtil.getSizeOfElement(driver, "MCPM.xpath.YesPermission");
		if(i!=0)
		{
		WebUtil.clickedWithAction(driver, "MCPM.xpath.YesPermission");
		}
		WebUtil.wait(5000);
		WebUtil.click("EmailDoc.xpath.To");
		WebUtil.setWebEdit("EmailDoc.xpath.TypeDistInp", "Distribution");
		WebUtil.wait(2000);
		WebUtil.click("EmailDoc.xpath.selectType");
		WebUtil.wait(2000);
		WebUtil.setWebEdit("EmailDoc.xpath.EnterSearchName", searchDG);
		WebUtil.sendEnterKeyWebEdit(driver,"EmailDoc.xpath.EnterSearchName");
		WebUtil.wait(2000);
		emailSearchContactGrid(driver,1,"EmailDoc.xpath.SelectRecord",searchDG);
		WebUtil.wait(2000);
		WebUtil.click("EmailDoc.xpath.Okbutton");
		WebUtil.wait(2000);
		WebUtil.click("EmailDoc.xpath.Sendbutton");
		WebUtil.wait(2000);
		WebUtil.click("EmailDoc.xpath.SentOK");
		WebUtil.wait(2000);
	}

	public void sendEmailDGSelectAllDoc(WebDriver driver,String searchDG){

		WebUtil.javaExecuter(driver, "Doc.xpath.EmailButton");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.YesPermission");
		WebUtil.click("EmailDoc.xpath.To");
		WebUtil.setWebEdit("EmailDoc.xpath.TypeDistInp", "Distribution");
		WebUtil.wait(3000);
		WebUtil.click("EmailDoc.xpath.selectType");
		WebUtil.wait(2000);
		WebUtil.setWebEdit("EmailDoc.xpath.EnterSearchName", searchDG);
		WebUtil.sendEnterKeyWebEdit(driver,"EmailDoc.xpath.EnterSearchName");
		WebUtil.wait(2000);
		emailSearchContactGrid(driver,1,"EmailDoc.xpath.SelectRecord",searchDG);
		WebUtil.wait(2000);
		WebUtil.click("EmailDoc.xpath.Okbutton");
		WebUtil.wait(2000);
		WebUtil.click("EmailDoc.xpath.Sendbutton");
		WebUtil.wait(2000);
		WebUtil.click("EmailDoc.xpath.SentOK");
		WebUtil.wait(2000);
	}
	
	public int getTheDocCountFromManageDoc(WebDriver driver)
	{		
		String pagination=WebUtil.readElementText(driver, "MCPMDoc.xpath.CPCount");
		int i=pagination.indexOf("of");
		String count=pagination.substring(i+3);
		System.out.println(count);
		//ExcelUtil.setColumnDataByColName("src/testdata/TestData_EntityDoc.xlsx", "CP", "CountOfDoc", 2, count);
		ReportUtil.reportInputValueOnly("The document count in the Grid", count, true);
		return Integer.parseInt(count);
	}	

	public static void emailSearchContactGrid(WebDriver driver,
			int colIndex, String WebTableXpathKey, String searchText) {
		WebElement table = WebUtil.findElement(WebTableXpathKey, driver);
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		String tab = XPath.myprop.get(WebTableXpathKey);
		int rowsize = rows.size();
		for (int j = 2; j <= rowsize; j++) {
			WebElement rowValue = driver.findElement(By.xpath(tab
					+ "/tr[" + j + "]/td["+ colIndex +"]/div[contains(text(),'"+searchText+"')]"));

			Actions actions = new Actions(driver);
			actions.moveToElement(rowValue).doubleClick().build().perform();
			String sCellValue = rowValue.getText();
			System.out.println(sCellValue);
		}
	}
}
