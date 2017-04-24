package dtu.planner.app;

import javax.swing.*;

import static dtu.planner.app.ProjectPlanner.app;

public class PositionSelectGUI {

    private JPanel mainPanel;
    private JButton devButton;
    private JButton adminButton;

    PositionSelectGUI() {

        setup();

        adminButton.addActionListener(e -> {
            String password = JOptionPane.showInputDialog("Enter Password");
            if (password.equals("admin")) {
                new AdministratorGUI();
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Invalid Password");
            }
        });

        devButton.addActionListener(e -> {
            String initials = (String) JOptionPane.showInputDialog(mainPanel,
                    "select developer",
                    "Developer Login",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    app.getInitialsOf(app.getDevelopers()),
                    null);

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
            }
        });
    }

    private void setup() {
        JFrame titleSelectFrame = new JFrame("Project Planner");
        titleSelectFrame.setContentPane(mainPanel);
        titleSelectFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        titleSelectFrame.pack();
        titleSelectFrame.setSize(260, 165);
        titleSelectFrame.setLocationRelativeTo(null);
        titleSelectFrame.setVisible(true);
    }
}

