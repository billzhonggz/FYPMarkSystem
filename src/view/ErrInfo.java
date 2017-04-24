package view;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;

import javax.swing.*;
import java.awt.event.*;

public class ErrInfo extends JDialog
{
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea textArea1;
    private String ErrMsg;
    private final static String newline = "\n";

    public ErrInfo(String message)
    {
        this.ErrMsg = message;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        textArea1.append(ErrMsg+ newline);

        buttonOK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {onOK();}
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK()
    {
// add your code here
        dispose();
    }

    private void onCancel()
    {
// add your code here if necessary
        dispose();
    }


    public static void main(String[] args)
    {
        ErrInfo dialog = new ErrInfo("Test Information");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
