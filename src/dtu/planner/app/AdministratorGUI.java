package dtu.planner.app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static dtu.planner.app.ProjectPlanner.*;

public class AdministratorGUI {

    private JPanel adminPanel;

    // project
    private static Object[] projectColumnNames = new Object[]{"name", "participants", "manager"};
    private static DefaultTableModel projectTableModel = new DefaultTableModel(projectColumnNames, 0);
    private JTable projectTable;

    private JButton registerProjectButton;
    private JButton unregisterProjectButton;

    // developer
    private static Object[] developerColumnNames = new Object[]{"initials"};
    private static DefaultTableModel developerTableModel = new DefaultTableModel(developerColumnNames, 0);
    private JTable developerTable;

    private JButton registerDeveloperButton;
    private JButton unregisterDeveloperButton;
    private JButton assignManagerButton;
    private JButton assignDeveloperButton;
    private JButton reassignDeveloperButton;
    private JButton reassignManagerButton;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;

    AdministratorGUI() {

        setup();

        projectTable.setModel(projectTableModel);
        developerTable.setModel(developerTableModel);

        registerDeveloperButton.addActionListener(e -> {
            String initials = JOptionPane.showInputDialog("Type Developer Initials");
            app.admin.registerDeveloper(initials);
            developerTableModel.addRow(new Object[] {initials});
        });

        unregisterDeveloperButton.addActionListener(e -> {
            String initials = (String) developerTable.getValueAt(developerTable.getSelectedRow(), 0);
            if (JOptionPane.showConfirmDialog(adminPanel, "Unregister: " + initials, "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                app.admin.unregisterDeveloper(initials);
                developerTableModel.removeRow(developerTable.getSelectedRow());
            }
        });

        registerProjectButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Write Project Name");
            app.admin.registerProject(name);
            projectTableModel.addRow(new Object[] {name, app.getProjectBy(name).getParticipants().size()});
        });

        unregisterProjectButton.addActionListener(e -> {
            String name = (String) projectTable.getValueAt(projectTable.getSelectedRow(), 0);
            if (JOptionPane.showConfirmDialog(adminPanel, "Unregister: " + name, "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                app.admin.unregisterProject(name);
                projectTableModel.removeRow(projectTable.getSelectedRow());
            }
        });

        assignDeveloperButton.addActionListener(e -> {
            String name = (String) projectTable.getValueAt(projectTable.getSelectedRow(),0);
            String initials = (String) JOptionPane.showInputDialog(adminPanel,
                    "Select Developer",
                    "Select Developer", //
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    app.getInitialsOf(app.getDevelopers()),
                    null);
            if (initials != null) {
                app.getProjectBy(name).assignParticipant(initials);
                projectTable.setValueAt(app.getProjectBy(name).getParticipants().size(),
                        projectTable.getSelectedRow(), 1);
            }
        });

        reassignDeveloperButton.addActionListener(e -> {
            String name = (String) projectTable.getValueAt(projectTable.getSelectedRow(),0);
            String initials = (String) JOptionPane.showInputDialog(adminPanel,
                    "Reassign Developer",
                    "Reassign developer",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    app.getProjectBy(name).getParticipants().toArray(),
                    null);
            app.getProjectBy(name).reassignParticipant(initials);
            projectTable.setValueAt(app.getProjectBy(name).getParticipants().size(),
                    projectTable.getSelectedRow(), 1);
        });

        assignManagerButton.addActionListener(e -> {
            String projectName = (String) projectTable.getValueAt(projectTable.getSelectedRow(),0);
            Project project = app.getProjectBy(projectName);
            if (project.getManager() == null) {
                String initials = (String) JOptionPane.showInputDialog(adminPanel,
                        "Select Manager",
                        "Select manager", //
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        project.getParticipants().toArray(),
                        null);

                if (initials != null) {
                    app.getDeveloperBy(initials).appointManager(projectName);
                    projectTable.setValueAt(initials, projectTable.getSelectedRow(), 2);
                }
            }
        });

        reassignManagerButton.addActionListener(e -> {
            String name = (String) projectTable.getValueAt(projectTable.getSelectedRow(),0);
            String initials = app.getProjectBy(name).getManager();
            if (JOptionPane.showConfirmDialog(adminPanel, "Reassign manager: " + initials, "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                app.getDeveloperBy(initials).reassignManagerTitle();
                projectTable.setValueAt(null, projectTable.getSelectedRow(), 2);
            }
        });
    }

    private void setup() {
        JFrame adminFrame = new JFrame("Project Planner - Administrator");
        adminFrame.setContentPane(adminPanel);
        adminFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        adminFrame.pack();
        adminFrame.setSize(1000, 500);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setVisible(true);
    }
}

