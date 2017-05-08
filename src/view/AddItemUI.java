package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 2017/4/22.
 */
public class AddItemUI {
    private JTextField item1name;
    private JTextField item1per;
    private JTextField item2name;
    private JPanel Jpanel2;
    private JTextField item2per;
    private JTextField item3name;
    private JTextField item3per;
    private JTextField item4name;
    private JTextField item4per;
    private JTextField item5name;
    private JTextField item5per;
    private JButton saveButton;
    private JButton cancelButton;
    private JLabel itemGroupID;
    private JLabel groupIdTF;
    private JLabel totalPercentTF;
    private JPanel JPanel1;

    public static void main(String[] args){
        JFrame frame = new JFrame("AddItemUI");
        frame.setContentPane(new AddItemUI().JPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public AddItemUI()
    {
        JFrame frame = new JFrame("AddItemUI");
        frame.setContentPane(this.JPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String[] saveInput = new String[20];

               //get itemname
               String name1 = item1name.getText();
               saveInput[0] = name1;

               //get percentage
                int id = 0;
                try
                {
                    id = Integer.parseInt((String)item1per.getText());
                } catch (NumberFormatException e1)
                {
                    e1.printStackTrace();
                    ErrInfo dialog = new ErrInfo("Please make sure your input is integer.");
                    dialog.pack();
                    dialog.setVisible(true);
                    return;
                }
                saveInput[1] = Integer.toString(id);

                String name2 = item2name.getText();
                saveInput[2] = name2;

                int id1 = 0;
                try
                {
                    id1 = Integer.parseInt((String)item2per.getText());
                } catch (NumberFormatException e1)
                {
                    e1.printStackTrace();
                    ErrInfo dialog = new ErrInfo("Please make sure your input is integer.");
                    dialog.pack();
                    dialog.setVisible(true);
                    return;
                }
                saveInput[3] = Integer.toString(id);

                String name3 = item3name.getText();
                saveInput[4] = name3;

                int id3 = 0;
                try
                {
                    id3 = Integer.parseInt((String)item3per.getText());
                } catch (NumberFormatException e1)
                {
                    e1.printStackTrace();
                    ErrInfo dialog = new ErrInfo("Please make sure your input is integer.");
                    dialog.pack();
                    dialog.setVisible(true);
                    return;
                }
                saveInput[5] = Integer.toString(id);

                String name4 = item4name.getText();
                saveInput[6] = name4;

                int id4 = 0;
                try
                {
                    id4 = Integer.parseInt((String)item4per.getText());
                } catch (NumberFormatException e1)
                {
                    e1.printStackTrace();
                    ErrInfo dialog = new ErrInfo("Please make sure your input is integer.");
                    dialog.pack();
                    dialog.setVisible(true);
                    return;
                }
                saveInput[7] = Integer.toString(id);

                String name5 = item5name.getText();
                saveInput[8] = name5;

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
