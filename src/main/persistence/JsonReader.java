package persistence;

import model.RowLogbook;

// A class representing a reader that reads row logbook from JSON file
// References code found in Json Serialization Demo
public class JsonReader {
    // EFFECTS: constructs instance of reader to read from file
    public JsonReader() {
        // stub
    }

    // EFFECTS: reads row logbook from file and returns it, throws IOException
    // if there is an error when reading data from file
    public RowLogbook readLogbookFromJson() {
        return null; // stub
    }

    // EFFECTS: reads file and return as string
    public String readFile() {
        return "";// stub
    }

    // EFFECTS: returns row logbook parsed from JSON object
    public RowLogbook parseRowLogbook() {
        return null; // stub
    }

    // MODIFIES: rowLogbook
    // EFFECTS: parses row entries from JSON file and adds them to row logbook
    public void loadRowEntriesFromJson() {
        // stub
    }

    // MODIFIES: rowLogbook
    // EFFECTS: parses a row entry in JSON object and adds it to row logbook
    public void loadRowEntryFromJson() {
        // stub
    }
}
