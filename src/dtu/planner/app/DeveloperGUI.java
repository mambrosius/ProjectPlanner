package dtu.planner.app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static dtu.planner.app.ProjectPlanner.app;

public class DeveloperGUI {

    private String devInitials;

    private JPanel devPanel;

    private JButton button1;
    private JButton button2;

    private static Object[] activityColumnNames = new Object[]{"activity"};
    private DefaultTableModel activityTableModel;
    private JTable activityTable;


    DeveloperGUI(String initials) {

        this.devInitials = initials;

        setup();

        button1.addActionListener(e -> {

        });

        button2.addActionListener(e -> {

        });
    }

    private void setup() {
        JFrame devFrame = new JFrame("Project Planner - " + devInitials);
        devFrame.setContentPane(devPanel);
        devFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        devFrame.pack();
        devFrame.setSize(1000, 500);
        devFrame.setLocationRelativeTo(null);
        devFrame.setVisible(true);

        activityTableModel =  new DefaultTableModel(
                app.getDeveloperBy(devInitials).getActivityData(), activityColumnNames);
        activityTable.setModel(activityTableModel);
    }
}
