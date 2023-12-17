package homework6.microTasks.task2;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParsingTest {

    @Test
    public void parseText() {
        assertEquals("", Parsing.parseText(""));

        assertEquals("", Parsing.parseText("Да; и; но ((4))"));

        assertEquals("ДЕНЬ ДОБРЫЙ", Parsing.parseText("Добрый день!"));

        assertEquals("ЗЕЛЕНЫЙ СИНЕ", Parsing.parseText("сине-зеленый"));

        assertEquals("БЕЗ КОФЕ МОЛОКОМ САХАРА ЧАШКА", Parsing.parseText("Чашка кофе с молоком без сахара."));

        assertEquals("ЕЩЕ МНОГО РАЗ", Parsing.parseText("Эх раз, да еще раз,  да еще много-много " +
                "раз!"));
        assertEquals("ADIPISCING ALIQUA ALIQUIP AMET ANIM AUTE CILLUM COMMODO CONSECTETUR CONSEQUAT CULPA CUPIDATAT " +
                        "DESERUNT DOLOR DOLORE DUIS EIUSMOD ELIT ENIM ESSE EST EXCEPTEUR EXERCITATION FUGIAT INCIDIDUNT " +
                        "IPSUM IRURE LABORE LABORIS LABORUM LOREM MAGNA MINIM MOLLIT NISI NON NOSTRUD NULLA OCCAECAT OFFICIA" +
                        " PARIATUR PROIDENT QUI QUIS REPREHENDERIT SED SINT SIT SUNT TEMPOR ULLAMCO VELIT VENIAM VOLUPTATE",
                Parsing.parseText("Lorem ipsum dolor sit amet, consectetur  adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
                        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure  dolor" +
                        " in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.  Excepteur sint " +
                        "occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
    }
}