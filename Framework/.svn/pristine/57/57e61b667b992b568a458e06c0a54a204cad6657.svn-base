package com.markit.framework.mcpm.pageMethods;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;


public class RequestTrackerpage {
	
	public void checkStatusOfRAFandEntitybatch(WebDriver driver)
	{
		String readValue=null;
		String status=null;
		NavigationPage nav= new NavigationPage();
		NavigationPage.setMcpmFrame(driver);
		nav.navigateToRequestTracker(driver);
		NavigationPage.setMcpmFrame(driver);
		readValue=WebUtil.readElementText(driver, "RequestTracker.xpath.RequestRFA");
		if(readValue.equalsIgnoreCase("Request for RFA onboarding"));
		{
			status=WebUtil.readElementText(driver, "RequestTracker.xpath.StatusRFA");
			statusCheck(driver,status);
		}
		readValue=WebUtil.readElementText(driver, "RequestTracker.xpath.RequestEntity");
		if(readValue.equalsIgnoreCase("Fund Bulk Upload"));
		{
			status=WebUtil.readElementText(driver, "RequestTracker.xpath.StatusEntity");
			statusCheck(driver,status);
		}
	}
	
	public void statusCheck(WebDriver driver,String status)
	{
		if(status.equalsIgnoreCase("Complete"))
		{
			ReportUtil.reportStringMatch("Verify the status of RFAOnboarding bulk request", "Complete", status);
		}
		if(status.equalsIgnoreCase("Pending"))
		{
			String currentStatus=WebUtil.readElementText(driver, "RequestTracker.xpath.StatusRFA");
			while(currentStatus=="Pending"){
				WebUtil.clickedWithAction(driver, "RequestTracker.xpath.Refresh");
				currentStatus=WebUtil.readElementText(driver, "RequestTracker.xpath.StatusRFA");
			}
		}
		if(status.equalsIgnoreCase("Closed with error"))
		{
			ReportUtil.reportStringMatch("Verify the status of RFAOnboarding bulk request", "Complete", status);
			driver.close();
		}
		
	}

	
	}


