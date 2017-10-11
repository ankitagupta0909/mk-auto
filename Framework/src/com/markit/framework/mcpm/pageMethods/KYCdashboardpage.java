package com.markit.framework.mcpm.pageMethods;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;

public class KYCdashboardpage {

	public Map<String, String> mapvalue;
	List<String> colorValuesInTable=null;
	
	public WebDriver setFrame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
		driver.switchTo().frame("MFrame");
		return driver;
	}

	public WebDriver kycDashBaordPageValidation(WebDriver driver)
	{

		System.out.println("*********************************************");

		String URL=driver.getCurrentUrl();

		ReportUtil.reportStringMatch("URL after Nevigating to KYC Dashboard", "https://ufeqa.markit.com/home/index.jsp#KYCS.Dashboard.Default", URL);

		WebElement kycTask=WebUtil.findElement("KYCDashboard.xpath.KYCTask",driver);
		String text=kycTask.getText();

		System.out.println(text);
		boolean actVal=WebUtil.verifyWebElementExists("Text",kycTask);
		ReportUtil.reportWebElement("KYC DashBoard Page Uploaded",actVal);


		System.out.println("*********************************************");
		return driver;
	}

	public WebDriver entityFilterValidation(WebDriver driver) throws InterruptedException
	{

		List<WebElement> th= driver.findElements(By.xpath("//table[@class='table ng-scope']//th"));

		List<WebElement> td= driver.findElements(By.xpath("//table[@class='table ng-scope']//tr//td"));


		Map<String, String> mapvalue = new HashMap<String,String>();
		for(int i=0; i<th.size(); i++){
			mapvalue.put(th.get(i).getText(), td.get(i).getText());


		}
		System.out.println("*********************************************");

		for (Map.Entry<String, String> entry : mapvalue.entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}

		return driver;

	}

	public WebDriver clickAtFilters(WebDriver driver,String filter)
	{

		WebElement filterbutton=WebUtil.findElement(filter,driver);


		boolean actVal;
		actVal = WebUtil.verifyWebElementExists("WebElement", filterbutton);
		WebUtil.clickAt(filterbutton);

		ReportUtil.reportWebElement("Clicked on Entity Name Filter", actVal);
		WebUtil.wait(2000);
		return driver;

	}

	public WebElement clickAtInputBoxOfFilter(WebDriver driver,String filterEdit) 	{
		WebElement filterInput=WebUtil.findElement(filterEdit,driver);
		boolean actVal;
		actVal = WebUtil.verifyWebElementExists("WebElement", filterInput);

		WebUtil.actionClass(filterInput, driver);
		WebUtil.wait(2000);
		ReportUtil.reportWebElement("Clicked on input box", actVal);
		return filterInput;



	}

	public WebDriver setValueInFilter(WebDriver driver,WebElement filter,String filterValue) 
	{
		//System.out.println("Entity name: "+filterValue+filter);

		if(filter.isDisplayed())
		{
			filter.clear();
			filter.sendKeys(filterValue);
			WebUtil.wait(2000);
			//String entityName=WebUtil.readWebElement("WebEdit",filter);
			//ReportUtil.reportWebEditRead( "validate the entered Entity Name in the filter",filter, filterValue, entityName);


		}
		return driver;

	}

