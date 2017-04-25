package dtu.planner.app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static dtu.planner.app.ProjectPlanner.app;

public class ManagerGUI {

    private String manager;

    //private Project project;
    //private String projectName;
    //private Project project = new Project(null);

    private JPanel managerPanel;

    private JButton addActivityButton;
    private JButton removeActivityButton;
    private JButton assignDeveloperButton;
    private JButton unassignDeveloperButton;

    private static DefaultTableModel developerTableModel;
    private JTable developerTable;

    private static DefaultTableModel activityTableModel;
    private JTable activityTable;

    //private static DefaultComboBoxModel respComboBoxModel;
    private JComboBox<String> respBox;

    ManagerGUI(String manager) {

        this.manager = manager;

        setup();

        addActivityButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Name activity").toLowerCase();
            if (!name.isEmpty())
                if (getProject().addActivity(name))
                    updateActivityTable();
                else
                    JOptionPane.showMessageDialog(managerPanel, "\"" + name + "\" already exists");
        });
            /*
            if (app.getProject(projectName).getNamesOfActivities().contains(activityName)) {

            } else if (!activityName.isEmpty()) {
                app.getProject(projectName).assignActivity(activityName);
                activityTableModel.addRow(new Object[] {activityName});
            }*/

        removeActivityButton.addActionListener(e -> {
            String name = (String) activityTable.getValueAt(activityTable.getSelectedRow(),0);
            if (JOptionPane.showConfirmDialog(managerPanel, "Remove: " + name, "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                getProject().removeActivity(name);
                updateActivityTable();
                //activityTableModel.removeRow(activityTable.getSelectedRow());
            }
        });

        assignDeveloperButton.addActionListener(e -> {
            String name = (String) activityTable.getValueAt(activityTable.getSelectedRow(), 0);
            String initials = (String) JOptionPane.showInputDialog(managerPanel,
                    "Select Developer",
                    "Select Developer", //
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    app.getInitialsOfDevelopers(getProject().getDevelopers()).toArray(),
                    null);

            if (initials != null) {
                getProject().getManager().assignActivity(initials, name, getProject().getName());
                updateActivityTable();
                /*
                app.getDeveloper(managerInitials).asManager.assignActivity(activityName, participantInitials);
                activityTable.setValueAt(
                        app.getProject(projectName).getActivity(activityName).getDevelopers().size(),
                        activityTable.getSelectedRow(), 1);
                        */
            }
        });

        unassignDeveloperButton.addActionListener(e -> {
            String name = (String) activityTable.getValueAt(activityTable.getSelectedRow(), 0);
            String initials = (String) JOptionPane.showInputDialog(managerPanel,
                    "Select Developer",
                    "Select Developer", //
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    app.getInitialsOfDevelopers(getProject().getActivity(name).getDevelopers()).toArray(),
                    null);
            if (initials != null) {
                if (JOptionPane.showConfirmDialog(managerPanel, "Unassign developer: " + initials,
                        "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    getProject().getManager().unassignActivity(initials, name, getProject().getName());
                    updateActivityTable();
                }
            }
        });

        respBox.addActionListener(e -> {
            updateActivityTable();
            updateDeveloperTable();
        });
    }

    private void setup() {

        JFrame devFrame = new JFrame("Project Planner");
        devFrame.setContentPane(managerPanel);
        devFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        devFrame.pack();
        devFrame.setSize(1000, 500);
        devFrame.setLocationRelativeTo(null);
        devFrame.setVisible(true);

        for (String resp : app.getDeveloper(manager).getNamesOfResp())
            respBox.addItem(resp);
        respBox.setPopupVisible(false);

        developerTableModel = new DefaultTableModel(
                Developer.getData(getProject().getDevelopers()), Developer.columnNames);
        developerTable.setModel(developerTableModel);

        activityTableModel = new DefaultTableModel(
                Activity.getData(getProject().getActivities()) , Activity.columnNames);
        activityTable.setModel(activityTableModel);
    }

    private Project getProject() {
        return app.getProject(respBox.getSelectedItem().toString());
    }

    private void updateDeveloperTable() {
        developerTableModel.setDataVector(Developer.getData(getProject().getDevelopers()), Developer.columnNames);
    }

    private void updateActivityTable() {
        activityTableModel.setDataVector(Activity.getData(getProject().getActivities()), Activity.columnNames);
    }
}
