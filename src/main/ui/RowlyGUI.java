package ui;

import java.awt.*;
import javax.swing.*;

import model.RowLogbook;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// A class representing the GUI for the Rowly app with a title panel and navigation menu
// for users to select different actions within the app
public class RowlyGUI extends JFrame {

    private JPanel titleMenuPanel;
    private ActionPanelGUI actionPanel;
    private RowLogbook logbook;

    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 300;
    private static final String FILE_DESTINATION = "./data/logbook.json";

    private String[] options = { "Add Entry", "View Entries", "Logbook Totals", "Personal Bests", "Save and Exit"};

    public RowlyGUI() {
        super("Rowly Rowing Workout Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setLayout(new BorderLayout());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleWindowClosing();
            }
        });

        runSplashScreen();
        logbook = new RowLogbook();

        makeTitleMenuPanel();
        actionPanel = new ActionPanelGUI(logbook);
        add(titleMenuPanel, BorderLayout.NORTH);
        add(actionPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        loadDataPopUp();
    }

    // MODIFIES: this
    // EFFECTS: prompts user to load previous data
    public void loadDataPopUp() {
        int result = JOptionPane.showConfirmDialog(null,
                "Do you want to load your previously saved entries?", "Load Data",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            JsonReader reader = new JsonReader(FILE_DESTINATION);
            try {
                this.logbook = reader.readLogbookFromJson();
                actionPanel.updateLogbook(this.logbook);
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
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            saveChanges();
            dispose();
        } else if (option == JOptionPane.NO_OPTION) {
            dispose();
        }
    }

    // MODIFIES: this
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

    // MODIFIES: this
    // EFFECTS: makes all components of title and navigation bar at top of window
    public void makeTitleMenuPanel() {
        titleMenuPanel = new JPanel(new BorderLayout());
        titleMenuPanel.setBackground(Color.WHITE);

        ImageIcon wave = new ImageIcon("src/main/resources/wave.png");
        Image scaledImage = wave.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel waveImageLeft = new JLabel(scaledIcon);
        JLabel waveImageRight = new JLabel(scaledIcon);

        titleMenuPanel.add(makeTitlePanel(), BorderLayout.CENTER);
        titleMenuPanel.add(waveImageLeft, BorderLayout.WEST); 
        titleMenuPanel.add(waveImageRight, BorderLayout.EAST);

        final JComboBox userActions = new JComboBox(options);
        userActions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userChoice = (String) userActions.getSelectedItem();
                if (userChoice == "Save and Exit") {
                    saveChanges();
                    dispose();
                }
                actionPanel.displayBasedOnSelection(userChoice);
            }
        });
        titleMenuPanel.add(userActions, BorderLayout.SOUTH);
    }

    // EFFECTS: creates panel with title
    public JPanel makeTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Your Rowly Logbook", SwingConstants.CENTER);
        Font titleFont = new Font("SansSerif", Font.BOLD, 16);
        title.setFont(titleFont);
        title.setForeground(new Color(0, 35, 102));
        JLabel selectOption = new JLabel("Please select an option:", SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        titlePanel.setBackground(Color.WHITE);
        titlePanel.add(title, BorderLayout.NORTH);
        titlePanel.add(selectOption, BorderLayout.SOUTH);
        return titlePanel;
    }

    // EFFECTS: shows splash screen with rowly logo for 2 seconds
    public void runSplashScreen() {
        JWindow splashScreen = new JWindow();
        splashScreen.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        splashScreen.setLocationRelativeTo(null);

        ImageIcon rowlyLogo = new ImageIcon("src/main/resources/rowly_logo3.png");
        Image scaledImage = rowlyLogo.getImage().getScaledInstance(400, 500, Image.SCALE_SMOOTH);
        JLabel splashImage = new JLabel();
        splashImage.setIcon(new ImageIcon(scaledImage));

        splashScreen.add(splashImage);
        splashScreen.setVisible(true);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted.");
        }

        splashScreen.dispose();
    }

    public static void main(String[] args) {
        new RowlyGUI();
    }
}
