package com.markit.framework.pega.pageMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;

public class PEGAUnassignedCases {

	public void applyFilter(WebDriver driver,String ClickFilterXpathKey,String searchText){
		
		WebElement e=WebUtil.findElement(ClickFilterXpathKey, driver);
		WebUtil.actionClass(e, driver);
		WebUtil.waitUntilElementPresent(driver, "PEGA.xpath.SearchTextFilter");
		
		WebElement el=WebUtil.findElement("PEGA.xpath.SearchTextFilter", driver);
		WebUtil.setWebEdit(el,searchText);
		
		WebUtil.wait(5000);
		WebUtil.click("PEGA.xpath.ApplyFilter");
		ReportUtil.reportWebElement("Apply button has been clicked on Filter", true);
		WebUtil.wait(5000);
	}
	
	
}
