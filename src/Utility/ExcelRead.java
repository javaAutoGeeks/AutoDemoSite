package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.text.BadLocationException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {

	private static XSSFSheet sh =null ;
	 
	private static XSSFWorkbook wb = null ;

	private static XSSFCell Cell =  null;

	private static XSSFRow Row = null;
	
	public static Object[][] getExcelData(String FilePath, String SheetName) throws Exception {   


		String[][] tabArray = null;

		try {

			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Access the required test data sheet

			 wb = new XSSFWorkbook(ExcelFile);

			 sh = wb.getSheet(SheetName);

			int startRow = 1;

			int startCol = 0;

			int ci,cj;

			int totalRows = sh.getLastRowNum();
			int totalCols = sh.getRow(0).getLastCellNum();
			
			System.out.println("Last Row - "+totalRows);
			System.out.println("Last Col - "+totalCols);

			tabArray=new String[totalRows][totalCols];

			ci=0;

			for (int i=startRow;i<=totalRows;i++, ci++) {           	   

				cj=0;
				int totalCols1 = sh.getRow(i).getLastCellNum();
				//System.out.println("Last Col of "+i+" row = "+totalCols1 );
				for (int j=startCol;j<=totalCols1-1;j++, cj++){

					tabArray[ci][cj]=getCellData(i,j);

					System.out.print(tabArray[ci][cj]+" | ");  

				}
				
				System.out.println();

			}

		}

		catch (FileNotFoundException e){

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		catch (IOException e){

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		return(tabArray);

	}


	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try{

			Cell = sh.getRow(RowNum).getCell(ColNum);

			int dataType = Cell.getCellType();

			if  (dataType == 3) {

				return "";

			}else{

				String CellData = Cell.getStringCellValue();

				return CellData;

			}	

		}
		
		catch (Exception e){

			System.out.println(e.getMessage());

			throw (e);

		}

	}



}
