package homework6.microTasks.task1;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class DoubleStreamProcessorTest {
    @Test
    public void emptyStreamTest() {
        NullPointerException ex = assertThrows(NullPointerException.class, () ->
                DoubleStreamProcessor.getQuantityNull(null)
        );
        assertEquals("Пустой поток.", ex.getMessage());

        ex = assertThrows(NullPointerException.class, () ->
                DoubleStreamProcessor.hasDoubleValue(null)
        );
        assertEquals("Пустой поток.", ex.getMessage());

        ex = assertThrows(NullPointerException.class, () ->
                DoubleStreamProcessor.differenceBetweenMinMax(null)
        );

        assertEquals("Пустой поток.", ex.getMessage());

        ex = assertThrows(NullPointerException.class, () ->
                DoubleStreamProcessor.filterValues(null, 1)
        );
        assertEquals("Пустой поток.", ex.getMessage());
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () ->
                DoubleStreamProcessor.filterValues(Arrays.stream(new double[0]), Double.NaN)
        );
        assertEquals("Лимит - не число.", ex2.getMessage());

        ex = assertThrows(NullPointerException.class, () ->
                DoubleStreamProcessor.maxLength(null)
        );
        assertEquals("Пустой поток.", ex.getMessage());
    }

    @Test
    public void threeValuesTest() {
        double[] d = {0, 0, 0};

        assertEquals(3, DoubleStreamProcessor.getQuantityNull(Arrays.stream(d)));

        assertFalse(DoubleStreamProcessor.hasDoubleValue(Arrays.stream(d)));

        assertEquals(0, DoubleStreamProcessor.differenceBetweenMinMax(Arrays.stream(d)), 0);


        double[] val = DoubleStreamProcessor.filterValues(Arrays.stream(d), -1);
        for (int i = 0; i < val.length; i++) {
            assertEquals(0, val[i], 0);
        }

        assertEquals(0, DoubleStreamProcessor.maxLength(Arrays.stream(d)), 0);
    }

    @Test
    public void fiveValuesTest() {
        double[] d = {0.1, 0.33333, 0.555555, 0.6, 0.8888};

        assertEquals(0, DoubleStreamProcessor.getQuantityNull(Arrays.stream(d)));

        assertTrue(DoubleStreamProcessor.hasDoubleValue(Arrays.stream(d)));

        assertEquals(0.78, DoubleStreamProcessor.differenceBetweenMinMax(Arrays.stream(d)), 0.1);


        double[] val = DoubleStreamProcessor.filterValues(Arrays.stream(d), 0.5);
        double[] res = {0.555555, 0.6, 0.8888};
        for (int i = 0; i < val.length; i++) {
            assertEquals(res[i], val[i], 0);
        }

        assertEquals(0.55, DoubleStreamProcessor.maxLength(Arrays.stream(d)), 0.1);
    }

    @Test
    public void ManyValuesTest() {
        double[] d = new double[]{
                1, 0, 0, -7, 8,
                0, 0, 12345.0987654321d, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0
        };
        Random random = new Random(2);
        for (int i = 8; i < d.length; i++) {
            d[i] = random.nextDouble(-10, 20);
        }

        assertEquals(4, DoubleStreamProcessor.getQuantityNull(Arrays.stream(d)));

        assertTrue(DoubleStreamProcessor.hasDoubleValue(Arrays.stream(d)));

        assertEquals(12354.183368572954d, DoubleStreamProcessor.differenceBetweenMinMax(Arrays.stream(d)), 0.1);


        double[] val = DoubleStreamProcessor.filterValues(Arrays.stream(d), -10);
        assertEquals(d.length, val.length);

        val = DoubleStreamProcessor.filterValues(Arrays.stream(d), 12355d);
        assertEquals(0, val.length);

        val = DoubleStreamProcessor.filterValues(Arrays.stream(d), 19);
        assertEquals(3, val.length);

        double[] res = {12345.0987654321d, 19.576307997086047d, 19.6226250169528d};
        for (int i = 0; i < val.length; i++) {
            assertEquals(res[i], val[i], 0);
        }

        assertEquals(-3.1552620887974694, DoubleStreamProcessor.maxLength(Arrays.stream(d)), 0);
    }
}