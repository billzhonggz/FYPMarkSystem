package view;

import controller.ControllerExportReport;
import model.Item;
import model.ModelExportReport;
import model.ModelStudent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by ZHONG on 2017/5/14.
 */
public class ExportReportUI implements IModelListener {
    private JLabel groupId;
    private JTable stuGradeList;
    private JButton assignGradesButton;
    private JButton exportGradeReportButton;
    private JPanel exportGradePanel;
    private JButton backButton;

    private ModelExportReport mer;
    private ControllerExportReport cer;

    public ExportReportUI() {
        final JFrame frame = new JFrame("EnterMarkUI");
        frame.setContentPane(this.exportGradePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        assignGradesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cer.assignGrades();
            }
        });

        exportGradeReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cer.writeExcel();
                JOptionPane.showMessageDialog(frame, "Grade report exported successfully.");
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cer.backToEnterMark();
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        new ExportReportUI();
    }

    public void notifyModelListener() {
        // Get lists and display.
        ArrayList<ModelStudent> students = mer.getStudents();
        ArrayList<Item> items = mer.getItems();
        Object[][] gradeListData = new Object[students.size()][10];
        String[] header = {
                "Student ID",
                "Name",
                "Mobile",
                "Project Name",
                items.get(0).getItemName(),
                items.get(1).getItemName(),
                items.get(2).getItemName(),
                items.get(3).getItemName(),
                items.get(4).getItemName(),
                "Total Score"
        };
        for (int i = 0; i < students.size(); i++) {
            gradeListData[i][0] = students.get(i).getS_id();
            gradeListData[i][1] = students.get(i).getName();
            gradeListData[i][2] = students.get(i).getMobile();
            gradeListData[i][3] = students.get(i).getProject_name();
            gradeListData[i][4] = students.get(i).getScore1();
            gradeListData[i][5] = students.get(i).getScore2();
            gradeListData[i][6] = students.get(i).getScore3();
            gradeListData[i][7] = students.get(i).getScore4();
            gradeListData[i][8] = students.get(i).getScore5();
            gradeListData[i][9] = students.get(i).getTotalScore();
        }
        DefaultTableModel model = new DefaultTableModel(gradeListData, header) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        stuGradeList.setModel(model);
        groupId.setText(Integer.toString(mer.getCurrentGroupId()));
    }

    public void setMVC(ModelExportReport mer, ControllerExportReport cer) {
        this.mer = mer;
        this.cer = cer;
        this.mer.setView(this);
    }
}
