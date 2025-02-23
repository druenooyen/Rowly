package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.RowEntry;
import model.RowLogbook;

// A class to test implementation of JsonWriter
public class JsonWriterTest extends JsonTest {
    JsonWriter writer;
    JsonReader reader;

    @Test
    void testWriterIllegalFile() {
        writer = new JsonWriter("./data/?Illegal:filename.json");
        try {
            writer.openWriter();
            fail("Did not throw FileNotFoundException as expected");
        } catch (FileNotFoundException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLogbook() {
        try {
            writer = new JsonWriter("./data/testWriteEmptyLogbook");
            RowLogbook logbook = new RowLogbook();
            writer.openWriter();
            writer.writeLogbookToJsonFile(logbook);
            writer.closeWriter();

            reader = new JsonReader("./data/testWriteEmptyLogbook");
            logbook = reader.readLogbookFromJson();
            assertEquals(0, logbook.countEntries());

        } catch (FileNotFoundException e) {
            fail("Did not expect FileNotFoundException");
        } catch (IOException e) {
            fail("Did not expect IOException");
        }
    }

    @Test
    void testWriterBasicLogbook() {
        try {
            writer = new JsonWriter("./data/testWriteBasicLogbook");
            RowLogbook logbook = new RowLogbook();
            RowEntry testEntry1 = new RowEntry("01-01-2000", 2000, "00:08:00", 24);
            RowEntry testEntry2 = new RowEntry("12-25-2010", 10000, "00:45:10", 18);
            logbook.addEntry(testEntry1);
            logbook.addEntry(testEntry2);

            writer.openWriter();
            writer.writeLogbookToJsonFile(logbook);
            writer.closeWriter();

            reader = new JsonReader("./data/testWriteBasicLogbook");
            logbook = reader.readLogbookFromJson();
            assertEquals(2, logbook.countEntries());
            List<RowEntry> rowEntries = logbook.getRowLogbook();
            testRowEntry(rowEntries.get(0), "01-01-2000", 2000, "00:08:00", 480, 24, false);
            testRowEntry(rowEntries.get(1), "12-25-2010", 10000, "00:45:10", 2710, 18, false);

        } catch (FileNotFoundException e) {
            fail("Did not expect FileNotFoundException");
        } catch (IOException e) {
            fail("Did not expect IOException");
        }
    }
}
