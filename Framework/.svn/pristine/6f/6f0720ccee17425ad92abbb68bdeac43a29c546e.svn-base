package com.markit.mcpm.framework.testcases;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.framework.mcpm.pageMethods.AUMPage;
import com.markit.framework.mcpm.pageMethods.CPAUMPage;
import com.markit.framework.mcpm.pageMethods.CPDocumentsPage;
import com.markit.framework.mcpm.pageMethods.DistributionGroupsPage;
import com.markit.framework.mcpm.pageMethods.EntityPage;
import com.markit.framework.mcpm.pageMethods.MCPMDocumentsPage;
import com.markit.framework.mcpm.pageMethods.NavigationPage;

public class PermissionTC {
	DistributionGroupsPage dg= new DistributionGroupsPage();
	NavigationPage nav=new NavigationPage();
	MCPMDocumentsPage doc=new MCPMDocumentsPage();
	private static String path="src/testdata/TestData_EntityDoc.xlsx";
	private static String entityName=null;
	private static String count=null;
	public static int numberOfDoc=2;
	private static String dgName=null;
	AUMPage aum=new AUMPage();
	
	
	
	
	public void tc_01_permissionLessThen20Doc(WebDriver driver)
	{
		
	   nav.navigateToCreateEntityInStage(driver);		
		EntityPage ep=new EntityPage();
		entityName=ep.createEntity(driver,path, "Entity",1);
		ep.saveEntityDetails(driver);
		WebUtil.wait(2000);
		nav.navigateToManageDocumentsInStage(driver);
		String docType=ExcelUtil.getColumnDataByColName(path,"Document","DocType",2);
	    for(int i=0;i<numberOfDoc;i++)
		{
      doc.addDocument(driver, entityName, docType, "Test Permission");
		}
		nav.navigateToDistributionGroups(driver);
		dgName=dg.createDG(driver, "tc_DG_01_Creation");
		nav.navigateToManageDocumentsInStage(driver);
		doc.setEntityFilter(entityName);	
		doc.selectAllCheckBox();
		System.out.print("");
		doc.permissonDocSelectAll(driver, dgName);	
	}
	
	public void tc_02_verifyLessThen20PermissionDocInCP(WebDriver driver)
	{
		CPDocumentsPage cp=new CPDocumentsPage();
		nav.navigateToCPViews(driver);		
		String expectedCount=Integer.toString(1+numberOfDoc);
		doc.setEntityFilter(entityName);	
		String count1=cp.getTheCountOfDocInTheGrid(driver);
		ReportUtil.reportStringMatch("The count of the Permissioned Doc in CP",expectedCount , count1);
	}
	
	public void tc_03_verifyNonPermissionedCP(WebDriver driver)
	{
		
		nav.navigateToCPViews(driver);
		doc.setEntityFilter(entityName);
		String value=WebUtil.readElementText(driver, "MCPMDoc.xpath.CPCount");
		ReportUtil.reportStringMatch("The count of the Permissioned Doc in CP","No data to display" , value);
	}
	
	public void tc_removeAccessOfDoc(WebDriver driver)
	{
		nav.navigateToManageDocumentsInStage(driver);
		doc.selectAllCheckBox();
		doc.unPermissonAllDocs(driver);
	}
	
	public int tc_verifyTheCurrentDocCountInCP(WebDriver driver)
	{
		nav.navigateToCPViews(driver);
		CPDocumentsPage cp=new CPDocumentsPage();
		count=cp.getTheCountOfDocInTheGrid(driver);
		ReportUtil.reportInputValueOnly("The current count of the Permissioned Doc in CP", count, true);
	    return Integer.parseInt(count);
	}
	
	public void tc_04_permission20Doc(WebDriver driver)
	{
		nav.navigateToManageDocumentsInStage(driver);
		doc.selectPassedNumberofCheckBox(driver, 20);
		doc.permissonDoc(driver, dgName);
	}
	
