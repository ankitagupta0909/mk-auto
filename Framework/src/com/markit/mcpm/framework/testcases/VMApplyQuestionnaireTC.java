package com.markit.mcpm.framework.testcases;

import java.sql.Connection;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.WebUtil;
import com.markit.framework.mcpm.pageMethods.NavigationPage;
import com.markit.framework.mcpm.pageMethods.VMPage;

public class VMApplyQuestionnaireTC {
	NavigationPage nav=new NavigationPage();
	VMPage vm=new VMPage();
	
	public void applyQuestionnaire(WebDriver driver,Connection con)
	{
		boolean condition=false;
		
		nav.navigateToVM(driver);
		vm.selectItemsCountFromPaginationDropDown(driver);
		vm.clickAtApplyQuestionnaireLink(driver);
		int i=WebUtil.getSizeOfElement(driver, "VM.xpath.TableContent");
		if(i!=0)
		{
			 condition=true;
		}
		while(condition)
		{
			
			String count=vm.dbValidationForQueue(con);
			int check=Integer.parseInt(count);
			System.out.println("Value of count "+count);	
			if(check<=5)
			{
			//WebUtil.scrollBarUp(driver);
			vm.clickAtSelectAllLink(driver);
			vm.clickAtApplyQuestionnaireAction(driver);
			vm.clickAtRadioButtonToApplyQuestionnaire(driver, "QUestionnaire A");
			vm.clickAtApply(driver);
			vm.clickOnOkbuttonOnProcessingPopUp(driver);
			WebUtil.wait(60000);
			System.out.println("time out");
			/*vm.checkTheCurrentPage(driver);
			for(int j=0;j<=10;j++)
			{
			WebUtil.scrollBarUp(driver);
			}*/
			}
		}
	}
	
	
}
