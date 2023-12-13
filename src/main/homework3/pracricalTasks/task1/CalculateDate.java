package homework3.pracricalTasks.task1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CalculateDate {
    // получает дату понедельника по указанной неделе
    public static LocalDate getFirstDayOfWeek(LocalDate date, int weekNumber) {
        // получить дату первого понедельника
        LocalDate date2 = date.withMonth(1).withDayOfMonth(1);
        while (date2.getDayOfWeek().getValue() != 1) {
            date2 = date2.plusDays(1);
        }

        // оказаться на нужной неделе
        int currentWeek = 1;
        while (currentWeek != weekNumber) {
            date2 = date2.plusWeeks(1);
            currentWeek++;
        }

        return date2;
    }

    // записывает 7 дат начиная с указанной
    public static List<LocalDate> getRange(LocalDate firstDayOfWeek) {
        if (firstDayOfWeek != null) {
            return IntStream
                    .rangeClosed(0, 6)
                    .mapToObj(firstDayOfWeek::plusDays)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>(0);
        }
    }
}
