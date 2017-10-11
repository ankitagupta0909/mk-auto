package com.markit.framework.mcpm.pageMethods;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.WebUtil;

public class CPAUMPage {
	
	public String getTheCountOfAUMFromTheGrid(WebDriver driver)
	{
		String count=null;
		String pagination=WebUtil.readElementText(driver, "MCPMDoc.xpath.CPCount");
		if(pagination.equalsIgnoreCase("No data to display"))
		{
			count="0";
		}
		else
		{
		int i=pagination.indexOf("of");
		count=pagination.substring(i+3);
		}
		System.out.println(count);
		//ExcelUtil.setColumnDataByColName("src/testdata/TestData_EntityDoc.xlsx", "CP", "CountOfDoc", 2, count);
		return count;
	}

	

}
