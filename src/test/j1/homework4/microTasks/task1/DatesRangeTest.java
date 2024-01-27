package j1.homework4.microTasks.task1;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DatesRangeTest {
    @Test
    public void getZeroDates() {
        LocalDate localDate1 = LocalDate.parse("2023-11-12");
        LocalDate localDate2 = LocalDate.parse("2023-11-11");

        LocalDate[] localDates = DatesRange.getDates(localDate1, localDate2);

        assertArrayEquals(new LocalDate[0], localDates);
    }

    @Test
    public void getCorrectRangeDates() {
        LocalDate[] localDates = DatesRange.getDates(LocalDate.parse("2020-05-28"),
                LocalDate.parse("2020-06-02"));

        assertEquals(LocalDate.parse("2020-05-28"), localDates[0]);
        assertEquals(LocalDate.parse("2020-06-01"), localDates[localDates.length - 2]);
        assertEquals(LocalDate.parse("2020-06-02"), localDates[localDates.length - 1]);


        LocalDate[] localDates1 = DatesRange.getDates(LocalDate.parse("2023-02-28"),
                LocalDate.parse("2023-02-28"));

        assertEquals(LocalDate.parse("2023-02-28"), localDates1[localDates1.length - 1]);
    }
}