package com.markit.framework.mcpm.pageMethods;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jfree.util.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.markit.common.framework.util.DBUtility;
import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;

public class SDLPage {

	private static Map<String,List<String>> table1=new HashMap<String,List<String>>();
	
	private static  String selectDropDownOptionPath=XPath.myprop.get("SDLEntity.xpath.selectOption");  
	private Map<String,List<String>> table=new HashMap<String,List<String>>();
	Map<String,List<String>> entityScreener=new HashMap<String,List<String>>();
	Map<String,List<String>> producerDashboard=new HashMap<String,List<String>>();
	Map<String,List<String>> dashboardData=new HashMap<String,List<String>>();

	public void fillGeneralReg(WebDriver driver,Map<String,String> sdlTestData){
		NavigationPage.setMcpmFrame(driver);
		fillAddress(driver,"","", sdlTestData.get("addr"), sdlTestData.get("secAddr"), sdlTestData.get("city"), sdlTestData.get("state"), sdlTestData.get("zip"), sdlTestData.get("country"), "General");
		WebUtil.setWebEdit("SDLGR.xpath.name", sdlTestData.get("name"));
		WebUtil.setWebEdit("SDLGR.xpath.email", sdlTestData.get("email"));
		WebUtil.setWebEdit("SDLGR.xpath.phone", sdlTestData.get("phone"));  
		selectDropdownSDLEntity(driver,selectDropDownOptionPath,sdlTestData.get("isPrincipalMultibranch"),"SDLGR.xpath.clickMultibranchDD");
		WebUtil.click("SDLEntity.xpath.SaveButton");
		WebUtil.wait(5000);
		try{
			WebUtil.click("SDLEntity.xpath.closeAfterSave");
		}catch(Exception e){
			Log.error("Unable to close pop up after saving SDL details"+e.getMessage());
		}

	}

