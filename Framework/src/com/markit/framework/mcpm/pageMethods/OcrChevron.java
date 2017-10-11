package com.markit.framework.mcpm.pageMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;

public class OcrChevron {
	 private static Integer randomInt=WebUtil.generateRandomNumber();
	 String random=WebUtil.removeThepassedItemFromString(randomInt.toString(), "-");
	 public static String wbPath="src/testdata/TestData_EntityDoc.xlsx";
	 public static String sheetName="OCR";
	 private static int iRowNum=1;
	 public static String[] cotactLE=new String[6];
	 public static String[] controllerNP=new String[6];
	 public static String[] controllerLE=new String[7];
	 

	public void clickonOcrChevron(WebDriver driver)
	{
		WebUtil.clickedWithAction(driver, "OCR.xpath.OcrChevron");
	}
	
	public void acceptTandC(WebDriver driver)
	{
		int size=0;
		size=WebUtil.getSizeOfElement(driver, "OCR.xpath.CheckboxTandC");
		if(size!=0)
		{
			WebUtil.click("OCR.xpath.CheckboxTandC");
			WebUtil.click("OCR.xpath.AcceptTandC");
		}
	}
	
	public void fillAddress(WebDriver driver)
	{
		WebUtil.setWebEdit("OCR.name.EntityAdd1", "Address"+random);
		WebUtil.setWebEdit("OCR.name.EntityEmail", "AddressEmail"+random+"@markit.com");		
		WebUtil.setWebEdit("OCR.name.EntityCity", "City"+random);
		WebUtil.setWebEdit("OCR.name.EntityState", "State"+random);
		WebUtil.setWebEdit("OCR.name.EntityZip", "Zip"+random);
		WebUtil.setWebEdit("OCR.name.EntityPhone", "44324"+random);
		WebUtil.setWebEdit("OCR.name.EntityCountry", "Austria");
	}
	
	public void fillLegalEntityContact(WebDriver driver)
	{
		String value=null;			
		
		value=WebUtil.setWebEdit("OCR.name.LegalEntityFname", "LegalEntityfirstName"+random);
		cotactLE[0]=value;
		
		
		value=WebUtil.setWebEdit("OCR.name.LegalEntityLname", "LegalEntitylastName"+random);		
		cotactLE[1]=value;
		
		
		value=WebUtil.setWebEdit("OCR.name.LegalEntityJobTilte", "LEJobTitle"+random);
		cotactLE[2]=value;
		
		
		value=WebUtil.setWebEdit("OCR.name.LegalEntityContactRel", "LEContactRel"+random);
		cotactLE[3]=value;
		
		
	
		value=WebUtil.setWebEdit("OCR.name.LegalEntityPhone", "79797879"+random);
		cotactLE[5]=value;
		
		
		value=WebUtil.setWebEdit("OCR.name.LegalEntityEmail", "legalEntityEmail"+random+"@markit.com");
		cotactLE[4]=value;
		
	
	}
	
