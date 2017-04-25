package dtu.planner.app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static dtu.planner.app.ProjectPlanner.app;

public class ManagerGUI {

    private String managerInitials;
    private String projectName;

    private JPanel managerPanel;

    private JButton addActivityButton;
    private JButton removeActivityButton;
    private JButton assignDeveloperButton;
    private JButton reassignDeveloperButton;

    private final Object[] developerColumnNames = new Object[]{"initials"};
    private static DefaultTableModel developerTableModel;
    private JTable developerTable;

    private static Object[] activityColumnNames = new Object[]{"activity", "participants", "estimated work hours"};
    private static DefaultTableModel activityTableModel = new DefaultTableModel(activityColumnNames, 0);
    private JTable activityTable;

    ManagerGUI(String initials) {
        this.managerInitials = initials;
        this.projectName = app.getDeveloperBy(managerInitials).asManager.getProjectName();

        setup();

        addActivityButton.addActionListener(e -> {
            String activityName = JOptionPane.showInputDialog("Name activity");
            if (app.getProjectBy(projectName).getNamesOfActivities().contains(activityName)) {
                JOptionPane.showMessageDialog(null, "\"" + activityName + "\" already exists");
            } else if (!activityName.isEmpty()) {
                app.getProjectBy(projectName).addActivity(activityName);
                activityTableModel.addRow(new Object[] {activityName});
            }
        });

        removeActivityButton.addActionListener(e -> {
            String activityName = (String) activityTable.getValueAt(activityTable.getSelectedRow(),0);
            if (JOptionPane.showConfirmDialog(managerPanel, "Remove: " + activityName, "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                app.getProjectBy(projectName).removeActivity(activityName);
                activityTableModel.removeRow(activityTable.getSelectedRow());
            }
        });

        assignDeveloperButton.addActionListener(e -> {
            String activityName = (String) activityTable.getValueAt(activityTable.getSelectedRow(), 0);
            String participantInitials = (String) JOptionPane.showInputDialog(managerPanel,
                    "Select Developer",
                    "Select Developer", //
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    app.getProjectBy(this.projectName).getParticipants().toArray(),
                    null);

            if (participantInitials != null) {
                app.getDeveloperBy(managerInitials).asManager.assignDeveloper(activityName, participantInitials);
                activityTable.setValueAt(
                        app.getProjectBy(projectName).getActivityBy(activityName).getParticipants().size(),
                        activityTable.getSelectedRow(), 1);
            }
        });

        reassignDeveloperButton.addActionListener(e -> {
            String activityName = (String) activityTable.getValueAt(activityTable.getSelectedRow(), 0);
            String participantInitials = (String) JOptionPane.showInputDialog(managerPanel,
                    "Select Developer",
                    "Select Developer", //
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    app.getProjectBy(this.projectName).getActivityBy(activityName).getParticipants().toArray(),
                    null);

            if (participantInitials != null) {
                if (JOptionPane.showConfirmDialog(managerPanel, "Reassign participant: " + participantInitials,
                        "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    app.getDeveloperBy(managerInitials).asManager.reassignDeveloper(activityName, participantInitials);
                    activityTable.setValueAt(
                            app.getProjectBy(projectName).getActivityBy(activityName).getParticipants().size(),
                            activityTable.getSelectedRow(), 1);
                }
            }

        });




    }

    private void setup() {

        JFrame devFrame = new JFrame("Project Planner - " + managerInitials);
        devFrame.setContentPane(managerPanel);
        devFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        devFrame.pack();
        devFrame.setSize(1000, 500);
        devFrame.setLocationRelativeTo(null);
        devFrame.setVisible(true);

        developerTableModel = new DefaultTableModel(
                app.getProjectBy(projectName).getDeveloperData(), developerColumnNames);
        developerTable.setModel(developerTableModel);

        activityTable.setModel(activityTableModel);
    }
}
