package dtu.planner.ui;

import dtu.planner.exceptions.CustomException;
import dtu.planner.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeveloperUi extends JFrame {

    private DateServer date = new DateServer();

    private ProjectPlanner temp;
    private Developer dev;

    private JPanel devPanel;

    private JButton logActivityButton;
    private DefaultTableModel activityTableModel;
    private JTable activityTable;
    private JLabel dateLabel;
    private JLabel initialsLabel;
    private JButton sendRequestButton;
    private JButton registerButton;
    private JComboBox<Object> activityReqBox;

    private JComboBox<Object> devReqBox;
    private DefaultComboBoxModel<Object> devReqModel;

    private JComboBox<Activity> replyBox;
    private JComboBox<Absence> absenceBox;
    private JButton acceptButton1;
    private JButton denyButton1;
    private JLabel replyLabel;
    private JTextField inputTimeTextField;
    private JComboBox<Object> logActivityBox;

    public DeveloperUi(Developer developer, ProjectPlanner model) {

        this.temp = model;
        this.dev = developer;
        this.dev.setProjectPlanner(model);
        setup();

        logActivityButton.addActionListener(e -> {
            try {
                //String name = (String) activityTable.getValueAt(activityTable.getSelectedRow(), 0);
                dev.logActivity(getLogActivity(), inputTimeTextField.getText());
                updateActivityTable(dev.getActivityData());
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            }
        });

        sendRequestButton.addActionListener(e -> {
            try {
                dev.seekAssistance(getSelectedActivity(), getSelectedDev());
                showMessageDialog("request sent");
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            }
        });

        activityReqBox.addActionListener(e -> {
            if (dev.hasActivity()) {
                devReqModel = new DefaultComboBoxModel<>(dev.getAvailableDevelopers(getSelectedActivity()));
                devReqBox.setModel(devReqModel);
            }
        });

        acceptButton1.addActionListener(e -> {
            dev.replyReq(getSelectedReq(), true);
            update();
        });

        denyButton1.addActionListener(e -> {
            dev.replyReq(getSelectedReq(), false);
            update();
        });

    }

    private void setup() {

        setContentPane(devPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setSize(1000, 500);
        setLocationRelativeTo(null);

        dateLabel.setText(date.toString());
        initialsLabel.setText(dev.getInitials());

        activityTableModel = new DefaultTableModel(Activity.getColumnNames(), 0);
        activityTable.setModel(activityTableModel);

        absenceBox.setModel(new DefaultComboBoxModel<>(Absence.values()));

        update();
    }

    private void updateActivityTable(Object[][] activityData) {
        activityTableModel.setDataVector(activityData, Activity.getColumnNames());
    }

    public void update() {

        updateActivityTable(dev.getActivityData());

        inputTimeTextField.setText("");

        DefaultComboBoxModel<Object> activityReqModel = new DefaultComboBoxModel<>(dev.getActivities());
        activityReqBox.setModel(activityReqModel);

        if (dev.hasActivity()) {
            DefaultComboBoxModel<Object> devReqModel =
                    new DefaultComboBoxModel<>(dev.getAvailableDevelopers(getSelectedActivity()));
            devReqBox.setModel(devReqModel);
        }

        logActivityBox.setModel(new DefaultComboBoxModel<>(dev.getActivities()));


        DefaultComboBoxModel<Activity> replyBoxModel = new DefaultComboBoxModel<>();

        for (Activity activity : dev.getReqMap().values()) {
            System.out.print(activity.getName());
            replyBoxModel.addElement(activity);
        }

        if (dev.getReqMap().isEmpty()) {
            replyBox.setVisible(false);
            acceptButton1.setVisible(false);
            denyButton1.setVisible(false);
            replyLabel.setText("no requests");
        } else {
            replyBox.setVisible(true);
            acceptButton1.setVisible(true);
            denyButton1.setVisible(true);
            replyBox.setModel(replyBoxModel);
            replyLabel.setText("request from " + getSelectedReq().getProjectName());
        }
    }

    private String getSelectedActivity() {
        if (activityReqBox.getSelectedItem() == null)
            return "";
        else
            return activityReqBox.getSelectedItem().toString();
    }

    private String getLogActivity() {
        return logActivityBox.getSelectedItem().toString();
    }


    private String getSelectedDev() {
        if (devReqBox.getSelectedItem() == null)
            return "";
        else
            return devReqBox.getSelectedItem().toString();

    }

    private Activity getSelectedReq() {
        return (Activity) replyBox.getSelectedItem();
    }

    private Activity getActivity(String activity, String project) {
        return getProject(project).getActivityMap().get(activity);
    }

    private Project getProject(String project) {
        return temp.getProjectMap().get(project);
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}
