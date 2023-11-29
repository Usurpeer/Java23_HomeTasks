package homework2.practicalTasks.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        startTask();
    }

    public static void startTask() {
        List<Item> items = getNomenclature();
        long inn = 100_000_000_000L;

        var creator1 = new Creator(inn + 0, "Creator1", "AddressCr1", List.copyOf(items));
        var creator2 = new Creator(inn + 1, "Creator2", "AddressCr2", List.copyOf(items));
        var creator3 = new Creator(inn + 2, "Creator3", "AddressCr3", List.copyOf(items));

        var supplier1 = new Supplier(inn + 3, "Supplier1", "AddressSup1", 10, creator1);
        var supplier2 = new Supplier(inn + 4, "Supplier2", "AddressSup2", 30, creator2);


        System.out.println("\nВведите артикул. Для выхода - не число.");
        Scanner scanner = new Scanner(System.in);
        String inputValue = scanner.nextLine();

        while (isLong(inputValue)) {
            long article = Long.parseLong(inputValue);
            outputData(article, new Vendor[]{creator1, creator2, creator3, supplier1, supplier2});

            System.out.println("\nВведите артикул. Для выхода - не число.");
            inputValue = scanner.nextLine();
        }
        System.out.println("Конец программы.");
    }

    // список номенклатуры
    public static List<Item> getNomenclature() {
        List<Item> items = new ArrayList<>(15);

        long acticle = 1_000L;
        double price = 15_000;

        for (int i = 0; i < 15; i++) {
            acticle++;
            price++;
            String title = "Item: " + i;

            items.add(new Item(acticle, title, price));
        }
        return items;
    }

    private static boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // вывод информации из вендоров по артиклю
    public static void outputData(long article, Vendor[] vendors) {
        for (Vendor vendor : vendors) {
            System.out.println("\n----------------------------------------------");
            if (vendor.hasItemByArticle(article)) {
                Item item = vendor.getItemByArticle(article);
                System.out.println("Имя товара: " + item.getTitle());
                System.out.println("Его цена: " + item.getPrice());
                System.out.println("\nАдресс поставщика: " + vendor.getVendorAddress());
                System.out.println("Наименование поставщика у поставщика\n\t" + vendor.getVendorTitle() + "");
            } else {
                System.out.println("У поставщика " + vendor.getVendorTitle() + " - нет этого товара.");
            }
            System.out.println("----------------------------------------------\n");
        }
    }
}
