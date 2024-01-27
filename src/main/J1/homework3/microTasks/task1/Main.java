package j1.homework3.microTasks.task1;

class Main {
    public static void main(String[] args) {
        printDate();
    }

    public static void printDate() {
        // MonthInfo monthInfo = new MonthInfo("2023-11-12");
        MonthInfo monthInfo = new MonthInfo();

        System.out.println(monthInfo.getDate());
        System.out.println("Полное название месяца на русском языке.\n\t" + monthInfo.getMonthName());
        System.out.println("Номер месяца в виде числа (1 — январь, 2 — февраль, …).\n\t" + monthInfo.getMonthNumber());
        System.out.println("День недели первого числа месяца в виде краткого текста (пн, вт, ср, чт, пт, сб, вс).\n\t" + monthInfo.getMothWeekday());
        System.out.println("Дату последнего дня месяца.\n\t" + monthInfo.getMothLastDayDate());
        System.out.println("Количество дней в месяце.\n\t" + monthInfo.getMonthQuantityDays());
        System.out.println("Номер квартала, к которому относится месяц, с годом и приставкой Q (\"2023 Q1\"," +
                "\"2023 Q2\", \"2023 Q3\", \"2023 Q4\").\n\t" + monthInfo.getMonthQuarter());
    }
}
