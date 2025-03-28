package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

// A class representing the logbook of rowing workout entries
public class RowLogbook {
    private List<RowEntry> rowLogbook;
    private String personalBest2km;
    private String personalBest6km;
    private int entryCount;
    private int totalDistance;
    private int totalSeconds;

    // EFFECTS: creates logbook to store workout entries
    public RowLogbook() {
        rowLogbook = new ArrayList<RowEntry>();
        personalBest2km = "None";
        personalBest6km = "None";
        entryCount = 0;
        totalDistance = 0;
        totalSeconds = 0;
    }

    // MODIFIES: this
    // EFFECTS: adds RowEntry to logbook
    public void addEntry(RowEntry rowEntry) {
        rowLogbook.add(rowEntry);
        EventLog.getInstance().logEvent(new Event("Entry added to logbook."));
        update2kmPersonalBest();
        update6kmPersonalBest();
        entryCount++;
        updateTotalDistance(rowEntry);
        updateTotalTime(rowEntry);
    }

    // EFFECTS: returns number of entries in logbook
    public int countEntries() {
        // EventLog.getInstance().logEvent(new Event("Total entries recalculated."));
        return entryCount;
    }

    // REQUIRES: size of logbook is > i
    // EFFECTS: returns RowEntry at given index in list
    public RowEntry getRowEntry(int i) {
        return rowLogbook.get(i);
    }

    // EFFECTS: returns total distance of all logbook entries
    public void updateTotalDistance(RowEntry rowEntry) {
        totalDistance += rowEntry.getDistance();
        EventLog.getInstance().logEvent(new Event("Totals recalculated."));
    }

    // REQUIRES: totalSeconds is >= 0
    // EFFECTS: converts total seconds to time in hh:mm:ss format
    private String convertToTimeString(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        return String.format("%d:%02d:%02d", hours, minutes, seconds);
    }

    // EFFECTS: updates total time for all logbook entries
    public void updateTotalTime(RowEntry rowEntry) {
        totalSeconds += rowEntry.getTotalSeconds();
    }

    // EFFECTS: updates shortest 2km time in workout logbook
    public void update2kmPersonalBest() {
        int shortest2kTime = Integer.MAX_VALUE;
        for (RowEntry entry : rowLogbook) {
            if (entry.getDistance() == 2000 && entry.getTotalSeconds() < shortest2kTime) {
                shortest2kTime = entry.getTotalSeconds();
            }
        }
        if (shortest2kTime == Integer.MAX_VALUE) {
            return;
        }
        if (!convertToTimeString(shortest2kTime).equals(personalBest2km)) {
            personalBest2km = convertToTimeString(shortest2kTime);
            EventLog.getInstance().logEvent(new Event("2km personal best updated."));
        }
    }

    // EFFECTS: updates shortest 6km time in workout logbook
    public void update6kmPersonalBest() {
        int shortest6kTime = Integer.MAX_VALUE;
        for (RowEntry entry : rowLogbook) {
            if (entry.getDistance() == 6000 && entry.getTotalSeconds() < shortest6kTime) {
                shortest6kTime = entry.getTotalSeconds();
            }
        }
        if (shortest6kTime == Integer.MAX_VALUE) {
            return;
        }
        if (!convertToTimeString(shortest6kTime).equals(personalBest6km)) {
            personalBest6km = convertToTimeString(shortest6kTime);
            EventLog.getInstance().logEvent(new Event("6km personal best updated."));
        }
    }

    // EFFECTS: converts rowLogbook to JSON object and returns it
    public JSONObject toJson() {
        JSONArray jsonArray = new JSONArray();
        for (RowEntry e : rowLogbook) {
            jsonArray.put(e.toJson());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rowEntries", jsonArray);

        EventLog.getInstance().logEvent(new Event("Entries saved to file."));

        return jsonObject;
    }

    // getters
    public List<RowEntry> getRowLogbook() {
        return rowLogbook;
    }

    public String get2kmPB() {
        return personalBest2km;
    }

    public String get6kmPB() {
        return personalBest6km;
    }

    public String getTotalTime() {
        return convertToTimeString(totalSeconds);
    }

    public int getTotalDistance() {
        return totalDistance;
    }
}
