package j1.homework6.microTasks.task2;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Parsing {
    public static String parseText(String str) {

        return
                Arrays.stream(str.split("\\s+|[-;(),.'`\"]"))
                        .filter(a -> a.length() >= 3)
                        .map(String::toUpperCase)
                        .map(a -> a.replaceAll("[,./?!':;]", ""))
                        .distinct()
                        .sorted()
                        .collect(Collectors.joining(" "));}
}
