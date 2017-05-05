package dtu.planner.ui;

import dtu.planner.models.Absence;
import dtu.planner.models.Activity;
import dtu.planner.models.Date;
import dtu.planner.models.Developer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DeveloperUi extends JFrame {

    private Date date = new Date();

    private Developer dev;

    private JPanel devPanel;

    private JButton logActivityButton;
    private DefaultTableModel activityTableModel;
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
    private JComboBox<Absence> absenceBox;
    private JButton acceptButton1;
    private JButton denyButton1;

    public DeveloperUi(Developer developer) {

        this.dev = developer;
        setup();

        logActivityButton.addActionListener(e -> {
            String name = (String) activityTable.getValueAt(activityTable.getSelectedRow(), 0);
            Double hours = Double.parseDouble(JOptionPane.showInputDialog(devPanel, "log work hours"));
            dev.logActivity(name, hours);
            updateActivityTable(dev.getActivityData());
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

        DefaultComboBoxModel<Absence> absenceModel = new DefaultComboBoxModel<>(Absence.values());
        absenceBox.setModel(absenceModel);

        if (dev.hasActivity())
            updateActivityTable(dev.getActivityData());
    }

    private void updateActivityTable(Object[][] activityData) {
        activityTableModel.setDataVector(activityData, Activity.getColumnNames());
    }

    public void update() {
        updateActivityTable(dev.getActivityData());
    }
}
