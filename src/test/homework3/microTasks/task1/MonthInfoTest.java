package homework3.microTasks.task1;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class MonthInfoTest {
    private static final MonthInfo monthInfo = new MonthInfo("2023-11-12");
    private static final MonthInfo monthInfo2 = new MonthInfo("2020-02-12");

    @Test
    public void ctorInitNoParams() {
        MonthInfo monthInfo = new MonthInfo();

        assertEquals(LocalDate.now(), monthInfo.getDate());
    }

    @Test
    public void ctorInitWithParams() {
        MonthInfo monthInfo = new MonthInfo("2023-11-12");

        assertEquals(LocalDate.parse("2023-11-12"), monthInfo.getDate());
    }

    @Test
    public void getMonthNameTest() {
        assertEquals("ноябрь", monthInfo.getMonthName());
        assertEquals("февраль", monthInfo2.getMonthName());
    }

    @Test
    public void getMonthNumberTest() {
        assertEquals(11, monthInfo.getMonthNumber());
        assertEquals(2, monthInfo2.getMonthNumber());
    }

    @Test
    public void getMothWeekdayTest() {
        assertEquals("ср", monthInfo.getMothWeekday());
        assertEquals("сб", monthInfo2.getMothWeekday());
    }

    @Test
    public void getMothLastDayDateTest() {
        assertEquals(LocalDate.parse("2023-11-30"), monthInfo.getMothLastDayDate());
        assertEquals(LocalDate.parse("2020-02-29"), monthInfo2.getMothLastDayDate());
    }

    @Test
    public void getMonthQuantityDaysTest() {
        assertEquals(30, monthInfo.getMonthQuantityDays());
        assertEquals(29, monthInfo2.getMonthQuantityDays());
    }

    @Test
    public void getMonthQuarter() {
        assertEquals("2023 Q4", monthInfo.getMonthQuarter());
        assertEquals("2020 Q1", monthInfo2.getMonthQuarter());
    }
}