	public void fillControllerDetails(WebDriver driver,String controllerType)
	{
		String value=null;
		WebUtil.clickedWithAction(driver, "OCR.xpath.AddNew");
		if(controllerType.equalsIgnoreCase("Legal Entity"))
		{
			
			WebUtil.clickedWithAction(driver, "OCR.xpath.LegalEntityCheckbox");
			value=WebUtil.setWebEdit("OCR.name.ControllerLEName", "ControllerLEName"+random);
			controllerLE[0]=value;
			WebUtil.setWebEdit("OCR.xpath.ControllerLEAdd1", "ControllerLEAddress"+random);
			WebUtil.setWebEdit("OCR.xpath.ControllerLEemailContact", "ControllerLEemailContact"+random+"@markit.com");
			value=WebUtil.setWebEdit("OCR.xpath.ControllerLEFName", "ControllerLEfirstName"+random);
			controllerLE[3]=value;
			value=WebUtil.setWebEdit("OCR.xpath.ControllerLELName", "ControllerLELastName"+random);
			controllerLE[4]=value;
			value=WebUtil.setWebEdit("OCR.xpath.ControllerLERelOwner","ControllerLErelOwner" +random);
			controllerLE[1]=value;
			WebUtil.setWebEdit("OCR.xpath.ControllerLELeiName","ABCDEFGHIJKLMNOP"+random.substring(0, 4));			
			WebUtil.setWebEdit("OCR.xpath.ControllerLECity", "ControllerLECity"+random);
			WebUtil.setWebEdit("OCR.xpath.ControllerLEState", "ControllerLEState"+random);
			WebUtil.setWebEdit("OCR.xpath.ControllerLEZip", "Zip"+random);			
			WebUtil.setWebEdit("OCR.xpath.ControllerLEPhoneContact", "83987193"+random);
			WebUtil.setWebEdit("OCR.xpath.ControllerLECountry", "Austria");
			value=WebUtil.setWebEdit("OCR.xpath.ControllerLEJobTitle", "ControllerLEJobTitle"+random);
			controllerLE[2]=value;		
			WebUtil.setWebEdit("OCR.xpath.ControllerLERel", "ControllerLERel"+random);	
			value=WebUtil.setWebEdit("OCR.xpath.ControllerLEPhone", "87087070"+random);	
			controllerLE[6]=value;
			value=WebUtil.setWebEdit("OCR.xpath.ControllerLEemail", "ControllerLE"+random+"@markit.com");
			controllerLE[5]=value;
			
		}
		if(controllerType.equalsIgnoreCase("Natural Person"))
		{
			WebUtil.clickedWithAction(driver, "OCR.xpath.ControllerNPCheckBox");
			value=WebUtil.setWebEdit("OCR.xpath.ControllerNPFName", "ControllerNPFName"+random);
			controllerNP[2]=value;
			value=WebUtil.setWebEdit("OCR.xpath.ControllerNPLName", "ControllerLNP"+random);
			controllerNP[3]=value;
			value=WebUtil.setWebEdit("OCR.xpath.ControllerNPRelationship", "ControllerNPRel"+random);
			controllerNP[0]=value;
			value=WebUtil.setWebEdit("OCR.xpath.ControllerNPJob", "ControllerNPJob"+random);
			controllerNP[1]=value;
			WebUtil.setWebEdit("OCR.xpath.ControllerNPAdd1", "ControllerNPAddress"+random);
			value=WebUtil.setWebEdit("OCR.xpath.ControllerNPEmail", "ControllerNPemail"+random+"@markit.com");
			controllerNP[4]=value;
			WebUtil.setWebEdit("OCR.xpath.ControllerNPEmployer", "ControllerNPEmployer"+random);			
			WebUtil.setWebEdit("OCR.xpath.ControllerNPCity", "ControllerNPCity"+random);
			WebUtil.setWebEdit("OCR.xpath.ControllerNPState", "ControllerNPState"+random);
			WebUtil.setWebEdit("OCR.xpath.ControllerNPZip", "Zip"+random);
			value=WebUtil.setWebEdit("OCR.xpath.ControllerNPphone", "878780"+random);	
			controllerNP[5]=value;
			WebUtil.setWebEdit("OCR.xpath.ControllerNPCountry", "Austria");		
			WebUtil.clickedWithAction(driver, "OCR.xpath.ControllerNPCountryOption");
		}
		WebUtil.clickedWithAction(driver, "OCR.xpath.ControllerSave");
		WebUtil.wait(3000);
		WebUtil.clickedWithAction(driver, "OCR.xpath.ControllerOK");
		
	}
	
	public void addReportingEntity(WebDriver driver,String entityName)
	{
		WebUtil.clickedWithAction(driver, "OCR.xpath.AddreportingEntity");
		WebElement ele=driver.findElement(By.xpath("//div[@id='mdeMultiSelectAddFcm']//table//tr//td//div[contains(text(),'"+entityName+"')]"));
		WebUtil.actionClass(ele, driver);
		WebUtil.clickedWithAction(driver, "OCR.xpath.SaveReportingEntity");
		WebUtil.clickedWithAction(driver, "OCR.xpath.AlertOk");
	}
	
	public void saveDetails(WebDriver driver)
	{
		WebUtil.clickedWithAction(driver, "OCRChevron.xpath.Save");
		WebUtil.wait(2000);
		WebUtil.clickedWithAction(driver, "OCRChevron.xpath.SaveOk");
		WebUtil.clickedWithAction(driver, "OCRChevron.xpath.Exit");
		WebUtil.wait(3000);
	}

