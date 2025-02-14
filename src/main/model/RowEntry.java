package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// A class representing a rowing workout entry having a date, distance (in meters), time (in seconds), and flag status
public class RowEntry {
    private String date;
    private int distance;
    private String duration;
    private int totalSeconds;
    private int rate;
    private boolean flagStatus;

    // REQUIRES: date have the format yyyy-mm-dd
    // distance > 0
    // time have the format hh:mm:ss
    // EFFECTS: constructs a workout entry for given date, distance, duration, rate,
    // and sets the flagged status to false
    public RowEntry(String date, int distance, String duration, int rate) {
        this.date = date;
        this.distance = distance;
        this.duration = duration;
        this.totalSeconds = getTimeInSeconds(duration);
        this.rate = rate;
        flagStatus = false;
    }

    // EFFECTS: converts hh:mm:ss time input into seconds
    public int getTimeInSeconds(String duration) {
        Scanner scanner = new Scanner(duration);
        scanner.useDelimiter(":"); 

        int hours = scanner.nextInt();
        int minutes = scanner.nextInt();
        int seconds = scanner.nextInt();

        // LocalTime parsedTime = LocalTime.parse(duration, DateTimeFormatter.ofPattern("HH:mm:ss"));
        // int totalSeconds = parsedTime.getHour() * 3600 + parsedTime.getMinute() * 60 + parsedTime.getSecond();
        int totalSeconds = hours * 3600 + minutes * 60 + seconds;
        return totalSeconds;
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

    public String getTime() {
        return duration;
    }

    public int getTotalSeconds() {
        return totalSeconds;
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
