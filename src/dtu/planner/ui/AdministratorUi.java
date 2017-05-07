package dtu.planner.ui;

import dtu.planner.exceptions.CustomException;
import dtu.planner.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdministratorUi extends JFrame {

    private Administrator admin;

    private JPanel adminPanel;
    private JPanel registerPanel;
    private JLabel dateLabel;
    private JLabel registerLabel;
    private JLabel unregisterLabel;
    private JTextField inputTextField;

    private DefaultTableModel projectTableModel = new DefaultTableModel(Project.getColumnNames(), 0);
    private DefaultTableModel developerTableModel = new DefaultTableModel(Developer.getColumnNames(), 0);
    private JTable projectTable;
    private JTable developerTable;

    private JCheckBox asManCheck;
    private JComboBox registerTypeBox;
    private JComboBox unregisterTypeBox;
    private JComboBox<Object> assignToBox;
    private JComboBox<Object> unassignFromBox;
    private JComboBox<Object> devAssignBox;
    private JComboBox<Object> devUnassignBox;
    private JComboBox<Object> unregisterBox;

    private JButton assignButton;
    private JButton unassignButton;
    private JButton registerButton;
    private JButton unregisterButton;

    public AdministratorUi(ProjectPlanner projectPlanner) {

        this.admin = new Administrator(projectPlanner);
        setup();

        registerButton.addActionListener(e -> {
            try {
                admin.register(registerTypeBox.getSelectedItem(), inputTextField.getText().toLowerCase());
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            } finally {
                updateView();
            }
        });

        unregisterButton.addActionListener(e -> {
            try {
                admin.unregister(unregisterTypeBox.getSelectedItem(), unregisterBox.getSelectedItem());
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            } finally {
                updateView();
            }
        });

        assignButton.addActionListener(e -> {
            try {
                admin.assign(getAssignTo(), getDevAssign(), asManCheck.isSelected());
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            } finally {
                updateView();
            }
        });

        unassignButton.addActionListener(e -> {
            admin.unassign(getUnassignFrom(), devUnassignBox.getSelectedItem().toString());
            updateView();
        });

        assignToBox.addActionListener(e -> {
            updateManCheck();
            asManCheck.revalidate();
        });

        registerTypeBox.addActionListener(e -> {
            if (registerTypeBox.getSelectedItem().equals("developer"))
                registerLabel.setText("initials");
            else
                registerLabel.setText("name");
            updateView();
        });

        unregisterTypeBox.addActionListener(e -> {
            if (unregisterTypeBox.getSelectedItem().equals("project")) {
                unregisterBox.setModel(new DefaultComboBoxModel<>(admin.getProjects()));
                registerLabel.setText("name");
            } else {
                unregisterBox.setModel(new DefaultComboBoxModel<>(admin.getDevelopers()));
                registerLabel.setText("initials");
            }
        });

        unassignFromBox.addActionListener(e -> {
            if (admin.getProjects().length > 0)
                devUnassignBox.setModel(new DefaultComboBoxModel<>(admin.getAssignedDevelopers(getUnassignFrom())));
        });

        asManCheck.addActionListener(e -> {
            if (admin.getProjects().length > 0)
                if (asManCheck.isSelected()) {
                    devAssignBox.setModel(new DefaultComboBoxModel<>(admin.getDevelopers()));
                } else {
                    devAssignBox.setModel(new DefaultComboBoxModel<>(admin.getAvailableDevelopers(getAssignTo())));
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
        registerLabel.setText("name");
        unregisterLabel.setText("name");

        projectTable.setModel(projectTableModel);
        developerTable.setModel(developerTableModel);
        registerPanel.getRootPane().setDefaultButton(registerButton);

        updateView();
    }

    public void updateView() {
        inputTextField.setText("");
        admin.updateProjectTable(projectTableModel);
        admin.updateDeveloperTable(developerTableModel);

        unregisterTypeBox.revalidate();

        assignToBox.setModel(new DefaultComboBoxModel<>(admin.getProjects()));
        unassignFromBox.setModel(new DefaultComboBoxModel<>(admin.getProjects()));

        if (admin.getProjects().length > 0) {
            updateManCheck();
            if (asManCheck.isSelected())
                devAssignBox.setModel(new DefaultComboBoxModel<>(admin.getDevelopers()));
            else
                devAssignBox.setModel(new DefaultComboBoxModel<>(admin.getAvailableDevelopers(getAssignTo())));
            devUnassignBox.setModel(new DefaultComboBoxModel<>(admin.getAssignedDevelopers(getUnassignFrom())));
        }

        if (unregisterTypeBox.getSelectedItem().equals("project")) {
            unregisterBox.setModel(new DefaultComboBoxModel<>(admin.getProjects()));
            registerLabel.setText("name");
        } else {
            unregisterBox.setModel(new DefaultComboBoxModel<>(admin.getDevelopers()));
            registerLabel.setText("initials");
        }
    }

    private void updateManCheck() {
        Boolean hasManager = admin.getProject(getAssignTo()).hasManager();
        asManCheck.setVisible(!hasManager);
        if (hasManager)
            asManCheck.setSelected(false);
    }

    private String getDevAssign() {
        return devAssignBox.getSelectedItem().toString();
    }

    private String getAssignTo() {
        return assignToBox.getSelectedItem().toString();
    }

    private String getUnassignFrom() {
        return unassignFromBox.getSelectedItem().toString();
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}