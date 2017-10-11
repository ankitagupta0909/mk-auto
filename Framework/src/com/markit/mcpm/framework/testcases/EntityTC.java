package com.markit.mcpm.framework.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.framework.mcpm.pageMethods.EntityPage;
import com.markit.framework.mcpm.pageMethods.KYCdashboardpage;
import com.markit.framework.mcpm.pageMethods.NavigationPage;

public class EntityTC {

	
	public String createEntity(WebDriver driver){
		NavigationPage nav=new NavigationPage();
		nav.navigateToCreateEntity(driver);
		EntityPage ep=new EntityPage();
	    String entityName=ep.createEntity(driver,"src/testdata/TestData_EntityDoc.xlsx", "Entity",1);
		ep.saveEntityDetails(driver);
		WebUtil.wait(5000);
		return entityName;
	}
	
	public void createMultipleEntities(WebDriver driver,String wbPath,String sheetName)
	{
		NavigationPage nav=new NavigationPage();
		int i=1 ;
		while(i<100)
		{
		nav.navigateToCreateEntity(driver);
		EntityPage ep=new EntityPage();
		
	    int iRowNum=1;
	    String legalName="AutoLegal"+i;
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
		ep.saveEntityDetails(driver);
		i++;
		}
	}
}
