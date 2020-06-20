package weekendstatsautomationreports.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWrite {


	public void writeToExcel(String sheetName , LinkedHashMap<String, String> values) throws Exception {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(sheetName);

		int total_values = values.size();
		System.out.println("total_values> "+total_values);
		System.out.println("Hi");

		//Creating Two Rows
		Row row1 = sheet.createRow(0);
		Row row2 = sheet.createRow(1);


		for(int i = 0; i < total_values; i++) 
		{
			//Adding the column values to Row_1 
			Cell cell = row1.createCell(i);
			System.out.println(i+"--> "+values.keySet().toArray()[i]);
			cell.setCellValue((String) values.keySet().toArray()[i]);

			//Adding the column values to Row_2
			Cell cell2 = row2.createCell(i);
			System.out.println(i+"--> "+values.get(values.keySet().toArray()[i]));
			cell2.setCellValue((String) values.get(values.keySet().toArray()[i]));
		}

		FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.dir")+"/reports/"+sheetName+".xlsx");
		workbook.write(outputStream);

	}   /* End of WritetoExcel Method */
}
