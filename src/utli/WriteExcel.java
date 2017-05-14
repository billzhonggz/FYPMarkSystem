package utli;

import model.Item;
import model.ModelStudent;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by ZHONG on 2017/5/14.
 */
public class WriteExcel {

    public static void writeExcel(ArrayList<ModelStudent> students, ArrayList<Item> items) {
        //Create a new Workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("FYP Grade Report");

        //Create the data for the excel sheet
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        int i = 1;
        // Read item list for header.
        String item1Name = items.get(0).getItemName();
        String item2Name = items.get(1).getItemName();
        String item3Name = items.get(2).getItemName();
        String item4Name = items.get(3).getItemName();
        String item5Name = items.get(4).getItemName();
        // Set up header.
        data.put(Integer.toString(i), new Object[]{"Student ID", "Student Name", "Project Name", item1Name, item2Name, item3Name, item4Name, item5Name, "Total Score", "Grade"});
        for (ModelStudent s : students) {
            i++;
            data.put(Integer.toString(i), new Object[]{s.getS_id(), s.getName(), s.getProject_name(), s.getScore1(), s.getScore2(), s.getScore3(), s.getScore4(), s.getScore5(), s.getTotalScore(), s.getGrade()});
        }
        //Iterate over data and write it to the sheet
        Set keyset = data.keySet();
        int rownum = 0;
        for (Object key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                cell.setCellType(CellType.STRING);
                if (obj instanceof Integer)
                    cell.setCellValue(Integer.toString((Integer) obj));
                else if (obj instanceof Character)
                    cell.setCellValue(Character.toString((Character) obj));
                else
                    cell.setCellValue((String) obj);
            }
        }
        //Save the excel sheet
        try {
            FileOutputStream out = new FileOutputStream(new File("FYPGradeReport.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Grade Report Successfully Created");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArrayList<ModelStudent> students = new ArrayList<ModelStudent>();
        ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 0; i < 5; i++) {
            items.add(new Item(Integer.toString(i), i));
            ModelStudent s = new ModelStudent(1, i + 1430003000, "Test student", 13800138000L, "Test Project Name", i, i, i, i, i, i * 5);
            s.setGrade('A');
            students.add(s);
        }
        writeExcel(students, items);
    }
}
