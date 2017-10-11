package com.markit.demo;



import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.markit.common.framework.util.ExcelUtil;

public class ExcelJiraUtil {

	public static void main(String[] args){
		
		excelUpdate();
		
	
	}
	
	public  static void excelRead(String actualKey, int actualKeyRowNo){
		String excelPath="src/com/markit/demo/JIRAExport.xlsx";
		Workbook wbObj=ExcelUtil.getWorkBookObject(excelPath);
	    Sheet sheetObj=wbObj.getSheet("JIRA_Export_KYC");
	    boolean flag = false;
	    Map<String,String> keyValueExpected=new HashMap<String,String> ();
	    
	    
	    
	    for(int i=1;i<sheetObj.getLastRowNum();i++){
	    if ((sheetObj.getRow(i)) != null) {
	    keyValueExpected=ExcelUtil.getRowDataAndMapWithColName(excelPath, "JIRA_Export_KYC", i);
		if(keyValueExpected.get("Key").equals(actualKey)){
			sheetObj=wbObj.getSheet("Tasks_KYC");   			
			ExcelUtil.setColumnDataByColName(excelPath, "Tasks_KYC", "Status", actualKeyRowNo, keyValueExpected.get("Status"));
		    flag=true;
		}else{
		  System.out.println("abc");
		}
	   }
	    }
	    
	    if(flag){
	    }else{
	    	sheetObj=wbObj.getSheet("JIRA_Export_KYC");
	    	ExcelUtil.setColumnDataByColName(excelPath, "Tasks_KYC", "JIRA_ID", sheetObj.getLastRowNum(), keyValueExpected.get("Key"));
	    	
	    }
	}
	
	public static void excelUpdate(){
		  String excelPath="src/com/markit/demo/JIRA.xlsx";
		  Workbook wbObj=ExcelUtil.getWorkBookObject(excelPath);
	      Sheet sheetObj=wbObj.getSheet("Tasks_KYC");  
	      Row fstRowObj=sheetObj.getRow(0);      	      
	      int colNumber=ExcelUtil.getColumnNumberByColumnName("JIRA_ID", fstRowObj);
	      for(int i=1;i<sheetObj.getLastRowNum();i++){
	      String actualKey= ExcelUtil.getColumnDataByColName(excelPath, "Tasks_KYC", "JIRA_ID", i);
	      excelRead(actualKey, i);      
	      }
	      
	}
}
