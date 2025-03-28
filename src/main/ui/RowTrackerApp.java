package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.RowEntry;
import model.RowLogbook;
import persistence.JsonReader;
import persistence.JsonWriter;

// Creates an instance of rowing workout tracker application
// References code found in Lab 4 Flashcard Reviewer
public class RowTrackerApp {
    private boolean isProgramRunning;
    private RowLogbook rowLogbook;
    private Scanner scanner;
    private JsonReader reader;
    private JsonWriter writer;
    private static final String FILE_DESTINATION = "./data/logbook.json";

    // EFFECTS: runs the rowing workout tracker application
    public RowTrackerApp() {
        init();

        printDivider();
        System.out.println("Welcome to Rowly, your rowing workout tracker!");
        printDivider();
        processReloadRequest();

        while (isProgramRunning) {
            processMenuCommands();
        }
    }

    // processes user input to load saved logbook option
    private void processReloadRequest() {
        System.out.println("Would you like to load your previously saved logbook? (y/n)");
        String input = scanner.next();
        input = input.toLowerCase();

        switch (input) {
            case "y":
                loadRowLogbook();
                break;
            case "n":
                break;
            default:
                System.out.println("Invalid input, please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: inititalizes the application with starting values
    private void init() {
        isProgramRunning = true;
        scanner = new Scanner(System.in);
        rowLogbook = new RowLogbook();
        reader = new JsonReader(FILE_DESTINATION);
        writer = new JsonWriter(FILE_DESTINATION);
    }

    // EFFECTS: processes user input to the menu
    @SuppressWarnings("methodlength")
    private void processMenuCommands() {
        displayMenuOptions();
        String userInput = scanner.next();
        switch (userInput) {
            case "a":
                addNewEntry();
                break;
            case "v":
                printLogbook();
                break;
            case "f":
                printFlaggedEntries();
                break;
            case "t":
                printLogbookTotals();
                break;
            case "p":
                printPersonalBests();
                break;
            case "q":
                processSaveRequest();
                quitApp();
                break;
            default:
                System.out.println("Invalid input, please try again.");
        }
        printDivider();
    }

    // EFFECTS: processes user input to save option
    public void processSaveRequest() {
        System.out.println("Would you like to save your logbook to file? (y/n)");
        String input = scanner.next();
        input = input.toLowerCase();

        switch (input) {
            case "y":
                saveRowLogbook();
                break;
            case "n":
                break;
            default:
                System.out.println("Invalid input, please try again.");
        }
    }

    // EFFECTS: displays menu options to user
    private void displayMenuOptions() {
        System.out.println("Please select from the following options:");
        System.out.println("a: Add a new workout entry");
        System.out.println("v: View all workout entries in logbook");
        System.out.println("f: View flagged entries in logbook");
        System.out.println("t: View logbook totals");
        System.out.println("p: View current personal bests");
        System.out.println("q: Quit the application");
        printDivider();
    }

    // MODIFIES: this
    // EFFECTS: creates new RowEntry and adds to Row Logbook
    private void addNewEntry() {
        System.out.println("Please enter the date of your workout (yyyy-mm-dd)");
        String date = scanner.next();
        System.out.println("Please enter the distance of your workout (in meters)");
        int distance = scanner.nextInt();
        System.out.println("Please enter the duration of your workout (in hh:mm:ss)");
        String duration = scanner.next();
        System.out.println("Please enter the average stroke rate of your workout");
        int rate = scanner.nextInt();
        printDivider();

        RowEntry rowEntry = new RowEntry(date, distance, duration, rate);
        displayRowEntry(rowEntry);
        System.out.println("Add to logbook? (y/n)");
        String userInput = scanner.next();
        if (userInput.equals("y")) {
            rowLogbook.addEntry(rowEntry);
            System.out.println("New entry successfully created!");
        }
        flagAddedEntry(rowEntry);
    }

    // MODIFIES: rowEntry
    // EFFECTS: flags given entry depending on user input
    private void flagAddedEntry(RowEntry rowEntry) {
        System.out.println("Would you like to flag this workout? (y/n)");
        String userInput = scanner.next();
        if (userInput.equals("y")) {
            rowEntry.flagWorkout();
            System.out.println("Workout has been flagged!");
        }
    }

    // EFFECTS: prints out overview of rowEntry
    private void displayRowEntry(RowEntry rowEntry) {
        System.out.println("Summary of your workout on " + rowEntry.getDate() + ":");
        System.out.println("Distance: " + rowEntry.getDistance() + " meters");
        System.out.println("Duration: " + rowEntry.getTime());
        System.out.println("Stroke Rate: " + rowEntry.getRate() + " strokes per minute");
    }

    // EFFECTS: prints out one line summary of rowEntry
    private void displayRowEntrySummary(RowEntry rowEntry) {
        System.out.println("Date: " + rowEntry.getDate() + " // "
                + "Distance: " + rowEntry.getDistance() + " meters // "
                + "Duration: " + rowEntry.getTime() + " // "
                + "Rate: " + rowEntry.getRate() + " strokes per minute");
    }

    // EFFECTS: prints all row entries in log to the screen
    private void printLogbook() {
        if (isEmptyLogbook()) {
            return;
        }
        System.out.println("Here are your logbook entries:");
        for (RowEntry r : rowLogbook.getRowLogbook()) {
            displayRowEntrySummary(r);
        }
    }

    // EFFECTS: prints all flagged workout entries in log to the screen, prints
    // message to user if logbook has no entries or no flagged entries
    private void printFlaggedEntries() {
        if (isEmptyLogbook()) {
            return;
        }
        ArrayList<RowEntry> flaggedEntries = new ArrayList<>();
        for (RowEntry r : rowLogbook.getRowLogbook()) {
            if (r.getFlagStatus()) {
                flaggedEntries.add(r);
            }
        }
        if (flaggedEntries.isEmpty()) {
            System.out.println("There are no flagged entries in your logbook.");
        } else {
            System.out.println("Here are your flagged entries:");
            for (RowEntry r : flaggedEntries) {
                displayRowEntrySummary(r);
            }
        }
    }

    // EFFECTS: prints total workouts, total distance, and total time
    // of all entries in logbook
    private void printLogbookTotals() {
        if (isEmptyLogbook()) {
            return;
        }
        System.out.println("Your logbook totals:");
        System.out.println("Total Entries: " + rowLogbook.countEntries());
        System.out.println("Total Distance: " + rowLogbook.getTotalDistance() + " meters");
        System.out.println("Total Time: " + rowLogbook.getTotalTime());
    }

    // EFFECTS: prints current 2km and 6km personal bests
    private void printPersonalBests() {
        if (isEmptyLogbook()) {
            return;
        }
        System.out.println("Your current personal bests:");
        System.out.println("2km: " + rowLogbook.get2kmPB());
        System.out.println("6km: " + rowLogbook.get6kmPB());
    }

    // EFFECTS: if logbook has no entries, prints user message and returns
    // true, otherwise returns false
    private boolean isEmptyLogbook() {
        if (rowLogbook.getRowLogbook().isEmpty()) {
            System.out.println("Your logbook is empty! Please add a new entry.");
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: prints closing message and sets program to not running
    public void quitApp() {
        System.out.println("Thank you for using the rowing tracker. Keep on paddling!");
        isProgramRunning = false;
    }

    // EFFECTS: prints a divider line of dashes
    private void printDivider() {
        System.out.println("-------------------------------------------");
    }

    // MODIFIES: this
    // EFFECTS: loads row logbook from file
    public void loadRowLogbook() {
        try {
            rowLogbook = reader.readLogbookFromJson();
            System.out.println("Logbook has been successfully loaded from " + FILE_DESTINATION);
            printDivider();
        } catch (IOException e) {
            System.out.println("Unable to load logbook from " + FILE_DESTINATION);
        }
    }

    // EFFECTS: saves row logbook to file
    public void saveRowLogbook() {
        try {
            writer.openWriter();
            writer.writeLogbookToJsonFile(rowLogbook);
            writer.closeWriter();
            System.out.println("Logbook has been successfully saved to " + FILE_DESTINATION);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save logbook to " + FILE_DESTINATION);
        }
    }
}
