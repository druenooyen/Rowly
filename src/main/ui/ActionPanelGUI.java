package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import model.RowEntry;
import model.RowLogbook;

public class ActionPanelGUI extends JPanel {
    private RowLogbook logbook;
    private JPanel rowEntryPanel;
    private JPanel allEntriesPanel;
    private JPanel logbookTotalsPanel;
    private JPanel personalBestsPanel;

    private JButton addButton;
    private ActionListener actionListener;
    private JTextField dateField;
    private JTextField distanceField;
    private JTextField durationField;
    private JTextField rateField;

    private CardLayout actionPanelLayout;

    public ActionPanelGUI(RowLogbook logbook) {
        this.logbook = logbook;
        actionPanelLayout = new CardLayout();
        this.setLayout(actionPanelLayout);
       
        makeRowEntryPanel();
        makeAllEntriesPanel();
        makeLogbookTotalsPanel();
        makePersonalBestsPanel();
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

        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                int distance = Integer.parseInt(distanceField.getText());
                String duration = durationField.getText();
                int rate = Integer.parseInt(rateField.getText());

                RowEntry newEntry = new RowEntry(date, distance, duration, rate);
                logbook.addEntry(newEntry);
                JOptionPane.showMessageDialog(null, "Entry Added to Logbook! \n Summary: " + displayEntry(newEntry));
            }
        };
        addButton.addActionListener(actionListener);
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
        this.add(rowEntryPanel, "Add Entry");
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
        this.add(allEntriesPanel, "View Entries");
    }

    // MODIFIES: this
    // EFFECTS: Makes panel for user to view logbook totals
    public void makeLogbookTotalsPanel() {
        logbookTotalsPanel = new JPanel();
        logbookTotalsPanel.setLayout(new GridLayout(4, 1, 5, 5));
        this.add(logbookTotalsPanel, "Logbook Totals");

    }

    // MODIFIES: this
    // EFFECTS: Makes panel for user to view personal bests
    public void makePersonalBestsPanel() {
        personalBestsPanel = new JPanel();
        personalBestsPanel.setLayout(new GridLayout(4, 1, 5, 5));
        updatePersonalBests();
        this.add(personalBestsPanel, "Personal Bests");
    }


    // EFFECTS: Displays appropriate GUI based on userChoice
    public void displayBasedOnSelection(String userChoice) {
        if (userChoice.equals("Logbook Totals")) {
            updateLogbookTotals();
            actionPanelLayout.show(this, "Logbook Totals");

        } else if (userChoice.equals("View Entries")) {
            updateAllEntries();
            actionPanelLayout.show(this, "View Entries");

        } else if (userChoice.equals("Personal Bests")) {
            updatePersonalBests();
            actionPanelLayout.show(this, "Personal Bests");
        } else {
            actionPanelLayout.show(this, "Add Entry");
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays totals for all entries in logbook
    public void updateLogbookTotals() {
        logbookTotalsPanel.removeAll();
        JLabel totalDistance = new JLabel("Your total distance:", SwingConstants.CENTER);
        JLabel totalDistanceValue = new JLabel(" " + logbook.findTotalDistance(), SwingConstants.CENTER);
        JLabel totalTime = new JLabel("Your total time:", SwingConstants.CENTER);
        JLabel totalTimeValue = new JLabel(" " + logbook.findTotalTime(), SwingConstants.CENTER);
        Font pbFont = new Font("SansSerif", Font.BOLD, 18);
        totalDistance.setFont(pbFont);
        totalDistanceValue.setFont(pbFont);
        totalTime.setFont(pbFont);
        totalTimeValue.setFont(pbFont);

        logbookTotalsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                "🏋️ Your Totals 🏋️",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 20),
                Color.BLACK));

        logbookTotalsPanel.add(totalDistance);
        logbookTotalsPanel.add(totalDistanceValue);
        logbookTotalsPanel.add(totalTime);
        logbookTotalsPanel.add(totalTimeValue);
        logbookTotalsPanel.revalidate();
        logbookTotalsPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: Displays all entries in logbook
    public void updateAllEntries() {
        allEntriesPanel.removeAll();
        allEntriesPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                "🏋️ Your Entries 🏋️",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 20),
                Color.BLACK));
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
                BorderFactory.createLineBorder(Color.BLACK, 2),
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

}
