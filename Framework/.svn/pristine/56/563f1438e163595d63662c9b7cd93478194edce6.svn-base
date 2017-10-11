package com.markit.framework.pega.pageMethods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;

public class PEGANavigationBar {


	public void navigationTopMenuTeamManager(WebDriver driver,String linkName){

		List<WebElement> e= WebUtil.findElements("PEGA.xpath.ToolsLink", driver);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		switch(linkName){
		case "Dashboard":
		WebUtil.clickAt(e.get(0));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		break;
		
		case "Unassigned Cases":
		WebUtil.clickAt(e.get(1));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		break;

		case "Cases":
		WebUtil.clickAt(e.get(2));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		break;
		
		case "Reports":
		WebUtil.clickAt(e.get(3));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		break;
		
		case "Support":
		WebUtil.clickAt(e.get(4));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		break;
		
		case "Tools":
		WebUtil.clickAt(e.get(5));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		break;

		case "Dev Tools":
		WebUtil.clickAt(e.get(6));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		break;
		}
	}
	
	public WebDriver switchFrameUnassignedCases(WebDriver driver){
		driver.switchTo().defaultContent();
		String iFrame=XPath.myprop.get("PEGA.unassignedCases.iFrame");
		driver.switchTo().frame(driver.findElement(By.name(iFrame)));
    	return driver;
		
	}
	
 
    
    public WebDriver switchFrame0(WebDriver driver){
		driver.switchTo().defaultContent();
		String iFrame=XPath.myprop.get("PEGA.Cases.iFrame0");
		driver.switchTo().frame(driver.findElement(By.name(iFrame)));
    	return driver;
		
	}
    
    public WebDriver switchFrame1(WebDriver driver){
 		driver.switchTo().defaultContent();
 		String iFrame=XPath.myprop.get("PEGA.Cases.iFrame1");
 		driver.switchTo().frame(driver.findElement(By.name(iFrame)));
     	return driver;
 		
 	}
    
    public WebDriver switchFrame2(WebDriver driver){
 		driver.switchTo().defaultContent();
 		String iFrame=XPath.myprop.get("PEGA.Cases.iFrame2");
 		driver.switchTo().frame(driver.findElement(By.name(iFrame)));
     	return driver;
 		
 	}
    
    public WebDriver switchFrame3(WebDriver driver){
 		driver.switchTo().defaultContent();
 		String iFrame=XPath.myprop.get("PEGA.Cases.iFrame3");
 		driver.switchTo().frame(driver.findElement(By.name(iFrame)));
     	return driver;
 		
 	}
    
    
}
