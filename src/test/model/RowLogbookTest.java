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
        testRowLogbook.add(testRowEntry1);
        testRowLogbook.add(testRowEntry2);
    }

    @Test
    void testConstructor() {
        assertEquals(2, testRowLogbook.length());
        assertEquals(testRowEntry1, testRowLogbook.get(0));
        assertEquals(testRowEntry2, testRowLogbook.get(1));
    }

    @Test
    void testFindTotalWorkoutsEmpty() {
        testRowLogbook = new RowLogbook();
        assertEquals(0, testRowLogbook.findTotalWorkouts());
    }

    @Test
    void testFindTotalWorkouts() {
        assertEquals(2, testRowLogbook.findTotalWorkouts());
    }

    @Test
    void testFindTotalWorkoutsAddEntry() {
        assertEquals(2, testRowLogbook.findTotalWorkouts());
        testRowLogbook.add(testRowEntry3);
        assertEquals(3, testRowLogbook.findTotalWorkouts());
    }

    @Test
    void testFindTotalDistanceEmpty() {
        testRowLogbook = new RowLogbook();
        assertEquals(0, testRowLogbook.findTotalDistance());
    }

    @Test
    void testFindTotalDistance() {
        assertEquals(8000, testRowLogbook.findTotalDistance());
    }

    @Test
    void testFindTotalDistanceAddEntry() {
        assertEquals(8000, testRowLogbook.findTotalDistance());
        testRowLogbook.add(testRowEntry3);
        assertEquals(14000, testRowLogbook.findTotalDistance());
    }

    @Test
    void testFindTotalTimeEmpty() {
        testRowLogbook = new RowLogbook();
        assertEquals(0, testRowLogbook.findTotalTime());
    }

    @Test
    void testFindTotalTime() {
        assertEquals(2040, testRowLogbook.findTotalTime());
    }

    @Test
    void testFindTotalTimeAddEntry() {
        assertEquals(2040, testRowLogbook.findTotalTime());
        testRowLogbook.add(testRowEntry3);
        assertEquals(2040 + 1400, testRowLogbook.findTotalTime());
    }

    @Test
    void testFind2kmPersonalBest() {
        assertEquals(480, testRowLogbook.find2kmPersonalBest());
    }

    @Test
    void testFind2kmPersonalBestLaterInList() {
        testRowLogbook.add(testRowEntry4);
        assertEquals(460, testRowLogbook.find2kmPersonalBest());
    }

    @Test
    void testFind6kmPersonalBest() {
        assertEquals(1560, testRowLogbook.find2kmPersonalBest());
    }

    @Test
    void testFind6kmPersonalBestLaterInList() {
        testRowLogbook.add(testRowEntry3);
        assertEquals(1400, testRowLogbook.find2kmPersonalBest());
    }
}
