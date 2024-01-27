package homework2.practicalTasks.task1;

import java.util.ArrayList;
import java.util.List;

// класс поставщиков, родительский для производителя и дилера
abstract class Vendor {
    private long vendorInn; // ИНН
    private String vendorTitle; // название поставщика
    private String vendorAddress; // адрес поставщика
    private List<Item> items; // список товаров поставщика

    // получить глубокую копию всех товаров поставщика
    public List<Item> getItems() {
        return deepCloneItems(items);
    }

    public void setListItems(List<Item> items) {
        this.items = items;
    }

    public long getVendorInn() {
        return vendorInn;
    }

    public void setVendorInn(long vendorInn) {
        this.vendorInn = vendorInn;
    }

    public String getVendorTitle() {
        return vendorTitle;
    }

    public void setVendorTitle(String vendorTitle) {
        this.vendorTitle = vendorTitle;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    // получить копию товара по артиклю
    public Item getItemByArticle(long article) {
        for (Item item : items) {
            if (item.getArticle() == article) {
                return item.deepCopyItem();
            }
        }

        return null;
    }

    // получить цену по артиклю
    public double getItemPriceByArticle(long article) {
        for (Item item : items) {
            if (item.getArticle() == article) {
                return item.getPrice();
            }
        }

        return -1;
    }

    // существует ли товар с таким артиклем
    public boolean hasItemByArticle(long article) {
        for (Item item : items) {
            if (item.getArticle() == article) {
                return true;
            }
        }

        return false;
    }

    public List<Item> deepCloneItems(List<Item> items) {
        List<Item> newItems = new ArrayList<>();
        for (Item item : items) {
            newItems.add(item.deepCopyItem());
        }
        return newItems;
    }

    protected abstract void setItems();
}
