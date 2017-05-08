package utli;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import model.ModelStudent;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * Created by 子楹 on 2017/5/8.
 */
public class ReadExcel {

    public static void readExcel(String filepath) throws IOException{
        DecimalFormat df = new DecimalFormat("#");
        ArrayList<ModelStudent> student = new ArrayList<ModelStudent>();

        InputStream is = new FileInputStream(filepath);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);

        for(int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++){
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            for(int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++){
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                String test = getValue(xssfRow.getCell(0));

                if(xssfRow != null && test.equals("")){
                    String s_id = getValue(xssfRow.getCell(0));
                    String name = xssfRow.getCell(1).getStringCellValue();
                    String mobile = getValue(xssfRow.getCell(2));
                    String project_name = xssfRow.getCell(2).getStringCellValue();

                    double phonenumer_tmp = Double.parseDouble(mobile);
                    mobile = df.format(phonenumer_tmp);

                    System.out.println("s_id:"+ s_id +"name:"+ name +"mobile:"+ mobile +"project_name:"+ project_name +"\n");
                    student.add(new ModelStudent(Integer.parseInt(s_id), name, Integer.parseInt(mobile),project_name));
                }
            }
        }
        JDBC.InsertStudent(student);
    }

    @SuppressWarnings("deprecation")
    private static String getValue(XSSFCell xssfcell){
        if (xssfcell.getCellType() == xssfcell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfcell.getBooleanCellValue());
        } else if (xssfcell.getCellType() == xssfcell.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfcell.getNumericCellValue());
        } else {
            return String.valueOf(xssfcell.getStringCellValue());
        }
    }
}
