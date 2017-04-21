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

            if (app.getDeveloperBy(initials).isManager()) {



                String position = (String) JOptionPane.showInputDialog(mainPanel,
                        "login as",
                        "Login as",
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        new String[] {"developer", "manager"},
                        null);

                if (position.equals("manager")) {
                    new ManagerGUI(initials);
                } else if (position.equals("developer")) {
                    new DeveloperGUI(initials);
                }

            } else if (initials != null) {
                new DeveloperGUI(initials);
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

