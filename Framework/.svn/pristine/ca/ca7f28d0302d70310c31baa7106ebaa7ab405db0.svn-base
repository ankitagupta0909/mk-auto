package com.markit.framework.mcpm.pageMethods;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.markit.common.framework.util.ExcelUtil;
import com.markit.common.framework.util.ReportUtil;
import com.markit.common.framework.util.WebUtil;

public class OwnershipControlReportingMatchPage {
	 public static String controllerLegalEntityName=null;
	 private static ArrayList<String> actualValue=new ArrayList<String>();
	 private  Integer randomInt=WebUtil.generateRandomNumber();
	 private String random=WebUtil.removeThepassedItemFromString(randomInt.toString(), "-");
	public void filterOCR(WebDriver driver,String filterXpath,String filterInput)
	{
		NavigationPage.setMcpmFrame(driver);
		WebUtil.setWebEdit(filterXpath, filterInput);
		WebUtil.clickedWithAction(driver, "OCRMatch.xpath.ApplyFilerLegalEntity");
	}	

	public class EntityInformation
	{
				
		public void clickAtEntityInfoTab(WebDriver driver)
		{
			WebUtil.clickedWithAction(driver, "OCRMatch.xpath.EntityInfoTab");
			WebUtil.wait(2000);
		}
		
		public void readContentOFEntityInfoTable(WebDriver driver)
		{
			actualValue.clear();
			List<WebElement> ele=new ArrayList<WebElement>();
			String value=null;		
			ele=WebUtil.findElements("OCRMatch.xpath.TableRow", driver);
			System.out.println(ele.size());
			for(int i=3;i<=ele.size();i++)
			{
				value=driver.findElement(By.xpath("//div[@id='mde_appOcrPanel']//table//tr[2]//td["+i+"]//div")).getText();
				actualValue.add(value);	
				
			}
			
			
		}
		
		public void clearFilter(WebDriver driver)
		{
			WebUtil.clickedWithAction(driver, "OCRMatch.xpath.ClearFilterLE");
			WebUtil.wait(3000);
		}
		

		public void verifyContactLEGrid(WebDriver driver)
		{
			System.out.println("Expected "+OcrChevron.cotactLE.length);
			System.out.println("Actual "+actualValue.size());
		
			for(int i=0;i<OcrChevron.cotactLE.length;i++)
			{
				ReportUtil.reportStringMatch("Value comparision", OcrChevron.cotactLE[i], actualValue.get(i));
				//System.out.println(OcrChevron.cotactLE[i]);
			}
		}
		
		public void verifyDownloadOCRInfo(WebDriver driver)
		{
			
			String username=System.getProperty("user.name");
			String path="C:/Users/"+username+"/Downloads";
			WebUtil.emptyAFolder(path);
			WebUtil.clickedWithAction(driver, "OCRMatch.xpath.DownloadOCRInfo");
			WebUtil.wait(4000);
			List<String> fileName=WebUtil.readFilesNameInAFolder(path);
			ReportUtil.reportStringMatch("Verify the downloaded file name of OCR Info", "Download All OCR Information.xlsx", fileName.get(0).toString());
			
		}
		
		public void filterEntityInfoTab(WebDriver driver,String filterXpath,String filterInput)
		{
			NavigationPage.setMcpmFrame(driver);
			WebUtil.setWebEdit(filterXpath, filterInput);
			WebUtil.wait(2000);
			WebUtil.clickedWithAction(driver, "OCRMatch.xpath.ApplyFileterEntityInfo");
			WebUtil.wait(2000);
		}
		
		
		public void editFromEntityInfoTab(WebDriver driver)
		{			
			WebUtil.clickedWithAction(driver, "OCRMatch.xpath.EditEntityInfo");
			WebUtil.wait(4000);
		}
		public void selectAllEntityInfoTab(WebDriver driver)
		{			
			WebUtil.clickedWithAction(driver, "OCRMatch.xpath.SelectAllEntityInfo");
			WebUtil.wait(4000);
		}
		public String editContactLEDetails(WebDriver driver,String fieldName,String xpathName,String inputValue)
		{
			WebUtil.wait(2000);
			WebUtil.clickedWithAction(driver, "OCRMatch.xpath.EditEntityInfo");
			WebUtil.wait(4000);
			String value=WebUtil.setWebEdit(xpathName, inputValue);
			switch(fieldName)
			{
			case "LegalEntityFname":
				OcrChevron.cotactLE[0]=value;
				break;
			case "LegalEntityLname":
				OcrChevron.cotactLE[1]=value;
				break;
			case "LegalEntityJobTilte":
				OcrChevron.cotactLE[2]=value;
				break;
			case "LegalEntityContactRel":
				OcrChevron.cotactLE[3]=value;
				break;
			case "LegalEntityEmail":
				OcrChevron.cotactLE[4]=value;
				break;
			case "LegalEntityPhone":
				OcrChevron.cotactLE[5]=value;
				break;
			default:
				System.out.println("Invalid entry");
				break;				
			}
				
			WebUtil.clickedWithAction(driver, "OCRChevron.xpath.Save");
			WebUtil.wait(2000);
			WebUtil.clickedWithAction(driver, "OCRChevron.xpath.SaveOk");
			WebUtil.clickedWithAction(driver, "OCRChevron.xpath.Exit");
			WebUtil.wait(3000);
			return value;
		}
		
		
	}
	
