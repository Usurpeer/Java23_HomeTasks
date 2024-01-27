package j1.homework3.microTasks.task2;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateDifference {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    public static String getDifference(String dateTime, String timezone1, String timezone2) {
        ZonedDateTime zoneUTC = ZonedDateTime.parse(dateTime, formatter);

        ZonedDateTime utcOne = zoneUTC.withZoneSameInstant(ZoneId.of(timezone1));
        ZonedDateTime utcTwo = zoneUTC.withZoneSameInstant(ZoneId.of(timezone2));

        if (utcOne.getYear() - utcTwo.getYear() != 0) {
            return "YEAR";
        } else if (utcOne.getMonthValue() - utcTwo.getMonthValue() != 0) {
            return "MONTH";
        } else if (utcOne.getDayOfMonth() - utcTwo.getDayOfMonth() != 0) {
            return "DAY";
        } else if (utcOne.getHour() - utcTwo.getHour() != 0) {
            return "HOUR";
        }

        return "0";
    }
}
