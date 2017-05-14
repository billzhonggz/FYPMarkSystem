package view;

import controller.ControllerEnterMark;
import model.Item;
import model.ModelEnterMark;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by ZHONG on 2017/5/3.
 */
public class EnterMarkUI implements IModelListener {
    private JComboBox listGroupCB;
    private JTable enterMarkStuListTable;
    private JPanel EnterMarkPanel;
    private JButton exportGradeReportBtn;
    private JButton saveBtn;
    private JButton logoutButton;
    private JLabel selectedStuId;
    private JFormattedTextField score1;
    private JFormattedTextField score2;
    private JFormattedTextField score3;
    private JFormattedTextField score4;
    private JFormattedTextField score5;
    private JLabel selectedStuName;
    private JLabel item1Label;
    private JLabel item2Label;
    private JLabel item3Label;
    private JLabel item4Label;
    private JLabel item5Label;
    private JLabel item1Per;
    private JLabel item2Per;
    private JLabel item3Per;
    private JLabel item4Per;
    private JLabel item5Per;
    private JLabel totalScoreLabel;
    private JButton addNewGroupButton;
    private JLabel item6Label;
    private JLabel item7Label;
    private JLabel item8Label;
    private JLabel item9Label;
    private JLabel item10Label;
    private JLabel item11Label;
    private JLabel item12Label;
    private JLabel item6Per;
    private JLabel item7Per;
    private JLabel item8Per;
    private JLabel item9Per;
    private JLabel item10Per;
    private JLabel item11Per;
    private JLabel item12Per;
    private JComboBox grade1;
    private JComboBox grade2;
    private JComboBox grade3;
    private JComboBox grade4;
    private JComboBox grade5;
    private JComboBox grade6;
    private JComboBox grade7;
    private JComboBox grade8;
    private JComboBox grade9;
    private JComboBox grade10;
    private JComboBox grade11;
    private JComboBox grade12;

    private ModelEnterMark mek;
    private ControllerEnterMark cek;
    private int selectedId = 0;