	public class LegalEntityAndControllerInfo
	{
		
		public String addControllerLE(WebDriver driver)
		{
			
			WebUtil.clickedWithAction(driver, "OCRMatch.xpath.Add");
			WebUtil.clickedWithAction(driver, "OCRMatchAdd.xpath.ControlerLE");
			String value=WebUtil.setWebEdit("OCRMatchAdd.xpath.CLEName", "TabControllerLEName"+random);
			controllerLegalEntityName=value;
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CLEAddress", "TabControllerLEAddress"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CLEEmail", "TabCLEemail"+random+"@markit.com");
			String fName=WebUtil.setWebEdit("OCRMatchAdd.xpath.CLEFName", "TabCLEFName"+random);	
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CLELName", "TabCLELName"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CLERelOwner", "TabCLERelOwner"+random);			
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CLELEI", "ABCDEFGHIJKLMNOP"+random.substring(0, 4));
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CLECity", "TabCLECity"+random);			
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CLEState", "TabCLEState"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CLEZip", "Tab"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CLEPhone", "79797879"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CLECountry", "Bahamas");
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CLEJobTitle", "TabCLEJobTitle"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CLERelToCon", "TabCLERelToController"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CLEPhone2", "565665656"+random);			
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CLEEmail2", "TabCLEEmail2"+random+"@markit.com");
			WebUtil.clickedWithAction(driver, "OCRMatchAdd.xpath.Save");
			WebUtil.wait(2000);
			WebUtil.clickedWithAction(driver, "OCRMatchAdd.xpath.SaveOK");	
			WebUtil.wait(2000);	
			return fName;
		}
		
		public String addControllerNP(WebDriver driver)
		{
			WebUtil.clickedWithAction(driver, "OCRMatch.xpath.Add");
			WebUtil.clickedWithAction(driver, "OCRMatchAdd.xpath.CNPCheckBox");
			String fName=WebUtil.setWebEdit("OCRMatchAdd.xpath.CNPFName", "TabControllerNPFName"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CNPLName", "TabControllerNPLName"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CNPRelToOwner", "TabCNPRelOwner"+random);			
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CNPJobTitle", "TabCNPJob"+random);			
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CNPAddress", "TabCNPAddress"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CNPEmail", "TabCNPEmail"+random+"@markit.com");
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CNPEmployeeName", "TabCNPEName"+random);			
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CNPCity", "TabCNPCity"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CNPState", "TabCNPState"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CNPZip", "Tab"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CNPPhone", "423442"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.CNPCountry", "Algeria");	
			WebUtil.clickedWithAction(driver, "OCRMatchAdd.xpath.CNPCountryOption");
			WebUtil.clickedWithAction(driver, "OCRMatchAdd.xpath.Save");
			WebUtil.wait(2000);
			WebUtil.clickedWithAction(driver, "OCRMatchAdd.xpath.SaveOK");	
			WebUtil.wait(2000);
			return fName;
		}
		
		public String addLegalEntityContact(WebDriver driver)
		{
			WebUtil.clickedWithAction(driver, "OCRMatch.xpath.Add");
			WebUtil.clickedWithAction(driver, "OCRMatchAdd.xpath.LECCheckBox");
			String fName=WebUtil.setWebEdit("OCRMatchAdd.xpath.LECFName", "TabContactLEFName"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.LECLName", "TabContactLELName"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.LECJobTitle", "TabContactLEJob"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.LECContactReltoOwner", "TabContactLERel"+random);			
			WebUtil.setWebEdit("OCRMatchAdd.xpath.LECPhone", "342342342"+random);
			WebUtil.setWebEdit("OCRMatchAdd.xpath.LECEmail", "TabContactLEEmail"+random+"@markit.com");		
			WebUtil.clickedWithAction(driver, "OCRMatchAdd.xpath.Save");
			WebUtil.wait(2000);
			WebUtil.clickedWithAction(driver, "OCRMatchAdd.xpath.SaveOK");	
			WebUtil.wait(2000);	
			return fName;
		}
		
		public void clickAtLEandControllerTab(WebDriver driver)
		{
			WebUtil.clickedWithAction(driver, "OCRMatch.xpath.ControllerInfoTab");
			WebUtil.wait(2000);
		}
		
		public void readContentOFControllerTable(WebDriver driver,int startFrom)
		{
			actualValue.clear();
			List<WebElement> ele=new ArrayList<WebElement>();
			String value=null;		
			ele=WebUtil.findElements("OCRMatch.xpath.ControllerTableRow", driver);
			System.out.println(ele.size());
			for(int i=startFrom;i<=ele.size();i++)
			{
				value=driver.findElement(By.xpath("//div[contains(@id,'tabpanel')]/div[2]/div[2]/div[2]//table//tbody//tr//td["+i+"]//div")).getText();
				ReportUtil.reportInputValueOnly("OCR Match Grid Values", value, true);
				actualValue.add(value);	
				
			}			
		}
		public void verifyContactControllerGrid(WebDriver driver,String[] arrayName)
		{
			System.out.println("Expected "+OcrChevron.cotactLE.length);
			System.out.println("Actual "+actualValue.size());
		
			for(int i=0;i<OcrChevron.cotactLE.length;i++)
			{
				ReportUtil.reportStringMatch("Value comparision", arrayName[i], actualValue.get(i));
				//System.out.println(OcrChevron.cotactLE[i]);
			}
		}
		
		public void verifyDownloadLEandControllerInfo(WebDriver driver)
		{
			
			String username=System.getProperty("user.name");
			String path="C:/Users/"+username+"/Downloads";
			WebUtil.emptyAFolder(path);
			WebUtil.clickedWithAction(driver, "OCRMatch.xpath.DownloadLEandControllerInfo");
			WebUtil.wait(4000);
			List<String> fileName=WebUtil.readFilesNameInAFolder(path);
			ReportUtil.reportStringMatch("Verify the downloaded file name of LE and Controller info", "Legal Entity Contact and Controller Information.xlsx", fileName.get(0).toString());
			
		}
		public void selectAll(WebDriver driver)
		{
			WebUtil.clickedWithAction(driver, "OCRChevron.xpath.SelectAllController");
			WebUtil.wait(2000);
		}
		public void filterControllerInfoTab(WebDriver driver,String filterXpath,String filterInput)
		{
			NavigationPage.setMcpmFrame(driver);
			WebUtil.setWebEdit(filterXpath, filterInput);
			WebUtil.wait(2000);
			WebUtil.clickedWithAction(driver, "OCRMatch.xpath.ConttrollerApplyFilter");
			//OCRMatch.xpath.ConttrollerApplyFilter
			//OCRMatch.xpath.ApplyFileterEntityInfo
			WebUtil.wait(2000);
		}
		
		public void clearfilter(WebDriver driver)
		{
			WebUtil.clickedWithAction(driver, "OCRMatch.xpath.ClearFilterController");
			WebUtil.wait(3000);
		}
		
		public void editControllersDetails(WebDriver driver,String controllerType,String feildName,String xpthName,String inputValue)
		{
			WebUtil.clickedWithAction(driver, "OCRChevron.xpath.EditOnControllerInfo");
					
			int size=WebUtil.getSizeOfElement(driver, "OCRChevron.xpath.ContollerDialog");
			if(size!=0)
			{  
				 if(controllerType.contentEquals("NP"))
				  {
					String value=WebUtil.setWebEdit(xpthName, inputValue);
					switch(feildName)
					{
					case "ControllerNPRelationship":
						OcrChevron.controllerNP[0]=value;
						break;
					case "ControllerNPJob":
						OcrChevron.controllerNP[1]=value;
						break;
					case "ControllerNPFName":
						OcrChevron.controllerNP[2]=value;
						break;
					case "ControllerNPLName":
						OcrChevron.controllerNP[3]=value;
						break;
					case "ControllerNPEmail":
						OcrChevron.controllerNP[4]=value;
						break;
					case "ControllerNPphone":
						OcrChevron.controllerNP[5]=value;
						break;
					default:
						System.out.println("Invalid entry");
						break;	
					}
					WebUtil.setWebEdit("OCRMatch.xpath.ControllerNPCountry", "Aruba");
				  }
				 if(controllerType.contentEquals("LE"))
				 {
					 String value=WebUtil.setWebEdit(xpthName, inputValue);
						switch(feildName)
						{
						case "ControllerLEName":
							OcrChevron.controllerLE[0]=value;
							break;
						case "ControllerLERelOwner":
							OcrChevron.controllerLE[1]=value;
							break;
						case "ControllerLEJobTitle":
							OcrChevron.controllerLE[2]=value;
							break;
						case "ControllerLEFName":
							OcrChevron.controllerLE[3]=value;
							break;
						case "ControllerLELName":
							OcrChevron.controllerLE[4]=value;
							break;
						case "ControllerLEemail":
							OcrChevron.controllerLE[5]=value;
							break;
						case "ControllerLEPhone":
							OcrChevron.controllerLE[6]=value;
							break;
						default:
							System.out.println("Invalid entry");
							break;	
						}
				 }
				 WebUtil.clickedWithAction(driver, "OCRMatch.xpath.SaveEditController");
				 WebUtil.wait(2000);
				 WebUtil.clickedWithAction(driver, "OCRMatch.xpath.SaveEditControllerOK");
			}
	    }
			

	}

}
