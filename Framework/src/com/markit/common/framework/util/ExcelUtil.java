package com.markit.common.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {
	public static List<String> TestDataList;
	public static final Logger log = Logger.getLogger("appLogger");


	/** Returns the Workbook object
	 * @param filepath
	 * @return
	 */
	/**
	 * @param filepath
	 * @return
	 */
	public static Workbook getWorkBookObject (String filepath) 
	{

		FileInputStream fis=null;
		try {
			fis = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			log.error("FileInputStream object is not created");
			e.printStackTrace();
		}
		File file = new File(filepath);
		String fileName = file.getName();

		String[] arrpath= fileName.split("\\.");
		String ext=arrpath[1];
		Workbook wb=null;
		if(ext.equalsIgnoreCase("xlsx"))
		{
			try {
				wb=new XSSFWorkbook(fis);
			} catch (IOException e) {
				log.error("Workbook is not created");
				e.printStackTrace();
			}
		}
		else if(ext.equalsIgnoreCase("XLS"))
		{
			try {
				wb= new HSSFWorkbook(fis);
			} catch (IOException e) {
				log.error("Workbook is not created");
				e.printStackTrace();
			}
		}
		return wb;
	}

	/** Returns the data of a row as per passed test script id
	 * @param testDataPath
	 * @param SheetName
	 * @param refColumnName
	 * @param ScriptID
	 * @return
	 */
	public static List<String> fn_GetTestData(String testDataPath, String SheetName, String refColumnName, String ScriptID) {
		TestDataList=new ArrayList<String>();
		Workbook wbookObj=getWorkBookObject(testDataPath);
		Sheet sheetObj=wbookObj.getSheet(SheetName);

		Row fstRowObj=sheetObj.getRow(0);
		int scriptIDCOlumnNumber=getColumnNumberByColumnName(refColumnName, fstRowObj);
		int reqiredRowNumber=fn_GetTCRowNumber(sheetObj, scriptIDCOlumnNumber, ScriptID);
		Row reqRowObj=sheetObj.getRow(reqiredRowNumber);
		int cellCnt=reqRowObj.getLastCellNum();
		for(int i=0; i<=cellCnt-1;i++){
			Cell cellObj=reqRowObj.getCell(i, Row.CREATE_NULL_AS_BLANK);
			String cellvalue=fn_GetCellData(cellObj);
			TestDataList.add(cellvalue);
		}

		return TestDataList;
	}
/*	public static List<String> getHeaderOfExcel(Sheet sheetObj){
	
		List<String> header=new ArrayList<>();
		int lastcell=sheetObj.getRow(0).getLastCellNum();
		 for(int i=0;i<=lastcell;i++)
		  {
		      try
		      {
		          System.out.println(cell.getSheet().getRow(0).getCell(currentcellIndex)
		        		    .getRichStringCellValue().toString());
		      }catch(Exception e)
		      {}
		  }
		Row rowObj=sheetObj.getRow(0);
		for(int i=0; i<=rowObj.getLastCellNum()-1;i++){		
			Cell cellObj=rowObj.getCell(i,Row.CREATE_NULL_AS_BLANK);			
			String cellvalue=cellObj.getStringCellValue();
			header.add(cellvalue);
		}	
				
		return header;
	}*/
	
	
	public static Map<String,String> fn_GetKYCTestData(String testDataPath, String SheetName, String refColumnName, String ScriptID) {
		Map<String,String> map=new HashMap<String,String>();  
		TestDataList=new ArrayList<String>();
		List<String> headers=new ArrayList<String>();
		Workbook wbookObj=getWorkBookObject(testDataPath);
		Sheet sheetObj=wbookObj.getSheet(SheetName);
		headers=getHeaderOfExcel(sheetObj);
		Row fstRowObj=sheetObj.getRow(0);
		int scriptIDCOlumnNumber=getColumnNumberByColumnName(refColumnName, fstRowObj);
		int reqiredRowNumber=fn_GetTCRowNumber(sheetObj, scriptIDCOlumnNumber, ScriptID);
		Row reqRowObj=sheetObj.getRow(reqiredRowNumber);
		int cellCnt=reqRowObj.getLastCellNum();
		for(int i=0; i<=cellCnt-1;i++){
			Cell cellObj=reqRowObj.getCell(i, Row.CREATE_NULL_AS_BLANK);
			String cellvalue=fn_GetCellData(cellObj);
			TestDataList.add(cellvalue);
		}	
			for(int j=0;j<TestDataList.size();j++)
			map.put(headers.get(j), TestDataList.get(j)); 
		

		return map;
	}

	/**Returns cell value
	 * @param cellObj
	 * @return
	 */
	public static String fn_GetCellData(Cell cellObj){
		int cellTypeNo=cellObj.getCellType();
		String cellvalue="";
		if(cellTypeNo==Cell.CELL_TYPE_NUMERIC){
			Double dblcellvalue=cellObj.getNumericCellValue();
			Integer intcellValue=dblcellvalue.intValue();
			cellvalue=intcellValue.toString();
		}else if(cellTypeNo==Cell.CELL_TYPE_STRING){
			cellvalue=cellObj.getStringCellValue();
		}else if(cellTypeNo==Cell.CELL_TYPE_BLANK){
			cellvalue="";
		}
		return cellvalue;
	}

	/**Returns the row number as per passed script id
	 * @param sheetObj
	 * @param scriptIDCOlumnNumber
	 * @param ScriptID
	 * @return
	 */
	public static int fn_GetTCRowNumber(Sheet sheetObj, int scriptIDCOlumnNumber, String ScriptID){
		int reqiredRowNumber=0;
		for(int i=0; i<=sheetObj.getLastRowNum();i++){

			Row rowObj=sheetObj.getRow(i);
			Cell cellObj=rowObj.getCell(scriptIDCOlumnNumber);
			String xlScriptID=cellObj.getStringCellValue();
			if(xlScriptID.equalsIgnoreCase(ScriptID)){
				reqiredRowNumber=i;
				break;
			}
		}
		return reqiredRowNumber;
	}

	/** Returns column number as per passed column name 
	 * @param argColumnName
	 * @param fstRowObj
	 * @return
	 */
	public static int getColumnNumberByColumnName(String argColumnName, Row fstRowObj){
		int ColumnNumber=0;

		for(int i=0; i<=fstRowObj.getLastCellNum()-1;i++){
			Cell cellObj=fstRowObj.getCell(i, Row.CREATE_NULL_AS_BLANK);
			String xlcolumnName=cellObj.getStringCellValue();
			if(xlcolumnName.equalsIgnoreCase(argColumnName)){
				ColumnNumber=i;
				break;
			}
		}
		return ColumnNumber;

	}


	/**Returns the cell value exists next to the passed FieldName
	 * @param TestDataList
	 * @param FieldName
	 * @return
	 */
	public static String fn_FetchFieldValue(List<String> TestDataList, String FieldName){
		String expData="";
		for(int i=0; i<=TestDataList.size()-1;i++){
			if(TestDataList.get(i).trim().equalsIgnoreCase(FieldName)){
				expData=TestDataList.get(i+1);
				log.info("Expected Data value: "+expData);

			}
		}
		return expData;
	}
	
	/** Fetch all the field value which have similar field id
	 * @param TestDataList
	 * @param FieldName
	 * @return
	 */
	public static List<String> fn_FetchSimilarFieldValue(List<String> TestDataList, String FieldName){
		List<String> expData=new ArrayList<>();
		try{
		for(int i=0; i<=TestDataList.size()-1;i++){
			if(TestDataList.get(i).trim().contains(FieldName)){
				expData.add(TestDataList.get(i+1));
			}
			
		}
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
		return expData;
	}
	/**Get the row counts
	 * @param sheetObj
	 * @param colNumber
	 * @param CellVal
	 * @return
	 */
	public static List<Integer> GetMultiRowNumber(Sheet sheetObj, int colNumber, String CellVal){
		ArrayList<Integer> reqiredRowNumbers=new ArrayList<Integer>();
		int rownum = 0;
		int rowcnt =sheetObj.getLastRowNum();
		for(int i=1; i<=rowcnt;i++){
			Row rowObj=sheetObj.getRow(i);
			Cell cellObj=rowObj.getCell(colNumber);
			String xlScriptID=cellObj.getStringCellValue();
			if(xlScriptID.equalsIgnoreCase(CellVal)){
				rownum=i;
				reqiredRowNumbers.add(rownum);
			}
		}
		return reqiredRowNumbers;
	}
/*	public static Map<String,String> XpathValues;
	public static Map<String,String> getXpath(String path,String sheetname ) 
	{
		Cell cell;
		String data;
		Workbook wb = ExcelUtil.getWorkBookObject(path);
		//    Workbook wb= ReadExcel.getWorkBookObject(path);
		Sheet sheet = wb.getSheet(sheetname);
		int rowcount = sheet.getLastRowNum();
		XpathValues = new HashMap<String, String>();
		//MissingCellPolicy mcp=Row.CREATE_NULL_AS_BLANK;
		String Key = "";
		for(int i=0;i<=rowcount;i++)
		{
			Row row= sheet.getRow(i);          
			MissingCellPolicy mcp=Row.CREATE_NULL_AS_BLANK;
			cell= row.getCell(0,mcp);
			Key= cell.getStringCellValue();
			cell= row.getCell(1,mcp);
			data= cell.getStringCellValue();
			XpathValues.put(Key, data);

		}
		return XpathValues;
	}*/

	/**Returns the count of rows available on the excel sheet
	 * @param testDataPath
	 * @return
	 */
	public static int countOfTheRowsInSheet(String testDataPath,String SheetName) {

		Workbook wbookObj=getWorkBookObject(testDataPath);
		Sheet sheetObj=wbookObj.getSheet(SheetName);
		
		int  count=sheetObj.getLastRowNum();
		log.info("Count of the row in the sheet: "+count);
		return count;
	}

	/** Set cell value for bulk upload data
	 * @param rowNo
	 * @param colNo
	 * @param wb
	 * @param list
	 */
	public static void setCellValueBulkUpload(int rowNo,int colNo, Workbook wb,ArrayList<String> list){

		for(int i=0;i<=list.size()-1;i++){
			Row createRow=wb.getSheet("Sheet1").getRow(++rowNo);
			//Row createRow=wb.getSheet("Sheet1").createRow(++rowNo);
			Cell cell=createRow.createCell(colNo);
			cell.setCellValue(list.get(i));
		}
		log.info("Values are set for bulk upload");
	}

/*	public static void setCellValueBulkUpload(int rowNo,int colNo, Workbook wb,ArrayList<String> list,int fixSize){

		for(int i=0;i<=fixSize-1;i++){
			Row createRow=wb.getSheet("Sheet1").getRow(++rowNo);
			//Row createRow=wb.getSheet("Sheet1").createRow(++rowNo);
			Cell cell=createRow.createCell(colNo);
			cell.setCellValue(list.get(i));
		}

	}*/
	/** Save written data to excel
	 * @param filePath
	 * @param wb
	 */
	public static void writeExcel(String filePath,Workbook wb){

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filePath);
			wb.write(fos);
			fos.close();
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Excel Writing Exception" +e.getMessage());
		} 

	}

	/**Reads all the content of the Excel as per passed sheet name
	 * @param testDataPath
	 * @param SheetName
	 * @return
	 */
	public static List<String> readExcelData(String testDataPath,String SheetName)
	{
		Workbook wbookObj=null;				 
		wbookObj=getWorkBookObject(testDataPath);				 
		Sheet sheetObj=wbookObj.getSheet(SheetName);
		List<String> columndata = new ArrayList<>();
		Iterator<Row> rowIterator = sheetObj.iterator();

		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();			             
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();			                 
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					columndata.add(cell.getStringCellValue());
					//System.out.print(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					columndata.add(cell.getBooleanCellValue()+ "");
					//System.out.print(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_BLANK:
					break;
				case Cell.CELL_TYPE_NUMERIC:		                   
					columndata.add(cell.getNumericCellValue() + "");
					// System.out.print(cell.getNumericCellValue());
					break;			                 		                    
				}
			}
		}
		log.info("All the content of the excel has been read");
		return columndata;
	}

	/**Verifies the expected value exits or not
	 * @param dataList
	 * @param expectedValue
	 * @return
	 */
	public static String isValueExist(List<String> dataList,String expectedValue)
	{ 	 int i = 0;
	String valueMatch=null;
	while (i < dataList.size()) {
		if(dataList.get(i).toString().equalsIgnoreCase(expectedValue))
		{ 
		valueMatch=dataList.get(i).toString();
		log.info("Expected value exists in excel"+dataList.get(i).toString());
		break;
		}				     
		i++;
	}		     
	return valueMatch; 
	}



	/**Returns the passed Column's content
	 * @param testDataPath
	 * @param SheetName
	 * @param refColumnName
	 * @return
	 */
	public static List<String> getColumnDataAsPerPassedName(String testDataPath, String SheetName, String refColumnName) 
	{
		Workbook wbookObj=getWorkBookObject(testDataPath);
		Sheet sheetObj=wbookObj.getSheet(SheetName);					
		Integer columnNo = null;				  
		List<String> cells = new ArrayList<String>();

		Row r = sheetObj.getRow(0);
		columnNo=getColumnNumberByColumnName(refColumnName, r);
		
		if (columnNo!=null)
		{ for (Row row : sheetObj)
		{ Cell c = row.getCell(columnNo);
		if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK)
		{ 
		} else if(c.getCellType()==Cell.CELL_TYPE_STRING){ 
		String value=c.getStringCellValue().toString();
		cells.add(value);
		}
		else if(c.getCellType()==Cell.CELL_TYPE_NUMERIC){
			Double dblcellvalue=c.getNumericCellValue();
			Integer intcellValue=dblcellvalue.intValue();
			String value=intcellValue.toString();
			cells.add(value);
		}
		}				
		}
		log.info("The data from "+refColumnName+" column has been read");
		return cells;			 
	}



