package com.markit.mcpm.framework.testcases;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;
import com.markit.framework.api.pageMethods.TokenGeneration;
import com.markit.framework.api.testcases.SDLAPI_TC;
import com.markit.framework.mcpm.pageMethods.EntityPage;
import com.markit.framework.mcpm.pageMethods.MCPMLoginpage;
import com.markit.framework.mcpm.pageMethods.NavigationPage;
import com.markit.framework.mcpm.pageMethods.SDLPage;

public class SDLTC extends SDLPage {


	SDLPage page=new SDLPage();
	NavigationPage nav=new NavigationPage();
	EntityPage e=new EntityPage();
	//Map<String,List<String>>blueIcons=new HashMap<String,List<String>>();
	Map<String,List<String>>greenIcons=new HashMap<String,List<String>>();
	static String CAquestName=null;
	static String EUquestName=null;
	static String JPquestName=null;
	static String USquestName=null;
	static List<String>colNamesJurRequired=new ArrayList<String>();
	static List<String>colNamesProducer=new ArrayList<String>();
	String testDataPath="src/testdata/SDL_TestData.xlsx";
	String testDataEditedPath="src/testdata/SDL_TestData_Edited.xlsx";
	public List<String> tc_createCPEnt(WebDriver driver,List<String> cpEntitiesName){
		String sheetName="EntityDetails";
		EntityTC entTc=new EntityTC();
		SDLTC sdl=new SDLTC();
		for(int i=0;i<=1;i++)
		{
			String entityName=entTc.createEntity(driver);
			cpEntitiesName.add(entityName);
			sdl.enterLEIforCPEntities(driver, cpEntitiesName.get(i));
			System.out.println("CP Entity  Name: "+cpEntitiesName.get(i));
		}		
		page.writeValueForKey(testDataPath, sheetName, "CptyEntName",cpEntitiesName.get(0)+"_"+cpEntitiesName.get(1));
		page.writeValueForKey(testDataPath, sheetName, "CptyComName",MCPMLoginpage.companyName);
		return cpEntitiesName;
	}

	public void fillSDLEntityData(WebDriver driver,String entityName){
		Map<String,String> sdlTestData=new HashMap<String,String>();
		String sheetName=null;
		try{
			sheetName="GeneralReg";
			sdlTestData=page.readData(testDataPath,sheetName,sdlTestData);
			page.fillGeneralReg(driver, sdlTestData);
		}catch(Exception e){
			e.printStackTrace();
			page.saveSDLEntityInfo(driver);
		}
		try{
			sheetName="US";
			sdlTestData=page.readData(testDataPath,sheetName,sdlTestData);
			page.fillUSEntDetails(driver, sdlTestData);
		}catch(Exception e){
			e.printStackTrace();
			page.saveSDLEntityInfo(driver);
		}
		try{
			sheetName="CA";
			sdlTestData=page.readData(testDataPath,sheetName,sdlTestData);
			page.fillCAEntDetails(driver, sdlTestData); 
		}  catch(Exception e){
			e.printStackTrace();
			page.saveSDLEntityInfo(driver);
		}
		try{
			sheetName="EU";
			sdlTestData=page.readData(testDataPath,sheetName,sdlTestData);
			page.fillEUEntDetails(driver, sdlTestData);}
		catch(Exception e){
			e.printStackTrace();
			page.saveSDLEntityInfo(driver);
		}
		try{
			sheetName="JP";
			sdlTestData=page.readData(testDataPath,sheetName,sdlTestData);
			page.fillJPEntDetails(driver, sdlTestData);}
		catch(Exception e){
			e.printStackTrace();
			page.saveSDLEntityInfo(driver);
		}
		try{
			sheetName="CH";
			sdlTestData=page.readData(testDataPath,sheetName,sdlTestData);
			page.fillCHEntDetails(driver, sdlTestData); }

		catch(Exception e){
			e.printStackTrace();
			page.saveSDLEntityInfo(driver);
		}

	}
	public void enterLEIforCPEntities(WebDriver driver,String entityName)
	{
		nav.navigateToManageEntities(driver);		
		e.setAndApplyFilter(driver, entityName,"Entity.name.LegalNameFilter");
		e.clickEditEntity(driver);		
		e.clickOnRegulatoryChevron(driver);
		e.fillLEI(driver);
		e.exitfromRehulatoryPage();		
	}

