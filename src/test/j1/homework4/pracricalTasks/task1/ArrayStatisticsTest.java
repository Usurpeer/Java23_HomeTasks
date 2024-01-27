package j1.homework4.pracricalTasks.task1;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class ArrayStatisticsTest {
    @Test
    public void ctorWrongDate() {
        int[] a = null;
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new ArrayStatistics(a)
        );
        assertEquals("Неверный входной массив.", ex.getMessage());

        int[] wrongArray = new int[]{0, 1, 2, -1, 3};
        ex = assertThrows(IllegalArgumentException.class, () ->
                new ArrayStatistics(wrongArray)
        );
        assertEquals("Недопустимое значение массива: " + wrongArray[3], ex.getMessage());
    }

    @Test
    public void getSampleTest() {
        int[] startArray = new int[]{
                1, 8, 1, 9, 6, 4, 7, 8, 3, 10
        };
        ArrayStatistics arrayStatistics = new ArrayStatistics(startArray);

        int[] sampleArr = arrayStatistics.getSample(5);
        assertEquals(5, sampleArr.length);

        assertTrue(isCorrectSample(startArray, sampleArr));

    }

    @Test
    public void isCorrectSampleTest() {
        int[] values = new int[]{
                1, 8, 1, 9, 6, 4, 7, 8, 3, 10
        };
        int[] sampleValues1 = new int[]{
                1, 8, 1, 9
        };

        assertTrue(isCorrectSample(values, sampleValues1));


        int[] sampleValues2 = new int[]{
                1, 8, 1, 9, 1, 1, 1, 1, 1, 8, 8, 8,
        };
        assertTrue(isCorrectSample(values, sampleValues2));

        int[] sampleValues3 = new int[]{
                1, 8, 12, 1, 9, 1, 13,
        };
        assertFalse(isCorrectSample(values, sampleValues3));
    }

    private boolean isCorrectSample(int[] values, int[] sampleValues) {
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

    @Test
    public void testOnEmptyArray() {
        ArrayStatistics arrayStatistics = new ArrayStatistics(new int[0]);

        assertArrayEquals(new int[0], arrayStatistics.getMode(1));
        assertArrayEquals(new int[0], arrayStatistics.getMode(0));

        assertEquals(0, arrayStatistics.getMedian());

        assertEquals(0.0d, arrayStatistics.getAverage(), 0);

        assertEquals(0.0d, arrayStatistics.getVariance(), 0);

        assertEquals(0.0d, arrayStatistics.getGeometricMean(), 0);

        assertArrayEquals(new int[0], arrayStatistics.shuffleValues());

        assertArrayEquals(new int[0], arrayStatistics.getSample(5));
    }

    @Test
    public void testOneElementArray() {
        ArrayStatistics arrayStatistics = new ArrayStatistics(new int[]{2});

        assertArrayEquals(new int[0], arrayStatistics.getMode(0));
        assertArrayEquals(new int[]{2}, arrayStatistics.getMode(1));
        assertArrayEquals(new int[]{2}, arrayStatistics.getMode(5));

        assertEquals(2, arrayStatistics.getMedian());

        assertEquals(2.0d, arrayStatistics.getAverage(), 0);

        assertEquals(0.0d, arrayStatistics.getVariance(), 0);

        assertEquals(2.0d, arrayStatistics.getGeometricMean(), 0);

        assertArrayEquals(new int[]{2}, arrayStatistics.shuffleValues());

        assertArrayEquals(new int[]{2, 2, 2, 2, 2}, arrayStatistics.getSample(5));
    }

    @Test
    public void testTreeElementsArray() {
        ArrayStatistics arrayStatistics = new ArrayStatistics(new int[]{5, 2, 5});

        assertArrayEquals(new int[0], arrayStatistics.getMode(0));
        assertArrayEquals(new int[]{5}, arrayStatistics.getMode(1));
        assertArrayEquals(new int[]{5, 2}, arrayStatistics.getMode(5));

        assertEquals(5, arrayStatistics.getMedian());

        assertEquals(4.0d, arrayStatistics.getAverage(), 0);

        assertEquals(2.0d, arrayStatistics.getVariance(), 0);

        assertEquals(3.68d, arrayStatistics.getGeometricMean(), 0.01d);

        // в каких то случаях он будет вываливаться, тк мало элементов и массив может повториться
        assertFalse(Arrays.equals(arrayStatistics.shuffleValues(), new int[]{5, 2, 5}));

        int[] sampleArr = arrayStatistics.getSample(5);
        assertEquals(5, sampleArr.length);

        assertFalse(Arrays.equals(sampleArr, new int[]{5, 2, 5}));
    }

    @Test
    public void testTenElementsArray() {
        int[] startArray = new int[]{
                1, 8, 1, 9, 6, 4, 7, 8, 3, 10
        };
        ArrayStatistics arrayStatistics = new ArrayStatistics(startArray);

        assertArrayEquals(new int[0], arrayStatistics.getMode(0));
        assertArrayEquals(new int[]{1}, arrayStatistics.getMode(1));
        assertArrayEquals(new int[]{1, 8, 9}, arrayStatistics.getMode(3));

        assertEquals(6, arrayStatistics.getMedian());

        assertEquals(5.7d, arrayStatistics.getAverage(), 0);

        assertEquals(9.61d, arrayStatistics.getVariance(), 0.1d);

        assertEquals(4.42d, arrayStatistics.getGeometricMean(), 0.01d);

        // в каких то случаях он будет вываливаться, тк мало элементов и массив может повториться
        assertFalse(Arrays.equals(arrayStatistics.shuffleValues(), startArray));

        int[] sampleArr = arrayStatistics.getSample(5);
        assertEquals(5, sampleArr.length);

        assertFalse(Arrays.equals(sampleArr, startArray));
    }

    @Test
    public void testManyElementsArray() {
        int[] startArray = new int[100_000];
        Random random = new Random(2);
        for (int i = 0; i < startArray.length; i++) {
            startArray[i] = random.nextInt(1, 9);
        }

        ArrayStatistics arrayStatistics = new ArrayStatistics(startArray);

        assertArrayEquals(new int[0], arrayStatistics.getMode(0));
        assertArrayEquals(new int[]{8}, arrayStatistics.getMode(1));
        assertArrayEquals(new int[]{8, 7, 6, 5, 2}, arrayStatistics.getMode(5));

        assertEquals(5, arrayStatistics.getMedian());

        assertEquals(4.51d, arrayStatistics.getAverage(), 0.1d);

        assertEquals(5.25d, arrayStatistics.getVariance(), 0.1d);

        assertEquals(3.77d, arrayStatistics.getGeometricMean(), 0.01d);

        assertFalse(Arrays.equals(arrayStatistics.shuffleValues(), startArray));

        int[] sampleArr = arrayStatistics.getSample(200_000);
        assertEquals(200_000, sampleArr.length);

        assertFalse(Arrays.equals(sampleArr, startArray));
    }

    @Test
    public void testMethodExceptions() {
        int[] startArray = new int[]{
                1, 8, 1, 9, 6, 4, 7, 8, 3, 10
        };
        ArrayStatistics arrayStatistics = new ArrayStatistics(startArray);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                arrayStatistics.getMode(-1)
        );
        assertEquals("Неверный параметр: " + -1, ex.getMessage());

        ex = assertThrows(IllegalArgumentException.class, () ->
                arrayStatistics.getSample(-1)
        );
        assertEquals("Недопустимый размер выборки: " + -1, ex.getMessage());

    }
}