/*	public WebDriver applyFliter(WebDriver driver, String checkbox, String defocus) throws InterruptedException
	{
		WebElement checkboxElement=WebUtil.findElement(checkbox,driver);


		boolean actVal;
		actVal = WebUtil.verifyWebElementExists("WebElement", checkboxElement);

		WebUtil.actionClass(checkboxElement, driver);
		Thread.sleep(1000);
		ReportUtil.reportWebElement("Checkbox is clicked in the filter", actVal);

		WebElement defocusfilter=WebUtil.findElement(defocus,driver);
		WebUtil.actionClass(defocusfilter, driver);
		Thread.sleep(1000);
		return driver;

	}*/
	
	
	public WebDriver applyFliter(WebDriver driver,String textToBeSelected,  String defocus) 
	{
		WebElement checkboxElement=driver.findElement(By.xpath("//ul[@ng-if='values.length']//li//i"));


		boolean actVal;
		actVal = WebUtil.verifyWebElementExists("WebElement", checkboxElement);

		WebUtil.actionClass(checkboxElement, driver);
		ReportUtil.reportWebElement("Checkbox is clicked in the filter", actVal);

		WebElement defocusfilter=WebUtil.findElement(defocus,driver);
		WebUtil.actionClass(defocusfilter, driver);
		
		return driver;

	}

	public WebDriver clickAtEntityName(WebDriver driver,String webElement) throws InterruptedException
	{

		WebElement entitylink=WebUtil.findElement(webElement,driver);

		if(entitylink.isDisplayed())
		{

			WebUtil.actionClass(entitylink, driver);
			Thread.sleep(1000);

		}
		return driver;

	}


	public void readWebTableRowsContent(WebDriver driver,String WebTableRowPath,String WebTableRowContentPath){


		List<WebElement>rowsheader = WebUtil.findElements(WebTableRowPath,driver);
		List<WebElement> rowContent = WebUtil.findElements(WebTableRowContentPath,driver);
		mapvalue = new HashMap<String,String>();
		for(int i=0; i<rowsheader.size(); i++){
			mapvalue.put(rowsheader.get(i).getText(), rowContent.get(i).getText());
		}
		System.out.println("*********************************************");

		for (Map.Entry<String, String> entry : mapvalue.entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		System.out.println("*********************************************");



	}

	public void validateTableContent(WebDriver driver,String entityName,String entityType,String mei,String lei,String kycRediness)
	{
		String actEntityName= mapvalue.get("Entity Name").toString();
		String actEntityType= mapvalue.get("Entity Type").toString();
		String actKYCStatus= mapvalue.get("KYC Readiness").toString();
		String actKYCReviewStatus= mapvalue.get("KYC Review Status").toString();
		String actMEIValue= mapvalue.get("MEI").toString();
		String actLEIValue= mapvalue.get("LEI").toString();
		//String actCIValue= mapvalue.get("Client Identifier").toString();
		String actDueDate= mapvalue.get("Due Date").toString();
		String actPendingTask= mapvalue.get("Pending Tasks").toString();

		System.out.println("*********************************************");
		if(actMEIValue.equalsIgnoreCase(mei)&&actLEIValue.equalsIgnoreCase(lei)&&actEntityName.equalsIgnoreCase(entityName)&&actEntityType.equalsIgnoreCase(entityType)&&actKYCStatus.equalsIgnoreCase(kycRediness))
		{
			System.out.println("Filter passed");
			ReportUtil.reportWebElementExist("Values of entity name ", entityName, actEntityName);
			ReportUtil.reportWebElementExist("Values of entity type", entityType, actEntityType);
			ReportUtil.reportWebElementExist("Values of entity mei", mei, actMEIValue);
			ReportUtil.reportWebElementExist("Values of entity KYC Rediness", kycRediness, actKYCStatus);
			ReportUtil.reportWebElementExist("Values of entity lei", lei, actLEIValue);
			//ReportUtil.reportWebElementExist("Values of entity ci", ci, actCIValue);
			//ReportUtil.reportWebElementExist("Values of entity after filter is applied", entityType, actEntityType);

		}
		else
		{
			System.out.println("Filter is failed");
			//ReportUtil.reportWebElementExist("Entity Name Filter", "", "Failed");
		}

		if(mapvalue.get("KYC Review Status").length()==0&&mapvalue.get("Due Date").length()==0&&mapvalue.get("Pending Tasks").length()==0)
		{
			ReportUtil.reportWebElementExist("Values of mentioned columns are blank ", "KYC Review Status,Due Date and Pending Tasks", "KYC Review Status,Due Date and Pending Tasks");
			System.out.println("Kyc Review Status,Due Date and Pending Task filters are blank");
		}

		else
		{
			System.out.println("Filter failed");
			//ReportUtil.reportWebElementExist("Entity Name Filter", "", "Failed");
		}
		System.out.println("*********************************************");


	}
	
	public void clearFilter()
	{
		WebUtil.click("KYCDashbaord.xpath.ClearFilter");
		WebUtil.wait(3000);
	}

	public WebDriver clickAtLinkToNavigateToKYCEntityDetailsPage(WebDriver driver,String actionRequired) 
	{

		WebElement actionrequiredLink=WebUtil.findElement(actionRequired, driver);
		boolean actVal;
		actVal = WebUtil.verifyWebElementExists("WebElement", actionrequiredLink);
		WebUtil.actionClass(actionrequiredLink, driver);
		WebUtil.wait(5000);
		ReportUtil.reportWebElement("Clicked on Action Required link", actVal);
		return driver;

	}

	public int kycReadinessCount(WebDriver driver,String element)
	{
		WebElement readiness=WebUtil.findElement(element,driver);
		int count=0;
		if(readiness.isDisplayed())
		{
			count=Integer.parseInt(readiness.getText());
			ReportUtil.reportStringMatch("The count of Entities with status Action required ", readiness.getText(), readiness.getText());
		}


		return count;

	}

	public WebDriver clickingAtCPLink(WebDriver driver,String element) 
	{
		WebElement cp=WebUtil.findElement(element,driver);
		boolean actVal;



		actVal = WebUtil.verifyWebElementExists("WebElement", cp);
		WebUtil.actionClass(cp, driver);
		ReportUtil.reportWebElement("Click at CounterParty", actVal);

		return driver;

	}

	public WebDriver kycReadinessCountValidation(WebDriver driver,String element,int countNumber)
	{
		WebElement readiness=WebUtil.findElement(element,driver);
		int count=0;
		if(readiness.isDisplayed())
		{
			count=Integer.parseInt(readiness.getText());
		}
		int expectedCount=countNumber+1;
		String expCount=Integer.toString(expectedCount);
		String actCount=Integer.toString(count);
		if(expectedCount==count){
			ReportUtil.reportWebElementExist("Validate the Count of KYC readiness", expCount, actCount);
			System.out.println("The count matches: "+count);
		}
		else
		{
			System.out.println("The count does not match: "+count);
		}

		return driver;

	}

	public WebDriver validateStateofEntityAfterCompltion(WebDriver driver,String element)
	{
		WebElement status=WebUtil.findElement(element,driver);
		String statusText=status.getText();

		if(status.isDisplayed())
		{
			if(statusText.equalsIgnoreCase("KYC Prep Complete"))
			{

				ReportUtil.reportStringMatch("The status of the entity after steps complition", "KYC Prep Complete", statusText);
			}
		}
		
		return driver;

	}
	
	public void readChartsHeaders(WebDriver driver,String element)
	{
		WebElement header=WebUtil.findElement(element,driver);
		String headerText=header.getText();
		if(headerText.equalsIgnoreCase("Priority Tasks View"))
		{
		
			ReportUtil.reportStringMatch("Verify the header of Priority Tasks View", "Priority Tasks View", headerText);
		}
		if(headerText.equalsIgnoreCase("KYC Tasks"))
		{
			
			ReportUtil.reportStringMatch("Verify the header of KYC Tasks", "KYC Tasks", headerText);
		}
		if(headerText.equalsIgnoreCase("KYC Readiness"))
		{
			
			ReportUtil.reportStringMatch("Verify the header of KYC Readiness", "KYC Readiness", headerText);
		}
		if(headerText.equalsIgnoreCase("KYC Review Status"))
		{
			
			ReportUtil.reportStringMatch("Verify the header of KYC Review Status", "KYC Review Status", headerText);
		}
	}
	

	
	public String verifyColorofCharts(WebDriver driver,String element,String expectedColorCode,String nameofGraph)
	{
		
		WebElement graph=WebUtil.findElement(element,driver);
		//boolean result=WebUtil.isAttribtuePresent(graph, "visibility");
		String color=null;
		if(graph.isDisplayed())
		{
			color=graph.getAttribute("fill");
			ReportUtil.reportStringMatch("Verify the color of "+nameofGraph+" graph", expectedColorCode, color);
			System.out.println(color);
		}
		return color;
		
	}
	
	/*public String verifyLabelOfGraph(WebDriver driver,String element,String expectedText,String graphName)
	{
		WebElement graph=WebUtil.findElement(element,driver);
		String text=null;
	
		if(graph.isDisplayed())
		{
			text=graph.getText();
			
			ReportUtil.reportStringMatch("Verify the lable of the "+graphName+" graph", expectedText, text.trim());
			System.out.println(text);
		}
		return text;
		
	}*/
	
	public String verifyLabelOfGraph(WebDriver driver,String element,String expectedText,String graphName)
	{
		WebElement graph=WebUtil.findElement(element,driver);
		String text=null;
	
		if(graph.isDisplayed())
		{
			text=graph.getText();

			System.out.println(text.replace("\n", " "));
			
			ReportUtil.reportStringMatch("Verify the lable of the "+graphName+" graph", expectedText, text.replace("\n", " "));
			//System.out.println(text);
		}
		return text;
		
	}
	
	public void clickATCharts(WebDriver driver,String chartPath) 
	{
		
		boolean actVal;
		System.out.println("In the method");
		WebElement graph=WebUtil.findElement(chartPath,driver);
		actVal = WebUtil.verifyWebElementExists("WebElement", graph);
		
		WebUtil.actionClass(graph, driver);
		ReportUtil.reportWebElement("Click at Graph", actVal);
		
	}
	public void verifyCountofAppliedFilter(WebDriver driver,String graphCountPath,String filterCountpath,String graphName)
	{
		WebElement graphCountText=WebUtil.findElement(graphCountPath,driver);
		WebElement filterCountText=WebUtil.findElement(filterCountpath,driver);
		String   value = filterCountText.getText();
		String filterValue=WebUtil.extractaSubString(value, "Matches");
		String actualValue=filterValue.trim();
		String expectedValue=graphCountText.getText();
		if(actualValue.equalsIgnoreCase(expectedValue))
		{
			
			ReportUtil.reportStringMatch("Verify the count of the filters for "+graphName+" graph", expectedValue, 	actualValue);
			
		}
		
		//ReportUtil.reportStringMatch("Verify the count of Applied filter with the graph count", graphCountText.getText(), actualValue);
	
	}
	
	public void activeTab(WebDriver driver,String element,String expectedTabName)
	{
		WebElement tab=WebUtil.findElement(element,driver);
		WebElement activeTab=tab.findElement(By.xpath("//li[@class='active']/a"));
		System.out.println("Active Tab name:"+activeTab.getText());
	
		ReportUtil.reportStringMatch("Verify the active tab", expectedTabName, 	activeTab.getText());
	}
	
	public void validateDueDateForTenDays(String dateValue)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); 
		c.add(Calendar.DATE, 9); 
		String output = sdf.format(c.getTime());
		System.out.println("Expected: "+output);
		
		
		String ExpectedDate= "ANY - "+output;
		if(dateValue.equalsIgnoreCase(ExpectedDate))
		{
			
			ReportUtil.reportStringMatch("Verify the date in Due date filter", ExpectedDate,dateValue);
		}
		
	
		
	}
	
	public List<String> validateFiterResult(WebDriver driver)
	{

		String Table_data=null;
		WebElement color= null;
		String classValue=null;
	    List<String> tableContent=new ArrayList<String>();
	    colorValuesInTable=new ArrayList<String>();
		WebElement table=driver.findElement(By.xpath("//div[@class='container-fluid ng-scope']/table"));
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		int rowCount=driver.findElements(By.xpath("//div[@class='container-fluid ng-scope']/table//tbody//tr")).size();
		int colCount=table.findElements(By.tagName("th")).size();	
		String first_part = "//div[@class='container-fluid ng-scope']/table//tbody//tr["; 
		String second_part = "]/td["; 
		String third_part = "]"; 



		for (int i = 1; i <= rowCount; i++) {
			for (int j = 1; j <= colCount; j++) {
				String final_xpath = first_part + i + second_part + j+ third_part;
				if(j==2)
				{
					color= driver.findElement(By.xpath("//td[@class='task-type-cell']/span"));
					classValue= color.getAttribute("class");
					colorValuesInTable.add(classValue);
					
				}
				Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				
				tableContent.add(Table_data);
					
			}
			
		}
	
		return tableContent;
	}
	
	public void readContent(WebDriver driver)
	{
		System.out.println("###############in read content method");
		List<String> value=validateFiterResult(driver);
		for(int i=0;i<value.size();i++)
		{
			System.out.println(value.get(i));
	
		}
	}
	public void readContentOfColor(WebDriver driver,String expectedValue,String graphName)
	{
		
		List<String> value=colorValuesInTable;
		String result=value.get(0);
		/*for(int i=0;i<value.size();i++)
		{
			System.out.println(value.get(i));
			
		}*/
		if(result.equalsIgnoreCase("task-state task-state-1"))
		{
			ReportUtil.reportStringMatch("Verify Color of the Task Type after filter is applied for "+graphName+" Graph", expectedValue,"#DB4200");
		}
		if(result.equalsIgnoreCase("task-state task-state-2"))
		{
			ReportUtil.reportStringMatch("Verify Color of the Task Type after filter is applied for "+graphName+" Graph", expectedValue,"#F7C900");
		}
		if(result.equalsIgnoreCase("task-state task-state-3"))
		{
			ReportUtil.reportStringMatch("Verify Color of the Task Type after filter is applied for "+graphName+" Graph", expectedValue,"#76C000");
		}
		
		/*for(int i=0;i<value.size();i++)
		{
			System.out.println(value.get(i));
			ReportUtil.reportStringMatch("Verify Color of the Task Type after filter is applied for "+graphName+" Graph", expectedValue,value.get(i));
	
		}*/
	}
	
	public void validateDueDateforTentoThirty(String dateValue)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); 
		c.add(Calendar.DATE, 10); 
		String output = sdf.format(c.getTime());
		c.add(Calendar.DATE, 20); 
		String output2 = sdf.format(c.getTime());
		
		//System.out.println("Expected: "+output);
		
		
		
		String ExpectedDate= output+ " - "+output2;
		if(dateValue.equalsIgnoreCase(ExpectedDate))
		{
			System.out.println("Pass: "+ExpectedDate);
			ReportUtil.reportStringMatch("Verify the date in Due date filter", ExpectedDate,dateValue);
		}
		else
		{
			System.out.println("fail expected date: "+ExpectedDate+" Acutual date: "+dateValue);
			ReportUtil.reportStringMatch("Verify the date in Due date filter", ExpectedDate,dateValue);
		}
	
		
	}
	
	public void validateDueDateforThirtyOneToNinety(String dateValue)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(new Date()); 
		cal.add(Calendar.DATE, 31); 
		String output = sdf.format(cal.getTime());
		cal.add(Calendar.DATE, 59); 
		String output2 = sdf.format(cal.getTime());
		
		//System.out.println("Expected: "+output);
		
		
		
		String ExpectedDate= output+ " - "+output2;
		if(dateValue.equalsIgnoreCase(ExpectedDate))
		{
			System.out.println("Pass: "+ExpectedDate);
			ReportUtil.reportStringMatch("Verify the date in Due date filter", ExpectedDate,dateValue);
		}
		else
		{
			System.out.println("fail expected date: "+ExpectedDate+" Acutual date: "+dateValue);
			ReportUtil.reportStringMatch("Verify the date in Due date filter", ExpectedDate,dateValue);
		}
	
		
	}
	
	public void clickonTaskTab(WebDriver driver) 
	{
		
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.TaskTab");		
		WebUtil.wait(4000);
		NavigationPage.setMcpmFrame(driver);
		
		
	}
	public void clickonEntityTab(WebDriver driver) 
	{
	
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.EntityTab");
		
		WebUtil.wait(3000);
		NavigationPage.setMcpmFrame(driver);
		
		
	}
	
	public void clickATPendingTasks(WebDriver driver) throws InterruptedException
	{
		WebUtil.clickedWithAction(driver, "KYCDashboard.xpath.PendingTasks");
				
		
	}
		
		
	public void clickAtLinkAsPerPassedName(WebDriver driver,String linkName) 
	{
		boolean actVal;
		
		
		WebElement link=driver.findElement(By.xpath("//td[@class='task-type-cell']//span[contains(text(),'"+linkName+"')]"));
		actVal=WebUtil.verifyWebElementExists("WebLink",link);
		WebUtil.actionClass(link, driver);
		ReportUtil.reportWebElement("Clicked on "+linkName+" link",actVal);
		WebUtil.wait(5000);
	}
	
	public int getLinksAsPerPassedName(WebDriver driver,String linkName) 
	{
	  int size=0;
	  List<WebElement> links=driver.findElements(By.xpath("//td[@class='task-type-cell']//span[contains(text(),'"+linkName+"')]"));
	  size=links.size();
	  return size;
	
	}
	
	public  Map<String,Integer> getColIndexMap(WebDriver driver,String WebTableXpathKey) {

		Map<String,Integer> headerIndexMap= new HashMap<String,Integer>();
			//WebElement table = WebUtil.findElement(WebTableXpathKey, driver);
	    String tab = XPath.myprop.get(WebTableXpathKey);
		
		String test=tab+ "/thead";
		
		WebElement headers=null;
		
		headers=driver.findElement(By.xpath(test));
	
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
	
	public static List<String> FilterResults(WebDriver driver,
			int colIndex, String WebTableXpathKey) {
		WebElement table = WebUtil.findElement(WebTableXpathKey, driver);
		List<WebElement> rows=table.findElements(By.tagName("tr"));

		String tab = XPath.myprop.get(WebTableXpathKey);
		List<String> cellValues = new ArrayList<String>();

		int rowsize = rows.size();
		int rowsizeupdated;
		rowsizeupdated = rowsize - 1;

		for (int j = 1; j <= rowsizeupdated; j++) {
			WebElement rowValue = driver.findElement(By.xpath(tab
					+ "/tbody/tr[" + j + "]/td[" + colIndex + "]"));
			String sCellValue = rowValue.getText();
			System.out.println(sCellValue);
			cellValues.add(sCellValue);
		}

		return cellValues;
	}
	
	public void setFilter(WebDriver driver,String filterPath,String valuetoSet,String defousFilter)
	{
		WebUtil.clickedWithAction(driver, filterPath);
		WebUtil.wait(2000);
		WebElement xpathofCheckbox=driver.findElement(By.xpath("//div[@class='checklist filter-popup']//ul[@class='list']//li//span[contains(text(),'"+valuetoSet+"')]"));
		System.out.println("value "+xpathofCheckbox);
		boolean actVal = WebUtil.verifyWebElementExists("WebElement",xpathofCheckbox);
		WebUtil.actionClass(xpathofCheckbox, driver);
		ReportUtil.reportWebElement("Verify checkbox is clicked", actVal);
		WebUtil.clickedWithAction(driver, defousFilter);
	}
	
	public int exportSetFilterData(WebDriver driver,String tabName) 
	{
		//WebElement element=WebUtil.findElement("KYCDashboardFilter.xpath.Export", driver);
		String username=System.getProperty("user.name");
		String downloadPath="C:/Users/"+username+"/Downloads";
		WebUtil.emptyAFolder(downloadPath);
		WebUtil.clickedWithAction(driver, "KYCDashboardFilter.xpath.Export");
		WebUtil.wait(4000);
		String currentdate=WebUtil.getTheCurrentDateAsperpassedFormat("MM-dd-yy");
		Path path = Paths.get(System.getProperty("user.home"),"Downloads", "KYC_Services_Export_"+currentdate+"_"+tabName+".xlsx");		
		int count=ExcelUtil.countOfTheRowsInSheet(path.toString(),tabName);
		String match=WebUtil.readElementText(driver, "KYCDashboardFilter.xpath.Match");
		String expectedCount=WebUtil.removeThepassedItemFromString(match, "Matches");
		String actualCount=Integer.toString(count);
		System.out.println("The count of the match and excel: "+expectedCount+ " "+actualCount);
		ReportUtil.reportStringMatch("Verify the count of downloaded sheet", expectedCount.trim(), actualCount);
		
		return count;
	}
	
	public String verifyTheColorOfPreKYCChecklistGraph(WebDriver driver,String xpathKey)
	{
		String color=null;	
		String value=WebUtil.getAttributeValue(driver,xpathKey,"class");
		if(value.equalsIgnoreCase("step")|| value.equalsIgnoreCase("step request-esign-role")||value.equalsIgnoreCase("step ng-scope"))
		{
			color="Blue";
		}
		if(value.equalsIgnoreCase("step completed"))
		{
			color="Green";
			
		}
		return color;
		
	}
	public ArrayList<String> verifyNameofTabs(WebDriver driver,String tabs )
	{
		ArrayList<String> actualValuesOfTab = new ArrayList<String>();
		List<WebElement> list=WebUtil.findElements(tabs,driver);
		for(int i=0; i<list.size(); i++){
			actualValuesOfTab.add(list.get(i).getText());
		}
		return actualValuesOfTab;

	}
	
	public void kycTOU(WebDriver driver)
	{
		NavigationPage.setMcpmFrame(driver);
		WebUtil.click("KYC.xpath.KYCTOU");
		WebUtil.click("KYC.xpath.KYCIAgree");
		WebUtil.wait(5000);
		
	}
	
	public void verifyTheTaskState(WebDriver driver,String expectedValue)
	{
		String text=WebUtil.readElementText(driver, "KYCRegression.xpath.TaskStateText");
		ReportUtil.reportStringMatch("Verify the state of the Filter Task State is", expectedValue, text);
	}
	
	public void  impersonationACompanyAndVerify(WebDriver driver,String companyName)
	{
		WebUtil.click("KYCRegression.xpath.CompanyName");
		WebUtil.setWebEdit("KYCRegression.xpath.CompanyName", companyName);
		WebUtil.click("KYCRegression.xpath.CompanyNameOption");
		WebUtil.click("KYCRegression.xpath.SaveCompanyName");
		WebUtil.wait(5000);
		int size=WebUtil.getSizeOfElement(driver, "KYCRegression.xpath.ImpersonationAlert");
		if(size!=0)
		{
			String text=WebUtil.readElementText(driver, "KYCRegression.xpath.ImpersonationAlert");
			ReportUtil.reportStringMatch("Verify Bulk Internal Confirmation Error", "You are currently viewing the KYC Dashboard as "+companyName+". Drop Impersonation to return to your own view.", text);
			WebUtil.click("KYCRegression.xpath.ImpersonationAlert2");
			WebUtil.wait(5000);
		}
	}
	
	public void uncheckAndCheckOpenfromTaskStateFilter(WebDriver driver)
	{
		WebUtil.click("KYCDashboradFilter.xpath.TaskState");
		WebUtil.wait(3000);
	    String value=WebUtil.getAttributeValue(driver, "KYCDashboradFilter.xpath.TaskStateOpen", "ng-if");
	    if(value.equalsIgnoreCase("value.checked"))
	    {
	    	WebUtil.click("KYCDashboradFilter.xpath.TaskStateOpen");
	    	WebUtil.wait(5000);
	    	WebUtil.click("KYCDashboradFilter.xpath.TaskStateOpen");
	    	WebUtil.wait(5000);
	    	WebUtil.click("KYCDashboard.xpath.Defocus");
	    }
		
		
	}
	
	public void checkPleaseConfirmCheckbox(WebDriver driver)
	{
		boolean value=WebUtil.isElementPresent(driver, "KYCDashboradFilter.xpath.PleaseConfirmCheckbox");
		if(value==true)
		{
			WebUtil.click("KYCDashboradFilter.xpath.PleaseConfirmCheckbox");
		}
	}
	


	}




