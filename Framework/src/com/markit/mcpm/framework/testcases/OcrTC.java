package com.markit.mcpm.framework.testcases;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.framework.mcpm.pageMethods.AdminPage;
import com.markit.framework.mcpm.pageMethods.EntityPage;
import com.markit.framework.mcpm.pageMethods.MCPMLoginpage;
import com.markit.framework.mcpm.pageMethods.NavigationPage;
import com.markit.framework.mcpm.pageMethods.OcrChevron;
import com.markit.framework.mcpm.pageMethods.OwnershipControlReportingMatchPage;
import com.markit.framework.mcpm.pageMethods.OwnershipControlReportingMatchPage.EntityInformation;

public class OcrTC {
	
	public static String wbPath="src/testdata/TestData_EntityDoc.xlsx";
	public static String sheetName="OCR";
	private static String entityName=null;
	private static String entityNameFCM="AutoLegal6PsrdIR";
	OwnershipControlReportingMatchPage.EntityInformation ei= new OwnershipControlReportingMatchPage().new EntityInformation();
	OwnershipControlReportingMatchPage.LegalEntityAndControllerInfo con= new OwnershipControlReportingMatchPage().new LegalEntityAndControllerInfo();
	OwnershipControlReportingMatchPage ownership=new OwnershipControlReportingMatchPage();
	private static int rowNumber=1;
	NavigationPage nav=new NavigationPage();
	OcrChevron ocr=new OcrChevron();
	private static String companyName=null;
	AdminPage admin=new AdminPage();
	EntityPage ep=new EntityPage();	
	private static String legalContactFName=null;
	private static String controllerLEFName=null;
	private static String controllerNPFName=null;
	
	public void tc_01_createAFCMEntity(WebDriver driver)
	{
		nav.navigateToCreateEntity(driver);
		EntityPage ep=new EntityPage();	
		ep.createEntity(driver,wbPath, "Entity",2);
		WebUtil.wait(2000);
		ep.checkFCMCheckBox(driver);
		ep.saveEntityDetails(driver);
	}
	
	public void tc_02_createAnewCompanyAndSwitchUser(WebDriver driver)
	{
		List<String> tc1 =ExcelUtil.fn_GetTestData("src/testdata/testWorkbook.xlsx","Login","AutomationTestCaseName","tc_Login_KYCDashboardRegression");
		String userName=ExcelUtil.fn_FetchFieldValue(tc1,"User_Name");
		String compName= "Auto OCR Company"+WebUtil.generateRandomNumber();
		nav.navigateToAdminPage(driver);
		companyName=admin.addCompany(driver, compName, "BS", 1);
		WebUtil.wait(5000);
		admin.changeCompanyOfAUser(driver,userName, companyName);
	}	

	public void tc_03_verifyTandC(WebDriver driver)
	{	
		MCPMLoginpage m=new MCPMLoginpage();
		m.mcpmTOU(driver);
	    nav.navigateToCreateEntity(driver);	
		entityName=ep.createEntity(driver,wbPath, "Entity",rowNumber);
		WebUtil.wait(2000);
		ep.saveEntityDetails(driver);
		WebUtil.wait(3000);
		nav.navigateToManageEntities(driver);		
		ep.setAndApplyFilter(driver, entityName, "Entity.name.LegalNameFilter");
		ep.clickEditEntity(driver);
		ocr.clickonOcrChevron(driver);
		WebUtil.wait(3000);
	    ocr.acceptTandC(driver);
	}
	
	public void tc_mandatory(WebDriver driver)
	{
		ocr.mandatoryFieldCheck(driver);
	}
	
	public void tc_04_fillDetails(WebDriver driver)
	{		
		ocr.fillAddress(driver);
		ocr.fillLegalEntityContact(driver);
		ocr.fillControllerDetails(driver, "Legal Entity");
		ocr.fillControllerDetails(driver, "Natural Person");
     	ocr.addReportingEntity(driver, entityNameFCM);
		ocr.saveDetails(driver);
	}
	public void tc_05_verifyThefilledDetailsOnMatchGrid(WebDriver driver)
	{
		nav.navigateToCFTCOwnershipandControlReporting(driver);	
		ei.clickAtEntityInfoTab(driver);
		ownership.filterOCR(driver, "OCRMatch.name.EntityFilter", entityName);		
		ei.readContentOFEntityInfoTable(driver);
		ei.verifyContactLEGrid(driver);
		con.clickAtLEandControllerTab(driver);
		con.filterControllerInfoTab(driver, "OCRMatch.xpath.RelationShipName", OcrChevron.controllerNP[0]);
		con.readContentOFControllerTable(driver, 4);
		con.verifyContactControllerGrid(driver,OcrChevron.controllerNP);
		con.filterControllerInfoTab(driver, "OCRMatch.xpath.RelationShipName", OcrChevron.controllerLE[1]);
		con.readContentOFControllerTable(driver, 3);
		con.verifyContactControllerGrid(driver,OcrChevron.controllerLE);
	}
	
