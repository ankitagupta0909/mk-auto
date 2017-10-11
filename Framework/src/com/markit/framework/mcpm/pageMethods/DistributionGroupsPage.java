package com.markit.framework.mcpm.pageMethods;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.WebUtil;

public class DistributionGroupsPage {

	
	public String createDG(WebDriver driver,String tcName)
	{
		List<String> tc1 =ExcelUtil.fn_GetTestData("src/testdata/testWorkbook.xlsx","TestDataSheet","TestCaseName",tcName);
		List<String> userList= new ArrayList<>();
		WebElement ele=null;
		userList=ExcelUtil.fn_FetchSimilarFieldValue(tc1,"UserName");
		WebUtil.clickedWithAction(driver,"MCPMDG.xpath.MangeDG");		
		for(int i=0;i<userList.size();i++)
		{
		WebUtil.setWebEdit("MCPMDG.name.DGSerach", userList.get(i));
		WebUtil.wait(2000);
		WebUtil.findElement("MCPMDG.name.DGSerach", driver).sendKeys(Keys.ENTER);
		WebUtil.wait(2000);
		ele=driver.findElement(By.xpath("//div[contains(@id,'ext-comp')]//table[@class='x-grid-table x-grid-table-resizer']//td//div[contains(text(),'"+userList.get(i)+"')]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).doubleClick().build().perform();
		}
		String dgName="Auto DG"+WebUtil.generateRandomNumber();
		/*	WebUtil.clickedWithAction(driver, "MCPMDG.xpath.DGNameDropDown");
		WebUtil.wait(2000);*/
		WebUtil.clickedWithAction(driver, "MCPMDG.xpath.DGName");
		WebUtil.wait(2000);
		WebUtil.sendKeysWithAction(driver, "MCPMDG.xpath.DGName", dgName);
		WebUtil.clickedWithAction(driver, "MCPMDG.xpath.DGSave");
		WebUtil.wait(6000);
		return dgName;
	}
	
	public void deleteDG(WebDriver driver,String dgName)
	{
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.DeleteDG");
		WebElement ele=driver.findElement(By.xpath("//div[contains(@id,'window')]//table//tr//td//div[contains(text(),'"+dgName+"')]"));
		WebUtil.actionClass(ele, driver);
		WebUtil.clickedWithAction(driver, "MCPMDG.xpath.DeleteButton");
		WebUtil.clickedWithAction(driver, "MCPMDG.xpath.YesDelete");
		WebUtil.wait(8000);
		//WebUtil.clickedWithAction(driver, "MCPMDG>xpath.CloseDG");
	}
}
