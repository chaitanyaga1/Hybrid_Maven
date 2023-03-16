package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excelfileproperty {
Workbook wb;
	
public Excelfileproperty(String inputpath) throws Throwable {

	FileInputStream fi=new FileInputStream(inputpath);
	wb=WorkbookFactory.create(fi);
}
public int getRow(String sheetname) {

	return wb.getSheet(sheetname).getLastRowNum();
}
public String getCellData(String sheetname,int rows,int cells) {
	
	String data="";
	if(wb.getSheet(sheetname).getRow(rows).getCell(cells).getCellType()==Cell.CELL_TYPE_NUMERIC) {
		int datatype=(int) wb.getSheet(sheetname).getRow(rows).getCell(cells).getNumericCellValue();
	 data=String.valueOf(datatype);
	}else {
	data=wb.getSheet(sheetname).getRow(rows).getCell(cells).getStringCellValue();	
	}
	return data;
     
}
public void setCellData(String sheetname,int rows, int cells, String status,String writeexcel) throws Throwable {
	
	Sheet sh=wb.getSheet(sheetname);
     Row rw=sh.getRow(rows);
    Cell cels=rw.createCell(cells);
    cels.setCellValue(status);
    if(status.equalsIgnoreCase("Pass")) {
    	
    	CellStyle style=wb.createCellStyle();
    	Font font=wb.createFont();
    	font.setColor(IndexedColors.GREEN.getIndex());
        font.setBold(true);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);
        rw.getCell(cells).setCellStyle(style);
    
    }else if(status.equalsIgnoreCase("Fail")) {
    	
    	CellStyle style=wb.createCellStyle();
    	Font font=wb.createFont();
    	font.setColor(IndexedColors.BLUE.getIndex());
        font.setBold(true);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);
        rw.getCell(cells).setCellStyle(style);
}else if(status.equalsIgnoreCase("Blocked")) {
	
	CellStyle style=wb.createCellStyle();
	Font font=wb.createFont();
	font.setColor(IndexedColors.GREEN.getIndex());
    font.setBold(true);
    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    style.setFont(font);
    rw.getCell(cells).setCellStyle(style);
}
 FileOutputStream fo=new FileOutputStream(writeexcel);
 wb.write(fo);
}
public static void main(String []args) throws Throwable {
	Excelfileproperty xl=new Excelfileproperty("D:/Sample.xlsx"); 
	  int rows= xl.getRow("EmpData");
		System.out.println("No of Rows Count"+rows);
		for(int i=1;i<=rows;i++) {
	  String fname= xl.getCellData("EmpData", i, 0);
		String sname=xl.getCellData("EmpData", i, 1);
	 String sn=xl.getCellData("EmpData", i, 2);
	 String snl=xl.getCellData("EmpData", i, 3);	
	 System.out.println(fname+" "+sname+"  "+sn+"  "+snl);
    }


}

}