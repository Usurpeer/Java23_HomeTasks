package j1.homework3.pracricalTasks.task1;

import static org.junit.Assert.*;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;


public class InitializerTest {
    @Test
    public void ctorWrongDate() {
        final String dateFalse = "23-12-1";
        final int weekNumber = 15;

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Initializer(dateFalse, weekNumber)
        );
        assertEquals("Недопустимый формат даты: " + dateFalse, ex.getMessage());


        final String dateFalse2 = "2023 12 1";

        ex = assertThrows(IllegalArgumentException.class, () ->
                new Initializer(dateFalse2, weekNumber)
        );
        assertEquals("Недопустимый формат даты: " + dateFalse2, ex.getMessage());
    }

    @Test
    public void ctorWrongWeekNumber() {
        final String date = "2023-12-1";
        final int weekNumber = 0;

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Initializer(date, weekNumber)
        );
        assertEquals("Недопустимый формат номера недели: " + weekNumber, ex.getMessage());
    }

    @Test
    public void getWrongDates() {
        final int weekNumber = 54;
        Initializer initializer = new Initializer("2022-01-01", weekNumber);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, initializer::getDates);

        assertEquals("Недопустимый формат номера недели: " + weekNumber, ex.getMessage());
    }

    @Test
    public void getCorrectDatesForBeginningYear() {
        Initializer initializer = new Initializer("2022-01-01", 1);
        List<LocalDate> list = initializer.getDates();
        LocalDate localDateFirst = LocalDate.parse("2022-01-03");

        assertEquals(localDateFirst, list.get(0));

        LocalDate localDateLast = LocalDate.parse("2022-01-09");
        assertEquals(localDateLast, list.get(list.size() - 1));
    }

    @Test
    public void getCorrectDatesForEndingYear() {
        Initializer initializer = new Initializer("2018-12-31", 53);

        List<LocalDate> list = initializer.getDates();

        LocalDate localDateFirst = LocalDate.parse("2018-12-31");

        assertEquals(localDateFirst, list.get(0));

        LocalDate localDateLast = LocalDate.parse("2019-01-06");

        assertEquals(localDateLast, list.get(list.size() - 1));
    }

    @Test
    public void getCorrectDates() {
        Initializer initializer = new Initializer("2018-12-31", 20);
        List<LocalDate> list = initializer.getDates();
        LocalDate localDateFirst = LocalDate.parse("2018-05-14");

        assertEquals(localDateFirst, list.get(0));

        LocalDate localDateLast = LocalDate.parse("2018-05-20");
        assertEquals(localDateLast, list.get(list.size() - 1));
    }
}