package utli;

import java.io.File;
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

    public static List<ModelStudent> getAllByDb(){
        List<ModelStudent> list = new ArrayList<ModelStudent>();
        /*try{
            SQLiteAccess sqlite = new SQLiteAccess();
            String sql = "select * from student";
            ResultSet rs = sqlite.Search(sql, null);
        }*/
    }
    public static List<ModelStudent> getAllByExcel(){

    }
}