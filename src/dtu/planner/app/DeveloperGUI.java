package dtu.planner.app;

import com.sun.deploy.panel.JreTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static dtu.planner.app.ProjectPlanner.app;

public class DeveloperGUI {

    private String initials;

    private JFrame devFrame;
    private JPanel devPanel;

    private JButton logActivityButton;

    private DefaultTableModel activityTableModel = new DefaultTableModel();
    private JTable activityTable;
    private JLabel dateLabel;
    private JLabel initialsLabel;
    private JButton sendButton;
    private JButton registerButton;
    private JTabbedPane tabbedPane1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTabbedPane tabbedPane2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JButton acceptButton1;
    private JButton denyButton1;

    DeveloperGUI(String initials, Boolean visible) {

        this.initials = initials;

        setup();

        devFrame.setVisible(visible);


        logActivityButton.addActionListener(e -> {
            String name = (String) activityTable.getValueAt(activityTable.getSelectedRow(), 0);
            Double hours = Double.parseDouble(JOptionPane.showInputDialog(devPanel, "log work hours"));
            app.getDeveloper(this.initials).logActivity(name, hours);
            updateActivityTable();
        });
    }

    private void setup() {

        devFrame = new JFrame("Project Planner - Developer");
        devFrame.setContentPane(devPanel);
        devFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        devFrame.pack();
        devFrame.setSize(1000, 500);
        devFrame.setLocationRelativeTo(null);

        dateLabel.setText(app.date.toString());
        initialsLabel.setText(this.initials);

        activityTable.setModel(activityTableModel);
        updateActivityTable();
    }

    void updateActivityTable() {
        activityTableModel.setDataVector(
                Activity.getData(app.getDeveloper(initials).getActivities()), Activity.columnNames);
    }

    JFrame getDevFrame() {
        return devFrame;
    }

    void dispose() {
        devFrame.dispose();
    }

    DefaultTableModel getActivityTableModel() {
        return activityTableModel;
    }
}
