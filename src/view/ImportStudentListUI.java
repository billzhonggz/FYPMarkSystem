package view;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ModelStudent;
import org.json.JSONArray;
import org.json.JSONObject;
import utli.ReadExcel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ZHONG on 2017/5/13.
 */
public class ImportStudentListUI {
    private JButton importBtn;
    private JTable importStudentTable;
    private JPanel JPanel1;
    private JSONArray inserted;
    private int groud_id = 1;

    public ImportStudentListUI() throws Exception {
        JFrame frame = new JFrame("Import Student List");
        frame.setContentPane(this.JPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //this.groud_id = groud_id;
        importBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ReadExcel.checkExcelVaild(new File("input/student.xlsx"));
                } catch (Exception e1) {
                    e1.printStackTrace();
                    ErrInfo dialog = new ErrInfo(e1.getMessage());
                    dialog.pack();
                    dialog.setVisible(true);
                }
                try {
                    inserted = ReadExcel.readExcel("input","student.xlsx",groud_id);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    ErrInfo dialog = new ErrInfo(e1.getMessage());
                    dialog.pack();
                    dialog.setVisible(true);
                }
                ArrayList<ModelStudent> students = new ArrayList<ModelStudent>();
                // Traverse JSON array.
                try {
                    for (int i = 0; i < inserted.length(); i++) {
                        JSONObject record = inserted.getJSONObject(i);
                        ObjectMapper mapper = new ObjectMapper();
                        students.add(mapper.readValue(record.toString(),ModelStudent.class));
                    }
                }catch (JsonMappingException e1) {
                    e1.printStackTrace();
                    ErrInfo dialog = new ErrInfo(e1.getMessage());
                    dialog.pack();
                    dialog.setVisible(true);
                }catch (IOException e1) {
                    e1.printStackTrace();
                    ErrInfo dialog = new ErrInfo(e1.getMessage());
                    dialog.pack();
                    dialog.setVisible(true);
                }
                // Assign students to object array.
                Object[][] studentTable = new Object[students.size()][4];
                String[] heander = {"Student ID", "Name", "Mobile", "Project Name"};
                for (int i = 0; i < students.size(); i++) {
                    studentTable[i][0] = students.get(i).getS_id();
                    studentTable[i][1] = students.get(i).getName();
                    studentTable[i][2] = students.get(i).getMobile();
                    studentTable[i][3] = students.get(i).getProject_name();
                }
                DefaultTableModel model = new DefaultTableModel(studentTable,heander) {
                    public boolean isCellEditable(int row,int col)
                    {
                        return false;
                    }
                };
                importStudentTable.setModel(model);
            }
        });
    }

    public static void main(String[] args) {
        try {
            new ImportStudentListUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
