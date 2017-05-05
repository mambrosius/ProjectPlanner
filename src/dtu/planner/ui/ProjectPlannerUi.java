package dtu.planner.ui;

import dtu.planner.models.ProjectPlanner;

import javax.swing.*;

public class ProjectPlannerUi extends JFrame {

    private ProjectPlanner projectPlanner;
    private JPanel mainPanel;
    private JButton devLoginButton;
    private JButton adminLoginButton;
    private JTextField devTextField;
    private JPasswordField adminPasswordField;

    public ProjectPlannerUi() {

        adminLoginButton.addActionListener(e -> {
            if (!projectPlanner.adminLogin(adminPasswordField.getPassword()))
                JOptionPane.showMessageDialog(null, " invalid password");
            adminPasswordField.setText("");
        });

        devLoginButton.addActionListener(e -> {
            String initials = devTextField.getText().toLowerCase();
            if (!projectPlanner.devLogin(initials))
                JOptionPane.showMessageDialog(null, initials + " does not exist");
            devTextField.setText("");
        });
    }

    public void startApplication() {
        projectPlanner = new ProjectPlanner();
        setup();
    }

    private void setup() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setSize(300, 250);
        setLocationRelativeTo(null);
    }
}