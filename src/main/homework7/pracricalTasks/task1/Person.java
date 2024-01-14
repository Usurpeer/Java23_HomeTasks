package homework7.pracricalTasks.task1;

import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private final String name;
    private TreeSet<String> favorites;

    Person(String name, String favorites) {
        this.name = name;
        setFavorites(favorites);
    }

    Person(String name, TreeSet<String> favorites) {
        this.name = name;
        setFavorites(favorites);
    }

    public String getName() {
        return name;
    }

    public TreeSet<String> getFavorites() {
        return new TreeSet<>(favorites);
    }

    // парсит строку
    public void setFavorites(String favorites) {
        // регулярка - слова, состоящие хотя бы из одного символа
        Pattern pattern = Pattern.compile("[a-zа-я]");
        Matcher matcher = pattern.matcher(favorites);

        TreeSet<String> favoritesList = new TreeSet<>();
        while (matcher.find()) {
            favoritesList.add(matcher.group());
        }

        this.favorites = new TreeSet<>(favoritesList);
    }

    public void setFavorites(TreeSet<String> favorites) {
        this.favorites = new TreeSet<>(favorites);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Имя: ");
        stringBuilder.append(name);

        stringBuilder.append("\n\tЛюбимки: ");
        for (String fav : favorites) {
            stringBuilder.append(fav).append(", ");
        }
        return stringBuilder.toString();
    }
}
