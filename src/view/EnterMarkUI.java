package view;

import controller.ControllerEnterMark;
import model.ModelEnterMark;
import model.ModelStudent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Created by ZHONG on 2017/5/3.
 */
public class EnterMarkUI implements IModelListener {
    private JComboBox listGroupCB;
    private JTable enterMarkStuListTable;
    private JTable enterMarkEvaItemListTable;
    private JPanel EnterMarkPanel;
    private JButton nextStudentBtn;
    private JButton saveBtn;
    private JButton previousStudentBtn;
    private JButton logoutButton;

    private ModelEnterMark mek;
    private ControllerEnterMark cek;


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
        }
    }

    public void setMVC(ModelEnterMark mek, ControllerEnterMark cek) {
        this.mek = mek;
        this.cek = cek;
        this.mek.setView(this);
        this.cek.searchGroupIds();
    }
}
