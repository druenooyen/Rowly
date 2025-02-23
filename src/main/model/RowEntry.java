package model;

import java.util.Scanner;

import org.json.JSONObject;

// A class representing a rowing workout entry having a date, distance (in meters),
// time (in total seconds and in hh:mm:ss), stroke rate, and flag status
public class RowEntry {
    private String date;
    private int distance;
    private String duration;
    private int totalSeconds;
    private int rate;
    private boolean flagStatus;

    // REQUIRES: date is in the format yyyy-mm-dd, distance > 0, 
    //           duration have the format hh:mm:ss, rate > 0
    // EFFECTS: constructs a workout entry for given date, distance, duration, rate,
    //          and sets the flagged status to false
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
        try (Scanner scanner = new Scanner(duration)) {
            scanner.useDelimiter(":"); 

            int hours = scanner.nextInt();
            int minutes = scanner.nextInt();
            int seconds = scanner.nextInt();

            int totalSeconds = hours * 3600 + minutes * 60 + seconds;
            return totalSeconds;
        }
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

    // EFFECTS: converts rowEntry to JSON object and returns it
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        
        jsonObject.put("date", date);
        jsonObject.put("distance", distance);
        jsonObject.put("duration", duration);
        jsonObject.put("totalSeconds", totalSeconds);
        jsonObject.put("rate", rate);
        jsonObject.put("flagStatus", flagStatus);

        return jsonObject;
    } 
}
