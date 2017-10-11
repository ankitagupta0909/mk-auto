package com.markit.framework.mcpm.pageMethods;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import atu.testng.reports.logging.LogAs;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;


public class EntityPage {

	public WebDriver fillEntityData(WebDriver driver,ArrayList<String> values) 
	{
		WebElement entityNameWebElement=WebUtil.findElement("Entity.xpath.LegalName",driver);
		WebUtil.wait(2000);
		WebUtil.setWebEdit(entityNameWebElement,values.get(0));
		String entityName=WebUtil.readWebElement("WebEdit",entityNameWebElement);
		ReportUtil.reportWebEditRead( "Entered Enity Name",entityNameWebElement, values.get(0), entityName);

		WebElement displayNameWebElement=WebUtil.findElement("Entity.xpath.DisplayName",driver);
		WebUtil.setWebEdit(displayNameWebElement,values.get(1));
		String display=WebUtil.readWebElement("WebEdit",displayNameWebElement);
		ReportUtil.reportWebEditRead( "Entered display name",displayNameWebElement, values.get(1), display);

		WebElement displayNameOption=driver.findElement(By.xpath("//li[contains(text(),'"+values.get(1)+"')]"));

		WebUtil.actionClass(displayNameOption, driver);


		WebElement country=WebUtil.findElement("Entity.xpath.Country",driver);
		WebUtil.setWebEdit(country,values.get(2));
		String countryName=WebUtil.readWebElement("WebEdit",country);
		ReportUtil.reportWebEditRead( "Entered country name",country, values.get(2), countryName);
		WebUtil.wait(5000);
		WebElement countryNameOption=driver.findElement(By.xpath("//li[contains(text(),'"+values.get(2)+"')]"));
		WebUtil.actionClass(countryNameOption, driver);

		WebElement entityTypeWebElement=WebUtil.findElement("Entity.xpath.EntityType",driver);
		WebUtil.setWebEdit(entityTypeWebElement,values.get(3));
		String entityTypename=WebUtil.readWebElement("WebEdit",entityTypeWebElement);
		ReportUtil.reportWebEditRead( "Entered Entity Type name",entityTypeWebElement, values.get(3), entityTypename);

		WebUtil.wait(5000);
		WebElement entityTypeOption=driver.findElement(By.xpath("//li[contains(text(),'"+values.get(3)+"')]"));

		WebUtil.actionClass(entityTypeOption, driver);

		WebUtil.wait(2000);
		if(values.get(4).length()!=0)
		{
			WebElement subType=WebUtil.findElement("Entity.xpath.EntitySubType",driver);
			WebUtil.setWebEdit(subType,values.get(4));
			String entitySubTypename=WebUtil.readWebElement("WebEdit",subType);
			ReportUtil.reportWebEditRead( "Entered Entity Type name",subType, values.get(4), entitySubTypename);
			WebUtil.wait(5000);
			WebElement subTypeOption=driver.findElement(By.xpath("//li[contains(text(),'"+values.get(4)+"')]"));
			WebUtil.actionClass(subTypeOption, driver);

		}
		/*WebElement entityLEI=WebUtil.findElement("Entity.xpath.LEI",driver);
		WebUtil.setWebEdit(entityLEI,values.get(5));
		Thread.sleep(500);
		WebElement entityCI=WebUtil.findElement("Entity.xpath.CI",driver);
		WebUtil.setWebEdit(entityCI,values.get(6));
		Thread.sleep(500);
		WebElement entityMEI=WebUtil.findElement("Entity.xpath.MEI",driver);
		WebUtil.setWebEdit(entityMEI,values.get(7));*/	
		return driver;		
	}

