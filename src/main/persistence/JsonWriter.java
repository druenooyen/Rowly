package persistence;

import java.io.FileNotFoundException;

import model.RowLogbook;

// A class representing a writer that writes row logbook as JSON file  
// References code found in Json Serialization Demo
public class JsonWriter {
    // EFFECTS: constructs a writer instance to write to destinationFile
    public JsonWriter(String destinationFile) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: opens a new writer, throws FileNotFound exception if
    // error when opening or creating destination file
    public void openWriter() throws FileNotFoundException {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of row logbook to destination file
    public void writeLogbookToJsonFile(RowLogbook logbook) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void closeWriter() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void saveStringToFile() {
        // stub
    }
}
