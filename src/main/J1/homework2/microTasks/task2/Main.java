package j1.homework2.microTasks.task2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        GaussRandom gaussRandom = new GaussRandom(1);

        double[] standardNextNormal = new double[1000];
        double[] overloadNextNormal = new double[standardNextNormal.length];

        for (int i = 0; i < standardNextNormal.length; i++) {
            standardNextNormal[i] = gaussRandom.nextNormal();
            overloadNextNormal[i] = gaussRandom.nextNormal(1, 1);
        }

        int[] standardHits = CalcucateHits.getHits(50, standardNextNormal);
        int[] overloadHits = CalcucateHits.getHits(50, overloadNextNormal);

        System.out.println("Получившиеся попадания значений встроенного метода");
        System.out.println(Arrays.toString(standardHits));

        System.out.println("Получившиеся попадания значений собственного метода");
        System.out.println(Arrays.toString(overloadHits));

        System.out.println("\nКак мне кажется результаты достаточно похожи, по крайней мере, на нормальное " +
                "распределение.");
    }
}
