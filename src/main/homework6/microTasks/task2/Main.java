package homework6.microTasks.task2;

public class Main {
    public static void main(String[] args) {
        System.out.println("ТЕКС: " + "");
        System.out.println("\t" + Parsing.parseText(""));

        System.out.println("ТЕКС: " + "Да; и; но ((4))");
        System.out.println("\t" + Parsing.parseText("Да; и; но ((4))"));

        System.out.println("ТЕКС: " + "Добрый день!");
        System.out.println("\t" + Parsing.parseText("Добрый день!"));

        System.out.println("ТЕКС: " + "сине-зеленый");
        System.out.println("\t" + Parsing.parseText("сине-зеленый"));

        System.out.println("ТЕКС: " + "Чашка кофе с молоком без сахара.");
        System.out.println("\t" + Parsing.parseText("Чашка кофе с молоком без сахара."));

        System.out.println("ТЕКС: " + "Эх раз, да еще раз, да еще много-много раз!");
        System.out.println("\t" + Parsing.parseText("Эх раз, да еще раз, да еще много-много раз!"));

        System.out.println("ТЕКС: " + "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor" +
                "  incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation  " +
                "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in  " +
                "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non  " +
                "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        System.out.println("\t" + Parsing.parseText("Lorem ipsum dolor sit amet, consectetur adipiscing elit,  sed do " +
                "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud  " +
                "exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in  " +
                "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint " +
                "occaecat  cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. "));
    }
}
