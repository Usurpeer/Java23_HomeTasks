package j1.homework3.microTasks.task1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.IsoFields;
import java.util.Locale;

public class MonthInfo {
    private LocalDate date;
    private final Locale locale = new Locale("ru");
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
    //private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");

    public MonthInfo() {
        date = LocalDate.now();
        date.format(formatter);
    }

    public MonthInfo(String strDate) {
        this.date = LocalDate.parse(strDate, formatter);
    }

    public LocalDate getDate() {
        return date;
    }

    // Полное название месяца на русском языке.
    public String getMonthName() {
        return date.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, locale);
    }

    // Номер месяца в виде числа (1 — январь, 2 — февраль, …).
    public int getMonthNumber() {
        return date.getMonthValue();
    }

    // День недели первого числа месяца в виде краткого текста (пн, вт, ср, чт, пт, сб, вс).
    public String getMothWeekday() {
        return date.withDayOfMonth(1).getDayOfWeek().getDisplayName(TextStyle.SHORT_STANDALONE, locale);
    }

    // Дату последнего дня месяца.
    public LocalDate getMothLastDayDate() {
        return date.withDayOfMonth(
                date.getMonth().length(date.isLeapYear()));
    }

    // Количество дней в месяце.
    public int getMonthQuantityDays() {
        return date.getMonth().length(date.isLeapYear());
    }


    // Номер квартала, к которому относится месяц, с годом и приставкой Q ("2023 Q1",
    //"2023 Q2", "2023 Q3", "2023 Q4").
    public String getMonthQuarter() {
        return date.getYear() + " Q" + date.get(IsoFields.QUARTER_OF_YEAR);
    }
}
