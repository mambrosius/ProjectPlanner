package dtu.planner.app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private static DefaultTableModel developerTableModel = new DefaultTableModel();
    private JTable developerTable;

    private static DefaultTableModel activityTableModel = new DefaultTableModel();
    private JTable activityTable;

    private JComboBox<String> respBox;
    private JButton setEstimatedWorkHoursButton;
    private JLabel dateLabel;
    private JLabel initialLabel;
    private JPanel managerMainPanel;
    private JPanel devPanel;
    private JTabbedPane managerMenu;
    private JTable projectTable;
    private JTabbedPane tabbedPane1;
    private JButton assignManagerButton;
    private JButton unassignManagerButton;
    private JTabbedPane tabbedPane2;
    private JButton registerDeveloperButton;
    private JButton registerProjectButton;
    private JButton unregisterDeveloperButton;
    private JButton unregisterProjectButton;
    private JPanel adminPanel;
    private JButton asDeveloperButton;

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

        removeActivityButton.addActionListener(e -> {
            String name = (String) activityTable.getValueAt(activityTable.getSelectedRow(),0);
            if (JOptionPane.showConfirmDialog(managerPanel, "Remove: " + name, "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                getProject().removeActivity(name);
                updateActivityTable();
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

        setEstimatedWorkHoursButton.addActionListener(e -> {
            String name = (String) activityTable.getValueAt(activityTable.getSelectedRow(), 0);
            Double hours = Double.parseDouble(JOptionPane.showInputDialog(managerPanel, "Set work hours as x.x"));
            System.out.print(hours);
            getProject().getActivity(name).setEstimatedWorkHours(hours);
            updateActivityTable();
        });

        asDeveloperButton.addActionListener(e -> {

        });
    }

    private void setup() {

        JFrame devFrame = new JFrame("Project Planner - Manager");
        devFrame.setContentPane(managerMainPanel);
        devFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        devFrame.pack();
        devFrame.setSize(1000, 500);
        devFrame.setLocationRelativeTo(null);
        devFrame.setVisible(true);

        for (String resp : app.getDeveloper(manager).getNamesOfResp())
            respBox.addItem(resp);
        respBox.setPopupVisible(false);

        dateLabel.setText(app.date.toString());
        initialLabel.setText(this.manager);

        developerTable.setModel(developerTableModel);
        activityTable.setModel(activityTableModel);
        updateDeveloperTable();
        updateActivityTable();

        managerMenu.remove(devPanel);

        //devPanel.setVisible(false);
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
