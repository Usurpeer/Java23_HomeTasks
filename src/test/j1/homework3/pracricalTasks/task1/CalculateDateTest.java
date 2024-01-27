package j1.homework3.pracricalTasks.task1;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

public class CalculateDateTest {

    private final static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

    @Test
    public void getFirstDayOfWeek() {
        final LocalDate date1 = LocalDate.parse("2018-12-31", formatter);
        final int weekNumber1 = 1;

        assertEquals(LocalDate.parse("2018-01-01"), CalculateDate.getFirstDayOfWeek(date1, weekNumber1));

        final LocalDate date2 = LocalDate.parse("2018-12-31", formatter);
        final int weekNumber2 = 53;

        assertEquals(LocalDate.parse("2018-12-31"), CalculateDate.getFirstDayOfWeek(date2, weekNumber2));

        final LocalDate date3 = LocalDate.parse("2018-12-31", formatter);
        final int weekNumber3 = 20;

        assertEquals(LocalDate.parse("2018-05-14"), CalculateDate.getFirstDayOfWeek(date3, weekNumber3));
    }

    @Test
    public void getRange() {
        LocalDate dayMonday1 = LocalDate.parse("2018-05-14");
        LocalDate dayMondayForList = LocalDate.parse("2018-05-14");

        List<LocalDate> localDates1 = CalculateDate.getRange(dayMondayForList);
        for (int i = 0; i < 7; i++) {
            assertEquals(dayMonday1, localDates1.get(i));
            dayMonday1 = dayMonday1.plusDays(1);
        }

        LocalDate dayMonday2 = LocalDate.parse("2018-12-31");
        dayMondayForList = LocalDate.parse("2018-12-31");

        List<LocalDate> localDates2 = CalculateDate.getRange(dayMondayForList);
        for (int i = 0; i < 7; i++) {
            assertEquals(dayMonday2, localDates2.get(i));
            dayMonday2 = dayMonday2.plusDays(1);
        }

        LocalDate dayMonday3 = LocalDate.parse("2018-01-01");
        dayMondayForList = LocalDate.parse("2018-01-01");

        List<LocalDate> localDates3 = CalculateDate.getRange(dayMondayForList);
        for (int i = 0; i < 7; i++) {
            assertEquals(dayMonday3, localDates3.get(i));
            dayMonday3 = dayMonday3.plusDays(1);
        }
    }
}