package homework3.pracricalTasks.task1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Initializer {
    private final LocalDate date;
    private final int weekNumber;

    public Initializer(String strDate, int weekNumber) {
        try {
            // можно было сделать || weekNumber > 53, но это проверятся ниже
            if (weekNumber <= 0) {
                throw new IllegalArgumentException("Недопустимый формат номера недели: " + weekNumber);
            }

            this.weekNumber = weekNumber;

            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            this.date = LocalDate.parse(strDate, formatter);

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Недопустимый формат даты: " + strDate);
        }
    }

    public List<LocalDate> getDates() {
        LocalDate dateFirstMonday = CalculateDate.getFirstDayOfWeek(date, weekNumber);

        if (date.getYear() != dateFirstMonday.getYear()) {
            throw new IllegalArgumentException("Недопустимый формат номера недели: " + weekNumber);
        }

        return CalculateDate.getRange(dateFirstMonday);
    }
}
