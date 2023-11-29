package homework2.practicalTasks.task2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// родительский документ отгрузки со склада
// сделал abstract, просто чтобы не создавали его объекты
abstract class ShipmentDocument {
    private String documentId; // GUID документа
    private Date documentDate; // дата документа
    private String documentType = ""; // тип отгрузки: sale - продажа, moving - перемещение
    private String storageTitle; // название склада отгрузки
    private String storageOwner; // владелец склада отгрузки
    private List<Item> items; // список товаров на складе


    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getStorageTitle() {
        return storageTitle;
    }

    public void setStorageTitle(String storageTitle) {
        this.storageTitle = storageTitle;
    }

    public String getStorageOwner() {
        return storageOwner;
    }

    public void setStorageOwner(String storageOwner) {
        this.storageOwner = storageOwner;
    }


    // получить все товары склада (глубокое копирование)
    public List<Item> getItems() {
        return deepCloneItems(items);
    }

    public void setItems(List<Item> items) {
        this.items = deepCloneItems(items);
    }

    // количество товаров в документе
    public int getItemsCount() {
        return items.size();
    }

    // список GUID товаров
    public String[] getItemsId() {
        String[] itemsId = new String[items.size()];

        int i = 0;
        for (Item item : items) {
            itemsId[i] = item.getItemId();
            i++;
        }
        return itemsId;
    }

    // список артикулов товаров
    public String[] getItemsArticle() {
        String[] itemsArticle = new String[items.size()];

        int i = 0;
        for (Item item : items) {
            itemsArticle[i] = item.getArticle();
            i++;
        }

        return itemsArticle;
    }

    // список названий товаров
    public String[] getItemsTitle() {
        String[] itemsTitle = new String[items.size()];

        int i = 0;
        for (Item item : items) {
            itemsTitle[i] = item.getTitle();
            i++;
        }

        return itemsTitle;
    }

    // список количества штук товаров
    public int[] getItemsQuantity() {
        int[] itemsQuantity = new int[items.size()];

        int i = 0;
        for (Item item : items) {
            itemsQuantity[i] = item.getCountItems();
            i++;
        }

        return itemsQuantity;
    }

    // список цен товаров
    public double[] getItemsPrice() {
        double[] itemsQuantity = new double[items.size()];

        int i = 0;
        for (Item item : items) {
            itemsQuantity[i] = item.getPrice();
            i++;
        }

        return itemsQuantity;
    }

    // глубокое копирование списка всех товаров
    private List<Item> deepCloneItems(List<Item> items) {
        List<Item> newItems = new ArrayList<>();
        for (Item item : items) {
            newItems.add(item.deepCopyItem());
        }
        return newItems;
    }

    // Суммарная стоимость товаров в документе.
    public double totalAmount() {
        int[] itemsQuantity = getItemsQuantity();
        double[] itemsPrice = getItemsPrice();

        double sum = 0;
        for (int i = 0; i < itemsQuantity.length; i++) {
            sum += itemsQuantity[i] * itemsPrice[i];
            // а так разве не будет накапливаться погрешность округления?
            // sum += Math.round(itemsQuantity[i] * itemsPrice[i] * 100) / 100.0;
        }

        sum = Math.round(sum * 100) / 100.0;
        return sum;
    }

    // Стоимость товара с переданным id
    public double itemAmount(String id) {
        for (Item item : items) {
            if (item.getItemId().equals(id)) {
                // Мне кажется округление не нужно
                return Math.round(item.getCountItems() * item.getPrice() * 100) / 100.0;
            }
        }

        return -1;
    }

    // Суммарная стоимость товаров, попадающих в список промо-акций
    // Сказано "товаров", поэтому убрал "break" тк так было бы первого встретившегося товара
    public double promoSum(String[] promoArticles) {
        int[] itemsQuantity = getItemsQuantity();
        double[] itemsPrice = getItemsPrice();
        String[] itemsArticle = getItemsArticle();

        double sum = 0;
        for (int i = 0; i < itemsQuantity.length; i++) {
            for (String promoArticle : promoArticles) {
                if (itemsArticle[i].equals(promoArticle)) {
                    sum += itemsQuantity[i] * itemsPrice[i];
                }
            }
        }

        sum = Math.round(sum * 100) / 100.0;
        return sum;
    }

    public void addItem(Item item) {
        items.add(item.deepCopyItem());
    }
}