	public void tc_createCPRelForMyEntity(WebDriver driver,String myEntityname,String cpCompanyName,String cpEntityName)
	{  
	String sheetName="EntityDetails";
	page.writeValueForKey(testDataPath, sheetName, "MyEntName",myEntityname);
	nav.navigateToManageEntities(driver);
	e.setAndApplyFilter(driver, myEntityname,"Entity.name.LegalNameFilter");
	e.clickEditEntity(driver);		
	e.clickOnRegulatoryChevron(driver);
	e.fillLEI(driver);		
	e.addCP(driver,cpCompanyName, cpEntityName);
	e.exitfromRehulatoryPage();			
	}
	
	public void tc_fillDetailsAndVerifyEntityScreenerwithProducerDashbaord(WebDriver driver,String entityName)
	{
		NavigationPage.setMcpmFrame(driver);
		nav.navigateToSDL(driver);
		page.clickOnEntityScreenerTab(driver);
		page.setFilter(driver, "True Legal Name",entityName);		
		page.clickOnEntityLink(driver);
		fillSDLEntityData(driver, entityName);		
		page.clickOnbackToDashboardLink(driver);
		page.getCenterTableDataOnEntityScreener(driver);
		page.clickOnSDLProducerTab(driver);
		page.setFilter(driver, "True Legal Name",entityName);
		page.getCenterTableDataOnSDLProducer(driver);
		page.verifySDLProducerDataMatchesEntityScreener();		

	}

	public void tc_verifyRelIconOnSDLProducer(WebDriver driver){
		page.getCenterTableDataOnSDLProducer(driver);	
		List<String> ca_Rel_text = new ArrayList<String>();
		List<String> eu_Rel_text = new ArrayList<String>();
		List<String> jp_Rel_text = new ArrayList<String>();
		List<String> us_Rel_text = new ArrayList<String>();		
		 ca_Rel_text=page.getValueFromMapAsPerKey("Canada Rel");
		 eu_Rel_text=page.getValueFromMapAsPerKey("EU Rel");
		 jp_Rel_text=page.getValueFromMapAsPerKey("Japan Rel");
		us_Rel_text=page.getValueFromMapAsPerKey("United States Rel");
		ReportUtil.reportStringContainsMatch("Verify red dot jur required on SDL producer for CA REl", "icon-data-completed", ca_Rel_text.get(0));
		ReportUtil.reportStringContainsMatch("Verify red dot jur required on SDL producer for EU Rel", "icon-data-completed", eu_Rel_text.get(0));
		ReportUtil.reportStringContainsMatch("Verify red dot jur required on SDL producer for JP Rel", "icon-data-completed", jp_Rel_text.get(0));
		ReportUtil.reportStringContainsMatch("Verify red dot jur required on SDL producer for US Rel", "icon-data-completed", us_Rel_text.get(0));
	}
	
	public void tc_fillSDLQuest(WebDriver driver){
		SDLPage page=new SDLPage();
		Map<String,String> sdlTestData=new HashMap<String,String>();
		String sheetName="Ques";
		sdlTestData=page.readData(testDataPath,sheetName,sdlTestData);

		SDLPage.navigateToQuestLibrary(driver);
		try{
			CAquestName=page.createCAquest(driver, sdlTestData);
			page.validateQuestCreated(driver, CAquestName);
		}catch(Exception e){
			e.printStackTrace();
			page.saveAndCloseQuest(driver);
		}
		try{
			EUquestName=page.createEUquest(driver, sdlTestData);		
			page.validateQuestCreated(driver, EUquestName);
		}catch(Exception e){
			e.printStackTrace();
			page.saveAndCloseQuest(driver);
		}
		try{
			JPquestName=page.createJPquest(driver, sdlTestData);
			page.validateQuestCreated(driver, JPquestName);
		}catch(Exception e){
			e.printStackTrace();
			page.saveAndCloseQuest(driver);
		}
		try{
			USquestName=page.createUSquest(driver, sdlTestData);
			page.validateQuestCreated(driver, USquestName);

		}catch(Exception e){
			e.printStackTrace();
			page.saveAndCloseQuest(driver);
		}
		 sheetName="QuesRefName";
		 page.writeValueForKey(testDataPath, sheetName, "caQuestName", CAquestName);
		 page.writeValueForKey(testDataPath, sheetName, "euQuestName", EUquestName);
		 page.writeValueForKey(testDataPath, sheetName, "jpQuestName", JPquestName);
		 page.writeValueForKey(testDataPath, sheetName, "usQuestName", USquestName);
	}

