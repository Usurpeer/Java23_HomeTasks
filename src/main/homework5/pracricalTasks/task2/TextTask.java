package homework5.pracricalTasks.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextTask {
    private String text;

    public TextTask(String text) {
        if (text == null) {
            throw new NullPointerException("Ошибка, входной текст null.");
        }
        if (text.isEmpty()) {
            throw new IllegalArgumentException("Ошибка, входной текст пустая строка.");
        }
        this.text = text;
    }

    // 1. Метод поиска получает в качестве параметра строку поиска, а возвращает массив с номерами символов для
    // каждого вхождения искомой строки в исходный текст
    public int[] findSubstring(String substring) {
        return findSubstring(substring, false);
    }

    // 3. Добавьте к обоим методам перегрузки с булевым параметром, учитывать ли регистр символов при поиске
    public int[] findSubstring(String substring, boolean caseSensitive) {
        if (substring == null) {
            throw new NullPointerException("Ошибка, входная подстрока null.");
        }
        if (substring.isEmpty()) {
            return new int[0];
        }

        if (!caseSensitive) {
            String lowerCaseText = text.toLowerCase();
            String lowerCaseSubstring = substring.toLowerCase();
            return findSubstringInText(lowerCaseText, lowerCaseSubstring);
        } else {
            return findSubstringInText(text, substring);
        }
    }

    private int[] findSubstringInText(String lowerCaseText, String lowerCaseSubstring) {
        List<Integer> indexes = new ArrayList<>();

        int index = 0;
        while (index != -1) {
            index = lowerCaseText.indexOf(lowerCaseSubstring, index);
            if (index != -1) {
                indexes.add(index);
                index++;
            }
        }
        return indexes.stream().mapToInt(i -> i).toArray();
    }

    // 2. Метод замены получает две строки: искомый текст и текст для замены. Этот метод должен вернуть количество
    // произведенных замен, а текст, получившийся после замены, сохранить вместо исходного
    public int replace(String substring, String replaceSubstring) {
        return replace(substring, replaceSubstring, false);
    }

    public int replace(String substring, String replaceSubstring, boolean caseSensitive) {
        if (substring == null) {
            throw new NullPointerException("Ошибка, входная подстрока null.");
        }
        if (replaceSubstring == null) {
            throw new NullPointerException("Ошибка, входная подстрока на замену null.");
        }

        if (!caseSensitive) {
            String lowerCaseText = text.toLowerCase();
            String lowerCaseSubstring = substring.toLowerCase();
            String lowerCaseReplaceSubstring = replaceSubstring.toLowerCase();
            return replaceText(lowerCaseText, lowerCaseSubstring, lowerCaseReplaceSubstring);
        } else {
            return replaceText(text, substring, replaceSubstring);
        }
    }

    private int replaceText(String text, String substring, String replaceSubstring) {
        Pattern p = Pattern.compile(substring, Pattern.LITERAL);
        Matcher m = p.matcher(text);

        int quantityReplace = 0;
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            quantityReplace++;
            m.appendReplacement(sb, replaceSubstring);
        }
        m.appendTail(sb);

        this.text = sb.toString();

        return quantityReplace;
    }

    public String getText() {
        return text;
    }
}
