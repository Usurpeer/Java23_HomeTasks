package homework2.microTasks.task2;

import java.util.Random;

class GaussRandom {

    private Random rnd = new Random();

    // поля, необходимые для генерации чисел с помощью Бокса-Мюллера
    private boolean isCos = true;
    private double var1 = rnd.nextDouble();
    private double var2 = rnd.nextDouble();

    GaussRandom() {
    }

    GaussRandom(long seed) {
        this.rnd.setSeed(seed);
    }

    public double nextNormal(double m, double sigma) {

        // первый вариант преобразования Бокса-Мюллера
        double sqrt = Math.sqrt(-2 * Math.log(var2));

        double z1;
        if (isCos) {
            z1 = Math.cos(2 * Math.PI * var1) * sqrt;
            isCos = false;

        } else {
            z1 = Math.sin(2 * Math.PI * var1) * sqrt;
            isCos = true;
            var1 = rnd.nextDouble();
            var2 = rnd.nextDouble();
        }

        // переход к общему нормальному распределению
        return m + sigma * z1;
    }

    public synchronized double nextNormal() {
        return this.rnd.nextGaussian();
    }
}