	public void tc_addSignatory(WebDriver driver,String entityName){
		SDLPage page=new SDLPage();
		Map<String,String> sdlTestData=new HashMap<String,String>();
		String sheetName="AS";
		sdlTestData=page.readData(testDataPath,sheetName,sdlTestData);
		SDLPage.navigateToAS(driver);
		page.addSignatoryLink();
		String signatory=page.addSignatory(driver, sdlTestData, entityName);
		page.writeValueForKey(testDataPath, sheetName, "AS_name", signatory);
		page.writeValueForKey(testDataEditedPath, sheetName, "AS_name", signatory);
		page.setFilter(driver, "Name", signatory);
		page.setMapValuesForLeftAndRightTable(driver, "SDLquestionnaire.xpath.LeftGridTable", "SDLquestionnaire.xpath.LeftGridRows");
		List<String> value=page.getValueFromMapAsPerKey("Name");
		ReportUtil.reportStringMatch("Verified that AS is created", signatory, value.get(0));
	}


	public void tc_addRelQuest(WebDriver driver){
		SDLPage page=new SDLPage();
		Map<String,String> sdlTestData=new HashMap<String,String>();
		String sheetName="QuesRefName";
		sdlTestData=page.readData(testDataPath,sheetName,sdlTestData);	
		page.navigateToSDLProducer(driver);
		WebUtil.click("SDLProd.xpath.CheckBox");
		page.navigateToAddRelQuest(driver);		
		page.addQuestByJur(driver, "CA", sdlTestData.get("caQuestName"));
		page.addQuestByJur(driver, "EU", sdlTestData.get("euQuestName"));
		page.addQuestByJur(driver, "JP", sdlTestData.get("jpQuestName"));
		page.addQuestByJur(driver, "US", sdlTestData.get("usQuestName"));
		WebUtil.click("SDLAddQuest.xpath.ApplyQuest");
		WebUtil.wait(5000);
		WebUtil.click("SDLAddQuest.xpath.Yesbutton");
		WebUtil.wait(5000);
	}

	public void tc_signAndShareAndVerify(WebDriver driver){
		List<String>expected=new ArrayList<String>();
		List<String>actual=new ArrayList<String>();
		List<String> relKeyList=new ArrayList<String>();
		NavigationPage.setMcpmFrame(driver);	
		page.navigateToSignShare();
		expected=page.readSignAndShareModules(driver);
		page.shareSDLModules();
		actual=page.verifyIcons(driver, "Green", "Producer Dashboard");
		System.out.println("Expected: "+expected.size());
		System.out.println("Actual: "+actual.size());
		relKeyList=page.relationshipKeys(actual);
		actual.removeAll(relKeyList);

		if(expected.size()==actual.size()){  	
			if(actual.containsAll(expected))
			{
				for(int i=0;i<actual.size();i++)
				{
					ReportUtil.reportWebElement("Shared Modules are "+ expected.get(i) , true);
					System.out.println("Expected: "+expected.get(i)+" "+"Actual"+actual.get(i));
				}
			}else
			{
				ReportUtil.reportWebElement("Shared Modules Does not Match", false);
			}
		}	   
		else
		{
			ReportUtil.reportWebElement("Shared Modules Does not Match", false);
		}

	}

