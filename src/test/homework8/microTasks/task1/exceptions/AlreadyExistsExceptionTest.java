package homework8.microTasks.task1.exceptions;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AlreadyExistsExceptionTest {
    @Test
    public void getMessage() {
        String value = "text";
        int position = 2;

        var ex = new AlreadyExistsException(value, position);
        assertEquals("""

                Текст:
                \ttext
                Уже был введен под номером: 2
                """, ex.getMessage());
    }
}