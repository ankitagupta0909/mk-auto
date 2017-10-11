package com.markit.framework.kyc.pageMethods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.openqa.selenium.WebDriver;

import com.markit.common.framework.util.WebUtil;



public class Admin {


	public static String  writeCsv(String file,String valueToBeReplaced) throws IOException {		
		String csvFile = readFile(file);
		System.out.println(csvFile);
	    EntityIdGenerator idgen = new EntityIdGenerator();		
	    long id=idgen.generate();
	    System.out.println(id);
		String legalName="Automation"+id;
		csvFile = csvFile.replaceAll(valueToBeReplaced, legalName);	
		System.out.println(csvFile);
		PrintWriter pw = new PrintWriter(new File(file));
        StringBuilder sb = new StringBuilder();
		sb.append(csvFile);
		pw.write(sb.toString());
		pw.close();		

		return legalName;
		
	}
		
		
	public static void setCsvDefaultState(String file,String legalName,String valueToBeReplaced) throws IOException{
				
		String csvUpdatedFile = readFile(file);
		csvUpdatedFile=csvUpdatedFile.replaceAll(legalName, valueToBeReplaced);
		PrintWriter pw = new PrintWriter(new File(file));
        StringBuilder sb = new StringBuilder();	
		sb.append(csvUpdatedFile);
		pw.write(sb.toString());
		pw.close();
	}
			
	
	private static String readFile(String file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");
	    try {
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(ls);
	        }
	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}
	
	public void navigateToBatchTab()
	{
		WebUtil.click("AdminPotal.xpath.BatchesTab");
		WebUtil.wait(3000);
	}
	
	public void clickAtActionsButton()
	{
		WebUtil.click("AdminPotal.xpath.BatchesTabActions");
		WebUtil.wait(2000);
	}
	public void uploadEntityTemplate()
	{
		clickAtActionsButton();
		WebUtil.click("AdminPotal.xpath.BatchesTabUpload");
		WebUtil.click("AdminPotal.xpath.BatchesTabChoose");
		uploadDocument("Chrome");
		WebUtil.click("AdminPotal.xpath.BatchesTabSubmit");
	}
	
	public void uploadDocument(String browserName) 
	{
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			String path1=System.getProperty("user.dir");
			String path2="/src/testdata/BatchUploadEntityKYC.csv";
			String path3=path2.replace("/", "\\");
			String path=path1+path3;
			WebUtil.wait(1000);
			String[] autoit={"src/drivers/UploadDocForChrome.exe",path};
			try {
				Runtime.getRuntime().exec(autoit);
				WebUtil.wait(4000);
			} catch (IOException e) {
				e.printStackTrace();
			}

			WebUtil.wait(2000);
		}

		if(browserName.equalsIgnoreCase("FF"))
		{
			String[] b= {"src/drivers/UploadDocumentForFF.exe"};
			try {
				Runtime.getRuntime().exec(b);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			WebUtil.wait(20000);
		}
		if(browserName.equalsIgnoreCase("IE"))
		{
			String[] c= {"src/drivers/UploadDocumentForIE.exe"};
			try {
				Runtime.getRuntime().exec(c);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			WebUtil.wait(20000);
		}
		
	}
}
