package j1.homework6.pracricalTasks.task1;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Random;

import static org.junit.Assert.*;

public class CommentTest {
    Random random = new Random();
    @Test
    public void ctorWrongValuesTest(){
        NullPointerException ex = assertThrows(NullPointerException.class, () ->
                new Comment(null,
                        LocalDate.now().minusDays(random.nextInt(0, 5)),
                        true, "Сообщение 1")
        );
        assertEquals("Нулевое имя автора.", ex.getMessage());

        ex = assertThrows(NullPointerException.class, () ->
                new Comment("null",
                        LocalDate.now().minusDays(random.nextInt(0, 5)),
                        true, null)
        );
        assertEquals("Нулевое сообщение.", ex.getMessage());

        ex = assertThrows(NullPointerException.class, () ->
                new Comment("null",
                        null,
                        true, "")
        );
        assertEquals("Дата создания не задана.", ex.getMessage());


        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () ->
                new Comment("",
                        LocalDate.now().minusDays(random.nextInt(0, 5)),
                        true, "message")
        );
        assertEquals("Имя автора не задано.", ex2.getMessage());

        ex2 = assertThrows(IllegalArgumentException.class, () ->
                new Comment("fsfdf",
                        LocalDate.now().minusDays(random.nextInt(0, 5)),
                        true, "")
        );
        assertEquals("Сообщение не задано.", ex2.getMessage());

    }
}