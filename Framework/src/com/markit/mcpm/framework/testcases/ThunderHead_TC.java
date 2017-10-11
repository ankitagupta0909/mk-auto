package com.markit.mcpm.framework.testcases;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.WebUtil;
import com.markit.framework.mcpm.pageMethods.ThunderHeadPage;
import com.markit.framework.mcpm.pageMethods.NavigationPage;

public class ThunderHead_TC {
	NavigationPage nav=new NavigationPage();
	ThunderHeadPage th=new ThunderHeadPage();
	public void tc_01_AddDocBox(WebDriver driver)
	{
		nav.navigateToDocumentNegotiation(driver);
		th.createDocBox(driver);
		WebUtil.closeBrowser(driver);
	}

}
