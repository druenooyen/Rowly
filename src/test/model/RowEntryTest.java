package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RowEntryTest {
    RowEntry testRowEntry;
    
    @BeforeEach
    void runBefore() {
        testRowEntry = new RowEntry("2025-02-07", 2000, 480, 30);
    }

    @Test
    void testConstructor() {
        assertEquals("2025-02-07", testRowEntry.getDate());
        assertEquals(2000, testRowEntry.getDistance());
        assertEquals(480, testRowEntry.getTime());
        assertEquals(30, testRowEntry.getRate());
        assertFalse(testRowEntry.getFlagStatus());
    }

    @Test
    void testFlagWorkout() {
        assertFalse(testRowEntry.getFlagStatus());
        testRowEntry.flagWorkout();
        assertTrue(testRowEntry.getFlagStatus());
    }

    @Test
    void testFlagWorkoutAlreadyFlagged() {
        assertFalse(testRowEntry.getFlagStatus());
        testRowEntry.flagWorkout();
        assertTrue(testRowEntry.getFlagStatus());
        testRowEntry.flagWorkout();
        assertTrue(testRowEntry.getFlagStatus());
    }

    @Test
    void testUnflagWorkout() {
        assertFalse(testRowEntry.getFlagStatus());
        testRowEntry.flagWorkout();
        assertTrue(testRowEntry.getFlagStatus());
        testRowEntry.unflagWorkout();
        assertFalse(testRowEntry.getFlagStatus());
    }

    @Test
    void testUnflagWorkoutAlreadyUnflagged() {
        assertFalse(testRowEntry.getFlagStatus());
        testRowEntry.unflagWorkout();
        assertFalse(testRowEntry.getFlagStatus());
    }
}