	public void mandatoryFieldCheck(WebDriver driver)
	{
		List<WebElement> ele=new ArrayList<WebElement>();
		String value=null;
		ele=WebUtil.findElements("OCR.xpath.mandatory", driver);
		Integer sizeOfMandatory=ele.size();
		ReportUtil.reportStringContainsMatch("Mandoartory fields count for Address and Legal Entity Contact", "13",sizeOfMandatory.toString() );
		for(int i=0;i<ele.size();i++)
		{
			value=ele.get(i).getAttribute("name");
			ReportUtil.reportInputValueOnly("Mandatory fields name for Address and Legal Entity Contact", value, true);			
		}
		int size=WebUtil.getSizeOfElement(driver, "OCR.xpath.ControllerInfomandatory");
		if(size!=0)
		{
			ReportUtil.reportInputValueOnly("Controller Information Section is Mandoatory", "Controller Information", true);			
		}
		else
		{
			ReportUtil.reportInputValueOnly("Controller Information Section is not Mandoatory", "Controller Information", false);			
		}
		WebUtil.clickedWithAction(driver, "OCR.xpath.AddNew");
		WebUtil.clickedWithAction(driver, "OCR.xpath.LegalEntityCheckbox");
		ele=WebUtil.findElements("OCR.xpath.Controllermandatory", driver);
		sizeOfMandatory=ele.size();
		ReportUtil.reportStringContainsMatch("Mandoartory fields count for Legal Entity Controller", "16",sizeOfMandatory.toString() );
		for(int i=0;i<ele.size();i++)
		{
			value=ele.get(i).getAttribute("name");
			ReportUtil.reportInputValueOnly("Mandatory fields name for Legal Entity Controller", value, true);			
		}
		WebUtil.clickedWithAction(driver, "OCR.xpath.ControllerCancel");
		WebUtil.clickedWithAction(driver, "OCR.xpath.AddNew");
		WebUtil.clickedWithAction(driver, "OCR.xpath.ControllerNPCheckBox");
		ele=WebUtil.findElements("OCR.xpath.Controllermandatory", driver);
		sizeOfMandatory=ele.size();
		ReportUtil.reportStringContainsMatch("Mandoartory fields count for natural Person Controller", "12",sizeOfMandatory.toString() );
		for(int i=0;i<ele.size();i++)
		{
			value=ele.get(i).getAttribute("name");
			ReportUtil.reportInputValueOnly("Mandatory fields name for Natural Person Controller", value, true);			
		}
		WebUtil.clickedWithAction(driver, "OCR.xpath.ControllerCancel");
	}
	
	public void selectFormExistingForLEContact(WebDriver driver,String legalEntityContactName)
	{
		WebUtil.clickedWithAction(driver, "OCRChevron.xpath.SelectFromExisting");
		WebUtil.clickedWithAction(driver, "OCRChevron.xpath.OkWarning");
		int size=WebUtil.getSizeOfElement(driver, "OCRChevron.xpath.SelectDialog");
		if(size!=0)
		{
			WebElement ele=driver.findElement(By.xpath("//div[@id='ExistingControllerOrContactDialog']//table//tbody//tr//td/div[contains(text(),'"+legalEntityContactName+"')]"));
			WebUtil.actionClass(ele, driver);			
		}
	}
	
	public void selectFormExistingForController(WebDriver driver,String controllerName)
	{
		WebUtil.clickedWithAction(driver, "OCRChevron.xpath.SelectFromExistingController");
		int size=WebUtil.getSizeOfElement(driver, "OCRChevron.xpath.SelectDialog");
		if(size!=0)
		{
			WebElement ele=driver.findElement(By.xpath("//div[@id='ExistingControllerOrContactDialog']//table//tbody//tr//td[4]/div[contains(text(),'"+controllerName+"')]"));
			WebUtil.actionClass(ele, driver);			
		}
	}
	
	
	
	public void editFirstNameofOCRDetail(WebDriver driver,String type,String inputValue)
	{
		WebUtil.wait(2000);
		String value=null;	
		List<WebElement> ele=new ArrayList<WebElement>();
	    ele=driver.findElements(By.name("ocrFname"));

	    if(type.contentEquals("ControllerNP"))
	    {
	       	value=WebUtil.setWebEdit(ele.get(2), inputValue);	
	    	OcrChevron.controllerNP[2]=value;				
	    }
	    if(type.contentEquals("ControllerLE"))
	    {
	       	value=WebUtil.setWebEdit(ele.get(1), inputValue);	
		
	    	OcrChevron.controllerLE[3]=value;			
	    }
	    if(type.contentEquals("LE"))
	    {
	       	value=WebUtil.setWebEdit(ele.get(0), inputValue);			
	    	OcrChevron.cotactLE[0]=value;			
	    }

    }
	
	public void deleteControllerfromChevron(WebDriver driver,String controllerName)
	{
		WebElement ele=driver.findElement(By.xpath("//span[contains(text(),'"+controllerName+"')]/a"));
		WebUtil.actionClass(ele, driver);
		WebUtil.clickedWithAction(driver, "OCRChevron.xpath.OKDelete");
	}
		
	
}
