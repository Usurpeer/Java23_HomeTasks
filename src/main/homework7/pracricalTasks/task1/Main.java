package homework7.pracricalTasks.task1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nВведите ИМЯ: Любимка, Любимка,... (через запятую)");

        ArrayList<Person> persons = new ArrayList<>();
        //inputFavorites(userFavorites);
        staticValues(persons);
        printFav(persons);

        // всеобщие "любимки" - что нравится каждому из пользователей;
        printOneTask(persons);
        // все "любимки" - что нравится хотя бы одному из пользователей;
        printTwoTask(persons);
        // для каждого пользователя - что ему нравится, а всем остальным нет;
        printTreeTask(persons);
        // для каждой «любимки» - скольки пользователям она нравится.
        printFourTask(persons);
    }


    // ввод данных с клавиатуры
    public static void inputFavorites(ArrayList<Person> persons) {
        Scanner scanner = new Scanner(System.in);
        String inputValue = scanner.nextLine();

        while (!inputValue.isEmpty()) {
            addPerson(inputValue, persons);

            System.out.println("\nВведите ИМЯ: Любимка, Любимка,... (через запятую)");
            inputValue = scanner.nextLine();
        }
        scanner.close();
    }

    private static void addPerson(String inputValue, ArrayList<Person> persons) {
        // если есть хотя бы одно двоеточие
        if (inputValue.contains(":")) {
            // весь текст в нижнему регистру
            inputValue = inputValue.toLowerCase();

            int indexColon = inputValue.indexOf(":");
            // получить имя - вся строка до первого двоеточия
            String name = inputValue.substring(0, indexColon);
            String favorites = inputValue.substring(indexColon);

            // если данное имя уже было введено
            Person addedPers = getPers(name, persons);
            if (addedPers != null) {
                addedPers.setFavorites(favorites);
            } else {
                // строка (может и пустая), в которой хранятся все любимки, с повторениями, с знаками и тп
                Person person = new Person(name, favorites);

                persons.add(person);
            }

        } else {
            System.out.println("Введена неверная строка");
        }
    }

    // проверяет, содержится ли такое имя в списке
    private static Person getPers(String name, ArrayList<Person> persons) {
        for (Person person : persons) {
            if (name.equals(person.getName())) {
                return person;
            }
        }
        return null;
    }

    public static void staticValues(ArrayList<Person> persons) {
        String inputValue = "Антон::: Любимка";

        addPerson(inputValue, persons);

        // а б в з к
        inputValue = "Антон::: з, ,,,, к  б. " +
                "а в";

        addPerson(inputValue, persons);

        // а б з й я
        inputValue = "Антон1: я б з й а";

        addPerson(inputValue, persons);

        // ""
        inputValue = "Антон2:";

        addPerson(inputValue, persons);
    }

    public static void printFav(ArrayList<Person> persons) {
        for (Person person : persons) {
            System.out.println(person);
        }
    }


    // всеобщие "любимки" - что нравится каждому из пользователей;
    // то есть пересечение всех любимок
    private static void printOneTask(ArrayList<Person> persons) {
        TreeSet<String> allFav = Favorites.commonFavorites(persons);
        System.out.println("\n\nвсеобщие \"любимки\" - что нравится каждому из пользователей\t");
        for (String fav : allFav) {
            System.out.print(fav + ", ");
        }
    }

    // все "любимки" - что нравится хотя бы одному из пользователей;
    // просто все любимки без повторений
    private static void printTwoTask(ArrayList<Person> persons) {
        TreeSet<String> allFav = Favorites.allFavorites(persons);
        System.out.println("\n\nвсе любимки - что нравится хотя бы одному из пользователей\t");
        for (String fav : allFav) {
            System.out.print(fav + ", ");
        }
    }

    // для каждого пользователя - что ему нравится, а всем остальным нет;
    // уникальные для каждого пользователя
    private static void printTreeTask(ArrayList<Person> persons) {
        ArrayList<Person> newPersons = Favorites.uniqueFavorites(persons);

        System.out.println("\n\nдля каждого пользователя - что ему нравится, а всем остальным нет\t");
        printFav(newPersons);
    }

    // для каждой «любимки» - скольки пользователям она нравится.
    // любимка - количество пользователей
    private static void printFourTask(ArrayList<Person> persons) {
        HashMap<String, Integer> allFav = Favorites.quantityPersonsToFavorites(persons);
        System.out.println("\n\nдля каждой «любимки» - скольки пользователям она нравится\t");
        for (Map.Entry<String, Integer> entry : allFav.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
