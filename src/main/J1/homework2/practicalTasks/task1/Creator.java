package j1.homework2.practicalTasks.task1;

import java.util.List;

// класс производителя
class Creator extends Vendor {
    Creator(long vendorInn, String vendorTitle, String vendorAddress, List<Item> items) {
        setVendorInn(vendorInn);
        setVendorTitle(vendorTitle);
        setVendorAddress(vendorAddress);
        setListItems(List.copyOf(items));

        setItems();
    }

    @Override
    protected void setItems() {
        List<Item> copyItems = deepCloneItems(getItems());
        super.setListItems(copyItems);
    }

    public void addItem(Item item) {
        List<Item> items = getItems();
        items.add(item.deepCopyItem());
        setListItems(items);
    }
}
