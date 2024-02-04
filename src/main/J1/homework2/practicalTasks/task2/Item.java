package j1.homework2.practicalTasks.task2;

class Item {
    private String itemId; // GUID товара, хотел сделать UUID
    private String article; // артикль товара, хотел сделать long
    private String title; // название товара
    private double price; // цена товара
    private int countItems; // количество товара на складе

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCountItems() {
        return countItems;
    }

    public void setCountItems(int countItems) {
        this.countItems = countItems;
    }

    public Item(String itemId, String article, String title, double price, int countItems) {
        this.itemId = itemId;
        this.article = article;
        this.title = title;
        this.price = price;
        this.countItems = countItems;
    }

    public double itemAmount(){
        return Math.round(countItems * price * 100) / 100.0;
    }
    public Item deepCopyItem() {
        return new Item(itemId, article, title, price, countItems);
    }
}
