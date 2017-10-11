package com.markit.framework.kyc.pageMethods;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.markit.common.framework.util.DBConnection;
import com.markit.common.framework.util.DBUtility;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;

public class EntityScreener {
	
	Connection con=DBConnection.dbConnections.get("KYC");

	/** Set Filter for KYC Entity screener tabs
	 * @param driver
	 * @param FilterName
	 * @param FilterLabelLoc
	 * @param FilterChkbox
	 * @param FilterEditLoc
	 * @param filinptval
	 * @return driver
	 * @throws Exception
	 */
	public static WebDriver setFilter(WebDriver driver, String FilterName,
			String FilterLabelLoc, String FilterChkbox, String FilterEditLoc,
			String filinptval) throws Exception {

		WebElement FilterLabel = WebUtil.findElement(FilterLabelLoc, driver);

		Thread.sleep(2000);
		if (FilterLabel.isDisplayed())
		{
		Thread.sleep(2000);
		WebUtil.waitUntilElementPresent(driver, FilterLabelLoc);

		WebUtil.clickAt(FilterLabel);
		}
	
		WebElement FilterEdit = WebUtil.findElement(FilterEditLoc, driver);
		// Set the value in the filter
		FilterEdit.sendKeys(filinptval);
		//WebUtil.sendKeysWithAction(driver, FilterEditLoc, filinptval);
		//WebUtil.setWebEdit(FilterEdit, filinptval);
/*		WebUtil.actionClass(FilterEdit, driver);
		FilterEdit.sendKeys(filinptval);*/
		Thread.sleep(5000);

		System.out.println("Chkpoint 1");
		String filactval = WebUtil.readWebElement("WebEdit", FilterEdit);
		ReportUtil.reportWebEditRead("Enter" + FilterName, FilterEdit,
				filinptval, filactval);
		//WebElement chkbox=driver.findElement(By.xpath("//ul[@class='list values ng-scope']//span[contains(text(),'"+filinptval+"')]"));;
		WebUtil.waitUntilElementPresent(driver, FilterChkbox);
		WebUtil.verifyChkboxCheck(driver, FilterChkbox);
		//WebUtil.actionClass(chkbox, driver);
		System.out.println("Chkpoint 2");
		WebUtil.wait(4000);
		/*WebUtil.waitUntilElementPresent(driver, "EntityScreener.xpath.DefocusFilter");
		Thread.sleep(5000);*/
/*		WebElement div =WebUtil.findElement("EntityScreener.xpath.DefocusFilter", driver);
		div.click();*/
        
		System.out.println("Chkpoint 2");
		return driver;
	}

	/**Select Checkbox in Filter
	 * @param driver
	 * @param FilterChkbox
	 * @throws Exception
	 */
	public static void selectChkbx(WebDriver driver,String FilterChkbox) throws Exception {

		WebUtil.waitUntilElementPresent(driver, FilterChkbox);
		WebUtil.verifyChkboxCheck(driver, FilterChkbox);
	}

	/**Select drop down Filter
	 * @param driver
	 * @param FilterLabelLoc
	 * @return Driver
	 * @throws Exception
	 */
	public static WebDriver selectFilter(WebDriver driver,String FilterLabelLoc) throws Exception {

		WebElement FilterLabel = WebUtil.findElement(FilterLabelLoc, driver);
		WebUtil.waitUntilElementPresent(driver, FilterLabelLoc);
		WebUtil.clickAt(FilterLabel);
		return driver;
	}

	/** Reads the text appearing on Filter button 
	 * @param driver
	 * @param FilterLabelLoc
	 * @param ElementType
	 * @param filInptVal
	 */
	public static void readKYCFilterLabel(WebDriver driver,String FilterLabelLoc,String ElementType,String filInptVal){
		WebElement FilterLabel = WebUtil.findElement(FilterLabelLoc, driver);
		String actVal=WebUtil.readWebElement(ElementType, FilterLabel);
		ReportUtil.reportStringMatch("Verify selected value should be visible on Filter button", filInptVal, actVal);
	}
	/** Defocus Filter
	 * @param driver
	 */
	public static void defocusFilter(WebDriver driver){
		WebUtil.waitUntilElementPresent(driver, "EntityScreener.xpath.DefocusFilter");
		WebElement div =WebUtil.findElement("EntityScreener.xpath.DefocusFilter", driver);
		div.click();
	}

