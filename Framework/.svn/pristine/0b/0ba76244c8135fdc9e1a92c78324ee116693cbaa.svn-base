package com.markit.framework.mcpm.pageMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;
import com.markit.common.framework.util.XPath;

public class NavigationPage {


	public WebDriver navigateToKYCServices(WebDriver driver) 
	{	
		
		WebDriver driver1=setMcpmFrame(driver);
		WebElement kycService=WebUtil.findElement("Menu.xpath.KYCServices",driver1);
		boolean actVal;
		actVal = WebUtil.verifyWebElementExists("WebElement", kycService);

		WebUtil.actionClass(kycService, driver1);
		ReportUtil.reportWebElement("Clicked on KYC Services", actVal);

		WebElement kycManage=WebUtil.findElement("SubMenu.xpath.ManageKYC",driver1);
		actVal = WebUtil.verifyWebElementExists("WebElement", kycManage);
		WebUtil.actionClass(kycManage, driver1);
	    ReportUtil.reportWebElement("Clicked on Manage KYC Entities", actVal);	   

		WebUtil.wait(20000);
		setMcpmFrame(driver);

		return driver1;

	}


	public WebDriver navigateBackToHomePage(WebDriver driver,String element) 
	{
		WebElement cp=WebUtil.findElement(element,driver);

		if(cp.isDisplayed())
		{
			WebUtil.actionClass(cp, driver);
		}


		return driver;

	}

	public WebDriver navigateBackToDashBoard(WebDriver driver,String element) 
	{
		WebElement btd=WebUtil.findElement(element,driver);

		boolean actVal;
		if(btd.isDisplayed())
		{
			actVal = WebUtil.verifyWebElementExists("WebElement", btd);
			WebUtil.actionClass(btd, driver);
			ReportUtil.reportWebElement("Back to Dashboard", actVal);
		}

		WebUtil.wait(2000);
		return driver;

	}

	/**This method navigates to RFA from Home Menu option
	 * @param driver
	 * @return
	 */
	public WebDriver navigateToRFA(WebDriver driver) 
	{

		WebDriver driver1=setMcpmFrame(driver);
		WebElement rfa=WebUtil.findElement("Menu.xpath.RFA",driver1);
		boolean actVal;
		actVal = WebUtil.verifyWebElementExists("WebElement", rfa);

		WebUtil.actionClass(rfa, driver1);
		
		WebElement manageRFA=WebUtil.findElement("SubMenu.xpath.RFA",driver1);
		actVal = WebUtil.verifyWebElementExists("WebElement", manageRFA);
		
		WebUtil.actionClass(manageRFA, driver1);
	
		//ReportUtil.reportWebElement("Verify RFA in menu has been clicked", actVal);
		
		WebUtil.wait(20000);
		
		WebUtil.refreshURL(driver1);
		WebUtil.wait(20000);
		NavigationPage.setMcpmFrame(driver1);
		NavigationPage.verifyMcpmPageLoad(driver1, "RFA.xpath.NavigMasterlist");
		
		return driver1;

	}
	
