package j1.homework3.microTasks.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CalculateWeekends {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

    public static int getQuantityWeekends(String strStartDate, String strEndDate) {
        LocalDate startDate = LocalDate.parse(strStartDate, formatter);
        LocalDate endDate = LocalDate.parse(strEndDate, formatter);

        int result = 0;
        while (startDate.isBefore(endDate.plusDays(1))) {
            if (startDate.getDayOfWeek().getValue() == 6 || startDate.getDayOfWeek().getValue() == 7) {
                result++;
            }

            startDate = startDate.plusDays(1);
        }
        return result;
    }
}
