package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.RowEntry;
import model.RowLogbook;

// A class representing a reader that reads row logbook from JSON file
// References code found in Json Serialization Demo
public class JsonReader {
    String sourceFile;

    // EFFECTS: constructs instance of reader to read from file
    public JsonReader(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    // EFFECTS: returns row logbook read from source file, throws IOException
    // if there is an error when reading data from file
    public RowLogbook readLogbookFromJson() throws IOException {
        String jsonString = readFile();
        JSONObject jsonObject = new JSONObject(jsonString);
        return parseRowLogbook(jsonObject);
    }

    // EFFECTS: reads file and return as a string, throws IOException
    // if there is an error when reading data from file
    public String readFile() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(sourceFile), StandardCharsets.UTF_8)) {
            stream.forEach(s -> stringBuilder.append(s));
        }
        return stringBuilder.toString();
    }

    // EFFECTS: returns row logbook parsed from JSON object
    public RowLogbook parseRowLogbook(JSONObject jsonObject) {
        RowLogbook logbook = new RowLogbook();
        loadRowEntriesFromJson(logbook, jsonObject);
        return logbook;
    }

    // MODIFIES: rowLogbook
    // EFFECTS: parses row entries from JSON file and adds them to row logbook
    public void loadRowEntriesFromJson(RowLogbook rowLogbook, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("rowEntries");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonArrayObject = jsonArray.getJSONObject(i);
            loadRowEntryFromJson(rowLogbook, jsonArrayObject);
        }
    }

    // MODIFIES: rowLogbook
    // EFFECTS: creates row entry from parsed JSONArray object and adds it to row
    // logbook
    public void loadRowEntryFromJson(RowLogbook rowLogbook, JSONObject jsonArrayObject) {
        String date = jsonArrayObject.getString("date");
        int distance = jsonArrayObject.getInt("distance");
        String duration = jsonArrayObject.getString("duration");
        int rate = jsonArrayObject.getInt("rate");
        boolean flagStatus = jsonArrayObject.getBoolean("flagStatus");
        RowEntry rowEntry = new RowEntry(date, distance, duration, rate);

        if (flagStatus == true) {
            rowEntry.flagWorkout();
        }

        rowLogbook.addEntry(rowEntry);
    }
}
