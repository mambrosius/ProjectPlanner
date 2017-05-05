package dtu.planner.ui;

import dtu.planner.exceptions.CustomException;
import dtu.planner.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ManagerUi extends JFrame {

    private Date date = new Date();

    private Manager man;

    private JPanel managerMainPanel;
    private JTabbedPane managerMenu;

    private JLabel dateLabel;
    private JLabel initialLabel;

    private JButton resetButton;
    private JButton addActivityButton;
    private JButton removeActivityButton;
    private JButton assignDeveloperButton;
    private JButton unassignDeveloperButton;
    private JButton setEstimatedWorkHoursButton;

    private static DefaultTableModel developerTableModel;
    private JTable developerTable;

    private static DefaultTableModel activityTableModel;
    private JTable activityTable;

    private DefaultComboBoxModel<Object> respBoxModel;
    private JComboBox<Object> respBox;

    public ManagerUi(Manager manager, ProjectPlanner projectPlanner) {

        this.man = manager;
        this.man.setProjectPlanner(projectPlanner);

        setup();

        addActivityButton.addActionListener(e -> {
            try {
                String name = showInputDialog("Name activity");
                man.addActivity(name, getSelectedResp());
                updateActivityTable(man.getActivityData(getSelectedResp()));
            } catch (CustomException ex) {
                showMessageDialog(ex.getMessage());
            }
        });

        removeActivityButton.addActionListener(e -> {
            try {
                try {
                    String name = getSelectedActivity();
                    man.removeActivity(name, getSelectedResp(), showConfirmDialog("remove " + name + "?"));
                    updateActivityTable(man.getActivityData(getSelectedResp()));
                } catch (CustomException ex) {
                    showMessageDialog(ex.getMessage());
                }
            } catch (ArrayIndexOutOfBoundsException ex) {

            }
        });

        assignDeveloperButton.addActionListener(e -> {
            try {
                String name = getSelectedActivity();
                try {
                    String initials = showInputBoxDialog("Select developer",
                            man.getAvailableDevelopers(name, getSelectedResp()));
                    man.assignActivity(initials, name, getSelectedResp());
                    updateDeveloperTable(man.getDeveloperData(getSelectedResp()));
                    updateActivityTable(man.getActivityData(getSelectedResp()));
                } catch (Exception ex) {
                    showMessageDialog(ex.getMessage());
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                showMessageDialog("select an activity");
            }
        });

        unassignDeveloperButton.addActionListener((ActionEvent e) -> {
            try {
                try {
                    String resp = getSelectedResp();
                    String activity = getSelectedActivity();
                    String initials =
                            showInputBoxDialog("Select developer", man.getActivityDevelopers(activity, resp));
                    man.unassignDeveloper(initials, activity, resp);
                    updateDeveloperTable(man.getDeveloperData(getSelectedResp()));
                    updateActivityTable(man.getActivityData(getSelectedResp()));
                } catch (CustomException ex) {
                    showMessageDialog(ex.getMessage());
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                showMessageDialog("select an activity");
            }
        });

        setEstimatedWorkHoursButton.addActionListener(e -> {

            try {
                String name = getSelectedActivity();
                Double hours = Double.parseDouble(showInputDialog("type work hours as x.x"));
                man.setEstimatedHours(hours, name, getSelectedResp());
                updateActivityTable(man.getActivityData(getSelectedResp()));
            } catch (ArrayIndexOutOfBoundsException ex) {
                showMessageDialog("select an activity");
            }
        });


        managerMenu.addChangeListener(e -> {
            man.refreshTab(managerMenu.getSelectedIndex(), this);

            /*
            man.refreshTap(managerMenu.getTitleAt(managerMenu.getSelectedIndex()));
            managerMenu.getSelectedComponent()
                    managerMenu.ta
            /*
            String manSel = ;
            switch (manSel) {
                case "manager":
                    updateActivityTable(man.getActivityData(getSelectedResp()));
                    updateDeveloperTable(man.getDeveloperData(getSelectedResp()));
                    break;
                case "developer":

                    //devGui.updateActivityTable();
                    break;
                default:
                    adminGui.updateProjectTable();
                    adminGui.updateDeveloperTable();
                    break;
            }
            */
        });

        respBox.addActionListener(e -> {
            updateActivityTable(man.getActivityData(getSelectedResp()));
            updateDeveloperTable(man.getDeveloperData(getSelectedResp()));
        });

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                man.dispose();
            }
        });


    }

    private void setup() {
        setContentPane(managerMainPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setSize(1100, 600);
        setLocationRelativeTo(null);

        respBoxModel = new DefaultComboBoxModel<>(man.getRespMap().keySet().toArray());
        respBox.setModel(respBoxModel);

        /*for (String resp : man.getRespMap().keySet())
            respBoxModel.addElement(resp);*/

        managerMenu.addTab("developer", man.setupDeveloperTap());
        managerMenu.addTab("administrator", man.setupAdminTap());

        developerTableModel = new DefaultTableModel(Developer.getColumnNames(), 0);
        developerTable.setModel(developerTableModel);
        activityTableModel = new DefaultTableModel(Activity.getColumnNames(), 0);
        activityTable.setModel(activityTableModel);

        if (man.hasDeveloper(getSelectedResp()))
            updateDeveloperTable(man.getDeveloperData(getSelectedResp()));

        if (man.hasActivity(getSelectedResp()))
            updateActivityTable(man.getActivityData(getSelectedResp()));

        dateLabel.setText(date.toString());
        initialLabel.setText(man.getInitials());
    }

    public int showConfirmDialog(String message) {
        return JOptionPane.showConfirmDialog(null, message, "Warning", JOptionPane.YES_NO_OPTION);
    }

    public String showInputDialog(String message) {
        return JOptionPane.showInputDialog(message).toLowerCase();
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private String showInputBoxDialog(String message, Object[] keys) {
        return (String) JOptionPane.showInputDialog(null, message, "Select",
                JOptionPane.INFORMATION_MESSAGE, null, keys, "");
    }

    public void updateActivityTable(Object[][] activityData) {
        activityTableModel.setDataVector(activityData, Activity.getColumnNames());
    }

    public void updateDeveloperTable(Object[][] developerData) {
        developerTableModel.setDataVector(developerData, Developer.getColumnNames());
    }

    private String getSelectedResp() {
        return respBox.getSelectedItem().toString();
    }

    private String getSelectedActivity() {
        return (String) activityTable.getValueAt(activityTable.getSelectedRow(),0);
    }

    public void update() {
        updateDeveloperTable(man.getDeveloperData(getSelectedResp()));
        updateActivityTable(man.getActivityData(getSelectedResp()));

        respBoxModel = new DefaultComboBoxModel<>(man.getRespMap().keySet().toArray());
        respBox.setModel(respBoxModel);
    }
}
