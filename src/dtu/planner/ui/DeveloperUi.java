package dtu.planner.ui;

import dtu.planner.models.Date;
import dtu.planner.models.Developer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DeveloperUi extends JFrame {

    private Date date = new Date();

    private Developer dev;

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

    public DeveloperUi(Developer developer) {

        this.dev = developer;
        setup();

        /*

        logActivityButton.addActionListener(e -> {
            String name = (String) activityTable.getValueAt(activityTable.getSelectedRow(), 0);
            Double hours = Double.parseDouble(JOptionPane.showInputDialog(devPanel, "log work hours"));
            app.getDeveloper(this.initials).logActivity(name, hours);
            updateActivityTable();
        });
    }

    void updateActivityTable() {
        activityTableModel.setDataVector(
                Activity.getData(app.getDeveloper(initials).getActivities()), Activity.columnNames);
    }

    DefaultTableModel getActivityTableModel() {
        return activityTableModel;
    }
    */
    }

    private void setup() {

        setContentPane(devPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setSize(1000, 500);
        setLocationRelativeTo(null);

        dateLabel.setText(date.toString());
        initialsLabel.setText(dev.getInitials());

        activityTable.setModel(activityTableModel);
        //updateActivityTable();
    }
}
