package HomeWork1.MicroTasks.Task1;

public class Task1 {
    public static void main(String[] args) {
        double[] inputArray = {30.0, 10000.1, 12.5, 99.99, 0.0, -23.45, -4.5, -129.675}; // массив входных значений

        System.out.println("\nПрограмма, которая выведет в виде таблицы округление всеми возможными методами из " +
                "класса Math");
        System.out.format("+---+-------------------+-------------------+-------------------+-------------------+" +
                "-------------------+%n");
        System.out.format("| № |  Число на входе:  +   Math.round(n)   +    Math.ceil(n)   +    Math.rint(n)   +" +
                "    Math.floor(n)  +%n");
        System.out.format("+---+-------------------+-------------------+-------------------+-------------------+" +
                "-------------------+%n");

        for (int i = 0; i < inputArray.length; i++) {
            System.out.format("| %d | %-17s | %-17s | %-17s | %-17s | %-17s |%n",
                    (i + 1),
                    inputArray[i],
                    Math.round(inputArray[i]),
                    Math.ceil(inputArray[i]),
                    Math.rint(inputArray[i]),
                    Math.floor(inputArray[i]));
        }

        System.out.format("+---+-------------------+-------------------+-------------------+-------------------+" +
                "-------------------+%n");
    }
}
