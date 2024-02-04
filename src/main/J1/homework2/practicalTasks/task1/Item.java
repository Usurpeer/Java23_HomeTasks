package j1.homework2.practicalTasks.task1;

class Item {
    private long article; // артикль товара
    private String title; // название товара
    private double price; // цена товара

    public long getArticle() {
        return article;
    }

    public void setArticle(long article) {
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

    public Item(long article, String title, double price) {
        this.article = article;
        this.title = title;
        this.price = price;
    }

    public Item deepCopyItem() {
        return new Item(article, title, price);
    }
}