	public String createEntity(WebDriver driver,String wbPath, String sheetName,int iRowNum){

		String legalName="AutoLegal"+WebUtil.generateRandomString(true, true, 7);
		String ci=legalName+"_CI";
		ExcelUtil.setColumnDataByColName(wbPath, sheetName, "TrueLegalName", iRowNum, legalName);
		ExcelUtil.setColumnDataByColName(wbPath, sheetName, "CI", iRowNum, ci);

		String country=ExcelUtil.getColumnDataByColName(wbPath,sheetName,"Country",iRowNum);
		String dispName=ExcelUtil.getColumnDataByColName(wbPath,sheetName,"DisplayName",iRowNum);
		String entType=ExcelUtil.getColumnDataByColName(wbPath,sheetName,"EntityType",iRowNum);
		String entSubType=ExcelUtil.getColumnDataByColName(wbPath,sheetName,"EntitySubType",iRowNum);
		
		try{
		WebUtil.setWebEdit("Entity.name.LegalName", legalName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		WebUtil.waitUntilElementClickable(driver, "Entity.name.CI");
		WebUtil.setWebEdit("Entity.name.CI", ci);

		String list_part1=WebUtil.returnXPathVal("Entity.xpath.list1");
		String list_part2=WebUtil.returnXPathVal("Entity.xpath.list2");
		WebUtil.wait(3000);
		WebUtil.waitUntilElementClickable(driver, "Entity.name.DisplayName");
		WebUtil.setWebEdit("Entity.name.DisplayName", dispName);
		WebElement displayNameOption=driver.findElement(By.xpath(list_part1+dispName+list_part2));
		WebUtil.actionClass(displayNameOption, driver);	
		WebUtil.wait(3000);
		WebUtil.waitUntilElementClickable(driver, "Entity.name.Country");
		WebUtil.setWebEdit("Entity.name.Country", country);
		WebUtil.wait(2000);
		WebElement countryNameOption=driver.findElement(By.xpath(list_part1+country+list_part2));
		WebUtil.actionClass(countryNameOption, driver);
		WebUtil.waitUntilElementClickable(driver, "Entity.name.EntityType");
		WebUtil.setWebEdit("Entity.name.EntityType", entType);
		WebElement entityTypeOption=driver.findElement(By.xpath(list_part1+entType+list_part2));		
		WebUtil.actionClass(entityTypeOption, driver);
		if(entType.equalsIgnoreCase("Fund"))
		{
			WebUtil.wait(3000);
			WebUtil.setWebEdit("Entity.name.EntitySubType", entSubType);
		
		}
		return legalName;

	}

	public void clickEditEntity(WebDriver driver){
		WebUtil.click("Entity.xpath.SelectChkbox");
		WebUtil.wait(3000);
		WebUtil.click("Entity.xpath.EditEntity");
		WebUtil.waitUntilElementClickable(driver, "Entity.name.LegalName");
		WebUtil.wait(5000);
		NavigationPage.setMcpmFrame(driver);
	}

	public String editEntityTypeEligibleKYC(WebDriver driver,String wbPath, String sheetName,int iRowNum){
		String list_part1=WebUtil.returnXPathVal("Entity.xpath.list1");
		String list_part2=WebUtil.returnXPathVal("Entity.xpath.list2");
		String entType=ExcelUtil.getColumnDataByColName(wbPath,sheetName,"EditValidEntityTypeKYC",iRowNum);
		WebUtil.setWebEdit("Entity.name.EntityType", entType);
		WebElement entityTypeOption=driver.findElement(By.xpath(list_part1+entType+list_part2));		
		WebUtil.actionClass(entityTypeOption, driver);
		return entType;
	}

	public String editEntityTypeNonKYC(WebDriver driver,String wbPath, String sheetName,int iRowNum){
		String list_part1=WebUtil.returnXPathVal("Entity.xpath.list1");
		String list_part2=WebUtil.returnXPathVal("Entity.xpath.list2");
		String entType=ExcelUtil.getColumnDataByColName(wbPath,sheetName,"EditInvalidEntityTypeKYC",iRowNum);
		WebUtil.setWebEdit("Entity.name.EntityType", entType);
		WebElement entityTypeOption=driver.findElement(By.xpath(list_part1+entType+list_part2));		
		WebUtil.actionClass(entityTypeOption, driver);	
		return entType;
	}

	public String editCountry(WebDriver driver,String wbPath, String sheetName,int iRowNum){
		String list_part1=WebUtil.returnXPathVal("Entity.xpath.list1");
		String list_part2=WebUtil.returnXPathVal("Entity.xpath.list2");
		String country=ExcelUtil.getColumnDataByColName(wbPath,sheetName,"EditCountry",iRowNum);
		WebUtil.setWebEdit("Entity.name.Country", country);
		WebElement countryNameOption=driver.findElement(By.xpath(list_part1+country+list_part2));
		WebUtil.actionClass(countryNameOption, driver);	
		return country;
	}

	public String editDisplayName(WebDriver driver,String wbPath, String sheetName,int iRowNum){
		String list_part1=WebUtil.returnXPathVal("Entity.xpath.list1");
		String list_part2=WebUtil.returnXPathVal("Entity.xpath.list2");
		String dispName=ExcelUtil.getColumnDataByColName(wbPath,sheetName,"EditDispName",iRowNum);		
		WebUtil.setWebEdit("Entity.name.DisplayName", dispName);
		WebElement displayNameOption=driver.findElement(By.xpath(list_part1+dispName+list_part2));
		WebUtil.actionClass(displayNameOption, driver);	
		return dispName;
	}


	public String editLegalName(WebDriver driver,String wbPath, String sheetName,int iRowNum){	
		String legalName=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "TrueLegalName", iRowNum);
		WebUtil.clearWebEdit("Entity.name.LegalName");
		WebUtil.setWebEdit("Entity.name.LegalName",legalName+"_edit");
		ExcelUtil.setColumnDataByColName(wbPath, sheetName, "TrueLegalName", iRowNum, legalName+"_edit");
		return legalName+"_edit";
	}


	public String editCI(WebDriver driver,String wbPath, String sheetName,int iRowNum){		
		String ci=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "CI", iRowNum); 
		WebUtil.clearWebEdit("Entity.name.CI");
		WebUtil.setWebEdit("Entity.name.CI", ci+"_edit");
		ExcelUtil.setColumnDataByColName(wbPath, sheetName, "CI", iRowNum, ci+"_edit");
		return ci+"_edit";
	}


