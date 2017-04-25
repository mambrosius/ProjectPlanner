package dtu.planner.app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static dtu.planner.app.ProjectPlanner.app;

public class DeveloperGUI {

    private String initials;

    private JPanel devPanel;

    private JButton button1;

    private static Object[] activityColumnNames = new Object[]{"activity"};
    private DefaultTableModel activityTableModel;
    private JTable activityTable;

    DeveloperGUI(String initials) {

        this.initials = initials;

        setup();

        button1.addActionListener(e -> {

        });
    }

    private void setup() {

        JFrame frame = new JFrame("Project Planner - " + initials);
        frame.setContentPane(devPanel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        activityTableModel =  new DefaultTableModel(
                Activity.getData(app.getDeveloper(initials).getActivities()), activityColumnNames);
        activityTable.setModel(activityTableModel);
    }
}
