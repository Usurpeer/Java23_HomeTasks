package j1.homework4.pracricalTasks.task2;

public class OptimizedDeliveriesFuel {
    private double[][] suppliersValues; // таблица данных поставщиков, доставки
    private Azs[] azsVolumes; // объем поставок
    private double[][] calculationTableResult = new double[6][6]; // таблица поставок, сумм, которую нужно вывести
    private double totalCost; // сумма столбцов правой части таблицы

    public OptimizedDeliveriesFuel(double[][] suppliersValues, Azs[] supplyVolumes) {
        if (supplyVolumes == null) {
            throw new NullPointerException("Нулевые значения массива необходимых поставок.");
        }
        if (suppliersValues == null) {
            throw new NullPointerException("Нулевые значения массива данных поставщиков.");
        }
        if(supplyVolumes.length == 0){
            throw new IllegalArgumentException("Недопустимый размер массива необходимых поставок.");
        }
        if (suppliersValues.length == 0 || suppliersValues[0].length == 0) {
            throw new IllegalArgumentException("Недопустимый размер массива данных поставщиков.");
        }


        this.suppliersValues = copyDoubleArray(suppliersValues);

        this.azsVolumes = copyAzsArray(supplyVolumes);

        calculationTableResult[calculationTableResult.length - 1][calculationTableResult[0].length - 1]
                = Integer.MAX_VALUE; // сумма с учетом доставки

        calcByRecursivePermutation(this.azsVolumes.length, this.azsVolumes);
        this.totalCost = calcTotalCost(calculationTableResult);
    }

    // Метод переставляет АЗС в массиве azsVolumes, для каждой перестановки вызывает calculateArray
    private void calcByRecursivePermutation(int n, Azs[] azsVolumes) {
        if (n == 1) {
            calculateTableResult(azsVolumes);
        } else {
            for (int i = 0; i < n - 1; i++) {
                calcByRecursivePermutation(n - 1, azsVolumes);
                if (n % 2 == 0) {
                    swap(azsVolumes, i, n - 1);
                } else {
                    swap(azsVolumes, 0, n - 1);
                }
            }
            calcByRecursivePermutation(n - 1, azsVolumes);
        }
    }

    private static void swap(Azs[] elements, int a, int b) {
        Azs tmp = elements[a].getAzs();
        elements[a] = elements[b].getAzs();
        elements[b] = tmp;
    }

    // метод вычисляет лучший calculationTableResult для одной перестановки azsVolumes
    private void calculateTableResult(Azs[] azsVolumes) {

        double[][] suppliersValues = copyDoubleArray(this.suppliersValues); // копия массива данных поставщиков
        // из нее будут вычитаться данные при подсчете
        double[][] tableResult = new double[6][6]; // вычисления для 4-х АЗС

        for (int i = 0; i < azsVolumes.length; i++) {
            // метод получает лучший исход для конкретной АЗС из имеющихся данных таблиц
            // по ссылке suppliersValues и tableResult изменяются
            calculateBestAzsResult(suppliersValues, azsVolumes[i], tableResult);
        }

        // если вычисление при текущей последовательности АЗС была дешевле, то заменить
        if (calcTotalCost(tableResult) < calcTotalCost(this.calculationTableResult)) {
           /* for (int i = 0; i < tableResult.length; i++) {
                for (int j = 0; j < tableResult[i].length; j++) {
                    this.calculationTableResult[i][j] = tableResult[i][j];
                }
            }*/
            this.calculationTableResult = copyDoubleArray(tableResult);
        }
    }

    private double calcTotalCost(double[][] resArray) {
        double cost = 0;
        for (int i = 0; i < resArray[0].length; i++) {
            resArray[i][5] = (double) Math.round(resArray[i][5] * 100) / 100;
            cost += resArray[i][5];
        }
        return cost;
    }

    private void calculateBestAzsResult(double[][] suppliersValues, Azs azs, double[][] tableResult) {
        int iAzsInTableResult = 0; // индекс значений конкретной АЗС в таблице tableResult
        int iAzsInSuppliersValues = 2; // индекс значений конкретной АЗС в таблице suppliersValues
        if (azs.getTitle().equals("Б")) {
            iAzsInTableResult = 1;
            iAzsInSuppliersValues = 3;
        } else if (azs.getTitle().equals("В")) {
            iAzsInTableResult = 2;
            iAzsInSuppliersValues = 4;
        } else if (azs.getTitle().equals("Г")) {
            iAzsInTableResult = 3;
            iAzsInSuppliersValues = 5;
        }

        // копии значений, так как промежуточные вычисления и нужно сохранить исходные значения
        double[][] suppliersValuesCopy = copyDoubleArray(suppliersValues);
        double[][] tableResultCopy = copyDoubleArray(tableResult);
        Azs azs1 = azs.getAzs();

        // перебор вычислений лучшей стоимости для текущей азс от каждого поставщика, в случае, если поставщик
        // заканчивается - продолжает вычитать из остальных
        double costOneAzs = recursiveCalcFuel(suppliersValuesCopy, azs1, tableResultCopy, iAzsInTableResult,
                iAzsInSuppliersValues, 0);


        for (int i = 1; i < suppliersValues[0].length; i++) {

            double[][] suppliersValuesCopy2 = copyDoubleArray(suppliersValues); // копия
            double[][] resultCopy = copyDoubleArray(tableResult);
            Azs azs2 = azs.getAzs();

            double costFuelSupToAzs2 = recursiveCalcFuel(suppliersValuesCopy2, azs2, resultCopy, iAzsInTableResult,
                    iAzsInSuppliersValues, i);

            // если получился более дешевый маршрут, то сохранить
            if (costFuelSupToAzs2 < costOneAzs) {
                suppliersValuesCopy = suppliersValuesCopy2;
                tableResultCopy = resultCopy;
            }
        }

        // эти циклы нужны, потому что даже собственный copy не хочет копировать значения, а копирует ссылку...
        //suppliersValues = copyDoubleArray(suppliersValuesCopy);
        //tableResult = copyDoubleArray(tableResultCopy);
        for (int i = 0; i < suppliersValues.length; i++) {
            for (int j = 0; j < suppliersValues[i].length; j++) {
                suppliersValues[i][j] = suppliersValuesCopy[i][j];
            }
        }
        for (int i = 0; i < tableResult.length; i++) {
            for (int j = 0; j < tableResult[i].length; j++) {
                tableResult[i][j] = tableResultCopy[i][j];
            }
        }
    }

