package view;

import controller.ControllerItemGroup;
import model.ModelItemGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 2017/4/22.
 */
public class AddItemUI implements IModelListener {
    private final int[] percents = new int[12];
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
    private JLabel groupIdTF;
    private JLabel totalPercentTF;
    private JPanel JPanel1;
    private JTextField item6name;
    private JTextField item7name;
    private JTextField item8name;
    private JTextField item9name;
    private JTextField item10name;
    private JTextField item11name;
    private JTextField item12name;
    private JTextField item6per;
    private JTextField item7per;
    private JTextField item8per;
    private JTextField item9per;
    private JTextField item10per;
    private JTextField item11per;
    private JTextField item12per;
    private ModelItemGroup mig;
    private ControllerItemGroup cig;

    public AddItemUI() {
        final JFrame frame = new JFrame("AddItemUI");
        frame.setContentPane(this.JPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //get itemname
                String name1 = null;
                int id1 = 0;
                String name2 = null;
                int id2 = 0;
                String name3 = null;
                int id3 = 0;
                String name4 = null;
                int id4 = 0;
                String name5 = null;
                int id5 = 0;
                String name6 = null;
                int id6 = 0;
                String name7 = null;
                int id7 = 0;
                String name8 = null;
                int id8 = 0;
                String name9 = null;
                int id9 = 0;
                String name10 = null;
                int id10 = 0;
                String name11 = null;
                int id11 = 0;
                String name12 = null;
                int id12 = 0;
                try {
                    name1 = item1name.getText();
                    id1 = Integer.parseInt(item1per.getText());
                    percents[0] = id1;


                    name2 = item2name.getText();
                    id2 = Integer.parseInt(item2per.getText());
                    percents[1] = id2;


                    name3 = item3name.getText();
                    id3 = Integer.parseInt(item3per.getText());
                    percents[2] = id3;


                    name4 = item4name.getText();
                    id4 = Integer.parseInt(item4per.getText());
                    percents[3] = id4;


                    name5 = item5name.getText();
                    id5 = Integer.parseInt(item5per.getText());
                    percents[4] = id5;


                    name6 = item6name.getText();
                    id6 = Integer.parseInt(item6per.getText());
                    percents[5] = id6;


                    name7 = item7name.getText();
                    id7 = Integer.parseInt(item7per.getText());
                    percents[6] = id7;


                    name8 = item8name.getText();
                    id8 = Integer.parseInt(item8per.getText());
                    percents[7] = id8;


                    name9 = item9name.getText();
                    id9 = Integer.parseInt(item9per.getText());
                    percents[8] = id9;


                    name10 = item10name.getText();
                    id10 = Integer.parseInt(item10per.getText());
                    percents[9] = id10;


                    name11 = item11name.getText();
                    id11 = Integer.parseInt(item11per.getText());
                    percents[10] = id11;


                    name12 = item12name.getText();
                    id12 = Integer.parseInt(item12per.getText());
                    percents[11] = id12;
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(frame,"Some inputs are not integer.");
                }


                // Calculate percents.
                if (calcPreSum()) {
                    // Add to list.
                    cig.addItemToList(name1, id1);
                    cig.addItemToList(name2, id2);
                    cig.addItemToList(name3, id3);
                    cig.addItemToList(name4, id4);
                    cig.addItemToList(name5, id5);
                    cig.addItemToList(name6, id6);
                    cig.addItemToList(name7, id7);
                    cig.addItemToList(name8, id8);
                    cig.addItemToList(name9, id9);
                    cig.addItemToList(name10, id10);
                    cig.addItemToList(name11, id11);
                    cig.addItemToList(name12, id12);

                    // Add to Database.
                    cig.addItemsToDb();
                    cig.forwardToAddStudent();
                    frame.setVisible(false);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Entered percents do not have a sum of 100.");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AddItemUI");
        frame.setContentPane(new AddItemUI().JPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private boolean calcPreSum() {
        int sum = 0;
        for (int a : percents) {
            sum += a;
        }
        totalPercentTF.setText(Integer.toString(sum));
        if (sum != 100) {
            totalPercentTF.setForeground(Color.RED);
            return false;
        }
        if (sum == 100) {
            totalPercentTF.setForeground(Color.BLACK);
            return true;
        }
        return false;
    }

    public void setMVC(ModelItemGroup mig, ControllerItemGroup cig) {
        this.mig = mig;
        this.cig = cig;
        mig.setView(this);
    }

    public void notifyModelListener() {
        groupIdTF.setText(Integer.toString(mig.getGroupId()));
    }

}
