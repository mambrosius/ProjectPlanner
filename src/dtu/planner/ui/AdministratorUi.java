package dtu.planner.ui;

import dtu.planner.exceptions.CustomException;
import dtu.planner.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdministratorUi extends JFrame {

    private Date date = new Date();

    private Administrator admin;

    private JPanel adminPanel;

    // project
    private DefaultTableModel projectTableModel;
    private JTable projectTable;

    private JButton registerProjectButton;
    private JButton unregisterProjectButton;

    // developer
    private DefaultTableModel developerTableModel;
    private JTable developerTable;

    private JLabel dateLabel;
    private JButton registerDeveloperButton;
    private JButton unregisterDeveloperButton;
    private JButton assignManagerButton;
    private JButton assignDeveloperButton;
    private JButton unassignDeveloperButton;
    private JButton unassignManagerButton;

    public AdministratorUi(ProjectPlanner projectPlanner) {

        this.admin = new Administrator(projectPlanner);

        setup();

        registerProjectButton.addActionListener(e -> {
            try {
                String name = showInputDialog("name the project");
                admin.registerProject(name);
                updateProjectTable(admin.getProjectData());
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            }
        });

        unregisterProjectButton.addActionListener(e -> {
            try {
                String name = getSelectedProject(true);
                admin.unregisterProject(name, showConfirmDialog("unregister " + name + "?"));
                updateProjectTable(admin.getProjectData());
            } catch (ArrayIndexOutOfBoundsException ex) {
                showMessageDialog("select a project");
            }
        });

        registerDeveloperButton.addActionListener(e -> {
            try {
                String initials = showInputDialog("type initials");
                admin.registerDeveloper(initials);
                updateDeveloperTable(admin.getDeveloperData());
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            }
        });

        unregisterDeveloperButton.addActionListener(e -> {
            try {
                String initials = getSelectedDeveloper();
                admin.unregisterDeveloper(initials, showConfirmDialog("unregister " + initials + "?"));
                updateDeveloperTable(admin.getDeveloperData());
            } catch (ArrayIndexOutOfBoundsException ex) {
                showMessageDialog("select a developer");
            }
        });

        assignDeveloperButton.addActionListener(e -> {
            try {
                String project = getSelectedProject(false);
                String initials = showInputBoxDialog("Select developer", admin.getAvailableDevelopers(project));
                if (admin.assignDeveloper(initials, project))
                    admin.assignManager(initials, project, showConfirmDialog("assign " + initials + " as manager?"));
                updateDeveloperTable(admin.getDeveloperData());
                updateProjectTable(admin.getProjectData());
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            }
        });

        unassignDeveloperButton.addActionListener(e -> {
            try {
                String project = getSelectedProject(false);
                String initials = showInputBoxDialog("Select developer", admin.getDevelopers(project));
                if (initials != null) {
                    admin.unassignDeveloper(initials, project, showConfirmDialog("unassign " + initials + "?"));
                    updateDeveloperTable(admin.getDeveloperData());
                    updateProjectTable(admin.getProjectData());
                }
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            }
        });

        assignManagerButton.addActionListener(e -> {
            String project = getSelectedProject(false);
            if (admin.hasManager(project))
                showMessageDialog("a manager is already assigned");
            else
                try {
                    String initials = showInputBoxDialog("Select developer", admin.getDevelopers(project));
                    if (initials != null) {
                        admin.assignManager(initials, project, showConfirmDialog("assign " + initials + " as manager?"));
                        updateDeveloperTable(admin.getDeveloperData());
                        updateProjectTable(admin.getProjectData());
                    }
                } catch (CustomException ex) {
                    showMessageDialog(ex.getMessage());
                }

        });

        unassignManagerButton.addActionListener(e -> {
            try {
                String project = getSelectedProject(true);
                if (admin.hasManager(project)) {
                    admin.unassignManager(project,
                            showConfirmDialog("unassign " + admin.getManagerName(project) + " as manager?"));
                    updateDeveloperTable(admin.getDeveloperData());
                    updateProjectTable(admin.getProjectData());
                } else {
                    showMessageDialog("no manager is assigned");
                }
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            }
        });
    }

    private void setup() {

        setContentPane(adminPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setSize(1000, 500);
        setLocationRelativeTo(null);

        projectTableModel = new DefaultTableModel(Project.getColumnNames(), 0);
        projectTable.setModel(projectTableModel);

        developerTableModel = new DefaultTableModel(Developer.getColumnNames(), 0);
        developerTable.setModel(developerTableModel);

        if (admin.haveProjects())
            updateProjectTable(admin.getProjectData());

        if (admin.haveDevelopers())
            updateDeveloperTable(admin.getDeveloperData());

        dateLabel.setText(date.toString());
    }

    private String getSelectedProject(Boolean disable) {
        if (disable)
            return (String) projectTable.getValueAt(projectTable.getSelectedRow(), 1);
        else
            try {
                return (String) projectTable.getValueAt(projectTable.getSelectedRow(), 1);
            } catch (ArrayIndexOutOfBoundsException ex) {
                showMessageDialog("select a project");
                return null;
            }
    }

    private String getSelectedDeveloper() {
        return (String) developerTable.getValueAt(developerTable.getSelectedRow(), 0);
    }

    private void updateProjectTable(Object[][] projectData) {
        projectTableModel.setDataVector(projectData, Project.getColumnNames());
    }

    private void updateDeveloperTable(Object[][] developerData) {
        developerTableModel.setDataVector(developerData, Developer.getColumnNames());
    }

    private int showConfirmDialog(String message) {
        return JOptionPane.showConfirmDialog(null, message, "Warning", JOptionPane.YES_NO_OPTION);
    }

    private String showInputDialog(String message) {
        return JOptionPane.showInputDialog(null, message, "").toLowerCase();
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private String showInputBoxDialog(String message, Object[] keys) {
        return (String) JOptionPane.showInputDialog(null, message, "Select",
                JOptionPane.INFORMATION_MESSAGE, null, keys, "");
    }
}