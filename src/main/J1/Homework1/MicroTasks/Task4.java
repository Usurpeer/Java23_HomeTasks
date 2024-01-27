package j1.Homework1.MicroTasks;

import java.util.Random;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        System.out.println("\nНеобходимо сгенерировать N случайных чисел в диапазоне [-1000; 1000] и определить самую" +
                " длиннную серию знакоповторений (количество идущих подряд чисел одного знака). \n0 не является ни " +
                "положительным, ни отрицательным числом и прерывает серию. \nЧисло N вводит пользователь. Сохранять " +
                "сгенерированные числа не нужно.");

        System.out.println("\nВведите N - размерность массива.");
        Scanner scanner = new Scanner(System.in);
        String inputValue = scanner.nextLine();

        // бесконечный цикл, пока не будет введен INT
        while (!isInt(inputValue)) {
            System.out.println("Введите N - размерность массива.");
            inputValue = scanner.nextLine();
        }

        Random rnd = new Random();
        int prevNumber = rnd.nextInt(2001) - 1000;
        printSign(prevNumber);
        int generatedNumber;

        int quantityRepetitions = 1; // текущее количество знакоповторений
        int maxQuantityRepetitions = 1; // максимальная зафиксированное количество знакоповторений
        int n = Integer.parseInt(inputValue);

        for (int i = 0; i < n - 1; i++) {
            generatedNumber = rnd.nextInt(2001) - 1000;
            printSign(generatedNumber);

            if (generatedNumber == 0) {
                quantityRepetitions = 1;
            } else if (Math.signum(prevNumber) == Math.signum(generatedNumber) && prevNumber != 0) {
                quantityRepetitions++;
            } else {
                quantityRepetitions = 1;
            }
            maxQuantityRepetitions = Math.max(quantityRepetitions, maxQuantityRepetitions);
            prevNumber = generatedNumber;
        }

        System.out.println("\nСамая длинная серия: " + maxQuantityRepetitions);
    }

    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void printSign(int val) {
        if (val == 0) {
            System.out.print("0");
        } else if (val < 0) {
            System.out.print("-");
        } else {
            System.out.print("+");
        }
    }
}
