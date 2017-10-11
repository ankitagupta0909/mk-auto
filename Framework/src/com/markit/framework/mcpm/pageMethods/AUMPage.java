package com.markit.framework.mcpm.pageMethods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;

public class AUMPage {
	
	public void addAUM(WebDriver driver,String entityName)
	{
		WebUtil.clickedWithAction(driver, "MCPMAum.xpath.AddAUM");
		WebUtil.setWebEdit("MCPMAUM.name.EntityName", entityName);
		WebUtil.clickedWithAction(driver, "MCPMAUM.xpath.OptionList");
		WebUtil.clickedWithAction(driver, "MCPMAUM.xpath.Add");
		WebUtil.clickedWithAction(driver, "MCPMAUM.xpath.Calender");
		WebUtil.clickedWithAction(driver, "MCPMAUM.xpath.CurrentDate");
		WebUtil.setWebEdit("MCPMAUM.name.Currency", "AFN");
		WebUtil.setWebEdit("MCPMAUM.name.AUMValue", "30");
		WebUtil.clickedWithAction(driver, "MCPMAUM.xpath.Update");
		WebUtil.clickedWithAction(driver, "MCPMAUM.xpath.Close");
		WebUtil.wait(5000);
	}
	
	public void setEntityFilter(WebDriver driver,String entityName)
	{
		WebUtil.setWebEdit("MCPMAUM.name.DispalyNameFilter", entityName);
		WebUtil.clickedWithAction(driver, "MCPMAUM.xpath.Apply");
		WebUtil.wait(5000);
	}

	public void selectAllCheckBox()
	{
		WebUtil.click("MCPMAUM.xpath.SelectAll");
	}
	
	public void permissonAUMSelectAll(WebDriver driver,String dgName)
	{
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.Permisson");
		WebUtil.wait(3000);
		WebElement yes=WebUtil.findElement("MCPMDoc.xpath.YesPermission", driver);
		if(yes.isDisplayed())
		{
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.YesPermission");
		}
		WebElement ele=driver.findElement(By.xpath("//div[@id='mde.appDocumentPermissionDialogs']//table//tr//td//div[contains(text(),'"+dgName+"')]/ancestor::tr/td/div"));
		WebUtil.actionClass(ele, driver);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.GrantPermisson");
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.YesPermission");
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.NoteOk");
		WebUtil.wait(3000);
	}
	
	public void permissonAUM(WebDriver driver,String dgName)
	{
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.Permisson");
		WebUtil.wait(3000);
		WebElement ele=driver.findElement(By.xpath("//div[@id='mde.appDocumentPermissionDialogs']//table//tr//td//div[contains(text(),'"+dgName+"')]/ancestor::tr/td/div"));
		WebUtil.actionClass(ele, driver);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.GrantPermisson");
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.YesPermission");
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.NoteOk");
		WebUtil.wait(3000);
	}
	public void unPermissonAllAUM(WebDriver driver)
	{
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.Permisson");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.YesPermission");
		WebUtil.wait(2000);
	    WebUtil.clickedWithAction(driver,"MCPMAUM.xpath.UnpermissionSelectAll");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.RemovePermission");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.YesPermission");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.NoteOk");
		WebUtil.wait(2000);
	}
	
	public void selectPassedNumberofCheckBox(WebDriver driver,int count)
	{
		
		List<WebElement> chkbox=driver.findElements(By.xpath("//table[@class='x-grid-table x-grid-table-resizer']//div[@class='x-grid-row-checker']"));
		for(int i=0;i<=count-1;i++){
			chkbox=driver.findElements(By.xpath("//table[@class='x-grid-table x-grid-table-resizer']//div[@class='x-grid-row-checker']"));
			chkbox.get(i).click();
		}
		WebUtil.wait(5000);
	}
	
	public int getTheAUMCountFromManageAUM(WebDriver driver)
	{		
		String pagination=WebUtil.readElementText(driver, "MCPMDoc.xpath.CPCount");
		int i=pagination.indexOf("of");
		String count=pagination.substring(i+3);
		System.out.println(count);
		//ExcelUtil.setColumnDataByColName("src/testdata/TestData_EntityDoc.xlsx", "CP", "CountOfDoc", 2, count);
		ReportUtil.reportInputValueOnly("The document count in the Grid", count, true);
		return Integer.parseInt(count);
	}	
	
	public void sendEmailDG(WebDriver driver,String searchDG){

		WebUtil.javaExecuter(driver, "Doc.xpath.EmailButton");
		WebUtil.wait(5000);
		WebUtil.click("EmailDoc.xpath.To");
		WebUtil.wait(5000);
		int i=WebUtil.getSizeOfElement(driver, "MCPM.xpath.YesPermission");
		if(i!=0)
		{
		WebUtil.clickedWithAction(driver, "MCPM.xpath.YesPermission");
		}
		WebUtil.wait(5000);
		WebUtil.setWebEdit("EmailDoc.xpath.TypeDistInp", "Distribution");
		WebUtil.wait(3000);
		WebUtil.click("EmailDoc.xpath.selectType");
		WebUtil.wait(5000);
		WebUtil.setWebEdit("EmailDoc.xpath.EnterSearchName", searchDG);
		WebUtil.sendEnterKeyWebEdit(driver,"EmailDoc.xpath.EnterSearchName");		
		WebElement dg=driver.findElement(By.xpath("//div[@role='dialog'][2]//div[contains(text(),'"+searchDG+"')]"));
		WebUtil.doubleClickonElement(driver,dg);
		WebUtil.click("EmailDoc.xpath.Okbutton");
		WebUtil.wait(2000);
		WebUtil.click("EmailDoc.xpath.Sendbutton");
		WebUtil.wait(2000);
		WebUtil.click("EmailDoc.xpath.SentOK");
		WebUtil.wait(2000);
	}
	


}
