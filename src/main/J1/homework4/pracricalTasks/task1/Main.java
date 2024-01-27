package j1.homework4.pracricalTasks.task1;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        testArray(1, 5, 5, 2, 2);
        testArray(1, 2, 1, 3, 3);
        testArray(1, 5, 3, 2, 5);
        testArray(1, 5, 10, 3, 5, new int[]{ 1, 8, 1, 9, 6, 4, 7, 8, 3, 10});
        testArray(1, 9, 100_000, 5, 200_000);
    }

    private static void testArray(int origin, int bound, int len, int mode, int sampleLen, int[]... array) {
        System.out.println("\n\n------------------------------");
        System.out.println("Запуск с массивом длины: " + len);
        int[] randomArray = new int[len];

        System.out.println("Получившийся массив: ");
        if (array.length == 1) {
            randomArray = array[0];
            for (int j : randomArray) {
                System.out.print(j + ", ");
            }
        } else {
            Random random = new Random(2);
            for (int i = 0; i < randomArray.length; i++) {
                int a = random.nextInt(origin, bound);
                randomArray[i] = a;
                if(randomArray.length <= 1000) {
                    System.out.print(randomArray[i] + ", ");
                }
            }
            if(randomArray.length > 1000){
                System.out.println("Длина массива слишком велика для вывода: " + randomArray.length);
            }
        }

        ArrayStatistics arrayStatistics = new ArrayStatistics(randomArray);

        printMode(arrayStatistics, mode);
        printMedian(arrayStatistics);
        printAverage(arrayStatistics);
        printVariance(arrayStatistics);
        printGeometricMean(arrayStatistics);
        printShuffleValues(arrayStatistics);
        printSample(arrayStatistics, sampleLen);
        System.out.println("\n------------------------------");
    }

    private static void printMode(ArrayStatistics arrayStatistics, int mode) {
        int[] a = arrayStatistics.getMode(mode);
        System.out.println("\nМода: ");
        for (int j : a) {
            System.out.print(j + ", ");
        }
    }

    private static void printMedian(ArrayStatistics arrayStatistics) {
        System.out.println("\nМедиана: " + arrayStatistics.getMedian());
    }

    private static void printAverage(ArrayStatistics arrayStatistics) {
        System.out.println("\nСреднее: " + arrayStatistics.getAverage());
    }

    private static void printVariance(ArrayStatistics arrayStatistics) {
        System.out.println("\nДисперсия: " + arrayStatistics.getVariance());
    }

    private static void printGeometricMean(ArrayStatistics arrayStatistics) {
        System.out.println("\nСреднее геометрическое: " + arrayStatistics.getGeometricMean());
    }

    private static void printShuffleValues(ArrayStatistics arrayStatistics) {
        int[] shuffleArray = arrayStatistics.shuffleValues();
        System.out.println("\nПеремешанный массив: \t");
        if (shuffleArray.length <= 1000) {
            for (int j : shuffleArray) {
                System.out.print(j + ", ");
            }
        } else {
            System.out.println("Его длина слишком велика:" + shuffleArray.length);
        }
    }

    private static void printSample(ArrayStatistics arrayStatistics, int len) {
        int[] sampleArray = arrayStatistics.getSample(len);
        System.out.println("\nВыбранный массив: \t");
        if (sampleArray.length <= 1000) {
            for (int j : sampleArray) {
                System.out.print(j + ", ");
            }
        } else {
            System.out.println("Его длина слишком велика:" + sampleArray.length);
        }

    }
}
