package view;

import controller.ControllerEnterMark;
import model.Item;
import model.ModelEnterMark;
import model.ModelStudent;

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
    private JButton nextStudentBtn;
    private JButton saveBtn;
    private JButton previousStudentBtn;
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

    private ModelEnterMark mek;
    private ControllerEnterMark cek;
    private int selectedId;


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
                ModelStudent s = mek.findModelStudentBySid(selectedId);
                score1.setText(Integer.toString(s.getScore1()));
                score2.setText(Integer.toString(s.getScore2()));
                score3.setText(Integer.toString(s.getScore3()));
                score4.setText(Integer.toString(s.getScore4()));
                score5.setText(Integer.toString(s.getScore5()));
                totalScoreLabel.setText(Integer.toString(s.getTotalScore()));
            }
        });
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Calculate and forward total score.
                int s1 = 0;
                int s2 = 0;
                int s3 = 0;
                int s4 = 0;
                int s5 = 0;
                int st = 0;
                try {
                    s1 = Integer.parseInt(score1.getText());
                    s2 = Integer.parseInt(score2.getText());
                    s3 = Integer.parseInt(score3.getText());
                    s4 = Integer.parseInt(score4.getText());
                    s5 = Integer.parseInt(score5.getText());
                    st = s1 * Integer.parseInt(item1Per.getText()) / 100 +
                            s2 * Integer.parseInt(item2Per.getText()) / 100 +
                            s3 * Integer.parseInt(item3Per.getText()) / 100 +
                            s4 * Integer.parseInt(item4Per.getText()) / 100 +
                            s5 * Integer.parseInt(item5Per.getText()) / 100;
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Please input integers.");
                }
                if (selectedId != 0) {
                    cek.setScore(selectedId, s1, s2, s3, s4, s5, st);
                    totalScoreLabel.setText(Integer.toString(st));
                    JOptionPane.showMessageDialog(frame, "Score of " + selectedId + " saved. ");
                }
                else
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
            ArrayList<ModelStudent> students = mek.getStudents();
            Object[][] studentTable = new Object[students.size()][4];
            String[] heander = {"Student ID", "Name", "Mobile", "Project Name"};
            for (int i = 0; i < students.size(); i++) {
                studentTable[i][0] = students.get(i).getS_id();
                studentTable[i][1] = students.get(i).getName();
                studentTable[i][2] = students.get(i).getMobile();
                studentTable[i][3] = students.get(i).getProject_name();
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
