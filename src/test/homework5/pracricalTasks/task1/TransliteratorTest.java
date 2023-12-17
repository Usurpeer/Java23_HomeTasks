package homework5.pracricalTasks.task1;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransliteratorTest {

    @Test
    public void transToLangToLatinTest() {
        boolean isLatin = false;
        String rusStr = "Русские буквы, транскрипция на латинские. Если такой буквы в алфавите нет, то" +
                " она остаётся. Жцщшку йклмн";

        assertEquals("Russkie bukvy, transkripcziya na latinskie. Esli takoj bukvy v alfavite net, " +
                "to ona ostayotsya. Zhczszshku jklmn", Transliterator.transToLang(rusStr, isLatin));

        String rusStr2 = "analogichno i etot nichego ne sdelaet s latinskoi strokoi.";

        assertEquals("analogichno i etot nichego ne sdelaet s latinskoi strokoi.",
                Transliterator.transToLang(rusStr2, isLatin));
    }

    @Test
    public void transToLangToRusTest() {
        boolean isLatin = true;
        String latinStr = "Tut dolzhen byl byt' russkij tekst dlya proverki, voobsze-to ego ochen' trudno " +
                "pridumyvat'. Poe'tomu ya vospol'zovalsya generatorom. zhirnyj zhelud' zheltyj szelka";

        assertEquals("Тут должен был быть русский текст для проверки, вообще-то его очень трудно " +
                        "придумывать. Поэтому я воспользовался генератором. жирный желудь желтый щелка",
                Transliterator.transToLang(latinStr, isLatin));

        String latinStr2 = "А вот здесь должна быть строка на латинице, проверка, что метод ничего не тронет.";

        assertEquals("А вот здесь должна быть строка на латинице, проверка, что метод ничего не тронет.",
                Transliterator.transToLang(latinStr2, isLatin));
    }
}