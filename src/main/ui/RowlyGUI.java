package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import model.RowEntry;
import model.RowLogbook;

import java.awt.event.*;

public class RowlyGUI extends JFrame {

    private JLabel title;
    private JPanel titleMenuPanel;
    private CardLayout actionPanelLayout;
    private JPanel actionPanel;
    private JPanel rowEntryPanel;
    private JPanel allEntriesPanel;
    private JPanel logbookTotalsPanel;
    private JPanel personalBestsPanel;

    JTextField dateField;
    JTextField distanceField;
    JTextField durationField;
    JTextField rateField;

    private RowLogbook logbook;
    private JButton addButton;

    private String[] options = { "Add Entry", "View Entries", "Logbook Totals", "Personal Bests" };

    public RowlyGUI() {
        super("Rowly Rowing Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        setLayout(new BorderLayout());

        runSplashScreen();
        logbook = new RowLogbook();
        makeTitleMenuPanel();

        final JComboBox userActions = new JComboBox(options);
        userActions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userChoice = (String) userActions.getSelectedItem();
                displayBasedOnSelection(userChoice);
            }
        });

        titleMenuPanel.add(userActions, BorderLayout.SOUTH);
        makeActionPanel();

        add(titleMenuPanel, BorderLayout.NORTH);
        add(actionPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: makes all components of title menu panel
    public void makeTitleMenuPanel() {
        titleMenuPanel = new JPanel(new BorderLayout());
        title = new JLabel("Welcome to Rowly! Please select an option:");
        titleMenuPanel.add(title, BorderLayout.NORTH);
    }

    // EFFECTS: make all components for action panel
    public void makeActionPanel() {
        actionPanelLayout = new CardLayout();
        actionPanel = new JPanel(actionPanelLayout);
        makeRowEntryPanel();
        makeAllEntriesPanel();
        makeLogbookTotalsPanel();
        makePersonalBestsPanel();
    }

    // EFFECTS: shows splash screen with rowly logo for 3 seconds
    public void runSplashScreen() {
        JWindow splashScreen = new JWindow();
        splashScreen.setSize(400, 500);
        splashScreen.setLocationRelativeTo(null);

        ImageIcon rowlyLogo = new ImageIcon("src/main/resources/rowly_logo3.png");
        Image scaledImage = rowlyLogo.getImage().getScaledInstance(400, 500, Image.SCALE_SMOOTH);
        JLabel splashImage = new JLabel();
        splashImage.setIcon(new ImageIcon(scaledImage));

        splashScreen.add(splashImage);
        splashScreen.setVisible(true);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted.");
        }

        splashScreen.dispose();
    }

    // MODIFIES: this
    // EFFECTS: Makes panel for user to input new row entry
    public void makeRowEntryPanel() {
        rowEntryPanel = new JPanel();
        rowEntryPanel.setLayout(new BoxLayout(rowEntryPanel, BoxLayout.Y_AXIS));

        dateField = new JTextField(15);
        distanceField = new JTextField(15);
        durationField = new JTextField(15);
        rateField = new JTextField(5);
        addButton = new JButton("Add Entry to Logbook");

        addComponentsToRowEntryPanel();

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

    // MODIFIES: this
    // EFFECTS: Adds all panels and labels to rowentrypanel
    public void addComponentsToRowEntryPanel() {
        rowEntryPanel.add(new JLabel("Enter Date (yyyy-mm-dd):"));
        rowEntryPanel.add(dateField);
        rowEntryPanel.add(new JLabel("Enter Distance (in meters):"));
        rowEntryPanel.add(distanceField);
        rowEntryPanel.add(new JLabel("Enter Duration (hh:mm:ss):"));
        rowEntryPanel.add(durationField);
        rowEntryPanel.add(new JLabel("Enter Rate:"));
        rowEntryPanel.add(rateField);
        rowEntryPanel.add(addButton);
        actionPanel.add(rowEntryPanel, "Add Entry");
    }

    // EFFECTS: Generates written summary of rowEntry
    public String displayEntry(RowEntry rowEntry) {
        String date = rowEntry.getDate();
        int distance = rowEntry.getDistance();
        String time = rowEntry.getTime();
        int rate = rowEntry.getRate();

        return "Date: " + date + " // Distance: " + distance + "m // Time: " + time + " // Rate: " + rate;
    }

    // // MODIFIES: this
    // // EFFECTS: Makes panel for user to view all logbook entries
    public void makeAllEntriesPanel() {
        allEntriesPanel = new JPanel();
        actionPanel.add(allEntriesPanel, "View Entries");
    }

    // MODIFIES: this
    // EFFECTS: Makes panel for user to view logbook totals
    public void makeLogbookTotalsPanel() {
        logbookTotalsPanel = new JPanel();
        logbookTotalsPanel.setLayout(new BoxLayout(logbookTotalsPanel, BoxLayout.Y_AXIS));
        actionPanel.add(logbookTotalsPanel, "Logbook Totals");

    }

    // MODIFIES: this
    // EFFECTS: Makes panel for user to view personal bests
    public void makePersonalBestsPanel() {
        personalBestsPanel = new JPanel();
        personalBestsPanel.setLayout(new GridLayout(4, 1, 5, 5));
        updatePersonalBests();
        actionPanel.add(personalBestsPanel, "Personal Bests");
    }

    // EFFECTS: Displays appropriate GUI based on userChoice
    public void displayBasedOnSelection(String userChoice) {
        if (userChoice.equals("Logbook Totals")) {
            updateLogbookTotals();
            actionPanelLayout.show(actionPanel, "Logbook Totals");

        } else if (userChoice.equals("View Entries")) {
            updateAllEntries();
            actionPanelLayout.show(actionPanel, "View Entries");

        } else if (userChoice.equals("Personal Bests")) {
            updatePersonalBests();
            actionPanelLayout.show(actionPanel, "Personal Bests");

        } else {
            actionPanelLayout.show(actionPanel, "Add Entry");
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
    // EFFECTS: Displays all entries in logbook
    public void updateAllEntries() {
        allEntriesPanel.removeAll();
        for (RowEntry r : logbook.getRowLogbook()) {
            allEntriesPanel.add(new JLabel(displayEntry(r)));
        }
        allEntriesPanel.revalidate();
        allEntriesPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: Displays personal bests
    public void updatePersonalBests() {
        personalBestsPanel.removeAll();
        JLabel best2km = new JLabel("🏅 Your 2km personal best 🏅", SwingConstants.CENTER);
        JLabel best2kmValue = new JLabel(logbook.find2kmPersonalBest(), SwingConstants.CENTER);
        JLabel best6km = new JLabel("🏅 Your 6km personal best 🏅", SwingConstants.CENTER);
        JLabel best6kmValue = new JLabel(logbook.find6kmPersonalBest(), SwingConstants.CENTER);
        Font pbFont = new Font("SansSerif", Font.BOLD, 18);
        best2km.setFont(pbFont);
        best6kmValue.setFont(pbFont);
        best2kmValue.setFont(pbFont);
        best6km.setFont(pbFont);
        personalBestsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2),
                "🏋️ Personal Bests 🏋️",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 20),
                Color.BLACK));

        personalBestsPanel.add(best2km);
        personalBestsPanel.add(best2kmValue);
        personalBestsPanel.add(best6km);
        personalBestsPanel.add(best6kmValue);
        personalBestsPanel.revalidate();
        personalBestsPanel.repaint();
    }

    public static void main(String[] args) {
        new RowlyGUI();
    }
}
