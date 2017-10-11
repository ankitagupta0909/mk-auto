package com.markit.common.framework.util;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginUtility {

	public void login(WebDriver driver, List<String> testdata,String unamePath,String pwdPath,String loginPath,String logoutPath)
		 {
		String actUsername = ExcelUtil.fn_FetchFieldValue(testdata, "User_Name");
		String actPassword = ExcelUtil.fn_FetchFieldValue(testdata, "Password");	
		WebUtil.setWebEdit(unamePath, actUsername);	
		WebUtil.setWebEdit(pwdPath, actPassword);
		WebUtil.click(loginPath);
		boolean actVal;
		if (logoutPath.equals("NA")){		
		}else{
		// Verify logout text
		WebElement logoutlink = WebUtil.findElement(logoutPath,driver);
		actVal = WebUtil.verifyWebElementExists("WebLink", logoutlink);
		ReportUtil.reportWebElement("Verify user is able to login successfully", actVal);}	
		WebUtil.wait(20000);
	}

}
