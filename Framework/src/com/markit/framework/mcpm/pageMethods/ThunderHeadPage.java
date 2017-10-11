package com.markit.framework.mcpm.pageMethods;

import java.text.DateFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;

public class ThunderHeadPage {
	public static WebDriver setDashboardFrame(WebDriver driver)
	{
		String iFrame=null;
		driver.switchTo().defaultContent();
 	    iFrame=XPath.myprop.get("MCPM.ufeqa.iframe");
 		driver.switchTo().frame(driver.findElement(By.name(iFrame)));
 		iFrame=XPath.myprop.get("ThunderHeadDoc.ufeqa.iframe");
  		driver.switchTo().frame(driver.findElement(By.name(iFrame)));
		return driver;
	}
	public static WebDriver setInterviewFrame(WebDriver driver)
	{
		String iFrame=null;
		driver.switchTo().defaultContent();
		iFrame=XPath.myprop.get("MCPM.ufeqa.iframe");
 		driver.switchTo().frame(driver.findElement(By.name(iFrame)));
 		iFrame=XPath.myprop.get("ThunderHeadInterview.ufeqa.iframe");
  		driver.switchTo().frame(driver.findElement(By.name(iFrame)));
		return driver;
	}
	
	public void getBackToCP(WebDriver driver)
	{
		WebUtil.click("ThunderHead.xpath.CPlink");
		WebUtil.wait(4000);
	}
	
	public void createDocBox(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String date=WebUtil.getTheCurrentDateAsperpassedFormat("yyyy/MM/dd HH:mm:ss");
		setDashboardFrame(driver);
		WebUtil.click("ThunderHead.xpath.AddDoc");
		WebUtil.click("ThunderHead.xpath.FromTemplate");
		NavigationPage.setMcpmFrame(driver);
		WebUtil.wait(2000);
		WebUtil.click("ThunderHead.xpath.SelectInput");
		WebUtil.wait(2000);
		WebUtil.click("ThunderHead.xpath.Select");
		WebUtil.setWebEdit("ThunderHead.xpath.DocBoxName", "DocBox "+date);
		WebUtil.setWebEdit("ThunderHead.xpath.Textarea", date);
		WebUtil.click("ThunderHead.xpath.Create");
		WebUtil.wait(10000);
		setInterviewFrame(driver);
		WebUtil.setWebEdit("ThunderHead.xpath.ISDALegalNamePartyA", "Auto_"+WebUtil.generateRandomString(true, true, 4));
		WebUtil.setWebEdit("ThunderHead.xpath.ShortNamePartyA", "Auto_"+WebUtil.generateRandomString(true, true, 4));
		WebUtil.setWebEdit("ThunderHead.xpath.ISDALegalNamePartyB", "Auto_"+WebUtil.generateRandomString(true, true, 4));
		WebUtil.setWebEdit("ThunderHead.xpath.ShortNamePartyB", "Auto_"+WebUtil.generateRandomString(true, true, 4));
		WebUtil.click("ThunderHead.xpath.Exclusive");
		WebUtil.click("ThunderHead.xpath.Next");
		WebUtil.wait(4000);
		WebUtil.click("ThunderHead.id.AddFund");
		WebUtil.wait(2000);
		WebUtil.setWebEdit("ThunderHead.xpath.FundName", "Auto_"+WebUtil.generateRandomString(true, true, 4));
		WebUtil.click("ThunderHead.xpath.Calender");
		WebUtil.click("ThunderHead.xpath.Today");
		WebUtil.click("ThunderHead.id.Next");
		WebUtil.wait(4000);
		WebUtil.click("ThunderHead.xpath.AnnexIS");
		WebUtil.click("ThunderHead.id.Next");
		WebUtil.wait(4000);
		WebUtil.click("ThunderHead.xpath.DeterminingParty");
		WebUtil.click("ThunderHead.xpath.CalculationAgent");
		WebUtil.click("ThunderHead.id.Submit");
		WebUtil.wait(20000);
		NavigationPage.setMcpmFrame(driver);
		WebUtil.click("ThunderHead.xpath.StartEditingButton");
		WebUtil.click("ThunderHead.xpath.RequestDocument");
		WebUtil.click("ThunderHead.id.NextRequestAgreement");
		WebUtil.wait(2000);
		WebUtil.click("ThunderHead.xpath.SelectCP");
		WebUtil.setWebEdit("ThunderHead.xpath.InputBoxRDA", "Arshad");
		WebUtil.click("ThunderHead.xpath.RDASelectUser");
		WebUtil.click("ThunderHead.id.Send");		
		
	}
	
	public void ss_flow(WebDriver driver,String docboxName)
	{
		WebElement ele=driver.findElement(By.xpath("//div[contains(text(),'"+docboxName+"')]"));
		WebUtil.actionClass(ele, driver);
		WebUtil.click("ThunderHead.xpath.Agree");
	}

}
