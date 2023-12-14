package homework3.microTasks.task3;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculateWeekendsTest {
    @Test
    public void getDifferenceTest() {
        assertEquals(0, CalculateWeekends.getQuantityWeekends("2023-11-01", "2023-11-01"));
        assertEquals(1, CalculateWeekends.getQuantityWeekends("2023-05-07", "2023-05-10"));
        assertEquals(4, CalculateWeekends.getQuantityWeekends("2023-05-01", "2023-05-16"));
        assertEquals(105, CalculateWeekends.getQuantityWeekends("2023-01-01", "2023-12-31"));
    }
}