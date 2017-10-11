package com.markit.framework.mcpm.pageMethods;

import java.sql.Connection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.markit.common.framework.util.DBConnection;
import com.markit.common.framework.util.DBUtility;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;

public class AdminPage {
	//private static String nameOfCompany="Automation 13 April 2016_1"; 


	

	Connection con=DBConnection.dbConnections.get("MCPM");
	public String addCompany(WebDriver driver,String companyName,String companytype,int count) 
	{
		WebUtil.clickedWithAction(driver, "MCPMAdmin.xpath.AddCompany");
		companyName=companyName+"_"+count;
		WebUtil.sendKeysWithAction(driver, "MCPMAdmin.name.CompanyNameInput", companyName);
		WebUtil.clickedWithAction(driver, "MCPMAdmin.xpath.CompanyType");
		WebUtil.wait(5000);
		int size=WebUtil.getSizeOfElement(driver, "MCPMAdmin.xpath.ChooseCompany");
		if(size!=0)
		{
			WebUtil.clickedWithAction(driver, "MCPMAdmin.xpath.CreateOwnOption");
					
		}
		WebUtil.clickedWithAction(driver, "MCPMAdmin.xpath.CompanyType");
		if(companytype.equalsIgnoreCase("Buyside")||companytype.equalsIgnoreCase("BS"))
		{
			WebUtil.sendKeysWithAction(driver, "MCPMAdmin.xpath.CompanyTypeInput", "BS");
		
		}
		else
		{
			WebUtil.sendKeysWithAction(driver, "MCPMAdmin.xpath.CompanyTypeInput", "SS");

		}
		
		WebUtil.clickedWithAction(driver, "MCPMAdmin.xpath.SaveCompany");
		WebUtil.wait(15000);
		return companyName;
	}
	
