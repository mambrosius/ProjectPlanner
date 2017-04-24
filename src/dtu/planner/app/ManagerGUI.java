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
    private JButton assignActivityButton;
    private JButton reassignActivityButton;

    private final Object[] developerColumnNames = new Object[]{"initials"};
    private static DefaultTableModel developerTableModel;
    private JTable developerTable;

    private static Object[] activityColumnNames = new Object[]{"activity", "participants"};
    private static DefaultTableModel activityTableModel = new DefaultTableModel(activityColumnNames, 0);
    private JTable activityTable;

    ManagerGUI(String initials) {
        this.managerInitials = initials;
        this.projectName = app.getDeveloperBy(managerInitials).asManager.getProjectName();

        setup();

        addActivityButton.addActionListener(e -> {
            String activityName = JOptionPane.showInputDialog("Name activity");
            app.getProjectBy(projectName).addActivity(activityName);
            activityTableModel.addRow(new Object[] {activityName});
        });

        removeActivityButton.addActionListener(e -> {
            String activityName = (String) activityTable.getValueAt(activityTable.getSelectedRow(),0);
            if (JOptionPane.showConfirmDialog(managerPanel, "Remove: " + activityName, "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                app.getProjectBy(projectName).removeActivity(activityName);
                activityTableModel.removeRow(activityTable.getSelectedRow());
            }
        });

        assignActivityButton.addActionListener(e -> {
            String activityName = (String) activityTable.getValueAt(activityTable.getSelectedRow(), 0);
            String participantInitials = (String) JOptionPane.showInputDialog(managerPanel,
                    "Select Developer",
                    "Select Developer", //
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    app.getProjectBy(this.projectName).getParticipants().toArray(),
                    null);

            if (participantInitials != null) {
                app.getDeveloperBy(managerInitials).asManager.assignActivity(activityName, participantInitials);
                activityTable.setValueAt(
                        app.getProjectBy(projectName).getActivityBy(activityName).getParticipants().size(),
                        activityTable.getSelectedRow(), 1);
            }
        });

        reassignActivityButton.addActionListener(e -> {

        });

        /*
        assignActivityButton.addActionListener(e -> {
            String name = (String) projectTable.getValueAt(projectTable.getSelectedRow(), 0);
            String managerInitials = (String) JOptionPane.showInputDialog(adminPanel,
                    "Select Developer",
                    "Select Developer", //
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    app.getInitialsOf(app.getDevelopers()),
                    null);

            if (managerInitials != null) {
                app.getProjectBy(name).assignParticipant(managerInitials);
                projectTable.setValueAt(app.getProjectBy(name).getParticipants().size(),
                        projectTable.getSelectedRow(), 1);
            }
        });

        reassignActivityButton.addActionListener(e -> {
            String name = (String) projectTable.getValueAt(projectTable.getSelectedRow(),0);
            String managerInitials = (String) JOptionPane.showInputDialog(adminPanel,
                    "Reassign Developer",
                    "Reassign developer",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    app.getProjectBy(name).getParticipants().toArray(),
                    null);

            app.getProjectBy(name).reassignParticipant(managerInitials);
            projectTable.setValueAt(app.getProjectBy(name).getParticipants().size(),
                    projectTable.getSelectedRow(), 1);
        });
        */
    }

    private void setup() {

        JFrame devFrame = new JFrame("Project Planner - " + managerInitials);
        devFrame.setContentPane(managerPanel);
        devFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        devFrame.pack();
        devFrame.setSize(1000, 500);
        devFrame.setLocationRelativeTo(null);
        devFrame.setVisible(true);

        developerTableModel =  new DefaultTableModel(
                app.getDeveloperBy(managerInitials).asManager.getParticipantsData(), developerColumnNames);
        developerTable.setModel(developerTableModel);

        activityTable.setModel(activityTableModel);
    }
}
