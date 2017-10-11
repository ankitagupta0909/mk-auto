package com.markit.framework.mcpm.pageMethods;


import java.sql.Connection;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.markit.common.framework.util.DBUtility;
import com.markit.common.framework.util.WebUtil;

public class VMPage {
	public static int count=0;
	
	public void selectItemsCountFromPaginationDropDown(WebDriver driver)
	{	
		WebUtil.selectValueFromDropDownAsPerPassedVaule(driver, "VM.xpath.VMPageinationDropDown", "number:250");
		WebUtil.wait(15000);
	}
	public void clickAtApplyQuestionnaireLink(WebDriver driver)
	{
		WebUtil.click("VM.xpath.VMApplyQuestionnaireLink");
		WebUtil.wait(30000);
	}
	public void clickAtSelectAllLink(WebDriver driver)
	{
		WebUtil.click("VM.xpath.SelectAll");
		WebUtil.wait(2000);
	}
	
	public void clickAtApplyQuestionnaireAction(WebDriver driver)
	{
		WebUtil.click("VM.xpath.VMActions");
		WebUtil.wait(2000);
		WebUtil.click("VM.xpath.ApplyQuestionnaire");
		WebUtil.wait(8000);
	}
	public void clickAtRadioButtonToApplyQuestionnaire(WebDriver driver,String nameOfQuestionnaire)
	{
		WebElement ele=driver.findElement(By.xpath("//span[@title='"+nameOfQuestionnaire+"']/ancestor::tr//td[4]"));
		WebUtil.actionClass(ele, driver);
	}
	
	public void clickAtApply(WebDriver driver)
	{
		WebUtil.click("VM.xpath.ApplyButton");
		WebUtil.wait(2000);
		WebUtil.click("VM.xpath.ConfirmButton");
		WebUtil.wait(25000);
	}
	
	public String dbValidationForQueue(Connection con)
	{

		String i=null;
		if(con!=null)
		{		
			i=DBUtility.getColData(con,"select count(*) from ER_PROTOCOL_QUEUE_TABLE", "COUNT(*)");
		}
		return i;		
	}
	
	public void checkTheCurrentPage(WebDriver driver)
	{
		String currentPage=WebUtil.readElementText(driver, "VM.xpath.CurrentPage");
	
		currentPage=WebUtil.getAttributeValue(driver, "VM.xpath.CurrentPage", "value");
		System.out.println("Current Page: "+currentPage);
		String totalPage=WebUtil.readElementText(driver, "VM.xpath.TotalCountofPage");
		System.out.println("Current Page: "+totalPage);
		int count=Integer.parseInt(currentPage);
		count=count+1;
		//System.out.println("incremented : "+count+" The string value "+String.valueOf(count));
		if(!currentPage.equalsIgnoreCase(totalPage))
		{
			
			WebUtil.setWebEdit("VM.xpath.CurrentPage", String.valueOf(count));
			WebUtil.wait(15000);
			System.out.println("time out");
		}
	}
	
	public void clickOnOkbuttonOnProcessingPopUp(WebDriver driver)
	{
		WebUtil.wait(2000);
		WebUtil.click("VM.xpath.ProcessingDataOk");
		WebUtil.wait(2000);
	}


}
