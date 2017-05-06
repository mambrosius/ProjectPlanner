package dtu.planner.ui;

import dtu.planner.exceptions.CustomException;
import dtu.planner.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdministratorUi extends JFrame {

    private Administrator admin;

    private JPanel adminPanel;
    private JLabel dateLabel;

    private JButton registerProjectButton;
    private JButton unregisterProjectButton;
    private JButton registerDeveloperButton;
    private JButton unregisterDeveloperButton;
    private JButton assignDeveloperButton;
    private JButton unassignDeveloperButton;
    private JButton assignManagerButton;
    private JButton unassignManagerButton;

    private DefaultTableModel projectTableModel = new DefaultTableModel(Project.getColumnNames(), 0);
    private DefaultTableModel developerTableModel = new DefaultTableModel(Developer.getColumnNames(), 0);
    private JTable projectTable;
    private JTable developerTable;

    public AdministratorUi(ProjectPlanner projectPlanner) {

        this.admin = new Administrator(projectPlanner);
        setup();

        registerProjectButton.addActionListener(e -> {
            try {
                admin.registerProject(showInputDialog("name the project"));
                updateView();
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            }
        });

        unregisterProjectButton.addActionListener(e -> {
            try {
                admin.unregisterProject(getSelectedProject());
                updateView();
            } catch (CustomException ex) {
                showMessageDialog("select a project");
            }
        });

        registerDeveloperButton.addActionListener(e -> {
            try {
                admin.registerDeveloper(showInputDialog("type initials"));
                updateView();
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            }
        });

        unregisterDeveloperButton.addActionListener(e -> {
            try {
                admin.unregisterDeveloper(getSelectedDeveloper());
                updateView();
            } catch (ArrayIndexOutOfBoundsException ex) {
                showMessageDialog("select a developer");
            }
        });

        assignDeveloperButton.addActionListener(e -> {
            try {
                admin.assignDeveloper(getSelectedProject());
                updateView();
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            }
        });

        unassignDeveloperButton.addActionListener(e -> {
            try {
                admin.unassignDeveloper(getSelectedProject());
                updateView();
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            }
        });

        assignManagerButton.addActionListener(e -> {
            try {
                admin.assignManager(getSelectedProject());
                updateView();
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            }
        });

        unassignManagerButton.addActionListener(e -> {
            try {
                admin.unassignManager(getSelectedProject());
                updateView();
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

        dateLabel.setText(admin.getDateServer().toString());

        projectTable.setModel(projectTableModel);
        developerTable.setModel(developerTableModel);
        updateView();
    }

    public void updateView() {
        admin.updateProjectTable(projectTableModel);
        admin.updateDeveloperTable(developerTableModel);
    }

    private String getSelectedProject() throws CustomException {
        try {
            return (String) projectTable.getValueAt(projectTable.getSelectedRow(), 1);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new CustomException("select a project");
        }
    }

    private String getSelectedDeveloper() {
        return (String) developerTable.getValueAt(developerTable.getSelectedRow(), 0);
    }

    private String showInputDialog(String message) {
        return JOptionPane.showInputDialog(null, message, "").toLowerCase();
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}