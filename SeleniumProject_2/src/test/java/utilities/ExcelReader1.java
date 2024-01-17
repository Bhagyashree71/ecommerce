package utilities;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.HashMap;

public class ExcelReader1 {
    private static HashMap<String, HashMap<String, String>> testData;

    public static void init() {
        if (testData == null) {
            try {
                testData=new HashMap<>();
                FileInputStream fileInputStream = new FileInputStream("src/test/resources/exceldata2.xlsx");
                XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
                XSSFSheet sheet = workbook.getSheet("Sheet1");

                int rowCount = sheet.getLastRowNum();
                for (int i = 1; i <= rowCount; i++) {
                    XSSFRow row = sheet.getRow(i);
                    XSSFRow header = sheet.getRow(0);
                    int colCount = row.getLastCellNum();
                    HashMap<String, String> rowData = new HashMap<>();
                    for (int j = 1; j < colCount; j++) {
                        rowData.put(header.getCell(j).toString(), row.getCell(j).toString());
                    }
                    testData.put(row.getCell(0).toString(), rowData);

                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static HashMap<String, String> getTestData(String testCase) {
        init();
        return testData.get(testCase);
    }

}




