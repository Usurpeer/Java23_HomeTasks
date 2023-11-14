package HomeWork1.MicroTasks;

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        System.out.println("\nПользователь вводит последовательность произвольных рациональных чисел. \nКоличество " +
                "чисел заранее неизвестно, ввод продолжается, пока пользователь не введет пустую строку или значение," +
                " не являющееся числом. \nПодсчитайте и выведите количество введенных чисел, минимальное, " +
                "максимальное, сумму и среднее. Сохранять числа в массив не нужно.");

        double sumValues = 0; // сумма чисел
        int quantityNum = 0; // количество введеных чисел
        double maxValue = Double.MIN_VALUE; // максимальное число
        double minValue = Double.MAX_VALUE; // минимальное число
        double averageValue = 0;

        System.out.println("\nВведите значение. Для выхода - не число.");
        Scanner scanner = new Scanner(System.in);
        String inputValue = scanner.nextLine();

        while (isDouble(inputValue)) {
            double inputNum = Double.parseDouble(inputValue);

            sumValues += inputNum;
            quantityNum++;
            maxValue = Math.max(inputNum, maxValue);
            minValue = Math.min(inputNum, minValue);
            averageValue = sumValues / quantityNum;

            System.out.println("\nВведите значение. Для выхода - не число.");
            inputValue = scanner.nextLine();
        }

        System.out.println("Ввод чисел окончен.");

        if (quantityNum > 0) {
            System.out.println("\nКоличество введеных чисел: " + quantityNum);
            System.out.println("Минимальное число: " + minValue);
            System.out.println("Максимальное число: " + maxValue);
            System.out.println("Сумма введеных чисел: " + sumValues);
            System.out.println("Среднее всех чисел: " + averageValue);
        } else {
            System.out.println("Числа не были введены.");
        }

        System.out.println("Программа завершена.");
    }

    private static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
