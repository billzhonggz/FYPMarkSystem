package utli;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;


/**
 * Created by ZHONG on 2017/5/13.
 */
public class ReadExcel {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 判断Excel的版本,获取Workbook
     */
    public static Workbook getWorkbok(InputStream in, File file) throws IOException {
        Workbook wb = null;
        if (file.getName().endsWith(EXCEL_XLS)) { // Excel 2003
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) { // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     * 判断文件是否是excel
     */
    public static void checkExcelVaild(File file) throws Exception {
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        if (!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))) {
            throw new Exception("文件不是Excel");
        }
    }

    /**
     * 读取Excel测试，兼容 Excel 2003/2007/2010
     *
     * @throws FileNotFoundException
     * @throws Exception
     */

    @SuppressWarnings("finally")
    public static JSONArray readExcel(String path, String name, int group_id) throws Exception {
        group_id = group_id;
        //String sqlfile =path+ "//" + name.substring(0,name.lastIndexOf(".")) +".txt";
        //System.out.println(sqlfile);
        //BufferedWriter bw = new BufferedWriter(new FileWriter(new File(sqlfile)));
        String filename = path + "//" + name;
        JSONArray array =null;
        try {
            // 同时支持Excel 2003、2007
            File excelFile = new File(filename); // 创建文件对象
            FileInputStream is = new FileInputStream(excelFile); // 文件流
            checkExcelVaild(excelFile);
            Workbook workbook = getWorkbok(is, excelFile);
            // Workbook workbook = WorkbookFactory.create(is); // 这种方式
            // Excel2003/2007/2010都是可以处理的
            int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
            /**
             * 设置当前excel中sheet的下标：0开始
             */
            Sheet sheet = workbook.getSheetAt(0); // 遍历第一个Sheet
            Row row;
            Cell cell1;
            int rows = sheet.getLastRowNum();
            // json数组
            array = new JSONArray();
            //用于存储excel表，第一行字段内容
            String[] tag = new String[20];
            int tagNum =0;
            JSONObject jsonObj = null;
            System.out.println(rows);
            for (int icount = 0; icount <= rows; icount++) {
                jsonObj = new JSONObject();
                row = sheet.getRow(icount);
                int line = row.getPhysicalNumberOfCells();
                for (int j = 0; j < line; j++) {
                    cell1 = row.getCell(j);
                    if (icount != 0 && j % 2 == 0)
                        // Set number to string.
                        cell1.setCellType(CellType.STRING);
                    if (icount == 0) {
                        tagNum = line;
                        tag[j] = cell1.toString();
                    } else {
                        jsonObj.put(tag[j], cell1);
                    }
                }
                if (icount != 0) {
                    array.put(jsonObj);
                }
            }
            String tableName="student";
            writeSql(tag,array,tagNum,tableName, group_id);
            //bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //bw.close();
            return array;
        }
    }

    /**
     *
     * @Title: writeSql
     * @Description: TODO
     * @param @param tag
     * @param @param array
     * @param @param bw
     * @param @param tagNum
     * @param @param tableName    参数
     * @return void    返回类型
     * @throws
     */
    private static String writeSql(String[] tag, JSONArray array, int tagNum, String tableName, int group_id) {
        String sql = "";
        sql = "INSERT INTO `" + tableName + "` (group_id, ";
        for(int i = 0;i<tagNum;i++){
            if(i != tagNum-1){
                sql += "`"+tag[i]+"`" +", ";
            }else{
                sql += "`"+tag[i]+"`" +" ";
            }
        }
        sql += ") VALUES";
        JSONObject jsonObj = null;
        for(int i=0;i<array.length();i++){
            jsonObj = (JSONObject) array.get(i);
            for(int j=0;j<tagNum;j++){
                if(j==0){
                    sql += " ('" + group_id + "', ";
                    sql += "'" +jsonObj.get(tag[j])+"'" + " ,";
                }else if(j == tagNum-1){
                    sql += "'" +jsonObj.get(tag[j])+"'" +" )";
                }else{
                    sql += "'" +jsonObj.get(tag[j])+"'" +" ,";
                }


            }
            if(i != array.length()-1){
                sql += " ,";
            }else{
                sql += " ";
            }

        }
        System.out.print(sql);
        // Do sql insert.
        SQLiteAccess s = new SQLiteAccess();
        try {
            s.execSqlNoReturn(sql);
        } catch (SQLiteConnectionInvalidException e) {
            e.printStackTrace();
        }
        s.closeConnection();
        return sql;
    }
}
