package dtu.planner.app;

import javax.swing.*;
import java.util.Arrays;

import static dtu.planner.app.ProjectPlanner.app;

public class MainGUI {

    private JLabel dateLabel;
    private JPanel mainPanel;

    private JPasswordField adminPasswordField;
    private JButton adminLoginButton;
    private JButton devLoginButton;
    private JTextField devTextField;
    private JTabbedPane mainTabbedPane;
    private JPanel adminPanel;
    private JPanel devPanel;

    MainGUI() {
        setup();

        adminLoginButton.addActionListener(e -> {
            if (Arrays.equals(adminPasswordField.getPassword(), "admin".toCharArray()))
                new AdministratorGUI(true);
            else
                JOptionPane.showMessageDialog(null, "Invalid Password");
            adminPasswordField.setText("");
        });

        devLoginButton.addActionListener(e -> {
            String initials =  devTextField.getText().toLowerCase();

            if (app.getDevelopers().contains(app.getDeveloper(initials))) {
                devTextField.setText("");
                if (app.getDeveloper(initials).getResponsibilities().isEmpty())
                    new DeveloperGUI(initials, true);
                else
                    new ManagerGUI(initials);
            } else
                JOptionPane.showMessageDialog(mainPanel, "\""+ initials + "\" does not exist");
            devTextField.setText("");
        });
    }

    private void setup() {
        JFrame mainFrame = new JFrame("Project Planner");
        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setSize(300, 250);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        //adminPanel.getRootPane().setDefaultButton(adminLoginButton);
        //dateLabel.setText(app.date.toString());
    }
}
