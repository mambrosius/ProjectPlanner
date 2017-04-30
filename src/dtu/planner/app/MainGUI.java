package dtu.planner.app;

import javax.swing.*;
import java.util.Arrays;

import static dtu.planner.app.ProjectPlanner.app;

public class MainGUI {

    //private ManagerGUI manGui;
    //private DeveloperGUI devGui;
    //private AdministratorGUI adminGui;

    private JPanel mainPanel;
    private JButton devLoginButton;
    private JTextField devTextField;
    private JPasswordField adminPasswordField;
    private JPanel loginPanel;
    private JTabbedPane mainTabbedPane;

    private JButton adminLoginButton;


    MainGUI() {

        setup();

        adminLoginButton.addActionListener(e -> {
            if (Arrays.equals(adminPasswordField.getPassword(), "admin".toCharArray()))
                new AdministratorGUI(); //setupAdminTap();
            else
                JOptionPane.showMessageDialog(null, "Invalid Password");
            adminPasswordField.setText("");
        });

        devLoginButton.addActionListener(e -> {
            String initials =  devTextField.getText().toLowerCase();

            if (app.getDevelopers().contains(app.getDeveloper(initials))) {
                devTextField.setText("");
                if (app.getDeveloper(initials).getResponsibilities().isEmpty())
                    new DeveloperGUI(initials);
                else
                    new ManagerGUI(initials);
                //setupDeveloperTap(initials);

                   // setupManagerTap(initials);
                    //setupAdminTap();
                /*
                } else
                    mainTabbedPane.remove(loginPanel);*/
            } else
                JOptionPane.showMessageDialog(loginPanel, "\""+ initials + "\" does not exist");
            devTextField.setText("");
        });
    }

    private void setup() {
        JFrame mainFrame = new JFrame("Project Planner");
        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setSize(500, 250);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    /*
    private void setupAdminTap() {
        adminGui = new AdministratorGUI(false);

        Container adminContainer = new Container();
        adminContainer.add(adminGui.getAdminPanel(), adminGui);
        mainTabbedPane.addTab("administrator", adminContainer);

        mainTabbedPane.remove(loginPanel);
    }

    private void setupDeveloperTap(String initials) {
        devGui = new DeveloperGUI(initials, false);

        Container devContainer = new Container();
        devContainer.add(devGui.getDevPanel(), devGui);
        mainTabbedPane.addTab("developer", devContainer);
    }

    private void setupManagerTap(String initials) {

        manGui = new ManagerGUI(initials, false);
        Container manContainer = new Container();
        manContainer.add(manGui.getManagerPanel(), manGui);
        mainTabbedPane.addTab("manager", manContainer);
    }
    */
    /*

    void closeTaps() {
        manGui.dispose();
        devGui.dispose();
        adminGui.dipose();
        mainTabbedPane.removeAll();
        mainTabbedPane.addTab("login", loginPanel);
    }
    */

    public JPanel getLoginPanel() {
        return loginPanel;
    }
}
