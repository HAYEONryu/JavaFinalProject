package edu.handong.csee.java.utils;

import java.util.*;
import java.io.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelReader {
	public ArrayList<String> getData(InputStream is, String name) {

		ArrayList<String> allvalues = new ArrayList<String>();
		String rowline = name + ",";

		try {

			XSSFWorkbook workbook = new XSSFWorkbook(is);

			int rowindex = 0;
			int columnindex = 0;
			// 시트 수 (첫번째에만 존재하므로 0을 준다)
			// 만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
			XSSFSheet sheet = workbook.getSheetAt(0);
			// 행의 수
			int rows = sheet.getPhysicalNumberOfRows();
			for (rowindex = 0; rowindex < rows; rowindex++) {
				// 행을읽는다
				XSSFRow row = sheet.getRow(rowindex);
				if (row != null) {
					// 셀의 수
					int cells = row.getPhysicalNumberOfCells();
					for (columnindex = 0; columnindex <= cells; columnindex++) {
						// 셀값을 읽는다
						XSSFCell cell = row.getCell(columnindex);
						String value = "";
						// 셀이 빈값일경우를 위한 널체크
						if (cell == null) {
							continue;
						} else {
							// 타입별로 내용 읽기
							switch (cell.getCellType()) {

							case NUMERIC:
								value = cell.getNumericCellValue() + "";
								rowline = rowline + value + ",";
								break;
							case STRING:
								value = cell.getStringCellValue() + "";
								rowline = rowline + "\"" + value + "\"" + ",";
								break;
							case BLANK:
								value = cell.getBooleanCellValue() + "";
								rowline = rowline + value + ",";
								break;
							default:
								break;
							}
						}
					}
				}
				allvalues.add(rowline);
				rowline = "";
			}
			allvalues.add(0, Integer.toString(columnindex));

			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return allvalues;

	}

}
