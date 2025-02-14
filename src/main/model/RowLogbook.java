package model;

import java.util.ArrayList;
import java.util.List;

// A class representing the logbook of rowing workout entries
public class RowLogbook {
    List<RowEntry> rowLogbook;

    // EFFECTS: Creates logbook to store workout entries
    public RowLogbook() {
        rowLogbook = new ArrayList<RowEntry>();
    }

    // MODIFIES: this
    // EFFECTS: Adds given RowEntry to logbook
    public void addEntry(RowEntry entry) {
        rowLogbook.add(entry);
    }

    // EFFECTS: Returns number of entries in logbook
    public int countEntries() {
        return rowLogbook.size();
    }

    // EFFECTS: Returns RowEntry at given index in list
    public RowEntry getRowEntry(int i) {
        return rowLogbook.get(i);
    }

    // EFFECTS: Returns list of row entries in logbook
    public List<RowEntry> getRowLogbook() {
        return rowLogbook;
    }

    // EFFECTS: Returns total distance of all logbook entries
    public int findTotalDistance() {
        int distance = 0;
        for (RowEntry entry : rowLogbook) {
            distance += entry.getDistance();
        }
        return distance;
    }

    // EFFECTS: converts total seconds to time in hh:mm:ss format
    public String convertToTimeString(int totalSeconds) {
        int hours = totalSeconds / 3600;
           int minutes = (totalSeconds % 3600) / 60;
           int seconds = totalSeconds % 60;
           String time = hours + ":" + minutes + ":" + seconds;
           return time;
       }

    // EFFECTS: returns total time of all logbook entries
    public String findTotalTime() {
        int time = 0;
        for (RowEntry entry : rowLogbook) {
            time += entry.getTotalSeconds();
        }
        return convertToTimeString(time);
    }

    // EFFECTS: returns best 2km time in workout logbook, if none
    //          in logbook then returns "None"
    public String find2kmPersonalBest() {
        int shortest2kTime = Integer.MAX_VALUE;
        for (RowEntry entry : rowLogbook) {
            if (entry.getDistance() == 2000 && entry.getTotalSeconds() < shortest2kTime) {
                shortest2kTime = entry.getTotalSeconds();
            }
        }
        if (shortest2kTime == Integer.MAX_VALUE) {
            return "None";
        } else {
        return convertToTimeString(shortest2kTime);
        }
    }

    // EFFECTS: Returns best 6km time in workout logbook, if none
    //          in logbook then returns "None"
    public String find6kmPersonalBest() {
        int shortest6kTime = Integer.MAX_VALUE;
        for (RowEntry entry : rowLogbook) {
            if (entry.getDistance() == 6000 && entry.getTotalSeconds() < shortest6kTime) {
                shortest6kTime = entry.getTotalSeconds();
            }
        }
        if (shortest6kTime == Integer.MAX_VALUE) {
            return "None";
        } else {
        return convertToTimeString(shortest6kTime);
        }
    }
}