	/**Compare values of table to the entered value in Filter
	 * @param driver
	 * @param WebTableXpathKey
	 * @param FilterLabel
	 * @param FilterEditLoc
	 * @param filinptval
	 */
	public static void VerifyFilterMatches(WebDriver driver,String WebTableXpathKey,String FilterLabel,String FilterEditLoc, String filinptval){

		//Read the resultant web grid
		WebElement filtertextbox= WebUtil.findElement(FilterLabel,driver);
		List<String> cellValues =FilterResults(driver, FilterEditLoc, WebTableXpathKey);
		if(cellValues!=null) {
			Iterator<String> j = cellValues.iterator();
			while(j.hasNext()){
				String coltxt = j.next();
				if (coltxt.equals(filinptval)){

					ReportUtil.reportWebEditRead("Verify on applying Filter: "+FilterEditLoc+" correct value displayed in grid", filtertextbox, filinptval, coltxt);

					//break;
				}
			}
		}
	}


	/**Read the table and return values for column
	 * @param driver
	 * @param sColValue
	 * @param WebTableXpathKey
	 * @return list of values after reading
	 */
	public static List<String> FilterResults(WebDriver driver,String sColValue,String WebTableXpathKey){
		WebElement table= WebUtil.findElement(WebTableXpathKey,driver);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		List<WebElement> cols = table.findElements(By.tagName("th"));
		List<String> cellValues = new ArrayList<String>();

		int rowsize=rows.size();
		int colsize=cols.size();

		for (int i=1;i<=colsize;i++){
			String tab=XPath.myprop.get(WebTableXpathKey);
			WebElement colheader = driver.findElement(By.xpath(tab+"/table/thead/tr[1]/th["+i+"]"));

			String sValue1=	colheader.getText();

			if(sValue1.equalsIgnoreCase(sColValue)){
				// If the sValue match with the description, it will initiate one more inner loop for all the columns of 'i' row 
				for (int j=1;j<=rowsize-1;j++){
					WebElement rowValue= driver.findElement(By.xpath(tab+"/table/tbody/tr["+j+"]/td["+i+"]"));
					String sCellValue=rowValue.getText();
					System.out.println(sCellValue);
					cellValues.add(sCellValue);
				}
				break;
			}
		}

		return cellValues;
	}

	/**Read Match count on Entity Screener
	 * @param driver
	 * @param locatorName
	 * @return count
	 */
	public static String readCountofMatches(WebDriver driver, String locatorName) {
		WebElement matches = WebUtil.findElement(locatorName, driver);
		String count = WebUtil.readLinkText(matches);

		String str1[] = count.split(" ");
		System.out.println(str1[0]);
		return str1[0];
	}