	public void fillUSEntDetails(WebDriver driver,Map<String,String> sdlTestData){
		NavigationPage.setMcpmFrame(driver); 
		WebUtil.click("SDL.xpath.USlink");  
		verifySDLEntPageLoad(driver, "SDLUS.xpath.cftcEntStatus", 10);
		selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("US_cftcEntStatus"),"SDLUS.xpath.cftcEntStatus");   
		selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("US_cbrGeneral"),"SDLUS.xpath.cftcCrossBorder");
		selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("US_PREntStatus"),"SDLUS.xpath.prEntStatus");
		selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("US_PRcbr"),"SDLUS.xpath.prCrossBorder");

		String UShedging=sdlTestData.get("US_hedgingExemption");
		checkCheckboxSDLEntity(driver, "SDLUS.xpath.hedgingExStatus", UShedging);
		selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("US_swapHedgingExemption"),"SDLUS.xpath.swapHedgExRep");
		if(sdlTestData.get("US_swapHedgingExemption").equals("Trade Filing by Recipient")){
			WebUtil.scrollToBottom(driver);
			checkCheckboxSDLEntity(driver, "SDLUS.xpath.hedgingExStatus", sdlTestData.get("US_swap_financialOblig"));
			selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("US_swap_SECIssuer"),"SDLUS.xpath.SECIssuer");
			selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("US_swap_BoardApproval"),"SDLUS.xpath.BoardApproval");
			WebUtil.setWebEdit("SDLUS.xpath.SECCentralIndKey", sdlTestData.get("US_swap_SECKeyNo"));

		}	
		WebUtil.scrollToBottom(driver);
		selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("US_isUSAANA"),"SDLUS.xpath.isAANA");
		WebUtil.scrollToBottom(driver);
		if(sdlTestData.get("US_isUSAANA").equals("Yes")){
			fillAddress(driver,sdlTestData.get("US_legalName"),sdlTestData.get("US_entId"),
					sdlTestData.get("US_addr"),sdlTestData.get("US_secAddr"), sdlTestData.get("US_city"), 
					sdlTestData.get("US_state"),sdlTestData.get("US_zip"), sdlTestData.get("US_country")
					,"AANA");
			WebUtil.scrollToBottom(driver);
		}

		if(sdlTestData.get("US_usAANAthreshold").contains("'")){
			String arr[]=sdlTestData.get("US_usAANAthreshold").split("'");
			selectDropdownSDLEntity_threshold(driver, selectDropDownOptionPath,arr[0],"SDL.xpath.AANAthreshold");
		}else{
			selectDropdownSDLEntity_threshold(driver, selectDropDownOptionPath,sdlTestData.get("US_usAANAthreshold"),"SDL.xpath.AANAthreshold");
		}

		if(sdlTestData.get("US_usAANAthreshold").equals("US AANA information will be separately reported by the following person:")){
			WebUtil.scrollToBottom(driver);
			fillAddress(driver,sdlTestData.get("US_Threshold_legalName"),sdlTestData.get("US_Threshold_entId"),
					sdlTestData.get("US_Threshold_addr"),sdlTestData.get("US_Threshold_secAddr"), 
					sdlTestData.get("US_Threshold_city"), sdlTestData.get("US_Threshold_state"),
					sdlTestData.get("US_Threshold_zip"), sdlTestData.get("US_Threshold_country"),"Threshold");
		}else if(sdlTestData.get("US_usAANAthreshold").contains("Not above")){
			WebUtil.scrollToBottom(driver);
			selectDropdownSDLEntity_threshold(driver, selectDropDownOptionPath,sdlTestData.get("US_Threshold_Estimate"),"SDL.xpath.thresholdEstimate");
		}
		saveSDLEntityInfo(driver);
	}

	public void fillCAEntDetails(WebDriver driver,Map<String,String> sdlTestData){
		NavigationPage.setMcpmFrame(driver); 
		WebUtil.click("SDL.xpath.CAlink");  
		verifySDLEntPageLoad(driver, "SDLCA.xpath.isDomesticFRFI", 10);
		selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("CA_isDomesticFRFI"),"SDLCA.xpath.isDomesticFRFI");   
		selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("CA_branchFRFI"),"SDLCA.xpath.CAbranchFRFI");
		selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("CA_coveredEntity"),"SDLCA.xpath.isCoveredEnt");
		selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("CA_isCanadaAANA"),"SDLCA.xpath.isAANA");
		WebUtil.scrollToBottom(driver);
		if(sdlTestData.get("CA_isCanadaAANA").equals("Yes")){
			fillAddress(driver,sdlTestData.get("CA_legalName"),sdlTestData.get("CA_entId"),
					sdlTestData.get("CA_addr"),sdlTestData.get("CA_secAddr"), sdlTestData.get("CA_city"),
					sdlTestData.get("CA_state"),sdlTestData.get("CA_zip"), sdlTestData.get("CA_country")
					,"AANA");
			WebUtil.scrollToBottom(driver);
		}

		if(sdlTestData.get("CA_AANAThreshold").contains("'")){
			String arr[]=sdlTestData.get("CA_AANAThreshold").split("'");
			selectDropdownSDLEntity_threshold(driver, selectDropDownOptionPath,arr[0],"SDL.xpath.AANAthreshold");
		}else{
			selectDropdownSDLEntity_threshold(driver, selectDropDownOptionPath,sdlTestData.get("CA_AANAThreshold"),"SDL.xpath.AANAthreshold");
		}

		if(sdlTestData.get("CA_AANAThreshold").equals("Canada AANA information will be separately reported by the following person:")){
			WebUtil.scrollToBottom(driver);
			fillAddress(driver,sdlTestData.get("CA_legalName"),sdlTestData.get("CA_entId"),
					sdlTestData.get("CA_Threshold_addr"),sdlTestData.get("CA_Threshold_secAddr"),
					sdlTestData.get("CA_Threshold_city"), sdlTestData.get("CA_Threshold_state"),
					sdlTestData.get("CA_Threshold_zip"), sdlTestData.get("CA_Threshold_country"),"Threshold");
		}else if(sdlTestData.get("CA_AANAThreshold").contains("Not above")){
			WebUtil.scrollToBottom(driver);
			selectDropdownSDLEntity_threshold(driver, selectDropDownOptionPath,sdlTestData.get("CA_thresholdEstimate"),"SDL.xpath.thresholdEstimate");
		}

		saveSDLEntityInfo(driver);
	}

	public void fillEUEntDetails(WebDriver driver,Map<String,String> sdlTestData){
		NavigationPage.setMcpmFrame(driver); 
		WebUtil.click("SDL.xpath.EUlink");  
		verifySDLEntPageLoad(driver, "SDLEU.xpath.princEntType", 10);
		if(sdlTestData.get("EU_typeofEUEnt").equals("null")){
			selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("EU_principalEntType"),"SDLEU.xpath.princEntType");
			selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("EU_thirdCountEntity"),"SDLEU.xpath.thirdCountryEnt");
			selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("EU_isEUAANA"),"SDLEU.xpath.isAANA");
			if(sdlTestData.get("EU_isEUAANA").equals("Yes")){
				WebUtil.scrollToBottom(driver);
				fillAddress(driver,sdlTestData.get("EU_legalName"),sdlTestData.get("EU_entId"),
						sdlTestData.get("EU_addr"),sdlTestData.get("EU_secAddr"), sdlTestData.get("EU_city"),
						sdlTestData.get("EU_state"),sdlTestData.get("EU_zip"), sdlTestData.get("EU_country")
						,"AANA");
			}	 
			if(sdlTestData.get("EU_EUAANAthreshold").contains("'")){
				String arr[]=sdlTestData.get("EU_EUAANAthreshold").split("'");
				selectDropdownSDLEntity_threshold(driver, selectDropDownOptionPath,arr[0],"SDL.xpath.AANAthreshold");
			}else{
				selectDropdownSDLEntity_threshold(driver, selectDropDownOptionPath,sdlTestData.get("EU_EUAANAthreshold"),"SDL.xpath.AANAthreshold");
			}

			if(sdlTestData.get("EU_EUAANAthreshold").equals("EU AANA information will be separately reported by the following person")){
				WebUtil.scrollToBottom(driver);
				fillAddress(driver,sdlTestData.get("EU_Threshold_legalName"),sdlTestData.get("EU_Threshold_entId"),
						sdlTestData.get("EU_Threshold_addr"),sdlTestData.get("EU_Threshold_secAddr"), 
						sdlTestData.get("EU_Threshold_city"), sdlTestData.get("EU_Threshold_state"),
						sdlTestData.get("EU_Threshold_zip"), sdlTestData.get("EU_Threshold_country"),"Threshold");
			}else if(sdlTestData.get("EU_EUAANAthreshold").contains("Not above")){
				WebUtil.scrollToBottom(driver);
				selectDropdownSDLEntity_threshold(driver, selectDropDownOptionPath,sdlTestData.get("EU_thresholdEstimate"),"SDL.xpath.thresholdEstimate");
			}
		} else{			
			checkCheckboxSDLEntity(driver, "SDLEU.xpath.typeofEUEntity", sdlTestData.get("EU_typeofEUEnt"));
		}

		saveSDLEntityInfo(driver);
	}


	public void fillCHEntDetails(WebDriver driver,Map<String,String> sdlTestData){
		NavigationPage.setMcpmFrame(driver); 
		WebUtil.click("SDL.xpath.CHlink");  
		verifySDLEntPageLoad(driver, "SDLCH.xpath.principalEntType", 10);
		if(sdlTestData.get("CH_typeofFMIA").equals("null")){
			selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("CH_principalEntType"),"SDLCH.xpath.principalEntType");
			selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("CH_thirdCountryEnt"),"SDLCH.xpath.FMIAthirdcountryEnt");
			selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("CH_isAANA"),"SDLCH.xpath.isAANA");
			if(sdlTestData.get("CH_isAANA").equals("Yes")){
				WebUtil.scrollToBottom(driver);
				fillAddress(driver,sdlTestData.get("CH_legalName"),sdlTestData.get("CH_entId"),
						sdlTestData.get("CH_addr"),sdlTestData.get("CH_secAddr"), sdlTestData.get("CH_city"), 
						sdlTestData.get("CH_state"),sdlTestData.get("CH_zip"), sdlTestData.get("CH_country")
						,"AANA");
			}	 

			if(sdlTestData.get("CH_AANAthreshold").contains("'")){
				String arr[]=sdlTestData.get("CH_AANAthreshold").split("'");
				selectDropdownSDLEntity_threshold(driver, selectDropDownOptionPath,arr[0],"SDL.xpath.AANAthreshold");
			}else{
				selectDropdownSDLEntity_threshold(driver, selectDropDownOptionPath,sdlTestData.get("CH_AANAthreshold"),"SDL.xpath.AANAthreshold");
			}

			if(sdlTestData.get("CH_AANAthreshold").equals("FMIA AANA information will be separately reported by the following person:")){
				WebUtil.scrollToBottom(driver);
				fillAddress(driver,sdlTestData.get("CH_Threshold_legalName"),sdlTestData.get("CH_Threshold_entId"),
						sdlTestData.get("CH_Threshold_addr"),sdlTestData.get("CH_Threshold_secAddr"),
						sdlTestData.get("CH_Threshold_city"), sdlTestData.get("CH_Threshold_state"),
						sdlTestData.get("CH_Threshold_zip"), sdlTestData.get("CH_Threshold_country"),"Threshold");
			}else if(sdlTestData.get("CH_AANAthreshold").contains("Not above")){
				WebUtil.scrollToBottom(driver);
				selectDropdownSDLEntity_threshold(driver, selectDropDownOptionPath,sdlTestData.get("CH_AANAthresholdEstimate"),"SDL.xpath.thresholdEstimate");
			}
		} 
		else{
			checkCheckboxSDLEntity(driver, "SDLCH.xpath.FMIAexemptEnt", sdlTestData.get("CH_typeofFMIA"));
		}
		saveSDLEntityInfo(driver);
	}

	public void fillJPEntDetails(WebDriver driver,Map<String,String> sdlTestData){
		NavigationPage.setMcpmFrame(driver); 
		WebUtil.click("SDL.xpath.JPlink"); 
		//verifySDLEntPageLoad(driver, "SDLJP.xpath.entityOrgJP", 10);
		selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("JP_entOrganizedJP"),"SDLJP.xpath.entityOrgJP");   

		if(sdlTestData.get("JP_entOrganizedJP").equals("Yes")){
			selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("JP_principalStatus"),"SDLJP.xpath.principalSt");  
		}

		if(sdlTestData.get("JP_principalStatus").equals("null") ||  !(sdlTestData.get("JP_principalStatus").equals("None of the above"))){		
			selectDropdownSDLEntity(driver, selectDropDownOptionPath,sdlTestData.get("JP_isJapanAANA"),"SDLJP.xpath.isAANA");
			WebUtil.scrollToBottom(driver);
			if(sdlTestData.get("JP_isJapanAANA").equals("Yes")){
				fillAddress(driver,sdlTestData.get("JP_legalName"),sdlTestData.get("JP_entId"),
						sdlTestData.get("JP_addr"),sdlTestData.get("JP_secAddr"), sdlTestData.get("JP_city"),
						sdlTestData.get("JP_state"),sdlTestData.get("JP_zip"), sdlTestData.get("JP_country")
						,"AANA");
				WebUtil.scrollToBottom(driver);
			}
		}		
		if(sdlTestData.get("JP_AANAthreshold").contains("'")){
			String arr[]=sdlTestData.get("JP_AANAthreshold").split("'");
			selectDropdownSDLEntity_threshold(driver, selectDropDownOptionPath,arr[0],"SDL.xpath.AANAthreshold");
		}else{
			selectDropdownSDLEntity_threshold(driver, selectDropDownOptionPath,sdlTestData.get("JP_AANAthreshold"),"SDL.xpath.AANAthreshold");
		}

		if(sdlTestData.get("JP_AANAthreshold").equals("Japan AANA information will be separately reported by the following person:")){
			WebUtil.scrollToBottom(driver);
			fillAddress(driver,sdlTestData.get("JP_Threshold_legalName"),sdlTestData.get("JP_Threshold_entId"),
					sdlTestData.get("JP_Threshold_addr"),sdlTestData.get("JP_Threshold_secAddr"), 
					sdlTestData.get("JP_Threshold_city"),sdlTestData.get("JP_Threshold_state"),
					sdlTestData.get("JP_Threshold_zip"), sdlTestData.get("JP_Threshold_country"),"Threshold");
		}else if(sdlTestData.get("JP_AANAthreshold").contains("Not above")){
			WebUtil.scrollToBottom(driver);
			selectDropdownSDLEntity_threshold(driver, selectDropDownOptionPath,sdlTestData.get("JP_thresholdEstimate"),"SDL.xpath.thresholdEstimate");
		}	
		saveSDLEntityInfo(driver);
	}



	public void fillAddress(WebDriver driver,String legalName, String entId,String addr, String addr2, String city, String state,String zip,String country,String addrType){		
		List<WebElement> legNameEl=WebUtil.findElements("SDLAANA.xpath.legalName", driver);
		List<WebElement> entIdEl=WebUtil.findElements("SDLAANA.xpath.EntId", driver);
		List<WebElement> addrEl=WebUtil.findElements("SDLThres.xpath.addr", driver);
		List<WebElement> addr2El=WebUtil.findElements("SDLThres.xpath.addr2", driver);
		List<WebElement> cityEl=WebUtil.findElements("SDLThres.xpath.city", driver);
		List<WebElement> stateEl=WebUtil.findElements("SDLThres.xpath.state", driver);
		List<WebElement> zipEl=WebUtil.findElements("SDLThres.xpath.zip", driver);		
		List<WebElement> countryEl=WebUtil.findElements("SDLThres.xpath.country", driver);

		if(addrType.equals("AANA")){
			WebUtil.setWebEdit(legNameEl.get(0), legalName);
			WebUtil.setWebEdit(entIdEl.get(0), entId);
		}
		if(addrType.equals("AANA")||addrType.equals("General")){
			WebUtil.setWebEdit(addrEl.get(0), addr);
			WebUtil.setWebEdit(addr2El.get(0), addr2);
			WebUtil.setWebEdit(cityEl.get(0), city);
			WebUtil.setWebEdit(stateEl.get(0), state);
			WebUtil.setWebEdit(zipEl.get(0), zip);
			WebUtil.setWebEdit(countryEl.get(0), country);
			WebUtil.click("SDLGR.xpath.SelectCountryDropDown");	
		} 
		else if(addrType.equals("Threshold")){
			WebUtil.setWebEdit(legNameEl.get(1), legalName);
			WebUtil.setWebEdit(entIdEl.get(1), entId);
			WebUtil.setWebEdit(addrEl.get(1), addr);
			WebUtil.setWebEdit(addr2El.get(1), addr2);
			WebUtil.setWebEdit(cityEl.get(1), city);
			WebUtil.setWebEdit(stateEl.get(1), state);
			WebUtil.setWebEdit(zipEl.get(1), zip);
			WebUtil.setWebEdit(countryEl.get(1), country);
			WebUtil.click("SDLGR.xpath.SelectCountryDropDown");
		}
	}

	public  void selectDropdownSDLEntity(WebDriver driver,String path,String value,String dropdownPath){
		WebUtil.click(dropdownPath);
		String selectDDpathPath=path+value+"')]";
		WebElement selectOption=driver.findElement(By.xpath(selectDDpathPath));    	
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.visibilityOf(selectOption));
		WebUtil.clickAt(selectOption);
	}

	public  void selectDropdownSDLEntity_threshold(WebDriver driver,String path,String value,String dropdownPath){
		WebUtil.click(dropdownPath);
		String selectDDpathPath=path+value+"')]";
		WebElement selectOption=driver.findElement(By.xpath(selectDDpathPath));    	

		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", selectOption);
		WebUtil.wait(2000);
	}

	public void checkCheckboxSDLEntity(WebDriver driver, String path,String value){
		String [] strArr=null;
		if(value.contains("_")){
			strArr=value.split("_");
			for(int i=0;i<strArr.length;i++){
				String checkboxpath=XPath.myprop.get(path)+strArr[i]+"']";
				WebElement checkbox=driver.findElement(By.xpath(checkboxpath));
				checkbox.click();
			}		
		} else{
			String checkboxpath=XPath.myprop.get(path)+value+"']";
			WebElement checkbox=driver.findElement(By.xpath(checkboxpath));
			checkbox.click();
		}

	}

	public void saveSDLEntityInfo(WebDriver driver){
		WebUtil.scrollBarUp(driver);
		WebUtil.clickedWithAction(driver, "SDLEntity.xpath.SaveButton");
		System.out.println();
		WebUtil.wait(40000);
		try{
			if(WebUtil.isElementPresent(driver, "SDLEntity.xpath.closeAfterSave"))
			WebUtil.click("SDLEntity.xpath.closeAfterSave");
		}catch(Exception e){
			Log.error("Unable to close pop up after saving SDL details"+e.getMessage());
		}
	}
	public  Map<String, String> readData(String testDataPath,String sheetName,Map<String, String> sdlTestData){
		Workbook wb=	ExcelUtil.getWorkBookObject(testDataPath);
		List<String> key=ExcelUtil.getColumnDataAsPerPassedName(testDataPath, sheetName, "Key");
		List<String> val=ExcelUtil.getColumnDataAsPerPassedName(testDataPath, sheetName, "Value");		
		Iterator<String> keyIt=key.iterator();
		Iterator<String> valIt=val.iterator();		
		while(keyIt.hasNext()&&valIt.hasNext()){
			sdlTestData.put(keyIt.next(), valIt.next().toString().trim());			
		}		
		System.out.println("Map"+sdlTestData.toString());
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sdlTestData;
	}


	public Map<String,List<String>> setMapValuesForLeftAndRightTable(WebDriver driver,String tableHeadersPath,String tableRowsPath)
	{
		int index1=0;
		int index2=0;
		List<WebElement> headers=WebUtil.findElements(tableHeadersPath, driver);      
		List<WebElement> rows=WebUtil.findElements(tableRowsPath, driver);   
		String xpath=XPath.myprop.get(tableRowsPath);
		//Map<String,List<String>> table=new HashMap<String,List<String>>();		
		for(int i=0;i<headers.size();i++)
		{
			List<String> tableData=new ArrayList<String>();
			index1++;
			String headersText=headers.get(i).getText();
			System.out.println("Headers are: "+headers.get(i).getText());				
			String text=null;		

			for(int n=0;n<rows.size();n++)
			{
				index2++;
			
				if(headers.get(i).getText().equalsIgnoreCase("Action Needed"))
				{
					text=driver.findElement(By.xpath(xpath+"["+index2+"]//td["+index1+"]//span")).getAttribute("class");
				}
				else
				{
					text=driver.findElement(By.xpath(xpath+"["+index2+"]//td["+index1+"]")).getText();
				}
				//System.out.println("Text is = " + text);
				tableData.add( text);
			}
			index2=0;
			table1.put(headersText, tableData);			
		}
		return table1;

	}

	public Map<String,List<String>> setMapValueforCentreTable(WebDriver driver,String pageName)
	{
		int index1=0;
		int index2=0;
		int in=0;
		int size=0;
		String header=null;
		String headersText=null;
		String att=null;
		List<WebElement> centre_mainHeaders= WebUtil.findElements("MCPMSDL.xpath.GridCentreTableMainHeaders", driver);
		List<WebElement> centre_Subheaders= WebUtil.findElements("MCPMSDL.xpath.GridCentreTableSubHeaders", driver);
		List<WebElement> centre_rows=WebUtil.findElements("MCPMSDL.xpath.GridCentreTableRows", driver);
		List<String> finalHeaders=new ArrayList<String>();
		String xpath=XPath.myprop.get("MCPMSDL.xpath.GridCentreTableRows");
		//String xpath2=XPath.myprop.get("MCPMSDL.xpath.GridCentreTableClass");
		String xpath3=XPath.myprop.get("MCPMSDL.xpath.GridCentreTableClassForReceiver");

		for(int i=0;i<centre_mainHeaders.size();i++)
		{				
			headersText=centre_mainHeaders.get(i).getAttribute("innerText").toString();
			att=centre_mainHeaders.get(i).getAttribute("colspan");
			size=Integer.parseInt(att);			
			for(int j=0;j<size;j++)
			{
				header=headersText+ " " +centre_Subheaders.get(in).getAttribute("innerText").toString();
				finalHeaders.add(header);					
				in++;      					
			}
		}
		for(int i=0;i<finalHeaders.size();i++)
		{
			List<String> tableData=new ArrayList<String>();
			index1++;					
			String text=null;	
			for(int n=0;n<centre_rows.size();n++)
			{
				index2++;
				if(pageName.equalsIgnoreCase("Entity Screener")){
					text=driver.findElement(By.xpath(xpath+"["+index2+"]//td["+index1+"]//span")).getAttribute("class");
				} else if(pageName.equalsIgnoreCase("Receiver Dashboard"))
				{
					int count=driver.findElements(By.xpath(xpath+"["+index2+"]//td["+index1+"]"+xpath3)).size();
					if(!(count==0))
					text=driver.findElement(By.xpath(xpath+"["+index2+"]//td["+index1+"]"+xpath3)).getAttribute("ng-if");	 
				}
				else{
					text=driver.findElement(By.xpath(xpath+"["+index2+"]//td["+index1+"]/div")).getAttribute("class");
					if(text.equals("table-cell-icon relationship-icons")){
						text=driver.findElement(By.xpath(xpath+"["+index2+"]//td["+index1+"]/div/span")).getAttribute("class");				
					}
				}				 
				tableData.add( text);
			}
			index2=0;
			table1.put(finalHeaders.get(i), tableData);	
		}			
		return table1;
	}



	public List<String> getValueFromMapAsPerKey(String key) 
	{

		List<String> data=new ArrayList<String>();
		if(table1.containsKey(key))
		{
			data=table1.get(key);
			return data;
		}
		else
		{
			return data;
		}		
	}

	public void setFilter(WebDriver driver,String filterName,String entityName)
	{
		String xpath1=XPath.myprop.get("SDL.xpath.Filters1");
		String xpath2=XPath.myprop.get("SDL.xpath.Filters2");	
		String xpath=xpath1+filterName+xpath2;
		WebElement ele=driver.findElement(By.xpath(xpath));
		ele.click();
		//WebUtil.click("SDLquestionnaire.xpath.MyTrueLegalNameFilter");
		WebUtil.setWebEdit("SDL.xpath.FiltersInput", entityName);
		WebUtil.wait(8000);
		if(WebUtil.isElementPresent(driver, "SDL.xpath.FilterCheckbox"))
		{
			WebUtil.click("SDL.xpath.FilterCheckbox");
			WebUtil.wait(5000);
			WebUtil.click("SDL.xpath.DefocusFilter");
		}

	}
	


	public  void writeValueForKey(String testDataPath,String sheetName,String KeyToUpdate,String setValue){
		Workbook wb=	ExcelUtil.getWorkBookObject(testDataPath);
		Sheet sheetObj=wb.getSheet(sheetName);
		Row fstRowObj=sheetObj.getRow(0);
		int colNo=ExcelUtil.getColumnNumberByColumnName("Key", fstRowObj);
		int rowNo=ExcelUtil.getRowNumberByColName(sheetObj, colNo, KeyToUpdate);	
		ExcelUtil.setColumnDataByColName(testDataPath, sheetName, "Value", rowNo, setValue);
	}

	public void verifySDLEntPageLoad(WebDriver driver, String path, int sec){
		WebElement verifyPageLoaded=WebUtil.findElement(path, driver);
		(new WebDriverWait(driver, sec))
		.until(ExpectedConditions.visibilityOf(verifyPageLoaded));
	}

	public static void navigateToQuestLibrary(WebDriver driver){
		NavigationPage.setMcpmFrame(driver);	
		WebUtil.click("SDL.xpath.QuestLibrary");
		WebUtil.wait(5000);
	}

	public String addQuest(WebDriver driver){
		WebUtil.wait(5000);
		WebUtil.click("SDL.xpath.AddQuest");
		WebUtil.wait(5000);
		WebUtil.click("SDL.xpath.SDLQuest");
		WebUtil.wait(5000);
		String questRefName=WebUtil.generateRandomString(true, true, 5);
		WebUtil.setWebEdit("SDL.xpath.QuestRefName","Automation"+questRefName);
		WebUtil.wait(5000);
		return "Automation"+questRefName;
	}

	public void selectRadioBtn(WebDriver driver, String pathKey, String value){
		String selectRadiobtnPath =XPath.myprop.get(pathKey)+value+"']";
		WebElement radioBtn=driver.findElement(By.xpath(selectRadiobtnPath));
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.visibilityOf(radioBtn));
		radioBtn.click();
	}

	public void saveAndCloseQuest(WebDriver driver){
		WebUtil.click("SDL.xpath.CompleteQuest");
		WebUtil.wait(20000);
		WebUtil.click("SDLEntity.xpath.closeAfterSave");
	}

	public String createCAquest(WebDriver driver,Map<String,String> sdlTestData){
		String questName=addQuest(driver);
		WebUtil.click("SDL.xpath.QuestJurisdName");		
		WebUtil.click("SDL.xpath.selectCAQuest");		
		selectRadioBtn(driver,"SDLQuest.xpath.selectRadioButton",sdlTestData.get("canadianBranch"));
		saveAndCloseQuest(driver);
		return "CA-"+questName;
	}

	public String createEUquest(WebDriver driver,Map<String,String> sdlTestData){
		String questName=addQuest(driver);
		WebUtil.click("SDL.xpath.QuestJurisdName");	
		WebUtil.click("SDL.xpath.selctEUQuest");
		selectRadioBtn(driver,"SDLQuest.xpath.selectRadioButton",sdlTestData.get("euDSFguarantee"));
		selectRadioBtn(driver,"SDLQuest.xpath.selectRadioButton",sdlTestData.get("euBranches"));
		saveAndCloseQuest(driver);
		return "EU-"+questName;
	}
	public String createJPquest(WebDriver driver,Map<String,String> sdlTestData){
		String questName=addQuest(driver);
		WebUtil.click("SDL.xpath.QuestJurisdName");	
		WebUtil.click("SDL.xpath.selectJPQuest");
		selectRadioBtn(driver,"SDLQuest.xpath.selectRadioButton",sdlTestData.get("areJapanBranchTrans"));
		selectRadioBtn(driver,"SDLQuest.xpath.selectRadioButton",sdlTestData.get("isJapanBranch"));
		if(sdlTestData.get("areJapanBranchTrans").contains("Some or All Japan Branch Transactions")){		
			selectRadioBtn(driver,"SDLJP.xpath.selectHORadioBtn",sdlTestData.get("areHOoutsideJP"));
		}			
		saveAndCloseQuest(driver);
		return "JP-"+questName;
	}
	public String createUSquest(WebDriver driver,Map<String,String> sdlTestData){
		String questName=addQuest(driver);
		WebUtil.click("SDL.xpath.QuestJurisdName");	
		WebUtil.click("SDL.xpath.selectUSQuest");
		selectRadioBtn(driver,"SDLQuest.xpath.selectRadioButton",sdlTestData.get("cftcUSBranches"));
		selectRadioBtn(driver,"SDLQuest.xpath.selectRadioButton",sdlTestData.get("cftcUSGuarantees"));
		selectRadioBtn(driver,"SDLQuest.xpath.selectRadioButton",sdlTestData.get("prUSBranches"));
		selectRadioBtn(driver,"SDLQuest.xpath.selectRadioButton",sdlTestData.get("prUSGuarantees"));
		selectRadioBtn(driver,"SDLQuest.xpath.selectRadioButton",sdlTestData.get("swapsHedging"));
		saveAndCloseQuest(driver);
		return "US-"+questName;
	}

	public static void navigateToAS(WebDriver driver){	
		NavigationPage.setMcpmFrame(driver);
		WebUtil.click("SDL.xpath.AS");
		WebUtil.wait(5000);
		String text=WebUtil.readElementText(driver, "SDL.xpath.StatusFilter");
		ReportUtil.reportStringMatch("Verify Status Filter should be set to ACtive", "Active", text);		
	}

	public void addSignatoryLink(){
		WebUtil.click("SDL.xpath.AddSignatory");
		WebUtil.wait(5000);
	}

	public String addSignatory(WebDriver driver,Map<String,String> sdlTestData,String legalName){
		String AS=WebUtil.generateRandomString(true, true, 5);
		WebUtil.setWebEdit("SDL.xpath.ASname", "AutoAS"+AS);
		WebUtil.setWebEdit("SDL.xpath.AStitle", sdlTestData.get("AS_title"));
		WebUtil.setWebEdit("SDL.xpath.ASinstitution", sdlTestData.get("AS_institution"));		
		selectDropdownSDLEntity(driver, XPath.myprop.get("SDL.xpath.ASactingAsOptionSelect"), sdlTestData.get("AS_actingAs"), "SDL.xpath.ASactingAs"); 

		WebUtil.click("SDL.xpath.AStrueLegalFilter");
		WebUtil.wait(5000);
		WebUtil.setWebEdit("SDL.xpath.AStrueLegalSearchBox", legalName);      
		String chkboxPath1=XPath.myprop.get("SDL.xpath.AStrueLegalChkBox");
		String chkboxPath2=XPath.myprop.get("SDL.xpath.AStruelegalChkBox2");
		String chkboxPath=chkboxPath1+legalName+chkboxPath2;
		WebElement truelegalChkbox=driver.findElement(By.xpath(chkboxPath));
		truelegalChkbox.click();
		WebUtil.click("SDL.xpath.ASclickAnywhere");
		WebUtil.wait(5000);
		WebUtil.click("SDL.xpath.ASselectEntityChkBox");
		WebUtil.wait(5000);
		WebUtil.click("SDL.xpath.ASsave");
		WebUtil.wait(20000);
		WebUtil.click("SDLEntity.xpath.closeAfterSave");

		return "AutoAS"+AS;       
	}

	public void navigateToAddRelQuest(WebDriver driver){
		NavigationPage.setMcpmFrame(driver);
		WebUtil.click("SDLProd.xpath.ActionsButton");
		WebUtil.wait(2000);
		WebUtil.click("SDLProd.xpath.AddRelQuest");	
		WebUtil.wait(5000);
	}
	public void addQuestByJur(WebDriver driver,String jurName,String questName){
		String questPath=XPath.myprop.get("SDLAddQuest.xpath.selectQuest");

		switch(jurName){
		case "CA":
			WebUtil.click("SDLAddQuest.xpath.CADD");	
			WebElement selQues=driver.findElement(By.xpath(questPath+questName+"']"));	
			selQues.click();
			break;
		case "EU":
			WebUtil.click("SDLAddQuest.xpath.EUDD");	
			selQues=driver.findElement(By.xpath(questPath+questName+"']"));	
			selQues.click();	
			break;
		case "JP":
			WebUtil.click("SDLAddQuest.xpath.JPDD");	
			selQues=driver.findElement(By.xpath(questPath+questName+"']"));	
			selQues.click();	
			break;
		case "US":
			WebUtil.click("SDLAddQuest.xpath.USDD");	
			selQues=driver.findElement(By.xpath(questPath+questName+"']"));	
			selQues.click();
			break;
		}
	}

	public void clickOnEntityLink(WebDriver driver)
	{

		if(WebUtil.isElementPresent(driver, "MCPMSDL.xpath.EntityLink"))
		{
			WebUtil.click("MCPMSDL.xpath.EntityLink");
			WebUtil.wait(8000);
		}
	}
	public void clickOnbackToDashboardLink(WebDriver driver)
	{
		if(WebUtil.isElementPresent(driver, "MCPMSDL.xpath.BackToDashBoard"))
		{
			WebUtil.click("MCPMSDL.xpath.BackToDashBoard");
			WebUtil.wait(5000);
		}
	}

	public void clickOnEntityScreenerTab(WebDriver driver)
	{
		if(WebUtil.isElementPresent(driver, "MCPMSDL.xpath.EntityScreenerTab"))
		{
			WebUtil.click("MCPMSDL.xpath.EntityScreenerTab");
			WebUtil.wait(8000);
		}
	}

	public void clickOnSDLProducerTab(WebDriver driver)
	{
		if(WebUtil.isElementPresent(driver, "MCPMSDL.xpath.EntityScreenerTab"))
		{
			WebUtil.click("MCPMSDL.xpath.SDLProducerTab");
			WebUtil.wait(5000);
		}
	}


	public void editGeneralReg(WebDriver driver,String testDataPath){		
		clickOnEntityLink(driver);
		List<WebElement> addrEl=WebUtil.findElements("SDLThres.xpath.addr", driver);
		String text=addrEl.get(0).getAttribute("title");
		WebUtil.setWebEdit(addrEl.get(0), text+"updated");		
		saveSDLEntityInfo(driver);
		writeValueForKey(testDataPath, "GeneralReg", "addr",text+"updated");
		clickOnbackToDashboardLink(driver);
	}

	public List<String> editEntityJurDetails(WebDriver driver,String testDataPath){
		NavigationPage.setMcpmFrame(driver); 
		List<String> editedModules=new ArrayList<String>();
		clickOnEntityLink(driver);		
		WebUtil.wait(7000);
		WebUtil.click("SDL.xpath.USlink");
		//verifySDLEntPageLoad(driver, "SDLUS.xpath.cftcEntStatus", 10);
		String value="CFTC Financial End User";
		selectDropdownSDLEntity(driver, selectDropDownOptionPath,value,"SDLUS.xpath.cftcEntStatus");
		saveSDLEntityInfo(driver);
		writeValueForKey(testDataPath, "US", "US_cftcEntStatus",value);
		editedModules.add("United States CFTC Entity");

		WebUtil.click("SDL.xpath.CAlink");  
		//verifySDLEntPageLoad(driver, "SDLCA.xpath.isDomesticFRFI", 10);
		WebUtil.wait(7000);
		value="No";
		selectDropdownSDLEntity(driver, selectDropDownOptionPath,value,"SDLCA.xpath.isDomesticFRFI");
		saveSDLEntityInfo(driver);
		writeValueForKey(testDataPath, "CA", "CA_isDomesticFRFI",value);
		editedModules.add("Canada OSFI Entity");

		WebUtil.click("SDL.xpath.EUlink");  
		//verifySDLEntPageLoad(driver, "SDLEU.xpath.princEntType", 10);
		WebUtil.wait(7000);
		value="NFC+";
		selectDropdownSDLEntity(driver, selectDropDownOptionPath,value,"SDLEU.xpath.princEntType");
		saveSDLEntityInfo(driver);
		writeValueForKey(testDataPath, "EU", "EU_principalEntType",value);
		editedModules.add("EU Entity");

		WebUtil.click("SDL.xpath.CHlink");  
		WebUtil.wait(7000);
		value="a FMIA Article 93(4)(a) Entity";
		checkCheckboxSDLEntity(driver, "SDLCH.xpath.FMIAexemptEnt", value);
		value="a FMIA Article 93(4)(b) Entity";
		checkCheckboxSDLEntity(driver, "SDLCH.xpath.FMIAexemptEnt", value);
		saveSDLEntityInfo(driver);
		writeValueForKey(testDataPath, "CH", "CH_typeofFMIA",value);
		editedModules.add("Switzerland FMIA Entity");
	    System.out.println();

		WebUtil.click("SDL.xpath.JPlink"); 
		//	verifySDLEntPageLoad(driver, "SDLJP.xpath.entityOrgJP", 10);
		WebUtil.wait(7000);
		value="No";
		selectDropdownSDLEntity(driver, selectDropDownOptionPath,value,"SDLJP.xpath.entityOrgJP"); 
		saveSDLEntityInfo(driver);
		writeValueForKey(testDataPath, "JP", "JP_entOrganizedJP",value);
		editedModules.add("Japan Entity");

		return editedModules;
	}


	public void navigateToSignShare(){
		WebUtil.click("SDLProd.xpath.ActionsButton");
		WebUtil.click("SDLProd.xpath.SignShare");
		WebUtil.wait(5000);
	}

	public void shareSDLModules(){
		WebUtil.click("SDLsignshare.xpath.ShareButton");
		WebUtil.wait(60000);
	}




	public void navigateToUnshare(){
		WebUtil.click("SDLProd.xpath.ActionsButton");
		WebUtil.click("SDLProd.xpath.Unshare");
		WebUtil.wait(5000);
	}

	public void unshareSDLModules(){
		WebUtil.click("SDLsignshare.xpath.UnshareButton");
		WebUtil.wait(60000);
	}


	public void verifySignAndSharePopUp(WebDriver driver,String JurModule){
		switch("JurModule"){
		case "CAOSFI":			
			WebUtil.verifyElementDisplayed(driver, "SDLsignshare.xpath.CAOSFI");
		case "CAAANA":
			WebUtil.verifyElementDisplayed(driver, "SDLsignshare.xpath.CAAANA");
		case "EUENTITY":
			WebUtil.verifyElementDisplayed(driver, "SDLsignshare.xpath.EUentity");
		case "EUAANA":
			WebUtil.verifyElementDisplayed(driver, "SDLsignshare.xpath.EUAANA");
		case "JPENTITY":
			WebUtil.verifyElementDisplayed(driver, "SDLsignshare.xpath.JPEntity");
		case "JPAANA":
			WebUtil.verifyElementDisplayed(driver, "SDLsignshare.xpath.JPAANA");
		case "CHENTITY":
			WebUtil.verifyElementDisplayed(driver, "SDLsignshare.xpath.CHEntity");
		case "CHAANA":
			WebUtil.verifyElementDisplayed(driver, "SDLsignshare.xpath.CHAANA");
		case "USCFTC":
			WebUtil.verifyElementDisplayed(driver, "SDLsignshare.xpath.USCFTC");
		case "USPR":
			WebUtil.verifyElementDisplayed(driver, "SDLsignshare.xpath.USPR");
		case "USSWAP":
			WebUtil.verifyElementDisplayed(driver, "SDLsignshare.xpath.USswap");
		case "USAANA":
			WebUtil.verifyElementDisplayed(driver, "SDLsignshare.xpath.USAANA");
		}
	}

	public void getCenterTableDataOnEntityScreener(WebDriver driver)
	{		
		entityScreener.putAll(setMapValueforCentreTable(driver,"Entity Screener"));				
	}

	public Map<String, List<String>> getCenterTableDataOnSDLProducer(WebDriver driver)
	{				
		producerDashboard.putAll(setMapValueforCentreTable(driver,"Producer Dashboard"));	
		return producerDashboard;
	}

	public Map<String, List<String>> getCenterTableData(WebDriver driver,String pageName)
	{				
		dashboardData.putAll(setMapValueforCentreTable(driver,pageName));
		return dashboardData;	

	}

	public void verifySDLProducerDataMatchesEntityScreener()
	{
		for (String key: producerDashboard.keySet())
		{
			if (entityScreener.containsKey(key))
			{
				if((entityScreener.get(key).get(0)!=null && entityScreener.get(key).get(0).equals("checkbox_icon ng-scope checked")) &&
						(producerDashboard.get(key).get(0)!=null && producerDashboard.get(key).get(0).contains("icon-data-completed")))
				{	
					ReportUtil.reportStringContainsMatch("Producer Icons Verificaiton Pass_"+key, "icon-data-completed",producerDashboard.get(key).get(0));
				}
				else if(producerDashboard.get(key).get(0).equals(entityScreener.get(key).get(0)))
				{  
					ReportUtil.reportStringContainsMatch("Producer Icons Verificaiton Pass_"+key, producerDashboard.get(key).get(0), entityScreener.get(key).get(0));
				}
				else if((entityScreener.get(key).get(0)!=null && entityScreener.get(key).get(0).equals("checkbox_icon ng-scope")) &&
						(producerDashboard.get(key).get(0)!=null && producerDashboard.get(key).get(0).equals("table-cell-icon")))
				{
					ReportUtil.reportWebElementExist("Producer Icons Verificaiton Pass_"+key+"_is blank", "table-cell-icon", producerDashboard.get(key).get(0));
				}
				else
				{	 
					ReportUtil.reportWebElementExist("Producer Icons Verificaiton Fail_"+key, "icon-data-completed", producerDashboard.get(key).get(0));	    	  
				}	    	 
			}	     
		}
	}

	public void verifySDLProducerData(Map<String,List<String>> Expected,Map<String,List<String>> Actual,String typeOfIcon)
	{
		for (String key: Expected.keySet())
		{
			if((Actual.get(key).get(0).equals(typeOfIcon)))
			{
				ReportUtil.reportWebElementExist("Producer Icons Verificaiton Pass_"+key, typeOfIcon, producerDashboard.get(key).get(0));	  
			} 
			else if(Actual.get(key).get(0).equals(Expected.get(key).get(0))&&Actual.get(key).get(0).equals("")){
				ReportUtil.reportStringMatch("Producer Icon Before and After are same for"+key, Expected.get(key).get(0), Actual.get(key).get(0));
			}
			else{	 
				ReportUtil.reportWebElementExist("Producer Icons Verificaiton Fail_"+key, typeOfIcon, producerDashboard.get(key).get(0));	    	  
			}	    	 
		}	     
	}


	public void navToSelfDisclosureJurisdictions(WebDriver driver)
	{
		WebUtil.click("SDLNav.xpath.DashboardButton");
		WebUtil.wait(2000);
		WebUtil.click("SDLNav.xpath.SelfDisclosureJurisdictions");
		WebUtil.wait(5000);
		NavigationPage.setMcpmFrame(driver);
	}

	public void checkJurisdictionRequiredCheckBoxes(WebDriver driver,String countryInitials,String jurisdictionName)
	{
		WebUtil.click("SDLJurRequired.xpath.EditButton");
		String xpath1=XPath.myprop.get("SDLJurRequired.xpath.EditCheckBox1");
		String xpath2=XPath.myprop.get("SDLJurRequired.xpath.EditCheckBox2");
		String[] splited =jurisdictionName.split("\\s+");
		String jurName=null;
		String className=null;
		if(splited.length==2)
		{		
			jurName=splited[0].toUpperCase()+"_"+splited[1].toUpperCase();
			className=countryInitials.toUpperCase()+"_"+jurName.trim();

		}
		else
		{
			className=countryInitials.toUpperCase()+"_"+splited[0].toUpperCase();
		}
		String xpath=xpath1+className+xpath2;
		String att=driver.findElement(By.xpath(xpath)).getAttribute("class");
		if(att.equalsIgnoreCase("rs-icon rs-icon-checkbox ng-scope rs-icon-checkbox__active"))
		{
			WebElement ele=driver.findElement(By.xpath(xpath));
			ele.click();
			WebUtil.wait(5000);
		}	
		if(WebUtil.isElementPresent(driver, "SDLJurRequired.xpath.SaveAndSubmit"))
		{
			WebUtil.click("SDLJurRequired.xpath.SaveAndSubmit");
			WebUtil.wait(8000);
		}
	}



	public List<String> verifyTheRequestedJurisdictions(WebDriver driver,String pageName)
	{

		 List<String> colNamesJurRequired=new ArrayList<String>();
		 List<String> colNamesProducer=new ArrayList<String>();
		List<WebElement> elements=WebUtil.findElements("SDLJurRequired.xpath.CentreTableCols", driver);
		int size=elements.size();
		String xpath1,xpath2=null;

		if(pageName.equalsIgnoreCase("Jurisdiction Required"))
		{
			xpath1=XPath.myprop.get("SDLJurRequired.xpath.Request1");
			xpath2=XPath.myprop.get("SDLJurRequired.xpath.Request2");
		}
		else
		{
			xpath1=XPath.myprop.get("SDLJurRequired.xpath.RedIconsPath1");
			xpath2=XPath.myprop.get("SDLJurRequired.xpath.RedIconsPath2");
		}
		int index=0;
		String className=null;		
		boolean flag=false;
		List<WebElement> ele=null;
		for(int i=0;i<size;i++)
		{
			index++;			
			ele=driver.findElements(By.xpath(xpath1+index+xpath2));
			if(ele!=null&&ele.size()>0)
			{
				className=driver.findElement(By.xpath(xpath1+index+xpath2)).getAttribute("class");
				if(className.contains("column-"))
				{
					String[] col = className.split("column-");

					if(pageName.equalsIgnoreCase("Jurisdiction Required"))					
						colNamesJurRequired.add(col[1]);
					else					
						colNamesProducer.add(col[1]);	
				}
			}			
		}
		if(pageName.equalsIgnoreCase("Jurisdiction Required"))
			return colNamesJurRequired;
		else
			return colNamesProducer;

	}

	


	public void clearFilters(){
		WebUtil.click("SDL.xpath.ClearFilters");
		WebUtil.wait(5000);
	}

	public void navigateToSDLProducer(WebDriver driver){
		NavigationPage.setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "SDl.xpath.DashboardLink");
		WebUtil.clickedWithAction(driver, "SDL.xpath.ProducerLink");
		WebUtil.wait(12000);
	}

	public void validateQuestCreated(WebDriver driver, String questName){
		setFilter(driver, "Reference Name", questName);
		setMapValuesForLeftAndRightTable(driver, "SDLquestionnaire.xpath.LeftGridTable", "SDLquestionnaire.xpath.LeftGridRows");
		List<String> value=getValueFromMapAsPerKey("Reference Name");
		ReportUtil.reportStringMatch("Questionnaire Name is", questName, value.get(0));
		clearFilters();
	}

	public List<String> verifyIcons(WebDriver driver,String iconColor,String pageName)
	{
		Map<String,List<String>> icons=new HashMap<String,List<String>>();
		icons=getCenterTableData(driver,pageName);
		List<String> green=new ArrayList<String>();
		List<String> blue=new ArrayList<String>();
		for(String key:icons.keySet()){
			if(icons.get(key).get(0) != null)
			{
				if(icons.get(key).get(0).contains("icon-data-completed-and-shared")||icons.get(key).get(0).contains("received"))
				{
					green.add(key);
				}
				else if(icons.get(key).get(0).equals("table-cell-icon icon-data-completed icon-red-dot-small-after")||icons.get(key).get(0).equals("table-cell-icon icon-data-completed")||icons.get(key).get(0).equals("icon-data-completed"))
				{
					blue.add(key);			
				}
			}
		}
		if(iconColor.equalsIgnoreCase("Green"))
			return green;
		else
			return blue;

	}
	public void navToSelfDisclosureReceiver(WebDriver driver)
	{
		WebUtil.click("SDLNav.xpath.DashboardButton");
		WebUtil.wait(2000);
		WebUtil.click("SDLNav.xpath.SelfDisclosureReceiver");
		WebUtil.wait(15000);
		NavigationPage.setMcpmFrame(driver);
	}

	public List<String> readSignAndShareModules(WebDriver driver)
	{
		List<WebElement> ele=WebUtil.findElements("SDLSignAndShare.xpath.Blocks", driver);
		String xpath1=XPath.myprop.get("SDLSignAndShare.xpath.Blocks");
		String xpath2=XPath.myprop.get("SDLSignAndShare.xpath.CountryName");       
		String xpath3=XPath.myprop.get("SDLSignAndShare.xpath.JurisdticionModule");
		int index1=1,index2=1;
		System.out.println("Size: "+ele.size());
		List<String> modulesName=new ArrayList<String>();      
		List<WebElement> moduleElements=new ArrayList<WebElement>();
		String countryName=null;
		String jurdictionName=null;
		String att=null;
		for(int i=0;i<ele.size();i++)
		{      countryName=driver.findElement(By.xpath(xpath1+"["+index1+"]"+xpath2)).getText();
		moduleElements=driver.findElements(By.xpath(xpath1+"["+index1+"]"+xpath3));               
		for(int j=0;j<moduleElements.size();j++)
		{
			att=driver.findElement(By.xpath(xpath1+"["+index1+"]"+xpath3+"["+index2+"]"+"//span")).getAttribute("class");
			if(att.equalsIgnoreCase("checkbox_icon ng-pristine ng-untouched ng-valid checked"))
			{
				jurdictionName=driver.findElement(By.xpath(xpath1+"["+index1+"]"+xpath3+"["+index2+"]"+"//p")).getText();
				if(countryName.equals("European Union"))
				{
					countryName="EU";
				} 
				String countJur=countryName+" "+jurdictionName;
				if (countJur.equals("United States CFTC")||(countJur.equals("United States PR"))){                          	
					countJur=countJur+" Entity";
				}
				modulesName.add(countJur);
				index2++;
			}
		}
		index2=1;
		index1++;                          
		}       
		return modulesName;
	}

	public List<String> relationshipKeys(List<String> actualGrid){
		List<String> relKeyList=new ArrayList<String>();
		for(String key:actualGrid){
			if(key.contains("Entity")||key.contains("Swap")){
				StringTokenizer st=new StringTokenizer(key," ");
				String countryName=st.nextToken();
				String relKeyName=null;
				if(countryName.equals("United")){
					relKeyName=countryName+" States Rel";
				}else{
					relKeyName=countryName+" Rel";
				}
				if(!relKeyList.contains(relKeyName))
					relKeyList.add(relKeyName);	    	
			}
		}
		for(String key:relKeyList)
		{
			ReportUtil.reportWebElement("Removed Keys from the list "+key, true);
		}
		return relKeyList;
	}
	
	public String readTheSplitedText(String sheetName,String keyName)
	{
		Map<String,String> sdlTestData=new HashMap<String,String>();
		sdlTestData=readData("src/testdata/SDL_TestData.xlsx",sheetName,sdlTestData);		 
		String value=sdlTestData.get(keyName);
		String [] strArr=new String[2];
		if(value.contains("_"))
		{
			strArr=value.split("_");
			return strArr[1];
		}
		else
		{
		strArr[0]=	value;
		return strArr[0];
		}
	}
	public void exitSDL()
	{
		WebUtil.click("SDL.xpath.CounterpartyManager");
		WebUtil.wait(7000);
	}
	
	public void setCPTrueLegalNameFilter(WebDriver driver,String filterName,String entityName)
	{
		String xpath1=XPath.myprop.get("SDL.xpath.Filters1");
		String xpath2=XPath.myprop.get("SDL.xpath.Filters2");	
		String xpath=xpath1+filterName+xpath2;
		WebElement ele=driver.findElement(By.xpath(xpath));
		ele.click();
		//WebUtil.click("SDLquestionnaire.xpath.MyTrueLegalNameFilter");
		WebUtil.setWebEdit("SDL.xpath.FiltersInput", entityName);
		WebUtil.wait(8000);
		if(WebUtil.isElementPresent(driver, "SDLFilter.xpath.CPTrueLegalName"))
		{
			WebUtil.click("SDLFilter.xpath.CPTrueLegalName");
			WebUtil.wait(5000);
			WebUtil.click("SDL.xpath.DefocusFilter");
		}

	}
	
	public void clearFilter()
	{
		WebUtil.click("SDLFilter.xpath.ClearFilter");
		WebUtil.wait(8000);
	}
	
	public void clickonExportResult()
	{
		WebUtil.click("SDL.xpath.ExportResult");
		WebUtil.wait(6000);
	}
	public Map<String,String> getAPIDetails(Connection con,String myEntityName,String cpEnytityName)
	{
	
		Map<String,String> data=new HashMap<String,String>();
	
		if(con!=null)
		{	
			
			data=DBUtility.getAllColNamesAndValues(con, "select \n" +
					"A.ENTITY_RELATION_ID AS relationshipId,\n" +
					"BMY.LEI AS lei,\n" +
					"BMY.ENTITY_NAME AS trueLegalName,\n" +
					"CL.MCPM_CLIENT_ID AS institutionId,\n" +
					"CL.NAME AS institutionName,\n" +
					"BCPTY.lei as cptyLei,\n" +
					"BCPTY.ENTITY_NAME as cptyLegalName,\n" +
					"CLCPTY.MCPM_CLIENT_ID AS cptyInstitutionId,\n" +
					"CLCPTY.NAME AS cptyInstitutionName\n" +
					"from \n" +
					"RS.ENTITY_RELATION A left outer join s_client_entity_screener BMY on (A.FROM_ENTITY_ID=BMY.ENTITY_ID and A.FROM_CLIENT_ID=BMY.CLIENT_ID)\n" +
					"left outer join s_client_entity_screener BCPTY on (A.TO_ENTITY_ID=BCPTY.ENTITY_ID and A.TO_CLIENT_ID=BCPTY.CLIENT_ID)\n" +
					"left outer join kyc.client CL on (BMY.CLIENT_ID=CL.CLIENT_ID)\n" +
					"left outer join kyc.client CLCPTY on (BCPTY.CLIENT_ID=CLCPTY.CLIENT_ID)\n" +
					"where\n" +
					"BMY.ENTITY_NAME='"+cpEnytityName+"' and BCPTY. ENTITY_NAME ='"+myEntityName+"'");
			
		}
			
		return data;
	}
	
	public void signAndShareAll(WebDriver driver)
	{
		if((WebUtil.getSizeOfElement(driver, "SDLProd.xpath.CheckBox"))!=0)
		{
		WebUtil.click("SDLProd.xpath.CheckBox");
		}
		navigateToSignShare();
		shareSDLModules();
	}
	
	public void writeValuesInExcelforAPIValidation(Connection con,String myEntity,String cpEntity,String testDataPath)
	{
		Map<String ,String> data=new HashMap<String,String>();
		data=getAPIDetails(con,myEntity,cpEntity);		
		String sheetName="CPEntDetails";
		writeValueForKey(testDataPath, sheetName, "CptyComName",data.get("CPTYINSTITUTIONNAME"));
		writeValueForKey(testDataPath, sheetName, "myComName",data.get("INSTITUTIONNAME"));
		writeValueForKey(testDataPath, sheetName, "CptyEntName",data.get("CPTYLEGALNAME"));
		writeValueForKey(testDataPath, sheetName, "MyEntName",data.get("TRUELEGALNAME"));
		writeValueForKey(testDataPath, sheetName, "myLEI",data.get("LEI"));
		writeValueForKey(testDataPath, sheetName, "cptyLEI",data.get("CPTYLEI"));		
		writeValueForKey(testDataPath, sheetName, "relationshipIdDb",data.get("RELATIONSHIPID"));
		writeValueForKey(testDataPath, sheetName, "instIdDb",data.get("INSTITUTIONID"));
		writeValueForKey(testDataPath, sheetName, "cptyInstIdDb",data.get("CPTYINSTITUTIONID"));
		
		
	}
	
	public String readRelIDFromExcel()	{
		String filePath="src/testdata/SDL_TestData.xlsx";
		Map<String,String> sdlTestData=new HashMap<String,String>();
		sdlTestData=readData(filePath,"CPEntDetails",sdlTestData);		 
		String relID=sdlTestData.get("relationshipIdDb");
		return relID;
	}
	
	public  List<String> getSpecificColData(String testDataPath,String SheetName,String columnName)
	{
		Workbook wbookObj=ExcelUtil.getWorkBookObject(testDataPath);
		Sheet sheetObj=wbookObj.getSheet(SheetName);
		Row row =sheetObj.getRow(0);
		List<String> cells = new ArrayList<String>();
		String cellValue=null;
		int columnIndex=0;
		for(Cell cell:row)
		{
			if(cell.getStringCellValue().equalsIgnoreCase(columnName))
			{
				columnIndex=cell.getColumnIndex();
				break;
			}
		}
		if(columnIndex != 0)
		{
			for(Row rows: sheetObj)
			{
				Cell c = rows.getCell(columnIndex);
				if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK)
				{
				} 
				else
				{
					DataFormatter formatter = new DataFormatter();
					cellValue=formatter.formatCellValue(c);
					if(!cellValue.equalsIgnoreCase(columnName) && !cellValue.equalsIgnoreCase("null"))
						cells.add(cellValue.trim());	
				}
			}
		}
		else
		{
			ReportUtil.reportWebElement("Passed column name does not exit",false);
		}
		return cells;		
	}

	public  void compareTwoColsofSameExcel(String testDataPath,String SheetName,String colName1,String colName2)
	{
		List<String> col1=new ArrayList<String>();
		List<String> col2=new ArrayList<String>();
		col1=getSpecificColData(testDataPath, SheetName, colName1);	
		col2=getSpecificColData(testDataPath, SheetName, colName2);
		String col1Size=String.valueOf(col1.size());
		String col2Size=String.valueOf(col2.size());
		if(col1.size()==col2.size())
		{		
				for(int i=0;i<col1.size();i++)
				{	
					if(!col2.get(i).trim().contains("[")){
					ReportUtil.reportStringMatch("Verify the UI value match API response", col1.get(i).trim(), col2.get(i).trim());
					}
					else if(col1.get(i).trim().contains("_")){
						String[] col1Arr=col1.get(i).trim().split("_");
						String col2NewVal=col2.get(i).trim().replace("[","");
						col2NewVal=col2NewVal.trim().replace("]","");
						String[] col2Arr=col2NewVal.trim().split(",");
						Arrays.sort(col1Arr);
						Arrays.sort(col2Arr);
						for(int j=0;j<col1Arr.length;j++){
					   ReportUtil.reportStringMatch("Verify the multiple values match list of API response", col1Arr[j].trim(), col2Arr[j].trim());	
						}    
					    }
					else if(col2.get(i).trim().contains("[")&& !(col1.get(i).trim().contains("_"))){
						String col2NewVal=col2.get(i).trim().replace("[","");
						col2NewVal=col2NewVal.trim().replace("]","");
						String col1NewVal=col1.get(i).trim().replace("[","");
						col1NewVal=col1.get(i).trim().replace("]","");
						ReportUtil.reportStringMatch("Verify the multiple values match list of API response", col1NewVal.trim(),col2NewVal.trim());
					}
				}			
		}
		else
		{
			ReportUtil.reportStringMatch("The columns size do not match", col1Size, col2Size);			
		}
	}
}


