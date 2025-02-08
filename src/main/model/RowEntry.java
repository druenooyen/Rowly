package model;

// A class representing a rowing workout entry having a date, distance (in meters), time, and flag status
public class RowEntry {
    private String date;
    private int distance;
    private int duration;
    private int rate;
    private boolean flagStatus;

    // REQUIRES: date have the format yyyy-mm-dd
    // distance > 0
    // time have the format hh:mm:ss
    // EFFECTS: constructs a workout entry for given date, distance, duration (seconds), rate,
    // and sets the flagged status to false
    public RowEntry(String date, int distance, int duration, int rate) {
        this.date = date;
        this.distance = distance;
        this.duration = duration;
        this.rate = rate;
        flagStatus = false;
    }

    // MODIFIES: this
    // EFFECTS: sets workout entry flag status to true
    public void flagWorkout() {
        flagStatus = true;
    }

    // MODIFIES: this
    // EFFECTS: sets workout entry flag status to false
    public void unflagWorkout() {
        flagStatus = false;
    }

    // getters
    public boolean getFlagStatus() {
        return flagStatus; 
    }

    public String getDate() {
        return date; 
    }

    public int getDuration() {
        return duration;
    }

    public int getDistance() {
        return distance; 
    }

    public int getRate() {
        return rate;
    }

    // // setters
    // public boolean setFlagStatus() {
    //     return false; // stub
    // }

    // public String setDate() {
    //     return null; // stub
    // }

    // public int setDuration() {
    //     return 0; // stub
    // }

    // public int setDistance() {
    //     return 0; // stub
    // }

}
