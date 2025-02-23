package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.RowEntry;

// Parent class for testing correct row entries in JsonReader and JsonWriter
public class JsonTest {
    public void testRowEntry(RowEntry rowEntry, String date, int distance, String time, int totalSeconds, int rate,
            boolean flagStatus) {
        assertEquals(date, rowEntry.getDate());
        assertEquals(distance, rowEntry.getDistance());
        assertEquals(time, rowEntry.getTime());
        assertEquals(totalSeconds, rowEntry.getTotalSeconds());
        assertEquals(rate, rowEntry.getRate());
        assertEquals(flagStatus, rowEntry.getFlagStatus());
    }
}
