package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import model.RowLogbook;

import java.awt.event.*;

public class RowlyGUI extends JFrame {

    private JLabel title;
    private JPanel titleMenuPanel;
    private JPanel actionPanel;
    private JPanel newEntryPanel;
    private JPanel logbookTotalsPanel;
    private JPanel personalBestsPanel;
    private RowLogbook logbook;

    private String[] options = { "Add New Entry", "View Logbook Totals", "View Personal Bests" };

    public RowlyGUI() {
        super("Rowly Rowing Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setLayout(new FlowLayout());

        // Create the logbook and GUI for it
        logbook = new RowLogbook();

        // Create the top panel
        titleMenuPanel = new JPanel(new BorderLayout());
        title = new JLabel("Welcome to Rowly! Please select an option:");
        titleMenuPanel.add(title, BorderLayout.NORTH);

        // Create the selection of user actions
        final JComboBox userActions = new JComboBox(options);
        userActions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userChoice = (String) userActions.getSelectedItem();
                displayBasedOnSelection(userChoice);
            }
        });

        titleMenuPanel.add(userActions, BorderLayout.SOUTH);

        // Create the bottom panel options
        actionPanel = new JPanel();
        newEntryPanel = makeNewEntryPanel();
        logbookTotalsPanel = makeLogbookTotalsPanel();
        personalBestsPanel = makePersonalBestsPanel();
        actionPanel.add(newEntryPanel, BorderLayout.CENTER);
        actionPanel.add(logbookTotalsPanel, BorderLayout.CENTER);
        actionPanel.add(personalBestsPanel, BorderLayout.CENTER);

        blankPanel(actionPanel);

        add(titleMenuPanel);
        add(actionPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    // EFFECTS: Makes panel for user to input new row entry
    public JPanel makeNewEntryPanel() {
        return new JPanel(); //stub
    }
    

    // EFFECTS: Makes panel for user to view logbook totals
      public JPanel makeLogbookTotalsPanel() {
        return new JPanel(); //stub
    }

    // EFFECTS: Makes panel for user to view personal bests
      public JPanel makePersonalBestsPanel() {
        return new JPanel(); //stub
    }

     // EFFECTS: Makes all action panels invisible
     public void blankPanel(JPanel inputPanel) {
        //stub
    }


    // EFFECTS: Displays appropriate GUI based on userChoice
    public void displayBasedOnSelection(String userChoice) {
        if (userChoice.equals("View Logbook Totals"))
            displayLogbookTotals();
        else if (userChoice.equals("View Personal Bests"))
            displayPersonalBests();
        else
            displayAddEntry();
    }

    // EFFECTS: Displays area for user to input new row entry
    public void displayAddEntry() {
        // stub
    }

    // EFFECTS: Displays totals for all entries in logbook
    public void displayLogbookTotals() {
        // stub
    }

    // EFFECTS: Displays personal bests
    public void displayPersonalBests() {
        // stub
    }

    public static void main(String[] args) {
        new RowlyGUI();
    }
}
