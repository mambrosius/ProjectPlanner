package dtu.planner.app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static dtu.planner.app.ProjectPlanner.*;

public class AdministratorGUI {

    private JPanel adminPanel;

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
    private JButton reassignDeveloperButton;
    private JButton reassignManagerButton;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;

    AdministratorGUI() {

        setup();

        registerDeveloperButton.addActionListener(e -> {
            String initials = JOptionPane.showInputDialog("Type Developer Initials").toLowerCase();
            if (app.getInitialsOfDevelopers().contains(initials)) {
                JOptionPane.showMessageDialog(null,  "\"" + initials + "\" already exists");
            } else if (!initials.isEmpty()) {
                app.admin.registerDeveloper(initials);
                developerTableModel.setDataVector(
                        app.getDeveloperDataOf(app.getDevelopers()), app.developerColumnNames);
            }
        });

        unregisterDeveloperButton.addActionListener(e -> {
            String initials = (String) developerTable.getValueAt(developerTable.getSelectedRow(), 0);
            if (JOptionPane.showConfirmDialog(adminPanel, "Unregister: " + initials, "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                app.admin.unregisterDeveloper(initials);
                developerTableModel.setDataVector(
                        app.getDeveloperDataOf(app.getDevelopers()), app.developerColumnNames);
            }
        });

        registerProjectButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Write Project Name").toLowerCase();

            if (app.getNamesOfProjects().contains(name)) {
                JOptionPane.showMessageDialog(null, "\"" + name + "\" already exists");
            } else if (!name.isEmpty()) {
                app.admin.registerProject(name);
                projectTableModel.setDataVector(app.getProjectDataOf(app.getProjects()), app.projectColumnNames);
            }
        });

        unregisterProjectButton.addActionListener(e -> {
            String name = (String) projectTable.getValueAt(projectTable.getSelectedRow(), 0);
            if (JOptionPane.showConfirmDialog(adminPanel, "Unregister: " + name, "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                app.admin.unregisterProject(name);
                projectTableModel.setDataVector(app.getProjectDataOf(app.getProjects()), app.projectColumnNames);
            }
        });

        assignDeveloperButton.addActionListener(e -> {
            String name = (String) projectTable.getValueAt(projectTable.getSelectedRow(),0);
            String initials = (String) JOptionPane.showInputDialog(adminPanel,
                    "Select Developer",
                    "Select Developer", //
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    app.getInitialsOfDevelopers().toArray(),
                    null);
            if (initials != null) {
                app.getProjectBy(name).assignParticipant(initials);
                projectTableModel.setDataVector(app.getProjectDataOf(app.getProjects()), app.projectColumnNames);
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
            projectTableModel.setDataVector(app.getProjectDataOf(app.getProjects()), app.projectColumnNames);
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
                    projectTableModel.setDataVector(app.getProjectDataOf(app.getProjects()), app.projectColumnNames);

                }
            }
        });

        reassignManagerButton.addActionListener(e -> {
            String name = (String) projectTable.getValueAt(projectTable.getSelectedRow(),0);
            String initials = app.getProjectBy(name).getManager();
            if (JOptionPane.showConfirmDialog(adminPanel, "Reassign manager: " + initials, "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                app.getDeveloperBy(initials).reassignManagerTitle();
                projectTableModel.setDataVector(app.getProjectDataOf(app.getProjects()), app.projectColumnNames);
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

        projectTableModel = new DefaultTableModel(
                app.getProjectDataOf(app.getProjects()), app.projectColumnNames);
        projectTable.setModel(projectTableModel);

        developerTableModel = new DefaultTableModel(
                app.getDeveloperDataOf(app.getDevelopers()), app.developerColumnNames);
        developerTable.setModel(developerTableModel);
    }
}