    // рекурсивный алгоритм вычитания оставшихся объемов с проверкой на пустоту
    // такой большой метод, а не много маленьких, потому что здесь много ссылочных
    // действий и это были бы еще большие проблемы с отладкой
    private double recursiveCalcFuel(double[][] suppliersValues, Azs azs, double[][] tableResult, int iAzsInTableResult,
                                     int iAzsInSuppliersValues, int currentSupplier) {
        int valueAzs = azs.getValue();
        double costFuelAzs = 0;

        // если топлива хватает для заправки АЗС, то просто взять полностью
        if (suppliersValues[currentSupplier][0] >= valueAzs) {
            suppliersValues[currentSupplier][0] -= valueAzs; // получение остатка
            
            costFuelAzs += valueAzs * suppliersValues[currentSupplier][1]; // вычисление стоимости без доставки

            tableResult[currentSupplier][iAzsInTableResult] = valueAzs; // сохранение объема
            tableResult[currentSupplier][4] += costFuelAzs; // сохранение стоимости без доставки

            costFuelAzs += suppliersValues[currentSupplier][iAzsInSuppliersValues]; // вычисление с доставкой

            tableResult[currentSupplier][5] += costFuelAzs; // сохранение с доставкой
        } else {
            // тк это объема поставщика не хватает, то его объем - множитель
            costFuelAzs += suppliersValues[currentSupplier][0] * suppliersValues[currentSupplier][1]; 

            tableResult[currentSupplier][4] += costFuelAzs;

            costFuelAzs += suppliersValues[currentSupplier][iAzsInSuppliersValues]; // доставка

            tableResult[currentSupplier][5] += costFuelAzs; // сохранение
            tableResult[currentSupplier][iAzsInTableResult] = suppliersValues[currentSupplier][0];

            valueAzs -= (int) suppliersValues[currentSupplier][0]; // остаток
            Azs azs1 = new Azs(azs.getTitle(), valueAzs); // сохранение его в объект

            suppliersValues[currentSupplier][0] = 0; // обнуление
            
            // далее рекурсивные оптимальные вычисления следующего поставщика, тк не всё получили
            double[][] suppliersValuesCopy = copyDoubleArray(suppliersValues);
            double[][] tableResultCopy = copyDoubleArray(tableResult);

            // если хотя бы у 1-го поставщика есть топливо
            if (isNotOverFuel(suppliersValues)) {
                double newCostFuelAzs = Double.MAX_VALUE;

                for (int i = 0; i < 6; i++) {
                    if (suppliersValues[i][0] > 0) {

                        double[][] suppliersValuesCopy2 = copyDoubleArray(suppliersValues);
                        double[][] tableResultCopy2 = copyDoubleArray(tableResult);

                        // вычисление "остатка" рекурсивно оптимально перебором
                        double newSumRes2 = recursiveCalcFuel(suppliersValuesCopy2, azs1, tableResultCopy2,
                                iAzsInTableResult, iAzsInSuppliersValues, i);

                        if (newSumRes2 < newCostFuelAzs) {
                            newCostFuelAzs = newSumRes2;

                            for (int k = 0; k < suppliersValuesCopy.length; k++) {
                                for (int j = 0; j < suppliersValuesCopy[k].length; j++) {
                                    suppliersValuesCopy[k][j] = suppliersValuesCopy2[k][j];
                                }
                            }
                            for (int k = 0; k < tableResultCopy.length; k++) {
                                for (int j = 0; j < tableResultCopy[k].length; j++) {
                                    tableResultCopy[k][j] = tableResultCopy2[k][j];
                                }
                            }
                        }
                    }

                }
                costFuelAzs += newCostFuelAzs;
            }
            // эти циклы нужны, потому что даже собственный copy не хочет копировать значения, а копирует ссылку...
            for (int i = 0; i < suppliersValues.length; i++) {
                for (int j = 0; j < suppliersValues[i].length; j++) {
                    suppliersValues[i][j] = suppliersValuesCopy[i][j];
                }
            }
            for (int i = 0; i < tableResult.length; i++) {
                for (int j = 0; j < tableResult[i].length; j++) {
                    tableResult[i][j] = tableResultCopy[i][j];
                }
            }

        }
        return costFuelAzs;
    }

    // все поставщики пустые
    private boolean isNotOverFuel(double[][] suppliersValues) {
        for (int i = 0; i < suppliersValues[0].length; i++) {
            if (suppliersValues[i][0] > 0) {
                return true;
            }
        }
        return false;
    }

    private Azs[] copyAzsArray(Azs[] azsVolumes) {
        Azs[] azsVolumesCopy = new Azs[azsVolumes.length];

        for (int i = 0; i < azsVolumes.length; i++) {
            azsVolumesCopy[i] = azsVolumes[i].getAzs();
        }
        return azsVolumesCopy;
    }

    private static double[][] copyDoubleArray(double[][] array) {
        double[][] newArray = new double[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double[][] getCalculationTableResult() {
        return calculationTableResult;
    }

}
