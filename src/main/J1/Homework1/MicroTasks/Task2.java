package j1.Homework1.MicroTasks;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        System.out.println("\nПользователь вводит произвольное значение. Определите и выведите тип данных по " +
                "введенному значению. \nТипы данных: Рациональное число, Целое число, Логическое, Текст.");

        while (true) {
            System.out.println("\nВведите произвольное значение: ");
            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextDouble()) {
                if (scanner.hasNextInt()) {
                    System.out.println("Реализация через методы \'hasNext...\'\n" +
                            "Введеное число является: рациональным, целым.");
                } else {
                    System.out.println("Реализация через методы \'hasNext...\'\n" +
                            "Введеное число является: рациональным, дробным.");
                }
            } else if (scanner.hasNextBoolean()) {
                System.out.println("Реализация через методы \'hasNext...\'\n" +
                        "Введеное значение является: Логическим.");
            } else {
                System.out.println("Реализация через методы \'hasNext...\'\n" +
                        "Введеное значение - строка.");
            }

            String inputValue = scanner.nextLine();
            System.out.println("\nВведеное значение: " + inputValue);

            if (isDouble(inputValue)) {
                if (isInt(inputValue)) {
                    System.out.println("Реализация через методы \'parse...\'\n" +
                            "Введеное число является: рациональным, целым.");
                } else {
                    System.out.println("Реализация через методы \'parse...\'\n'" +
                            "Введеное число является: рациональным, дробным.");
                }
            } else if (Boolean.parseBoolean(inputValue)) {
                System.out.println("Реализация через методы \'parse...\'\n" +
                        "Введеное значение является: Логическим.");
            } else {
                System.out.println("Реализация через методы \'parse...\'\n" +
                        "Введеное значение - строка.");
            }
        }
    }

    private static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