	public WebDriver saveEntityDetails(WebDriver driver) 
	{
		WebElement save=WebUtil.findElement("Entity.xpath.Save",driver);
		WebUtil.clickAt(save);
		WebUtil.wait(5000);
		WebElement savePopUp=WebUtil.findElement("Entity.xpath.SavePopUP",driver);
		System.out.println(savePopUp.getText());

		ReportUtil.reportStringMatch("Verify Entity is saved", "Entity created/updated successfully", savePopUp.getText());
		WebUtil.wait(8000);
		WebElement ok=WebUtil.findElement("Entity.xpath.Ok",driver);
		WebUtil.actionClass(ok, driver);
		WebUtil.wait(5000);
		WebElement exit=WebUtil.findElement("Entity.xpath.Exit",driver);

		boolean actVal;
		actVal = WebUtil.verifyWebElementExists("WebElement", exit);
		WebUtil.clickAt(exit);
		ReportUtil.reportWebElement("Entity is created", actVal);
		System.out.println("Entity is created");
		return driver;		
	}
	
	public void saveDetails(WebDriver driver)
	{
		WebElement save=WebUtil.findElement("Entity.xpath.Save",driver);
		WebUtil.clickAt(save);
		WebUtil.wait(5000);
		WebElement savePopUp=WebUtil.findElement("Entity.xpath.SavePopUP",driver);
		System.out.println(savePopUp.getText());

		ReportUtil.reportStringMatch("Verify Entity is saved", "Entity created/updated successfully", savePopUp.getText());
		WebUtil.wait(8000);
		WebElement ok=WebUtil.findElement("Entity.xpath.Ok",driver);
		WebUtil.actionClass(ok, driver);
		WebUtil.wait(5000);
	}

	public void generateNameLEIforBulkUpload(ArrayList<String> eName, ArrayList<String> lei, int size){	
		int randNo=WebUtil.generateRandomNumber();
		for(int j=0;j<=size;j++){
			eName.add("AutoEnt"+j+"_"+randNo);
			String LEI = WebUtil.generateRandomString(false, true,16);
			lei.add("AUTO"+LEI);
		}
	}

	public void generateEntityTypeforBulkUpload(ArrayList<String> entityType,int size){
		for(int j=0;j<=size;j++){
			entityType.add("Government");
		}
	}

	public void bulkUploadEntityTemplateUpdate(String filePath,ArrayList<String> entityName,ArrayList<String> lei,ArrayList<String> entityType ){
		try {
			Workbook wb = ExcelUtil.getWorkBookObject(filePath);
			int rowNo=0;
			Row row=wb.getSheet("Sheet1").getRow(rowNo);
			int colNoEntName=ExcelUtil.getColumnNumberByColumnName("EntityName", row);
			int colNoLEI=ExcelUtil.getColumnNumberByColumnName("LEI", row);
			int colNoEntityType=ExcelUtil.getColumnNumberByColumnName("EntityType", row);
			int colNoLegalName=ExcelUtil.getColumnNumberByColumnName("TrueLegalName", row);
			ExcelUtil.setCellValueBulkUpload(rowNo, colNoEntName, wb, entityName);
			ExcelUtil.setCellValueBulkUpload(rowNo, colNoLegalName, wb, entityName);
			ExcelUtil.setCellValueBulkUpload(rowNo, colNoLEI, wb, lei);
			ExcelUtil.setCellValueBulkUpload(rowNo, colNoEntityType, wb, entityType);
			ExcelUtil.writeExcel(filePath, wb);
		}  catch (Exception e) {
			e.printStackTrace();
			ReportUtil.reportElementException("Write bulk upload Excel exception", LogAs.FAILED);
		} 
	}


