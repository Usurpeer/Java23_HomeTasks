package homework2.practicalTasks.task2;

import java.util.Date;
import java.util.List;

// документ отгрузки со склада (продажа покупателю)
class SaleShipmentDocument extends ShipmentDocument {
    private String customer; // получатель (только для продажи)

    public SaleShipmentDocument(String documentId, Date documentDate, String storageTitle, String storageOwner,
                                String customer, List<Item> items) {
        setDocumentType("sale");

        setDocumentId(documentId);
        setDocumentDate(documentDate);
        setStorageTitle(storageTitle);
        setStorageOwner(storageOwner);
        this.customer = customer;

        setItems(items);
    }

    // Является ли продажа оптовой для переданного минимального объема.
    public boolean isWholesale(double minQuantity) {
        // не нужна тк метод вынесен в отельный класс
        /*String documentType = getDocumentType();

        if (documentType.equals("moving")) {
            return false;
        }*/

        int sumQuantity = 0;
        int[] itemsQuantity = getItemsQuantity();
        for (int quantity : itemsQuantity) {
            if (quantity >= minQuantity) {
                return true;
            }
            // добавил проверку для небольшого ускорения в некоторых случаях, а может и замедления)
            if (sumQuantity >= minQuantity) {
                return true;
            }
            sumQuantity += quantity;
        }
        return sumQuantity >= minQuantity;
    }

}
