package ui;

import java.awt.*;
import javax.swing.*;

import model.RowEntry;
import model.RowLogbook;

import java.awt.event.*;

public class RowlyGUI extends JFrame {

    private JLabel title;
    private JPanel titleMenuPanel;
    private JPanel actionPanel;
    private JPanel rowEntryPanel;
    private JPanel logbookTotalsPanel;
    private JPanel personalBestsPanel;
    private RowLogbook logbook;
    private JButton addButton;

    private static String[] options = { "Add New Entry", "View Logbook Totals", "View Personal Bests" };

    @SuppressWarnings("methodlength")
    public RowlyGUI() {
        super("Rowly Rowing Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 500));
        setLayout(new BorderLayout());

        logbook = new RowLogbook();

        titleMenuPanel = new JPanel(new BorderLayout());
        title = new JLabel("Welcome to Rowly! Please select an option:");
        titleMenuPanel.add(title, BorderLayout.NORTH);

        final JComboBox userActions = new JComboBox(options);
        userActions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userChoice = (String) userActions.getSelectedItem();
                displayBasedOnSelection(userChoice);
            }
        });

        titleMenuPanel.add(userActions, BorderLayout.SOUTH);

        actionPanel = new JPanel();
        makeNewEntryPanel();
        makeLogbookTotalsPanel();
        makePersonalBestsPanel();
        blankPanel(actionPanel);

        add(titleMenuPanel, BorderLayout.NORTH);
        add(actionPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: Makes panel for user to input new row entry
    @SuppressWarnings("methodlength") 
    public void makeNewEntryPanel() {
        rowEntryPanel = new JPanel();
        rowEntryPanel.setLayout(new BoxLayout(rowEntryPanel, BoxLayout.Y_AXIS));

        JTextField dateField = new JTextField(15);
        JTextField distanceField = new JTextField(15);
        JTextField durationField = new JTextField(15);
        JTextField rateField = new JTextField(5);
        addButton = new JButton("Add Entry to Logbook");

        rowEntryPanel.add(new JLabel("Enter Date:"));
        rowEntryPanel.add(dateField);
        rowEntryPanel.add(new JLabel("Enter Distance:"));
        rowEntryPanel.add(distanceField);
        rowEntryPanel.add(new JLabel("Enter Duration:"));
        rowEntryPanel.add(durationField);
        rowEntryPanel.add(new JLabel("Enter Rate:"));
        rowEntryPanel.add(rateField);
        rowEntryPanel.add(addButton);
        actionPanel.add(rowEntryPanel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get user input from fields
                String date = dateField.getText();
                int distance = Integer.parseInt(distanceField.getText());
                String duration = durationField.getText();
                int rate = Integer.parseInt(rateField.getText());

                RowEntry newEntry = new RowEntry(date, distance, duration, rate);
                logbook.addEntry(newEntry);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Makes panel for user to view logbook totals
    public void makeLogbookTotalsPanel() {
        logbookTotalsPanel = new JPanel();
        logbookTotalsPanel.setLayout(new BoxLayout(logbookTotalsPanel, BoxLayout.Y_AXIS));

        int totalDistance = logbook.findTotalDistance();
        logbookTotalsPanel.add(new JLabel("Your total distance:" + totalDistance));

        String totalTime = logbook.findTotalTime();
        logbookTotalsPanel.add(new JLabel("Your total time:" + totalTime));

        actionPanel.add(logbookTotalsPanel);
    }

    // MODIFIES: this
    // EFFECTS: Makes panel for user to view personal bests
    public void makePersonalBestsPanel() {
        personalBestsPanel = new JPanel();
        personalBestsPanel.setLayout(new BoxLayout(personalBestsPanel, BoxLayout.Y_AXIS));

        String personalBest2km = logbook.find2kmPersonalBest();
        personalBestsPanel.add(new JLabel("Your current 2km personal best:" + personalBest2km));

        String personalBest6km = logbook.find6kmPersonalBest();
        personalBestsPanel.add(new JLabel("Your total time:" + personalBest6km));

        actionPanel.add(personalBestsPanel);
    }

    // MODIFIES: this
    // EFFECTS: Makes all action panels invisible
    public void blankPanel(JPanel inputPanel) {
        rowEntryPanel.setVisible(false);
        logbookTotalsPanel.setVisible(false);
        personalBestsPanel.setVisible(false);
    }

    // EFFECTS: Displays appropriate GUI based on userChoice
    public void displayBasedOnSelection(String userChoice) {
        if (userChoice.equals("View Logbook Totals")) {
            displayLogbookTotals();
        } else if (userChoice.equals("View Personal Bests")) {
            displayPersonalBests();
        } else {
            displayAddEntry();
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays area for user to input new row entry
    public void displayAddEntry() {
        logbookTotalsPanel.setVisible(false);
        personalBestsPanel.setVisible(false);
        rowEntryPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Displays totals for all entries in logbook
    public void displayLogbookTotals() {
        rowEntryPanel.setVisible(false);
        personalBestsPanel.setVisible(false);
        logbookTotalsPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Displays personal bests
    public void displayPersonalBests() {
        rowEntryPanel.setVisible(false);
        logbookTotalsPanel.setVisible(false);
        personalBestsPanel.setVisible(true);
    }

    public static void main(String[] args) {
        new RowlyGUI();
    }
}