	public String addUser(WebDriver driver,String nameOfCompany,String emailAddress,int count) 
	{
		WebUtil.clickedWithAction(driver, "MCPMAdmin.xpath.AddUser");
		WebUtil.sendKeysWithAction(driver, "MCPMAdmin.xpath.Company", nameOfCompany);
		System.out.println("Name: "+nameOfCompany);
		WebElement option=driver.findElement(By.xpath("//li[contains(text(),'"+nameOfCompany+"')]"));
		WebUtil.actionClass(option, driver);
		String email=WebUtil.removeThepassedItemFromString(emailAddress, "@markit.com");
		String emailId=email+count+"@markit.com";
		System.out.println(emailId);
		WebUtil.sendKeysWithAction(driver, "MCPMAdmin.xpath.Email", emailId);
		WebUtil.clickedWithAction(driver, "MCPMAdmin.xpath.UserName");
		WebUtil.sendKeysWithAction(driver, "MCPMAdmin.xpath.Password1", "markit123");
		WebUtil.sendKeysWithAction(driver, "MCPMAdmin.xpath.Password2", "markit123");
		String name=WebUtil.removeThepassedItemFromString(emailId, "@markit.com");
		String[] str_array = name.split("\\.");
		String fname = str_array[0]; 
		String lname = str_array[1];
		//System.out.println(fname + " "+lname);
		WebUtil.sendKeysWithAction(driver, "MCPMAdmin.xpath.FName", fname);
		WebUtil.sendKeysWithAction(driver, "MCPMAdmin.xpath.LName", lname);
		WebUtil.clickedWithAction(driver, "MCPMAdmin.xpath.BSRead");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "MCPMAdmin.xpath.BSWrite");
		boolean actVal=false;
		WebElement save=driver.findElement(By.xpath("//div[@id='duser-dlg']//div[3]//button/span[contains(text(),'Save')]"));
		actVal = WebUtil.verifyWebElementExists("WebElement",save);
		WebUtil.actionClass(save, driver);	
		ReportUtil.reportWebElement("Verify Save is clicked: ", actVal);
		WebUtil.wait(8000);
		WebElement ok=driver.findElement(By.xpath("//div[@role='alertdialog']//button/span"));
		actVal = WebUtil.verifyWebElementExists("WebElement",ok);
		WebUtil.actionClass(ok, driver);
		ReportUtil.reportWebElement("Verify Ok is clicked: ", actVal);
		return  emailId;
		
	}
	
	public String dbValidationForCompany(String nameOfCompany)
	{
		
		String companyName=DBUtility.getColData(con,"select * from mc_companies where companyname='"+nameOfCompany+"'","companyname");
		//System.out.println("Company Name: "+companyName);
		ReportUtil.reportStringMatch("Verify the created Company is available in DB", nameOfCompany, companyName);
		String companyid=DBUtility.getColData(con,"select * from mc_companies where companyname='"+nameOfCompany+"'","companyid");
		return companyid;	
		
	}
	
	public String dbValidationForUser(String emailId,String companyId)
	{
		String userName=DBUtility.getColData(con,"select * from mc_users where username='"+emailId+"' and companyid='"+companyId+"'","username");
		//System.out.println("User Name: "+userName);
		ReportUtil.reportStringMatch("Verify the created user is available in DB", emailId, userName);
		return userName;	
		
	}
	
	public void changeCompanyOfAUser(WebDriver driver,String emailId,String nameOfCompany)
	{
		boolean actVal=false;
		WebUtil.setWebEdit("MCPMAdmin.name.UserName", emailId);
		WebUtil.clickedWithAction(driver, "MCPMAdmin.xpath.SelectuserName");
		WebUtil.click("MCPMAdmin.xpath.Apply");
		WebUtil.wait(5000);
		WebUtil.click("MCPMAdmin.xpath.CheckBox");
		WebUtil.click("MCPMAdmin.xpath.EditUser");
		WebUtil.wait(5000);
		WebUtil.setWebEdit("MCPMAdmin.name.Company", nameOfCompany);
		WebElement ele=driver.findElement(By.xpath("//div[@class='list-ct']/ul//li[contains(text(),'"+nameOfCompany+"')]"));
		ele.click();
		WebElement password=WebUtil.findElement("MCPM.name.Password", driver);
		password.clear();
		WebElement save=driver.findElement(By.xpath("//div[@id='duser-dlg']//div[3]//button/span[contains(text(),'Save')]"));
		actVal = WebUtil.verifyWebElementExists("WebElement",save);
		WebUtil.actionClass(save, driver);	
		ReportUtil.reportWebElement("Verify Save is clicked: ", actVal);
		WebUtil.wait(5000);
		WebElement ok=driver.findElement(By.xpath("//div[@role='alertdialog']//button/span"));
		actVal = WebUtil.verifyWebElementExists("WebElement",ok);
		WebUtil.actionClass(ok, driver);
		ReportUtil.reportWebElement("Verify Ok is clicked: ", actVal);
		
	}
	
	public void addNewRole(WebDriver driver,String emailId,String roleName)
	{
		boolean actVal=false;
		WebElement ele=null;
		WebUtil.setWebEdit("MCPMAdmin.name.UserName", emailId);
		WebUtil.clickedWithAction(driver, "MCPMAdmin.xpath.SelectuserName");
		WebUtil.click("MCPMAdmin.xpath.Apply");
		WebUtil.wait(5000);
		WebUtil.click("MCPMAdmin.xpath.CheckBox");
		WebUtil.click("MCPMAdmin.xpath.EditUser");
		WebUtil.wait(5000);
	    ele=driver.findElement(By.xpath("//div[@id='duser-dlg']//div[contains(@id,'gridview')]//table//tbody//tr//td//div[contains(text(),'"+roleName+"')]"));
		WebUtil.actionClass(ele, driver);
		WebUtil.wait(2000);
		WebElement save=driver.findElement(By.xpath("//div[@id='duser-dlg']//div[3]//button/span[contains(text(),'Save')]"));
		actVal = WebUtil.verifyWebElementExists("WebElement",save);
		WebUtil.actionClass(save, driver);	
		ReportUtil.reportWebElement("Verify Save is clicked: ", actVal);
		WebUtil.wait(4000);
		WebElement ok=driver.findElement(By.xpath("//div[@role='alertdialog']//button/span"));
		actVal = WebUtil.verifyWebElementExists("WebElement",ok);
		WebUtil.actionClass(ok, driver);
		ReportUtil.reportWebElement("Verify Ok is clicked: ", actVal);
		
	}
	
	public void removeRole(WebDriver driver, String roleName)
	{
		boolean actVal=false;
		WebElement ele=null;
		ele=driver.findElement(By.xpath("//div[@id='duser-dlg']//span[contains(text(),'User Roles')]/ancestor::div[contains(@id,'ext-comp-')]//div[3]//table//tbody//tr//td//div[contains(text(),'"+roleName+"')]"));
		WebUtil.actionClass(ele, driver);
		WebElement save=driver.findElement(By.xpath("//div[@id='duser-dlg']//div[3]//button/span[contains(text(),'Save')]"));
		actVal = WebUtil.verifyWebElementExists("WebElement",save);
		WebUtil.actionClass(save, driver);	
		ReportUtil.reportWebElement("Verify Save is clicked: ", actVal);
		WebUtil.wait(4000);
		WebElement ok=driver.findElement(By.xpath("//div[@role='alertdialog']//button/span"));
		actVal = WebUtil.verifyWebElementExists("WebElement",ok);
		WebUtil.actionClass(ok, driver);
		ReportUtil.reportWebElement("Verify Ok is clicked: ", actVal);
	}
	
	public int verifyIfUserHaveThePassedRole(WebDriver driver,String emailId,String roleName)
	{
		boolean actVal=false;
		WebElement ele=null;
		WebUtil.setWebEdit("MCPMAdmin.name.UserName", emailId);
		WebUtil.clickedWithAction(driver, "MCPMAdmin.xpath.SelectuserName");
		WebUtil.click("MCPMAdmin.xpath.Apply");
		WebUtil.wait(5000);
		WebUtil.click("MCPMAdmin.xpath.CheckBox");
		WebUtil.click("MCPMAdmin.xpath.EditUser");
		WebUtil.wait(5000);
		int size=driver.findElements(By.xpath("//div[@id='duser-dlg']//span[contains(text(),'User Roles')]/ancestor::div[contains(@id,'ext-comp-')]//div[3]//table//tbody//tr//td//div[contains(text(),'"+roleName+"')]")).size();
		return size;
	}

}
