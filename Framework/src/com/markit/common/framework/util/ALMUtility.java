package com.markit.common.framework.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ALMUtility {

	public static final Logger log = Logger.getLogger("appLogger");
	/** read Final Test Status Sheet
	 * @param sheetObj
	 * @param almTestCaseName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String readFinalTestStatus (Sheet sheetObj, String almTestCaseName) throws FileNotFoundException, IOException
	{
		Row fstRowObj=sheetObj.getRow(0);
		int failcounter= 0;
		int skipcounter=0;
		int passcounter=0;
		ArrayList<String> TestStatus = new ArrayList<String>();

		///get column number
		int statuscolno = ExcelUtil.getColumnNumberByColumnName("TestCaseStatus", fstRowObj);
		///get column no of almtest case col
		int almtestcaseno = ExcelUtil.getColumnNumberByColumnName("ALMTestCaseName",  fstRowObj);
		///get multiple row numbers with same AutoTestCaseName
		List<Integer> autotcrows = ExcelUtil.GetMultiRowNumber(sheetObj, almtestcaseno, almTestCaseName); 
		///Define the iterator 
		Iterator<Integer> rowIterator = autotcrows.iterator();

		while (rowIterator.hasNext()){
			Row statusrow= sheetObj.getRow(rowIterator.next());
			Cell statuscell=statusrow.getCell(statuscolno);
			if (statuscell!=null){
				String TestStatusVal= statuscell.getStringCellValue();
				TestStatus.add(TestStatusVal);
			}else{
				TestStatus.add("");
				log.info("null cell");
			}
		}       
		///iterate the Test status values defined
		Iterator<String> statusIterator = TestStatus.iterator();
		while (statusIterator.hasNext()){
			String statusVal = statusIterator.next();
			if (statusVal.equalsIgnoreCase("Fail")){
				failcounter = failcounter+1;				   
			}
			else if(statusVal.equalsIgnoreCase("No run") || statusVal.equals("")||(statusVal==null)){
				failcounter =0;
				skipcounter = skipcounter+1;
			}			   
			else if(statusVal.equalsIgnoreCase("Pass")){				   
				passcounter = passcounter+1;
			}
		}

		if (failcounter>0){
			String FinalTestStatus = "FAILED";
			return FinalTestStatus;
		}else if (skipcounter>0){
			String FinalTestStatus = "NO_RUN";
			return FinalTestStatus;
		} else if (passcounter>0) {
			String FinalTestStatus = "PASSED";
			return FinalTestStatus;
		} else return null;
	}



	/** Update Final status in sheet
	 * @param WorkbookPath
	 * @param testCaseMappingSheetName
	 * @param finalTestStatusSheetName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void SheetUpdatewithTestStatus(String WorkbookPath, String testCaseMappingSheetName, String finalTestStatusSheetName) throws FileNotFoundException, IOException{
		HashSet<String> hs = new HashSet<String>();
		HashMap<String, String> TestNameStatusMap = new HashMap<String, String>();
		//add elements to HashSet
		Workbook wbookObj=ExcelUtil.getWorkBookObject(WorkbookPath);
		Sheet mappingSheetObj=wbookObj.getSheet(testCaseMappingSheetName);
		Sheet finalStatShtObj =wbookObj.getSheet(finalTestStatusSheetName);
		Row fstRowObj=mappingSheetObj.getRow(0);

		///get column number
		int tccolno = ExcelUtil.getColumnNumberByColumnName("ALMTestCaseName", fstRowObj);
		for(int i=1; i<=mappingSheetObj.getLastRowNum();i++){
			Row rowObj=mappingSheetObj.getRow(i);
			Cell cellObj=rowObj.getCell(tccolno);
			String almtcname=cellObj.getStringCellValue();
			hs.add(almtcname);
		}
		Iterator<String> itr = hs.iterator(); 
		while(itr.hasNext()){ 
			String almTestCaseName= itr.next();
			log.info("TC Name"+almTestCaseName);
			String TestStatus=readFinalTestStatus(mappingSheetObj, almTestCaseName);
			log.info("TC Status"+TestStatus);
			TestNameStatusMap.put(almTestCaseName, TestStatus);
		} 	
		//iterate the hashmap to  set values in an excel
		Iterator<String> testCaseIterator = TestNameStatusMap.keySet().iterator();
		int rowIndex=1;
		while(testCaseIterator.hasNext()){
			Row row =finalStatShtObj.createRow(rowIndex++);
			String testNameKey=testCaseIterator.next();
			int testCellIndex =0;
			int statCellIndex =1;
			row.createCell(testCellIndex).setCellValue(testNameKey);
			String statusVal=TestNameStatusMap.get(testNameKey);
			row.createCell(statCellIndex).setCellValue(statusVal);
		}
		try {
			FileOutputStream fos = new FileOutputStream(WorkbookPath);
			wbookObj.write(fos);
			fos.close();
			log.info( " is successfully written");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error("file not found in ALM Utility"+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			log.error("IO Exception in ALM Utility"+e.getMessage());
		}
	}	



	/** Update status from method sheet to the Mapping Sheet
	 * @param methodWorkbookPath --Provide test workbook name containing methodteststatus sheet
	 * @param WorkbookPath -- Provide Mapping Excel name
	 * @param testCaseMappingSheetName
	 * @param finalTestStatusSheetName
	 * @param methodTestStatusSheetName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void methodSheetUpdatewithTestStatus(String methodWorkbookPath, String WorkbookPath, String testCaseMappingSheetName, String finalTestStatusSheetName, String methodTestStatusSheetName) throws FileNotFoundException, IOException{
		//Read the MethodTestStatus sheet and store the values in hashmap
		HashMap<String, String> MethodStatusMap = new HashMap<String, String>();

		Workbook wbookMethodObj=ExcelUtil.getWorkBookObject(methodWorkbookPath);
		Workbook wbookObj=ExcelUtil.getWorkBookObject(WorkbookPath);
		Sheet methodSheetObj=wbookMethodObj.getSheet(methodTestStatusSheetName);
		Sheet mappingSheetObj=wbookObj.getSheet(testCaseMappingSheetName);
		Row methodfstRowObj=methodSheetObj.getRow(0);

		///get column number
		int methodcolno = ExcelUtil.getColumnNumberByColumnName("MethodName", methodfstRowObj);
		int methodstatuscolno = ExcelUtil.getColumnNumberByColumnName("Status", methodfstRowObj);

		for(int i=1; i<=methodSheetObj.getLastRowNum();i++){
			Row rowObj=methodSheetObj.getRow(i);
			Cell methodCellObj=rowObj.getCell(methodcolno);
			String methodName=methodCellObj.getStringCellValue();
			Cell statCellObj=rowObj.getCell(methodstatuscolno);
			String methodStatus = statCellObj.getStringCellValue();
			MethodStatusMap.put(methodName, methodStatus);
		}
		//iterate the hashmap to  set values in an TestCaseMappingSheet
		Iterator<String> methodSheetIterator = MethodStatusMap.keySet().iterator();
		Row mappingsheetfstrowobj=mappingSheetObj.getRow(0);
		int autoTcColno = ExcelUtil.getColumnNumberByColumnName("AutomationTestCaseName", mappingsheetfstrowobj);
		int autoTstStatcolno = ExcelUtil.getColumnNumberByColumnName("TestCaseStatus", mappingsheetfstrowobj);
		int rowIndex=1;
		while(methodSheetIterator.hasNext()){
			String autotestNameKey=methodSheetIterator.next();
			String statusVal=MethodStatusMap.get(autotestNameKey);
			//Get all the row numbers which has the same auto test case
			List<Integer> autotcrows = ExcelUtil.GetMultiRowNumber(mappingSheetObj, autoTcColno, autotestNameKey);
			///Define the iterator 
			Iterator<Integer> rowIterator = autotcrows.iterator();
			while (rowIterator.hasNext()){
				int rowIteNext=rowIterator.next();
				Cell statuscell= mappingSheetObj.getRow(rowIteNext).createCell(autoTstStatcolno);
				if(statuscell!=null) {
					statuscell.setCellValue(statusVal);
				}
				else{
					log.info("null cell");
				}
				log.info(statuscell);
			}       

			try {
				FileOutputStream fos = new FileOutputStream(WorkbookPath);
				wbookObj.write(fos);
				fos.close();
				log.info( " is successfully written");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				log.error("file not found in ALM Utility"+e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				log.error("IO Exception in ALM Utility"+e.getMessage());
			}
		}
		SheetUpdatewithTestStatus(WorkbookPath, testCaseMappingSheetName, finalTestStatusSheetName);
	}


}