/** get Col Data by colName
 * @param wbPath
 * @param SheetName
 * @param refColumnName
 * @param rowmNum
 * @return
 */
public static String getColumnDataByColName(String wbPath, String SheetName, String refColumnName,int rowmNum) 
{
	Workbook wbookObj=getWorkBookObject(wbPath);
	Sheet sheetObj=wbookObj.getSheet(SheetName);					
	Integer columnNo = null;		
	String value=null;

	Row row = sheetObj.getRow(0);
	columnNo=getColumnNumberByColumnName(refColumnName, row);
	
	if (columnNo!=null){
		 row = sheetObj.getRow(rowmNum);
		Cell c=row.getCell(columnNo);
		if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK){ 
			value="";
		}
		else if(c.getCellType()==Cell.CELL_TYPE_NUMERIC){
			Double dblcellvalue=c.getNumericCellValue();
			Integer intcellValue=dblcellvalue.intValue();
			value=intcellValue.toString();
		}else{
		 value=c.getStringCellValue();
		}
	}
	return value;
}		
		

/** set Col Data by colName
 * @param wbPath
 * @param SheetName
 * @param refColumnName
 * @param rowmNum
 * @param setValue -- the value that needs to be set
 * @return
 */
public static void setColumnDataByColName(String wbPath, String SheetName, String refColumnName,int rowmNum, String setValue) 
{
	Workbook wbookObj=getWorkBookObject(wbPath);
	Sheet sheetObj=wbookObj.getSheet(SheetName);					
	Integer columnNo = null;		

	Row row = sheetObj.getRow(0);
	columnNo=getColumnNumberByColumnName(refColumnName, row);
	
	if (columnNo!=null){
		 row = sheetObj.getRow(rowmNum);
		Cell c=row.getCell(columnNo);
/*		if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK){ */
		c = row.createCell(columnNo, Cell.CELL_TYPE_NUMERIC);
		c.setCellValue(setValue);
		}

	//}
	writeExcel(wbPath, wbookObj);

}	

