package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	 XSSFWorkbook wb;

public ExcelFileUtil(String Excelpath) throws Throwable
{
	FileInputStream fi=new FileInputStream(Excelpath);
			wb=new XSSFWorkbook(fi);
			
}
//method counting no of rows
public int rowCount(String sheet)
{
	return wb.getSheet(sheet).getLastRowNum();
	
}

//method for reading celldata

public String getCellData(String sheetname,int row,int column)
{
	String data="  ";
	if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
	{
		int celldata=(int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
		data=String.valueOf(celldata);
		
	}
	else
	{
		data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		
	}

	return data;

}
//method for writing data
public void setCellData(String sheet,int row,int col,String status,String writeExcel) throws Throwable
{
	XSSFSheet ws=wb.getSheet(sheet);
	XSSFRow rownum=ws.getRow(row);
    XSSFCell cell=rownum.createCell(col);
    cell.setCellValue(status);
	if(status.equalsIgnoreCase("PASS"))
	{
		XSSFCellStyle style=wb.createCellStyle();
		XSSFFont font=wb.createFont();
		
		
		font.setColor(IndexedColors.GREEN.getIndex());
		style.setFont(font);
		font.setBold(true);
		rownum.getCell(col).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("FAIL"))
	{
		XSSFCellStyle style=wb.createCellStyle();
		XSSFFont font=wb.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		style.setFont(font);
		font.setBold(true);
		rownum.getCell(col).setCellStyle(style);
	}
	
	else if(status.equalsIgnoreCase("BLOCKED"))
	{
		XSSFCellStyle style=wb.createCellStyle();
		XSSFFont font=wb.createFont();
		font.setColor(IndexedColors.BLUE.getIndex());
		style.setFont(font);
		font.setBold(true);
		rownum.getCell(col).setCellStyle(style);
	}
	
	FileOutputStream fo=new FileOutputStream(writeExcel);
	wb.write(fo);
	
	}
public static void main(String[] args) throws Throwable
{
	ExcelFileUtil xl=new ExcelFileUtil("E:/samplepjct.xlsx");
	int rc=xl.rowCount("Emp1");
	System.out.println(rc);
	for(int i=1;i<rc;i++)
	{
		String fname=xl.getCellData("Emp1", i, 0);
		String mname=xl.getCellData("Emp1", i, 1);
		String lname=xl.getCellData("Emp1", i, 2);
		String eid=xl.getCellData("Emp1", i, 3);
		System.out.println(fname+ "   "+mname+ "  "+lname+ "   "+eid );
		xl.setCellData("Emp1", i, 4, "FAIL", "E:/Results1.xlsx");
		
	}
	
}



}


	 
		
		
	
	


