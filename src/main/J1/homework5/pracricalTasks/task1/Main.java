package j1.homework5.pracricalTasks.task1;

public class Main {
    public static void main(String[] args) {
        boolean isLatin = true;
        System.out.println(Transliterator.transToLang("Ya ch kh zh sh", isLatin));

        System.out.println(Transliterator.transToLang("Тест транлитерации ЖКЛМНОПРСТУФЦЧКШЩЪбьЭЮЯ", !isLatin));
    }
}