	public WebDriver navigateToAdminPage(WebDriver driver)
	{
		WebUtil.clickedWithAction(driver, "MCPM.xpath.ToolsMenuBar");
		/*WebElement menu=driver.findElement(By.xpath("//li[@class='mdeCssMenui'][8]/a"));
		menu.click();*/
		WebUtil.clickedWithAction(driver, "MCPM.xpath.UserSubMenu");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver,"MCPM.name.DefousMenu");
		return driver;		
	}
	
	

	/** This sets frame for any module of MCPM
	 * @param driver
	 * @return
	 */
	public static WebDriver setMcpmFrame(WebDriver driver){
 		driver.switchTo().defaultContent();
 		String iFrame=XPath.myprop.get("MCPM.ufeqa.iframe");
 		driver.switchTo().frame(driver.findElement(By.name(iFrame)));
     	return driver;
 		
 	}
	
	public WebDriver navigateToBulkUploadOfRFA(WebDriver driver) 
	{	
		NavigationPage.setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "MCPMRFA.xpath.Tools");
		WebUtil.clickedWithAction(driver, "MCPMRFA.xpath.RFAOnboarding");
		WebUtil.wait(4000);
		setMcpmFrame(driver);
		return driver;

	}
	
	public WebDriver navigateToBulkUploadOfEntity(WebDriver driver)  
	{	
		NavigationPage.setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "MCPMRFA.xpath.Tools");
		WebUtil.clickedWithAction(driver, "MCPM.xpath.EntityBatchUpload");
		WebUtil.wait(4000);
		setMcpmFrame(driver);
		return driver;

	}
	
	public WebDriver navigateToRequestTracker(WebDriver driver)  
	{	
		setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "Menu.xpath.Manage");
		WebUtil.clickedWithAction(driver, "Menu.xpath.RequestTracker");
		WebUtil.wait(4000);
		setMcpmFrame(driver);
		return driver;

	}
	
	public WebDriver navigateToKYCDashboardTools(WebDriver driver)
	{
		setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "KYCDashBoard.xpath.Tools");
		WebUtil.wait(1000);
		WebUtil.clickedWithAction(driver, "KYCDashBoard.xpath.CompanyActions");
		WebUtil.wait(9000);
		setMcpmFrame(driver);
		return driver;
		
	}
	
	public WebDriver navigateToKYCDashboardBulkInternalConfirmation(WebDriver driver)
	{
		setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "KYCDashBoard.xpath.Tools");
		WebUtil.wait(1000);
		WebUtil.clickedWithAction(driver, "KYCRegression.xpath.BulkIC");
		WebUtil.wait(20000);
		setMcpmFrame(driver);
		return driver;
		
	}
	
	public WebDriver navigateToKYCDashboardBulkPermissionRequests(WebDriver driver)
	{
		setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "KYCDashBoard.xpath.Tools");
		WebUtil.wait(1000);
		WebUtil.clickedWithAction(driver, "KYCRegression.xpath.BulkPermissonRequest");
		WebUtil.wait(3000);
		setMcpmFrame(driver);
		return driver;
		
	}
	
	
	public void navigateToCreateEntity(WebDriver driver){		
		setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "Menu.xpath.Create");
		WebUtil.clickedWithAction(driver, "SubMenu.xpath.NewEntity");		
		WebUtil.wait(5000);
	}
	
	
	public void navigateToCreateEntityInStage(WebDriver driver){		
	
		WebUtil.clickedWithAction(driver, "Menu.xpath.Create");
		WebUtil.clickedWithAction(driver, "SubMenu.xpath.NewEntity");		
		WebUtil.wait(5000);
	}
	
	public void navigateToManageEntities(WebDriver driver){		
		setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "Menu.xpath.Manage");
		WebUtil.clickedWithAction(driver, "SubMenu.xpath.ManageEntities");
		WebUtil.wait(5000);
		WebUtil.click("MCPM.name.MEIFilter");
		WebUtil.wait(2000);
	}

	
	public WebDriver navigateToManageDocuments(WebDriver driver)
	{
		setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "Menu.xpath.Manage");
		WebUtil.wait(1000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.DocumentsSubMenu");
		WebUtil.wait(4000);
		setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "MCPMDoc.name.Defocus");		
		WebUtil.wait(2000);
		return driver;
		
	}
	
	public WebDriver navigateToManageDocumentsInStage(WebDriver driver)
	{
		
		WebUtil.clickedWithAction(driver, "Menu.xpath.Manage");
		WebUtil.wait(1000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.DocumentsSubMenu");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.name.Defocus");		
		WebUtil.wait(2000);
		return driver;
		
	}
	
	public void navigateToManageAUM(WebDriver driver){		
		WebUtil.clickedWithAction(driver, "Menu.xpath.Manage");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "SubMenu.xpath.ManageAUM");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "MCPMAum.name.Defous");
	}

	
	public WebDriver navigateToDocumentNegotiation(WebDriver driver)
	{
		setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "Menu.xpath.Manage");
		WebUtil.clickedWithAction(driver, "MCPM.xpath.DocumentNegotiation");
		WebUtil.wait(7000);
		return driver;
		
	}
	
	public WebDriver navigateToDistributionGroups(WebDriver driver)
	{
		WebUtil.clickedWithAction(driver, "Menu.xpath.Manage");
		WebUtil.clickedWithAction(driver, "MCPM.xpath.DGSubMenu");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "MCPMDG.xpath.Defocus");
		return driver;
		
	}
	
	public WebDriver navigateToCPViewAUM(WebDriver driver)
	{
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.CPViewLink");
		WebUtil.clickedWithAction(driver, "MCPMAUM.xpath.CPAum");
		WebUtil.wait(3000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.CPApplyButton");
		WebUtil.wait(7000);
		return driver;
		
	}
	
	public WebDriver navigateToCPViews(WebDriver driver)
	{
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.CPViewLink");
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.CPDocLink");
		WebUtil.wait(5000);
		WebUtil.clickedWithAction(driver, "MCPMDoc.xpath.CPApplyButton");
		WebUtil.wait(10000);
		return driver;
		
	}

	public static void verifyMcpmPageLoad(WebDriver driver,String pathKey){
		for (int count=0;count<3;count++){
			try{
				NavigationPage.setMcpmFrame(driver);
				String Str = new String(pathKey);
				String[] locator = Str.split("\\.");
				WebElement elementx = null;
				String XPathVal=XPath.myprop.get(pathKey);
				if (locator[1].equals("xpath")){			
					elementx= driver.findElement(By.xpath(XPathVal));
				}
				else if (locator[1].equals("id")) {
					elementx=driver.findElement(By.id(XPathVal));
				}
				else if (locator[1].equals("css")) {
					elementx=driver.findElement(By.cssSelector(XPathVal));
				}
				else if (locator[1].equals("linkText")) {
					elementx=driver.findElement(By.linkText(XPathVal));
				}
				else if (locator[1].equals("name")) {
					elementx=driver.findElement(By.name(XPathVal));
				}  

			}
			catch(Exception e){
				ReportUtil.reportWebElement("Exception occured"+e.getMessage()+"Page not loaded, refreshing the page", true);
				WebUtil.refreshURL(driver);			
			}
		}
		NavigationPage.setMcpmFrame(driver);
	}
	
	public WebDriver navigateToKYCReportsAudit(WebDriver driver) 
	{	
		NavigationPage.setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "KYCRegression.xpath.ReportsMenuBar");
		WebUtil.clickedWithAction(driver, "KYCRegression.xpath.KYCAuditReportSubMenuBar");
		WebUtil.wait(4000);
		setMcpmFrame(driver);
		return driver;

	}
	public WebDriver navigateToKYCTrainingAddingNewEntity(WebDriver driver) 
	{	
		NavigationPage.setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "KYCRegression.xpath.TrainingMenuBar");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "KYCRegression.xpath.AddNewEntitySubMenuBar");
		WebUtil.wait(2000);
		setMcpmFrame(driver);
		return driver;

	}
	
	public void nameofApperaingMenu(WebDriver driver)
	{
		NavigationPage.setMcpmFrame(driver);
		List<WebElement> ele=new ArrayList<WebElement>();
		ele=WebUtil.findElements("MCPMKYCRegression.xpath.MenuBar", driver);
		List<String> name=new ArrayList<>();
		String menuname=null;
		String xpath1=null;
		String xpath2=null;
		String path=null;
		
		for(int i=2;i<=ele.size();i++)			
		{
			xpath1=WebUtil.returnXPathVal("MCPMKYCRegression.xpath.MenuBar");
			xpath2=WebUtil.returnXPathVal("MCPMKYCRegression.xpath.MenuBarValue");
			path= xpath1+"["+i+"]"+xpath2;
			menuname=driver.findElement(By.xpath(path)).getText();
			name.add(menuname);
		}
		
		for(int i=0;i<name.size();i++)
		{
		 ReportUtil.reportInputValueOnly("Name of Menu bar appering", name.get(i).toString(), true);
		 if(name.get(i).toString().equalsIgnoreCase("Manage KYC Entities"))
		 {
			 ReportUtil.reportStringMatch("Verify KYC menu is appearing", "Manage KYC Entities", name.get(i)); 
		 }
		}
	}
	
	public void navigateToKYCVerification(WebDriver driver)
	{
		NavigationPage.setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "KYCRegression.xpath.KYCDashboardMenu");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "KYCRegression.xpath.KYCVerificationSubMenu");
		WebUtil.wait(3000);
	}
	
	public WebDriver navigateToCFTCOwnershipandControlReporting(WebDriver driver)  
	{	
		setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "Menu.xpath.Manage");
		WebUtil.clickedWithAction(driver, "OCR.xpath.OCRNav");
		WebUtil.wait(4000);
		WebUtil.clickedWithAction(driver, "OCRMatch.name.PhoneFilter");
		setMcpmFrame(driver);
		return driver;

	}
	
	public WebDriver navigateToVM(WebDriver driver) 
	{
		setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "VM.xpath.IsdaAmendMenu");
		WebUtil.clickedWithAction(driver, "VM.xpath.VMMenu");
		WebUtil.wait(12000);
		setMcpmFrame(driver);
		return driver;	

	}
	public WebDriver navigateToSDL(WebDriver driver) 
	{
		setMcpmFrame(driver);
		WebUtil.clickedWithAction(driver, "VM.xpath.IsdaAmendMenu");
		WebUtil.clickedWithAction(driver, "SDL.xpath.SDLMenu");
		WebUtil.wait(12000);
		setMcpmFrame(driver);
		return driver;	

	}
}

