package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import model.RowEntry;
import model.RowLogbook;

// A class representing the GUI for the panels that users can navigate between 
// via the navigation menu
public class ActionPanelGUI extends JPanel {
    private RowLogbook logbook;
    private JPanel rowEntryPanel;
    private JPanel allEntriesPanel;
    private JPanel logbookTotalsPanel;
    private JPanel personalBestsPanel;
    private RowEntryListener rowEntryListener;

    private JButton addButton;
    private JTextField dateField;
    private JTextField distanceField;
    private JTextField durationField;
    private JTextField rateField;
    private CardLayout actionPanelLayout;
    private Font font = new Font("SansSerif", Font.BOLD, 16);

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
    // EFFECTS: creates panel for user to input new row entry
    public void makeRowEntryPanel() {
        rowEntryPanel = new JPanel();
        rowEntryPanel.setLayout(new BoxLayout(rowEntryPanel, BoxLayout.Y_AXIS));

        dateField = new JTextField(15);
        distanceField = new JTextField(15);
        durationField = new JTextField(15);
        rateField = new JTextField(5);
        addButton = new JButton("Add Entry to Logbook");

        addComponentsToRowEntryPanel();
        rowEntryListener = new RowEntryListener(dateField, distanceField, durationField, rateField, logbook);
        addButton.addActionListener(rowEntryListener);
    }

    // MODIFIES: this
    // EFFECTS: clears text from row entry textfields
    public void clearTextFields() {
        dateField.setText("");
        distanceField.setText("");
        durationField.setText("");
        rateField.setText("");
    }

    // MODIFIES: this
    // EFFECTS: adds all panels and labels to row entry panel
    public void addComponentsToRowEntryPanel() {
        rowEntryPanel.add(new JLabel("Enter Date (yyyy-mm-dd):"));
        rowEntryPanel.add(dateField);
        rowEntryPanel.add(new JLabel("Enter Distance (in meters):"));
        rowEntryPanel.add(distanceField);
        rowEntryPanel.add(new JLabel("Enter Duration (hh:mm:ss):"));
        rowEntryPanel.add(durationField);
        rowEntryPanel.add(new JLabel("Enter Rate (strokes/minute):"));
        rowEntryPanel.add(rateField);
        rowEntryPanel.add(addButton);
        this.add(rowEntryPanel, "Add Entry");
    }

    // EFFECTS: returns written summary of rowEntry
    public JPanel displayEntry(RowEntry rowEntry) {
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        displayPanel.add(new JLabel("Your entry from " + rowEntry.getDate() + ":"));
        displayPanel.add(new JLabel("Distance: " + rowEntry.getDistance() + "m"));
        displayPanel.add(new JLabel("Time: " + rowEntry.getTime()));
        displayPanel.add(new JLabel("Rate: " + rowEntry.getRate()));
        return displayPanel;
    }

