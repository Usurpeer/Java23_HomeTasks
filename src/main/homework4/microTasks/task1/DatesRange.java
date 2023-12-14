package homework4.microTasks.task1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatesRange {
    public static LocalDate[] getDates(LocalDate originStartingDate, LocalDate originEndingDate) {
        if (originStartingDate != null && originEndingDate != null) {
            LocalDate startingDate = LocalDate.from(originStartingDate);
            if (startingDate.isAfter(originEndingDate)) {
                return new LocalDate[0];
            }

            List<LocalDate> daysRange = new ArrayList<>();
            LocalDate endingDate = originEndingDate.plusDays(1);
            while (startingDate.isBefore(endingDate)) {
                daysRange.add(startingDate);
                startingDate = startingDate.plusDays(1);
            }

            return daysRange.toArray(new LocalDate[0]);
        } else {
            throw new NullPointerException("Неверная дата.");
        }
    }
}