	public void tc_unShare(WebDriver driver){
		
		List<String>greenIcon=new ArrayList<String>();
		List<String>blueIcon=new ArrayList<String>();
		greenIcon=page.verifyIcons(driver, "Green", "Producer Dashboard");
		page.navigateToUnshare();
		page.unshareSDLModules();
		blueIcon=page.verifyIcons(driver, "Blue", "Producer Dashboard");
		System.out.println("Size of blue: "+blueIcon.size());
		System.out.println("Size of green: "+greenIcon.size());
		if(blueIcon.size()==greenIcon.size())
		{
			if(blueIcon.containsAll(greenIcon))
			{
				for(int i=0;i<greenIcon.size();i++)
				ReportUtil.reportStringMatch("The unshared modules are", greenIcon.get(i), blueIcon.get(i));;					
				}
				else
				{
					System.out.println("The unshared Modules does not match: "+blueIcon.size()+" Green: "+greenIcon.size());
					ReportUtil.reportWebElement("The unshared Modules does not match", false);
				}				
			}
		}
	
	public void tc_editEntDetails(WebDriver driver){
		SDLPage page=new SDLPage();			
		List<String> editedModules=new ArrayList<String>();
		List<String> blueIconOnPD=new ArrayList<String>();
		List<String> remove=new ArrayList<String>();
		editedModules=page.editEntityJurDetails(driver,testDataEditedPath);		
		page.clickOnbackToDashboardLink(driver);
		blueIconOnPD=page.verifyIcons(driver, "Blue", "Producer Dashboard");
		remove=page.relationshipKeys(blueIconOnPD);
		blueIconOnPD.removeAll(remove);
		
		if(blueIconOnPD.size()==editedModules.size())
		{		
				if(blueIconOnPD.containsAll(editedModules))
				{
					for(String key:blueIconOnPD)
					ReportUtil.reportWebElement("Edited Modules "+key, true);
				}
		}
		else
		{
			ReportUtil.reportWebElement("Edited modules does not match", false);
		}		
	}

	public void tc_editGeneralReg(WebDriver driver,String entityName){
		SDLPage page=new SDLPage();
		List<String> greenIcon=new ArrayList<String>();
		List<String> blueIcon=new ArrayList<String>();		
		greenIcon=page.verifyIcons(driver, "Green", "Producer Dashboard");
		page.editGeneralReg(driver,testDataEditedPath);
		blueIcon=page.verifyIcons(driver, "Blue", "Producer Dashboard");
		
		if(greenIcon.size()==blueIcon.size())
		{
			for(int i=0;i<greenIcon.size();i++)
			ReportUtil.reportStringMatch("Blue icons", greenIcon.get(i), blueIcon.get(i));;		
		}
		else
		{
			ReportUtil.reportWebElement("Count does not match", false);
		}		

	}

	public void checkJurisdictionRequired(WebDriver driver,String cpEntityName)
	{
		WebUtil.wait(5000);
		nav.navigateToSDL(driver);
		SDLPage page=new SDLPage();
		page.navToSelfDisclosureJurisdictions(driver);		
		page.setFilter(driver, "True Legal Name", cpEntityName);
		page.checkJurisdictionRequiredCheckBoxes(driver, "US", "CFTC Entity");
		page.checkJurisdictionRequiredCheckBoxes(driver, "US", "AANA");
		page.checkJurisdictionRequiredCheckBoxes(driver, "EU", "Entity");
		page.checkJurisdictionRequiredCheckBoxes(driver, "EU", "AANA");
		colNamesJurRequired=page.verifyTheRequestedJurisdictions(driver,"Jurisdiction Required");
		
	}
	public void checkRedDotsOnProducerPage(WebDriver driver,String cpEntityNam)
	{
		SDLPage page=new SDLPage();
		colNamesProducer=page.verifyTheRequestedJurisdictions(driver,"Producer Dashboard");
		if(colNamesProducer.size()==colNamesJurRequired.size())
		{		
			if(colNamesProducer.containsAll(colNamesJurRequired))
			{
				for(int i = 0; i < colNamesProducer.size(); i++)
					ReportUtil.reportStringMatch("The Matched Values are: ", colNamesJurRequired.get(i), colNamesProducer.get(i));
			}
		}
		else
		 ReportUtil.reportWebElement("The Jurisdictions Required does not match", false);
	}
	

