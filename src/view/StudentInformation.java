package view;

import javax.swing.*;

/**
 * Created by 子楹 on 2017/4/23.
 */
public class StudentInformation {
    private JPanel panel1;
    private JButton Import;
    private JTable table1;

    public StudentInformation(){
        table1 = new JTable();
        String[] columnNames = {"s_id",
                "name",
                "mobile",
                "group_id"};
    }
}