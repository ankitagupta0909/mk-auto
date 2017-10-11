package com.markit.framework.kyc.pageMethods;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.LoginUtility;

public class Loginpage {
	public static String username=null;

	/** Login method
	 * @param driver
	 * @param testdata
     */
	public static void login(WebDriver driver, List<String> testdata){	
		LoginUtility luobj=new LoginUtility();
		luobj.login(driver, testdata, "Login.id.username", "Login.id.password", "Login.xpath.loginbtn", "Login.xpath.logoutlink");

	}

	
}