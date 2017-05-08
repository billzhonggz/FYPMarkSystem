package view;

import controller.ControllerItemGroup;
import model.ModelItemGroup;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 2017/4/22.
 */
public class AddItemUI implements IModelListener {
    private final int[] percents = new int[5];
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
    private ModelItemGroup mig;
    private ControllerItemGroup cig;

    public AddItemUI() {
        JFrame frame = new JFrame("AddItemUI");
        frame.setContentPane(this.JPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        // Set save button disable unless sum of percentages is 100%.
        saveButton.setEnabled(false);

        // Set listeners for all text fields.
        item1per.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                percents[0] = Integer.parseInt(item1per.getText());
                calcPreSum();
            }

            public void removeUpdate(DocumentEvent e) {
                percents[0] = Integer.parseInt(item1per.getText());
                calcPreSum();
            }

            public void changedUpdate(DocumentEvent e) {
                percents[0] = Integer.parseInt(item1per.getText());
                calcPreSum();
            }
        });

        item2per.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                percents[1] = Integer.parseInt(item2per.getText());
                calcPreSum();
            }

            public void removeUpdate(DocumentEvent e) {
                percents[1] = Integer.parseInt(item2per.getText());
                calcPreSum();
            }

            public void changedUpdate(DocumentEvent e) {
                percents[1] = Integer.parseInt(item2per.getText());
                calcPreSum();
            }
        });

        item3per.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                percents[2] = Integer.parseInt(item3per.getText());
                calcPreSum();
            }

            public void removeUpdate(DocumentEvent e) {
                percents[2] = Integer.parseInt(item3per.getText());
                calcPreSum();
            }

            public void changedUpdate(DocumentEvent e) {
                percents[2] = Integer.parseInt(item3per.getText());
                calcPreSum();
            }
        });

        item4per.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                percents[3] = Integer.parseInt(item4per.getText());
                calcPreSum();
            }

            public void removeUpdate(DocumentEvent e) {
                percents[3] = Integer.parseInt(item4per.getText());
                calcPreSum();
            }

            public void changedUpdate(DocumentEvent e) {
                percents[3] = Integer.parseInt(item4per.getText());
                calcPreSum();
            }
        });

        item5per.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                percents[4] = Integer.parseInt(item5per.getText());
                calcPreSum();
            }

            public void removeUpdate(DocumentEvent e) {
                percents[4] = Integer.parseInt(item5per.getText());
                calcPreSum();
            }

            public void changedUpdate(DocumentEvent e) {
                percents[4] = Integer.parseInt(item5per.getText());
                calcPreSum();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //get itemname
                String name1 = item1name.getText();
                //get percentage
                int id1 = 0;
                try {
                    id1 = Integer.parseInt((String) item1per.getText());
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                    ErrInfo dialog = new ErrInfo("Item 1 percentage is not integer.");
                    dialog.pack();
                    dialog.setVisible(true);
                    return;
                }
                cig.addItemToList(name1, id1);

                String name2 = item2name.getText();
                int id2 = 0;
                try {
                    id2 = Integer.parseInt((String) item2per.getText());
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                    ErrInfo dialog = new ErrInfo("Item 2 percentage is not integer.");
                    dialog.pack();
                    dialog.setVisible(true);
                    return;
                }
                cig.addItemToList(name2, id2);

                String name3 = item3name.getText();
                int id3 = 0;
                try {
                    id3 = Integer.parseInt((String) item3per.getText());
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                    ErrInfo dialog = new ErrInfo("Item 3 percentage is not integer.");
                    dialog.pack();
                    dialog.setVisible(true);
                    return;
                }
                cig.addItemToList(name3, id3);

                String name4 = item4name.getText();
                int id4 = 0;
                try {
                    id4 = Integer.parseInt((String) item4per.getText());
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                    ErrInfo dialog = new ErrInfo("Item 4 percentage is not integer.");
                    dialog.pack();
                    dialog.setVisible(true);
                    return;
                }
                cig.addItemToList(name4, id4);

                String name5 = item5name.getText();
                int id5 = 0;
                try {
                    id5 = Integer.parseInt((String) item5per.getText());
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                    ErrInfo dialog = new ErrInfo("Item 5 percentage is not integer.");
                    dialog.pack();
                    dialog.setVisible(true);
                    return;
                }
                cig.addItemToList(name5, id5);

                // Add to Database.
                cig.addItemsToDb();
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

    private void calcPreSum() {
        int sum = 0;
        for (int a : percents) {
            sum += a;
        }
        totalPercentTF.setText(Integer.toString(sum));
        if (sum != 100) {
            totalPercentTF.setForeground(Color.RED);
            saveButton.setEnabled(false);
        }
        if (sum == 100) {
            totalPercentTF.setForeground(Color.BLACK);
            saveButton.setEnabled(true);
        }
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
