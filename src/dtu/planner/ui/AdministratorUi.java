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

    private JCheckBox asManagerCheckBox;
    private JComboBox registerTypeBox;
    private JComboBox unregisterTypeBox;
    private JComboBox<Object> projectAssignBox;
    private JComboBox<Object> projectUnassignBox;
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
                admin.unregister(unregisterTypeBox.getSelectedItem(), unregisterBox.getSelectedItem().toString());
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            } finally {
                updateView();
            }
        });

        assignButton.addActionListener(e -> {
            try {
                admin.assign(projectAssignBox.getSelectedItem(), devAssignBox.getSelectedItem(), asManagerCheckBox.isSelected());
                updateView();
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            } finally {
                updateView();
            }
        });

        unassignButton.addActionListener(e -> {
            try {
                admin.unassignDeveloper(devUnassignBox.getSelectedItem().toString(), projectUnassignBox.getSelectedItem().toString());
                updateView();
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            }
        });

        projectAssignBox.addActionListener(e -> {
            if (!admin.getProjects().isEmpty())
                if (asManagerCheckBox.isSelected()) {
                    devAssignBox.setModel(new DefaultComboBoxModel<>(admin.getDevelopers().keySet().toArray()));
                } else {
                    devAssignBox.setModel(new DefaultComboBoxModel<>(admin.getAvailableDevelopers(projectAssignBox.getSelectedItem())));
                }
        });

        projectUnassignBox.addActionListener(e -> {
            if (!admin.getProjects().isEmpty())
                devUnassignBox.setModel(new DefaultComboBoxModel<>(admin.getAssignedDevelopers(projectAssignBox.getSelectedItem().toString())));
        });

        registerTypeBox.addActionListener(e -> {
            if (registerTypeBox.getSelectedItem().equals("developer"))
                registerLabel.setText("initials");
            else
                registerLabel.setText("name");
        });

        unregisterTypeBox.addActionListener(e -> {
            if (unregisterTypeBox.getSelectedItem().equals("developer"))
                registerLabel.setText("initials");
            else
                registerLabel.setText("name");
            updateView();
        });

        asManagerCheckBox.addActionListener(e -> {
            if (!admin.getProjects().isEmpty())
                if (asManagerCheckBox.isSelected()) {
                    devAssignBox.setModel(new DefaultComboBoxModel<>(admin.getDevelopers().keySet().toArray()));
                } else {
                    devAssignBox.setModel(new DefaultComboBoxModel<>(admin.getAvailableDevelopers(projectAssignBox.getSelectedItem())));
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
        admin.updateProjectTable(projectTableModel);
        admin.updateDeveloperTable(developerTableModel);

        inputTextField.setText("");

        projectAssignBox.setModel(new DefaultComboBoxModel<>(admin.getProjects().keySet().toArray()));
        if (!admin.getProjects().isEmpty()) {
            devAssignBox.setModel(new DefaultComboBoxModel<>(admin.getAvailableDevelopers(projectAssignBox.getSelectedItem())));
            if (admin.getProjects().get(projectAssignBox.getSelectedItem().toString()).hasManager()) {
                asManagerCheckBox.setSelected(false);
                asManagerCheckBox.setVisible(false);
            } else
                asManagerCheckBox.setVisible(true);
        }
        projectUnassignBox.setModel(new DefaultComboBoxModel<>(admin.getProjects().keySet().toArray()));
        if (!admin.getProjects().isEmpty())
            devUnassignBox.setModel(new DefaultComboBoxModel<>(admin.getAssignedDevelopers(projectUnassignBox.getSelectedItem().toString())));

        if (unregisterTypeBox.getSelectedItem().equals("project"))
            unregisterBox.setModel(new DefaultComboBoxModel<>(admin.getProjects().keySet().toArray()));
        else
            unregisterBox.setModel(new DefaultComboBoxModel<>(admin.getDevelopers().keySet().toArray()));
    }
    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}