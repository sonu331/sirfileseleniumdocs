package seleniumplus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcelUtility {

	public static Object[][] readExcel(String sheetname)
	{
		Object[][] obj=null;
		try {
			InputStream is=new FileInputStream("src/test/resources/TestData/Book1.xlsx");
			XSSFWorkbook workbook=new XSSFWorkbook(is);
			XSSFSheet sheet1=workbook.getSheet(sheetname);
			obj=new Object[sheet1.getLastRowNum()][];
			
			for(int i=1;i<=sheet1.getLastRowNum();i++)
			{
				obj[i-1]=new Object[sheet1.getRow(i).getPhysicalNumberOfCells()];
				for(int j=0;j<sheet1.getRow(i).getPhysicalNumberOfCells();j++)
				{
				System.out.print(sheet1.getRow(i).getCell(j).getStringCellValue()+"\t");
				obj[i-1][j]=sheet1.getRow(i).getCell(j).getStringCellValue();
				}
				System.out.println("");
			}
			workbook.close();		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

}
