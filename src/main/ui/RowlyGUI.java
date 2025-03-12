package ui;

import java.awt.*;
import javax.swing.*;

import model.RowEntry;
import model.RowLogbook;

import java.awt.event.*;

public class RowlyGUI extends JFrame {

    private JLabel title;
    private JPanel titleMenuPanel;
    private CardLayout actionPanelLayout;
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

        runSplashScreen();

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

        actionPanelLayout = new CardLayout();
        actionPanel = new JPanel(actionPanelLayout);
        makeRowEntryPanel();
        makeLogbookTotalsPanel();
        makePersonalBestsPanel();

        add(titleMenuPanel, BorderLayout.NORTH);
        add(actionPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: shows splash screen with rowly logo for 3 seconds
    public void runSplashScreen() {
        JWindow splashScreen = new JWindow();
        splashScreen.setSize(400, 500);
        splashScreen.setLocationRelativeTo(null);
        ImageIcon rowlyLogo = new ImageIcon("src/main/resources/rowly_logo3.png");
        JLabel splashImage = new JLabel(rowlyLogo);
        splashScreen.add(splashImage);
        splashScreen.setVisible(true);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }

        splashScreen.dispose();
    }

    // MODIFIES: this
    // EFFECTS: Makes panel for user to input new row entry
    @SuppressWarnings("methodlength")
    public void makeRowEntryPanel() {
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
        actionPanel.add(rowEntryPanel, "Add New Entry");

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
                JOptionPane.showMessageDialog(null, "Entry Added to Logbook! \n Summary: " + displayEntry(newEntry));
            }
        });
    }

    // EFFECTS: Generates written summary of rowEntry
    public String displayEntry(RowEntry rowEntry) {
        String date = rowEntry.getDate();
        int distance = rowEntry.getDistance();
        String time = rowEntry.getTime();
        int rate = rowEntry.getRate();

        return "Date: " + date + " // Distance: " + distance + " // Time: " + time + " // Rate: " + rate;
    }

    // MODIFIES: this
    // EFFECTS: Makes panel for user to view logbook totals
    public void makeLogbookTotalsPanel() {
        logbookTotalsPanel = new JPanel();
        logbookTotalsPanel.setLayout(new BoxLayout(logbookTotalsPanel, BoxLayout.Y_AXIS));
        actionPanel.add(logbookTotalsPanel, "View Logbook Totals");

    }

    // MODIFIES: this
    // EFFECTS: Makes panel for user to view personal bests
    public void makePersonalBestsPanel() {
        personalBestsPanel = new JPanel();
        personalBestsPanel.setLayout(new BoxLayout(personalBestsPanel, BoxLayout.Y_AXIS));
        actionPanel.add(personalBestsPanel, "View Personal Bests");
    }

    // EFFECTS: Displays appropriate GUI based on userChoice
    public void displayBasedOnSelection(String userChoice) {
        if (userChoice.equals("View Logbook Totals")) {
            updateLogbookTotals(); 
            actionPanelLayout.show(actionPanel, "View Logbook Totals");

        } else if (userChoice.equals("View Personal Bests")) {
            updatePersonalBests();  
            actionPanelLayout.show(actionPanel, "View Personal Bests");

        } else {
            actionPanelLayout.show(actionPanel, "Add New Entry");
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays totals for all entries in logbook
    public void updateLogbookTotals() {
        logbookTotalsPanel.removeAll();
        logbookTotalsPanel.add(new JLabel("Your total distance: " + logbook.findTotalDistance()));
        logbookTotalsPanel.add(new JLabel("Your total time: " + logbook.findTotalTime()));
        logbookTotalsPanel.revalidate();
        logbookTotalsPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: Displays personal bests
    public void updatePersonalBests() {
        personalBestsPanel.removeAll();
        personalBestsPanel.add(new JLabel("Your 2km personal best: " + logbook.find2kmPersonalBest()));
        personalBestsPanel.add(new JLabel("Your 6km personal best: " + logbook.find6kmPersonalBest()));
        personalBestsPanel.revalidate();
        personalBestsPanel.repaint();
    }

    public static void main(String[] args) {
        new RowlyGUI();
    }
}