	public void tc_06_verifyTheEditFromChevron(WebDriver driver)
	{
		nav.navigateToManageEntities(driver);
		ep.setAndApplyFilter(driver, entityName, "Entity.name.LegalNameFilter");
		ep.clickEditEntity(driver);
		ocr.clickonOcrChevron(driver);
		System.out.print("");
		ocr.editFirstNameofOCRDetail(driver, "LE", "LEFirstNameEditFromChevron"+WebUtil.generateRandomNumber());
		ocr.editFirstNameofOCRDetail(driver, "ControllerLE", "CLEFirstNameEditFromChevron"+WebUtil.generateRandomNumber());
		ocr.editFirstNameofOCRDetail(driver, "ControllerNP", "CNPFirstNameEditFromChevron"+WebUtil.generateRandomNumber());
		ocr.saveDetails(driver);
		
	}
	public void tc_07_verifyTheEditedDetailsOnMatchGrid(WebDriver driver)
	{
		WebUtil.refreshURL(driver);    
		nav.navigateToCFTCOwnershipandControlReporting(driver);
		ei.clickAtEntityInfoTab(driver);
		ownership.filterOCR(driver, "OCRMatch.name.EntityFilter", entityName);		
		//ei.filterEntityInfoTab(driver, "OCRMatch.name.EntityFilter", entityName);
		ei.readContentOFEntityInfoTable(driver);
		ei.verifyContactLEGrid(driver);
		con.clickAtLEandControllerTab(driver);		
		con.filterControllerInfoTab(driver, "OCRMatch.xpath.RelationShipName", OcrChevron.controllerNP[0]);
		con.readContentOFControllerTable(driver, 4);
		con.verifyContactControllerGrid(driver,OcrChevron.controllerNP);
		con.filterControllerInfoTab(driver, "OCRMatch.xpath.RelationShipName", OcrChevron.controllerLE[1]);
		con.readContentOFControllerTable(driver, 3);
		con.verifyContactControllerGrid(driver,OcrChevron.controllerLE);
		
	}
	
	public void tc_08_verifyAdddetailsFromMatchPage(WebDriver driver)
	{
		con.clickAtLEandControllerTab(driver);
		controllerLEFName=con.addControllerLE(driver);
		legalContactFName=con.addLegalEntityContact(driver);		
		controllerNPFName=con.addControllerNP(driver);
	}
	public void tc_09_verifyTheAddedDetailsOnChevron(WebDriver driver)
	{
		WebUtil.refreshURL(driver);
		nav.navigateToManageEntities(driver);
		ep.setAndApplyFilter(driver, entityName, "Entity.name.LegalNameFilter");
		ep.clickEditEntity(driver);
		ocr.clickonOcrChevron(driver);
		ocr.selectFormExistingForLEContact(driver, legalContactFName);
		ocr.selectFormExistingForController(driver, controllerLEFName);
		ocr.selectFormExistingForController(driver, controllerNPFName);
		ocr.saveDetails(driver);
	}
	
	public void tc_10_deleteControllerDetailsFromChevron(WebDriver driver)
	{
		nav.navigateToManageEntities(driver);
		ep.setAndApplyFilter(driver, entityName, "Entity.name.LegalNameFilter");
		ep.clickEditEntity(driver);
		ocr.clickonOcrChevron(driver);
		ocr.deleteControllerfromChevron(driver, OwnershipControlReportingMatchPage.controllerLegalEntityName);
		ocr.deleteControllerfromChevron(driver, controllerNPFName);
		ocr.saveDetails(driver);		
		
	}
	public void tc_11_verifyThedeletedControllerAvilableOnMatch(WebDriver driver)
	{
		
		WebUtil.refreshURL(driver);
		nav.navigateToCFTCOwnershipandControlReporting(driver);
		con.clickAtLEandControllerTab(driver);
		WebUtil.clickedWithAction(driver, "OCRChevron.xpath.RefreshController");
		con.filterControllerInfoTab(driver, "OCRMatch.xpath.FNameFiter", controllerLEFName);
		con.readContentOFControllerTable(driver, 1);
		con.filterControllerInfoTab(driver, "OCRMatch.xpath.FNameFiter", controllerNPFName);
		con.readContentOFControllerTable(driver, 1);		
	}
	
	public void tc_12_verifyDownloadDetailsFromControllerTab(WebDriver driver)
	{
		con.clearfilter(driver);
		con.verifyDownloadLEandControllerInfo(driver);
		ei.clickAtEntityInfoTab(driver);
		ei.clearFilter(driver);
		ei.verifyDownloadOCRInfo(driver);
	}
	
	
}
