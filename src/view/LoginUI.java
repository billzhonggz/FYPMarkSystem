package view;

import controller.ControllerLogin;
import model.ModelLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrator on 2017/4/24.
 */
public class LoginUI implements IModelListener {
    private JPasswordField password;
    private JButton enterButton;
    private JPanel loginPanel;
    private JLabel statusLabel;
    private int pwdStatus;
    private ModelLogin ml;
    private ControllerLogin cl;
    private JFrame frame;

    public void LoginUI() {

    }

    public void initLoginUI() {
        frame = new JFrame("LoginUI");
        frame.setContentPane(this.loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void setMVC(ModelLogin ml, final ControllerLogin cl) {
        this.ml = ml;
        this.cl = cl;
        ml.setView(this);

        // Perform an action to button.
        // TODO: Bug: Never reach the listener.
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pwd = password.getText();
                if (cl.checkPwd(pwd)) {
                    pwdStatus = 1;
                    // TODO: Forward to the next page.
                    cl.forwardToNext();
                    frame.setVisible(false);
                    frame.dispose();
                } else
                    pwdStatus = 0;
            }
        });
    }

    public void notifyModelListener() {
        statusLabel.setText("Login success.");
    }
}
