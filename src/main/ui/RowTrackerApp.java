package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.RowEntry;
import model.RowLogbook;

// Creates an instance of rowing workout tracker application
// References code found in Lab 4 Flashcard Reviewer
public class RowTrackerApp {
    private boolean isProgramRunning;
    private RowLogbook rowLogbook;
    private Scanner scanner;

    // EFFECTS: runs the rowing workout tracker application
    public RowTrackerApp() {
        init();

        printDivider();
        System.out.println("Welcome to Rowly, your rowing workout tracker!");
        printDivider();

        while (isProgramRunning) {
            processMenuCommands();
        }
    }

    // MODIFIES: this
    // EFFECTS: inititalizes the application with starting values
    private void init() {
        isProgramRunning = true;
        scanner = new Scanner(System.in);
        rowLogbook = new RowLogbook();
    }

    // EFFECTS: processes user input to the menu
    @SuppressWarnings("methodlength")
    private void processMenuCommands() {
        displayMenuOptions();
        String userInput = scanner.nextLine();
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
                quitApp();
                break;
            default:
                System.out.println("Invalid input, please try again.");
        }
        printDivider();
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
        System.out.println("Please enter the date of your workout (YYYY-MM-DD)");
        String date = scanner.nextLine();
        System.out.println("Please enter the distance of your workout (in meters)");
        int distance = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter the duration of your workout (in hh:mm:ss)");
        String duration = scanner.nextLine();
        System.out.println("Please enter the average stroke rate of your workout");
        int rate = scanner.nextInt();
        scanner.nextLine();
        printDivider();

        RowEntry rowEntry = new RowEntry(date, distance, duration, rate);
        displayRowEntry(rowEntry);
        System.out.println("Add to logbook? (y/n)");
        String userInput = scanner.nextLine();
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
        String userInput = scanner.nextLine();
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
                + "Duration: " + rowEntry.getTime());
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
    //          message to user if logbook has no entries or no flagged entries
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
    //         of all entries in logbook
    private void printLogbookTotals() {
        if (isEmptyLogbook()) {
            return;
        }
        System.out.println("Your logbook totals:");
        System.out.println("Total Entries: " + rowLogbook.countEntries());
        System.out.println("Total Distance: " + rowLogbook.findTotalDistance() + " meters");
        System.out.println("Total Time: " + rowLogbook.findTotalTime());
    }

    // EFFECTS: prints current 2km and 6km personal bests
    private void printPersonalBests() {
        if (isEmptyLogbook()) {
            return;
        }
        System.out.println("Your current personal bests:");
        System.out.println("2km: " + rowLogbook.find2kmPersonalBest());
        System.out.println("6km: " + rowLogbook.find6kmPersonalBest());
    }

    // EFFECTS: if logbook has no entries, prints user message and returns
    //         true, otherwise returns false
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

}
