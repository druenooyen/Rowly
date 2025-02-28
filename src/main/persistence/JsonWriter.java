package persistence;

import model.RowLogbook;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;

import org.json.JSONObject;

// A class representing a writer that writes row logbook as JSON file  
// Adapted from code found in Json Serialization Demo
public class JsonWriter {
    private PrintWriter writer;
    private String destinationFile;
    private static final int INDENTATION_FACTOR = 4;

    // EFFECTS: constructs a writer instance to write to destinationFile
    public JsonWriter(String destinationFile) {
        this.destinationFile = destinationFile;
    }

    // MODIFIES: this
    // EFFECTS: opens a new writer, throws FileNotFound exception if
    // error when opening or creating destination file
    public void openWriter() throws FileNotFoundException {
        writer = new PrintWriter(new File(destinationFile));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of row logbook to destination file
    public void writeLogbookToJsonFile(RowLogbook rowLogbook) {
        JSONObject jsonObject = rowLogbook.toJson();
        writer.print(jsonObject.toString(INDENTATION_FACTOR));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void closeWriter() {
        writer.close();
    }
}