	/**Clear Applied Filters
	 * @param driver
	 */
	public static void ClearFilters(WebDriver driver){
		WebElement Clearbtn=WebUtil.findElement("EntityScreener.xpath.Clear", driver);
		WebUtil.clickAt(Clearbtn);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickAtEntityNameLink(WebDriver driver,String entityName) throws InterruptedException
	{
		System.out.println("In the click method");
		WebElement element= driver.findElement(By.xpath("//a[@class='bold ng-binding']"));
		System.out.println("Xpath: "+element);
		WebUtil.actionClass(element, driver);
		System.out.println("item is clicked");
		
	}
	
	
	public void clickAtReports(WebDriver driver) throws InterruptedException
	{
		System.out.println("In the report method");
		WebUtil.click("Subscriber.xpath.EntityProfileReportXLS");
		Thread.sleep(2000);
		WebUtil.click("Subscriber.xpath.QualityAssuranceReportXLS");
		Thread.sleep(2000);
		WebUtil.click("Subscriber.xpath.EntityProfileReportPDF");
		Thread.sleep(2000);
		WebUtil.click("Subscriber.xpath.EvidentiarySourceReportPDF");
		Thread.sleep(2000);
		WebUtil.click("Subscriber.xpath.QualityAssuranceReportPDF");
		Thread.sleep(2000);
	}

	/**Enter Text in Filter and click search button
	 * 
	 * @param driver
	 * @param FilterEditLoc
	 * @param InptVal
	 * @param SrchbtnLoc
	 */
	public static void EnterFilter(WebDriver driver,String FilterEditLoc,String InptVal,String SrchbtnLoc){
		WebElement FilterEdit = WebUtil.findElement(FilterEditLoc, driver);
		WebUtil.setWebEdit(FilterEdit, InptVal);
		String filactval = WebUtil.readWebElement("WebEdit", FilterEdit);
		ReportUtil.reportWebEditRead("Enter text in Filter", FilterEdit, InptVal, filactval);

		WebElement Srchbtn=WebUtil.findElement(SrchbtnLoc, driver);
		WebUtil.clickAt(Srchbtn);
	}
	
	public void dbSubscriberAttribute(String subscriberId,String legalName){
		String entIdqry="select * from v_entity_screener where entity_name='"+legalName+"' and subscriber_id="+subscriberId;
		String entityId=DBUtility.getColData(con, entIdqry, "ENTITY_ID");
		String qry="SELECT entity_name, entity_id, mei, case_id, entity_type, review_type, bkjur, service_level, refresh_cycle, is_new_onboarding_request, req_profile_delivery_dt, subscriber_name, onboarding_state, annual_review_status, is_pending_tasks FROM v_entity_screener WHERE subscriber_id = "+subscriberId+" AND entity_id = "+entityId+" ";
		String ENTITY_NAME= DBUtility.getColData(con, qry, "ENTITY_NAME");
		ReportUtil.reportInputValueOnly("Verify column ENTITY_NAME in DB", ENTITY_NAME,true);       
		String ENTITY_ID= DBUtility.getColData(con, qry, "ENTITY_ID");
		ReportUtil.reportInputValueOnly("Verify column ENTITY_ID in DB", ENTITY_ID,true);      
		String MEI= DBUtility.getColData(con, qry, "MEI");
		ReportUtil.reportInputValueOnly("Verify column MEI in DB", MEI,true);       
		String CASE_ID= DBUtility.getColData(con, qry, "CASE_ID");
		ReportUtil.reportInputValueOnly("Verify column CASE_ID in DB", CASE_ID,true);       
		String ENTITY_TYPE= DBUtility.getColData(con, qry, "ENTITY_TYPE");
		ReportUtil.reportInputValueOnly("Verify column ENTITY_TYPE in DB", ENTITY_TYPE,true);       
		String REVIEW_TYPE= DBUtility.getColData(con, qry, "REVIEW_TYPE");
		ReportUtil.reportInputValueOnly("Verify column REVIEW_TYPE in DB", REVIEW_TYPE,true);      
		String BKJUR= DBUtility.getColData(con, qry, "BKJUR");
		ReportUtil.reportInputValueOnly("Verify column BKJUR in DB", BKJUR,true);      
		String SERVICE_LEVEL= DBUtility.getColData(con, qry, "SERVICE_LEVEL");
		ReportUtil.reportInputValueOnly("Verify column SERVICE_LEVEL in DB", SERVICE_LEVEL,true);      
		String REFRESH_CYCLE= DBUtility.getColData(con, qry, "REFRESH_CYCLE");
		ReportUtil.reportInputValueOnly("Verify column REFRESH_CYCLE in DB", REFRESH_CYCLE,true);       
		String IS_NEW_ONBOARDING_REQUEST= DBUtility.getColData(con, qry, "IS_NEW_ONBOARDING_REQUEST");
		ReportUtil.reportInputValueOnly("Verify column IS_NEW_ONBOARDING_REQUEST in DB", IS_NEW_ONBOARDING_REQUEST,true);
		String REQ_PROFILE_DELIVERY_DT= DBUtility.getColData(con, qry, "REQ_PROFILE_DELIVERY_DT");
		ReportUtil.reportInputValueOnly("Verify column REQ_PROFILE_DELIVERY_DT in DB", REQ_PROFILE_DELIVERY_DT,true);
		String SUBSCRIBER_NAME= DBUtility.getColData(con, qry, "SUBSCRIBER_NAME");
		ReportUtil.reportInputValueOnly("Verify column SUBSCRIBER_NAME in DB", SUBSCRIBER_NAME,true);
		String ONBOARDING_STATE= DBUtility.getColData(con, qry, "ONBOARDING_STATE");
		ReportUtil.reportInputValueOnly("Verify column ONBOARDING_STATE in DB", ONBOARDING_STATE,true);
		String ANNUAL_REVIEW_STATUS= DBUtility.getColData(con, qry, "ANNUAL_REVIEW_STATUS");       
		ReportUtil.reportInputValueOnly("Verify column ANNUAL_REVIEW_STATUS in DB", ANNUAL_REVIEW_STATUS,true);
		String IS_PENDING_TASKS= DBUtility.getColData(con, qry, "IS_PENDING_TASKS");
		ReportUtil.reportInputValueOnly("Verify column IS_PENDING_TASKS in DB", IS_PENDING_TASKS,true);

		String docIdQry="select  * from  (Select * from case_doc where case_id='"+CASE_ID+"' order by  doc_id desc) where rownum=1";
		String DOC_ID= DBUtility.getColData(con, docIdQry, "DOC_ID");
		ReportUtil.reportInputValueOnly("Verify column DOC_ID in DB", DOC_ID,true);

		String docTypeQry="select name from ref_doc_type where doc_type_id=(select doc_type_id from v_doc where doc_id='"+DOC_ID+"')";
		String DocType= DBUtility.getColData(con, docTypeQry, "NAME");
		ReportUtil.reportInputValueOnly("Verify column DocType in DB", DocType,true);	

	}
}
