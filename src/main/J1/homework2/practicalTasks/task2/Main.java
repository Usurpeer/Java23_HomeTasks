package homework2.practicalTasks.task2;

import java.util.*;

class Main {
    public static void main(String[] args) {
        startTask();
    }

    public static void startTask() {
        List<Item> items1 = getNomenclature(0);

        List<Item> items2 = getNomenclature(15);

        Date date = new Date();
        var saleShipment1 = new SaleShipmentDocument("docId1", date, "storageTitle1",
                "StorageOwner1", "customer1", items1);
        var saleShipment2 = new SaleShipmentDocument("docId2", date, "storageTitle2",
                "StorageOwner2", "customer2", items2);

        var movingShipment1 = new MovingShipmentDocument("docId1", date, "storageTitle1",
                "StorageOwner1", "movingStorage1", "MovingOwner1", items1);
        var movingShipment2 = new MovingShipmentDocument("docId2", date, "storageTitle2",
                "StorageOwner2", "movingStorage2", "StorageOwner2", items2);


        System.out.println("Выполнение реализованных методов");

        outputData(new ShipmentDocument[]{saleShipment1, saleShipment2, movingShipment1, movingShipment2});

        System.out.println("Конец программы.");
    }

    public static List<Item> getNomenclature(int startVal) {
        List<Item> items = new ArrayList<>(15);

        String itemId = "";
        String acticle = "";
        double price = 15_000.33;
        int countItems = 10;

        for (int i = startVal; i < startVal + 15; i++) {
            itemId = "ItemId: " + (i + 1);
            acticle = "Article: " + (i + 1);
            price += i;
            String title = "Item: " + i;
            countItems++;

            items.add(new Item(itemId, acticle, title, price, countItems));
        }
        return items;
    }

    public static void outputData(ShipmentDocument[] ShipmentDocuments) {
        for (ShipmentDocument doc : ShipmentDocuments) {
            System.out.println("\n----------------------------------------------");

            System.out.println("Суммарная стоимость товаров в документе, для склада: " + doc.getStorageTitle());
            System.out.println("\t" + doc.totalAmount() + "\n\n");

            String itemId = "ItemId: 1";
            System.out.println("Стоимость товара с переданным id: " + itemId);
            System.out.println("\t" + doc.itemAmount(itemId) + "\n\n");


            if (doc.getDocumentType().equals("sale")) {
                System.out.println("Суммарная стоимость товаров, попадающих в список промо-акции.");
                String[] promoArticles = {"Article: 1", "Article: 2", "Article: 4", "Article: 5"};
                System.out.println("Список промо-акции: " + Arrays.toString(promoArticles));
                SaleShipmentDocument saleShipmentDocument = (SaleShipmentDocument) doc;
                System.out.println("\t" + saleShipmentDocument.promoSum(promoArticles, 30) + "\n\n");

                System.out.println("Является ли продажа оптовой для переданного минимального объема.");

                double minQuantity1 = 1000000;
                System.out.println("Для числа: " + minQuantity1);

                SaleShipmentDocument s1 = (SaleShipmentDocument) doc;
                System.out.println("\t" + s1.isWholesale(minQuantity1) + "\n\n");

                double minQuantity2 = 0;
                System.out.println("Для числа: " + minQuantity2);

                SaleShipmentDocument s2 = (SaleShipmentDocument) doc;
                System.out.println("\t" + s2.isWholesale(minQuantity2));

            } else if (doc.getDocumentType().equals("moving")) {
                System.out.println("Суммарная стоимость товаров, попадающих в список промо-акции.");
                String[] promoArticles = {"Article: 1", "Article: 2", "Article: 4", "Article: 5"};
                System.out.println("Список промо-акции: " + Arrays.toString(promoArticles));
                System.out.println("\t" + doc.promoSum(promoArticles) + "\n\n");

                System.out.println("Является ли перемещение внутренним (между складами одного владельца).");

                System.out.println("Для: " + doc.getStorageOwner());
                MovingShipmentDocument m1 = (MovingShipmentDocument) doc;
                System.out.println("\t" + m1.isInternalMovement());
            }
            System.out.println("----------------------------------------------\n");
        }
    }
}