/**removeAllRows excluding first row of sheet
 * @param wbPath
 * @param SheetName
 */
public static void removeAllRowsExceptHeader(String wbPath, String SheetName) 
{
	Workbook wbookObj=ExcelUtil.getWorkBookObject(wbPath);
	Sheet sheetObj=wbookObj.getSheet(SheetName);					
	int rowCnt=sheetObj.getLastRowNum();	
	for (int i=0;i<=rowCnt;i++){
		if(i==0){}else{
		Row row=sheetObj.getRow(i);
        sheetObj.removeRow(row);	
		}
	}
	ExcelUtil.writeExcel(wbPath, wbookObj);
	}   

/**Get the data of entire row by providing row number
 * @param testDataPath
 * @param SheetName
 * @param rowNumber
 * @return
 */
public static Map<String,String> getRowDataAndMapWithColName(String testDataPath, String SheetName, int rowNumber) {
	Map<String,String> map=new HashMap<String,String>();  
	TestDataList=new ArrayList<String>();
	List<String> headers=new ArrayList<String>();	
	Workbook wbookObj=getWorkBookObject(testDataPath);
	Sheet sheetObj=wbookObj.getSheet(SheetName);
	headers=getHeaderOfExcel(sheetObj);	
	Row reqRowObj=sheetObj.getRow(rowNumber);
	int cellCnt=reqRowObj.getLastCellNum();
		
	for(int i=0; i<=cellCnt-1;i++){
		Cell cellObj=reqRowObj.getCell(i, Row.CREATE_NULL_AS_BLANK);
		String cellvalue=fn_GetCellData(cellObj);
		TestDataList.add(cellvalue);
	}	
		for(int j=0;j<TestDataList.size();j++)
		map.put(headers.get(j), TestDataList.get(j)); 
	return map;
}

/** Get column headers from Excel (top row values)
 * @param sheetObj
 * @return
 */
public static List<String> getHeaderOfExcel(Sheet sheetObj){
	
	List<String> header=new ArrayList<>();
	Row rowObj=sheetObj.getRow(0);
	for(int i=0; i<=rowObj.getLastCellNum()-1;i++){		
		Cell cellObj=rowObj.getCell(i,Row.CREATE_NULL_AS_BLANK);			
		String cellvalue=cellObj.getStringCellValue();
		header.add(cellvalue);
	}				
	return header;
}

/**get row number by passing col number and its value
 * @param sheetObj
 * @param colNumber
 * @param CellVal
 * @return
 */
public static int getRowNumberByColName(Sheet sheetObj, int colNumber, String CellVal){
	int rownum = 0;
	int rowcnt =sheetObj.getLastRowNum();
	for(int i=1; i<=rowcnt;i++){
		Row rowObj=sheetObj.getRow(i);
		Cell cellObj=rowObj.getCell(colNumber);
		String xlScriptID=cellObj.getStringCellValue();
		if(xlScriptID.equalsIgnoreCase(CellVal)){
			rownum=i;
		}
	}
	return rownum;
}

}