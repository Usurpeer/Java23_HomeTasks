package j1.homework4.pracricalTasks.task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double[][] suppliersValues = {
                {600, 5.2d, 803, 952, 997, 931},
                {420, 4.5d, 967, 1012, 848, 1200},
                {360, 6.1d, 825, 945, 777, 848},
                {250, 3.8d, 1024, 1800, 931, 999},
                {700, 6.4d, 754, 817, 531, 628},
                {390, 5.6d, 911, 668, 865, 1526},
        };

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n----------------------------------------------------------------------");
            printSuppliersValues(suppliersValues);

            Azs[] azsVolumes = new Azs[]{
                    new Azs("А", 400),
                    new Azs("Б", 550),
                    new Azs("В", 280),
                    new Azs("Г", 310)
            };

            //Azs[] azsVolumes = new Azs[4];
            //inputSupplyVolumes(azsVolumes, scanner); // Ввод объема поставок на каждую АЗС*/
            //
            printSupplyVolumes(azsVolumes);
            OptimizedDeliveriesFuel optimizedDeliveriesFuel = new OptimizedDeliveriesFuel(suppliersValues, azsVolumes);

            System.out.println("Итоговая сумма: " + optimizedDeliveriesFuel.getTotalCost());
            printCalculationTableResult(optimizedDeliveriesFuel.getCalculationTableResult());

            System.out.println("\n\n\n\n\n\n----------------------------------------------------------------------");
            printSuppliersValues(suppliersValues);

            Azs[] azsVolumes2 = new Azs[]{
                    new Azs("А", 700),
                    new Azs("Б", 500),
                    new Azs("В", 700),
                    new Azs("Г", 700)
            };

            //Azs[] azsVolumes2 = new Azs[4];

            //inputSupplyVolumes(azsVolumes, scanner); // Ввод объема поставок на каждую АЗС

            printSupplyVolumes(azsVolumes2);
            OptimizedDeliveriesFuel optimizedDeliveriesFuel2 = new OptimizedDeliveriesFuel(suppliersValues,
                    azsVolumes2);

            System.out.println("Итоговая сумма: " + optimizedDeliveriesFuel2.getTotalCost());
            printCalculationTableResult(optimizedDeliveriesFuel2.getCalculationTableResult());

            System.out.println("\n\n\n\n\n\n----------------------------------------------------------------------");
            printSuppliersValues(suppliersValues);

            Azs[] azsVolumes3 = new Azs[]{
                    new Azs("А", 683),
                    new Azs("Б", 683),
                    new Azs("В", 682),
                    new Azs("Г", 682)
            };
            //Azs[] azsVolumes3 = new Azs[4];

            //inputSupplyVolumes(azsVolumes, scanner); // Ввод объема поставок на каждую АЗС

            printSupplyVolumes(azsVolumes3);
            OptimizedDeliveriesFuel optimizedDeliveriesFuel3 = new OptimizedDeliveriesFuel(suppliersValues,
                    azsVolumes3);

            System.out.println("Итоговая сумма: " + optimizedDeliveriesFuel3.getTotalCost());
            printCalculationTableResult(optimizedDeliveriesFuel3.getCalculationTableResult());
            break;
        }
        //scanner.close();
    }

    public static void inputSupplyVolumes(Azs[] azsVolumes, Scanner scanner) {
        System.out.println("\nВведите необходимые объемы поставки на АЗС \"А\": ");
        String inputValue = scanner.nextLine();

        // ввод для поставщика А
        while (!isIntPositive(inputValue)) {
            System.out.println("\nВведите необходимые объемы поставки на АЗС \"А\": ");
            inputValue = scanner.nextLine();
        }
        azsVolumes[0] = new Azs("А", Integer.parseInt(inputValue));

        System.out.println("\nВведите необходимые объемы поставки на АЗС \"Б\": ");
        inputValue = scanner.nextLine();
        // ввод для поставщика Б
        while (!isIntPositive(inputValue)) {
            System.out.println("\nВведите необходимые объемы поставки на АЗС \"Б\": ");
            inputValue = scanner.nextLine();
        }
        azsVolumes[1] = new Azs("Б", Integer.parseInt(inputValue));

        System.out.println("\nВведите необходимые объемы поставки на АЗС \"В\": ");
        inputValue = scanner.nextLine();
        // ввод для поставщика В
        while (!isIntPositive(inputValue)) {
            System.out.println("\nВведите необходимые объемы поставки на АЗС \"В\": ");
            inputValue = scanner.nextLine();
        }
        azsVolumes[2] = new Azs("В", Integer.parseInt(inputValue));

        System.out.println("\nВведите необходимые объемы поставки на АЗС \"Г\": ");
        inputValue = scanner.nextLine();
        // ввод для поставщика Г
        while (!isIntPositive(inputValue)) {
            System.out.println("\nВведите необходимые объемы поставки на АЗС \"Г\": ");
            inputValue = scanner.nextLine();
        }
        azsVolumes[3] = new Azs("Г", Integer.parseInt(inputValue));
    }

    // Необходимые объемы поставки на каждую АЗС
    public static void printSupplyVolumes(Azs[] azsVolumes) {
        System.out.println("\nНеобходимые объемы поставки на каждую АЗС.");
        System.out.format("|    А    |    Б    |    В    |     Г    |%n");
        System.out.format("| %7d | %7d | %7d | %8d |%n",
                azsVolumes[0].getValue(),
                azsVolumes[1].getValue(),
                azsVolumes[2].getValue(),
                azsVolumes[3].getValue());
    }

    private static boolean isIntPositive(String str) {
        try {
            return Integer.parseInt(str) >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static void printSuppliersValues(double[][] suppliersValues) {
        System.out.println("\nИсходная таблица данных поставщиков.");

        System.out.format("| Поставщик |    Максимум    | Цена закупки |       Стоимость доставки на АЗС        |%n");
        System.out.format("|     №     | Объема закупки |  за единицу  |    А    |    Б    |    В    |     Г    |%n");
        for (int i = 0; i < suppliersValues.length; i++) {
            System.out.format("| %9d | %14s | %12s | %-7s | %-7s | %-7s | %-8s |%n",
                    (i + 1),
                    suppliersValues[i][0],
                    suppliersValues[i][1],
                    suppliersValues[i][2],
                    suppliersValues[i][3],
                    suppliersValues[i][4],
                    suppliersValues[i][5]);
        }
    }

    public static void printCalculationTableResult(double[][] calculationTableResult) {
        System.out.println("\nИтоговая таблица вычисленных поставок.");

        System.out.format("| Поставщик |    А    |    Б    |    В    |     Г    | Стоим. зак. | С учетом доставки |%n");
        for (int i = 0; i < calculationTableResult.length; i++) {
            System.out.format("| %9d | %7s | %7s | %7s | %8s | %11s | %17s |%n",
                    (i + 1),
                    calculationTableResult[i][0],
                    calculationTableResult[i][1],
                    calculationTableResult[i][2],
                    calculationTableResult[i][3],
                    calculationTableResult[i][4],
                    calculationTableResult[i][5]);
        }
    }
}