    // MODIFIES: this
    // EFFECTS: creates panel for user to view all logbook entries
    public void makeAllEntriesPanel() {
        allEntriesPanel = new JPanel();
        allEntriesPanel.setLayout(new BoxLayout(allEntriesPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(allEntriesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        this.add(scrollPane, "View Entries");
    }

    // MODIFIES: this
    // EFFECTS: creates panel for user to view logbook totals
    public void makeLogbookTotalsPanel() {
        logbookTotalsPanel = new JPanel();
        logbookTotalsPanel.setLayout(new GridLayout(3, 2, 5, 5));
        this.add(logbookTotalsPanel, "Logbook Totals");
    }

    // MODIFIES: this
    // EFFECTS: creates panel for user to view personal bests
    public void makePersonalBestsPanel() {
        personalBestsPanel = new JPanel();
        personalBestsPanel.setLayout(new GridLayout(4, 1, 5, 5));
        // updatePersonalBests();
        this.add(personalBestsPanel, "Personal Bests");
    }

    // MODIFIES: this
    // EFFECTS: displays appropriate panel based on userChoice
    public void displayBasedOnSelection(String userChoice) {
        switch (userChoice) {
            case "Logbook Totals":
                updateLogbookTotals();
                actionPanelLayout.show(this, "Logbook Totals");
                break;
            case "View Entries":
                updateAllEntries();
                actionPanelLayout.show(this, "View Entries");
                break;
            case "Personal Bests":
                updatePersonalBests();
                actionPanelLayout.show(this, "Personal Bests");
                break;
            default:
                actionPanelLayout.show(this, "Add Entry");
        }
    }

    // MODIFIES: this
    // EFFECTS: updates totals panel to display totals for all entries in current logbook
    public void updateLogbookTotals() {
        logbookTotalsPanel.removeAll();
        JLabel totalEntries = new JLabel("Total entries:", SwingConstants.CENTER);
        JLabel totalEntriesValue = new JLabel(" " + logbook.countEntries(), SwingConstants.CENTER);
        JLabel totalDistance = new JLabel("Total distance:", SwingConstants.CENTER);
        JLabel totalDistanceValue = new JLabel(" " + logbook.getTotalDistance() + "m", SwingConstants.CENTER);
        JLabel totalTime = new JLabel("Total time:", SwingConstants.CENTER);
        JLabel totalTimeValue = new JLabel(" " + logbook.getTotalTime(), SwingConstants.CENTER);

        totalDistance.setFont(font);
        totalDistanceValue.setFont(font);
        totalTime.setFont(font);
        totalTimeValue.setFont(font);
        totalEntries.setFont(font);
        totalEntriesValue.setFont(font);
        addBorder(logbookTotalsPanel, "Logbook Totals");

        logbookTotalsPanel.add(totalEntries);
        logbookTotalsPanel.add(totalEntriesValue);
        logbookTotalsPanel.add(totalDistance);
        logbookTotalsPanel.add(totalDistanceValue);
        logbookTotalsPanel.add(totalTime);
        logbookTotalsPanel.add(totalTimeValue);
        logbookTotalsPanel.revalidate();
        logbookTotalsPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: updates entries panel to display all entries in current logbook
    public void updateAllEntries() {
        if (allEntriesPanel.getComponentCount() == logbook.getRowLogbook().size()) {
            return; 
        }

        allEntriesPanel.removeAll();
        addBorder(allEntriesPanel, "Logbook Entries");
        for (int i = logbook.getRowLogbook().size() - 1; i >= 0; i--) {
            RowEntry r = logbook.getRowLogbook().get(i);
            allEntriesPanel.add(displayEntry(r));
        }
        allEntriesPanel.revalidate();
        allEntriesPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: updates personal bests panel to display personal bests in current logbook
    public void updatePersonalBests() {
        personalBestsPanel.removeAll();
        JLabel best2km = new JLabel("🏅 Your 2km personal best 🏅", SwingConstants.CENTER);
        JLabel best2kmValue = new JLabel(logbook.get2kmPB(), SwingConstants.CENTER);
        JLabel best6km = new JLabel("🏅 Your 6km personal best 🏅", SwingConstants.CENTER);
        JLabel best6kmValue = new JLabel(logbook.get6kmPB(), SwingConstants.CENTER);
        best2km.setFont(font);
        best6kmValue.setFont(font);
        best2kmValue.setFont(font);
        best6km.setFont(font);
        best2km.setForeground(new Color(212, 175, 55));
        best6km.setForeground(new Color(212, 175, 55));
        addBorder(personalBestsPanel, "Personal Bests");

        personalBestsPanel.add(best2km);
        personalBestsPanel.add(best2kmValue);
        personalBestsPanel.add(best6km);
        personalBestsPanel.add(best6kmValue);
        personalBestsPanel.revalidate();
        personalBestsPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: updates current logbook to given logbook
    public void updateLogbook(RowLogbook logbook) {
        this.logbook = logbook;
        rowEntryListener.updateLogbook(this.logbook);
    }

    // MODIFIES: panel
    // EFFECTS: adds a border with sylized title to panel
    public void addBorder(JPanel panel, String title) {
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 3),
                title,
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 20),
                new Color(0, 35, 102)));
    }
}
