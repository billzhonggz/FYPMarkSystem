package view;

import javax.swing.*;

/**
 * Created by ZHONG on 2017/5/3.
 */
public class EnterMarkUI {
    private JComboBox listGroupCB;
    private JTable enterMarkStuListTable;
    private JTable enterMarkEvaItemListTable;
    private JPanel EnterMarkPanel;
    private JButton nextStudentBtn;
    private JButton saveBtn;
    private JButton previousStudentBtn;
    private JButton logoutButton;

    public EnterMarkUI(){
        JFrame frame = new JFrame("EnterMarkUI");
        frame.setContentPane(this.EnterMarkPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
