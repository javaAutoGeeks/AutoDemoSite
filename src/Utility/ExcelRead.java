package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.text.BadLocationException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
	
	public static String[][] getExcelData(String fileName, String sheetName) {
		
	String[][] arrayExcelData = null;
	
	try {
		FileInputStream fis = new FileInputStream(fileName);
		
			
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		
		int rowCount = sheet.getLastRowNum();
		System.out.println("Total no. of Rows ="+rowCount);
		
//		int colNum = sheet.getColumn;
		
		for(int i=0;i<rowCount;i++) {
			
			
			
		}
		
		
		String CellData = sheet.getRow(1).getCell(1).getStringCellValue();
		
		System.out.println("Cell Data "+CellData);
		
		
		
		wb.close();
		}
	
	catch(FileNotFoundException e) {
		
		System.out.println();
		e.printStackTrace();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
	
	return arrayExcelData;
	
		}
	
	
	public String getData(String sheetNumber,int row,int col) {
		
		
		
		
		return null;
	}

}
