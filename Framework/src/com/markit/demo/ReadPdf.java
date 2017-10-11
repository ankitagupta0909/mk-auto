package com.markit.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFColor;

public class ReadPdf {

	public static void main(String[] args) throws InvalidFormatException, IOException {
	
		FileInputStream f=new FileInputStream("src/com/markit/demo/test.xls");
		
		
		Workbook wb = WorkbookFactory.create(f);
	
		Sheet s = wb.getSheetAt(0);
		CellStyle styleobj=wb.createCellStyle();
		CellStyle styleobjStringCell=wb.createCellStyle();
		Font fobj=wb.createFont();
		
		for (int i=0;i<=s.getLastRowNum();i++){
		Row r=s.getRow(i);
		
		try{
		for (int j=0;j<r.getLastCellNum();j++){

		Cell cobj=r.getCell(j);		
		if (cobj.getCellType()==Cell.CELL_TYPE_STRING){
		System.out.println(cobj.getStringCellValue());
		fobj.setBold(true);
		styleobjStringCell.setFont(fobj);
		cobj.setCellStyle(styleobjStringCell);
		
		}
		else if(cobj.getCellType()==Cell.CELL_TYPE_NUMERIC){
		System.out.println(cobj.getNumericCellValue());		
		styleobj.setFillForegroundColor(HSSFColor.ORANGE.index); 
		styleobj.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cobj.setCellStyle(styleobj);
		}		
		}	
		
		}catch(Exception e){
			System.out.println("exceptio"+e);
		}
		
	}
		FileOutputStream fileout=new FileOutputStream("src/com/markit/demo/test.xls");
		wb.write(fileout);
		wb.close();
	}
}