	public void tc_verifyCountOfDocInCPAfterPermission(WebDriver driver,int countOfDoc)
	{
		CPDocumentsPage cp=new CPDocumentsPage();
		nav.navigateToCPViews(driver);	
		int totalCount=Integer.parseInt(count);
		String expectedCount=Integer.toString(totalCount+countOfDoc);
		String count1=cp.getTheCountOfDocInTheGrid(driver);
		ReportUtil.reportStringMatch("The count of the Permissioned Doc in CP",expectedCount , count1);
		
	}
	
	public void tc_05_selectAllDoc_PermissionButton(WebDriver driver){
		nav.navigateToManageDocumentsInStage(driver);
		doc.selectAllCheckBox();
		doc.permissonDocSelectAll(driver, dgName);		
		numberOfDoc=doc.getTheDocCountFromManageDoc(driver);
	}
	
	
	public void tc_06_20DocPermission_EmailButton(WebDriver driver){
		nav.navigateToManageDocumentsInStage(driver);
		doc.selectPassedNumberofCheckBox(driver, 20);
		doc.sendEmailDG(driver, dgName);
		
	}
	
	public int tc_verifyTheCurrentCountofAUMinCP(WebDriver driver)
	{
		nav.navigateToCPViewAUM(driver);
		CPAUMPage aum=new CPAUMPage();
		count=aum.getTheCountOfAUMFromTheGrid(driver);
		ReportUtil.reportInputValueOnly("The current count of the Permissioned Doc in CP", count, true);
		return Integer.parseInt(count);
	}
	
	public void tc_07_verifyLessThen20AUMandPermission(WebDriver driver)
	{
		nav.navigateToManageAUM(driver);		
		aum.addAUM(driver, entityName);
		aum.setEntityFilter(driver, entityName);
		aum.selectAllCheckBox();
		aum.permissonAUMSelectAll(driver, dgName);
	}
	
	public void tc_verifyCountOfAUMAfeterPermissionInCP(WebDriver driver,int countToAdd)
	{
		nav.navigateToCPViewAUM(driver);
		CPAUMPage aum=new CPAUMPage();
		int totalCount=Integer.parseInt(count);
		String expectedCount=Integer.toString(totalCount+countToAdd);
		String count1=aum.getTheCountOfAUMFromTheGrid(driver);
		ReportUtil.reportStringMatch("The count of the Permissioned Doc in CP",expectedCount , count1);
	}
	
	public void tc_08_permission20AUM(WebDriver driver)
	{
		nav.navigateToManageAUM(driver);
		aum.selectPassedNumberofCheckBox(driver, 20);
		aum.permissonAUM(driver, dgName);
	}
	
	public void tc_09_selectAllAUM_PermissionButton(WebDriver driver){
		nav.navigateToManageAUM(driver);
		aum.selectAllCheckBox();
		aum.permissonAUMSelectAll(driver, dgName);
		numberOfDoc=aum.getTheAUMCountFromManageAUM(driver);
	}
	
	public void tc_10_20AUMPermission_EmailButton(WebDriver driver){
		nav.navigateToManageAUM(driver);
		aum.selectPassedNumberofCheckBox(driver, 20);
		aum.sendEmailDG(driver, dgName);		
		
	}
	public void tc_11_permmissionAllDocWithEmail(WebDriver driver)
	{
		nav.navigateToManageDocumentsInStage(driver);
		doc.selectAllCheckBox();
		doc.sendEmailDG(driver, dgName);
		numberOfDoc=doc.getTheDocCountFromManageDoc(driver);
		
	}
	public void tc_12_permmissionAllAUMWithEmail(WebDriver driver)
	{
		nav.navigateToManageAUM(driver);
		aum.selectAllCheckBox();
		aum.sendEmailDG(driver, dgName);
		numberOfDoc=aum.getTheAUMCountFromManageAUM(driver);
		
	}
	
	public void tc_removeAccessFromAUM(WebDriver driver)
	{
		AUMPage aum=new AUMPage();
		nav.navigateToManageAUM(driver);
		aum.selectAllCheckBox();
		aum.unPermissonAllAUM(driver);
	}
	
	public void cleanUp(WebDriver driver)
	{
		nav.navigateToDistributionGroups(driver);
		dg.deleteDG(driver, dgName);
		
	}
	
	
}
