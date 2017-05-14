package utli;

import model.Item;
import model.Student;
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

    public static void writeExcel(ArrayList<Student> students, ArrayList<Item> items) {
        //Create a new Workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("FYP Grade Report");

        //Create the data for the excel sheet
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        int i = 3;
        // Read item list for header.
        String item1Name = items.get(0).getItemName();
        String item2Name = items.get(1).getItemName();
        String item3Name = items.get(2).getItemName();
        String item4Name = items.get(3).getItemName();
        String item5Name = items.get(4).getItemName();
        String item6Name = items.get(5).getItemName();
        String item7Name = items.get(6).getItemName();
        String item8Name = items.get(7).getItemName();
        String item9Name = items.get(8).getItemName();
        String item10Name = items.get(9).getItemName();
        String item11Name = items.get(10).getItemName();
        String item12Name = items.get(11).getItemName();
        // Set up header.
        data.put(Integer.toString(1), new Object[]{
                "Student ID",
                "Student Name",
                "Project Name",
                item1Name,
                item2Name,
                item3Name,
                item4Name,
                item5Name,
                item6Name,
                item7Name,
                item8Name,
                item9Name,
                item10Name,
                item11Name,
                item12Name,
                "Total Score"
        });
        data.put(Integer.toString(2), new Object[]{
                "",
                "",
                "Weighting (%)",
                items.get(0).getItemPercentage(),
                items.get(1).getItemPercentage(),
                items.get(2).getItemPercentage(),
                items.get(3).getItemPercentage(),
                items.get(4).getItemPercentage(),
                items.get(5).getItemPercentage(),
                items.get(6).getItemPercentage(),
                items.get(7).getItemPercentage(),
                items.get(8).getItemPercentage(),
                items.get(9).getItemPercentage(),
                items.get(10).getItemPercentage(),
                items.get(11).getItemPercentage(),
                "--"
        });
        for (Student s : students) {
            i++;
            data.put(Integer.toString(i), new Object[]{
                    s.getS_id(),
                    s.getName(),
                    s.getProject_name(),
                    s.getScore1(),
                    s.getScore2(),
                    s.getScore3(),
                    s.getScore4(),
                    s.getScore5(),
                    s.getScore6(),
                    s.getScore7(),
                    s.getScore8(),
                    s.getScore9(),
                    s.getScore10(),
                    s.getScore11(),
                    s.getScore12(),
                    s.getTotalScore(),
            });
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
                else if (obj instanceof Float)
                    cell.setCellValue(Float.toString((Float) obj));
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

//    public static void main(String[] args) {
//        ArrayList<Student> students = new ArrayList<Student>();
//        ArrayList<Item> items = new ArrayList<Item>();
//        for (int i = 0; i < 5; i++) {
//            items.add(new Item(Integer.toString(i), i));
//            Student s = new Student(1, i + 1430003000, "Test student", 13800138000L, "Test Project Name", i, i, i, i, i, i * 5);
//            s.setGrade('A');
//            students.add(s);
//        }
//        writeExcel(students, items);
//    }
}
