package homework5.microTasks.task1;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnicodeCharInfoTest {

    UnicodeCharInfo unicodeCharInfo = new UnicodeCharInfo();

    @Test
    public void getterSetterTest(){
        UnicodeCharInfo unicodeCharInfo1 = new UnicodeCharInfo();
        unicodeCharInfo1.setSymbol('a');
        assertEquals('a', unicodeCharInfo1.getSymbol());

        unicodeCharInfo1.setSymbol('b');
        assertEquals('b', unicodeCharInfo1.getSymbol());

    }
    @Test
    public void getUnicodeInt() {
        unicodeCharInfo.setSymbol('0');
        assertEquals(48, unicodeCharInfo.getUnicodeInt());

        unicodeCharInfo.setSymbol('N');
        assertEquals(78, unicodeCharInfo.getUnicodeInt());

        unicodeCharInfo.setSymbol('Ж');
        assertEquals(1046, unicodeCharInfo.getUnicodeInt());
    }

    @Test
    public void getUnicodeHex() {
        unicodeCharInfo.setSymbol('0');
        assertEquals("U+0030", unicodeCharInfo.getUnicodeHex());

        unicodeCharInfo.setSymbol('N');
        assertEquals("U+004E", unicodeCharInfo.getUnicodeHex());

        unicodeCharInfo.setSymbol('λ');
        assertEquals("U+03BB", unicodeCharInfo.getUnicodeHex());
    }

    @Test
    public void getNextChar() {
        unicodeCharInfo.setSymbol('0');
        assertEquals('1', unicodeCharInfo.getNextChar());

        unicodeCharInfo.setSymbol('N');
        assertEquals('O', unicodeCharInfo.getNextChar());

        unicodeCharInfo.setSymbol('λ');
        assertEquals('μ', unicodeCharInfo.getNextChar());
    }

    @Test
    public void getPrevChar() {
        unicodeCharInfo.setSymbol('0');
        assertEquals('/', unicodeCharInfo.getPrevChar());

        unicodeCharInfo.setSymbol('N');
        assertEquals('M', unicodeCharInfo.getPrevChar());

        unicodeCharInfo.setSymbol('λ');
        assertEquals('κ', unicodeCharInfo.getPrevChar());
    }

    @Test
    public void getCharType() {
        unicodeCharInfo.setSymbol('0');
        assertEquals("DIGIT", unicodeCharInfo.getCharType());

        unicodeCharInfo.setSymbol('N');
        assertEquals("LETTER_UPPER", unicodeCharInfo.getCharType());

        unicodeCharInfo.setSymbol('z');
        assertEquals("LETTER_LOVER", unicodeCharInfo.getCharType());

        unicodeCharInfo.setSymbol(' ');
        assertEquals("SPACE", unicodeCharInfo.getCharType());

        unicodeCharInfo.setSymbol('&');
        assertEquals("OTHER", unicodeCharInfo.getCharType());
    }

    @Test
    public void alphabetNumber() {
        unicodeCharInfo.setSymbol('0');
        assertEquals(-1, unicodeCharInfo.alphabetNumber());

        unicodeCharInfo.setSymbol('N');
        assertEquals(14, unicodeCharInfo.alphabetNumber());

        unicodeCharInfo.setSymbol('z');
        assertEquals(26, unicodeCharInfo.alphabetNumber());

        unicodeCharInfo.setSymbol(' ');
        assertEquals(-1, unicodeCharInfo.alphabetNumber());

        unicodeCharInfo.setSymbol('&');
        assertEquals(-1, unicodeCharInfo.alphabetNumber());
    }
}