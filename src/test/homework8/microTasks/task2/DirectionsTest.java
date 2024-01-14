package homework8.microTasks.task2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionsTest {
    @Test
    public void directionsTest() {
        assertEquals("UP", Directions.UP.getTitle());
        assertEquals("37", Directions.UP.getCode());

        Assert.assertArrayEquals(new int[]{0, 1}, Directions.UP.getVector());

        assertEquals("Клавиша: UP\n\tКод Клавиши: 37\n\tЕё массив: [0, 1]", Directions.UP.toString());
    }
}