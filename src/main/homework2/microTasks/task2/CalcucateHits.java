package homework2.microTasks.task2;

import java.lang.reflect.Array;
import java.util.Arrays;

// класс, чтобы глянуть сгенерированные числа по отрезкам
// опять же могут быть где-то погрешности, примерные интервалы
// по этим "попаданиям" строятся гистограммы
class CalcucateHits {
    public static int[] getHits(int length, double[] numbs) {
        // копирование и сортировка для ускорения будуших проходов по массиву
        double[] sortedNumbs = Arrays.copyOf(numbs, numbs.length - 1);
        Arrays.sort(sortedNumbs);

        int[] hits = new int[length]; // на сколько отрезков будет разбита выборка
        double max = sortedNumbs[sortedNumbs.length - 1];
        double min = sortedNumbs[0];

        double stepVal = (max - min) / hits.length; // размер отрезка / шаг

        // отрезок [leftVal; rightVal)
        double leftVal = min;
        double rightVal = stepVal + min;

        int j = 0;
        // проход по числам массива, заполнение массива количеств попаданий в отрезки
        for (int i = 0; i < sortedNumbs.length; i++) {
            if (sortedNumbs[i] < rightVal && sortedNumbs[i] >= leftVal) {
                hits[j]++;
            } else {
                // сдвиг отрезка до подходящего
                while (sortedNumbs[i] > rightVal && j < hits.length - 1) {
                    leftVal = rightVal;
                    rightVal += stepVal;
                    j++;
                }
            }
        }

        return hits;
    }
}
