package dtu.planner.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class AdminLogin {

    private JFrame frame;
    private JPanel adminLoginPanel;

    private JButton cancelButton;
    private JButton loginButton;
    public JPasswordField passwordField;

    AdminLogin () {

        setup();

        loginButton.addActionListener(e -> {
            if (Arrays.equals(passwordField.getPassword(), "admin".toCharArray())) {
                new AdministratorGUI();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Password");
            }
            frame.dispose();
        });

        cancelButton.addActionListener(e -> {
           frame.dispose();
        });
    }

    private void setup() {
        frame = new JFrame("Admin Login");
        frame.setContentPane(adminLoginPanel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(250, 165);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getRootPane().setDefaultButton(loginButton);
        passwordField.setColumns(10);
    }
}



