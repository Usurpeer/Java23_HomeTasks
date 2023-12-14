package homework3.microTasks.task2;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateDifferenceTest {
    @Test
    public void getDifferenceTest() {
        assertEquals("0", DateDifference.getDifference(
                "2023-11-12T00:00:00Z",
                "UTC+00",
                "UTC+00"));

        assertEquals("HOUR", DateDifference.getDifference(
                "2020-06-01T14:25:16Z",
                "UTC+05",
                "UTC+01"));

        assertEquals("DAY", DateDifference.getDifference(
                "2023-05-10T23:00:00Z",
                "UTC-03",
                "UTC+04"));

        assertEquals("MONTH", DateDifference.getDifference(
                "2024-10-01T04:59:59Z",
                "UTC-08",
                "UTC-04"));

        assertEquals("YEAR", DateDifference.getDifference(
                "2010-12-31T20:15:00Z",
                "Europe/Moscow",
                "Asia/Vladivostok"));
    }

}