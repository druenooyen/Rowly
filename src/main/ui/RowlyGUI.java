package ui;

import java.awt.*;
import javax.swing.*;

import model.RowLogbook;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RowlyGUI extends JFrame {

    private JLabel title;
    private JPanel titleMenuPanel;
    private ActionPanelGUI actionPanel;
    private RowLogbook logbook;

    private static final String FILE_DESTINATION = "./data/logbook.json";

    private String[] options = { "Add Entry", "View Entries", "Logbook Totals", "Personal Bests", "Save" };

    public RowlyGUI() {
        super("Rowly Rowing Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        setLayout(new BorderLayout());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleWindowClosing();
            }
        });

        runSplashScreen();
        logbook = new RowLogbook();
        loadDataPopUp();
        makeTitleMenuPanel();
        actionPanel = new ActionPanelGUI(logbook);
        add(titleMenuPanel, BorderLayout.NORTH);
        add(actionPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: prompts user to load previous data
    public void loadDataPopUp() {
        int result = JOptionPane.showConfirmDialog(null,
                "Do you want to load your previously saved entries?", "Load Data",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            JsonReader reader = new JsonReader(FILE_DESTINATION);
            try {
                this.logbook = reader.readLogbookFromJson();
            } catch (IOException e) {
                System.out.println("Could not load data from file");
            }
        }
    }

    // EFFECTS: prompts user to save data to file when they close the window
    private void handleWindowClosing() {
        int option = JOptionPane.showConfirmDialog(
                this, 
                "Do you want to save your entries to file?",
                "Save Changes?",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            saveChanges();
            dispose();
        } else if (option == JOptionPane.NO_OPTION) {
            dispose();
        }
    }

    // EFFECTS: saves row entries to json file
    public void saveChanges() {
        JsonWriter writer = new JsonWriter(FILE_DESTINATION);
        try {
            writer.openWriter();
        } catch (FileNotFoundException e) {
            System.out.println("File destination not found");
        }
        writer.writeLogbookToJsonFile(logbook);
        writer.closeWriter();
    }

    // EFFECTS: makes all components of title menu panel
    public void makeTitleMenuPanel() {
        titleMenuPanel = new JPanel(new BorderLayout());
        title = new JLabel("Welcome to Rowly! Please select an option:");
        titleMenuPanel.add(title, BorderLayout.NORTH);

        final JComboBox userActions = new JComboBox(options);
        userActions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userChoice = (String) userActions.getSelectedItem();
                actionPanel.displayBasedOnSelection(userChoice);
            }
        });
        titleMenuPanel.add(userActions, BorderLayout.SOUTH);
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

    public static void main(String[] args) {
        new RowlyGUI();
    }
}