    public EnterMarkUI() {
        final JFrame frame = new JFrame("EnterMarkUI");
        frame.setContentPane(this.EnterMarkPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cek.backToLogin();
                frame.setVisible(false);
                frame.dispose();
            }
        });
        listGroupCB.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (ItemEvent.SELECTED == e.getStateChange()) {
                    int idSelected = (Integer) e.getItem();
                    cek.setCurrentGroupId(idSelected);
                }
            }
        });
        // Click listener for student list table.
        enterMarkStuListTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Transfer selected student information.
                int row = enterMarkStuListTable.getSelectedRow();
                selectedId = (Integer) enterMarkStuListTable.getValueAt(row, 0);
                String name = (String) enterMarkStuListTable.getValueAt(row, 1);
                selectedStuId.setText(Integer.toString(selectedId));
                selectedStuName.setText(name);
                Student s = mek.findModelStudentBySid(selectedId);
                grade1.setSelectedItem(s.getScore1());
                grade2.setSelectedItem(s.getScore2());
                grade3.setSelectedItem(s.getScore3());
                grade4.setSelectedItem(s.getScore4());
                grade5.setSelectedItem(s.getScore5());
                grade6.setSelectedItem(s.getScore6());
                grade7.setSelectedItem(s.getScore7());
                grade8.setSelectedItem(s.getScore8());
                grade9.setSelectedItem(s.getScore9());
                grade10.setSelectedItem(s.getScore10());
                grade11.setSelectedItem(s.getScore11());
                grade12.setSelectedItem(s.getScore12());
                totalScoreLabel.setText(String.valueOf(s.getTotalScore()));
            }
        });
        addNewGroupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cek.addNewGroup();
                frame.setVisible(false);
                frame.dispose();
            }
        });
        exportGradeReportBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cek.forwardToExport();
                frame.setVisible(false);
                frame.dispose();
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s1 = (String) grade1.getSelectedItem();
                String s2 = (String) grade2.getSelectedItem();
                String s3 = (String) grade3.getSelectedItem();
                String s4 = (String) grade4.getSelectedItem();
                String s5 = (String) grade5.getSelectedItem();
                String s6 = (String) grade6.getSelectedItem();
                String s7 = (String) grade7.getSelectedItem();
                String s8 = (String) grade8.getSelectedItem();
                String s9 = (String) grade9.getSelectedItem();
                String s10 = (String) grade10.getSelectedItem();
                String s11 = (String) grade11.getSelectedItem();
                String s12 = (String) grade12.getSelectedItem();
                if (selectedId != 0) {
                    cek.setScore(selectedId, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12);
                    JOptionPane.showMessageDialog(frame, "Score of " + selectedId + " saved. ");
                } else
                    JOptionPane.showMessageDialog(frame, "Save failed: student not selected. ");
            }
        });
    }

    public static void main(String[] args) {
        new EnterMarkUI();
    }

    public void notifyModelListener() {
        if (mek.getCurrentGroupId() == 0) {
            // Update group list.
            ArrayList<Integer> groupIds = mek.getGroupIds();
            for (int id : groupIds) {
                listGroupCB.addItem(id);
            }
        } else {
            // Update student table.
            ArrayList<Student> students = mek.getStudents();
            Object[][] studentTable = new Object[students.size()][8];
            String[] heander = {"Student ID", "Name", "Mobile", "Supervisor", "Observer", "Examiner", "Project Name", "G/I"};
            for (int i = 0; i < students.size(); i++) {
                studentTable[i][0] = students.get(i).getS_id();
                studentTable[i][1] = students.get(i).getName();
                studentTable[i][2] = students.get(i).getMobile();
                studentTable[i][3] = students.get(i).getSupervisor();
                studentTable[i][4] = students.get(i).getExaminer();
                studentTable[i][5] = students.get(i).getObserver();
                studentTable[i][6] = students.get(i).getProject_name();
                studentTable[i][7] = students.get(i).getG_i();
            }
            DefaultTableModel model = new DefaultTableModel(studentTable, heander) {
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
            enterMarkStuListTable.setModel(model);
            // Update item list.
            ArrayList<Item> items = mek.getItems();
            if (items.size() != 0) {
                item1Label.setText(items.get(0).getItemName());
                item1Per.setText(Integer.toString(items.get(0).getItemPercentage()));
                item2Label.setText(items.get(1).getItemName());
                item2Per.setText(Integer.toString(items.get(1).getItemPercentage()));
                item3Label.setText(items.get(2).getItemName());
                item3Per.setText(Integer.toString(items.get(2).getItemPercentage()));
                item4Label.setText(items.get(3).getItemName());
                item4Per.setText(Integer.toString(items.get(3).getItemPercentage()));
                item5Label.setText(items.get(4).getItemName());
                item5Per.setText(Integer.toString(items.get(4).getItemPercentage()));
                item6Label.setText(items.get(5).getItemName());
                item6Per.setText(Integer.toString(items.get(5).getItemPercentage()));
                item7Label.setText(items.get(6).getItemName());
                item7Per.setText(Integer.toString(items.get(6).getItemPercentage()));
                item8Label.setText(items.get(7).getItemName());
                item8Per.setText(Integer.toString(items.get(7).getItemPercentage()));
                item9Label.setText(items.get(8).getItemName());
                item9Per.setText(Integer.toString(items.get(8).getItemPercentage()));
                item10Label.setText(items.get(9).getItemName());
                item10Per.setText(Integer.toString(items.get(9).getItemPercentage()));
                item11Label.setText(items.get(10).getItemName());
                item11Per.setText(Integer.toString(items.get(10).getItemPercentage()));
                item12Label.setText(items.get(11).getItemName());
                item12Per.setText(Integer.toString(items.get(11).getItemPercentage()));
            }
            if (selectedId != 0) {
                // Update total score for selected student.
                Student s = mek.findModelStudentBySid(selectedId);
                totalScoreLabel.setText(String.valueOf(s.getTotalScore()));
            }
        }
    }

    public void setMVC(ModelEnterMark mek, ControllerEnterMark cek) {
        this.mek = mek;
        this.cek = cek;
        this.mek.setView(this);
        this.cek.searchGroupIds();
    }
}
