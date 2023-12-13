package Homework1.PracticalTasks;

import java.time.Duration;
import java.util.Scanner;

public class DateByMinutes {
    public static void main(String[] args) {
        System.out.println("\nПользователь вводит число минут, оставшихся до какого-то события. \nНеобходимо вывести," +
                " сколько это дней, часов, минут. \nНапример, 1923 - это 1 день, 8 часов, и 1 минута." +
                "\n---Все числа целые. \n---Если введено число меньше или равное 0, вывести \"Уже началось!\". " +
                "\n---Единицы времени больше дней (недели, месяцы, годы) выводить не нужно. \n---Оконччания всех слов" +
                " должны согласовываться с числом (1 день, 3 дня, 1005 дней, 21 минута и тд.). Желательно выделить " +
                "отдельный метод, который будет выбирать нужный вариант окончания/слова. \n---Циклы для решения не " +
                "нужны. \n---Добавьте возможность передать исходное число минут как аргумент командной строки.");

        String inputVal;
        Scanner scanner = new Scanner(System.in);

        if (args.length == 1) {
            inputVal = args[0];
        } else {
            System.out.println("\nВведите целое число минут.");
            inputVal = scanner.nextLine();
        }


        while (!isLong(inputVal)) {
            System.out.println("\nВведите целое число минут.");
            inputVal = scanner.nextLine();
        }

        long manyMinutes = Long.parseLong(inputVal);
        if (manyMinutes > 0) {
            Duration d = Duration.ofMinutes(manyMinutes);

            long days = d.toDaysPart();
            int hours = d.toHoursPart();
            int minutes = d.toMinutesPart();

            printDate(days, hours, minutes);
        } else {
            System.out.println("Уже началось!");
        }
    }

    private static void printDate(long days, int hours, int minutes) {
        String dayAddition = getAddition(days, 0);
        String hoursAddition = getAddition(hours, 1);
        String minutesAddition = getAddition(minutes, 2);

        System.out.print(days + " " + dayAddition);
        System.out.print(" " + hours + " " + hoursAddition);
        System.out.print(" " + minutes + " " + minutesAddition);
    }

    private static String getAddition(long val, int additionIndex) {
        String[][] addition = {
                {"дней", "день", "дня"},
                {"часов", "час", "часа"},
                {"минут", "минута", "минуты"},
        };

        long preLastDigit = val % 100 / 10;

        if (preLastDigit == 1) {
            return addition[additionIndex][0];
        }

        return switch ((int) (val % 10)) {
            case 1 -> addition[additionIndex][1];
            case 2, 3, 4 -> addition[additionIndex][2];
            default -> addition[additionIndex][0];
        };
    }

    private static boolean isLong(String val) {
        try {
            Long.parseLong(val);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
