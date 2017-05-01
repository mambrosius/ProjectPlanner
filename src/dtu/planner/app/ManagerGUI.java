package dtu.planner.app;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

import static dtu.planner.app.ProjectPlanner.app;

public class ManagerGUI {

    private String manager;

    //private Project project;
    //private String projectName;
    //private Project project = new Project(null);

    private JFrame manFrame;

    private DeveloperGUI devGui;
    private AdministratorGUI adminGui;

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
                devGui.updateActivityTable();
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
            Double hours = Double.parseDouble(JOptionPane.showInputDialog(managerPanel, "type work hours as x.x"));
            getProject().getActivity(name).setEstimatedWorkHours(hours);
            updateActivityTable();
        });

        manFrame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                devGui.dispose();
                adminGui.dispose();
            }
        });

        activityTableModel.addTableModelListener(e -> {
            updateDeveloperTable();
            devGui.updateActivityTable();
            adminGui.updateProjectTable();
            adminGui.updateDeveloperTable();
        });
    }

    private void setup() {

        manFrame = new JFrame("Project Planner - Manager");
        manFrame.setContentPane(managerMainPanel);
        manFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        manFrame.pack();
        manFrame.setSize(1100, 600);
        manFrame.setLocationRelativeTo(null);
        manFrame.setVisible(true);

        for (String resp : app.getDeveloper(manager).getNamesOfResp())
            respBox.addItem(resp);
        respBox.setPopupVisible(false);

        setupDeveloperTap(manager);
        setupAdminTap();

        dateLabel.setText(app.date.toString());
        initialLabel.setText(this.manager);

        developerTable.setModel(developerTableModel);
        activityTable.setModel(activityTableModel);
        updateDeveloperTable();
        updateActivityTable();
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

    private void setupAdminTap() {
        adminGui = new AdministratorGUI(false);
        managerMenu.addTab("administrator", adminGui.getAdminFrame().getContentPane());
    }

    private void setupDeveloperTap(String initials) {
        devGui = new DeveloperGUI(initials, false);
        managerMenu.addTab("developer", devGui.getDevFrame().getContentPane());
    }
}