	public void createCPRelWithSecondEntity(WebDriver driver)
	{
		page.exitSDL();
		String cpEntityName=page.readTheSplitedText("EntityDetails", "CptyEntName");
		String myEntityname=page.readTheSplitedText("EntityDetails", "MyEntName");
		String cpCompanyName=page.readTheSplitedText("EntityDetails", "CptyComName");
		System.out.println("Second entity name: "+cpEntityName);		
		nav.navigateToManageEntities(driver);
		e.setAndApplyFilter(driver, myEntityname,"Entity.name.LegalNameFilter");
		e.clickEditEntity(driver);		
		e.clickOnRegulatoryChevron(driver);
		e.addCP(driver,cpCompanyName, cpEntityName);
		e.exitfromRehulatoryPage();		
		
	}
	
	public void verifySecondEntityonSDL(WebDriver driver)
	{
		String cpEntityName=page.readTheSplitedText("EntityDetails", "CptyEntName");
		nav.navigateToSDL(driver);
     	page.setCPTrueLegalNameFilter(driver, "Cpty True Legal Name", cpEntityName);
		List<String> blue=new ArrayList<String>();
		blue=page.verifyIcons(driver, "Blue", "Producer Dashboard");
		for(String key:blue)
		{
			ReportUtil.reportWebElement("Blue icon: "+key, true);
		}
	}
	
	public void verifyTheReceiverDashboard(WebDriver driver,String cpName,String tcName)
	{
		page.clearFilter();
		String myEntityname=page.readTheSplitedText("EntityDetails", "MyEntName");	
		page.setFilter(driver, "My True Legal Name", myEntityname);
		page.setCPTrueLegalNameFilter(driver, "Cpty True Legal Name", cpName);
		List<String> greenProducer=new ArrayList<String>();
		List<String> greenReceiver=new ArrayList<String>();
		greenProducer=page.verifyIcons(driver, "Green", "Producer Dashboard");		
		WebUtil.closeBrowser(driver);
		driver=MCPMLoginTC.tc_03_Login_MCPM(tcName,"src/testdata/testWorkbook.xlsx","Login");
		nav.navigateToSDL(driver);
		page.navToSelfDisclosureReceiver(driver);
		page.setFilter(driver, "My True Legal Name", cpName);
		greenReceiver=page.verifyIcons(driver, "Green", "Receiver Dashboard");
		System.out.println("Size of Producer: "+greenProducer.size()+" Size of Receiver: "+greenReceiver.size());
		if(greenReceiver.size()==greenProducer.size())
		{
			
				for(int i=0;i<greenReceiver.size();i++)
				{
					ReportUtil.reportStringMatch("The green icons", greenProducer.get(i), greenReceiver.get(i));					
				}
		}
		else
		{
			ReportUtil.reportWebElement("Values do not match", false);
		}
				
	}
	
	public void verifyActionrequired(WebDriver driver)
	{
		Map<String,List<String>> rightTable=new HashMap<String,List<String>>();
		rightTable=page.setMapValuesForLeftAndRightTable(driver, "MCPMSDL.xpath.GridRightTableHeaders", "MCPMSDL.xpath.GridRightTableRows");
		List<String> value=new ArrayList<String>();
		value=page.getValueFromMapAsPerKey("Action Needed");
		if((value.get(0)!=null)&&value.get(0).equalsIgnoreCase("icon-data-exclamation ng-scope"))
		{
			ReportUtil.reportStringMatch("Verify Action required", "icon-data-exclamation ng-scope", value.get(0));
		}
		else
		{
			ReportUtil.reportWebElement("Action required does not exits", false);
		}	
	}
	
	public void downloadExportSheet(WebDriver driver)
	{
		String expected=WebUtil.readElementText(driver, "SDL.xpath.FilteredRelCount");
		page.clickonExportResult();		
		String path=WebUtil.downloadFolderPathAsperCurrentUser(driver);	
		System.out.println("Intial path: "+path);
		String date=WebUtil.getTheCurrentDateAsperpassedFormat("dd-MMM-yyyy");
		String fileName="SDLProducer_"+date;
		String finalPath=path+"/"+fileName+".xlsx";
		int count=ExcelUtil.countOfTheRowsInSheet(finalPath, fileName);
		String actual=Integer.toString(count);
		ReportUtil.reportWebElementExist("The count of excel sheet row", expected, actual);			
	}
	
