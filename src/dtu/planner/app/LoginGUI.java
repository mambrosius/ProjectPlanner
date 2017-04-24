package dtu.planner.app;

import javax.swing.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static dtu.planner.app.ProjectPlanner.app;

public class LoginGUI {

    private JPanel mainPanel;
    private JButton devButton;
    private JButton adminButton;

    LoginGUI() {

        setup();

        adminButton.addActionListener(e -> {
            new AdminLogin();
        });

        devButton.addActionListener(e -> {
            String initials = JOptionPane.showInputDialog("Enter Initials").toLowerCase();
            if (app.getInitialsOfDevelopers().contains(initials)) {
                if (!app.getDeveloperBy(initials).isManager()) {
                    new DeveloperGUI(initials);
                } else {
                    String[] options = new String[] {"manager", "developer"};

                    int option = JOptionPane.showOptionDialog(mainPanel, "login as", "Login",
                            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                    if (option == 0) {
                        new ManagerGUI(initials);
                    } else if (option == 1) {
                        new DeveloperGUI(initials);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(mainPanel, "\""+ initials + "\" does not exist");
            }
        });



            /*
            String initials = (String) JOptionPane.showInputDialog(mainPanel,
                    "select developer",
                    "Developer Login",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    app.getInitialsOf(app.getDevelopers()),
                    null);
            */
            /*
            if (initials != null) {
                if (!app.getDeveloperBy(initials).isManager()) {
                    new DeveloperGUI(initials);
                } else {
                    String selectedPosition = (String) JOptionPane.showInputDialog(mainPanel,
                            "login as",
                            "Login as",
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            new String[] {"developer", "manager"},
                            null);
                    if (selectedPosition.equals("manager")) {
                        new ManagerGUI(initials);
                    } else if (selectedPosition.equals("developer")) {
                        new DeveloperGUI(initials);
                    }
                }
            } else {

            }


        */
    }

    private void setup() {
        JFrame titleSelectFrame = new JFrame("Project Planner");
        titleSelectFrame.setContentPane(mainPanel);
        titleSelectFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        titleSelectFrame.pack();
        titleSelectFrame.setSize(250, 165);
        titleSelectFrame.setLocationRelativeTo(null);
        titleSelectFrame.setVisible(true);
    }
}

