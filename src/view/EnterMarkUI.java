package view;

import controller.ControllerEnterMark;
import model.ModelEnterMark;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ZHONG on 2017/5/3.
 */
public class EnterMarkUI implements IModelListener{
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


    public EnterMarkUI(){
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
    }

    public void notifyModelListener() {

    }

    public void setMVC(ModelEnterMark mek, ControllerEnterMark cek) {
        this.mek = mek;
        this.cek = cek;
        mek.setView(this);
    }

    public static void main(String[] args) {
        new EnterMarkUI();
    }
}
