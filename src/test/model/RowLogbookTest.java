package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RowLogbookTest {
    RowEntry testRowEntry1;
    RowEntry testRowEntry2;
    RowEntry testRowEntry3;
    RowEntry testRowEntry4;
    RowLogbook testRowLogbook;

    @BeforeEach
    void runBefore() {
        testRowEntry1 = new RowEntry("2025-02-07", 2000, 480, 30);
        testRowEntry2 = new RowEntry("2025-02-08", 6000, 1560, 28);
        testRowEntry3 = new RowEntry("2025-02-09", 6000, 1400, 32);
        testRowEntry4 = new RowEntry("2025-02-07", 2000, 460, 30);
        testRowLogbook = new RowLogbook();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testRowLogbook.countEntries());
        assertTrue(testRowLogbook.getRowLogbook().isEmpty());
    }

    @Test
    void testAddEntry() {
        testRowLogbook.addEntry(testRowEntry1);
        assertEquals(1, testRowLogbook.countEntries());
        assertEquals(testRowEntry1, testRowLogbook.getRowEntry(0));
        assertEquals(1, testRowLogbook.getRowLogbook().size());
    }

    @Test
    void testAddEntryMultipleEntries() {
        testRowLogbook.addEntry(testRowEntry1);
        testRowLogbook.addEntry(testRowEntry2);
        assertEquals(2, testRowLogbook.countEntries());
        assertEquals(testRowEntry1, testRowLogbook.getRowEntry(0));
        assertEquals(testRowEntry2, testRowLogbook.getRowEntry(1));
        assertEquals(2, testRowLogbook.getRowLogbook().size());
    }

    @Test
    void testFindTotalWorkoutsEmpty() {
        assertEquals(0, testRowLogbook.findTotalWorkouts());
    }

    @Test
    void testFindTotalWorkoutsSingleEntry() {
        testRowLogbook.addEntry(testRowEntry1);
        assertEquals(1, testRowLogbook.findTotalWorkouts());
    }

    @Test
    void testFindTotalWorkoutsMultipleEntries() {
        testRowLogbook.addEntry(testRowEntry1);
        testRowLogbook.addEntry(testRowEntry2);
        assertEquals(2, testRowLogbook.findTotalWorkouts());
    }

    @Test
    void testFindTotalDistanceEmpty() {
        assertEquals(0, testRowLogbook.findTotalDistance());
    }

    @Test
    void testFindTotalDistanceSingleEntry() {
        testRowLogbook.addEntry(testRowEntry1);
        assertEquals(2000, testRowLogbook.findTotalDistance());
    }

    @Test
    void testFindTotalDistanceMultipleEntries() {
        testRowLogbook.addEntry(testRowEntry1);
        testRowLogbook.addEntry(testRowEntry2);
        assertEquals(8000, testRowLogbook.findTotalDistance());
    }

    @Test
    void testFindTotalTimeEmpty() {
        assertEquals(0, testRowLogbook.findTotalTime());
    }

    @Test
    void testFindTotalTimeMultipleSingleEntry() {
        testRowLogbook.addEntry(testRowEntry1);
        assertEquals(480, testRowLogbook.findTotalTime());
    }

    @Test
    void testFindTotalTimeMultipleEntries() {
        testRowLogbook.addEntry(testRowEntry1);
        testRowLogbook.addEntry(testRowEntry2);
        assertEquals(2040, testRowLogbook.findTotalTime());
    }

    @Test
    void testFind2kmPersonalBest() {
        testRowLogbook.addEntry(testRowEntry1);
        testRowLogbook.addEntry(testRowEntry2);
        assertEquals(480, testRowLogbook.find2kmPersonalBest());
    }

    @Test
    void testFind2kmPersonalBestEndOfList() {
        testRowLogbook.addEntry(testRowEntry1);
        testRowLogbook.addEntry(testRowEntry2);
        testRowLogbook.addEntry(testRowEntry4);
        assertEquals(460, testRowLogbook.find2kmPersonalBest());
    }

    @Test
    void testFind2kmPersonalBestMiddleOfList() {
        testRowLogbook.addEntry(testRowEntry1);
        testRowLogbook.addEntry(testRowEntry2);
        testRowLogbook.addEntry(testRowEntry4);
        testRowLogbook.addEntry(testRowEntry1);
        assertEquals(460, testRowLogbook.find2kmPersonalBest());
    }

    @Test
    void testFind6kmPersonalBest() {
        testRowLogbook.addEntry(testRowEntry1);
        testRowLogbook.addEntry(testRowEntry2);
        assertEquals(1560, testRowLogbook.find6kmPersonalBest());
    }

    @Test
    void testFind6kmPersonalBestEndOfList() {
        testRowLogbook.addEntry(testRowEntry1);
        testRowLogbook.addEntry(testRowEntry2);
        testRowLogbook.addEntry(testRowEntry3);
        assertEquals(1400, testRowLogbook.find6kmPersonalBest());
    }

    @Test
    void testFind6kmPersonalBestMiddleOfList() {
        testRowLogbook.addEntry(testRowEntry1);
        testRowLogbook.addEntry(testRowEntry2);
        testRowLogbook.addEntry(testRowEntry3);
        testRowLogbook.addEntry(testRowEntry2);
        assertEquals(1400, testRowLogbook.find6kmPersonalBest());
    }
}
