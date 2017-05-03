package view;

import javax.swing.*;

/**
 * Created by Administrator on 2017/4/24.
 */
public class LoginUI {
    private JPasswordField password;
    private JButton enterButton;
    private JPanel loginPanel;

    public static void main(String[] args){
        JFrame frame = new JFrame("LoginUI");
        frame.setContentPane(new LoginUI().loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
