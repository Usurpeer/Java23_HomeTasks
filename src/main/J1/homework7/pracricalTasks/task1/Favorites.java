package j1.homework7.pracricalTasks.task1;

import java.util.*;

public class Favorites {
    // всеобщие "любимки" - что нравится каждому из пользователей;
    // то есть пересечение всех любимок
    public static TreeSet<String> commonFavorites(ArrayList<Person> persons) {
        if (persons == null || persons.isEmpty()){
            throw new IllegalArgumentException("Нулевой список пользователей");
        }
        // все любимки, которые будут изменяться
        TreeSet<String> allFav = allFavorites(persons);
        // любимки которые не везде содержатся и их нужно удалить
        TreeSet<String> removeFav = new TreeSet<>();

        // если любимка из всех любимок не содержится у текущего пользователя, то она удаляется
        for (String fav : allFav) {
            // получение любимок одного пользователя
            for (int i = 0; i < persons.size(); i++) {
                TreeSet<String> persFav = persons.get(i).getFavorites();
                if (!persFav.contains(fav)) {
                    removeFav.add(fav);
                }
            }
        }

        allFav.removeAll(removeFav);
        return allFav;
    }


    // все "любимки" - что нравится хотя бы одному из пользователей;
    // просто все любимки без повторений
    public static TreeSet<String> allFavorites(ArrayList<Person> persons) {
        if (persons == null || persons.isEmpty()){
            throw new IllegalArgumentException("Нулевой список пользователей");
        }
        TreeSet<String> allFav = new TreeSet<>();
        // по всем пользователям
        for (Person person : persons) {
            allFav.addAll(person.getFavorites());
        }
        return allFav;
    }


    // для каждого пользователя - что ему нравится, а всем остальным нет;
    // уникальные для каждого пользователя
    public static ArrayList<Person> uniqueFavorites(ArrayList<Person> persons) {
        if (persons == null || persons.isEmpty()){
            throw new IllegalArgumentException("Нулевой список пользователей");
        }

        ArrayList<Person> uniqueFavPersons = new ArrayList<>();

        // прохождение по всем пользователям
        for (int i = 0; i < persons.size(); i++) {
            TreeSet<String> resFav = new TreeSet<>(); // результат
            TreeSet<String> personFav = persons.get(i).getFavorites();  // любимки текущего пользователя

            // цикл по всем любимкам пользователя, на то, что их нет у других
            for (String fav : personFav) {
                if (checkContains(fav, i, persons)) {
                    resFav.add(fav);
                }
            }

            // добавление имени пользователя и его уникальных любимок
            uniqueFavPersons.add(new Person(persons.get(i).getName(), resFav));
        }
        return uniqueFavPersons;
    }

    // проверка, содержится ли любимка у всех остальных пользователей кроме текущего
    private static boolean checkContains(String favorites, int indexCurrentPers, ArrayList<Person> persons) {
        if(indexCurrentPers < 0){
            throw new IllegalArgumentException("Недопустимый индекс: " + indexCurrentPers);
        }
        if (persons == null || persons.isEmpty()){
            throw new IllegalArgumentException("Нулевой список пользователей");
        }
        for (int i = 0; i < persons.size(); i++) {

            // чтобы не рассмотреть свои же любимки
            if (indexCurrentPers == i) {
                continue;
            }
            TreeSet<String> personFav2 = persons.get(i).getFavorites();

            // если содержится, то отбросить
            if (personFav2.contains(favorites)) {
                return false;
            }

        }

        return true;
    }

    // для каждой «любимки» - скольки пользователям она нравится.
    // любимка - количество пользователей
    public static HashMap<String, Integer> quantityPersonsToFavorites(ArrayList<Person> persons) {
        if (persons == null || persons.isEmpty()){
            throw new IllegalArgumentException("Нулевой список пользователей");
        }

        HashMap<String, Integer> quantityPersToFav = new HashMap<>();

        for (Person person : persons) {
            TreeSet<String> favPers = person.getFavorites();

            for (String fav : favPers) {
                quantityPersToFav.put(fav, quantityPersToFav.getOrDefault(fav, 0) + 1);
            }
        }
        return quantityPersToFav;
    }
}

