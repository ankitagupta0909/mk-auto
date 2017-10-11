package com.markit.framework.mcpm.pageMethods;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.markit.common.framework.util.ExcelUtil;

import atu.testng.reports.logging.LogAs;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;

public class RFA {

	public static List<String> nextStepsValue=null;

	/**
	 * This method is to navigate to letter template library
	 * 
	 * @param driver
	 */
	public  void navigateLetterTemp(WebDriver driver) {
		NavigationPage nav = new NavigationPage();
		nav.navigateToRFA(driver);
		WebUtil.click("RFA.xpath.NavigLetterTemp");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is to navigate to Exhibit template library
	 * 
	 * @param driver
	 */
	public void navigateExhibitTemp(WebDriver driver) {
		NavigationPage nav = new NavigationPage();
		nav.navigateToRFA(driver);
		WebUtil.click("RFA.xpath.NavigExhibTemp");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**This method is to clear Filters on RFA pages
	 * @param driver
	 */
	public void clearFilters(WebDriver driver){

		WebUtil.click("RFA.xpath.ClearFilter");
		WebUtil.verifyElementDisabled("RFA.xpath.ClearFilter");
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is to navigate to Masterlist page
	 * 
	 * @param driver
	 */
	public void navigateMasterlist(WebDriver driver) {
		NavigationPage nav = new NavigationPage();
		nav.navigateToRFA(driver);
		WebUtil.click("RFA.xpath.NavigMasterlist");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**This method returns column index for tables having <= 2 header rows.
	 * @param driver
	 * @param sColName
	 * @param WebTableXpathKey
	 * @return
	 */
	public  Map<String,Integer> getColIndexMap(WebDriver driver,String WebTableXpathKey) {

		Map<String,Integer> headerIndexMap= new HashMap<String,Integer>();
		//WebElement table = WebUtil.findElement(WebTableXpathKey, driver);

		String tab = XPath.myprop.get(WebTableXpathKey);
		WebElement headers=driver.findElement(By.xpath(tab+ "/thead"));
		List<WebElement> headerRows = headers.findElements(By.tagName("tr"));

		int i=0;
		for(WebElement headerRow:headerRows){

			List<WebElement> headerCols = headerRow.findElements(By.tagName("th"));

			//for (int j=1;j<headerCols.size();j++){
			int j=1;
			for(WebElement headerCol:headerCols){	
				WebElement firstRowHeader = headerCol;			
				headerIndexMap.put(firstRowHeader.getText(), j);

				if(firstRowHeader.getAttribute("colspan")!=null){
					WebElement[]  rows = headerRows.toArray(new WebElement[headerRows.size()]);				
					WebElement secondRow = rows[i+1];
					List<WebElement> secondRowheaderCols = secondRow.findElements(By.tagName("th"));
					int k=j;
					for(WebElement secondRowHeader : secondRowheaderCols){					
						headerIndexMap.put(secondRowHeader.getText(), k);
						j=k;
						k++;					
					}
				}j++;
			}	
			if(i>=0){
				break;
			};
		}

		System.out.println(headerIndexMap);
		return headerIndexMap;
	}

	/**
	 * Read the table with <= 2 headers and return topmost value for column which can be used to
	 * verify a filter
	 * 
	 * @param driver
	 * @param sColValue
	 * @param WebTableXpathKey
	 * @return list of values after reading
	 */
	public static String ReadColValue(WebDriver driver, int colIndex,
			String WebTableXpathKey) {
		WebElement table = WebUtil.findElement(WebTableXpathKey, driver);
		List<WebElement> row=table.findElements(By.tagName("tr"));
		String sCellValue = null;
		String tab = XPath.myprop.get(WebTableXpathKey);

		for (int j = 1; j <= 1; j++) {
			WebElement rowValue = driver.findElement(By.xpath(tab
					+ "/tbody/tr[" + j + "]/td[" + colIndex + "]"));
			sCellValue = rowValue.getText();
			System.out.println(sCellValue);
		}
		int j = 2;
		while (sCellValue.equals("")) {

			WebElement rowValue1 = driver.findElement(By.xpath(tab
					+ "/tbody/tr[" + j + "]/td[" + colIndex + "]"));
			sCellValue = rowValue1.getText();
			if (j >= row.size() - 2) {
				break;
			}
			j = j++;
		}

		return sCellValue;

	}

	/**
	 * Read the table having <= 2 headers and return values for column
	 * 
	 * @param driver
	 * @param sColValue
	 * @param WebTableXpathKey
	 * @return list of values after reading
	 */


	public static List<String> FilterResults(WebDriver driver,
			int colIndex, String WebTableXpathKey) {
		WebElement table = WebUtil.findElement(WebTableXpathKey, driver);
		List<WebElement> rows=table.findElements(By.tagName("tr"));

		String tab = XPath.myprop.get(WebTableXpathKey);
		List<String> cellValues = new ArrayList<String>();

		int rowsize = rows.size();
		int rowsizeupdated;
		if (WebTableXpathKey.contains("letter")
				|| WebTableXpathKey.contains("Mast")) {
			rowsizeupdated = rowsize - 1;
		} else {
			rowsizeupdated = rowsize - 2;
		}


		for (int j = 1; j <= rowsizeupdated; j++) {
			WebElement rowValue = driver.findElement(By.xpath(tab
					+ "/tbody/tr[" + j + "]/td[" + colIndex + "]"));
			String sCellValue = rowValue.getText();
			System.out.println(sCellValue);
			cellValues.add(sCellValue);
		}

		return cellValues;
	}
	/**
	 * Compare values of table having <= 2 headers to the entered value in Filter
	 * 
	 * @param driver
	 * @param WebTableXpathKey
	 * @param FilterLabel
	 * @param FilterEditLoc
	 * @param filinptval
	 */	
	public static void VerifyFilterMatches(WebDriver driver,
			String WebTableXpathKey, String FilterLabel, int colIndex,
			String filinptval) {

		// Read the resultant web grid
		//WebElement filtertextbox = WebUtil.findElement(FilterLabel, driver);
		List<String> cellValues = FilterResults(driver, colIndex,
				WebTableXpathKey);
		if (cellValues != null) {
			Iterator<String> j = cellValues.iterator();
			while (j.hasNext()) {
				String coltxt = j.next();
				ReportUtil.reportStringMatch("Verify on applying Filter "+FilterLabel+" correct value displayed in grid", filinptval, coltxt);

			}
		}
	}



	/**Read the Tex hover text for a chart
	 * @param driver
	 * @param toolTipText
	 * @return number(int) in hover text
	 */
	public static String[] mouseHoverReadValues(WebDriver driver,String toolTipText){
		String str=new String(toolTipText);

		String a[]=str.split("\\n");
		System.out.println(a[0]);
		return a;

	}

	public static void verifyNextStepsCol(WebDriver driver)
	{
		WebElement table=driver.findElement(By.xpath("//table[@class='table table-hover table-responsive ng-scope']"));
		int rowCount=table.findElements(By.tagName("tr")).size();
		int colCount=table.findElements(By.tagName("th")).size();
		WebElement nextStep= null;
		String classValue=null;
		nextStepsValue=new ArrayList<String>();
		for (int i = 2; i <= rowCount; i++) {
			for (int j = 1; j <= colCount; j++) {
				if(j==8)
				{	nextStep= driver.findElement(By.xpath("//td/button"));
				classValue= nextStep.getAttribute("tooltip-html-unsafe");
				nextStepsValue.add(classValue);
				}
			}
		}
		for(int i=1;i<nextStepsValue.size();i++)
		{
			System.out.println("The values are: "+i+" "+nextStepsValue.get(i));
			if(nextStepsValue.get(i).equalsIgnoreCase("Edit Linked Exhibit"))
			{ReportUtil.reportStringMatch("Verify next Steps Column Value ","Edit Linked Exhibit","Edit Linked Exhibit");
			}
			if(nextStepsValue.get(i).equalsIgnoreCase("E-Sign"))
			{ReportUtil.reportStringMatch("Verify next Steps Column Value ","E-Sign","E-Sign");
			}
			if(nextStepsValue.get(i).contains("signature placeholders signed"))
			{ReportUtil.reportStringMatch("Verify next Steps Column Value ","signature placeholders signed","signature placeholders signed");
			}
		}
	}

	public static void validateTableContentAsPerCount(WebDriver driver,String valueofCount) throws InterruptedException
	{
		if (valueofCount.equalsIgnoreCase("0"))
		{	WebUtil.wait(8000);
			WebElement noResult=WebUtil.findElement("RFANoResult.xpath.NoResult", driver);
			String text=noResult.getText();
			System.out.println(text);
			ReportUtil.reportStringMatch("Verify the pop-up content when the count is 0","No search results found for this query.",text);
		}
		else
		{	verifyNextStepsCol(driver);
		}
	}


	/**Navigate on Create Letter Temp
	 * 
	 */	
	public void navigateCreateLetterTemp(){
		WebUtil.click("RFA.xpath.NavigCreateLetterTemp");
		WebUtil.wait(3000);
	}

	/**Navigate on Create Exhibit Temp
	 * 
	 */
	public void navigateCreateExhibitTemp(){
		WebUtil.click("RFA.xpath.NavigCreateExhibitTemp");
		WebUtil.wait(3000);
	}

	/** Create Letter Temp as per the choice of Party b placeholder
	 * @param driver
	 * @param PartyBPlaceholderName
	 */
	public String createLetterTemplate(WebDriver driver,String PartyBPlaceholderName){

		NavigationPage.setMcpmFrame(driver);
		navigateCreateLetterTemp();
		NavigationPage.setMcpmFrame(driver);

		WebUtil.click("RFACreateLetterTemp.xpath.PartyARelation");
		WebUtil.click("RFACreateLetterTemp.xpath.AddTextClick");

		switch(PartyBPlaceholderName){

		case "PBA":
			WebUtil.click("RFACreateLetterTemp.xpath.PartyBAddition");
			WebUtil.click("RFACreateLetterTemp.xpath.AddTextClick");
			break;

		case "PBR":
			WebUtil.click("RFACreateLetterTemp.xpath.PartyBRemoval");
			WebUtil.click("RFACreateLetterTemp.xpath.AddTextClick");
			break;

		case "FNC":
			WebUtil.click("RFACreateLetterTemp.xpath.FundNameChange");
			WebUtil.click("RFACreateLetterTemp.xpath.AddTextClick");
			break;

		case "EVC":
			WebUtil.click("RFACreateLetterTemp.xpath.ExhibitValueChange");
			WebUtil.click("RFACreateLetterTemp.xpath.AddTextClick");
			break;

		case "All":
			WebUtil.click("RFACreateLetterTemp.xpath.PartyBAddition");
			WebUtil.click("RFACreateLetterTemp.xpath.AddTextClick");
			WebUtil.click("RFACreateLetterTemp.xpath.PartyBRemoval");
			WebUtil.click("RFACreateLetterTemp.xpath.AddTextClick");
			WebUtil.click("RFACreateLetterTemp.xpath.FundNameChange");
			WebUtil.click("RFACreateLetterTemp.xpath.AddTextClick");
			WebUtil.click("RFACreateLetterTemp.xpath.ExhibitValueChange");
			WebUtil.click("RFACreateLetterTemp.xpath.AddTextClick");
			break;
		}

		WebUtil.wait(3000);
		WebUtil.click("RFACreateLetterTemp.xpath.BSsign");
		WebUtil.click("RFACreateLetterTemp.xpath.AddTextClick");
		WebUtil.click("RFACreateLetterTemp.xpath.SSsign");
		WebUtil.click("RFACreateLetterTemp.xpath.AddTextClick");
		WebUtil.click("RFACreateLetterTemp.xpath.SaveTemplate");
		WebUtil.wait(3000);

		WebElement enterTempName=WebUtil.findElement("RFACreateLetterTemp.xpath.EnterTempName",driver);
		WebUtil.wait(3000);
		int randNo=WebUtil.generateRandomNumber();
		String randTempName="AutoLetterTemp_"+PartyBPlaceholderName+"_"+randNo;
		WebUtil.setWebEdit(enterTempName, randTempName);

		WebUtil.click("RFACreateLetterTemp.xpath.Save");
		WebUtil.wait(3000);
		String valMsg=WebUtil.readElementText(driver, "RFACreateLetterTemp.xpath.ConfirmTempCreated");
		ReportUtil.reportStringMatch("Verify letter temp created", "Letter Template Saved", valMsg);

		WebUtil.click("RFACreateLetterTemp.xpath.Closepopup");
		WebUtil.wait(6000);

		return randTempName;

	}

	/**Create Exhibit Template with mandatory 4 columns
	 * @param driver
	 */
	public String createExhibitTemplate(WebDriver driver){

		driver=NavigationPage.setMcpmFrame(driver);

		navigateCreateExhibitTemp();
		List<WebElement> colList=WebUtil.findElements("RFACreateExTemp.xpath.colHeader", driver);

		for (int i=0; i<=colList.size()-4; i++){
			colList=WebUtil.findElements("RFACreateExTemp.xpath.colHeader", driver);
			WebUtil.wait(3000);

			WebUtil.setWebEdit(colList.get(i),"Col_"+i);
			WebUtil.wait(4000);
		}
		WebUtil.click("RFACreateLetterTemp.xpath.SaveTemplate");
		WebUtil.wait(3000);
		WebElement enterTempName=WebUtil.findElement("RFACreateExTemp.xpath.EnterTempName", driver);
		int randNo=WebUtil.generateRandomNumber();
		String exhbTem="AutoExTemp_"+randNo;
		WebUtil.setWebEdit(enterTempName, exhbTem);
		WebUtil.wait(3000);
		WebUtil.click("RFACreateExTemp.xpath.Save");
		WebUtil.wait(3000);

		WebUtil.click("RFACreateExTemp.xpath.CancelLinkMast");
		ReportUtil.reportWebElement("Verify Exhibit template created", true);
		WebUtil.wait(6000);
		return exhbTem;
	}



	/**Deletes topmost exhibit template
	 * 
	 */
	public void deleteExhibitTemplate(){
		WebUtil.click("RFAEx.xpath.DeleteTemp");
		WebUtil.wait(3000);
		WebUtil.click("RFACreateLetterTemp.xpath.Save");
		WebUtil.wait(5000);
	}

	/**Deletes topmost letter template
	 * 
	 */
	public void deleteLetterTemplate(){
		WebUtil.click("RFAletter.xpath.DeleteTemp");
		WebUtil.wait(3000);
		WebUtil.click("RFACreateLetterTemp.xpath.Save");
		WebUtil.wait(5000);
	}

	/** Select party b account to ADD,REMOVE or MODIFY
	 * @param driver
	 * @param entityName
	 * @param actionName
	 */
	public void selectPartyBaccounts(WebDriver driver,String entityName, String actionName){

		WebElement searchEnt=WebUtil.findElement("RFA.xpath.SearchPartyB", driver);
		searchEnt.clear();
		WebUtil.setWebEdit(searchEnt, entityName);
		searchEnt.sendKeys(Keys.ENTER);
		WebUtil.wait(6000);

		switch(actionName){
		case "ADD":
			WebUtil.click("RFA.xpath.AddPartyB");
			break;

		case "REMOVE":
			WebUtil.click("RFA.xpath.DeletePartyB");
			break;

		case "MODIFY":
			WebUtil.click("RFA.xpath.ModifyPartyB");	
			break;
		}
		searchEnt.clear();
		WebUtil.wait(5000);

	}

	/**Pass the letter temp name to select it when 
	 * creating RFA
	 * @param driver
	 * @param letterTemp
	 */
	public void selectLetterTemplate(WebDriver driver,String letterTemp){

		String xpath=WebUtil.returnXPathVal("RFA.xpath.ChooseLetterTemp");
		//String eval="//ul/li/a[contains(text(),'"+letterTemp+"')]";
		String ltxpath=xpath+"'"+letterTemp+"')]";
		WebElement lettertemp=driver.findElement(By.xpath(ltxpath));
		lettertemp.click();

	}

	/**Link topmost masterlist to exhibit
	 * @param driver
	 */
	public void linkMasterlistExhibit(WebDriver driver,String entityIM){

		NavigationPage.setMcpmFrame(driver);

		WebUtil.click("RFAEx.xpath.LinkMastbutton");
		WebUtil.wait(4000);

		String xpath_part1=WebUtil.returnXPathVal("RFA.xpath.linkExhbRadio1");
		String xpath_part2=WebUtil.returnXPathVal("RFA.xpath.linkExhbRadio2");
		
		String xpath=xpath_part1+entityIM+xpath_part2;

		WebElement linkRadioBtn=driver.findElement(By.xpath(xpath));
		linkRadioBtn.click();
		
		//WebUtil.click("RFACreateExTemp.xpath.LinkRadiobutton");
		WebUtil.wait(6000);
		WebUtil.click("RFACreateExTemp.xpath.LinkAndSave");

		WebUtil.wait(6000);

	}

	/** To Reject RFA
	 * @param driver
	 */
	public void rejectRFA(WebDriver driver){

		NavigationPage.setMcpmFrame(driver);
		WebUtil.click("RFA.xpath.SSRejectRFA");
		//WebUtil.clickedWithAction(driver, "RFA.xpath.SSRejectRFA");
		WebUtil.wait(3000);
		WebElement rejCom=WebUtil.findElement("RFA.xpath.RFARejectionReason", driver);
		WebUtil.setWebEdit(rejCom, "Auto Rejection Reason");
		WebUtil.wait(3000);
		WebUtil.click("RFA.xpath.Confirm");
		WebUtil.wait(6000);
	}

	/** Edit RFA and save
	 * without changing anything
	 */
	public void editRFA(){

		WebUtil.click("RFA.xpath.EDITRFA");
		WebUtil.wait(8000);
		WebUtil.click("RFA.xpath.ExhibSave");
		WebUtil.wait(5000);
		WebUtil.click("RFA.xpath.CloseAfterSave");
		WebUtil.wait(5000);
	}

	/** Recall RFA
	 */
	public void recallRFA(){
		WebUtil.click("RFA.xpath.BSRecallRFA");
		WebUtil.wait(5000);
		WebUtil.click("RFA.xpath.BSRecallbutton");
		WebUtil.wait(10000);
	}

	/** SEND RFA
	 */
	public void sendRFA(){
		WebUtil.click("RFA.xpath.SendRFA");
		WebUtil.wait(10000);
	}


	/** esign and Send rFA
	 * @param driver
	 */
	public void esignAndSendRFA(WebDriver driver){

		NavigationPage.setMcpmFrame(driver);
		WebUtil.click("RFAEsign.xpath.esignbutton");
		WebUtil.wait(8000);

		String elemId=WebUtil.returnXPathVal("RFAEsign.elemId.Scroll");
		WebUtil.scrollDownByElemId(driver, elemId, 2000);

		WebUtil.click("RFAEsign.xpath.BSsignCheckbx");
		WebUtil.wait(4000);
		WebUtil.click("RFAEsign.xpath.Accept");
		WebUtil.wait(4000);

		WebElement title=WebUtil.findElement("RFAEsign.xpath.Title", driver);
		WebUtil.setWebEdit(title, "title");
		WebUtil.wait(5000);

		WebUtil.click("RFAEsign.xpath.Date");
		WebUtil.wait(5000);
		WebUtil.click("RFAEsign.xpath.Confirm");
		WebUtil.wait(15000);

		sendRFA();

	}

	/** Update Date in masterlist template
	 * @param date
	 * @param size
	 */
	public void updateDateBulkMasterlistTemp(ArrayList<String> date, int size){
		for(int i=0;i<=size;i++){
			String sDate=WebUtil.getTheCurrentDateAsperpassedFormat("dd-MMM-yyyy");
			date.add(sDate);
		}
	}


	/** bulk upload template update
	 * @param filePath
	 * @param entityName
	 */
	public void bulkUploadMasterlistTemplateUpdate(String filePath,ArrayList<String> entityName){
		try {
			Workbook wb = ExcelUtil.getWorkBookObject(filePath);
			int rowNo=0;
			Row row=wb.getSheet("Sheet1").getRow(rowNo);
			int colNoIM=ExcelUtil.getColumnNumberByColumnName("Investment Manager", row);
			int colNoPartyB=ExcelUtil.getColumnNumberByColumnName("Party B Accounts True Legal Name", row);
			int colNoDate=ExcelUtil.getColumnNumberByColumnName("ISDA Master Agreement Reference Date", row);

			ArrayList<String> IM=new ArrayList<String>();
			ArrayList<String> dt=new ArrayList<String>();

			updateDateBulkMasterlistTemp(dt,1);
			for(int i=0;i<=1;i++){
				IM.add(entityName.get(3));			
			}
			System.out.println(IM.size());
			ArrayList<String> entityNameUpdate=new ArrayList<String>();
			entityNameUpdate.add(entityName.get(0));
			entityNameUpdate.add(entityName.get(1));
			System.out.println(entityNameUpdate.size());

			ExcelUtil.setCellValueBulkUpload(rowNo, colNoIM, wb, IM);
			ExcelUtil.setCellValueBulkUpload(rowNo, colNoPartyB, wb, entityNameUpdate);
			ExcelUtil.setCellValueBulkUpload(rowNo, colNoDate, wb, dt);
			ExcelUtil.writeExcel(filePath, wb);

		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ReportUtil.reportElementException("Write Excel exception", LogAs.FAILED);
		} 
	}
	
	/**batch Upload the RFA onboarding file
	* @param driver
	* @param mappingName
	*/
	public void uploadRFAOnboardingFile(WebDriver driver,String mappingName) 
	{
	       
	       NavigationPage nav= new NavigationPage();
	       nav.navigateToBulkUploadOfRFA(driver);
	       NavigationPage.setMcpmFrame(driver);
	       WebUtil.wait(2000);  
	       WebElement choose=WebUtil.findElement("MCPMRFA.xpath.Choose", driver);
	       WebUtil.actionClass(choose, driver);
	       String path1=System.getProperty("user.dir");
	       String path2="/src/testdata/Templates/OnboardingRFA.xlsx";
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
	       WebUtil.wait(6000);        
	       WebUtil.clickedWithAction(driver, "MCPMRFA.xpath.LoadData");
	       WebUtil.wait(4000);  
	       WebElement map=driver.findElement(By.xpath("//div[contains(text(),'"+mappingName+"')]"));
	       WebUtil.actionClass(map, driver);
	       WebUtil.wait(2000);
	       WebUtil.clickedWithAction(driver, "MCPM.xpath.LoadMapping");
		   WebUtil.wait(4000);
	       WebUtil.clickedWithAction(driver, "MCPMRFA.xpath.Upload");
	       WebUtil.wait(5000);  
	       WebUtil.clickedWithAction(driver, "MCPMRFA.xpath.Alert");
	       WebUtil.wait(2000);        
	}

	public void downloadMasterList(WebDriver driver)
	{
		NavigationPage.setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "RFAMasterLiast.xpath.MasterListLink");	
		NavigationPage.setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "RFAMasterLiast.xpath.ViewMasterList");

	}

	public void verify_MasterList(String sheetName,String expectedValue,String columnName)
	{

		String currentUser = System.getProperty("user.name");
		String path="C:/Users/"+currentUser+"/Downloads/Masterlist.xlsx";
		List<String> dataList=new ArrayList<>();
		dataList = ExcelUtil.getColumnDataAsPerPassedName(path, sheetName, columnName);
		String matchedValue=ExcelUtil.isValueExist(dataList, expectedValue);
		ReportUtil.reportStringMatch("Verify value exists in the excel", expectedValue, matchedValue);


	}

}
/*	*//**
 * Compare values of table to the entered value in Filter
 * 
 * @param driver
 * @param WebTableXpathKey
 * @param FilterLabel
 * @param FilterEditLoc
 * @param filinptval
 *//*
 *
 *
public static void VerifyFilterMatches(WebDriver driver,
		String WebTableXpathKey, String FilterLabel, String ColName,
		String filinptval) {

	// Read the resultant web grid
	WebElement filtertextbox = WebUtil.findElement(FilterLabel, driver);
	List<String> cellValues = FilterResults(driver, ColName,
			WebTableXpathKey);
	if (cellValues != null) {
		Iterator<String> j = cellValues.iterator();
		while (j.hasNext()) {
			String coltxt = j.next();
			if (coltxt.equals(filinptval)) {

				ReportUtil.reportWebEditRead("Verify on applying Filter: "
						+ ColName + ": correct value displayed in grid",
						filtertextbox, filinptval, coltxt);

				// break;
			}
		}
	}
}

  *//**
  * Read the table and return values for column
  * 
  * @param driver
  * @param sColValue
  * @param WebTableXpathKey
  * @return list of values after reading
  *//*
public static List<String> FilterResults(WebDriver driver,
		String sColValue, String WebTableXpathKey) {
	WebElement table = WebUtil.findElement(WebTableXpathKey, driver);
	List<WebElement> rows = table.findElements(By.tagName("tr"));
	List<WebElement> cols = table.findElements(By.tagName("th"));
	List<String> cellValues = new ArrayList<String>();

	int rowsize = rows.size();
	int colsize = cols.size();

	int rowsizeupdated;
	if (WebTableXpathKey.contains("letter")
			|| WebTableXpathKey.contains("Mast")) {
		rowsizeupdated = rowsize - 1;
	} else {
		rowsizeupdated = rowsize - 2;
	}
	for (int i = 1; i <= colsize; i++) {
		String tab = XPath.myprop.get(WebTableXpathKey);
		WebElement colheader = driver.findElement(By.xpath(tab
				+ "/thead/tr[1]/th[" + i + "]"));

		String sValue1 = colheader.getText();

		if (sValue1.equalsIgnoreCase(sColValue)) {
			// If the sValue match with the description, it will initiate
			// one more inner loop for all the columns of 'i' row
			for (int j = 1; j <= rowsizeupdated; j++) {
				WebElement rowValue = driver.findElement(By.xpath(tab
						+ "/tbody/tr[" + j + "]/td[" + i + "]"));
				String sCellValue = rowValue.getText();
				System.out.println(sCellValue);
				cellValues.add(sCellValue);
			}
			break;
		}
	}

	return cellValues;
}

   *//**
   * Read the table and return topmost value for column which can be used to
   * verify a filter
   * 
   * @param driver
   * @param sColValue
   * @param WebTableXpathKey
   * @return list of values after reading
   *//*
public static String ReadColValue(WebDriver driver, String sColValue,
		String WebTableXpathKey) {
	WebElement table = WebUtil.findElement(WebTableXpathKey, driver);
	List<WebElement> rows = table.findElements(By.tagName("tr"));
	List<WebElement> cols = table.findElements(By.tagName("th"));
	// List<String> cellValues = new ArrayList<String>();
	String sCellValue = null;
	int rowsize = rows.size();
	int colsize = cols.size();

	for (int i = 1; i <= colsize; i++) {
		String tab = XPath.myprop.get(WebTableXpathKey);
		WebElement colheader = driver.findElement(By.xpath(tab
				+ "/thead/tr[1]/th[" + i + "]"));

		String sValue1 = colheader.getText();

		if (sValue1.equalsIgnoreCase(sColValue)) {
			// If the sValue match with the description, it will initiate
			// one more inner loop for 'i' row
			for (int j = 1; j <= 1; j++) {
				WebElement rowValue = driver.findElement(By.xpath(tab
						+ "/tbody/tr[" + j + "]/td[" + i + "]"));
				sCellValue = rowValue.getText();
				System.out.println(sCellValue);
				// cellValues.add(sCellValue);

			}
			int j = 1;
			while (sCellValue.equals("")) {
				j = ++j;
				WebElement rowValue1 = driver.findElement(By.xpath(tab
						+ "/tbody/tr[" + j + "]/td[" + i + "]"));
				sCellValue = rowValue1.getText();
				if (j >= rowsize - 2) {
					break;
				}
			}
			break;
		}
	}
	return sCellValue;

	public static List<String> FilterResults1(WebDriver driver,
			int colIndex, String WebTableXpathKey,String attributName) {
		WebElement table = WebUtil.findElement(WebTableXpathKey, driver);
		List<WebElement> rows=table.findElements(By.tagName("tr"));

		String tab = XPath.myprop.get(WebTableXpathKey);
		List<String> cellValues = new ArrayList<String>();

		int rowsize = rows.size();
		int rowsizeupdated;
		if (WebTableXpathKey.contains("letter")
				|| WebTableXpathKey.contains("Mast")) {
			rowsizeupdated = rowsize - 1;
		} else {
			rowsizeupdated = rowsize - 2;
		}


		for (int j = 1; j <= rowsizeupdated; j++) {
			WebElement rowValue = driver.findElement(By.xpath(tab
					+ "/tbody/tr[" + j + "]/td[" + colIndex + "]"));
			String sCellValue = rowValue.getAttribute(attributName);
			System.out.println(sCellValue);
			cellValues.add(sCellValue);
		}

		return cellValues;
	}

	public static void VerifyFilterMatches1(WebDriver driver,
			String WebTableXpathKey, int colIndex,
			String filinptval,String attributeName,String expectedValue) {

		// Read the resultant web grid

		List<String> cellValues = FilterResults1(driver, colIndex,
				WebTableXpathKey,attributeName);
		if (cellValues != null) {
			Iterator<String> j = cellValues.iterator();
			while (j.hasNext()) {
				String coltxt = j.next();
				if (coltxt.equals(filinptval)) {

					ReportUtil.reportStringMatch("Verify the value after filter applied",expectedValue,coltxt);
					// break;
				}
			}
		}
	}

}*/
