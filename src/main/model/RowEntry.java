package model;

// A class representing a rowing workout entry having a date, distance (in meters), time, and flag status
public class RowEntry {

    // REQUIRES: date have the format yyyy-mm-dd
    // distance > 0
    // time have the format hh:mm:ss
    // EFFECTS: constructs a workout entry for given date, distance, duration (seconds), rate,
    // and sets the flagged status to false
    public RowEntry(String date, int distance, int duration, int rate) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: sets workout entry flag status to true
    public void flagWorkout() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: sets workout entry flag status to false
    public void unflagWorkout() {
        // stub
    }

    // getters
    public boolean getFlagStatus() {
        return false; // stub
    }

    public String getDate() {
        return null; // stub
    }

    public int getDuration() {
        return 0; // stub
    }

    public int getDistance() {
        return 0; // stub
    }

    public int getRate() {
        return 0; // stub
    }

    // setters
    public boolean setFlagStatus() {
        return false; // stub
    }

    public String setDate() {
        return null; // stub
    }

    public int setDuration() {
        return 0; // stub
    }

    public int setDistance() {
        return 0; // stub
    }

}
