package persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.RowEntry;
import model.RowLogbook;

// A class to test implementation of JsonReader
public class JsonReaderTest extends JsonTest {
    JsonReader reader;

    @Test
    void testReaderFileDoesntExist() {
        reader = new JsonReader("./data/FileDoesntExist");
        try {
            RowLogbook logbook = reader.readLogbookFromJson();
            fail("Did not throw IOException as expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLogbook() {
        reader = new JsonReader("./data/testReaderEmptyRowLogbook.json");
        try {
            RowLogbook logbook = reader.readLogbookFromJson();
            assertEquals(0, logbook.countEntries());
        } catch (IOException e) {
            fail("Did not expect IOException");
        }
    }

    @Test
    void testReaderBasicLogbook() {
        reader = new JsonReader("./data/testReaderBasicRowLogbook.json");
        try {
            RowLogbook logbook = reader.readLogbookFromJson();
            assertEquals(2, logbook.countEntries());
            List<RowEntry> rowEntries = logbook.getRowLogbook();
            testRowEntry(rowEntries.get(0), "02-17-2025", 2000, "00:07:30", 450, 32, false);
            testRowEntry(rowEntries.get(1), "02-18-2025", 6000, "00:24:30", 1470, 28, true);
        } catch (IOException e) {
            fail("Did not expect IOException");
        }
    }
}
