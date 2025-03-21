package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.*;

import model.RowEntry;
import model.RowLogbook;

// A class for an ActionListener that listens for the button press to prompt 
// the creation of a new RowEntry in the logbook using the user input fields
public class RowEntryListener implements ActionListener {
    private JTextField dateField;
    private JTextField distanceField;
    private JTextField durationField;
    private JTextField rateField;
    private RowLogbook logbook;

    public RowEntryListener(JTextField dateField, JTextField distanceField, JTextField durationField,
            JTextField rateField, RowLogbook logbook) {
        this.dateField = dateField;
        this.distanceField = distanceField;
        this.durationField = durationField;
        this.rateField = rateField;
        this.logbook = logbook;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String date = dateField.getText().trim();
        String duration = durationField.getText().trim();

        Integer distance = handleInvalidIntInput(distanceField.getText());
        Integer rate = handleInvalidIntInput(rateField.getText());

        if (distance == null || rate == null) {
            JOptionPane.showMessageDialog(null,
                    "Invalid input! Please enter numbers for Distance and Rate.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        clearTextFields();

        RowEntry newEntry = new RowEntry(date, distance, duration, rate);
        logbook.addEntry(newEntry);

        JOptionPane.showMessageDialog(null,
                "Entry Added to Logbook! \n Summary: " + displayEntrySummary(newEntry));
    }

    private Integer handleInvalidIntInput(String text) {
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void clearTextFields() {
        dateField.setText("");
        distanceField.setText("");
        durationField.setText("");
        rateField.setText("");
    }

    private String displayEntrySummary(RowEntry rowEntry) {
        return "Date: " + rowEntry.getDate() + " // Distance: " + rowEntry.getDistance() + "m // Time: "
                + rowEntry.getTime() + " // Rate: " + rowEntry.getRate();
    }
}
