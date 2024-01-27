package homework2.practicalTasks.task1;

import java.util.List;

// класс дилера
class Supplier extends Vendor {
    private final Creator creator;
    private double marginRate; // наценка (коэф)

    public double getMarginRate() {
        return marginRate;
    }

    public void setMarginRate(double marginRate) {
        this.marginRate = marginRate;
    }

    public Supplier(long vendorInn, String vendorTitle, String vendorAddress, double marginRate, Creator creator) {
        setVendorInn(vendorInn);
        setVendorAddress(vendorAddress);

        this.creator = creator;
        this.marginRate = marginRate;

        setTitleVendor(vendorTitle);
        setItems();
    }

    public void setTitleVendor(String vendorTitle) {
        setVendorTitle("Дилер: " + vendorTitle + " (" + creator.getVendorTitle() + ")");
    }

    @Override
    protected void setItems() {
        List<Item> i = deepCloneItems(creator.getItems());
        for (Item it : i) {
            it.setPrice(it.getPrice() + it.getPrice() * (marginRate / 100));
        }
        setListItems(i);
    }
}
