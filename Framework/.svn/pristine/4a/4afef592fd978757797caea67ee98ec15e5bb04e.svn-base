package com.markit.framework.pega.pageMethods;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.LoginUtility;

public class PEGALogin {

	public static void loginPega(WebDriver driver,List<String> testdata)
	{		
		LoginUtility loginobj=new LoginUtility();
		loginobj.login(driver, testdata, "PEGALogin.xpath.username", "PEGALogin.xpath.password", "PEGALogin.xpath.LoginButton", "NA");
	}
}
