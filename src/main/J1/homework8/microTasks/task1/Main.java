package j1.homework8.microTasks.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<String> records = new ArrayList<>();

        System.out.println("Введите неповторяющиеся записи.");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String record = scanner.nextLine();
                Records.addRecord(records, record);

                System.out.println("Введите новую запись.");
            }
        }

    }
}
