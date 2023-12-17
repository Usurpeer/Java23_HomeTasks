package homework5.pracricalTasks.task2;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextTaskTest {

    @Test
    public void ctorWrondText() {
        String text = "";
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new TextTask(text)
        );
        assertEquals("Ошибка, входной текст пустая строка.", ex.getMessage());

        NullPointerException ex2 = assertThrows(NullPointerException.class, () ->
                new TextTask(null)
        );
        assertEquals("Ошибка, входной текст null.", ex2.getMessage());
    }

    @Test
    public void findSubstringTest() {
        String str = "Abb Ab ab assdsaB";
        TextTask textTask = new TextTask(str);

        assertArrayEquals(new int[]{0, 4, 7, 15}, textTask.findSubstring("AB"));

        String str2 = "ABb Ab ab assdsaB";
        TextTask textTask2 = new TextTask(str2);

        assertArrayEquals(new int[]{0}, textTask2.findSubstring("AB", true));

        String str3 = "a";
        TextTask textTask3 = new TextTask(str3);

        assertArrayEquals(new int[0], textTask3.findSubstring("", true));
    }

    @Test
    public void replaceTextTest() {
        String str = "Abb Ab ab assdsaB";
        TextTask textTask = new TextTask(str);

        assertEquals(4, textTask.replace("AB", "ac"));
        assertEquals("acb ac ac assdsac", textTask.getText());

        String str2 = "Abb Ab ab assdsaB";
        TextTask textTask2 = new TextTask(str2);

        assertEquals(0, textTask2.replace("AB", "ac", true));
        assertEquals("Abb Ab ab assdsaB", textTask2.getText());

        String str3 = "Abb Ab ab assdsaB";
        TextTask textTask3 = new TextTask(str3);

        assertEquals(str3.length() + 1, textTask3.replace("", "ac", true));
        // после и перед каждым символом добавляется строка, если такое не надо, то добавить проверку на substring
        assertEquals("acAacbacbac acAacbac acaacbac acaacsacsacdacsacaacBac", textTask3.getText());

        String str4 = "Abb Ab ab assdsaB";
        TextTask textTask4 = new TextTask(str4);

        assertEquals(4, textTask4.replace("ab", ""));
        // после и перед каждым символом добавляется строка, если такое не надо, то добавить проверку на substring
        assertEquals("b   assds", textTask4.getText());
    }

    @Test
    public void findSubstringExceptionTest() {
        String str = "Abb Ab ab assdsaB";
        TextTask textTask = new TextTask(str);

        NullPointerException ex = assertThrows(NullPointerException.class, () ->
                textTask.findSubstring(null)
        );
        assertEquals("Ошибка, входная подстрока null.", ex.getMessage());
    }

    @Test
    public void replaceTextExceptionTest() {
        String str = "Abb Ab ab assdsaB";
        TextTask textTask = new TextTask(str);

        NullPointerException ex = assertThrows(NullPointerException.class, () ->
                textTask.replace(null, "")
        );
        assertEquals("Ошибка, входная подстрока null.", ex.getMessage());

        ex = assertThrows(NullPointerException.class, () ->
                textTask.replace("", null)
        );
        assertEquals("Ошибка, входная подстрока на замену null.", ex.getMessage());
    }
}