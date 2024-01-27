package j1.homework7.pracricalTasks.task2;


import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ParseBrackets {
    private ParseBrackets() {
    }

    private static final Map<Character, Character> brackets = new HashMap<>();

    static {
        brackets.put(')', '(');
        brackets.put('}', '{');
        brackets.put(']', '[');
    }

    public static int parse(String textBracket) {
        if(textBracket == null){
            throw new NullPointerException("Нулевая строка");
        }
        if(textBracket.isEmpty()){
            return -1;
        }
        Deque<Character> stack = new LinkedList<>();
        for (char c : textBracket.toCharArray()) {
            if (brackets.containsValue(c)) {
                stack.push(c);
            } else if (brackets.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != brackets.get(c)) {
                    return textBracket.lastIndexOf(c);
                }
            }
        }
        return stack.isEmpty() ? -1 : textBracket.length();
    }
}