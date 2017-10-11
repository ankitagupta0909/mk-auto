package com.markit.kyc.framework.testcases;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.framework.kyc.pageMethods.Loginpage;
import com.markit.framework.mcpm.pageMethods.MCPMLoginpage;

public class LoginTC {


	public static WebDriver tc_01_Login_KYC(String testcasename,String testdatasheetpath,String sheetname) throws Exception
	{
		ReportUtil.setRepHeader(testcasename);	
		List<String> testdata=ExcelUtil.fn_GetTestData(testdatasheetpath,sheetname,"AutomationTestCaseName",testcasename);
		String url=WebUtil.getURL(testcasename,testdatasheetpath,sheetname);
		String browsername=WebUtil.getBrowserName(testcasename,testdatasheetpath,sheetname);
		WebDriver driver=WebUtil.openBrowser(browsername, url);
		Loginpage.login(driver, testdata);
		/*MCPMLoginpage.login1(driver, testdata);*/
		//MCPMLoginpage.login1(driver,"tc_03_Login_KYC");
		
		return driver;
	}
	

	///////////Verify that 'Terms of Use Agreement' text is clickable (KYC_Sub_Login_M0002)//////////////////
	public void tc_02_verifyTOALink(WebDriver driver) throws InterruptedException
	{
		//WebElement termsofuseLink= WebUtil.findElement("WKYCLogin.xpath.Terms",driver);
	WebUtil.switchTabandGetBacktoBasePage(driver, "KYCLogin.xpath.Terms", "http://www.kyc.com/Terms-Of-Use","KYC Services Terms Of Use");
	//WebUtil.closeBrowser(driver); 
	}

	//////////Verify that 'support page' text is clickable (KYC_Sub_Login_M0004)//////////
	public void tc_03_verifySupportPageLink(WebDriver driver) throws InterruptedException
	{
		WebUtil.switchTabandGetBacktoBasePage(driver, "KYCLogin.xpath.Support", "http://www.markit.com/contact-us?","Support");
	}


	///////////Verify that the footer links are clickable(KYC_Sub_Login_M0018)////////////
	public void tc_04_verifyFooterTOULink(WebDriver driver) throws InterruptedException
	{
		WebUtil.switchTabandGetBacktoBasePage(driver, "KYCLogin.xpath.FooterTOU", "http://www.kyc.com/Terms-Of-Use","KYC Services Terms Of Use");
	}
	///////////Verify that the footer links are clickable(KYC_Sub_Login_M0018)////////////
	public void tc_05_verifyFooterPrivacyLink(WebDriver driver) throws InterruptedException
	{
		WebUtil.switchTabandGetBacktoBasePage(driver, "KYCLogin.xpath.FooterPrivacy", "http://www.kyc.com/Privacy-and-Cookie-Policy","Privacy and Cookie Policy");
	}
	///////////Verify that the footer links are clickable(KYC_Sub_Login_M0018)////////////
	public void tc_06_verifyFooterDisclaimerLink(WebDriver driver) throws InterruptedException
	{
		WebUtil.switchTabandGetBacktoBasePage(driver, "KYCLogin.xpath.FooterDisclaimer", "http://www.kyc.com/Disclaimer","Disclaimer");
	}


	public WebDriver tc_07_verifyLoginPage(String testcasename,String testdatasheetpath,String sheetname) throws Exception
	{
		// put inside WebUtil library function VerifyWebElementExist
				String url=WebUtil.getURL(testcasename,testdatasheetpath,sheetname);
				String browsername=WebUtil.getBrowserName(testcasename,testdatasheetpath,sheetname);
				WebDriver driver=WebUtil.openBrowser(browsername, url);
				WebElement login_button=WebUtil.findElement("Login.xpath.loginbtn",driver);
				//WebElement login_button=WebUtil.findElement("Login.xpath.loginbtn",driver);
				
				boolean actVal=WebUtil.verifyWebElementExists("WebButton",login_button);
				ReportUtil.reportWebElement("Login page",actVal);
				return driver;
	}
	
	public void tc_08_logoutLink(WebDriver driver)
	{
		WebElement logout= WebUtil.findElement("Login.xpath.logoutlink",driver);
		logout.click();
		WebElement login_button=WebUtil.findElement("Login.xpath.loginbtn",driver);
			
		boolean actVal=WebUtil.verifyWebElementExists("WebButton",login_button);
		ReportUtil.reportWebElement("Verify logout button exists on Login page",actVal);
	}
}



