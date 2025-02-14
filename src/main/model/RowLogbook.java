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

    // EFFECTS: Returns total number of logbook entries
    public int findTotalWorkouts() {
        return rowLogbook.size();
    }

    // EFFECTS: Returns total distance of all logbook entries
    public int findTotalDistance() {
        int distance = 0;
        for (RowEntry entry : rowLogbook) {
            distance += entry.getDistance();
        }
        return distance;
    }

    // EFFECTS: Returns total time of all logbook entries
    public int findTotalTime() {
        int time = 0;
        for (RowEntry entry : rowLogbook) {
            time += entry.getTime();
        }
        return time;
    }

    // REQUIRES: A 2km workout entry in the logbook
    // EFFECTS: Returns best 2km time in workout logbook
    public int find2kmPersonalBest() {
        int shortest2kTime = Integer.MAX_VALUE;
        for (RowEntry entry : rowLogbook) {
            if (entry.getDistance() == 2000 && entry.getTime() < shortest2kTime) {
                shortest2kTime = entry.getTime();
            }
        }
        return shortest2kTime;
    }

    // REQUIRES: A 6km workout entry in the logbook
    // EFFECTS: Returns best 6km time in workout logbook
    public int find6kmPersonalBest() {
        int shortest6kTime = Integer.MAX_VALUE;
        for (RowEntry entry : rowLogbook) {
            if (entry.getDistance() == 6000 && entry.getTime() < shortest6kTime) {
                shortest6kTime = entry.getTime();
            }
        }
        return shortest6kTime;
    }
}
