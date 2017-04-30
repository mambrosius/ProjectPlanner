package dtu.planner.app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static dtu.planner.app.ProjectPlanner.app;

public class DeveloperGUI {

    private String initials;

    private JPanel devPanel;

    private JButton logActivityButton;

    private DefaultTableModel activityTableModel = new DefaultTableModel();
    private JTable activityTable;
    private JLabel dateLabel;
    private JLabel initialsLabel;

    DeveloperGUI(String initials) {

        this.initials = initials;

        setup();

        logActivityButton.addActionListener(e -> {
            String name = (String) activityTable.getValueAt(activityTable.getSelectedRow(), 0);
            Double hours = Double.parseDouble(JOptionPane.showInputDialog(devPanel, "log work hours"));
            app.getDeveloper(this.initials).logActivity(name, hours);
            updateActivityTable();
        });
    }

    private void setup() {

        JFrame frame = new JFrame("Project Planner - Developer");
        frame.setContentPane(devPanel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        dateLabel.setText(app.date.toString());
        initialsLabel.setText(this.initials);

        activityTable.setModel(activityTableModel);
        updateActivityTable();
    }

    private void updateActivityTable() {
        activityTableModel.setDataVector(
                Activity.getData(app.getDeveloper(initials).getActivities()), Activity.columnNames);
    }
}
