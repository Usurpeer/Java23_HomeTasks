package homework8.microTasks.task1;

import homework8.microTasks.task1.exceptions.AlreadyExistsException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RecordsTest {
    final String str1 = "1";
    final String str2 = "2";
    final String str3 = "3";
    final String str4 = "2 ";

    @Test
    public void addRecordsExceptionTest() {
        List<String> records = new ArrayList<>() {
        };

        records.add(str1);
        records.add(str2);
        records.add(str3);

        var ex = assertThrows(AlreadyExistsException.class, () -> Records.addRecord(records, "2"));

        assertEquals("2", ex.getValue());
        assertEquals(records.indexOf("2"), ex.getPosition());
    }

    @Test
    public void correctAddRecordsTest() {
        List<String> records = new ArrayList<>() {
        };

        records.add(str1);
        records.add(str2);
        records.add(str3);
        records.add(str4);

        assertTrue(records.contains(str2));
        assertTrue(records.contains(str4));
    }
}