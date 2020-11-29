package lotto_project;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFunction {
	public HashMap<Integer, ArrayList<Integer>> lotto_excel_xls(int cnt) {
		HashMap<Integer, ArrayList<Integer>> resultMap = new HashMap<>();
		String fileUrl = "D:/workspace_lotto/lottoData/lotto.xls";
		
		try{
			FileInputStream file = new FileInputStream(fileUrl);
			HSSFWorkbook workbook = new HSSFWorkbook(file);
            
            int rowindex=0;
            int columnindex=0;
            
            HSSFSheet sheet = workbook.getSheetAt(0);
            
            int rows = sheet.getPhysicalNumberOfRows();
            ArrayList<Integer> temp = new ArrayList<>();
            for(rowindex = 0; rowindex < rows; rowindex++) {
            	HSSFRow row = sheet.getRow(rowindex);
            	if(row != null) {
            		int cells = row.getPhysicalNumberOfCells();
            		for(columnindex = 0; columnindex < cells; columnindex++) {
            			HSSFCell cell = row.getCell(columnindex);
            			cells = (cnt == 6) ? 6 : cells;
            			int value = 0; 
        				if(cell == null) {
        					continue;
        				} else {
        					value = (int) cell.getNumericCellValue();
        					temp.add(value);
        				}
            		}
            		resultMap.put(rowindex, temp);
            	}
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	public HashMap<Integer, ArrayList<Integer>> lotto_excel_xlsx(int cnt) {
		HashMap<Integer, ArrayList<Integer>> resultMap = new HashMap<>();
		String fileUrl = "D:/workspace_lotto/lottoData/lotto.xlsx";
		
		try{
			FileInputStream file = new FileInputStream(fileUrl);

			if(file == null) {
				file = new FileInputStream("./src/lotto_project/resource/lotto.xlsx");
			}
			
			XSSFWorkbook workbook = new XSSFWorkbook(file);
            
            int rowindex=0;
            int columnindex=0;
            
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            int rows = sheet.getPhysicalNumberOfRows();
            ArrayList<Integer> temp = new ArrayList<>();
            for(rowindex = 0; rowindex < rows; rowindex++) {
            	XSSFRow row = sheet.getRow(rowindex);
            	if(row != null) {
            		temp = new ArrayList<>();
            		int cells = row.getPhysicalNumberOfCells();
            		cells = (cnt == 6) ? 6 : cells;
            		for(columnindex = 0; columnindex < cells; columnindex++) {
            			XSSFCell cell = row.getCell(columnindex);
            			int value = 0; 
        				if(cell == null) {
        					continue;
        				} else {
        					value = (int) cell.getNumericCellValue();
        					temp.add(value);
        				}
            		}
            		resultMap.put(rowindex, temp);
            	}
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
}
