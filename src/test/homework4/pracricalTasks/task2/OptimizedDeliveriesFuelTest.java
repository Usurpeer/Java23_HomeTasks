package homework4.pracricalTasks.task2;

import org.junit.Test;

import static org.junit.Assert.*;

public class OptimizedDeliveriesFuelTest {

    @Test
    public void exceptionTest() {
        double[][] suppliersValues = new double[0][0];
        Azs[] supplyVolumes = new Azs[]{
                new Azs("А", 400),
                new Azs("Б", 550),
                new Azs("В", 280),
                new Azs("Г", 310)
        };

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new OptimizedDeliveriesFuel(suppliersValues, supplyVolumes)
        );
        assertEquals("Недопустимый размер массива данных поставщиков.", ex.getMessage());

        Azs[] supplyVolumes2 = new Azs[0];
        ex = assertThrows(IllegalArgumentException.class, () ->
                new OptimizedDeliveriesFuel(new double[1][1], supplyVolumes2)
        );
        assertEquals("Недопустимый размер массива необходимых поставок.", ex.getMessage());


        NullPointerException ex2 = assertThrows(NullPointerException.class, () ->
                new OptimizedDeliveriesFuel(null, supplyVolumes)
        );
        assertEquals("Нулевые значения массива данных поставщиков.", ex2.getMessage());


        ex2 = assertThrows(NullPointerException.class, () ->
                new OptimizedDeliveriesFuel(suppliersValues, null)
        );
        assertEquals("Нулевые значения массива необходимых поставок.", ex2.getMessage());
    }


    @Test
    public void algorithmOptimalityTest() {
        double[][] suppliersValues = {
                {600, 5.2d, 803, 952, 997, 931},
                {420, 4.5d, 967, 1012, 848, 1200},
                {360, 6.1d, 825, 945, 777, 848},
                {250, 3.8d, 1024, 1800, 931, 999},
                {700, 6.4d, 754, 817, 531, 628},
                {390, 5.6d, 911, 668, 865, 1526},
        };
        Azs[] supplyVolumes = new Azs[]{
                new Azs("А", 400),
                new Azs("Б", 550),
                new Azs("В", 280),
                new Azs("Г", 310)
        };
        OptimizedDeliveriesFuel optimizedDeliveriesFuel = new OptimizedDeliveriesFuel(suppliersValues, supplyVolumes);
        assertTrue(13572.0d > optimizedDeliveriesFuel.getTotalCost());
    }
}