	public void verifyAPIResponseAfterRelCreated(Connection con,String myEntity,String cpEntity) throws Exception
	{
		String testDataPath="src/testdata/SDL_TestData.xlsx";
		page.writeValuesInExcelforAPIValidation(con,myEntity,cpEntity,testDataPath);
		testDataPath="src/testdata/SDL_TestData_Edited.xlsx";
		page.writeValuesInExcelforAPIValidation(con,myEntity,cpEntity,testDataPath);
		String relID=page.readRelIDFromExcel();
		SDLAPI_TC api=new SDLAPI_TC();
		TokenGeneration tok=new TokenGeneration(); 
		String token = tok.generateToken("sdlGenerateTokenURL","sdlUserName","sdlPassword");
		StringBuffer result = new StringBuffer();
		result=api.tc_getSDLAPI_relId(token, relID);
		api.tc_readAndWriteAPIResponse(result, "src/testdata/SDL_TestData.xlsx", "CPEntDetails");
		api.tc_readAndWriteAPIResponse(result, "src/testdata/SDL_TestData_Edited.xlsx", "CPEntDetails");
		page.compareTwoColsofSameExcel("src/testdata/SDL_TestData.xlsx", "CPEntDetails", "Value", "API_Value");		
	}
	
	public void verifyAPIResponseAfterSignAndShare(String filePath,String filePathForGenReg) throws Exception
	{		
		
		String relID=page.readRelIDFromExcel();
		SDLAPI_TC api=new SDLAPI_TC();
		TokenGeneration tok=new TokenGeneration(); 
		String token = tok.generateToken("sdlGenerateTokenURL","sdlUserName","sdlPassword");
		StringBuffer result = new StringBuffer();
		result=api.tc_getSDLAPI_relId(token, relID);
		api.tc_readAndWriteAPIResponse(result, filePath, "CPEntDetails");
		page.compareTwoColsofSameExcel(filePath, "CPEntDetails", "Value", "API_Value");
		api.tc_readAndWriteAPIResponse(result, filePathForGenReg, "GeneralReg");
		page.compareTwoColsofSameExcel(filePathForGenReg, "GeneralReg", "Value", "API_Value");
		api.tc_readAndWriteAPIResponse(result, filePath, "US");
		page.compareTwoColsofSameExcel(filePath, "US", "Value", "API_Value");
		api.tc_readAndWriteAPIResponse(result, filePath, "CA");
		page.compareTwoColsofSameExcel(filePath, "CA", "Value", "API_Value");
		api.tc_readAndWriteAPIResponse(result, filePath, "EU");
		page.compareTwoColsofSameExcel(filePath, "EU", "Value", "API_Value");
		api.tc_readAndWriteAPIResponse(result, filePath, "JP");
		page.compareTwoColsofSameExcel(filePath, "JP", "Value", "API_Value");
		api.tc_readAndWriteAPIResponse(result, filePath, "CH");
		page.compareTwoColsofSameExcel(filePath, "CH", "Value", "API_Value");
		api.tc_readAndWriteAPIResponse(result, filePath, "AS");
		page.compareTwoColsofSameExcel(filePath, "AS", "Value", "API_Value");
		
	}
	public void verifyAPIResponseAfterUnshare() throws Exception
	{
		String filePath="src/testdata/SDL_TestData.xlsx";
		String relID=page.readRelIDFromExcel();
		SDLAPI_TC api=new SDLAPI_TC();
		TokenGeneration tok=new TokenGeneration(); 
		String token = tok.generateToken("sdlGenerateTokenURL","sdlUserName","sdlPassword");
		StringBuffer result = new StringBuffer();
		result=api.tc_getSDLAPI_relId(token, relID);
		api.tc_readAndWriteAPIResponse(result, filePath, "CPEntDetails");
		page.compareTwoColsofSameExcel(filePath, "CPEntDetails", "Value", "API_Value");
		api.tc_readAndWriteAPIResponse(result, filePath, "AdditionalParams");
		page.compareTwoColsofSameExcel(filePath, "AdditionalParams", "Value", "API_Value");
		
	}

}














