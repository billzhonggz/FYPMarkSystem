package utli;

import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import utli.SQLiteAccess;
import model.ModelStudent;


public class ReadWriteExcel {

    private static SQLiteAccess SQLiteAccess;
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

//    public static List<ModelStudent> getAllByDb(){
//        List<ModelStudent> list = new ArrayList<ModelStudent>();
//        try{
//            SQLiteAccess sqlite = new SQLiteAccess();
//            String sql = "select * from student";
//            ResultSet rs = sqlite.Search(sql, null);
//        }
//    }

    public static List<ModelStudent> getAllByExcel(){
        List<ModelStudent> list = new ArrayList<ModelStudent>();
        try{
            Workbook wb = Workbook.getWorkbook(new FileInputStream(EXCEL_XLSX));
            Sheet sh = wb.getSheet("Test sheet 1");
            int clos =  sh.getColumns();
            int rows = sh.getRows();
            System.out.println(clos + "rows:" + rows);
            for(int i = 1; i < rows; i++){
                for(int j = 0; j < clos; j++){
                    String s_id = sh.getCell(j++,i).getContents();
                    String name = sh.getCell(j++,i).getContents();
                    String mobile = sh.getCell(j++,i).getContents();
                    String group_id = sh.getCell(j++,i).getContents();
                    System.out.println("s_id:" +s_id+"name:"+name+"mobile:"+mobile+"group_id"+group_id);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}