package com.markit.framework.mcpm.pageMethods;
import java.sql.Connection;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.markit.common.framework.util.DBUtility;
import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.LoginUtility;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;


public class MCPMLoginpage {

	public static String username=null;
	public static String companyName=null;
	
	public static String  login1(WebDriver driver, List<String> testdata) {
		
		LoginUtility loginobj=new LoginUtility();
		loginobj.login(driver, testdata, "MCPMLogin.id.username", "MCPMLogin.id.password", "MCPMLogin.xpath.loginbtn", "MCPM.xpath.logout");
		companyName=ExcelUtil.fn_FetchFieldValue(testdata, "Company_Name");
		WebUtil.refreshURL(driver);
		WebUtil.wait(20000);
		return username;
	}
	
	public static void mcpmStageLogin(WebDriver driver,List<String> testdata) 
	{
		String username = ExcelUtil.fn_FetchFieldValue(testdata, "User_Name");
		String password = ExcelUtil.fn_FetchFieldValue(testdata, "Password");

		WebUtil.setWebEdit("MCPMStageLogin.xpath.UserName",username);
		WebUtil.setWebEdit("MCPMStageLogin.xpath.Password",password);
		WebUtil.clickedWithAction(driver, "MCPMStageLogin.xpath.Login");
		System.out.println("Loading");
		WebUtil.wait(35000);
		System.out.println("Time out");
		boolean actVal = false;
		WebElement logoutlink= WebUtil.findElement("MCPMStageLogin.xpath.LogOut",driver);
		actVal=WebUtil.verifyWebElementExists("WebLink",logoutlink);
		ReportUtil.reportWebElement("Verify logout exists",actVal);
		
	}
	
	public static String dbValidationforUserName(Connection con)
	{
		
		String firstName=null;
		String LastName=null;
		if(con!=null)
		{		
			firstName=DBUtility.getColData(con,"select * from mc_users where username='"+username+"'and deleted=0","fname");
			LastName=DBUtility.getColData(con,"select * from mc_users where username='"+username+"'and deleted=0","lname");
		}
		String name=firstName+" "+LastName;
		return name;		
	}
	
	public void mcpmTOU(WebDriver driver)
	{
		NavigationPage.setMcpmFrame(driver);
		WebUtil.click("MCPM.xpath.MCPMTOU");
		WebUtil.click("MCPM.xpath.MCPMIAgreeBtn");
		WebUtil.wait(5000);
		
	}
	

}


