package j1.homework7.pracricalTasks.task2;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParseBracketsTest {
    @Test
    public void parseTest(){
        assertEquals(-1, ParseBrackets.parse(""));

        assertEquals(-1, ParseBrackets.parse("()[]{}"));

        assertEquals(0, ParseBrackets.parse(")"));

        assertEquals(1, ParseBrackets.parse("["));

        assertEquals(2, ParseBrackets.parse("{}}"));

        assertEquals(4, ParseBrackets.parse("[fkv}"));

        assertEquals(-1, ParseBrackets.parse("(12+A[0])/((b{0,1} - c {6,n}) * 2)"));

        assertEquals(-1, ParseBrackets.parse("(((((()[[]]){()})())[[[]]]){[][]})"));

        assertEquals(3, ParseBrackets.parse("{[(])}"));

        assertEquals(8, ParseBrackets.parse("{a[b(c)b}a"));

    }
}