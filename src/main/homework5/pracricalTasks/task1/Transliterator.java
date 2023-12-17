package homework5.pracricalTasks.task1;

import java.util.HashMap;
import java.util.Map;

public class Transliterator {

    final static Map<String, String> toRusMap = getMapToRus();
    final static Map<String, String> toLatinMap = getMapToLatin();

    private static Map<String, String> getMapToRus() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "а");
        map.put("b", "б");
        map.put("v", "в");
        map.put("g", "г");
        map.put("d", "д");
        map.put("e", "е");
        map.put("yo", "ё");
        map.put("zh", "ж");
        map.put("z", "з");
        map.put("i", "и");
        map.put("j", "й");
        map.put("k", "к");
        map.put("l", "л");
        map.put("m", "м");
        map.put("n", "н");
        map.put("o", "о");
        map.put("p", "п");
        map.put("r", "р");
        map.put("s", "с");
        map.put("t", "т");
        map.put("u", "у");
        map.put("f", "ф");
        map.put("kh", "х");
        map.put("cz", "ц");
        map.put("ch", "ч");
        map.put("sh", "ш");
        map.put("sz", "щ");
        map.put("y", "ы");
        map.put("`", "ъ");
        map.put("'", "ь");
        map.put("e'", "э");
        map.put("yu", "ю");
        map.put("ya", "я");
        map.put("x", "кс");
        return map;
    }

    private static Map<String, String> getMapToLatin() {
        Map<String, String> map = new HashMap<>();
        map.put("а", "a");
        map.put("б", "b");
        map.put("в", "v");
        map.put("г", "g");
        map.put("д", "d");
        map.put("е", "e");
        map.put("ё", "yo");
        map.put("ж", "zh");
        map.put("з", "z");
        map.put("и", "i");
        map.put("й", "j");
        map.put("к", "k");
        map.put("л", "l");
        map.put("м", "m");
        map.put("н", "n");
        map.put("о", "o");
        map.put("п", "p");
        map.put("р", "r");
        map.put("с", "s");
        map.put("т", "t");
        map.put("у", "u");
        map.put("ф", "f");
        map.put("х", "kh");
        map.put("ц", "cz");
        map.put("ч", "ch");
        map.put("ш", "sh");
        map.put("щ", "sz");
        map.put("ы", "y");
        map.put("ъ", "'");
        map.put("ь", "`");
        map.put("э", "e'");
        map.put("ю", "yu");
        map.put("я", "ya");
        return map;
    }


    private static String getSymbol(String s, Map<String, String> transMap) {
        boolean charCase = Character.isUpperCase(s.charAt(0));
        String result = transMap.get(s.toLowerCase());
        return result == null ? "" : (charCase ? (result.charAt(0) + "").toUpperCase() +
                (result.length() > 1 ? result.substring(1) : "") : result);
    }

    public static String transToLang(String text, boolean isLatin) {
        // это латиница
        if (isLatin) {
            return transLang(text, toRusMap);
        } else {
            return transLang(text, toLatinMap);
        }
    }

    private static String transLang(String text, Map<String, String> langMap) {
        if (text.isEmpty()) {
            return text;
        }
        if (text.length() == 1) {
            return getSymbol(text, langMap);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); ) {
            String toTranslate = text.substring(i, i <= text.length() - 2 ? i + 2 : i + 1);
            String translated = getSymbol(toTranslate, langMap);

            if (translated.isEmpty()) {
                translated = getSymbol(toTranslate.charAt(0) + "", langMap);
                stringBuilder.append(translated.isEmpty() ? toTranslate.charAt(0) : translated);
                i++;
            } else {
                stringBuilder.append(translated);
                i += 2;
            }
        }
        return stringBuilder.toString();
    }
}
