package dtu.planner.ui;

import dtu.planner.exceptions.CustomException;
import dtu.planner.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ManagerUi extends JFrame {

    private DateServer date = new DateServer();

    private Manager man;

    private JPanel manMainPanel;
    private JTabbedPane managerMenu;

    private JLabel dateLabel;
    private JLabel initialLabel;
    private JTextField inputTextField;
    private JTextField timeInputField;

    private JButton registerButton;
    private JButton unregisterButton;
    private JButton assignButton;
    private JButton unassignButton;
    private JButton setEstimatedWorkHoursButton;

    private static DefaultTableModel developerTableModel = new DefaultTableModel(Developer.getColumnNames(), 0);
    private static DefaultTableModel activityTableModel = new DefaultTableModel(Activity.getColumnNames(), 0);
    private JTable developerTable;
    private JTable activityTable;

    private JComboBox<Object> respBox;
    private JComboBox<Object> activityUnregisterBox;
    private JComboBox<Object> assignToBox;
    private JComboBox<Object> devAssignBox;
    private JComboBox<Object> devUnassignBox;
    private JComboBox<Object> unassignFromBox;
    private JComboBox<Object> activityTimeBox;

    public ManagerUi(Manager manager, ProjectPlanner projectPlanner) {

        this.man = manager;
        this.man.setProjectPlanner(projectPlanner);

        setup();

        registerButton.addActionListener(e -> {
            try {
                man.register(getResp(), inputTextField.getText().toLowerCase());
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            } finally {
                updateView();
            }
        });

        unregisterButton.addActionListener(e -> {
            try {
                man.unregister(getResp(), getSelectedActivity());
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            } finally {
                updateView();
            }
        });

        assignButton.addActionListener(e -> {
            try {
                man.assign(getResp(), getAssignTo(), getInitialsAssign());
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            } finally {
                updateDeveloperTable(man.getDeveloperData(getResp()));
                updateActivityTable(man.getActivityData(getResp()));
                updateDevUnassignBox();
                updateDevAssignBox();
            }
        });

        unassignButton.addActionListener(e -> {
            try {
                man.unassign(getResp(), getUnassignFrom(), getInitialsUnassign());
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            } finally {
                updateDeveloperTable(man.getDeveloperData(getResp()));
                updateActivityTable(man.getActivityData(getResp()));
                updateDevUnassignBox();
                updateDevAssignBox();
            }
        });

        setEstimatedWorkHoursButton.addActionListener(e -> {
            try {
                man.setEstimatedHours(timeInputField.getText(), getActivityTime(), getResp());
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            } finally {
                updateActivityTable(man.getActivityData(getResp()));
                timeInputField.setText("");
            }
        });

        // TODO: do cleanup below
        respBox.addActionListener(e -> updateView());
        assignToBox.addActionListener(e -> updateDevAssignBox());
        unassignFromBox.addActionListener(e -> updateDevUnassignBox());
        managerMenu.addChangeListener(e -> man.refreshTab(managerMenu.getSelectedIndex(), this));

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                man.dispose();
            }
        });
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private void updateActivityTable(Object[][] activityData) {
        activityTableModel.setDataVector(activityData, Activity.getColumnNames());
    }

    private void updateDeveloperTable(Object[][] developerData) {
        developerTableModel.setDataVector(developerData, Developer.getColumnNames());
    }

    private String getSelectedActivity() {
        return activityUnregisterBox.getSelectedItem().toString();
    }

    public void updateView() {

        updateRespBox();
        updateAssignToBox();
        updateUnassignFromBox();
        updateActivityUnregisterBox();
        updateDevAssignBox();
        updateDevUnassignBox();
        updateActivityTimeBox();

        if (man.hasDeveloper(getResp()))
            updateDeveloperTable(man.getDeveloperData(getResp()));
        if (man.hasActivity(getResp()))
            updateActivityTable(man.getActivityData(getResp()));

        inputTextField.setText("");
        timeInputField.setText("");
    }

    private void setup() {
        setContentPane(manMainPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setSize(1100, 600);
        setLocationRelativeTo(null);

        managerMenu.addTab("developer", man.setupDeveloperTap());
        managerMenu.addTab("administrator", man.setupAdminTap());

        dateLabel.setText(date.toString());
        initialLabel.setText(man.getInitials());

        developerTable.setModel(developerTableModel);
        activityTable.setModel(activityTableModel);

        updateView();
    }

    private String getResp() {
        return respBox.getSelectedItem().toString();
    }

    private String getAssignTo() {
        return assignToBox.getSelectedItem().toString();
    }

    private String getUnassignFrom() {
        return unassignFromBox.getSelectedItem().toString();
    }

    private String getInitialsAssign() {
        return devAssignBox.getSelectedItem().toString();
    }

    private String getInitialsUnassign() {
        return devUnassignBox.getSelectedItem().toString();
    }

    private String getActivityTime() {
        return activityTimeBox.getSelectedItem().toString();
    }

    private void updateRespBox() {
        respBox.setModel(new DefaultComboBoxModel<>(man.getResps()));
    }

    private void updateActivityUnregisterBox() {
        activityUnregisterBox.setModel(new DefaultComboBoxModel<>(man.getActivities(getResp())));
    }

    private void updateAssignToBox() {
        assignToBox.setModel(new DefaultComboBoxModel<>(man.getActivities(getResp())));
    }

    private void updateUnassignFromBox() {
        unassignFromBox.setModel(new DefaultComboBoxModel<>(man.getActivities(getResp())));
    }

    private void updateDevAssignBox() {
        try {
            if (man.hasActivity(getResp())) {
                devAssignBox.setModel(new DefaultComboBoxModel<>(man.getAvailableDevs(getAssignTo(), getResp())));
            } else
                devAssignBox.setModel(new DefaultComboBoxModel<>());
        } catch (CustomException ex) {
            showMessageDialog(ex.getMessage());
        }
    }

    private void updateDevUnassignBox() {
        try {
            if (man.hasActivity(getResp())) {
                devUnassignBox.setModel(new DefaultComboBoxModel<>(man.getAssignedDevs(getUnassignFrom(), getResp())));
            } else
                devUnassignBox.setModel(new DefaultComboBoxModel<>());
        } catch (CustomException ex) {
            showMessageDialog(ex.getMessage());
        }
    }

    private void updateActivityTimeBox() {
        activityTimeBox.setModel(new DefaultComboBoxModel<>(man.getActivities(getResp())));
    }
}