	public void batchUploadEntity(WebDriver driver,String mappingName) 
	{		
		NavigationPage nav=new NavigationPage();		
		nav.navigateToBulkUploadOfEntity(driver);

		WebElement choose=WebUtil.findElement("MCPMRFA.xpath.Choose", driver);
		WebUtil.actionClass(choose, driver);
		String path1=System.getProperty("user.dir");
		String path2="/src/testdata/Templates/Entity_batch_Upload.xlsx";
		System.out.println("Test");
		String path3=path2.replace("/", "\\");
		String path=path1+path3;
		System.out.println(path);	
		WebUtil.wait(4000);
		String[] autoit={"src/drivers/RFAOnboardBulkUploadForChrome.exe",path};
		try {
			Runtime.getRuntime().exec(autoit);
		} catch (IOException e) {
			e.printStackTrace();
		}
		WebUtil.wait(10000);		
		WebUtil.clickedWithAction(driver, "MCPMRFA.xpath.LoadData");
		WebUtil.wait(4000);		
		WebElement map=driver.findElement(By.xpath("//div[@class='x-panel-body x-grid-body x-panel-body-default x-panel-body-default x-layout-fit']//div[contains(text(),'"+mappingName+"')]"));
		WebUtil.actionClass(map, driver);
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "MCPM.xpath.LoadMapping");
		WebUtil.wait(5000);
		WebUtil.clickedWithAction(driver, "MCPM.xpath.UpdateOnly");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "MCPMRFA.xpath.Upload");
		WebUtil.wait(3000);
		WebUtil.clickedWithAction(driver, "MCPMRFA.xpath.Alert");
		WebUtil.wait(2000);

	}

	public static List<String> entityFilterResults(WebDriver driver,
			int colIndex, String WebTableXpathKey) {
		WebElement table = WebUtil.findElement(WebTableXpathKey, driver);
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		String tab = XPath.myprop.get(WebTableXpathKey);
		List<String> cellValues = new ArrayList<String>();

		int rowsize = rows.size();
		colIndex=++colIndex;
		for (int j = 2; j <= rowsize; j++) {
			WebElement rowValue = driver.findElement(By.xpath(tab
					+ "/tr[" + j + "]/td[" + colIndex + "]/div"));
			String sCellValue = rowValue.getText();
			System.out.println(sCellValue);
			cellValues.add(sCellValue);
		}
		return cellValues;
	}

	public static void verifyFilterMatches(WebDriver driver,
			String WebTableXpathKey, String FilterLabel, int colIndex,
			String filinptval) {
		// Read the resultant web grid
		List<String> cellValues = entityFilterResults(driver, colIndex,
				WebTableXpathKey);
		if (cellValues != null) {
			Iterator<String> j = cellValues.iterator();
			while (j.hasNext()) {
				String coltxt = j.next();
				//ReportUtil.reportStringMatch("Verify on applying Filter "+FilterLabel+" correct value displayed in grid", filinptval, coltxt);
				ReportUtil.reportStringContainsMatch("Verify on applying Filter "+FilterLabel+" correct value displayed in grid", coltxt, filinptval);
			}
		}
	}

	public void setAndApplyFilter(WebDriver driver,String fieldValue,String filterPathKey){
		WebUtil.waitUntilElementClickable(driver, filterPathKey);		
		WebUtil.setWebEdit(filterPathKey, fieldValue);
		//WebUtil.waitUntilElementClickable(driver, "Entity.xpath.ApplyFilter");
		WebUtil.click("Entity.xpath.ApplyFilter");
		WebUtil.waitUntilElementClickable(driver, "Entity.xpath.WebTable");
		WebUtil.wait(7000);
	}

	public Map<String,Integer> getEntityColIndexMap(WebDriver driver){
		List<WebElement> list=WebUtil.findElements("Entity.xpath.WebTable", driver);
		Map<String,Integer> headerIndexMap= new HashMap<String,Integer>();		
		for (int i=1;i<=list.size()-1;i++){
			list=WebUtil.findElements("Entity.xpath.WebTable", driver);
			String elem=list.get(i).getText().toString();
			headerIndexMap.put(elem, i);
		}
		System.out.println(headerIndexMap);
		return headerIndexMap;
	}
	
	public void checkFCMCheckBox(WebDriver driver)
	{
		int size=WebUtil.getSizeOfElement(driver, "MCPM.xpath.FCMCheckBox");
		if(size!=0)
		{
			WebUtil.clickedWithAction(driver, "MCPM.xpath.FCMCheckBox");
		}
		else
		{
			ReportUtil.reportWebElement("Operation role is not avilable", false);
		}
	}
	
	public void clickOnRegulatoryChevron(WebDriver driver)
	{
		
		WebUtil.click("MCPMEntity.xpath.RegulatoryChevronTab");
		WebUtil.wait(7000);
	}
	
	public String fillLEI(WebDriver driver)
	{
		String lei=RandomStringUtils.randomAlphanumeric(20).toUpperCase();
		WebUtil.setWebEdit("RegulatoryChevron.xpath.LEI", lei);
		WebUtil.click("RegulatoryChevron.xpath.Defocus");
		WebUtil.wait(3000);
		WebUtil.click("RegulatoryChevron.xpath.SaveRegDetailsButton");
		WebUtil.wait(12000);
		WebUtil.click("RegulatoryChevron.xpath.SaveOkButton");
		WebUtil.wait(5000);
		return lei;
	}
	
	public void exitfromRehulatoryPage()
	{
		WebUtil.click("RegulatoryChevron.xpath.ExitRegPage");
		WebUtil.wait(4000);
	}
	
	public boolean checkAndClickCPEntityName(WebDriver driver,String cpName,String cpEntityName)
	{
		boolean flag=false;
		String entityname=null;
		WebElement ele=null;
		List<String> entityNameList=new ArrayList<String>();
		List<WebElement> rows=WebUtil.findElements("RegulatoryChevron.xpath.CPEntitiesNameRow", driver);
		String xpath=XPath.myprop.get("RegulatoryChevron.xpath.CPEntitiesNameRow");
		//System.out.println("Size of rows: "+rows.size());
		int index=0;
		for(int i=0;i<rows.size();i++)
		{
			index++;
			ele=driver.findElement(By.xpath(xpath+"["+index+"]//td//div"));
			entityname=driver.findElement(By.xpath(xpath+"["+index+"]//td//div")).getAttribute("innerText").toString();
			entityNameList.add(entityname);
			if(entityname.equalsIgnoreCase(cpEntityName))
			{
				WebUtil.actionClass(ele, driver);
				break;
			}
		//	System.out.println(entityname);
			
		}
				
		if(entityNameList.contains(cpEntityName))
		{
		//WebUtil.setContainsXpathAndClick("div",cpEntityName);
		WebUtil.click("RegulatoryChevron.xpath.CPSaveButton");
		WebUtil.wait(3000);
		WebUtil.click("RegulatoryChevron.xpath.OkSaveButton");
		WebUtil.wait(3000);
		WebUtil.click("RegulatoryChevron.xpath.SelectAllCheckBox");
		WebUtil.wait(3000);
		WebUtil.click("RegulatoryChevron.xpath.ApplyRegulations");
		WebUtil.wait(3000);
		WebUtil.click("RegulatoryChevron.xpath.SDLCheckBox");
		WebUtil.wait(3000);
		WebUtil.click("RegulatoryChevron.xpath.SaveCheckBox");
		WebUtil.wait(3000);
		WebUtil.click("RegulatoryChevron.xpath.OkCPSave");
		flag=true;
		return flag;
		}	
		
		else
		{
			return flag;
		}
	}
	
	public void addCP(WebDriver driver,String cpName,String cpEntityName)
	{
		boolean flag=false;
		WebUtil.click("RegulatoryChevron.xpath.AddButton");
		WebUtil.setWebEdit("RegulatoryChevron.xpath.CPNameInput", cpName);
		WebUtil.setContainsXpathAndClick("li", cpName);
		flag=checkAndClickCPEntityName(driver,cpName,cpEntityName);
		String att=null;
	
		while(flag!=true)
		{
			att=WebUtil.getAttributeValue(driver, "RegulatoryChevron.xpath.CPDialogBoxNextEnabled", "class");
			//System.out.println("attr: "+att);
			if(!att.contains("x-btn-default-toolbar-small-disabled"))
			{
				WebUtil.click("RegulatoryChevron.xpath.CPDialogBoxNext");
				WebUtil.wait(4000);
				flag=checkAndClickCPEntityName(driver, cpName,cpEntityName);
			
			}
			else
			{
				ReportUtil.reportWebElement("CP Entity does not exist", false);
			}			
			
		}
						
	}
}

