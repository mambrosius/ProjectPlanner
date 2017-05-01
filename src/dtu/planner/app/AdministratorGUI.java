package dtu.planner.app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static dtu.planner.app.ProjectPlanner.*;

public class AdministratorGUI {

    private JPanel adminPanel;
    JFrame adminFrame;


    // project
    private DefaultTableModel projectTableModel;
    private JTable projectTable;

    private JButton registerProjectButton;
    private JButton unregisterProjectButton;

    // developer
    private DefaultTableModel developerTableModel;
    private JTable developerTable;

    private JButton registerDeveloperButton;
    private JButton unregisterDeveloperButton;
    private JButton assignManagerButton;
    private JButton assignDeveloperButton;
    private JButton unassignDeveloperButton;
    private JButton unassignManagerButton;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JLabel dateLabel;

    AdministratorGUI(Boolean visible) {

        setup();
        adminFrame.setVisible(visible);

        registerDeveloperButton.addActionListener(e -> {
            String initials = JOptionPane.showInputDialog("Type Developer Initials").toLowerCase();
            if (!initials.isEmpty())
                if (app.getAdmin().registerDeveloper(initials))
                    updateDeveloperTable();
                else
                    JOptionPane.showMessageDialog(adminPanel, "\"" + initials + "\" already exists");
        });

        unregisterDeveloperButton.addActionListener(e -> {
            String initials = (String) developerTable.getValueAt(developerTable.getSelectedRow(), 0);
            if (JOptionPane.showConfirmDialog(adminPanel, "Unregister: " + initials, "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                app.getAdmin().unregisterDeveloper(initials);
                updateDeveloperTable();
            }
        });

        registerProjectButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Write Project Name").toLowerCase();
            if (!name.isEmpty())
                if (app.getAdmin().registerProject(name))
                    updateProjectTable();
                else
                    JOptionPane.showMessageDialog(adminPanel, "\"" + name + "\" already exists");
        });

        unregisterProjectButton.addActionListener(e -> {
            String name = (String) projectTable.getValueAt(projectTable.getSelectedRow(), 0);
            if (JOptionPane.showConfirmDialog(adminPanel, "Unregister: " + name, "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                app.getAdmin().unregisterProject(name);
                updateProjectTable();
            }
        });

        assignDeveloperButton.addActionListener(e -> {
            String name = (String) projectTable.getValueAt(projectTable.getSelectedRow(),0);
            String initials = (String) JOptionPane.showInputDialog(adminPanel,
                    "Select Developer",
                    "Select Developer", //
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    app.getInitialsOfDevelopers(app.getDevelopers()).toArray(),
                    null);
            if (initials != null) {
                app.getProject(name).assignDeveloper(initials);
                updateProjectTable();
            }
        });

        unassignDeveloperButton.addActionListener(e -> {
            String name = (String) projectTable.getValueAt(projectTable.getSelectedRow(),0);
            String initials = (String) JOptionPane.showInputDialog(adminPanel,
                    "Unassign Developer",
                    "Unassign developer",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    app.getInitialsOfDevelopers(app.getProject(name).getDevelopers()).toArray(),
                    null);
            if (app.getProject(name).unassignDeveloper(initials))
                updateProjectTable();
        });

        assignManagerButton.addActionListener(e -> {
            String name = (String) projectTable.getValueAt(projectTable.getSelectedRow(),0);
            if (app.getProject(name).getManager() == null) {
                String initials = (String) JOptionPane.showInputDialog(adminPanel,
                        "Select Manager",
                        "Select manager", //
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        app.getInitialsOfDevelopers(app.getProject(name).getDevelopers()).toArray(),
                        null);
                if (initials != null) {
                    app.getDeveloper(initials).appointManager(app.getProject(name));
                    updateProjectTable();
                }
            }
        });

        unassignManagerButton.addActionListener(e -> {
            String name = (String) projectTable.getValueAt(projectTable.getSelectedRow(),0);
            String initials = app.getProject(name).getManagerInitials();
            if (JOptionPane.showConfirmDialog(adminPanel, "Unassign manager: " + initials, "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                app.getDeveloper(initials).stopManage(app.getProject(name));
                updateProjectTable();
            }
        });



    }

    private void setup() {
        adminFrame = new JFrame("Project Planner - Administrator");
        adminFrame.setContentPane(adminPanel);
        adminFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        adminFrame.pack();
        adminFrame.setSize(1000, 500);
        adminFrame.setLocationRelativeTo(null);

        dateLabel.setText(app.date.toString());

        projectTableModel = new DefaultTableModel(Project.getData(app.getProjects()), Project.columnNames);
        projectTable.setModel(projectTableModel);

        developerTableModel = new DefaultTableModel(Developer.getData(app.getDevelopers()), Developer.columnNames);
        developerTable.setModel(developerTableModel);
    }

    void updateProjectTable() {
        projectTableModel.setDataVector(Project.getData(app.getProjects()), Project.columnNames);
    }

    void updateDeveloperTable() {
        developerTableModel.setDataVector(Developer.getData(app.getDevelopers()), Developer.columnNames);
    }

    JFrame getAdminFrame() {
        return adminFrame;
    }

    void dispose() {
        adminFrame.dispose();
    }
}


