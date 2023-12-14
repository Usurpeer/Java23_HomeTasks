package homework4.pracricalTasks.task1;


import java.util.*;

public class ArrayStatistics {
    private final int[] values;

    ArrayStatistics(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Неверный входной массив.");
        }
        for (int value : values) {
            if (value < 0) {
                throw new IllegalArgumentException("Недопустимое значение массива: " + value);
            }
        }
        this.values = values.clone();
    }

    // одно или несколько наиболее часто встречающихся значений в массиве;
    public int[] getMode(int mode) {
        if (mode < 0) {
            throw new IllegalArgumentException("Неверный параметр: " + mode);
        }
        if (mode == 0) {
            return new int[0];
        }

        Map<Integer, Integer> countMap = new HashMap<>(values.length); // Ключ - число, значение - количество повторений

        // заполнение значений, если не добавлено 1, если добавлено +1
        for (int i : values) {
            countMap.put(i, countMap.getOrDefault(i, 1) + 1);
        }

        // b - a то, что принимает компаратор в качестве аргумента
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> countMap.get(b) - countMap.get(a));

        // Добавление всех элементов в очередь, они будут отсортированы
        heap.addAll(countMap.keySet());

        int[] res = new int[Math.min(mode, heap.size())];
        for (int i = 0; i < mode && !heap.isEmpty(); i++) {
            res[i] = heap.poll();
        }
        return res;
    }

    // значение, делящее массив пополам (ровно половина значений больше медианы и ровно половина меньше);
    public int getMedian() {
        if (values.length == 0) {
            return 0;
        }
        int[] values = this.values.clone();

        Arrays.sort(values);

        if (values.length % 2 == 0) {
            return (values[values.length / 2] + values[values.length / 2 - 1]) / 2;
        } else {
            return values[values.length / 2];
        }
    }

    // среднее арифметическое (average);
    public double getAverage() {
        return Arrays.stream(values).average().orElse(0);
    }

    // дисперсия (variance) — сумма квадратов отклонений от среднего арифметического;
    public double getVariance() {
        if (values.length == 0) {
            return 0;
        }
        double avg = getAverage();

        double sumPow = 0;

        for (int value : values) {
            sumPow += (value - avg) * (value - avg);
        }

        return sumPow / values.length;
    }

    // среднее геометрическое (geometric mean);
    public double getGeometricMean() {
        if (values.length == 0) {
            return 0.0d;
        }
        double gm = 0.0d;
        for (int value : values) {
           /*// проверяется в конструкторе
           if (value < 0) {
                throw new ArithmeticException("Недопустимо отрицательное значение в массиве: " + value);
            }*/
            if (value == 0) {
                return 0;
            }
            gm += Math.log(value);
        }
        return Math.exp(gm / values.length);
    }

    // перемешивание (shuffle) — возвращает новый массив, содержащий все элементы исходного в случайном порядке
    // (контроль: каждое исходное значение встречается столько же раз, сколько в исходном массиве);
    // если не пользоваться встроенными, то так
    public int[] shuffleValues() {
        if (values.length == 0) {
            return new int[0];
        }
        // список хранит в себе индексы массива
        // когда случайно берется индекс, он удаляется, таким образом повторений не будет
        List<Integer> valNotAddedIndex = new ArrayList<>(values.length);

        for (int i = 0; i < values.length; i++) {
            valNotAddedIndex.add(i);
        }

        Random random = new Random();
        int randListIndex;
        int[] shuffledValues = new int[values.length];
        for (int i = 0; i < shuffledValues.length; i++) {
            randListIndex = random.nextInt(0, valNotAddedIndex.size());
            shuffledValues[i] = values[valNotAddedIndex.get(randListIndex)];
            valNotAddedIndex.remove(randListIndex);
        }

        if (isCorrectShuffle(shuffledValues)) {
            return shuffledValues;
        } else {
            return new int[0];
        }
    }

    private boolean isCorrectShuffle(int[] inputArray) {
        int[] values = this.values.clone();
        Arrays.sort(values);

        int[] shuffledValues = inputArray.clone();
        Arrays.sort(shuffledValues);
        if (shuffledValues.length != values.length) {
            return false;
        }

        for (int i = 0; i < shuffledValues.length; i++) {
            if (shuffledValues[i] != values[i]) {
                return false;
            }
        }

        return true;
    }

    // выборка (sample) с параметром размера выборки — возвращает новый массив, содержащий случайные значения из
    // исходного массива (значения могут повторяться, выборка может быть как меньше, так и больше исходного массива,
    // контроль — нет посторонних чисел, они на случайных местах).
    public int[] getSample(int sizeSample) {
        if (sizeSample < 0) {
            throw new IllegalArgumentException("Недопустимый размер выборки: " + sizeSample);
        }
        if (sizeSample == 0 || values.length == 0) {
            return new int[0];
        }

        int[] sampleValues = new int[sizeSample];

        Random random = new Random();
        for (int i = 0; i < sampleValues.length; i++) {
            sampleValues[i] = values[random.nextInt(0, values.length)];
        }

        if (isCorrectSample(sampleValues)) {
            return sampleValues;
        } else {
            return new int[0];
        }
    }

    private boolean isCorrectSample(int[] sampleValues) {
        boolean isCorrect;
        for (int sampleValue : sampleValues) {
            isCorrect = false;
            for (int value : values) {
                if (sampleValue == value) {
                    isCorrect = true;
                    break;
                }
            }
            if (!isCorrect) {
                return false;
            }
        }
        return true;
    }
}
